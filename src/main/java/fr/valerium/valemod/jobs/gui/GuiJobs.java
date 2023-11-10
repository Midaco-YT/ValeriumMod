package fr.valerium.valemod.jobs.gui;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.client.gui.buttons.ButtonFamer;
import fr.valerium.valemod.client.gui.buttons.ButtonHunter;
import fr.valerium.valemod.client.gui.buttons.ButtonMiner;
import fr.valerium.valemod.jobs.gui.JobsInfo.JobsInfoMiner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiJobs extends GuiScreen {

	private float xSizeFloat;
	/**
	 * y size of the inventory window in pixels. Defined as float, passed as int.
	 */
	private float ySizeFloat;
	private int guiLefte;
	private int guiTope;
	protected int xSize = 256;
	/** The Y size of the inventory window in pixels. */
	protected int ySize = 256;
	public Container inventorySlots;
	protected static RenderItem itemRender;

	private ButtonMiner myButton;

	final ResourceLocation bg = new ResourceLocation("valerium", "textures/gui/jobs/bar_on.png");
	final ResourceLocation bg2 = new ResourceLocation("valerium", "textures/gui/jobs/bar_off.png");
	final ResourceLocation bg3 = new ResourceLocation("valerium", "textures/gui//jobs/job_miner_1.png");
	final ResourceLocation bg4 = new ResourceLocation("valerium", "textures/gui/jobs/job_hunter_1.png");
	final ResourceLocation bg5 = new ResourceLocation("valerium", "textures/gui/jobs/job_farmer_1.png");
	final ResourceLocation bg6 = new ResourceLocation("valerium", "textures/gui/jobs/job_alchimist.png");
	final ResourceLocation cadremini = new ResourceLocation("valerium", "textures/gui/jobs/cadre.png");
	final ResourceLocation xpminer = new ResourceLocation("valerium", "textures/gui/jobs/xpbar_miner.png");
	final ResourceLocation xphunter = new ResourceLocation("valerium", "textures/gui/jobs/xpbar_hunter.png");
	final ResourceLocation xpfarmer = new ResourceLocation("valerium", "textures/gui/jobs/xpbar_farmer.png");
	final ResourceLocation xpalchi = new ResourceLocation("valerium", "textures/gui/jobs/xpbar_alchimiste.png");
	private static final ResourceLocation texture = new ResourceLocation("valerium",
			"textures/gui/jobs/JobsMenu.png");
	private int updateCounter = 0;

	public GuiJobs() {

	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		guiLefte = (this.width - this.xSize) / 2;
		guiTope = (this.height - this.ySize) / 2;
//		buttonList.add(new GuiButtonJob(66, guiLefte + 290, guiTope + 70, 22, 22, ""));//
//		buttonList.add(new ButtonCustom(67, guiLefte + 290, guiTope + 40, 22, 22, ""));//
//		buttonList.add(new ButtonMiner(68, guiLefte - 20, guiTope + 100, 70, 70, ""));//
		buttonList.add(new ButtonFamer(69, guiLefte + 100, guiTope + 66, 61, 61, ""));
		buttonList.add(new ButtonHunter(70, guiLefte + 37, guiTope + 66, 61, 61, ""));
		buttonList.add(new ButtonFamer(71, guiLefte + 163, guiTope + 66, 61, 61, ""));
		super.initGui();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id == 66) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiJobs());
		}

		if (button.id == 67) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(Minecraft.getMinecraft().thePlayer));
		}

		if (button.id == 69) {
			Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new JobsInfoMiner());
		}
		
		if (button.id == 69) {
			//Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new JobsInfoFarmer());//
		}
		
		if (button.id == 70) {
			//Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new JobsInfoMiner());//
		}
		
		if (button.id == 71) {
			//Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new JobsInfoAlchimist());//
		}

	}

	@Override
	public void updateScreen() {

		super.updateScreen();
	}

	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		mc.getTextureManager().bindTexture(texture);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		GL11.glScaled(1.0F, 1.0F, 1.0F);
		func_146110_a(guiLefte - 20, guiTope - -15, 0, 0, 300, 300, 300, 300);

		super.drawScreen(mouseX, mouseY, partialTick);
	}

	/*
	 * public void drawBarMiner(int mouseX, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.xpminer);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 89.0D, this.height /
	 * 2.0D - 18.5D, props.expHunter, 255.0D, 2.0D); }
	 * 
	 * public void drawBarHunter(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.xphunter);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 19.5D, this.height /
	 * 2.0D - 18.5D, props.expHunter, 255.0D, 2.0D); }
	 * 
	 * public void drawBarFarmer(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.xpfarmer);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - -50.5D, this.height /
	 * 2.0D - 18.5D, props.expHunter, 255.0D, 2.0D); }
	 * 
	 * public void drawCadreMiner(int mouseX, int mouseY, float partialTick) {
	 * 
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.cadremini);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 90.0D, this.height /
	 * 2.0D - 18.5D, 100.0D, 260.0D, 1.0D); }
	 * 
	 * public void drawCadreHunter(int mouseX, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.cadremini);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 20.0D, this.height /
	 * 2.0D - 18.5D, 100.0D, 260.0D, 1.0D); }
	 * 
	 * public void drawCadreFarmer(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.cadremini);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - -50.0D, this.height /
	 * 2.0D - 18.5D, 100.0D, 260.0D, 1.0D); }
	 * 
	 * public void drawMiner(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.bg3);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 67.0D, this.height /
	 * 2.0D - 40.5D, 18.0D, 20.0D, 0.0D); }
	 * 
	 * public void drawHunter(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.bg4);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 0.0D, this.height /
	 * 2.0D - 40.5D, 18.0D, 20.0D, 0.0D); }
	 * 
	 * public void drawFarmer(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.bg5);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - -70.0D, this.height /
	 * 2.0D - 40.5D, 18.0D, 20.0D, 0.0D); }
	 * 
	 * public void drawAlchimist(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.bg6);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 67.0D, this.height /
	 * 2.0D - -30.5D, 18.0D, 20.0D, 0.0D); }
	 * 
	 * public void drawTotalBar(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.bg);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 102.0D, this.height /
	 * 2.0D - 70.5D, props.getTotalLevel(), 398.0D, 1.0D); }
	 * 
	 * public void drawTotalCadre(int mouseC, int mouseY, float partialTick) {
	 * ExtendedEntityPropExp props = ExtendedEntityPropExp.get(mc.thePlayer);
	 * mc.renderEngine.bindTexture(this.bg2);
	 * DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 103.0D, this.height /
	 * 2.0D - 70.5D, 370.0D, 400.0D, 0.0D); }
	 * 
	 * public void drawToolTipsHunter(int mouseX, int mouseY) { int boxX =
	 * (this.width - this.xSize) / 2 + 62; // These are just like the center of the
	 * screen. int boxY = (this.height - this.ySize) / 2 + 66;// You make these in
	 * order to get a starting point on where to // check // (With these, it would
	 * be the top left of your GUI) ExtendedEntityPropExp props =
	 * ExtendedEntityPropExp.get(mc.thePlayer);
	 * 
	 * int defaultX = 64; // These are like the boxes of the area to check. int
	 * defaultY = 10; // So, if you're rendering an Item to hover over, it would be
	 * 16x16.
	 * 
	 * // This checks if the mouse is in the correct position on screen. You can add
	 * // and subtract from boxX and Y to determine where to be. if (mouseX > boxX
	 * && mouseX < boxX + defaultX && mouseY > boxY && mouseY < boxY + defaultY) {
	 * List list = new ArrayList(); // Just the list of text to be added. It is just
	 * like the addInformation method // now. GL11.glPushMatrix();
	 * list.add(props.expHunter + "/160"); // Each time you add something, it's
	 * gonna be a new line in the // tooltip. this.drawHoveringText(list, mouseX,
	 * mouseY, fontRendererObj); GL11.glDisable(GL11.GL_LIGHTING);
	 * GL11.glPopMatrix(); // End this matrix } }
	 * 
	 * public void drawToolTipsMiner(int mouseX, int mouseY) { int boxX =
	 * (this.width - this.xSize) / 2 - 22; // These are just like the center of the
	 * screen. int boxY = (this.height - this.ySize) / 2 + 66;// You make these in
	 * order to get a starting point on where to // check // (With these, it would
	 * be the top left of your GUI) ExtendedEntityPropExp props =
	 * ExtendedEntityPropExp.get(mc.thePlayer);
	 * 
	 * int defaultX = 64; // These are like the boxes of the area to check. int
	 * defaultY = 10; // So, if you're rendering an Item to hover over, it would be
	 * 16x16.
	 * 
	 * // This checks if the mouse is in the correct position on screen. You can add
	 * // and subtract from boxX and Y to determine where to be. if (mouseX > boxX
	 * && mouseX < boxX + defaultX && mouseY > boxY && mouseY < boxY + defaultY) {
	 * List list = new ArrayList(); // Just the list of text to be added. It is just
	 * like the addInformation method // now. GL11.glPushMatrix();
	 * list.add(props.expMiner + "/160"); // Each time you add something, it's gonna
	 * be a new line in the // tooltip. this.drawHoveringText(list, mouseX, mouseY,
	 * fontRendererObj); GL11.glDisable(GL11.GL_LIGHTING); GL11.glPopMatrix(); //
	 * End this matrix } }
	 * 
	 * public void drawToolTipsFarmer(int mouseX, int mouseY) { int boxX =
	 * (this.width - this.xSize) / 2 + 147; // These are just like the center of the
	 * screen. int boxY = (this.height - this.ySize) / 2 + 66;// You make these in
	 * order to get a starting point on where to // check // (With these, it would
	 * be the top left of your GUI) ExtendedEntityPropExp props =
	 * ExtendedEntityPropExp.get(mc.thePlayer);
	 * 
	 * int defaultX = 64; // These are like the boxes of the area to check. int
	 * defaultY = 10; // So, if you're rendering an Item to hover over, it would be
	 * 16x16.
	 * 
	 * // This checks if the mouse is in the correct position on screen. You can add
	 * // and subtract from boxX and Y to determine where to be. if (mouseX > boxX
	 * && mouseX < boxX + defaultX && mouseY > boxY && mouseY < boxY + defaultY) {
	 * List list = new ArrayList(); // Just the list of text to be added. It is just
	 * like the addInformation method // now. GL11.glPushMatrix();
	 * list.add(props.expFarmer + "/160"); // Each time you add something, it's
	 * gonna be a new line in the // tooltip. this.drawHoveringText(list, mouseX,
	 * mouseY, fontRendererObj); GL11.glDisable(GL11.GL_LIGHTING);
	 * GL11.glPopMatrix(); // End this matrix } }
	 * 
	 * public void drawToolTipsTotalXp(int mouseX, int mouseY) { int boxX =
	 * (this.width - this.xSize) / 2 - 16; // These are just like the center of the
	 * screen. int boxY = (this.height - this.ySize) / 2 + 12;// You make these in
	 * order to get a starting point on where to // check // (With these, it would
	 * be the top left of your GUI) ExtendedEntityPropExp props =
	 * ExtendedEntityPropExp.get(mc.thePlayer);
	 * 
	 * int defaultX = 232; // These are like the boxes of the area to check. int
	 * defaultY = 15; // So, if you're rendering an Item to hover over, it would be
	 * 16x16.
	 * 
	 * // This checks if the mouse is in the correct position on screen. You can add
	 * // and subtract from boxX and Y to determine where to be. if (mouseX > boxX
	 * && mouseX < boxX + defaultX && mouseY > boxY && mouseY < boxY + defaultY) {
	 * List list = new ArrayList(); // Just the list of text to be added. It is just
	 * like the addInformation method // now. GL11.glPushMatrix();
	 * list.add(props.getTotalLevel() + "/60000"); // Each time you add something,
	 * it's gonna be a new line in the // tooltip. this.drawHoveringText(list,
	 * mouseX, mouseY, fontRendererObj); GL11.glDisable(GL11.GL_LIGHTING);
	 * GL11.glPopMatrix(); // End this matrix } }
	 */
}
