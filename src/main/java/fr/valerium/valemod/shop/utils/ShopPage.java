package fr.valerium.valemod.shop.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.translate.common.texttotranslate.TTT;
import fr.valerium.valemod.shop.ShopCategory;
import fr.valerium.valemod.shop.ui.UILootMobsPage;
import fr.valerium.valemod.shop.ui.UIShopBlocksPage;
import fr.valerium.valemod.shop.ui.UIShopHomePage;
import fr.valerium.valemod.shop.ui.UIShopMineralPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public enum ShopPage {
  HOME(ShopPagePosition.LEFT, "Home", new ResourceLocation("shop", "textures/gui/navbar/home.png"), UIShopHomePage.class),
  BLOCKS(ShopPagePosition.RIGHT, "Blocks", new ResourceLocation("shop", "textures/gui/navbar/blocks.png"), UIShopBlocksPage.class),
  MINERALS(ShopPagePosition.RIGHT, "Minerals", new ResourceLocation("shop", "textures/gui/navbar/minerals.png"), UIShopMineralPage.class),
  LOOTMOBS(ShopPagePosition.RIGHT, "Mobs Loot", new ResourceLocation("shop", "textures/gui/navbar/loot_mob.png"), UILootMobsPage.class);

  private static final ShopPage[] values;

  private ShopPagePosition position;
  private String name;
  private ResourceLocation icon;
  private Class<? extends UIShopPage> clazz;

  static {
    values = values();
  }

  public static ShopPage[] getValues() {
    return values;
  }

  ShopPage(ShopPagePosition position, String name, Class<? extends UIShopPage> clazz) {
    this.position = position;
    this.name = name;
    this.clazz = clazz;
  }

  ShopPage(ShopPagePosition position, String name, ResourceLocation icon, Class<? extends UIShopPage> clazz) {
    this.position = position;
    this.name = name;
    this.icon = icon;
    this.clazz = clazz;
  }

  public ShopPagePosition getPosition() {
    return this.position;
  }

  public String getName() {
    return this.name;
  }

  public ResourceLocation getIcon() {
    return this.icon;
  }

  public UIShopPage getPage() throws Exception {
    return this.clazz.newInstance();
  }

  @SideOnly(Side.CLIENT)
  public void open() {
    try {
      Minecraft.getMinecraft().displayGuiScreen((GuiScreen)getPage());
    } catch (Exception e) {
      e.printStackTrace();
      // Apollon.instance().getNotificationManager().addNotification((ANotification)new MinecraftNotification("Paladium", 
      TTT.format("notification.shop.ui.error.page", new Object[0], "valerium");
    }
  }
}
