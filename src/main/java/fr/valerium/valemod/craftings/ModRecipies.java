package fr.valerium.valemod.craftings;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valerium.valemod.blocks.ModBlocks;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModRecipies {
	public static void init() {
		craftingRecipies();
		stoneCrafterRecipies();
		furnaceRecipies();
		mineralExtractor();
	}

	private static void stoneCrafterRecipies() {
		StoneCrafterRecipies.getManager().add(new ItemStack((Item)ModItems.random_ring), new ItemStack((Item)ModItems.valerium_ingot), new ItemStack((Item)ModItems.valerium_ingot), 1);
	}

	private static void mineralExtractor() {
		ItemStack input = new ItemStack(Blocks.cobblestone);
		ItemStack output = new ItemStack(ModItems.iron_particle);
		MineralExtractorRecipies.getInstance().registerRecipe(input, output);


	}

	
	private static void craftingRecipies() {
		GameRegistry.addRecipe(new ItemStack((Item)ModItems.random_ring), new Object[] { "XXX", "XXX", "XXX", 
		          Character.valueOf('X'), new ItemStack((Item)ModItems.valerium_axe) });
	}
	
	private static void furnaceRecipies() {
	    GameRegistry.addSmelting(new ItemStack((Block)ModBlocks.sulfur_ore), new ItemStack((Item)ModItems.sulfur_crystal), 0.1F);
	  }
}
