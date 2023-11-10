package fr.valerium.valemod.jobs.events;

import fr.valerium.valemod.jobs.JobManager;
import fr.valerium.valemod.jobs.Tasks.Tasks;
import fr.valerium.valemod.jobs.Tasks.Tasks.TaskType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class TaskManager {
	
	EntityPlayer player;
    JobManager jobManager = JobManager.get(player);
	Tasks task;
	
	Tasks stone = new Tasks(1, JobManager.MINER_NAME, Blocks.stone, 10, 50, 1, 0, TaskType.MINING);

}
