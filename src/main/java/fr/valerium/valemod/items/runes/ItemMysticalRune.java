package fr.valerium.valemod.items.runes;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMysticalRune extends Item {

	public ItemMysticalRune() {
		setUnlocalizedName("mystical_rune");
		setCreativeTab(ModVale.tabAlchimist);
		setTextureName("valerium:rune/mystical_rune");
	}
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
    }
}
