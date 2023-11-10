package fr.valerium.valemod.network;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.network.packet.PacketSendMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

@Sharable
public class PacketPipeline extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket> {
    private EnumMap<Side, FMLEmbeddedChannel> channels;

    private LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList<Class<? extends AbstractPacket>>();

    private boolean isPostInitialised = false;

    public boolean registerPacket(Class<? extends AbstractPacket> clazz) {
        if (this.packets.size() > 256)
            return false;
        if (this.packets.contains(clazz))
            return false;
        if (this.isPostInitialised)
            return false;
        this.packets.add(clazz);
        return true;
    }

    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().heapBuffer(); // Utilisez heapBuffer() au lieu de buffer()
        Class<? extends AbstractPacket> clazz = (Class) msg.getClass();
        if (!this.packets.contains(msg.getClass()))
            throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
        byte discriminator = (byte) this.packets.indexOf(clazz);
        buffer.writeByte(discriminator);
        msg.encodeInto(ctx, buffer);
        FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
        out.add(proxyPacket);
    }


    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
        EntityPlayer player;
        EntityPlayerMP entityPlayerMP;
        INetHandler netHandler;
        ByteBuf payload = msg.payload();
        byte discriminator = payload.readByte();
        Class<? extends AbstractPacket> clazz = this.packets.get(discriminator);
        if (clazz == null)
            throw new NullPointerException("No packet registered for discriminator: " + discriminator);
        AbstractPacket pkt = clazz.newInstance();
        pkt.decodeInto(ctx, payload.slice());
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT:
                player = getClientPlayer();
                pkt.handleClientSide(player);
                break;
            case SERVER:
                netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
                entityPlayerMP = ((NetHandlerPlayServer) netHandler).playerEntity;
                pkt.handleServerSide(entityPlayerMP);
                break;
        }
    }

    public void initialize() {
        this.channels = NetworkRegistry.INSTANCE.newChannel("ForgeToBukkit", this);
        registerPackets();

        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            System.out.println("PacketPipeline initialized on Server");
        } else if (side == Side.CLIENT) {
            System.out.println("PacketPipeline initialized on Client");
        } else {
            System.out.println("PacketPipeline not initialized on Client");
        }
    }

    public void registerPackets() {
        registerPacket(PacketSendMessage.class);
    }

    public void postInitialise() {
        if (this.isPostInitialised)
            return;
        this.isPostInitialised = true;
        Collections.sort(this.packets, new Comparator<Class<? extends AbstractPacket>>() {
            public int compare(Class<? extends AbstractPacket> clazz1, Class<? extends AbstractPacket> clazz2) {
                int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
                if (com == 0)
                    com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
                return com;
            }
        });
    }

    @SideOnly(Side.CLIENT)
	private static EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
    public void sendToAll(AbstractPacket message) {
        ((FMLEmbeddedChannel) this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        ((FMLEmbeddedChannel) this.channels.get(Side.SERVER)).writeAndFlush(message);
    }

    public void sendTo(AbstractPacket message, EntityPlayerMP player) {
        ((FMLEmbeddedChannel) this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        ((FMLEmbeddedChannel) this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        ((FMLEmbeddedChannel) this.channels.get(Side.SERVER)).writeAndFlush(message);
    }

    public void sendToServer(AbstractPacket message) {
        if (channels == null) {
            System.out.println("Error: The network channel is not available. Cannot send packet to server.");
            return;
        }
        ((FMLEmbeddedChannel) this.channels.get(Side.CLIENT)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        ((FMLEmbeddedChannel) this.channels.get(Side.CLIENT)).writeAndFlush(message);
    }

    public EnumMap<Side, FMLEmbeddedChannel> getChannels() {
        return channels;
    }

}
