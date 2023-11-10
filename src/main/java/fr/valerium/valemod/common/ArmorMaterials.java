package fr.valerium.valemod.common;

import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ArmorMaterials {
  public static ItemArmor.ArmorMaterial armorValerium;
  
  public static ItemArmor.ArmorMaterial armorTitane;
  
  public static ItemArmor.ArmorMaterial armorAmethyst;
  
  public static ItemArmor.ArmorMaterial armorAzurite;
  
  public static ItemArmor.ArmorMaterial armorGlasses;
  
  public static ItemArmor.ArmorMaterial armorMeteor;
  
  public static void init() {
    armorValerium = EnumHelper.addArmorMaterial("valeriumArmor", 320, new int[] { 4, 7, 5, 6 }, 21);
    armorTitane = EnumHelper.addArmorMaterial("titaneArmor", 190, new int[] { 3, 7, 6, 3 }, 19);
    armorAmethyst = EnumHelper.addArmorMaterial("amethystArmor", 160, new int[] { 3, 7, 6, 3 }, 21);
    armorAzurite = EnumHelper.addArmorMaterial("azuriteArmor", 370, new int[] { 4, 7, 5, 6 }, 30);
    armorMeteor = EnumHelper.addArmorMaterial("meteorArmor", 450, new int[] { 5, 8, 6, 7 }, 30);
    armorGlasses = EnumHelper.addArmorMaterial("glassesArmor", 160, new int[] { 1, 3, 3, 1 }, 21);
  }
}
