package fr.valerium.valemod.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.blocks.ModBlocks;
import fr.valerium.valemod.client.gui.GuiInventaire;
import fr.valerium.valemod.client.gui.OverlayStructureFinder;
import fr.valerium.valemod.client.gui.mainmenu.UIInGame;
import fr.valerium.valemod.client.gui.mainmenu.UIMainMenu;
import fr.valerium.valemod.crates.ModBlockCrates;
import fr.valerium.valemod.crates.ModItemCrates;
import fr.valerium.valemod.crates.itemRender.ItemRenderOasisCrate;
import fr.valerium.valemod.crates.itemRender.ItemRenderOasisKey;
import fr.valerium.valemod.crates.renderers.TileEntityOasisCrateSpecialRender;
import fr.valerium.valemod.crates.tileentity.TileEntityOasisCrate;
import fr.valerium.valemod.jobs.gui.OverlayXP;
import fr.valerium.valemod.renderer.TileEntityDisenchantingTableSpecialRender;
import fr.valerium.valemod.renderer.TileEntityGrowingStandSpecialRender;
import fr.valerium.valemod.renderer.items.ItemRenderDisenchantinTable;
import fr.valerium.valemod.renderer.items.ItemRenderGrowingStand;
import fr.valerium.valemod.tiles.TileEntityDisenchantingTable;
import fr.valerium.valemod.tiles.TileEntityGrowingStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	public static int renderInventoryTESRId;
	TileEntityDisenchantingTableSpecialRender render;
	TileEntityGrowingStandSpecialRender render1;
	TileEntityOasisCrateSpecialRender render2;
	
	@Override
	public void registerRenders() {
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
		 renderInventoryTESRId = RenderingRegistry.getNextAvailableRenderId();
		 MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.disenchanting_table), new ItemRenderDisenchantinTable(render, new TileEntityDisenchantingTable()));
		 MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.growing_stand), new ItemRenderGrowingStand(render1, new TileEntityGrowingStand()));
		 MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlockCrates.oasis_crate), new ItemRenderOasisCrate(render2, new TileEntityOasisCrate()));
		 MinecraftForgeClient.registerItemRenderer(ModItemCrates.oasis_key, (IItemRenderer)new ItemRenderOasisKey());

	}
	
	@Override
	public void registerOverlays() {
		MinecraftForge.EVENT_BUS.register(new OverlayStructureFinder());
		MinecraftForge.EVENT_BUS.register(new OverlayXP());
	}
	
	@Override
    public void registerTileEntityRender()
    {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrowingStand.class, new TileEntityGrowingStandSpecialRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisenchantingTable.class, new TileEntityDisenchantingTableSpecialRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOasisCrate.class, new TileEntityOasisCrateSpecialRender());
    }

	@SubscribeEvent
	public void onOpenGui(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			event.gui = new UIMainMenu();
		} else if (event.gui instanceof GuiIngameMenu) {
			event.gui = new UIInGame();
		}
	}

	/*
	 * @SubscribeEvent public void GuiOpenEvent(GuiOpenEvent event, InventoryPlayer
	 * playerInv) { if (event.gui instanceof GuiInventory) { event.gui = new
	 * GuiHDV(); } }
	 */

	/*
	 * @SubscribeEvent public void onInitGuiEvent(InitGuiEvent.Post event) { if
	 * (event.gui instanceof GuiInventory) { int i = event.gui.height / 4 + 48;
	 * event.buttonList.add(new GuiButtonJob(30, event.gui.width / 2 + 150, i + 24 *
	 * 2, 100, 20, "Teamspeak") {
	 * 
	 * @Override public void mouseReleased(int x, int y) { if
	 * (Desktop.isDesktopSupported()) { try { Desktop.getDesktop().browse(new
	 * URI("ts3server://ts.eraknight.com")); } catch (Exception e) { } } } }); }
	 */

	@SubscribeEvent
	@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
	public void onTickInv(TickEvent.ClientTickEvent event) {
		Minecraft mc = FMLClientHandler.instance().getClient();
		GuiScreen currentScreen = mc.currentScreen;
		if ((mc.currentScreen != null) && (mc.currentScreen.getClass().equals(GuiInventory.class))) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiInventaire(Minecraft.getMinecraft().thePlayer.inventoryContainer));
		}
	}
}
