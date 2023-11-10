package fr.valerium.valemod.client.gui.mainmenu;

import fr.valerium.indium.ui.UI;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import fr.valerium.valemod.client.gui.mainmenu.buttons.MainMenuButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;

public class UITest extends UI {
	public static final Color WHITE = new Color(240, 240, 240);
	private int x;
	private int y;

	private long animationEnd;
	

	public UITest() {
		super(null, "mainmenu:main");
	}

	public void initGui() {
		super.initGui();
		
		this.animationEnd = System.currentTimeMillis() + 1000L;
		addNode((new MainMenuButton(width(40.0F), height(40.0F), width(20.0F), height(7.0F), "", "Revenir en jeu", "").setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null))));
		addNode((new MainMenuButton(width(42.0F), height(50.0F), width(16.0F), height(7.0F), "", "Options", "").setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiOptions(this, this.mc.gameSettings)))));
		if (!this.mc.isIntegratedServerRunning()) {
			addNode((new MainMenuButton(width(42.0F), height(60.0F), width(16.0F), height(7.0F), "", "Déconexion", "").setTexture("mainmenu:textures/gui/main/button/menu").setHoveredTexture("mainmenu:textures/gui/main/button/menu_hover").setCallback(node -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new UIMainMenu()))));
		}

	}
	
	public void preDraw(int mouseX, int mouseY, float ticks) {
		GuiUtils.drawRect(10.0D, 10.0D, this.mc.displayWidth, this.mc.displayHeight, WHITE);
	    GuiUtils.drawCenteredStringWithCustomFont(mc, "- Valerium Menu -", this.x + this.width / 2.0D, this.y + height(5.0F) - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BLACK.getFont(), 130) / 2), Color.DARKPURPLE, Fonts.MONTSERRAT_BLACK.getFont(), 130);

	  }
	
	
	
  
  public boolean hasReturn() {
    return false;
  }
}
