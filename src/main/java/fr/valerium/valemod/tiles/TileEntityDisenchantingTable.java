package fr.valerium.valemod.tiles;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDisenchantingTable extends TileEntity implements IInventory {

	private ItemStack[] inventory;
	private int selectedEnchantment;

	public TileEntityDisenchantingTable() {
		inventory = new ItemStack[2];
	}
	
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		markDirty();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (slot == 0 && getStackInSlot(0).getItem() == Items.enchanted_book) {
			setInventorySlotContents(1, getStackInSlot(0));
			setInventorySlotContents(0, null);
			return getStackInSlot(1);
		} else {
			if (inventory[slot] != null) {
				ItemStack stack;
				if (inventory[slot].stackSize <= amount) {
					stack = inventory[slot];
					inventory[slot] = null;
					return stack;
				} else {
					stack = inventory[slot].splitStack(amount);
					if (inventory[slot].stackSize == 0) {
						inventory[slot] = null;
					}
					return stack;
				}
			} else {
				return null;
			}
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
			ItemStack stack = inventory[slot];
			inventory[slot] = null;
			return stack;
		} else {
			return null;
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false
				: ((player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		// On peut placer les items enchanté dans le slot 0 et les livres dans le slot
		// 1.
		if (slot == 0) {
			return stack.isItemEnchanted();
		} else if (slot == 1) {
			return stack.getItem() == Items.enchanted_book;
		} else {
			return false;
		}
	}

	@Override
	public String getInventoryName() {
		return "container.enchantmentTransfer";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList items = compound.getTagList("ItemInventory", 10);
		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
			int slot = item.getByte("Slot");
			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);
			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag("ItemInventory", items);
	}

	public void transferEnchantments(Enchantment data) {
		ItemStack enchantedItem = getStackInSlot(0);
		ItemStack book = getStackInSlot(1);

		if (enchantedItem == null || book == null) {
			return;
		}

		NBTTagList enchantmentsTag = enchantedItem.getEnchantmentTagList();
		for (int i = 0; i < enchantmentsTag.tagCount(); i++) {
			NBTTagCompound enchantmentTag = enchantmentsTag.getCompoundTagAt(i);
			int enchantId = enchantmentTag.getShort("id");
			int enchantLvl = enchantmentTag.getShort("lvl");
			Enchantment enchant = Enchantment.enchantmentsList[enchantId];
			book.addEnchantment(enchant, enchantLvl);
			enchantmentsTag.removeTag(i);
		}
		enchantedItem.setTagInfo("ench", enchantmentsTag);
		setInventorySlotContents(0, enchantedItem);
		setInventorySlotContents(1, book);
	}

	public Enchantment getSelectedEnchantment() {
		ItemStack enchantedItem = getStackInSlot(0);
		if (enchantedItem != null && enchantedItem.getItem() != Items.book) {
			NBTTagList enchantmentsTag = enchantedItem.getEnchantmentTagList();
			for (int i = 0; i < enchantmentsTag.tagCount(); i++) {
				NBTTagCompound enchantmentTag = enchantmentsTag.getCompoundTagAt(i);
				int enchantId = enchantmentTag.getShort("id");
				Enchantment enchant = Enchantment.enchantmentsList[enchantId];
				if (enchant.equals(selectedEnchantment)) {
					return enchant;
				}
			}
		}
		return null;
	}

	public void setSelectedEnchantment(int enchant) {
		this.selectedEnchantment = enchant;
	}
}
