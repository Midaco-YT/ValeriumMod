package fr.valerium.valemod.network.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.valerium.valemod.network.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class PacketSendMessage extends AbstractPacket {
	private String message;

	public PacketSendMessage() {
	}

	public PacketSendMessage(String message) {
		this.message = message;
	}

	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, message);
	}

	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		message = ByteBufUtils.readUTF8String(buffer);
	}

	public void handleClientSide(EntityPlayer player) {
		player.addChatComponentMessage(new ChatComponentText("Received message from server " + ": " + message));
	}

	public void handleServerSide(EntityPlayer player) {
		System.out.println("Message received from client: " + message);
	}
}
