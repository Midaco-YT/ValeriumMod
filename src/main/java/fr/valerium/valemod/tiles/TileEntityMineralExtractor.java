package fr.valerium.valemod.tiles;

import fr.valerium.valemod.craftings.MineralExtractorRecipies;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;


public class TileEntityMineralExtractor extends TileEntity implements IInventory, ISidedInventory {

	private ItemStack[] inventory;
	private static final int INPUT_SLOT = 0;
    private static final int IRON_SLOT = 1;
    private static final int DIAMOND_SLOT = 2;
    private static final int AMETHYST_SLOT = 3;
    private static final int TITANE_SLOT = 4;
    private static final int VALERIUM_SLOT = 5;
    private static final int FUEL_SLOT = 6;
    private static final int UPGRADE_SLOT = 7;

	private int fuelBurnTime;
	private int currentSmeltTime;
	private int burnTime;
	private int totalSmeltTime;

	public TileEntityMineralExtractor() {
		inventory = new ItemStack[8];
		fuelBurnTime = 0;
		currentSmeltTime = 0;
		burnTime = 0;
		totalSmeltTime = 100;
	}
	
	public boolean isBurning() {
        return this.burnTime > 0;
    }

	public int getCookProgress() {
        if (this.totalSmeltTime == 0) {
            return 0;
        }
        return this.currentSmeltTime * 16 / this.totalSmeltTime;
    }

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	public ItemStack getStackInSlot(int index) {
	    return this.inventory[index];
	}


	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (inventory[slot] != null) {
			ItemStack itemstack;
			if (inventory[slot].stackSize <= amount) {
				itemstack = inventory[slot];
				inventory[slot] = null;
				return itemstack;
			} else {
				itemstack = inventory[slot].splitStack(amount);
				if (inventory[slot].stackSize == 0) {
					inventory[slot] = null;
				}
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
	    super.readFromNBT(compound);
	    NBTTagList itemsTag = compound.getTagList("Items", 10);
	    for (int i = 0; i < itemsTag.tagCount(); ++i) {
	        NBTTagCompound itemCompound = itemsTag.getCompoundTagAt(i);
	        int slot = itemCompound.getInteger("Slot");
	        if (slot >= 0 && slot < this.inventory.length) {
	            this.inventory[slot] = ItemStack.loadItemStackFromNBT(itemCompound);
	        }
	    }
	    this.fuelBurnTime = compound.getInteger("FuelBurnTime");
	    this.currentSmeltTime = compound.getInteger("CurrentSmeltTime");
	    this.burnTime = compound.getInteger("BurnTime");
	    this.totalSmeltTime = compound.getInteger("TotalSmeltTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
	    super.writeToNBT(compound);
	    NBTTagList itemsList = new NBTTagList();
	    for (int i = 0; i < this.inventory.length; ++i) {
	        if (this.inventory[i] != null) {
	            NBTTagCompound itemCompound = new NBTTagCompound();
	            itemCompound.setInteger("Slot", i);
	            this.inventory[i].writeToNBT(itemCompound);
	            itemsList.appendTag(itemCompound);
	        }
	    }
	    compound.setTag("Items", itemsList);
	    compound.setInteger("FuelBurnTime", this.fuelBurnTime);
	    compound.setInteger("CurrentSmeltTime", this.currentSmeltTime);
	    compound.setInteger("BurnTime", this.burnTime);
	    compound.setInteger("TotalSmeltTime", this.totalSmeltTime);
	}

	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
			ItemStack itemstack = inventory[slot];
			inventory[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		inventory[slot] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "Mineral Extractor";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}
		return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (slot == INPUT_SLOT) {
			return stack.getItem() == Item.getItemFromBlock(Blocks.cobblestone);
		} else if (slot == FUEL_SLOT) {
			return TileEntityFurnace.isItemFuel(stack);
		} else if (slot == UPGRADE_SLOT) {
			return stack.getItem() == ModItems.speed_upgrade;
		} else {
			return false;
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if (side == 0) {
			return new int[] { IRON_SLOT, DIAMOND_SLOT, AMETHYST_SLOT, TITANE_SLOT, VALERIUM_SLOT };
		} else {
			return new int[] { INPUT_SLOT, FUEL_SLOT, UPGRADE_SLOT };
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return side == 0 && (slot == IRON_SLOT || slot == DIAMOND_SLOT || slot == AMETHYST_SLOT
				|| slot == TITANE_SLOT || slot == VALERIUM_SLOT);
	}

	@Override
	public void updateEntity() {
	    boolean flag = this.isBurning();
	    boolean inventoryChanged = false;
	    
	    if (this.isBurning()) {
	        --this.burnTime;
	    }
	    
	    if (!this.worldObj.isRemote) {
	        // Check if there is a fuel item in the fuel slot, if so start burning it
	        if (this.burnTime <= 0 && this.canSmelt()) {
	        	System.out.println("L'item recommence à cuire !");
	            ItemStack fuelStack = this.getStackInSlot(FUEL_SLOT);
	            this.burnTime = 200;
	            if (this.isBurning()) {
	                inventoryChanged = true;
	                if (fuelStack != null) {
	                    fuelStack.stackSize--;
	                    if (fuelStack.stackSize == 0) {
	                        this.setInventorySlotContents(FUEL_SLOT, fuelStack.getItem().getContainerItem(fuelStack));
	                    }
	                }
	            }
	        }
	        
	        // Check if the extractor can still smelt and continue doing so if possible
	        if (this.isBurning() && this.canSmelt()) {
	            this.currentSmeltTime++;
	            if (this.currentSmeltTime == this.totalSmeltTime) {
	                this.currentSmeltTime = 0;
	                this.smeltItem();
	                inventoryChanged = true;
	            }
	        } else {
	            this.currentSmeltTime = 0;
	        }
	        
	        // Update the block state depending on whether or not the extractor is burning
	        if (flag != this.isBurning()) {
	            inventoryChanged = true;
	            this.updateBlockState(this.isBurning(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	        }
	    }
	    
	    if (inventoryChanged) {
	        this.markDirty();
	    }
	}

	public void updateBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
	    if (this.worldObj == null || this.worldObj.isRemote) {
	        return;
	    }
	    
	    int metadata = this.getBlockMetadata();
	    boolean isBurning = this.isBurning();
	    
	    // Modifie la métadonnée pour refléter l'état de combustion
	    if (metadata != 0 && !isBurning) {
	        this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 2);
	    } else if (metadata == 0 && isBurning) {
	        this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 1, 2);
	    }
	}

	
	private boolean canSmelt() {
	    ItemStack inputStack = this.getStackInSlot(INPUT_SLOT);
	    if (inputStack == null) {
	        return false;
	    }

	    ItemStack outputStack1 = this.getStackInSlot(IRON_SLOT);
	    ItemStack outputStack2 = this.getStackInSlot(DIAMOND_SLOT);
	    ItemStack outputStack3 = this.getStackInSlot(AMETHYST_SLOT);
	    ItemStack outputStack4 = this.getStackInSlot(TITANE_SLOT);
	    ItemStack outputStack5 = this.getStackInSlot(VALERIUM_SLOT);

	    if (outputStack1 == null || outputStack2 == null || outputStack3 == null || outputStack4 == null
	            || outputStack5 == null) {
	        return true;
	    }

	    if (!outputStack1.getItem().equals(ModItems.iron_particle)
	            || !outputStack2.getItem().equals(ModItems.diamond_particle)
	            || !outputStack3.getItem().equals(ModItems.amethyst_particle)
	            || !outputStack4.getItem().equals(ModItems.titane_particle)
	            || !outputStack5.getItem().equals(ModItems.valerium_particle)) {
	        return false;
	    }

	    int outputStack1Size = outputStack1.stackSize;
	    int outputStack2Size = outputStack2.stackSize;
	    int outputStack3Size = outputStack3.stackSize;
	    int outputStack4Size = outputStack4.stackSize;
	    int outputStack5Size = outputStack5.stackSize;

	    if (outputStack1Size < outputStack1.getMaxStackSize() || outputStack2Size < outputStack2.getMaxStackSize()
	            || outputStack3Size < outputStack3.getMaxStackSize()
	            || outputStack4Size < outputStack4.getMaxStackSize()
	            || outputStack5Size < outputStack5.getMaxStackSize()) {
	        return true;
	    }

	    return false;
	}


	
	private void smeltItem() {
	    ItemStack inputStack = this.getStackInSlot(INPUT_SLOT);
	    ItemStack outputStack1 = this.getStackInSlot(IRON_SLOT);
	    ItemStack outputStack2 = this.getStackInSlot(DIAMOND_SLOT);
	    ItemStack outputStack3 = this.getStackInSlot(AMETHYST_SLOT);
	    ItemStack outputStack4 = this.getStackInSlot(TITANE_SLOT);
	    ItemStack outputStack5 = this.getStackInSlot(VALERIUM_SLOT);
	    System.out.println("L'item commence à cuire !");
	    System.out.println("Temps en tick : " + this.burnTime);
	    if (canSmelt()) {
	        if (this.burnTime == 0) {
	            this.burnTime = 200;
	            this.setInventorySlotContents(FUEL_SLOT,
	                    TileEntityFurnace.getItemBurnTime(this.getStackInSlot(FUEL_SLOT)) == 0 ? null
	                            : new ItemStack(this.getStackInSlot(FUEL_SLOT).getItem(),
	                                    this.getStackInSlot(FUEL_SLOT).stackSize - 1));
	        }
	        if (this.burnTime > 0) {
	            --this.burnTime;

	            if (this.burnTime == 0) {
	                ++this.burnTime;
	                inputStack.stackSize--;
	                ItemStack[] outputs = new ItemStack[]{MineralExtractorRecipies.getInstance().getOutput(inputStack)};

	                if (outputs != null) {
	                    for (int i = 0; i < outputs.length; i++) {
	                        if (outputs[i] != null) {
	                            int slot = IRON_SLOT + i;
	                            if (outputStack1.getItem() == outputs[i].getItem()) {
	                                outputStack1.stackSize += outputs[i].stackSize;
	                            } else if (outputStack2.getItem() == outputs[i].getItem()) {
	                                outputStack2.stackSize += outputs[i].stackSize;
	                            } else if (outputStack3.getItem() == outputs[i].getItem()) {
	                                outputStack3.stackSize += outputs[i].stackSize;
	                            } else if (outputStack4.getItem() == outputs[i].getItem()) {
	                                outputStack4.stackSize += outputs[i].stackSize;
	                            } else if (outputStack5.getItem() == outputs[i].getItem()) {
	                                outputStack5.stackSize += outputs[i].stackSize;
	                            } else if (outputs[slot] == null) {
	                                outputs[slot] = outputs[i].copy();
	                            }
	                        }
	                    }
	                }

	            } else {
	                ++this.burnTime;
	                if (this.burnTime == totalSmeltTime) {
	                    this.burnTime = 0;
	                    ItemStack[] outputs = new ItemStack[]{MineralExtractorRecipies.getInstance().getOutput(inputStack)};
	                    inputStack.stackSize--;
	                    for (int i = 0; i < outputs.length; i++) {
	                        if (outputs[i] != null) {
	                            int slot = IRON_SLOT + i;
	                            if (outputs[slot] == null) {
	                                outputs[slot] = outputs[i].copy();
	                            } else if (outputs[slot].getItem() == outputs[i].getItem()) {
	                                outputs[slot].stackSize += outputs[i].stackSize;
	                            }
	                        }
	                    }

	                    this.setInventorySlotContents(IRON_SLOT, outputs[1]);
	                    this.setInventorySlotContents(DIAMOND_SLOT, outputs[2]);
	                    this.setInventorySlotContents(AMETHYST_SLOT, outputs[3]);
	                    this.setInventorySlotContents(TITANE_SLOT, outputs[4]);
	                    this.setInventorySlotContents(VALERIUM_SLOT, outputs[5]);
	                }
	                System.out.println("Cuisson terminée !");
	            }
	        } else {
	            this.burnTime = 0;
	        }
	    }
	}
}