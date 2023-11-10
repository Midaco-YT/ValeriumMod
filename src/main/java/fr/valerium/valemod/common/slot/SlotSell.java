package fr.valerium.valemod.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSell extends Slot {
	   public SlotSell(IInventory inventory, int id, int x, int y) {
	      super(inventory, id, x, y);
	   }

	   public boolean isItemValid(ItemStack stack) {
	      return true;
	   }
	}
