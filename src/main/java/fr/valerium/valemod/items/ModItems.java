package fr.valerium.valemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valerium.valemod.common.ToolMaterialValerium;
import fr.valerium.valemod.items.MinerItems.ItemStructurFinder;
import fr.valerium.valemod.items.armors.ItemArmorAmethyst;
import fr.valerium.valemod.items.armors.ItemArmorAzurite;
import fr.valerium.valemod.items.armors.ItemArmorMeteor;
import fr.valerium.valemod.items.armors.ItemArmorTitane;
import fr.valerium.valemod.items.armors.ItemArmorValerium;
import fr.valerium.valemod.items.hammers.ItemValeriumHammer;
import fr.valerium.valemod.items.materials.AmethystIngot;
import fr.valerium.valemod.items.materials.AzuriteFragment;
import fr.valerium.valemod.items.materials.AzuriteIngot;
import fr.valerium.valemod.items.materials.SulfurCrystal;
import fr.valerium.valemod.items.materials.TitaneIngot;
import fr.valerium.valemod.items.materials.ValeriumIngot;
import fr.valerium.valemod.items.modifiers.ItemFilter;
import fr.valerium.valemod.items.modifiers.ItemFortuneModifier;
import fr.valerium.valemod.items.modifiers.ItemSpeedBoost;
import fr.valerium.valemod.items.particles.ItemAmethystParticle;
import fr.valerium.valemod.items.particles.ItemDiamondParticle;
import fr.valerium.valemod.items.particles.ItemIronParticle;
import fr.valerium.valemod.items.particles.ItemTitaneParticle;
import fr.valerium.valemod.items.particles.ItemValeriumParticle;
import fr.valerium.valemod.items.potions.ItemDoubleXPPotion;
import fr.valerium.valemod.items.rings.ItemFortuneRing;
import fr.valerium.valemod.items.rings.ItemRandomRing;
import fr.valerium.valemod.items.rings.ItemXpRing;
import fr.valerium.valemod.items.runes.ItemLegendaryRune;
import fr.valerium.valemod.items.runes.ItemMysticalRune;
import fr.valerium.valemod.items.scepter.ItemAmethystScepter;
import fr.valerium.valemod.items.scepter.ItemAzuriteScepter;
import fr.valerium.valemod.items.scepter.ItemTitaneScepter;
import fr.valerium.valemod.items.scepter.ItemValeriumScepter;
import fr.valerium.valemod.items.tools.AmethystAxe;
import fr.valerium.valemod.items.tools.AmethystPickaxe;
import fr.valerium.valemod.items.tools.AmethystShovel;
import fr.valerium.valemod.items.tools.AzuriteAxe;
import fr.valerium.valemod.items.tools.AzuritePickaxe;
import fr.valerium.valemod.items.tools.AzuriteShovel;
import fr.valerium.valemod.items.tools.ItemLegendaryPickaxe;
import fr.valerium.valemod.items.tools.TitaneAxe;
import fr.valerium.valemod.items.tools.TitanePickaxe;
import fr.valerium.valemod.items.tools.TitaneShovel;
import fr.valerium.valemod.items.tools.ValeriumAxe;
import fr.valerium.valemod.items.tools.ValeriumPickaxe;
import fr.valerium.valemod.items.tools.ValeriumShovel;
import fr.valerium.valemod.items.weapons.AmethystSword;
import fr.valerium.valemod.items.weapons.AzuriteSword;
import fr.valerium.valemod.items.weapons.TitaneSword;
import fr.valerium.valemod.items.weapons.ValeriumSword;

public class ModItems {

