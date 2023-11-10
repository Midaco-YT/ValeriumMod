package fr.valerium.valemod.commands;

import java.util.ArrayList;
import java.util.List;

import fr.valerium.valemod.jobs.JobManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class CommandTets extends CommandBase {
  public String getCommandUsage(ICommandSender p_71518_1_) {
    return "";
  }
  
  public String getCommandName() {
    return "info";
  }
  
  public List<String> getCommandAliases() {
    ArrayList<String> aliases = new ArrayList<String>();
    aliases.add("adminjobs");
    return aliases;
  }
  
  public void processCommand(ICommandSender sender, String[] args) throws CommandException {
	  EntityPlayer player = (EntityPlayer) sender;
	  JobManager jobManager = JobManager.get(player);
	  int exp = jobManager.experience.get(JobManager.MINER_NAME);
	  int level = jobManager.levels.get(JobManager.MINER_NAME);
	  sender.addChatMessage(new ChatComponentText(JobManager.MINER_NAME + " : level " + level));
  }
  
  public boolean canCommandSenderUseCommand(ICommandSender sender) {
    return true;
  }
}
