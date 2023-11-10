package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemTintedGlasses extends ItemArmor {
	
	public ItemTintedGlasses(int type, String name, String textureName) {
	    super(ArmorMaterials.armorGlasses, 0, type);
	    setUnlocalizedName(name);
	    setTextureName("valerium:" + textureName);
	    setCreativeTab(ModVale.tabValerium);
	  }
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
	    if (slot == 1)
	    return "valerium:textures/models/glasses.png";
		return type;
	  }
}
