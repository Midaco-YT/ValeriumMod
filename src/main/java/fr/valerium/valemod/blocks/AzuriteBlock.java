package fr.valerium.valemod.blocks;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AzuriteBlock extends Block {

	public AzuriteBlock(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("azurite_block");
		this.setBlockTextureName("valerium:azurite_block");
		this.setHardness(10);
	}
}
