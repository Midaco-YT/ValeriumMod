package fr.valerium.valemod.utils;

import net.minecraft.item.ItemStack;

public class CustomItemStack {
	private ItemStack itemStack;
	private int buyPrice;
	private int sellPrice;

	public CustomItemStack(ItemStack itemStack, int buyPrice, int sellPrice) {
		this.itemStack = itemStack;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}
}
