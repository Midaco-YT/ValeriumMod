package fr.valerium.valemod.client.gui;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiCustomMainMenu extends GuiScreen {

    private static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID + ":textures/gui/background.jpg");
    private int textPosition = 440;
    private String text = "§4Bienvenue sur Valerium";
    
    
	private void addSingleplayerMultiplayerButtons(int yPos, int yInterval)
    {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, yPos, I18n.format("menu.singleplayer")));
        //this.buttonList.add(new GuiButton(2, this.width / 2 - 100, yPos + yInterval * 1, I18n.format("menu.multiplayer", new Object[0])));
        this.buttonList.add(new GuiButton(20, this.width / 2 - 100, yPos + yInterval * 1, I18n.format("menu.localserver")));
        this.buttonList.add(new GuiButton(55, this.width / 2 - 100, yPos + yInterval * 1, I18n.format("Valerium")));
        
    }
	
	protected void actionPerformed(GuiButton p_146284_1_) {
		
		if (p_146284_1_.id == 55) {
            this.mc.displayGuiScreen(new GuiLoadingScreen());
          }
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		drawBackground();
		
		drawRect(0, 0, width, 12, 0x66000000);
        this.drawString(this.fontRendererObj, text, this.textPosition, 2, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
	
	public void drawBackground()
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        Tessellator var2 = Tessellator.instance;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        mc.getTextureManager().bindTexture(background);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        var2.startDrawingQuads();
        var2.addVertexWithUV(0.0D, height, 0.0D, 0.0D, 1.0D);
        var2.addVertexWithUV(width, height, 0.0D, 1.0D, 1.0D);
        var2.addVertexWithUV(width, 0.0D, 0.0D, 1.0D, 0.0D);
        var2.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        var2.draw();
    }
}
