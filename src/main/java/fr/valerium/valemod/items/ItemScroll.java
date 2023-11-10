package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemScroll extends Item {

	public ItemScroll() {
		setUnlocalizedName("scroll");
		setCreativeTab(ModVale.tabValerium);
		setTextureName("valerium:scroll");
	}
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
    }
}
