package fr.valerium.valemod.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketOpenHDV implements IMessage {

	public PacketOpenHDV() {
	}

	public PacketOpenHDV(EntityPlayer player) {
	}

	@Override
	public void toBytes(ByteBuf buffer) {
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
	}

	
}
