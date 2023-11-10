package fr.valerium.valemod.blocks.ores;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ValeriumOre extends Block {

	public ValeriumOre(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("valerium_ore");
		this.setBlockTextureName("valerium:ores/valerium_ore");
		this.setHardness(10);
	}
}
