package fr.valerium.valemod.blocks;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SulfurBlock extends Block {

	public SulfurBlock(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("sulfur_block");
		this.setBlockTextureName("valerium:sulfur_block");
		this.setHardness(10);
	}
}
