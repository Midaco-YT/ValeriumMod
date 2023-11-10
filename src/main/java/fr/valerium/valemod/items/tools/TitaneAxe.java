package fr.valerium.valemod.items.tools;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class TitaneAxe extends ItemAxe {
  public TitaneAxe() {
    super(ToolMaterialValerium.toolTypeTitane);
    setUnlocalizedName("titane_axe");
    setTextureName("valerium:titane_axe");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.titane_ingot)
      return true; 
    return false;
  }
}