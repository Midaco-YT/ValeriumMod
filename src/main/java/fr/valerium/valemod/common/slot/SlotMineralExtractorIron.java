package fr.valerium.valemod.common.slot;

import fr.valerium.valemod.items.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMineralExtractorIron extends Slot {
    
    public SlotMineralExtractorIron(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == ModItems.iron_particle;
    }
}
