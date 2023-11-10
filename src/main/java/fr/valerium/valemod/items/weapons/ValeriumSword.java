package fr.valerium.valemod.items.weapons;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ValeriumSword extends ItemSword {
	
	public ValeriumSword() {
		super(ToolMaterialValerium.toolTypeValerium);
		setUnlocalizedName("valerium_sword");
		setTextureName("valerium:valerium_sword");
		setCreativeTab(ModVale.tabValerium);
	}

	public boolean func_82789_a(ItemStack input, ItemStack repair) {
		if (repair.getItem() == ModItems.valerium_ingot)
			return true;
		return false;
	}

	public boolean isItemTool() {
		return true;
	}
}
