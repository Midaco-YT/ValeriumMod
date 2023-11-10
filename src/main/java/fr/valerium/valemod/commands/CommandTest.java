package fr.valerium.valemod.commands;

import fr.valerium.valemod.client.gui.GuiSpellSelection;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandTest extends CommandBase {

    @Override
    public String getCommandName() {
        return "test";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/test <message>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
    	if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            
            // Ouvrir votre interface utilisateur personnalisée
            Minecraft.getMinecraft().displayGuiScreen(new GuiSpellSelection());
    	}
        
    }
}
