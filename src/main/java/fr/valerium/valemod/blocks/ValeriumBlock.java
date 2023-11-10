package fr.valerium.valemod.blocks;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ValeriumBlock extends Block {

	public ValeriumBlock(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("valerium_block");
		this.setBlockTextureName("valerium:valerium_block");
		this.setHardness(10);
	}
}
