package fr.valerium.valemod.items.tools;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class AzuritePickaxe extends ItemPickaxe {
  public AzuritePickaxe() {
    super(ToolMaterialValerium.toolTypeAzurite);
    setUnlocalizedName("azurite_pickaxe");
    setTextureName("valerium:azurite_pickaxe");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.azurite_ingot)
      return true; 
    return false;
  }
}