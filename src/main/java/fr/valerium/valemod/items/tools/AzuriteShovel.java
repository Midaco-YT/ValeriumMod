package fr.valerium.valemod.items.tools;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class AzuriteShovel extends ItemSpade {
  public AzuriteShovel() {
    super(ToolMaterialValerium.toolTypeAzurite);
    setUnlocalizedName("azurite_shovel");
    setTextureName("valerium:azurite_shovel");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.azurite_ingot)
      return true; 
    return false;
  }
}