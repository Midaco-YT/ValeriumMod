package fr.valerium.valemod.items.tools;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ValeriumAxe extends ItemAxe {
  public ValeriumAxe() {
    super(ToolMaterialValerium.toolTypeValerium);
    setUnlocalizedName("valerium_axe");
    setTextureName("valerium:valerium_axe");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.valerium_ingot)
      return true; 
    return false;
  }
}