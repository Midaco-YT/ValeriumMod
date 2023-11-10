package fr.valerium.valemod.client.gui;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.ModVale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiHDV extends GuiScreen {

	private ResourceLocation background = new ResourceLocation("valerium", "textures/gui/HDVv2.png");

	private final int xSize = 256;
	private final int ySize = 256;

	private int guiLeft;
	private int guiTop;

	private GuiTextField text;
	private Minecraft minecraft;
	EntityPlayer player = Minecraft.getMinecraft().thePlayer;

	public void initGui() {
		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;
		this.text = new GuiTextField(this.fontRendererObj, this.width / 2 - 68, this.height / 2 -75, 100, 20);
		text.setMaxStringLength(10);
		text.setText("");
		       this.text.setFocused(true);
		buttonList.add(new GuiButton(0, guiLeft + 190, guiTop + 83, 50, 20, "Vendre"));

	}

	public void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			player.openGui(ModVale.instance, 1, player.worldObj, 0, 0, 0);
		}
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawBackground();
		drawString(fontRendererObj, "Prix", guiLeft + 185, guiTop + 111, 0xBCBCBC);
		drawString(fontRendererObj, "Item", guiLeft + 13, guiTop + 111, 0xBCBCBC);
		drawString(fontRendererObj, "Nom", guiLeft + 40, guiTop + 111, 0xBCBCBC);
		drawString(fontRendererObj, "Recherche:", guiLeft + 13, guiTop + 88, 0xEEEEEE);
		drawString(fontRendererObj, " $", guiLeft + 157, guiTop + 90, 0xEEEEEE);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void drawBackground() {
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		drawBack(this.background, guiLeft, guiTop, xSize, ySize, 0, 0);
		drawTexturedModalRect(guiLeft - 10, guiTop + 30, 0, 0, ySize, xSize);

		GL11.glPopMatrix();
	}
	
	public String getMoney(String money) {
	    if (!money.contains(",")) {
	      return money;
	    }
	    String[] sp = money.split(",");
	    
	    String m = sp[0];
	    
	    if (sp.length == 2) {
	      return m + "K";
	    }
	    
	    if (sp.length == 3) {
	      return m + " million";
	    }
	    
	    if (sp.length == 4) {
	      return m + " milliard";
	    }
	    
	    if (sp.length == 5) {
	      return m + " billion";
	    }
	    
	    if (sp.length == 6) {
	      return m + " billiard";
	    }

	    
	    return m + "M";
	  }


	private void drawBack(ResourceLocation location, int x, int y, int posX, int posY, int width, int height) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(x, y, posX, posY, width, height);
	}
	
	protected void keyTyped(char par1, int par2)
	{
	super.keyTyped(par1, par2);
	this.text.textboxKeyTyped(par1, par2);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void updateScreen() {
		super.updateScreen();
	}
}
