package fr.valerium.valemod.commands;

import java.util.ArrayList;
import java.util.List;

import fr.valerium.valemod.jobs.JobManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldServer;

public class CommandGetXpMiner extends CommandBase {
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "";
	}

	public String getCommandName() {
		return "exp";
	}

	public List<String> getCommandAliases() {
		ArrayList<String> aliases = new ArrayList<String>();
		aliases.add("exp");
		return aliases;
	}

	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {
			EntityPlayerMP playermp = (EntityPlayerMP) sender;
			JobManager jobManager = JobManager.get(playermp);
			int currentExperience = jobManager.getExperience(JobManager.MINER_NAME);
			int currentLevel = jobManager.levels.get(JobManager.MINER_NAME);
			WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
			jobManager.setLevel(JobManager.FARMER_NAME, 3);
			if(!worldserver.isRemote) {
				
				ChatComponentTranslation xp = new ChatComponentTranslation("tu es " + currentLevel + "level");
				playermp.addChatMessage(xp);
			}
		}
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
}
