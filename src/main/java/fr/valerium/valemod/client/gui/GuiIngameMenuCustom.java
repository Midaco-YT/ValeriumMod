package fr.valerium.valemod.client.gui;

import java.awt.Desktop;
import java.net.URI;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fr.valerium.valemod.client.gui.buttons.ButtonMenu;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiIngameMenuCustom extends GuiScreen {

	private int field_146445_a;
	private int field_146444_f;
	private static final String __OBFID = "CL_00000703";
	private static final ResourceLocation head = new ResourceLocation("textures/gui/head.png");

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@SuppressWarnings("unchecked")
	public void initGui() {

		this.field_146445_a = 0;
		this.buttonList.clear();
		byte var1 = -16;
		boolean var2 = true;
		this.buttonList.add(new ButtonMenu(1, this.width / 2 - 70, this.height / 4 + 130, 150, 30, "Deconnexion"));

		if (!this.mc.isIntegratedServerRunning()) {
			((GuiButton) this.buttonList.get(0)).displayString = I18n.format("Deconnexion", new Object[0]);
		}
		
		this.buttonList.add(new ButtonMenu(0, this.width / 2 - 70, this.height / 4 + 95, 150, 30, "Options"));

		this.buttonList.add(new ButtonMenu(4, this.width / 2 - 70, this.height / 4 + 60, 150, 30, "Revenir en jeu"));

	}

	protected void actionPerformed(GuiButton p_146284_1_) {
		switch (p_146284_1_.id) {
		case 0:
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
			break;

		case 1:
			p_146284_1_.enabled = false;
			this.mc.theWorld.sendQuittingDisconnectingPacket();
			this.mc.loadWorld((WorldClient) null);
			this.mc.displayGuiScreen(new GuiMainMenu());
			break;
		case 2:
			try {
				URI var2 = new URI("http://shandora.fr");

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(var2);
				}
			} catch (Exception var7) {
				var7.printStackTrace();
			}
			break;
		case 3:
			try {
				URI var2 = new URI("http://shandora.fr/wiki/");

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(var2);
				}
			} catch (Exception var7) {
				var7.printStackTrace();
			}
			break;
		default:
			break;

		case 4:
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
			break;

		case 5:
			this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
			break;

		case 6:
			this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
			break;

		case 8:
			

		}
	}

	public static void func_147046_a(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_,
			float p_147046_4_, EntityLivingBase p_147046_5_) {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) p_147046_0_, (float) p_147046_1_, 50.0F);
		GL11.glScalef((float) (-p_147046_2_), (float) p_147046_2_, (float) p_147046_2_);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float var6 = p_147046_5_.renderYawOffset;
		float var7 = p_147046_5_.rotationYaw;
		float var8 = p_147046_5_.rotationPitch;
		float var9 = p_147046_5_.prevRotationYawHead;
		float var10 = p_147046_5_.rotationYawHead;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan((double) (p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		p_147046_5_.renderYawOffset = (float) Math.atan((double) (p_147046_3_ / 40.0F)) * 20.0F;
		p_147046_5_.rotationYaw = (float) Math.atan((double) (p_147046_3_ / 40.0F)) * 40.0F;
		p_147046_5_.rotationPitch = -((float) Math.atan((double) (p_147046_4_ / 40.0F))) * 20.0F;
		p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
		p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
		GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		p_147046_5_.renderYawOffset = var6;
		p_147046_5_.rotationYaw = var7;
		p_147046_5_.rotationPitch = var8;
		p_147046_5_.prevRotationYawHead = var9;
		p_147046_5_.rotationYawHead = var10;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	public void drawTextureWithOptionalSize(int x, int y, int u, int v, int width, int height, int uSize, int vSize) {
		float scaledX = (float) 1 / uSize;
		float scaledY = (float) 1 / vSize;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + height), (double) this.zLevel,
				(double) ((float) (u + 0) * scaledX), (double) ((float) (v + height) * scaledY));
		tessellator.addVertexWithUV((double) (x + width), (double) (y + height), (double) this.zLevel,
				(double) ((float) (u + width) * scaledX), (double) ((float) (v + height) * scaledY));
		tessellator.addVertexWithUV((double) (x + width), (double) (y + 0), (double) this.zLevel,
				(double) ((float) (u + width) * scaledX), (double) ((float) (v + 0) * scaledY));
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) this.zLevel,
				(double) ((float) (u + 0) * scaledX), (double) ((float) (v + 0) * scaledY));
		tessellator.draw();
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	public void updateScreen() {
		super.updateScreen();
		++this.field_146444_f;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		func_147046_a(this.width / 2, height / 3, 35, (float) ((this.width - 176) / 2 + height / 8) - par1,
				(float) ((this.height - 166) / 2 + 75) - par2, this.mc.thePlayer);

		this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.DARK_PURPLE + "- Valerium Menu -",
				this.width / 2, 40, 16777215);

		super.drawScreen(par1, par2, par3);
	}
}