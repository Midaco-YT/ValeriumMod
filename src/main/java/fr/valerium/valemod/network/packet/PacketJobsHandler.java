package fr.valerium.valemod.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.jobs.JobManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;

public class PacketJobsHandler implements IMessageHandler<PacketJobs, IMessage> {

	@Override
	public IMessage onMessage(PacketJobs message, MessageContext ctx) {
		if (ctx.netHandler instanceof NetHandlerPlayServer) {
			JobManager jobManager = JobManager.get(((NetHandlerPlayServer) ctx.getServerHandler()).playerEntity);
			jobManager.levels = message.levels;
			jobManager.experience = message.experience;
			jobManager.maxExperience = message.maxExperience;
		}
		if (ctx.netHandler instanceof NetHandlerPlayClient) {
			JobManager jobManager = JobManager.get(getClientPlayer());
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	public static EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
}
