package fr.valerium.valemod.items.tools;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class TitanePickaxe extends ItemPickaxe {
  public TitanePickaxe() {
    super(ToolMaterialValerium.toolTypeTitane);
    setUnlocalizedName("titane_pickaxe");
    setTextureName("valerium:titane_pickaxe");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.titane_ingot)
      return true; 
    return false;
  }
}