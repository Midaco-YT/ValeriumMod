package fr.valerium.valemod.network.packet;

import fr.valerium.valemod.network.AbstractPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PacketHandler extends SimpleChannelInboundHandler<AbstractPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket packet) throws Exception {
        // Traite le paquet reçu
    }
}
