package fr.valerium.valemod.blocks.ores;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SulfurOre extends Block {

	public SulfurOre(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("sulfur_ore");
		this.setBlockTextureName("valerium:ores/sulfur_ore");
		this.setHardness(10);
	}
}
