package fr.valerium.valemod.items.weapons;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class AzuriteSword extends ItemSword {
  public AzuriteSword() {
    super(ToolMaterialValerium.toolTypeAzurite);
    setUnlocalizedName("azurite_sword");
    setTextureName("valerium:azurite_sword");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.azurite_ingot)
      return true; 
    return false;
  }
  
  public boolean isItemTool() {
    return true;
  }
}
