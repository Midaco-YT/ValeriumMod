package fr.valerium.valemod.common.gui;

import fr.valerium.valemod.common.slot.SlotMineralExtractorAmethyst;
import fr.valerium.valemod.common.slot.SlotMineralExtractorDiamond;
import fr.valerium.valemod.common.slot.SlotMineralExtractorFuel;
import fr.valerium.valemod.common.slot.SlotMineralExtractorIron;
import fr.valerium.valemod.common.slot.SlotMineralExtractorTitane;
import fr.valerium.valemod.common.slot.SlotMineralExtractorUpgrade;
import fr.valerium.valemod.common.slot.SlotMineralExtractorValerium;
import fr.valerium.valemod.tiles.TileEntityMineralExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMineralExtractor extends Container{

	private TileEntityMineralExtractor tileMineralExtractor;

    public ContainerMineralExtractor(TileEntityMineralExtractor tile, InventoryPlayer inventory) {
        this.tileMineralExtractor = tile;
        
        // Input Slot (Middle, Top)
        addSlotToContainer(new Slot((IInventory)tile, 0, 120, 54));
        
        // Output Slots (Left, Bottom and Right, Bottom)
        addSlotToContainer(new SlotMineralExtractorIron((IInventory)tile, 1, 84, 109));
        addSlotToContainer(new SlotMineralExtractorDiamond((IInventory)tile, 2, 102, 109));
        addSlotToContainer(new SlotMineralExtractorAmethyst((IInventory)tile, 3, 120, 109));
        addSlotToContainer(new SlotMineralExtractorTitane((IInventory)tile, 4, 138, 109));
        addSlotToContainer(new SlotMineralExtractorValerium((IInventory)tile, 5, 156, 109));
        
        // Fuel Slot (Left, Top)
        addSlotToContainer(new SlotMineralExtractorFuel((IInventory)tile, 6, 66, 61));
        
        // Upgrade Slot (Left, Center)
        addSlotToContainer(new SlotMineralExtractorUpgrade((IInventory)tile, 7, 66, 79));
        
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
	      if (slot < 8) {
	        if (!mergeItemStack(stack1, 8, 42, true))
	          return null; 
	        slots.onSlotChange(stack1, stack);
	      } 
	      if (slot >= 8) {
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
	    this.tileMineralExtractor.closeInventory();
	  }
	  
	  public boolean canInteractWith(EntityPlayer player) {
	    return this.tileMineralExtractor.isUseableByPlayer(player);
	  }
}
