package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;

public class ItemMeteor extends Item {

	public ItemMeteor() {
		setUnlocalizedName("meteor");
		setCreativeTab(ModVale.tabValerium);
		setTextureName("valerium:materials/meteor");
	}
}
