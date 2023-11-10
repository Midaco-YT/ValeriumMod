package fr.valerium.valemod.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGrinder extends Slot {
  public SlotGrinder(IInventory inventory, int id, int x, int y) {
    super(inventory, id, x, y);
  }
  
  public boolean isItemValid(ItemStack stack) {
    if (stack.getItem() instanceof fr.valerium.valemod.items.runes.ItemMysticalRune)
      return true; 
    if (stack.getItem() instanceof fr.valerium.valemod.items.runes.ItemLegendaryRune)
      return true; 
    if (stack.getItem() instanceof fr.valerium.valemod.items.rings.ItemFortuneRing)
      return true; 
    if (stack.getItem() instanceof fr.valerium.valemod.items.rings.ItemRandomRing)
      return true; 
    return false;
  }
  
  public ItemStack decrStackSize(int amount) {
    return super.decrStackSize(amount);
  }
  
  public int func_75219_a() {
    return 64;
  }
  
  public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
	  onCrafting(stack);
    super.onPickupFromSlot(player, stack);
  }
}
