package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class MeteorShovel extends ItemSpade {
  public MeteorShovel() {
    super(ToolMaterialValerium.toolTypeMeteor);
    setUnlocalizedName("meteor_shovel");
    setTextureName("valerium:meteor_shovel");
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.meteor)
      return true; 
    return false;
  }
}