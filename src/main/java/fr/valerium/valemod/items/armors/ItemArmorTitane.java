package fr.valerium.valemod.items.armors;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ArmorMaterials;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorTitane extends ItemArmor {
  public ItemArmorTitane(int type, String name, String textureName) {
    super(ArmorMaterials.armorTitane, 0, type);
    setUnlocalizedName(name);
    setTextureName("valerium:" + textureName);
    setCreativeTab(ModVale.tabValerium);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    if (slot == 2)
      return "valerium:textures/models/titane_armor_2.png"; 
    return "valerium:textures/models/titane_armor_1.png";
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.getItem() == ModItems.titane_ingot)
      return true; 
    return false;
  }
}
