package fr.valerium.valemod.blocks;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TitaneBlock extends Block {

	public TitaneBlock(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("titane_block");
		this.setBlockTextureName("valerium:titane_block");
		this.setHardness(10);
	}
}
