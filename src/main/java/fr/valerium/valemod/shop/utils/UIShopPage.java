package fr.valerium.valemod.shop.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.valerium.indium.nodes.abstracts.ANode;
import fr.valerium.indium.ui.UI;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import fr.valerium.valemod.shop.ShopCategory;
import fr.valerium.valemod.shop.ShopItem;
import fr.valerium.valemod.shop.ShopManager;
import fr.valerium.valemod.shop.node.FlatCloseNode;
import fr.valerium.valemod.shop.node.ItemSellNode;
import fr.valerium.valemod.shop.node.NavbarNode;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class UIShopPage extends UI {
  public static final Color WHITE = new Color(240, 240, 240);
  
  public static final Color ORANGE = new Color(255, 111, 0);
  
  public static final Color BLACK = new Color(28, 28, 28);
  
  public static final Color LIGHT_BLACK = new Color(48, 48, 48);
  
  public static final Color BORDER = new Color(193, 193, 193);
  
  public static final Color GREEN = new Color(48, 203, 31);
  
  public static final Color RED = new Color(239, 57, 38);
  
  public static final Color GRAY = new Color(112, 112, 112);
  
  public static final Color LIGHT_GRAY = new Color(210, 210, 210);
  
  public static final Color MAGENTA = new Color(139, 0, 139);
  
  private static final ResourceLocation LOGO_TEXTURE = new ResourceLocation("shop", "textures/gui/navbar/logo.png");
  
  private static long pbValue;
  
  private final ShopPage page;
  
  public double x;
  
  public double y;
  
  private ShopManager shopManager;
  
  public UIShopPage(ShopPage page) {
    this.page = page;
  }
  
  public void initGui() {
    super.initGui();
    //ShopMod.proxy.getNetwork().sendToServer((IMessage)new BBPacketShopBalance());
    double baseRatio = 1.7777777777777777D;
    double ratio = this.mc.displayWidth / this.mc.displayHeight;
    if (ratio < 1.7777777777777777D || Math.abs(ratio - 1.7777777777777777D) < 0.005D) {
      this.x = 0.0D;
      this.y = (this.mc.displayHeight - height(100.0D)) / 2.0D;
    } else {
      this.x = (this.mc.displayWidth - width(100.0D)) / 2.0D;
      this.y = 0.0D;
    } 
    initNavbar();
  }
  
  private void initNavbar() {
	    double navbarHeight = getNavbarHeight();
	    double x = width(9.43F) + width(77.2F); // Ajoutez une valeur de décalage à la position horizontale
	    double y = this.y + width(16.0F); // Utilisez la valeur Y de base
	    double offsetY = 0.0D;

	    List<NavbarNode> navbarNodes = new ArrayList<>();

	    for (ShopPage navItem : ShopPage.getValues()) {
	        try {
	            if (!navItem.getPage().isValid())
	                continue;
	        } catch (Exception exception) {
	            // Gérez l'exception ici
	        }

	        boolean isSelected = navItem.equals(this.page);

	        NavbarNode navbarNode = new NavbarNode(navItem, this.x + x, y + offsetY, navbarHeight, isSelected);
	        navbarNode.setZindex(100);
	        navbarNode.setCallback(n -> navItem.open());
	        navbarNodes.add(navbarNode);

	        if (isSelected) {
	            // Supprimez la partie qui ajoute le bouton de sélection
	        }

	        offsetY += navbarNode.getHeight() + width(1.0F); // Espacement vertical
	    }

	    // Ajoutez les boutons de la barre de navigation à la scène
	    for (NavbarNode node : navbarNodes) {
	        addNode((ANode) node);
	    }

	    // Ajoutez le FlatCloseNode à la même position relative que la barre de navigation
	    addNode((ANode) new FlatCloseNode(this.x + width(86.5F), this.y + height(10.5F), width(2.0F)));
	}



  
  public void preDraw(int mouseX, int mouseY, float ticks) {
	    double leftMargin = this.mc.displayWidth * 0.1;  // 10% de la largeur de la fenêtre
	    double topMargin = this.mc.displayHeight * 0.05; // 5% de la hauteur de la fenêtre
	    double rightMargin = this.mc.displayWidth * 0.8; // 90% de la largeur de la fenêtre
	    double bottomMargin = this.mc.displayHeight * 0.9; // 90% de la hauteur de la fenêtre

	    GuiUtils.drawRoundedRect(leftMargin, topMargin, BLACK, rightMargin, bottomMargin, 10.0F);
	}

  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    int borderWidth = getLineWidth();
    
    
    //BORDER
    GuiUtils.drawRect(this.x + width(10.0F), this.y + width(9.0F), this.x + width(90.0F), this.y + width(9.0F) + borderWidth, RED);
    
    //GuiUtils.drawRect(this.x + width(68.125F), this.y, this.x + width(68.125F) + borderWidth, this.y + getNavbarHeight(), WHITE);
    
    GuiUtils.drawRect(this.x + width(85.0F), this.y + height(8.69), this.x + width(85.0F) + borderWidth, this.y + getNavbarHeight() + width(45.5F), RED);
    
    
    GuiUtils.drawStringWithCustomFont(mc, "Shop - " + getPage(), this.x + width(40.0F), this.y + width(6.0F), WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 150);
  }
  
  protected List<ItemSellNode> displayCategoryButtons(ShopCategory category, double startX, double startY, double buttonSize, double buttonSpacing, Minecraft mc, int mouseX, int mouseY) {
	    List<ShopItem> items = shopManager.getItemsByCategory(category);

	    // Vous pouvez ajuster les positions et la taille des boutons selon vos besoins
	    double buttonX = startX;
	    double buttonY = startY;

	    for (ShopItem shopItem : items) {
	        ItemStack itemStack = shopItem.getItemStack(); // Utilisez directement l'ItemStack de shopItem
	        ItemSellNode categoryButton = new ItemSellNode(buttonX, buttonY, buttonSize, itemStack);
	        buttonX += buttonSpacing; // Ajustez selon vos besoins
	        buttonY += buttonSpacing; // Ajustez selon vos besoins
	        categoryButton.draw(mc, mouseX, mouseY); // Affichez le bouton dans votre GUI
	    }
		return buttonList;
	}


  
  
  private Map<ShopCategory, List<ItemSellNode>> displayAllCategoryButtons(double startX, double startY, double buttonSize, double buttonSpacing, Minecraft mc, int mouseX, int mouseY) {
	    Map<ShopCategory, List<ItemSellNode>> categoryButtonsMap = new HashMap<>();

	    for (ShopCategory category : ShopCategory.values()) {
	        List<ItemSellNode> buttons = displayCategoryButtons(category, startX, startY, buttonSize, buttonSpacing, mc, mouseX, mouseY);
	        categoryButtonsMap.put(category, buttons);
	    }

	    return categoryButtonsMap;
	}

  
  public int getLineWidth() {
    return (int)Math.max(width(0.06F), 1.5D);
  }
  
  public double getNavbarHeight() {
    return height(10.55D);
  }
  
  public ShopPage getPage() {
    return this.page;
  }
  
  public double width(double value) {
    double baseRatio = 1.7777777777777777D;
    double ratio = this.mc.displayWidth / this.mc.displayHeight;
    if (ratio < 1.7777777777777777D || Math.abs(ratio - 1.7777777777777777D) < 0.005D)
      return this.mc.displayWidth / 100.0D * value; 
    return this.mc.displayHeight * 1.7777777777777777D / 100.0D * value;
  }
  
  public float width(float value) {
    float baseRatio = 1.7777778F;
    float ratio = this.mc.displayWidth / this.mc.displayHeight;
    if (ratio < 1.7777778F || Math.abs(ratio - 1.7777778F) < 0.005F)
      return this.mc.displayWidth / 100.0F * value; 
    return this.mc.displayHeight * 1.7777778F / 100.0F * value;
  }
  
  public double height(double value) {
    double baseRatio = 1.7777777777777777D;
    return width(value) / 1.7777777777777777D;
  }
  
  public float height(float value) {
    float baseRatio = 1.7777778F;
    return width(value) / 1.7777778F;
  }
  
  public boolean isValid() {
    return true;
  }
}
