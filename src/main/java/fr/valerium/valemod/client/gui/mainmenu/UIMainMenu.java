package fr.valerium.valemod.client.gui.mainmenu;

import org.lwjgl.opengl.Display;

import fr.valerium.indium.ui.UI;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import fr.valerium.indium.utils.text.TextAlign;
import fr.valerium.valemod.client.gui.GuiLoadingScreen;
import fr.valerium.valemod.client.gui.mainmenu.buttons.MainMenuButton;
import fr.valerium.valemod.client.gui.mainmenu.buttons.MainMenuValeButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;

public class UIMainMenu extends UI {

	private ResourceLocation logo = new ResourceLocation("mainmenu", "textures/gui/main/valerium.png");

	private long animationEnd;
	
	private boolean displaySetTitle = false;
	
	private int textPosition = 440;
    private String text = "§4Bienvenue sur Valerium";

	private int panoramaTimer;

	public UIMainMenu() {
		super(null, "mainmenu:main");
	}

	public void initGui() {
		if(!this.displaySetTitle) {
    		Display.setTitle("Valerium V1 | " + this.mc.getSession().getUsername());  
    		this.displaySetTitle = true;
    	}
		super.initGui();
		this.animationEnd = System.currentTimeMillis() + 1000L;
		addNode((new MainMenuButton(width(42.0F), height(40.0F), width(16.0F), height(7.0F), "", "SOLO", "indium:mainmenu/textures/gui/main/play.png")).setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiSelectWorld((GuiScreen)this.instance))));
		addNode((new MainMenuValeButton(width(42.0F), height(50.0F), width(16.0F), height(7.0F), "", "VALERIUM")).setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiLoadingScreen())));
		
	}
	
	public void preDraw(int mouseX, int mouseY, float ticks) {
    drawBackground();
    GuiUtils.drawImageTransparent(width(35.0F), height(10.0F), this.logo, width(30.0F), height(10.0F), false);
    drawSplittedString("Valerium 2023 - Tous droits réservés", width(98.0F), height(95.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.width, TextAlign.RIGHT);
    drawSplittedString("Copyright Mojang AB. Do not distribute! Thanks to Forge for the loader", width(98.0F), height(97.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.width, TextAlign.RIGHT);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    if (System.currentTimeMillis() < this.animationEnd) {
      long diff = this.animationEnd - System.currentTimeMillis();
      float opacity = (float)diff / 250.0F;
      GuiUtils.drawImageTransparent(0.0D, 0.0D, 0.0D, 0.0D, this.width, this.height, this.width, this.height, getBackground(), Color.white, opacity);
    } 
  }
  
  public void updateScreen()
  {
      ++this.panoramaTimer;
      
      if (textPosition < 0 - mc.fontRenderer.getStringWidth(text))
  	{
  	    textPosition = width / 2 + 200;
  	}
  	textPosition -= 3; 
  	
  }
  
  public boolean hasReturn() {
    return false;
  }
}
