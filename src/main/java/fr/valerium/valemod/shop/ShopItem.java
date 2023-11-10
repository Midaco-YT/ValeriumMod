package fr.valerium.valemod.shop;

import net.minecraft.item.ItemStack;

public class ShopItem {
    private ItemStack itemStack; // L'objet ou le bloc à vendre
    private int buyPrice;
    private int sellPrice;
    private ShopCategory category;

    public ShopItem(ItemStack itemStack, int buyPrice, int sellPrice, ShopCategory category) {
        this.itemStack = itemStack;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.category = category;
    }

    // Méthode pour obtenir le prix d'achat
    public int getBuyPrice() {
        return buyPrice;
    }

    // Méthode pour obtenir le prix de vente
    public int getSellPrice() {
        return sellPrice;
    }

    // Méthode pour obtenir la catégorie
    public ShopCategory getCategory() {
        return category;
    }
    
    public ItemStack getItemStack() {
        return itemStack;
    }


    // Autres méthodes et champs de la classe
}
