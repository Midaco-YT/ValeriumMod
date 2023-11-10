package fr.valerium.valemod.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valerium.valemod.client.gui.GuiHDV;
import fr.valerium.valemod.commands.PacketOpenHDV;
import net.minecraft.client.Minecraft;

public class PacketOpenHDVHandler implements IMessageHandler<PacketOpenHDV, IMessage> {

	@Override
	public IMessage onMessage(PacketOpenHDV message, MessageContext ctx) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiHDV());
																
																
		return null;
	}
}
