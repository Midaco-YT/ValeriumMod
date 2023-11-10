package fr.valerium.valemod.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBag extends Slot {
  public SlotBag(IInventory inventory, int id, int x, int y) {
    super(inventory, id, x, y);
  }
  
  public boolean isItemValid(ItemStack stack) {
    if (!(stack.getItem() instanceof fr.valerium.valemod.items.ItemBag))
      return true; 
    return false;
  }
}