	public static ValeriumIngot valerium_ingot;
	public static AzuriteIngot azurite_ingot;
	public static AzuriteFragment azurite_fragment;
	public static TitaneIngot titane_ingot;
	public static AmethystIngot amethyst_ingot;
	public static SulfurCrystal sulfur_crystal;
	public static ValeriumSword valerium_sword;
	public static AzuriteSword azurite_sword;
	public static TitaneSword titane_sword;
	public static AmethystSword amethyst_sword;
	public static ValeriumPickaxe valerium_pickaxe;
	public static AzuritePickaxe azurite_pickaxe;
	public static TitanePickaxe titane_pickaxe;
	public static AmethystPickaxe amethyst_pickaxe;
	public static ValeriumAxe valerium_axe;
	public static AzuriteAxe azurite_axe;
	public static TitaneAxe titane_axe;
	public static AmethystAxe amethyst_axe;
	public static ValeriumShovel valerium_shovel;
	public static AzuriteShovel azurite_shovel;
	public static TitaneShovel titane_shovel;
	public static AmethystShovel amethyst_shovel;
	public static ItemArmorValerium valerium_helmet;
	public static ItemArmorValerium valerium_chestplate;
	public static ItemArmorValerium valerium_leggings;
	public static ItemArmorValerium valerium_boots;
	public static ItemArmorAzurite azurite_helmet;
	public static ItemArmorAzurite azurite_chestplate;
	public static ItemArmorAzurite azurite_leggings;
	public static ItemArmorAzurite azurite_boots;
	public static ItemArmorTitane titane_helmet;
	public static ItemArmorTitane titane_chestplate;
	public static ItemArmorTitane titane_leggings;
	public static ItemArmorTitane titane_boots;
	public static ItemArmorAmethyst amethyst_helmet;
	public static ItemArmorAmethyst amethyst_chestplate;
	public static ItemArmorAmethyst amethyst_leggings;
	public static ItemArmorAmethyst amethyst_boots;
	public static ItemArmorMeteor meteor_helmet;
	public static ItemArmorMeteor meteor_chestplate;
	public static ItemArmorMeteor meteor_leggings;
	public static ItemArmorMeteor meteor_boots;
	public static ItemXpRing xp_ring;
	public static ItemFortuneRing fortune_ring;
	public static ItemRandomRing random_ring;
	public static ItemLegendaryPickaxe legendary_pickaxe;
	public static ItemMysticalRune mystical_rune;
	public static ItemLegendaryRune legendary_rune;
	public static ItemXpTest item_test;
	public static ItemMoney money;
	public static ItemGlasses glasses;
	public static ItemTintedGlasses tinted_glasses;
	public static MeteorSword meteor_sword;
	public static MeteorPickaxe meteor_pickaxe;
	public static MeteorAxe meteor_axe;
	public static MeteorShovel meteor_shovel;
	public static ItemMeteor meteor;
	public static ItemStructurFinder structure_finder;
	public static ItemFortuneModifier fortuneModifier;
	public static ItemSpeedBoost speed_upgrade;
	public static ItemFilter item_filter;
	public static ItemIronParticle iron_particle;
	public static ItemDiamondParticle diamond_particle;
	public static ItemAmethystParticle amethyst_particle;
	public static ItemTitaneParticle titane_particle;
	public static ItemValeriumParticle valerium_particle;
	public static ItemDoubleXPPotion doubleXPPotion;
	public static ItemValeriumHammer valerium_hammer;
	public static ItemScroll scroll;
	public static ItemBag bag;
	public static ItemAzuriteScepter azurite_scepter;
	public static ItemValeriumScepter valerium_scepter;
	public static ItemTitaneScepter titane_scepter;
	public static ItemAmethystScepter amethyst_scepter;
	
	
	public static void init() {
		
		// Materials //
		valerium_ingot = new ValeriumIngot();
		azurite_ingot = new AzuriteIngot();
		azurite_fragment = new AzuriteFragment();
		meteor = new ItemMeteor();
		titane_ingot = new TitaneIngot();
		amethyst_ingot = new AmethystIngot();
		sulfur_crystal = new SulfurCrystal();
		
		doubleXPPotion = new ItemDoubleXPPotion();
	    
		
		// Excusives Miner Items //
		structure_finder = new ItemStructurFinder();
		fortuneModifier = new ItemFortuneModifier();
		speed_upgrade = new ItemSpeedBoost(0);
		item_filter = new ItemFilter();
		iron_particle = new ItemIronParticle();
		diamond_particle = new ItemDiamondParticle();
		amethyst_particle = new ItemAmethystParticle();
		titane_particle = new ItemTitaneParticle();
		valerium_particle = new ItemValeriumParticle();

		
		
		// Sword //
		valerium_sword = new ValeriumSword();
		azurite_sword = new AzuriteSword();
		titane_sword = new TitaneSword();
		amethyst_sword = new AmethystSword();
		meteor_sword = new MeteorSword();
		
		// Tools //
		valerium_pickaxe = new ValeriumPickaxe();
		azurite_pickaxe = new AzuritePickaxe();
		titane_pickaxe = new TitanePickaxe();
		amethyst_pickaxe = new AmethystPickaxe();
		meteor_pickaxe = new MeteorPickaxe();
		valerium_axe = new ValeriumAxe();
		azurite_axe = new AzuriteAxe();
		titane_axe = new TitaneAxe();
		amethyst_axe = new AmethystAxe();
		meteor_axe = new MeteorAxe();
		valerium_shovel = new ValeriumShovel();
		azurite_shovel = new AzuriteShovel();
		titane_shovel = new TitaneShovel();
		amethyst_shovel = new AmethystShovel();
		meteor_shovel = new MeteorShovel();
		legendary_pickaxe = new ItemLegendaryPickaxe(ToolMaterialValerium.toolTypeLegendary);
		valerium_hammer = new ItemValeriumHammer(ToolMaterialValerium.toolTypeValerium);
		
		
		
		// Armor //
		valerium_helmet = new ItemArmorValerium(0, "valerium_helmet", "valerium_helmet");
	    valerium_chestplate = new ItemArmorValerium(1, "valerium_chestplate", "valerium_chestplate");
	    valerium_leggings = new ItemArmorValerium(2, "valerium_leggings", "valerium_leggings");
	    valerium_boots = new ItemArmorValerium(3, "valerium_boots", "valerium_boots");
	    azurite_helmet = new ItemArmorAzurite(0, "azurite_helmet", "azurite_helmet");
	    azurite_chestplate = new ItemArmorAzurite(1, "azurite_chestplate", "azurite_chestplate");
	    azurite_leggings = new ItemArmorAzurite(2, "azurite_leggings", "azurite_leggings");
	    azurite_boots = new ItemArmorAzurite(3, "azurite_boots", "azurite_boots");
	    titane_helmet = new ItemArmorTitane(0, "titane_helmet", "titane_helmet");
	    titane_chestplate = new ItemArmorTitane(1, "titane_chestplate", "titane_chestplate");
	    titane_leggings = new ItemArmorTitane(2, "titane_leggings", "titane_leggings");
	    titane_boots = new ItemArmorTitane(3, "titane_boots", "titane_boots");
	    amethyst_helmet = new ItemArmorAmethyst(0, "amethyst_helmet", "amethyst_helmet");
	    amethyst_chestplate = new ItemArmorAmethyst(1, "amethyst_chestplate", "amethyst_chestplate");
	    amethyst_leggings = new ItemArmorAmethyst(2, "amethyst_leggings", "amethyst_leggings");
	    amethyst_boots = new ItemArmorAmethyst(3, "amethyst_boots", "amethyst_boots");
	    meteor_helmet = new ItemArmorMeteor(0, "meteor_helmet", "meteor_helmet");
	    meteor_chestplate = new ItemArmorMeteor(1, "meteor_chestplate", "meteor_chestplate");
	    meteor_leggings = new ItemArmorMeteor(2, "meteor_leggings", "meteor_leggings");
	    meteor_boots = new ItemArmorMeteor(3, "meteor_boots", "meteor_boots");
	    
	    // Stone //
	    xp_ring = new ItemXpRing();
	    fortune_ring = new ItemFortuneRing();
	    random_ring = new ItemRandomRing();
	    
	    // Runes //
	    mystical_rune = new ItemMysticalRune();
	    legendary_rune = new ItemLegendaryRune();
	    
	    // Other //
	    item_test = new ItemXpTest();
	    money = new ItemMoney();
	    glasses = new ItemGlasses();
	    tinted_glasses = new ItemTintedGlasses(0, "tinted_glasses", "tinted_glasses");
	    scroll = new ItemScroll(); 
	    bag = new ItemBag();
	    
	    // Legendary Item //
	    azurite_scepter = new ItemAzuriteScepter();
	    valerium_scepter = new ItemValeriumScepter();
	    titane_scepter = new ItemTitaneScepter();
	    amethyst_scepter = new ItemAmethystScepter();
	}
	
