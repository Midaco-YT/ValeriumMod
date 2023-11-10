package fr.valerium.valemod.common.slot;

import fr.valerium.valemod.items.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMineralExtractorDiamond extends Slot {
    
    public SlotMineralExtractorDiamond(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == ModItems.diamond_particle;
    }
}
