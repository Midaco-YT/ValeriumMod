package fr.valerium.valemod.blocks;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AmethystBlock extends Block {

	public AmethystBlock(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("amethyst_block");
		this.setBlockTextureName("valerium:amethyst_block");
		this.setHardness(10);
	}
}
