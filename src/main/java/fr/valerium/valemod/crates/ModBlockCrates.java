package fr.valerium.valemod.crates;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valerium.valemod.crates.blocks.BlockOasisCrate;
import net.minecraft.block.material.Material;

public class ModBlockCrates {

	public static BlockOasisCrate oasis_crate;
	
	public static void init() {
		
		oasis_crate = new BlockOasisCrate(Material.rock);
	}
	
	public static void register() {
		
		GameRegistry.registerBlock(oasis_crate, oasis_crate.getUnlocalizedName().substring(5));
	}
}
