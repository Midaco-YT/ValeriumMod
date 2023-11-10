package fr.valerium.valemod.client.gui.containers;

import fr.valerium.valemod.tiles.TileEntityGoldBarrel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGoldBarrel extends Container {

	private int numRows;
	
	public ContainerGoldBarrel(TileEntityGoldBarrel tile, InventoryPlayer inventory) {
		for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                this.addSlotToContainer(new Slot(tile, j + i * 9, 48 + j * 18, 8 + i * 18));
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

	 public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
	    {
	        ItemStack itemstack = null;
	        Slot slot = (Slot)this.inventorySlots.get(slotId);
	 
	        if(slot != null && slot.getHasStack())
	        {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();
	 
	            if(slotId < 9)
	            {
	                if(!this.mergeItemStack(itemstack1, 9, this.inventorySlots.size(), true))
	                {
	                    return null;
	                }
	            }
	            else if(!this.mergeItemStack(itemstack1, 0, 9, false))
	            {
	                return null;
	            }
	 
	            if(itemstack1.stackSize == 0)
	            {
	                slot.putStack((ItemStack)null);
	            }
	            else
	            {
	                slot.onSlotChanged();
	            }
	        }
	        return itemstack;
	    }


    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
