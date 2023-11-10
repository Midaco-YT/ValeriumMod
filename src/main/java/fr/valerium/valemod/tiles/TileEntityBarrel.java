package fr.valerium.valemod.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityBarrel extends TileEntity implements IInventory {

	private ItemStack[] inventory = new ItemStack[27];
    private String customName;
    private IInventory playerInventory;
    private boolean isOpen;
    private int blockMetadata;
    private World world;
    
    
    
    
    public TileEntityBarrel() {
        this.blockMetadata = 0;
        this.world = world;
    }

    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
    	super.readFromNBT(compound);
    	NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        
        if (compound.hasKey("CustomName"))
        {
            this.customName = compound.getString("CustomName");
        }
        
        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot");
     
            if (j >= 0 && j < this.inventory.length)
            {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }
     
    @Override
    public void writeToNBT(NBTTagCompound compound) {
    	super.writeToNBT(compound);
    	NBTTagList nbttaglist = new NBTTagList();
    	
    	for (int i = 0; i < this.inventory.length; i++) {
    		
    		if (this.inventory[i] != null) {
    			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
    			nbttagcompound1.setByte("Slot", (byte)i);
    			this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
        	}
    	}
    	
    	compound.setTag("Items", nbttaglist);
    }
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotId) {
		return inventory[slotId];
	}

	public ItemStack decrStackSize(int slotIndex, int amount) {
		if (this.inventory[slotIndex] != null) {
			if ((this.inventory[slotIndex]).stackSize <= amount) {
				ItemStack itemStack = this.inventory[slotIndex];
				this.inventory[slotIndex] = null;
				markDirty();
				return itemStack;
			}
			ItemStack itemstack = this.inventory[slotIndex].splitStack(amount);
			if ((this.inventory[slotIndex]).stackSize == 0)
				this.inventory[slotIndex] = null;
			markDirty();
			return itemstack;
		}
		return null;
	}

	@Override
    public ItemStack getStackInSlotOnClosing(int slotId)
    {
        if (this.inventory[slotId] != null)
        {
            ItemStack itemstack = this.inventory[slotId];
            this.inventory[slotId] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack stack) {
		this.inventory[slotIndex] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
		markDirty();
	}

	public String getInventoryName() {
		return "TileEntity.Barrel";
	}

	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}
	
	public void updateState() {
	    if (isOpen) {
	        blockMetadata = 1;
	    } else {
	        blockMetadata = 0;
	    }
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openInventory() {
		
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setOpen(boolean open) {
	    this.isOpen = open;
	}

}
