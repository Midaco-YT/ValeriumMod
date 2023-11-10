package fr.valerium.valemod.jobs.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;


public class GuiButtonJobsMiner extends GuiButton {
	
	private ResourceLocation buttonMiner = new ResourceLocation("valerium", "textures/gui/jobs/icon/dada.png");
	
	public GuiButtonJobsMiner(int buttonId, int x, int y, int widthIn, int HeightIn, String buttonText) {
		super(buttonId, x, y, widthIn, HeightIn, buttonText);
	}
	
	public void drawButton(Minecraft mc, int i1, int i2) {
		FontRenderer fr = mc.fontRenderer;
		mc.getTextureManager().bindTexture(buttonMiner);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		boolean mouseDragged = i1 >= this.xPosition && i2 >= this.yPosition && i1 < this.xPosition + this.width && i2 < this.yPosition + this.height;
		this.mouseDragged(mc, i1, i2);
		int i3 = this.getHoverState(mouseDragged);
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 10 + i3 * 20, this.width / 2, this.height);
        this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i3 * 20, this.width / 2, this.height);
	}
}