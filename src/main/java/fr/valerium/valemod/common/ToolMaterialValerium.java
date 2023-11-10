package fr.valerium.valemod.common;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ToolMaterialValerium {
  public static Item.ToolMaterial toolTypeValerium;
  
  public static Item.ToolMaterial toolTypeAzurite;
  
  public static Item.ToolMaterial toolTypeTitane;
  
  public static Item.ToolMaterial toolTypeAmethyst;

  public static Item.ToolMaterial toolTypeLegendary;
  
  public static Item.ToolMaterial toolTypeMeteor;
  
  
  
  
  public static void init() {
    toolTypeValerium = EnumHelper.addToolMaterial("valeriumTool", 3, 4999, 30.0F, 6.0F, 35);
    toolTypeAzurite = EnumHelper.addToolMaterial("azuriteTool", 3, 4999, 25.0F, 6.0F, 30);
    toolTypeTitane = EnumHelper.addToolMaterial("titaneTool", 3, 2999, 23.0F, 5.0F, 25);
    toolTypeAmethyst = EnumHelper.addToolMaterial("amethystTool", 3, 1999, 20.0F, 4.0F, 20);
    toolTypeLegendary = EnumHelper.addToolMaterial("legendaryTool", 7, 0, 70.0F, 60.0F, 50);
    toolTypeMeteor = EnumHelper.addToolMaterial("meteorTool", 6, 7999, 30.0F, 8.0F, 40);
  }
}