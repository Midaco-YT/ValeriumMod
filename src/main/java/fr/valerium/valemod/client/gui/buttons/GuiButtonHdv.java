package fr.valerium.valemod.client.gui.buttons;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.ResourceLocation;

public class GuiButtonHdv extends GuiButton {

	public ItemRenderer renderer;
	private ResourceLocation buttonTexture = new ResourceLocation("valerium:textures/gui/element_hdv.png");
	
	public GuiButtonHdv(int buttonId, int x, int y, int width, int hieght, String buttonText) {
		super(buttonId, x, y, width, hieght, buttonText);
	}
	
	public void drawButton(Minecraft mc, int i1, int i2) {
		FontRenderer fr = mc.fontRenderer;
		mc.getTextureManager().bindTexture(buttonTexture);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		boolean mouseDragged = i1 >= this.xPosition && i2 >= this.yPosition && i1 < this.xPosition + this.width && i2 < this.yPosition + this.height;
		this.mouseDragged(mc, i1, i2);
		int i3 = this.getHoverState(mouseDragged);
		
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0 * 20, this.width / 2, this.height);
		this.drawTexturedModalRect(this.xPosition + 10, this.yPosition, 10, 0 * 20, this.width / 2, this.height);
		this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 0 * 20, this.width / 2, this.height);
	}
}
