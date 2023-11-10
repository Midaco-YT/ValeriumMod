package fr.valerium.valemod.client.gui.containers;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.Reference;
import fr.valerium.valemod.common.gui.ContainerRuneCrafter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiRuneCrafter extends GuiContainer{

	
	private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/stone_crafter.png");
	public GuiRuneCrafter(InventoryPlayer invPlayer, World world, int x, int y, int z) {
		super(new ContainerRuneCrafter(invPlayer, world, x, y, z));
		
		this.ySize = 255;
		this.xSize = 255;
	}

	
	public void onGuiClosed() {
		super.onGuiClosed();
	}


	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		
		//this.fontRendererObj.drawString(StatCollector.translateToLocal("Stone Crafter"), 65, 49, 0x000000);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Inventory"), 45, 115, 0x000000);
	}
	
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		
		drawTexturedModalRect(guiLeft + 37, guiTop + 13, 0, 0, xSize, ySize);
	}
}
