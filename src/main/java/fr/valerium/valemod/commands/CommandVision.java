package fr.valerium.valemod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;

public class CommandVision extends CommandBase {
	@Override
	public String getCommandName() {
		return "vision";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "commands.vision.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments) {
		EntityPlayer player = (EntityPlayer) sender;
		if (player.isPotionActive(Potion.nightVision)) {
			for (int i = 0; i < 20; i++) {
				player.removePotionEffect(i);
			}
			player.addChatComponentMessage(
					new ChatComponentTranslation("§eVous venez de retirer la §bVision Nocturne§e!"));
		} else {
			player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 999999, 1));
			player.addChatComponentMessage(
					new ChatComponentTranslation("§eVous venez de d'activer la §bVision Nocturne§e!"));
		}
	}
}