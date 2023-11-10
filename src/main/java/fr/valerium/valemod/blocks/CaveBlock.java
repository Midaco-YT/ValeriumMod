package fr.valerium.valemod.blocks;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CaveBlock extends Block {

	public CaveBlock(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("cave_block");
		this.setBlockTextureName("valerium:caveblock/cave_block");
		this.setHardness(3);
	}
}
