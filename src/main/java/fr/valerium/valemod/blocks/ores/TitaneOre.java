package fr.valerium.valemod.blocks.ores;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TitaneOre extends Block {

	public TitaneOre(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabValerium);
		this.setBlockName("titane_ore");
		this.setBlockTextureName("valerium:ores/titane_ore");
		this.setHardness(10);
	}
}
