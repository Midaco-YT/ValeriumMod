package fr.valerium.valemod.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.valerium.valemod.jobs.gui.GuiJobs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class JobsCommand extends CommandBase {
  public String getCommandUsage(ICommandSender p_71518_1_) {
    return "";
  }
  
  public String getCommandName() {
    return "shop";
  }
  
  public List<String> getCommandAliases() {
    ArrayList<String> aliases = new ArrayList<String>();
    aliases.add("shop");
    return aliases;
  }
  
  public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    try {
      (new Timer()).schedule(new TimerTask() {
            public void run() {
              Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiJobs());
            }
          },  10L);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public boolean canCommandSenderUseCommand(ICommandSender sender) {
    return true;
  }
}
