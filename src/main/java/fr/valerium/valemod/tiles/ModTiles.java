package fr.valerium.valemod.tiles;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valerium.valemod.crates.tileentity.TileEntityOasisCrate;

public class ModTiles {
  public static void init() {
    GameRegistry.registerTileEntityWithAlternatives(TileEntityAzuriteFurnace.class, "valerium:valerium_furnace", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityStoneCrafter.class, "valerium:stone_crafter", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityBarrel.class, "valerium:barrel", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityIronBarrel.class, "valerium:iron_barrel", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityGoldBarrel.class, "valerium:gold_barrel", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityDiamondBarrel.class, "valerium:diamond_barrel", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityObsidianBarrel.class, "valerium:obsidian_barrel", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityCrystalBarrel.class, "valerium:crystal_barrel", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityDisenchantingTable.class, "valerium:disenchanting_table", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityOasisCrate.class, "valerium:oasis_crate", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityMineralExtractor.class, "valerium:mineral_extractor", new String[0]);
  }
}
