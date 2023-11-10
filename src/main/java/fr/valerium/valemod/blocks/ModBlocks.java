package fr.valerium.valemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valerium.valemod.blocks.barrels.BarrelBlock;
import fr.valerium.valemod.blocks.barrels.CrystalBarrelBlock;
import fr.valerium.valemod.blocks.barrels.DiamondBarrelBlock;
import fr.valerium.valemod.blocks.barrels.GoldenBarrelBlock;
import fr.valerium.valemod.blocks.barrels.IronBarrelBlock;
import fr.valerium.valemod.blocks.barrels.ObsidianBarrelBlock;
import fr.valerium.valemod.blocks.machines.MineralExtractorBlock;
import fr.valerium.valemod.blocks.machines.RuneCrafter;
import fr.valerium.valemod.blocks.machines.SeederBlock;
import fr.valerium.valemod.blocks.machines.StoneCrafter;
import fr.valerium.valemod.blocks.ores.AmethystOre;
import fr.valerium.valemod.blocks.ores.AzuriteOre;
import fr.valerium.valemod.blocks.ores.SulfurOre;
import fr.valerium.valemod.blocks.ores.TitaneOre;
import fr.valerium.valemod.blocks.ores.ValeriumOre;
import net.minecraft.block.material.Material;

public class ModBlocks {

	
	public static ValeriumBlock valerium_block;
	public static ValeriumOre valerium_ore;
	public static AzuriteBlock azurite_block;
	public static AzuriteOre azurite_ore;
	public static TitaneBlock titane_block;
	public static TitaneOre titane_ore;
	public static AmethystBlock amethyst_block;
	public static AmethystOre amethyst_ore;
	public static SulfurBlock sulfur_block;
	public static SulfurOre sulfur_ore;
	public static AzuriteFurnace azurite_furnace;
	public static RuneCrafter rune_crafter;
	public static StoneCrafter stone_crafter;
	public static CaveBlock cave_block;
	public static BarrelBlock barrel;
	public static IronBarrelBlock iron_barrel;
	public static GoldenBarrelBlock golden_barrel;
	public static DiamondBarrelBlock diamond_barrel;
	public static ObsidianBarrelBlock obsidian_barrel;
	public static CrystalBarrelBlock crystal_barrel;
	public static GrowthAcceleratorBlock growth_accelerator;
	public static FertilizedDirtBlock fertilized_dirt;
	public static GrowingStandBlock growing_stand;
	public static DisenchantingTableBlock disenchanting_table;
	public static MineralExtractorBlock mineral_extractor;
	//public static ValeriumLuckyBlock valerium_luckyblock;//
	//public static AzuriteLuckyBlock azurite_luckyblock;//
	
	public static SeederBlock seeder;
	
	public static void init() {
		
		// Ores //
		valerium_ore = new ValeriumOre(Material.rock);
		azurite_ore = new AzuriteOre(Material.rock);
		titane_ore = new TitaneOre(Material.rock);
		amethyst_ore = new AmethystOre(Material.rock);
		sulfur_ore = new SulfurOre(Material.rock);
		
		// Blocks //
		valerium_block = new ValeriumBlock(Material.rock);
		azurite_block = new AzuriteBlock(Material.rock);
		titane_block = new TitaneBlock(Material.rock);
		amethyst_block = new AmethystBlock(Material.rock);
		sulfur_block = new SulfurBlock(Material.rock);
		cave_block = new CaveBlock(Material.rock);
		growth_accelerator = new GrowthAcceleratorBlock();
		fertilized_dirt = new FertilizedDirtBlock();
		disenchanting_table = new DisenchantingTableBlock(Material.rock);
		
		// Machine //
		azurite_furnace = new AzuriteFurnace();
		rune_crafter = new RuneCrafter(Material.rock);
		stone_crafter = new StoneCrafter(Material.rock);
		growing_stand = new GrowingStandBlock(Material.rock);
		mineral_extractor = new MineralExtractorBlock();
		seeder = new SeederBlock();
		
		// BARREL //
		barrel = new BarrelBlock(Material.wood);
		iron_barrel = new IronBarrelBlock(Material.iron);
		golden_barrel = new GoldenBarrelBlock(Material.rock);
		diamond_barrel = new DiamondBarrelBlock(Material.rock);
		obsidian_barrel = new ObsidianBarrelBlock(Material.rock);
		crystal_barrel = new CrystalBarrelBlock(Material.glass);
		
		// LUCKY BLOCKS //
		//valerium_luckyblock = new ValeriumLuckyBlock();//
		//azurite_luckyblock = new AzuriteLuckyBlock();//
	}
	
	public static void register() {
		GameRegistry.registerBlock(valerium_block, valerium_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(valerium_ore, valerium_ore.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(azurite_block, azurite_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(azurite_ore, azurite_ore.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(titane_block, titane_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(titane_ore, titane_ore.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(amethyst_block, amethyst_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(amethyst_ore, amethyst_ore.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(sulfur_block, sulfur_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(sulfur_ore, sulfur_ore.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(rune_crafter, rune_crafter.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(stone_crafter, stone_crafter.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(cave_block, cave_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(barrel, barrel.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(iron_barrel, iron_barrel.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(golden_barrel, golden_barrel.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(diamond_barrel, diamond_barrel.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(obsidian_barrel, obsidian_barrel.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(crystal_barrel, crystal_barrel.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(growth_accelerator, growth_accelerator.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(fertilized_dirt, fertilized_dirt.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(growing_stand, growing_stand.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(disenchanting_table, disenchanting_table.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(mineral_extractor, mineral_extractor.getUnlocalizedName().substring(5));
		//GameRegistry.registerBlock(valerium_luckyblock, valerium_luckyblock.getUnlocalizedName().substring(5));//
		//GameRegistry.registerBlock(azurite_luckyblock, azurite_luckyblock.getUnlocalizedName().substring(5));//
		GameRegistry.registerBlock(seeder, seeder.getUnlocalizedName().substring(5));
	}
}
