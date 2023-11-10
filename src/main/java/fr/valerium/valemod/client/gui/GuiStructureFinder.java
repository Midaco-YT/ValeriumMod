package fr.valerium.valemod.client.gui;


import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiStructureFinder extends GuiScreen {

	private static final int GUI_WIDTH = 180;
	private static final int GUI_HEIGHT = 40;

	private static final int TEXT_FIELD_WIDTH = 150;
	private static final int TEXT_FIELD_HEIGHT = 20;

	private static final int BUTTON_WIDTH = 40;
	private static final int BUTTON_HEIGHT = 20;

	public String structureName;
	private GuiTextField structureNameField;
	private GuiButton searchButton;
	

	public GuiStructureFinder() {
		this.structureName = "";
	}

	@Override
	public void initGui() {
		int top = (this.height - GUI_HEIGHT) / 2;
		int left = (this.width - GUI_WIDTH) / 2;


		structureNameField = new GuiTextField(fontRendererObj, left + 10, top + 10, TEXT_FIELD_WIDTH,
				TEXT_FIELD_HEIGHT);
		structureNameField.setText(structureName);
		structureNameField.setFocused(true);
		structureNameField.setCanLoseFocus(false);

		searchButton = new GuiButton(0, left + 55, top + 40, 60, 20, "Search");
		searchButton.packedFGColour = 0xffffff;

		this.buttonList.add(searchButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		structureNameField.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
	    if (button.id == 0) { // Bouton "Search"
	        
	    	OverlayStructureFinder overlay = new OverlayStructureFinder();
	        overlay.structureName = this.getStructureName();
	        overlay.search(structureName);
	        overlay.displayMessage = true;
	        this.mc.displayGuiScreen(null);
	    }
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			// Fermer l'interface graphique lorsque la touche Echap est appuyée
			this.mc.displayGuiScreen(null);
		}
		structureNameField.textboxKeyTyped(typedChar, keyCode);
		structureName = structureNameField.getText();
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public String getStructureName() {
		return this.structureName;
	}
}
