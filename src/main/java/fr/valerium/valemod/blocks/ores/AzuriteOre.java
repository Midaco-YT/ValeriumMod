package fr.valerium.valemod.blocks.ores;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AzuriteOre extends Block {

	public AzuriteOre(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("azurite_ore");
		this.setBlockTextureName("valerium:ores/azurite_ore");
		this.setHardness(10);
	}
}
