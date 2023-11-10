package fr.valerium.valemod.jobs;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class JobLevelUtils {
    
	
	
    public static boolean canUseBlock(World world, int x, int y, int z, EntityPlayer player, String job, int requiredLevel) {
        JobManager manager = JobManager.get(player);
        int jobLevel = manager.levels.get(job);
        if (jobLevel < requiredLevel) {
            player.addChatMessage(new ChatComponentTranslation(I18n.format(EnumChatFormatting.DARK_RED + "[Jobs]" + EnumChatFormatting.RED + "Vous n'avez pas le niveau requis !")));
            return false;
        }
        return true;
    }
    
    public static boolean canPickupBlock(World world, EntityPlayer player, int x, int y, int z, String job, int i) {

        JobManager manager = JobManager.get(player);
        int jobLevel = manager.levels.get(job);
        if (jobLevel < i) {
            player.addChatMessage(new ChatComponentTranslation(I18n.format(EnumChatFormatting.DARK_RED + "[Jobs]" + EnumChatFormatting.RED + "Vous n'avez pas le niveau requis !")));
            return false;
        }
        return true;
    }
    
}
