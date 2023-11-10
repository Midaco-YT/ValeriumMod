package fr.valerium.valemod.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valerium.valemod.jobs.JobManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class OverlayStructureFinder {

	boolean displayMessage = true;
	boolean structureFound = false;
	public String structureName;
	FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
	

	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent.Post event) {
		if (event.type.equals(ElementType.ALL)) {
			if (displayMessage) {
				JobManager jobManager = JobManager.get(Minecraft.getMinecraft().thePlayer);
				int minerExperience = jobManager.getExperience(JobManager.MINER_NAME);
				int minerLevel = jobManager.getLevel(JobManager.MINER_NAME);
				fontRenderer.drawString("Structure", 10, 60, 0xffffff);
				fontRenderer.drawString("Name " + minerExperience, 10, 70, 0xA3A3A3);

				fontRenderer.drawString("Coordinates", 10, 90, 0xffffff);
				fontRenderer.drawString("x, y, z", 10, 100, 0xA3A3A3);

				fontRenderer.drawString("Distances", 10, 120, 0xffffff);
				fontRenderer.drawString("Block", 10, 130, 0xA3A3A3);

			}
		}
	}

	public void setMessageOn(boolean displayMessage) {
		this.displayMessage = displayMessage;
	}

	public void search(String structureType) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		World world = player.worldObj;
		String structureName = "";
		ChunkPosition chunkposition = null;
		switch (structureType) {
		case "Stronghold":
			structureName = "Stronghold";
			chunkposition = world.findClosestStructure(structureName, (int) player.posX, (int) player.posY,
					(int) player.posZ);
			break;
		case "Mineshaft":
			structureName = "Mineshaft";
			chunkposition = world.findClosestStructure(structureName, (int) player.posX, (int) player.posY,
					(int) player.posZ);
			break;
		case "Village":
			structureName = "Village";
			chunkposition = world.findClosestStructure(structureName, (int) player.posX, (int) player.posY,
					(int) player.posZ);
			break;
		default:
			System.out.println("Invalid structure type");
			return;
		}

		if (chunkposition != null) {
			player.addChatMessage(new ChatComponentText("The nearest " + structureName + " is located at x:"
					+ chunkposition.chunkPosX + " y:" + chunkposition.chunkPosY + " z:" + chunkposition.chunkPosZ));
		} else {
			player.addChatMessage(new ChatComponentText("No " + structureName + " found in loaded chunks"));
		}
	}

	public List<String> getList() {
		World world = Minecraft.getMinecraft().theWorld;
		List<String> structures = new ArrayList<String>(
				Arrays.asList("Mineshaft", "Village", "Stronghold", "Temple", "Fortress"));
		return structures;

	}

}