package fr.valerium.valemod.shop.ui;

import java.util.ArrayList;
import java.util.List;

import fr.valerium.valemod.shop.ShopCategory;
import fr.valerium.valemod.shop.node.ItemSellNode;
import fr.valerium.valemod.shop.utils.ShopPage;
import fr.valerium.valemod.shop.utils.UIShopPage;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class UIShopBlocksPage extends UIShopPage {

	public UIShopBlocksPage() {
		super(ShopPage.BLOCKS);
	}

	public void preDraw(int mouseX, int mouseY, float ticks) {
	}
	
	protected List<ItemSellNode> displayCategoryButtons(ShopCategory category, double startX, double startY, double buttonSize, double buttonSpacing, int mouseX, int mouseY) {
	    List<ItemSellNode> buttons = new ArrayList<>();

	    if (category == ShopCategory.BLOCKS) {
	        // Ajoutez vos boutons spécifiques pour la catégorie "blocks" ici
	        // Par exemple :
	        buttons.add(new ItemSellNode(startX, startY, buttonSize, new ItemStack(Items.stick))); // Remplacez "Items.STONE" par l'ItemStack de votre choix
	    } else {
	        // Utilisez la méthode de la classe parent pour les autres catégories
	        buttons.addAll(super.displayCategoryButtons(category, startX, startY, buttonSize, buttonSpacing, mc, mouseX, mouseY));
	    }

	    return buttons;
	}

}
