package fr.valerium.valemod.items.weapons;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class AmethystSword extends ItemSword {
  public AmethystSword() {
    super(ToolMaterialValerium.toolTypeAmethyst);
    setUnlocalizedName("amethyst_sword");
    setTextureName("valerium:amethyst_sword");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.amethyst_ingot)
      return true; 
    return false;
  }
  
  public boolean isItemTool() {
    return true;
  }
}
