package fr.valerium.valemod;

import java.io.File;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.blocks.ModBlocks;
import fr.valerium.valemod.commands.CommandGetXpMiner;
import fr.valerium.valemod.commands.CommandHDV;
import fr.valerium.valemod.commands.CommandInfoJobs;
import fr.valerium.valemod.commands.CommandMiner;
import fr.valerium.valemod.commands.CommandShop;
import fr.valerium.valemod.commands.CommandTest;
import fr.valerium.valemod.commands.CommandTets;
import fr.valerium.valemod.commands.CommandVision;
import fr.valerium.valemod.commands.JobsCommand;
import fr.valerium.valemod.commands.SpellInfoCommand;
import fr.valerium.valemod.common.ArmorMaterials;
import fr.valerium.valemod.common.GuiHandler;
import fr.valerium.valemod.common.KeyHandler;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.craftings.ModRecipies;
import fr.valerium.valemod.crates.ModBlockCrates;
import fr.valerium.valemod.crates.ModItemCrates;
import fr.valerium.valemod.crates.tileentity.TileEntityOasisCrate;
import fr.valerium.valemod.events.EventsHandler;
import fr.valerium.valemod.items.ModItems;
import fr.valerium.valemod.items.potions.PotionDoubleXP;
import fr.valerium.valemod.items.renderer.ItemBagRenderer;
import fr.valerium.valemod.jobs.events.EventsListener;
import fr.valerium.valemod.network.PacketPipeline;
import fr.valerium.valemod.network.packet.OverlayPacketHandler;
import fr.valerium.valemod.network.packet.PacketHandler;
import fr.valerium.valemod.network.packet.PacketJobs;
import fr.valerium.valemod.network.packet.PacketJobsHandler;
import fr.valerium.valemod.network.packet.PacketOverlay;
import fr.valerium.valemod.proxy.CommonProxy;
import fr.valerium.valemod.shop.ShopItemOffer;
import fr.valerium.valemod.shop.ShopManager;
import fr.valerium.valemod.spells.ModSpell;
import fr.valerium.valemod.spells.SpellRegistry;
import fr.valerium.valemod.tiles.ModTiles;
import fr.valerium.valemod.tiles.TileEntityDisenchantingTable;
import fr.valerium.valemod.tiles.TileEntityGrowingStand;
import fr.valerium.valemod.world.generation.WorldGenRegister;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptableRemoteVersions = "*")

public class ModVale {

	@Instance("valerium")
	public static ModVale instance;
	public static SpellRegistry spellRegistry;
	public static ShopManager shopManager;

	public static SimpleNetworkWrapper network;
	public static SimpleNetworkWrapper test;
	EventsHandler eventsHandler = new EventsHandler();
	EventsListener eventsListener = new EventsListener();
	public static final Potion potionDoubleXP = PotionDoubleXP.getInstance();
	private PacketHandler packetHandler;
	public PacketPipeline packetPipeline = new PacketPipeline();

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static CommonProxy proxy;

	private static File configDir;

	public static CreativeTabs tabValerium = new CreativeTabs("tabValerium") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ModItems.valerium_ingot;
		}
	};

	public static CreativeTabs tabBarrel = new CreativeTabs("tabBarrel") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.barrel);
		}
	};

	public static CreativeTabs tabMiner = new CreativeTabs("tabMiner") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ModItems.azurite_pickaxe;
		}
	};

	public static CreativeTabs tabFarmer = new CreativeTabs("tabFarmer") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.fertilized_dirt);
		}
	};

	public static CreativeTabs tabHunter = new CreativeTabs("tabHunter") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.fertilized_dirt);
		}
	};

	public static CreativeTabs tabAlchimist = new CreativeTabs("tabAlchimist") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Items.potionitem;
		}
	};

	public static CreativeTabs tabMoney = new CreativeTabs("tabMoney") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ModItems.money;
		}
	};

	public static CreativeTabs tabCrates = new CreativeTabs("tabCrates") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ModItems.money;
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ShopItemOffer.registerOffers();
		ModSpell.registerSpell();
		ToolMaterialValerium.init();
		ArmorMaterials.init();
		ModTiles.init();
		ModItems.init();
		ModItems.register();
		ModBlocks.init();
		ModBlocks.register();
		ModItemCrates.init();
		ModItemCrates.register();
		ModBlockCrates.init();
		ModBlockCrates.register();
		WorldGenRegister.mainRegsitry();
		// EventsMod.init();
		ModRecipies.init();
		network = NetworkRegistry.INSTANCE.newSimpleChannel("Channel");
		MinecraftForge.EVENT_BUS.register(new KeyHandler());
		// NetworkRegistry.INSTANCE.newChannel("ForgeToBukkit", new ChannelHandler[] {
		// (ChannelHandler)(this.packetHandler = new PacketHandler()) });
		// network.registerMessage(PacketOpenHDVHandler.class, PacketOpenHDV.class, 1,
		// Side.CLIENT);
		network.registerMessage(OverlayPacketHandler.class, PacketOverlay.class, 2, Side.CLIENT);
		network.registerMessage(PacketJobsHandler.class, PacketJobs.class, 3, Side.CLIENT);
		network.registerMessage(PacketJobsHandler.class, PacketJobs.class, 4, Side.SERVER);
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOasisCrate.class, new
		// TileEntityOasisCrateSpecialRender());//
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		shopManager = new ShopManager();
		ModSpell.registerSpell();
		proxy.registerRenders();
		proxy.registerOverlays();
		proxy.registerTileEntityRender();
		NetworkRegistry.INSTANCE.registerGuiHandler(ModVale.instance, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(eventsHandler);
		MinecraftForge.EVENT_BUS.register(eventsListener);
		MinecraftForgeClient.registerItemRenderer(ModItems.bag, new ItemBagRenderer());
		FMLCommonHandler.instance().bus().register(eventsListener);
		GameRegistry.registerTileEntity(TileEntityGrowingStand.class, "Growing Stand");
		GameRegistry.registerTileEntity(TileEntityDisenchantingTable.class, "Disenchanting Table");
		GameRegistry.registerTileEntity(TileEntityOasisCrate.class, "Oasis Crate");
		this.packetPipeline.initialize();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		this.packetPipeline.postInitialise();
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand((ICommand) new JobsCommand());
		event.registerServerCommand((ICommand) new CommandTets());
		event.registerServerCommand((ICommand) new CommandVision());
		event.registerServerCommand((ICommand) new CommandGetXpMiner());
		event.registerServerCommand((ICommand) new CommandHDV());
		event.registerServerCommand((ICommand) new CommandInfoJobs());
		event.registerServerCommand((ICommand) new CommandShop());
		event.registerServerCommand((ICommand) new CommandMiner());
		event.registerServerCommand((ICommand) new CommandTest());
		event.registerServerCommand((ICommand) new SpellInfoCommand());
	}

	public PacketHandler getPacketHandler() {
		return this.packetHandler;
	}
	
	public static SpellRegistry getSpellRegistry() {
        return spellRegistry;
    }
}
