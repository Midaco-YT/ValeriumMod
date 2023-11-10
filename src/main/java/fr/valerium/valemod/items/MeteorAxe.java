package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class MeteorAxe extends ItemAxe {
	public MeteorAxe() {
		super(ToolMaterialValerium.toolTypeMeteor);
		setUnlocalizedName("meteor_axe");
		setTextureName("valerium:meteor_axe");
		setCreativeTab(ModVale.tabValerium);
	}

	public boolean func_82789_a(ItemStack input, ItemStack repair) {
		if (repair.getItem() == ModItems.meteor)
			return true;
		return false;
	}
}