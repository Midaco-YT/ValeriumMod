package fr.valerium.valemod.common.gui;

import fr.valerium.valemod.tiles.TileEntityStoneCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerStoneCrafter extends Container{

	private TileEntityStoneCrafter tileStoneCrafter;
	  
	  public ContainerStoneCrafter(TileEntityStoneCrafter tile, InventoryPlayer inventory) {
	    this.tileStoneCrafter = tile;
	    addSlotToContainer(new Slot((IInventory)tile, 0, 156, 42));
	    addSlotToContainer(new Slot((IInventory)tile, 1, 84, 42));
	    bindPlayerInventory(inventory);
	  }
	  
	  private void bindPlayerInventory(InventoryPlayer inventory) {
	    int i;
	    for (i = 0; i < 3; i++) {
	      for (int j = 0; j < 9; j++)
	    	  addSlotToContainer(new Slot((IInventory)inventory, j + i * 9 + 9, 48 + j * 18, 131 + i * 18)); 
	    } 
	    for (i = 0; i < 9; i++)
	    	addSlotToContainer(new Slot((IInventory)inventory, i, 48 + i * 18, 189)); 
	  }
	  
	  public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
	    ItemStack stack = null;
	    Slot slots = (Slot) this.inventorySlots.get(slot);
	    if (slots != null && slots.getHasStack()) {
	      ItemStack stack1 = slots.getStack();
	      stack = stack1.copy();
	      if (slot < 6) {
	        if (!mergeItemStack(stack1, 6, 42, true))
	          return null; 
	        slots.onSlotChange(stack1, stack);
	      } 
	      if (slot >= 6) {
	        if (!mergeItemStack(stack1, 0, 5, false))
	          return null; 
	        slots.onSlotChange(stack1, stack);
	      } 
	      if (stack1.stackSize == 0) {
	        slots.putStack((ItemStack)null);
	      } else {
	        slots.onSlotChanged();
	      } 
	      if (stack1.stackSize == stack.stackSize)
	        return null; 
	      slots.onPickupFromSlot(player, stack1);
	    } 
	    return stack;
	  }
	  
	  public void onContainerClosed(EntityPlayer player) {
	    super.onContainerClosed(player);
	    this.tileStoneCrafter.closeInventory();
	  }
	  
	  public boolean canInteractWith(EntityPlayer player) {
	    return this.tileStoneCrafter.isUseableByPlayer(player);
	  }
}
