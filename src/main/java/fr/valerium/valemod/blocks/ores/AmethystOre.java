package fr.valerium.valemod.blocks.ores;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AmethystOre extends Block {

	public AmethystOre(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("amethyst_ore");
		this.setBlockTextureName("valerium:ores/amethyst_ore");
		this.setHardness(10);
	}
}
