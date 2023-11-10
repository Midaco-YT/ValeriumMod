package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.common.ToolMaterialValerium;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class MeteorPickaxe extends ItemPickaxe {
	  public MeteorPickaxe() {
	    super(ToolMaterialValerium.toolTypeMeteor);
	    setUnlocalizedName("meteor_pickaxe");
	    setTextureName("valerium:meteor_pickaxe");
	    setCreativeTab(ModVale.tabValerium);
	  }
	  
	  public boolean func_82789_a(ItemStack input, ItemStack repair) {
	    if (repair.getItem() == ModItems.meteor)
	      return true; 
	    return false;
	  }
	}