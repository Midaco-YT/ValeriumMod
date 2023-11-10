package fr.valerium.valemod.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.craftings.StoneCrafterRecipies;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStoneCrafter extends TileEntity implements IInventory, ISidedInventory {
	private ItemStack[] content = new ItemStack[6];

	private int workingTime = 0;

	private int timeNeeded = 200;

	public int getSizeInventory() {
		return this.content.length;
	}

	public ItemStack decrStackSize(int slotIndex, int amount) {
		if (this.content[slotIndex] != null) {
			if ((this.content[slotIndex]).stackSize <= amount) {
				ItemStack itemStack = this.content[slotIndex];
				this.content[slotIndex] = null;
				markDirty();
				return itemStack;
			}
			ItemStack itemstack = this.content[slotIndex].splitStack(amount);
			if ((this.content[slotIndex]).stackSize == 0)
				this.content[slotIndex] = null;
			markDirty();
			return itemstack;
		}
		return null;
	}

	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		if (this.content[slotIndex] != null) {
			ItemStack itemstack = this.content[slotIndex];
			this.content[slotIndex] = null;
			return itemstack;
		}
		return null;
	}

	public void setInventorySlotContents(int slotIndex, ItemStack stack) {
		this.content[slotIndex] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
		markDirty();
	}

	public String getInventoryName() {
		return "TileEntity.PaladiumMachine";
	}

	public boolean hasCustomInventoryName() {
		return false;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return (this.worldObj.getTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this) ? false
						: ((player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D,
								this.zCoord + 0.5D) <= 64.0D));
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return !(slot == 5);
	}

	public boolean isBurning() {
		return (this.workingTime > 0);
	}

	private boolean canSmelt() {
		if (this.content[0] == null || this.content[1] == null || this.content[2] == null || this.content[3] == null
				|| this.content[4] == null)
			return false;
		ItemStack itemstack = StoneCrafterRecipies.getManager().getSmeltingResult(new ItemStack[] { this.content[0],
				this.content[1], this.content[2], this.content[3], this.content[4] });
		if (itemstack == null)
			return false;
		if (this.content[5] == null)
			return true;
		if (!this.content[5].isItemEqual(itemstack))
			return false;
		int result = (this.content[5]).stackSize + itemstack.stackSize;
		return (result <= getInventoryStackLimit() && result <= this.content[5].getMaxStackSize());
	}

	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.content.length; i++) {
			if (this.content[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.content[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag((NBTBase) nbttagcompound1);
			}
		}
		compound.setTag("Items", (NBTBase) nbttaglist);
		compound.setShort("workingTime", (short) this.workingTime);
		compound.setShort("workingTimeNeeded", (short) this.timeNeeded);
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgress() {
		return this.workingTime * 16 / this.timeNeeded;
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.content = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xFF;
			if (j >= 0 && j < this.content.length)
				this.content[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
		this.workingTime = compound.getShort("workingTime");
		this.timeNeeded = compound.getShort("workingTimeNeeded");
	}

	public ItemStack getStackInSlot(int slot) {
		return this.content[slot];
	}

	public void updateEntity() {
		if (isBurning() && canSmelt())
			this.workingTime++;
		if (canSmelt() && !isBurning())
			this.workingTime = 1;
		if (canSmelt() && this.workingTime == this.timeNeeded) {
			smeltItem();
			this.workingTime = 0;
		}
		if (!canSmelt())
			this.workingTime = 0;
	}

	public void smeltItem() {
		if (canSmelt()) {
			ItemStack itemstack = StoneCrafterRecipies.getManager().getSmeltingResult(new ItemStack[] {
					this.content[0], this.content[1], this.content[2], this.content[3], this.content[4] });
			if (this.content[5] == null) {
				this.content[5] = itemstack.copy();
			} else if (this.content[5].getItem() == itemstack.getItem()) {
				(this.content[5]).stackSize += itemstack.stackSize;
			}
			(this.content[0]).stackSize--;
			(this.content[1]).stackSize--;
			(this.content[2]).stackSize--;
			(this.content[3]).stackSize--;
			(this.content[4]).stackSize--;
			if ((this.content[0]).stackSize <= 0)
				this.content[0] = null;
			if ((this.content[1]).stackSize <= 0)
				this.content[1] = null;
			if ((this.content[2]).stackSize <= 0)
				this.content[2] = null;
			if ((this.content[3]).stackSize <= 0)
				this.content[3] = null;
			if ((this.content[4]).stackSize <= 0)
				this.content[4] = null;
		}
	}

	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[0];
	}

	public boolean canInsertItem(int side, ItemStack stack, int slot) {
		return false;
	}

	public boolean canExtractItem(int side, ItemStack stack, int slot) {
		return false;
	}
}