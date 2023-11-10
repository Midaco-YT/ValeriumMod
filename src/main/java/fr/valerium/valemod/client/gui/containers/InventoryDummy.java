package fr.valerium.valemod.client.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryDummy implements IInventory {

	ItemStack[] content = new ItemStack[2];

	public int getSizeInventory() {
		return 1;
	}

	public ItemStack getStackInSlot(int slot) {
		return this.content[slot];
	}

	public ItemStack decrStackSize(int slotIndex, int amount) {
		if (this.content[slotIndex] != null) {
			ItemStack itemstack;
			if (this.content[slotIndex].stackSize <= amount) {
				itemstack = this.content[slotIndex];
				this.content[slotIndex] = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.content[slotIndex].splitStack(amount);
				if (this.content[slotIndex].stackSize == 0) {
					this.content[slotIndex] = null;
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		if (this.content[slotIndex] != null) {
			ItemStack itemstack = this.content[slotIndex];
			this.content[slotIndex] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int slotIndex, ItemStack stack) {
		this.content[slotIndex] = stack;
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	public String getInventoryName() {
		return "TileEntity.GuardianKeeper";
	}

	public boolean hasCustomInventoryName() {
		return false;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void markDirty() {
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	public void readFromNBT(NBTTagCompound comp) {
	}

	public void writeToNBT(NBTTagCompound comp) {
	}
}