package fr.valerium.valemod.client.gui.buttons;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiButtonJob extends GuiButton {

	private ResourceLocation buttonTexture = new ResourceLocation("valerium:textures/gui/element_job.png");
	
	public GuiButtonJob(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.displayString = buttonText;
	}
	
	public void drawButton(Minecraft mc, int i1, int i2) {
		FontRenderer fr = mc.fontRenderer;
		mc.getTextureManager().bindTexture(buttonTexture);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		boolean mouseDragged = i1 >= this.xPosition && i2 >= this.yPosition && i1 < this.xPosition + this.width && i2 < this.yPosition + this.height;
		this.mouseDragged(mc, i1, i2);
		int i3 = this.getHoverState(mouseDragged);
		int l = 14737632;
		
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0 + i3 * 22, this.width / 2, this.height);
		this.drawTexturedModalRect(this.xPosition + 11, this.yPosition, 11, 0 + i3 * 22, this.width / 2, this.height);
		this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 0 + i3 * 22, this.width / 2, this.height);
		
		this.drawCenteredString(fr, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
	}
	
}
