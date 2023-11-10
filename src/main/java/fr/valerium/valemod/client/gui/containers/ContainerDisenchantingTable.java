package fr.valerium.valemod.client.gui.containers;

import fr.valerium.valemod.common.slot.SlotBook;
import fr.valerium.valemod.common.slot.SlotResult;
import fr.valerium.valemod.common.slot.ToolAndArmorSlot;
import fr.valerium.valemod.tiles.TileEntityDisenchantingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDisenchantingTable extends Container {

	private final TileEntityDisenchantingTable tileEntity;

	public ContainerDisenchantingTable(InventoryPlayer playerInventory, TileEntityDisenchantingTable tileEntity) {
		this.tileEntity = tileEntity;

		addSlotToContainer(new ToolAndArmorSlot(tileEntity, 0, 71, 41));
		addSlotToContainer(new SlotBook(tileEntity, 1, 106, 41));
		addSlotToContainer(new SlotResult(tileEntity, 1, 150, 41));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 88 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 146));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		  Slot slot = (Slot) this.inventorySlots.get(index);

		  if (slot != null && slot.getHasStack()) {
		    ItemStack itemstack1 = slot.getStack();
		    itemstack = itemstack1.copy();

		    if (index < 2) {
		        if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
		            return null;
		        }
		    } else if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
		        return null;
		    }

		    if (itemstack1.stackSize == 0) {
		        slot.putStack(null);
		    } else {
		        slot.onSlotChanged();
		    }

		    if (itemstack1.stackSize == itemstack.stackSize) {
		        return null;
		    }
		    slot.onPickupFromSlot(playerIn, itemstack1);
		  }
		  return itemstack;
	}
}
