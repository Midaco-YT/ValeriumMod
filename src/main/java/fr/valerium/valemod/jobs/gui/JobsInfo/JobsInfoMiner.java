package fr.valerium.valemod.jobs.gui.JobsInfo;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.jobs.JobManager;
import fr.valerium.valemod.utils.DisplayHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class JobsInfoMiner extends GuiScreen {

	private int guiLeft;
	private int guiTop;
	protected int xSize = 256;
	protected int ySize = 256;
	private JobManager jobManager;
	
	final ResourceLocation background = new ResourceLocation("valerium", "textures/gui/jobs/back/Mineur.png");
	final ResourceLocation xp = new ResourceLocation("valerium", "textures/gui/jobs/bar/barminer.png");
	final ResourceLocation progressBar = new ResourceLocation("valerium", "textures/gui/jobs/barempty.png");
	final ResourceLocation iconMiner = new ResourceLocation("valerium", "textures/gui/jobs/icon/Mineur.png");

	
	public JobsInfoMiner() {

	}

	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void initGui() {
		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;
		super.initGui();
	}
	
	@Override
	public void updateScreen() {

		super.updateScreen();
	}
	
	
	
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		JobManager jobManager = JobManager.get(Minecraft.getMinecraft().thePlayer);
		mc.getTextureManager().bindTexture(background);
		int minerExperience = jobManager.experience.get(JobManager.MINER_NAME);
		int minerLevel = jobManager.getLevel(JobManager.MINER_NAME);
		//System.out.println(minerExperience);//
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		
		GL11.glScaled(1.0F, 1.0F, 1.0F);
		
		func_146110_a(guiLeft - 20, guiTop - -15, 0, 0, 300, 300, 300, 300);
		if(mc.theWorld.isRemote) {drawString(fontRendererObj, "Exp : " + minerExperience, guiLeft + 85, guiTop + 78, 0xFFFFFF);}
		drawString(fontRendererObj, "Niveau : " + jobManager.levels.get(JobManager.MINER_NAME), guiLeft + 85, guiTop + 68, 0xFFFFFF);
		
		mc.getTextureManager().bindTexture(iconMiner);
		//func_146110_a(this.xSize + 30, this.ySize - 30, 0, 0, this.width - 890, this.height - 440, 70, 70);//
		drawXpMiner(mouseX, mouseY, partialTick);
		drawCadreMiner(mouseX, mouseY, partialTick);
		super.drawScreen(mouseX, mouseY, partialTick);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		super.keyTyped(typedChar, keyCode);
	}
	
	public void drawXpMiner(int mouseX, int mouseY, float partialTick) {
		// Récupérez le JobManager du joueur
		  JobManager jobManager = JobManager.get(Minecraft.getMinecraft().thePlayer);

		  // Récupérez l'expérience et le niveau du métier "Miner" du joueur
		  int minerExperience = jobManager.getExperience(JobManager.MINER_NAME);
		  int minerLevel = jobManager.getLevel(JobManager.MINER_NAME);

		  // Affichez le niveau actuel du joueur pour le métier de mineur
		  drawString(fontRendererObj, "Niveau : " + minerLevel, guiLeft + 85, guiTop + 68, 0xFFFFFF);

		  // Calculate the width of the experience bar based on the player's current experience and the required experience for the current level
		  int xpWidth = (int) ((float) minerExperience / (float) jobManager.getMaxExperience(JobManager.MINER_NAME) * 100);

		  // If the experience bar width is greater than 100, set it to 100
		  if (xpWidth > 100) {
		    xpWidth = 100;
		  }

		  // Bind the texture for the experience bar
		  mc.getTextureManager().bindTexture(xp);

		  // Draw the experience bar with the calculated width
		  DisplayHelper.drawTexturedQuadFit(guiLeft + 19, guiTop + 134, xpWidth, 8, 0, 0, xpWidth, 8, 256, 256);
	
	}
	
	public void drawCadreMiner(int mouseX, int mouseY, float partialTick) {

		mc.renderEngine.bindTexture(this.progressBar);
		DisplayHelper.drawTexturedQuadFit(this.width / 2.0D - 110.0D, this.height / 2.0D, 206.0D, 20.0D, 0.0D, 0.0D, 120.0D, 20.0D, 120, 20);
	}
}
