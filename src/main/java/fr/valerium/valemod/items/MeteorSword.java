package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class MeteorSword extends ItemSword {
	
	public MeteorSword() {
		super(ToolMaterialValerium.toolTypeMeteor);
		setUnlocalizedName("meteor_sword");
		setTextureName("valerium:meteor_sword");
		setCreativeTab(ModVale.tabValerium);
	}

	public boolean func_82789_a(ItemStack input, ItemStack repair) {
		if (repair.getItem() == ModItems.meteor)
			return true;
		return false;
	}

	public boolean isItemTool() {
		return true;
	}
}
