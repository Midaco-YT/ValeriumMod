package fr.valerium.valemod.common;

import cpw.mods.fml.common.network.IGuiHandler;
import fr.valerium.valemod.client.gui.GuiBag;
import fr.valerium.valemod.client.gui.GuiBarrel;
import fr.valerium.valemod.client.gui.GuiCrystalBarrel;
import fr.valerium.valemod.client.gui.GuiDiamondBarrel;
import fr.valerium.valemod.client.gui.GuiDisenchantingTable;
import fr.valerium.valemod.client.gui.GuiGoldenBarrel;
import fr.valerium.valemod.client.gui.GuiIronBarrel;
import fr.valerium.valemod.client.gui.GuiMineralExtractor;
import fr.valerium.valemod.client.gui.GuiObsidianBarrel;
import fr.valerium.valemod.client.gui.GuiSeeder;
import fr.valerium.valemod.client.gui.GuiSellHdv;
import fr.valerium.valemod.client.gui.GuiStructureFinder;
import fr.valerium.valemod.client.gui.containers.ContainerBarrel;
import fr.valerium.valemod.client.gui.containers.ContainerCrystalBarrel;
import fr.valerium.valemod.client.gui.containers.ContainerDiamondBarrel;
import fr.valerium.valemod.client.gui.containers.ContainerDisenchantingTable;
import fr.valerium.valemod.client.gui.containers.ContainerGoldBarrel;
import fr.valerium.valemod.client.gui.containers.ContainerIronBarrel;
import fr.valerium.valemod.client.gui.containers.ContainerObsidianBarrel;
import fr.valerium.valemod.client.gui.containers.ContainerSellHdv;
import fr.valerium.valemod.client.gui.containers.GuiStoneCrafter;
import fr.valerium.valemod.common.gui.ContainerBag;
import fr.valerium.valemod.common.gui.ContainerMineralExtractor;
import fr.valerium.valemod.common.gui.ContainerSeeder;
import fr.valerium.valemod.common.gui.ContainerStoneCrafter;
import fr.valerium.valemod.common.inventory.InventoryBag;
import fr.valerium.valemod.items.ItemBag;
import fr.valerium.valemod.tiles.TileEntityBarrel;
import fr.valerium.valemod.tiles.TileEntityCrystalBarrel;
import fr.valerium.valemod.tiles.TileEntityDiamondBarrel;
import fr.valerium.valemod.tiles.TileEntityDisenchantingTable;
import fr.valerium.valemod.tiles.TileEntityGoldBarrel;
import fr.valerium.valemod.tiles.TileEntityIronBarrel;
import fr.valerium.valemod.tiles.TileEntityMineralExtractor;
import fr.valerium.valemod.tiles.TileEntityObsidianBarrel;
import fr.valerium.valemod.tiles.TileEntitySeeder;
import fr.valerium.valemod.tiles.TileEntityStoneCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	public static final int STONE_CRAFTER = 0;
	public static final int GUI_HDV = 1;
	public static final int BARREL = 2;
	public static final int IRON_BARREL = 3;
	public static final int GOLD_BARREL = 4;
	public static final int DIAMOND_BARREL = 5;
	public static final int OBSIDIAN_BARREL = 6;
	public static final int CRYSTAL_BARREL = 7;
	public static final int GUI_STRUCTURE_FINDER = 8;
	public static final int GUI_DISENCHANTING_TABLE = 9;
	public static final int GUI_MINERAL_EXTRACTOR = 10;
	public static final int BAG = 11;
	public static final int SEEDER = 12;

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			return new ContainerStoneCrafter((TileEntityStoneCrafter) world.getTileEntity(x, y, z), player.inventory);
		case 1:
			return new ContainerSellHdv(player);
		case 2:
			return new ContainerBarrel((TileEntityBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 3:
			return new ContainerIronBarrel((TileEntityIronBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 4:
			return new ContainerGoldBarrel((TileEntityGoldBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 5:
			return new ContainerDiamondBarrel((TileEntityDiamondBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 6:
			return new ContainerObsidianBarrel((TileEntityObsidianBarrel) world.getTileEntity(x, y, z),
					player.inventory);
		case 7:
			return new ContainerCrystalBarrel((TileEntityCrystalBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 8:
			return null;
		case 9:
			return new ContainerDisenchantingTable(player.inventory,
					(TileEntityDisenchantingTable) world.getTileEntity(x, y, z));
		case 10:
			return new ContainerMineralExtractor((TileEntityMineralExtractor) world.getTileEntity(x, y, z),
					player.inventory);
		case 11:
			if (player.getHeldItem() == null || !(player.getHeldItem().getItem() instanceof ItemBag))
				return null;
			return new ContainerBag(player.inventory, new InventoryBag(player.getHeldItem()));
		case 12:
			TileEntitySeeder tileEntity = (TileEntitySeeder) world.getTileEntity(x, y, z);
            return new ContainerSeeder(player.inventory, tileEntity);
		}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			return new GuiStoneCrafter((TileEntityStoneCrafter) world.getTileEntity(x, y, z), player.inventory);
		case 1:
			return new GuiSellHdv(player);
		case 2:
			return new GuiBarrel((TileEntityBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 3:
			return new GuiIronBarrel((TileEntityIronBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 4:
			return new GuiGoldenBarrel((TileEntityGoldBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 5:
			return new GuiDiamondBarrel((TileEntityDiamondBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 6:
			return new GuiObsidianBarrel((TileEntityObsidianBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 7:
			return new GuiCrystalBarrel((TileEntityCrystalBarrel) world.getTileEntity(x, y, z), player.inventory);
		case 8:
			return new GuiStructureFinder();
		case 9:
			return new GuiDisenchantingTable(player.inventory,
					(TileEntityDisenchantingTable) world.getTileEntity(x, y, z));
		case 10:
			return new GuiMineralExtractor((TileEntityMineralExtractor) world.getTileEntity(x, y, z), player.inventory);
		case 11:
			if (player.getHeldItem() == null || !(player.getHeldItem().getItem() instanceof ItemBag))
				return null;
			return new GuiBag(player.inventory, new InventoryBag(player.getHeldItem()));
		case 12:
			TileEntitySeeder tileEntity = (TileEntitySeeder) world.getTileEntity(x, y, z);
            return new GuiSeeder(player, tileEntity);
		}
		return null;
	}
}