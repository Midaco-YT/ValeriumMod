package fr.valerium.valemod.items.tools;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class AzuriteAxe extends ItemAxe {
  public AzuriteAxe() {
    super(ToolMaterialValerium.toolTypeAzurite);
    setUnlocalizedName("azurite_axe");
    setTextureName("valerium:azurite_axe");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.azurite_ingot)
      return true; 
    return false;
  }
}