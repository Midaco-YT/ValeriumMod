package fr.valerium.valemod.common.slot;

import fr.valerium.valemod.tiles.TileEntityDisenchantingTable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResult extends Slot {
    private final TileEntityDisenchantingTable tileEntity;

    public SlotResult(TileEntityDisenchantingTable tileEntity, int index, int xPosition, int yPosition) {
        super(tileEntity, index, xPosition, yPosition);
        this.tileEntity = tileEntity;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
		Enchantment enchantment = tileEntity.getSelectedEnchantment();
    if (enchantment != null && stack.getItem() == Items.book) {
    stack.addEnchantment(enchantment, 1);
    }
    super.onPickupFromSlot(playerIn, stack);
    }

}
