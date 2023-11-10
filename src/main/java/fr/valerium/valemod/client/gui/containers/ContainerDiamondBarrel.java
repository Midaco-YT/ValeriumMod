package fr.valerium.valemod.client.gui.containers;

import fr.valerium.valemod.tiles.TileEntityDiamondBarrel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDiamondBarrel extends Container {

	private int numRows;
	
	public ContainerDiamondBarrel(TileEntityDiamondBarrel tile, InventoryPlayer inventory) {
		for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 12; j++)
            {
                this.addSlotToContainer(new Slot(tile, j + i * 9, 21 + j * 18, 8 + i * 18));
            }
        }
		
        this.bindPlayerInventory(inventory);
    }


	 private void bindPlayerInventory(InventoryPlayer inventory) {
		    int i;
		    for (i = 0; i < 3; i++) {
		      for (int j = 0; j < 9; j++)
		    	  addSlotToContainer(new Slot((IInventory)inventory, j + i * 9 + 9, 48 + j * 18, 174 + i * 18)); 
		    } 
		    for (i = 0; i < 9; i++)
		    	addSlotToContainer(new Slot((IInventory)inventory, i, 48 + i * 18, 232)); 
		  }

	 @Override
	 public ItemStack transferStackInSlot(EntityPlayer player, int index) {
	     ItemStack itemStack = null;
	     Slot slot = (Slot) inventorySlots.get(index);

	     if (slot != null && slot.getHasStack()) {
	         ItemStack stack = slot.getStack();
	         itemStack = stack.copy();

	         int containerSlots = inventorySlots.size() - player.inventory.mainInventory.length;

	         if (index < containerSlots) {
	             if (!mergeItemStack(stack, containerSlots, inventorySlots.size(), true)) {
	                 return null;
	             }
	         } else if (!mergeItemStack(stack, 0, containerSlots, false)) {
	             return null;
	         }

	         if (stack.stackSize == 0) {
	             slot.putStack(null);
	         } else {
	             slot.onSlotChanged();
	         }

	         if (stack.stackSize == itemStack.stackSize) {
	             return null;
	         }
	         slot.onPickupFromSlot(player, stack);
	     }
	     return itemStack;
	 }



    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
