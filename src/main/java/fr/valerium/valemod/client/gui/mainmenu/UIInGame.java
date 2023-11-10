package fr.valerium.valemod.client.gui.mainmenu;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fr.valerium.indium.ui.UI;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import fr.valerium.valemod.client.gui.mainmenu.buttons.MainMenuButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

public class UIInGame extends UI {

	private int x;
	private int y;

	private long animationEnd;
	

	public UIInGame() {
		super(null, "mainmenu:main");
	}

	public void initGui() {
		super.initGui();
		
		this.animationEnd = System.currentTimeMillis() + 1000L;
		addNode((new MainMenuButton(width(40.0F), height(40.0F), width(20.0F), height(7.0F), "", "Revenir en jeu", "").setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null))));
		addNode((new MainMenuButton(width(42.0F), height(50.0F), width(16.0F), height(7.0F), "mainmenu:textures/gui/main/option", "Options", "mainmenu:textures/gui/main/option").setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiOptions(this, this.mc.gameSettings)))));
//		addNode((new InGameDecoButton(width(40.0F), height(60.0F), width(20.0F), height(7.0F), "Déconnexion")).setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> {
//		    Minecraft mc = Minecraft.getMinecraft();
//		    mc.theWorld.sendQuittingDisconnectingPacket();
//		    mc.loadWorld((WorldClient) null);
//		    mc.displayGuiScreen(new GuiMainMenu());
//		}));
		if (!this.mc.isIntegratedServerRunning()) {
			addNode((new MainMenuButton(width(42.0F), height(60.0F), width(16.0F), height(7.0F), "", "Déconexion", "").setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new UIMainMenu()))));
		}

	}
	
	public void preDraw(int mouseX, int mouseY, float ticks) {
	    
		this.drawDefaultBackground();
		drawPlayer(this.width / 2, height / 3, 100, (float) ((this.width - 176) / 2 + height / 8) - mouseX, (float) ((this.height - 166) / 2 + 75) - mouseY, this.mc.thePlayer);
	    GuiUtils.drawCenteredStringWithCustomFont(mc, "- Valerium Menu -", this.x + this.width / 2.0D, this.y + height(5.0F) - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BLACK.getFont(), 130) / 2), Color.DARKPURPLE, Fonts.MONTSERRAT_BLACK.getFont(), 130);

	  }
	
	
	
	
	public static void drawPlayer(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_,
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
  
  public boolean hasReturn() {
    return false;
  }
}
