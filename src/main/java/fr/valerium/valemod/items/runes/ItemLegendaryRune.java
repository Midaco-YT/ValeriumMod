package fr.valerium.valemod.items.runes;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemLegendaryRune extends Item {

	public ItemLegendaryRune() {
		setUnlocalizedName("legendary_rune");
		setCreativeTab(ModVale.tabAlchimist);
		setTextureName("valerium:rune/legendary_rune");
	}
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
    }
}
