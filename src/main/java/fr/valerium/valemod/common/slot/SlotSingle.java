package fr.valerium.valemod.common.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSingle extends Slot {
  ItemStack item;
  
  public SlotSingle(IInventory inventory, int id, int x, int y, Item item) {
    super(inventory, id, x, y);
    this.item = new ItemStack(item);
  }
  
  public boolean isItemValid(ItemStack stack) {
    if (stack.getItem() == this.item.getItem())
      return true; 
    return false;
  }
  
  public ItemStack decrStackSize(int amount) {
    return super.decrStackSize(amount);
  }
  
  public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
	  onCrafting(stack);
    super.onPickupFromSlot(player, stack);
  }
}