	public static void register() {
		
		GameRegistry.registerItem(valerium_ingot, "valerium_ingot");
		GameRegistry.registerItem(azurite_ingot, "azurite_ingot");
		GameRegistry.registerItem(azurite_fragment, "azurite_fragment");
		GameRegistry.registerItem(meteor, "meteor");
		GameRegistry.registerItem(titane_ingot, "titane_ingot");
		GameRegistry.registerItem(amethyst_ingot, "amethyst_ingot");
		GameRegistry.registerItem(sulfur_crystal, "sulfur_crystal");
		GameRegistry.registerItem(valerium_sword, "valerium_sword");
		GameRegistry.registerItem(azurite_sword, "azurite_sword");
		GameRegistry.registerItem(titane_sword, "titane_sword");
		GameRegistry.registerItem(amethyst_sword, "amethyst_sword");
		GameRegistry.registerItem(meteor_sword, "meteor_sword");
		GameRegistry.registerItem(valerium_pickaxe, "valerium_pickaxe");
		GameRegistry.registerItem(azurite_pickaxe, "azurite_pickaxe");
		GameRegistry.registerItem(titane_pickaxe, "titane_pickaxe");
		GameRegistry.registerItem(amethyst_pickaxe, "amethyst_pickaxe");
		GameRegistry.registerItem(legendary_pickaxe, "legendary_pickaxe");
		GameRegistry.registerItem(meteor_pickaxe, "meteor_pickaxe");
		GameRegistry.registerItem(valerium_axe, "valerium_axe");
		GameRegistry.registerItem(azurite_axe, "azurite_axe");
		GameRegistry.registerItem(titane_axe, "titane_axe");
		GameRegistry.registerItem(amethyst_axe, "amethyst_axe");
		GameRegistry.registerItem(meteor_axe, "meteor_axe");
		GameRegistry.registerItem(valerium_shovel, "valerium_shovel");
		GameRegistry.registerItem(azurite_shovel, "azurite_shovel");
		GameRegistry.registerItem(meteor_shovel, "meteor_shovel");
		GameRegistry.registerItem(titane_shovel, "titane_shovel");
		GameRegistry.registerItem(amethyst_shovel, "amethyst_shovel");
		GameRegistry.registerItem(valerium_helmet, "valerium_helmet");
		GameRegistry.registerItem(valerium_chestplate, "valerium_chestplate");
		GameRegistry.registerItem(valerium_leggings, "valerium_leggings");
		GameRegistry.registerItem(valerium_boots, "valerium_boots");
		GameRegistry.registerItem(azurite_helmet, "azurite_helmet");
		GameRegistry.registerItem(azurite_chestplate, "azurite_chestplate");
		GameRegistry.registerItem(azurite_leggings, "azurite_leggings");
		GameRegistry.registerItem(azurite_boots, "azurite_boots");
		GameRegistry.registerItem(titane_helmet, "titane_helmet");
		GameRegistry.registerItem(titane_chestplate, "titane_chestplate");
		GameRegistry.registerItem(titane_leggings, "titane_leggings");
		GameRegistry.registerItem(titane_boots, "titane_boots");
		GameRegistry.registerItem(amethyst_helmet, "amethyst_helmet");
		GameRegistry.registerItem(amethyst_chestplate, "amethyst_chestplate");
		GameRegistry.registerItem(amethyst_leggings, "amethyst_leggings");
		GameRegistry.registerItem(amethyst_boots, "amethyst_boots");
		GameRegistry.registerItem(meteor_helmet, "meteor_helmet");
		GameRegistry.registerItem(meteor_chestplate, "meteor_chestplate");
		GameRegistry.registerItem(meteor_leggings, "meteor_leggings");
		GameRegistry.registerItem(meteor_boots, "meteor_boots");
		GameRegistry.registerItem(xp_ring, "xp_ring");
		GameRegistry.registerItem(fortune_ring, "fortune_ring");
		GameRegistry.registerItem(random_ring, "random_stone");
		GameRegistry.registerItem(mystical_rune, "mystical_rune");
		GameRegistry.registerItem(legendary_rune, "legendary_rune");
		GameRegistry.registerItem(item_test, "item_test");
		GameRegistry.registerItem(money, "money");
		GameRegistry.registerItem(glasses, "glasses");
		GameRegistry.registerItem(tinted_glasses, "tinted_glasses");
		GameRegistry.registerItem(structure_finder, "structure_finder");
		GameRegistry.registerItem(fortuneModifier, "fortuneModifier");
		GameRegistry.registerItem(speed_upgrade, "speed_upgrade");
		GameRegistry.registerItem(item_filter, "item_filter");
		GameRegistry.registerItem(iron_particle, "iron_particle");
		GameRegistry.registerItem(diamond_particle, "diamond_particle");
		GameRegistry.registerItem(amethyst_particle, "amethyst_particle");
		GameRegistry.registerItem(titane_particle, "titane_particle");
		GameRegistry.registerItem(valerium_particle, "valerium_particle");
		GameRegistry.registerItem(doubleXPPotion, "doubleXPPotion");
		GameRegistry.registerItem(valerium_hammer, "valerium_hammer");
		GameRegistry.registerItem(scroll, "scroll");
		GameRegistry.registerItem(bag, "bag");
		GameRegistry.registerItem(azurite_scepter, "azurite_scepter");
		GameRegistry.registerItem(valerium_scepter, "valerium_scepter");
		GameRegistry.registerItem(titane_scepter, "titane_scepter");
		GameRegistry.registerItem(amethyst_scepter, "amethyst_scepter");
		}
}
