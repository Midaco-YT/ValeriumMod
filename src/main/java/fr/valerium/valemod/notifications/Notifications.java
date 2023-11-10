package fr.valerium.valemod.notifications;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class Notifications extends Gui {
	private static final ResourceLocation widget = new ResourceLocation(
			"textures/gui/achievement/achievement_background.png");
	private Minecraft mc;
	private int width;
	private int height;
	private String notificationTitle;
	private String notificationDescription;
	private long notificationTime;
	private boolean permanentNotification;

	public Notifications() {
		this.mc = FMLClientHandler.instance().getClient();
	}

	public void displayAchievement(String title, String description) {
		this.notificationTitle = I18n.format(title, new Object[0]);
		this.notificationDescription = description;
		this.notificationTime = Minecraft.getSystemTime();
		this.permanentNotification = false;
	}
	public void displayUnformattedAchievement(Achievement achievement) {
		this.notificationTitle = achievement.func_150951_e().getUnformattedText();
		this.notificationDescription = achievement.getDescription();
		this.notificationTime = Minecraft.getSystemTime() + 2500L;
		this.permanentNotification = true;
	}

	private void updateAchievementWindowScale() {
		GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		this.width = this.mc.displayWidth;
		this.height = this.mc.displayHeight;
		ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		this.width = scaledresolution.getScaledWidth();
		this.height = scaledresolution.getScaledHeight();
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, (double) this.width, (double) this.height, 0.0D, 1000.0D, 3000.0D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
	}

	public void updateAchievementWindow() {
		if (this.notificationTime != 0L && Minecraft.getMinecraft().thePlayer != null) {
			double d0 = (double) (Minecraft.getSystemTime() - this.notificationTime) / 3000.0D;

			if (!this.permanentNotification) {
				if (d0 < 0.0D || d0 > 1.0D) {
					this.notificationTime = 0L;
					return;
				}
			} else if (d0 > 0.5D) {
				d0 = 0.5D;
			}

			this.updateAchievementWindowScale();
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			double d1 = d0 * 2.0D;

			if (d1 > 1.0D) {
				d1 = 2.0D - d1;
			}

			d1 *= 4.0D;
			d1 = 1.0D - d1;

			if (d1 < 0.0D) {
				d1 = 0.0D;
			}

			d1 *= d1;
			d1 *= d1;
			int i = this.width - 160;
			int j = 0 - (int) (d1 * 36.0D);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			this.mc.getTextureManager().bindTexture(widget);
			GL11.glDisable(GL11.GL_LIGHTING);
			this.drawTexturedModalRect(i, j, 96, 202, 160, 32);

			if (this.permanentNotification) {
				this.mc.fontRenderer.drawSplitString(this.notificationDescription, i + 30, j + 7, 120, -1);
			} else {
				this.mc.fontRenderer.drawString(this.notificationTitle, i + 30, j + 7, -256);
				this.mc.fontRenderer.drawString(this.notificationDescription, i + 30, j + 18, -1);
			}

			// RenderHelper.enableGUIStandardItemLighting();
			// GL11.glDisable(GL11.GL_LIGHTING);
			// GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			// GL11.glEnable(GL11.GL_COLOR_MATERIAL);
			// GL11.glEnable(GL11.GL_LIGHTING);
			// this.renderItem.renderItemAndEffectIntoGUI(this.mc.fontRenderer,
			// this.mc.getTextureManager(), this.theAchievement.theItemStack, i
			// + 8, j + 8);
			// GL11.glDisable(GL11.GL_LIGHTING);
			// GL11.glDepthMask(true);
			// GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
	}

	public void clearAchievements() {
		this.notificationTime = 0L;
	}
}