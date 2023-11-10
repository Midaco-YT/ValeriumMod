package fr.valerium.valemod.client.gui.mainmenu;

import fr.valerium.indium.nodes.abstracts.ANode;
import fr.valerium.indium.nodes.label.TextLineNode;
import fr.valerium.indium.ui.UI;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import fr.valerium.indium.utils.text.TextAlign;
import net.minecraft.client.multiplayer.GuiConnecting;

public class UILoadingScreen extends UI {

	private int updateCounter = 0;
	String chargementText = "§6Chargement du Serveur ...";
	String serverName = "§6Valerium";
	String texteHaut = "§6Connexion au serveur ! ...";
	String bfsName = "§6Serveur: Lobby";
	
	public UILoadingScreen() {
		super(null, "mainmenu:main");
	}
	
	public void initGui() {
		super.initGui();
	}
	
	public void updateScreen()
	  {
	    this.updateCounter += 1;
	    if (this.updateCounter == 100) {
	      this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, "localhost", 25565)); // Ip = L'ip du serveur ! Port = Port du serveur.
	    }
	    if (this.updateCounter >= 101) {
	      this.mc.displayGuiScreen(new UIMainMenu());
	    }
	  }
	
	
	public void preDraw(int mouseX, int mouseY, float ticks) {
		String percent = this.updateCounter + "§6%";
		drawSplittedString(percent, width(98.0F), height(95.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.width, TextAlign.RIGHT);
	    TextLineNode textLineNode1 = new TextLineNode(width(9.0F), height(57.0F), GuiUtils.getStringWidth(this.mc, chargementText.toUpperCase(), Fonts.MONTSERRAT_SEMIBOLD.getFont(), 35), GuiUtils.getFontHeight(this.mc, Fonts.MONTSERRAT_SEMIBOLD.getFont(), 35) * 4, "COSMETIQUES", Color.white, Fonts.MONTSERRAT_SEMIBOLD.getFont(), 35);
	    textLineNode1.setEnabled(false);
	    textLineNode1.setY(-this.height);
	    addNode((ANode)textLineNode1);
		drawSplittedString(serverName, width(98.0F), height(95.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.width, TextAlign.RIGHT);
		drawSplittedString(texteHaut, width(98.0F), height(95.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.width, TextAlign.RIGHT);
		drawSplittedString(bfsName, width(98.0F), height(95.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.width, TextAlign.RIGHT);
	    drawBackground();
	  }
}
