package fr.valerium.valemod.client.gui;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.client.gui.containers.ContainerSellHdv;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiSellHdv extends GuiContainer {

	private ResourceLocation background = new ResourceLocation("valerium", "textures/gui/market_sellV2.png");
	FontRenderer fr;

	public GuiSellHdv(EntityPlayer player) {
		super((Container) new ContainerSellHdv(player));
		this.xSize = 176;
		this.ySize = 114;
		this.fr = Minecraft.getMinecraft().fontRenderer;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float arg0, int mouseX, int mouseY) {
		drawBackground();

		// Centering GUI
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		// And after the slots from the player's inventory

	}

	protected void mouseClicked(int mouseX, int mouseY, int p_73864_3_) {
		super.mouseClicked(mouseX, mouseY, p_73864_3_);
		int k = (this.width - 212) / 2;
		int l = (this.height - 218) / 2;
		boolean hover = (mouseX >= k + 5 && mouseY >= l + 5 && mouseX < k + 5 + 60 && mouseY < l + 5 + 10);
		if (hover)
			Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
	}

	public void drawBackground() {
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		drawBack(this.background, guiLeft, guiTop, xSize, ySize, 0, 0);
		drawTexturedModalRect(guiLeft - 50, guiTop - 74, 0, 0, ySize + 142, xSize + 29);

		GL11.glPopMatrix();
	}

	private void drawBack(ResourceLocation location, int x, int y, int posX, int posY, int width, int height) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(x, y, posX, posY, width, height);
	}
}