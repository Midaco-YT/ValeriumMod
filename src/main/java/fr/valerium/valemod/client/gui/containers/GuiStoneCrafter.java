package fr.valerium.valemod.client.gui.containers;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.common.gui.ContainerStoneCrafter;
import fr.valerium.valemod.tiles.TileEntityStoneCrafter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiStoneCrafter extends GuiContainer {
	public static ResourceLocation background = new ResourceLocation("valerium:textures/gui/stone_crafter_v2.png");

	private TileEntityStoneCrafter tile;

	public GuiStoneCrafter(TileEntityStoneCrafter tile, InventoryPlayer inventory) {
	    super((Container)new ContainerStoneCrafter(tile, inventory));
	    this.tile = tile;
	    this.ySize = 256;
	    this.xSize = 256;
	  }

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.5F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(background);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		drawTexturedModalRect(k + 40, l - 3, 0, 0, this.xSize, this.ySize);
		if (this.tile.isBurning()) {
			int i = this.tile.getCookProgress();
			drawTexturedModalRect(k + 90, l + 122, 201, 105, 20, i);
		}
	}
}