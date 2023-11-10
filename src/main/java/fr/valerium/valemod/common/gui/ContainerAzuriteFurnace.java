package fr.valerium.valemod.common.gui;

import fr.valerium.valemod.common.slot.SlotSingle;
import fr.valerium.valemod.items.ModItems;
import fr.valerium.valemod.tiles.TileEntityAzuriteFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerAzuriteFurnace extends Container {
  public TileEntityAzuriteFurnace tile;
  
  public ContainerAzuriteFurnace(TileEntityAzuriteFurnace tile, InventoryPlayer inventory) {
    this.tile = tile;
    addSlotToContainer((Slot)new SlotSingle((IInventory)tile, 0, 19, 35, (Item)ModItems.sulfur_crystal));
    addSlotToContainer(new Slot((IInventory)tile, 1, 56, 17));
    addSlotToContainer(new Slot((IInventory)tile, 2, 56, 53));
    bindPlayerInventory(inventory);
  }
  
  private void bindPlayerInventory(InventoryPlayer inventory) {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
    	  addSlotToContainer(new Slot((IInventory)inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
    	addSlotToContainer(new Slot((IInventory)inventory, i, 8 + i * 18, 142)); 
  }
  
  public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
    ItemStack stack = null;
    Slot slots = (Slot) this.inventorySlots.get(slot);
    if (slots != null && slots.getHasStack()) {
      ItemStack stack1 = slots.getStack();
      stack = stack1.copy();
      if (slot < 4) {
        if (!mergeItemStack(stack1, 4, 39, true))
          return null; 
        slots.onSlotChange(stack1, stack);
      } 
      if (slot >= 4) {
        if (FurnaceRecipes.smelting().getSmeltingResult(stack1) != null && 
          !mergeItemStack(stack1, 1, 2, true))
          return null; 
        if (TileEntityFurnace.isItemFuel(stack1) && 
          !mergeItemStack(stack1, 2, 3, true))
          return null; 
        if (stack.getItem() instanceof fr.valerium.valemod.items.materials.SulfurCrystal && 
          !mergeItemStack(stack1, 0, 1, true))
          return null; 
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
    super.onContainerClosed	(player);
    this.tile.closeInventory();
  }
  
  public boolean canInteractWith(EntityPlayer player) {
    return this.tile.isUseableByPlayer(player);
  }
}
