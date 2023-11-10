package fr.valerium.valemod.client.gui.buttons;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class ButtonInvisible extends GuiButton {

	private ResourceLocation buttonTexture = new ResourceLocation("valerium:textures/gui/jobs/cadred.png");
	
	public ButtonInvisible(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.displayString = buttonText;
	}
	
	public void drawButton(Minecraft mc, int i1, int i2) {
		FontRenderer fr = mc.fontRenderer;
		mc.getTextureManager().bindTexture(buttonTexture);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		
		//DisplayHelper.drawTexturedQuadFit(this.width/ 2.0D - 90.0D, this.height/ 2.0D - 40.5D, 66.0D, 9.0D, 1.0D);//
		this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 0 * 20, this.width / 2, this.height);
		
	}
}
