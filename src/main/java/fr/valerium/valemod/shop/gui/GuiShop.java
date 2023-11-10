package fr.valerium.valemod.shop.gui;

import java.util.List;

import fr.valerium.valemod.shop.ShopCategory;
import fr.valerium.valemod.shop.ShopItem;
import fr.valerium.valemod.shop.ShopManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiShop extends GuiScreen {
    private ShopManager shopManager;
    private ShopCategory currentCategory = ShopCategory.ALL;
    private List<ShopItem> itemsToDisplay;

    private int currentPage = 0;
    private int itemsPerPage = 10;

    public GuiShop(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            // Revenir � la page de base (cat�gorie "ALL")
            currentCategory = ShopCategory.ALL;
            currentPage = 0;
        } else if (button.id == 1) {
            // Changer � la cat�gorie "BLOCK"
            currentCategory = ShopCategory.BLOCKS;
            currentPage = 0;
        } else if (button.id == 2) {
            // Changer � la cat�gorie "MINERAL"
            currentCategory = ShopCategory.MINERALS;
            currentPage = 0;
        }
        updateItemsToDisplay();
    }

    private void updateItemsToDisplay() {
        itemsToDisplay = shopManager.getItemsByCategory(currentCategory);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        int xOffset = 10;
        int yOffset = 30;
        int slotSize = 20;

        int startIndex = currentPage * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, itemsToDisplay.size());

        for (int i = startIndex; i < endIndex; i++) {
            ShopItem shopItem = itemsToDisplay.get(i);
            Object itemOrBlock = shopItem.getItemStack();
            ItemStack itemStack = new ItemStack((Item) itemOrBlock);
            this.itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemStack, xOffset, yOffset);
            this.fontRendererObj.drawString(itemStack.getDisplayName(), xOffset + 24, yOffset + 6, 0xFFFFFF);
            this.fontRendererObj.drawString("Prix d'achat : " + shopItem.getBuyPrice(), xOffset + 24, yOffset + 18, 0xFFFF00);
            yOffset += slotSize + 5;
        }

        // Ajoutez les boutons de pagination si n�cessaire
        if (currentPage > 0) {
            this.buttonList.add(new GuiButton(3, xOffset, yOffset, 50, 20, "Page pr�c�dente"));
        }
        if (endIndex < itemsToDisplay.size()) {
            this.buttonList.add(new GuiButton(4, xOffset + 60, yOffset, 50, 20, "Page suivante"));
        }
    }
}