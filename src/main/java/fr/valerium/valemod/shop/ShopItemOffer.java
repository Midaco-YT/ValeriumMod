package fr.valerium.valemod.shop;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ShopItemOffer {

	public static ShopManager shopManager = new ShopManager();
	
	public static void registerOffers() {
		
		shopManager.addItemToShop(new ItemStack(Items.stick), 10, 5, ShopCategory.LOOT_MOB);
	}
}
