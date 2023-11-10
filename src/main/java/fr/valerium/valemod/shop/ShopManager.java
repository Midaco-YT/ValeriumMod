package fr.valerium.valemod.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class ShopManager {
    private Map<Object, ShopItem> shopItems;

    public ShopManager() {
        shopItems = new HashMap<Object, ShopItem>();
    }

    // Ajouter un objet dans le shop avec une cat�gorie sp�cifi�e
    public void addItemToShop(ItemStack itemStack, int buyPrice, int sellPrice, ShopCategory category) {
        ShopItem shopItem = new ShopItem(itemStack, buyPrice, sellPrice, category);
        shopItems.put(itemStack, shopItem);
    }

    // Obtenez le prix d'achat d'un objet
    public int getBuyPrice(Object itemOrBlock) {
        ShopItem shopItem = shopItems.get(itemOrBlock);
        if (shopItem != null) {
            return shopItem.getBuyPrice();
        }
        return 0; // Prix par d�faut s'il n'est pas trouv�
    }

    // Obtenez le prix de vente d'un objet
    public int getSellPrice(Object itemOrBlock) {
        ShopItem shopItem = shopItems.get(itemOrBlock);
        if (shopItem != null) {
            return shopItem.getSellPrice();
        }
        return 0; // Prix par d�faut s'il n'est pas trouv�
    }

    // Obtenez la cat�gorie d'un objet
    public ShopCategory getCategory(Object itemOrBlock) {
        ShopItem shopItem = shopItems.get(itemOrBlock);
        if (shopItem != null) {
            return shopItem.getCategory();
        }
        return ShopCategory.ALL; // Cat�gorie par d�faut s'il n'est pas trouv�
    }
    
    public List<ShopItem> getItemsByCategory(ShopCategory category) {
        List<ShopItem> items = new ArrayList<>();
        for (ShopItem item : shopItems.values()) {
            if (item.getCategory() == category) {
                items.add(item);
            }
        }
        return items;
    }

    // Autres m�thodes pour g�rer le GUI, les achats, etc.
}
