package fr.valerium.valemod.jobs.Tasks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Tasks {
	
	public enum TaskType {
	    MINING,
	    SMELTING,
	    CRAFTING,
	}

    public int id;
    public String jobName;
    public Block block;
    public int baseXp;
    public int newXp;
    public int minLevel;
    public int blockMetadata;
    public TaskType taskType;
    public Item item;

    public Tasks(int id, String jobName, Object object, int baseXp, int newXp, int minLevel, int blockMetadata, TaskType taskType) {
        this.id = id;
        this.jobName = jobName;
        if (object instanceof Block) {
            this.block = (Block) object;
        } else if (object instanceof Item) {
            this.item = (Item) object;
        } else {
            throw new IllegalArgumentException("Invalid object type. Only Block or Item are supported.");
        }
        this.baseXp = baseXp;
        this.newXp = newXp;
        this.minLevel = minLevel;
        this.blockMetadata = blockMetadata;
        this.taskType = taskType;
    }
    
    public static class CraftingEvent extends Tasks {
        public CraftingEvent(int id, String jobName, ItemStack craftedItem, int baseXp, int newXp, int minLevel, int blockMetadata) {
            super(id, jobName, craftedItem.getItem(), baseXp, newXp, minLevel, blockMetadata, TaskType.CRAFTING);
        }
    }
    
    public static class BreakEvent extends Tasks {
        public BreakEvent(int id, String jobName, Block block, int baseXp, int newXp, int minLevel, int blockMetadata) {
            super(id, jobName, block, baseXp, newXp, minLevel, blockMetadata, TaskType.MINING);
        }
    }
}
