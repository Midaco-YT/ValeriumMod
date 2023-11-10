package fr.valerium.valemod.client.gui;

import fr.valerium.valemod.jobs.Tasks.Tasks;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.stats.StatFileWriter;

public class GuiExpInfo extends GuiAchievements {
	
	Tasks task;
	long experience = task.baseXp;
    String jobName = task.jobName;
    
	
	
  public GuiExpInfo(GuiScreen p_i45026_1_, StatFileWriter p_i45026_2_) {
		super(p_i45026_1_, p_i45026_2_);
		// TODO Auto-generated constructor stub
	}
  @Override
  public void initGui() {
 
    super.initGui();

   
  }


  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      // R�cup�rez les informations sur l'exp�rience et le m�tier du joueur � partir de l'instance de JobManager

      // Utilisez les m�thodes de rendu de textes et de graphiques de Minecraft pour dessiner le GUI sur l'�cran
      drawCenteredString(mc.fontRenderer, "Exp�rience : " + experience, width / 2, height / 2 - 50, 0xffffff);
      drawCenteredString(mc.fontRenderer, "M�tier : " + jobName, width / 2, height / 2 - 30, 0xffffff);
  }

}
