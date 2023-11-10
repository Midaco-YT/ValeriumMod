package fr.valerium.valemod.commands;

import java.util.ArrayList;
import java.util.List;

import fr.valerium.valemod.shop.ShopManager;
import fr.valerium.valemod.shop.ui.UIShopHomePage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class CommandShop extends CommandBase {
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "";
	}

	public String getCommandName() {
		return "shop";
	}

	public List<String> getCommandAliases() {
		ArrayList<String> aliases = new ArrayList<String>();
		aliases.add("adminshop");
		return aliases;
	}

	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new UIShopHomePage());
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
}
