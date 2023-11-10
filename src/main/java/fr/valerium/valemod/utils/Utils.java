package fr.valerium.valemod.utils;

import java.util.HashMap;
import java.util.Map;

import fr.valerium.valemod.jobs.JobManager;
import fr.valerium.valemod.jobs.Tasks.Tasks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Utils {
	public static Map<Integer, Tasks> tasks = new HashMap<Integer, Tasks>();
	static {
		
		//tasks.put(id, new Tasks(id, Jobs, Blocks, exp, newXp, minLevel, metadata, TaskType));//
		
		// MINING //
		
		//§§ MINER §§//
		tasks.put(1, new Tasks.BreakEvent(1, JobManager.MINER_NAME, Blocks.stone, 1, 0, 5, 0));
		tasks.put(2, new Tasks.BreakEvent(2, JobManager.MINER_NAME, Blocks.coal_ore, 5, 0, 5, 0));
		tasks.put(3, new Tasks.BreakEvent(3, JobManager.MINER_NAME, Blocks.iron_ore, 3, 1, 5, 0));
		tasks.put(4, new Tasks.BreakEvent(4, JobManager.MINER_NAME, Blocks.diamond_ore, 30, 0, 3, 0));
		
		//§§ FARMER §§//
		tasks.put(5, new Tasks.BreakEvent(5, JobManager.FARMER_NAME, Blocks.wheat, 5, 2, 5, 7));
		tasks.put(6, new Tasks.BreakEvent(6, JobManager.FARMER_NAME, Blocks.potatoes, 5, 2, 5, 7));

		
		// CRAFTING //
		
		//§§ FARMER §§//
		tasks.put(7, new Tasks.CraftingEvent(7, JobManager.FARMER_NAME, new ItemStack(Items.bread), 5, 2, 5, 0));
	}
}
