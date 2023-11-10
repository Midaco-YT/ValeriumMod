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

    // Ajouter un objet dans le shop avec une catégorie spécifiée
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
        return 0; // Prix par défaut s'il n'est pas trouvé
    }

    // Obtenez le prix de vente d'un objet
    public int getSellPrice(Object itemOrBlock) {
        ShopItem shopItem = shopItems.get(itemOrBlock);
        if (shopItem != null) {
            return shopItem.getSellPrice();
        }
        return 0; // Prix par défaut s'il n'est pas trouvé
    }

    // Obtenez la catégorie d'un objet
    public ShopCategory getCategory(Object itemOrBlock) {
        ShopItem shopItem = shopItems.get(itemOrBlock);
        if (shopItem != null) {
            return shopItem.getCategory();
        }
        return ShopCategory.ALL; // Catégorie par défaut s'il n'est pas trouvé
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

    // Autres méthodes pour gérer le GUI, les achats, etc.
}
