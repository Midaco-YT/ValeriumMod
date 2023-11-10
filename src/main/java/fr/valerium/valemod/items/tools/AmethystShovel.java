package fr.valerium.valemod.items.tools;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class AmethystShovel extends ItemSpade {
  public AmethystShovel() {
    super(ToolMaterialValerium.toolTypeAmethyst);
    setUnlocalizedName("amethyst_shovel");
    setTextureName("valerium:amethyst_shovel");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.amethyst_ingot)
      return true; 
    return false;
  }
}