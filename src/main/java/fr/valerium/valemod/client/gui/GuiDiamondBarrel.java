package fr.valerium.valemod.client.gui;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.client.gui.containers.ContainerDiamondBarrel;
import fr.valerium.valemod.tiles.TileEntityDiamondBarrel;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiDiamondBarrel extends GuiContainer {

	private ResourceLocation background = new ResourceLocation("valerium", "textures/gui/container/diamond.png");
	FontRenderer fr;

	private TileEntityDiamondBarrel tile;

	public GuiDiamondBarrel(TileEntityDiamondBarrel tile, InventoryPlayer inventory) {
	    super((Container)new ContainerDiamondBarrel(tile, inventory));
	    this.tile = tile;
	    this.ySize = 256;
	    this.xSize = 256;
	    
	  }

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.5F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(background);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		drawTexturedModalRect(k + 9, l, 0, 0, this.xSize, this.ySize);
	}
}