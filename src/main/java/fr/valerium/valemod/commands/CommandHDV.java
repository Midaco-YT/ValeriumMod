package fr.valerium.valemod.commands;

import java.util.ArrayList;
import java.util.List;

import fr.valerium.valemod.ModVale;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandHDV extends CommandBase {
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "";
	}

	public String getCommandName() {
		return "market";
	}

	public List<String> getCommandAliases() {
		ArrayList<String> aliases = new ArrayList<String>();
		aliases.add("ah");
		return aliases;
	}

	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {
			EntityPlayerMP playermp = (EntityPlayerMP) sender;
			// sendpacket
			ModVale.network.sendTo(new PacketOpenHDV(), playermp);// Cette fois si enregistre ton packet côté
																	// CLIENT
		}
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
}
