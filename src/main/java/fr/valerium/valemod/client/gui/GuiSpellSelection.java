package fr.valerium.valemod.client.gui;

import java.util.ArrayList;
import java.util.List;

import fr.valerium.indium.ui.UI;
import fr.valerium.indium.utils.GuiUtils;
import fr.valerium.valemod.client.gui.buttons.UIButtonWheel;
import fr.valerium.valemod.spells.ModSpell;
import fr.valerium.valemod.spells.SpellSelection;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSpellSelection extends UI {
    private ResourceLocation background = new ResourceLocation("valerium", "textures/gui/spellWheel.png");

    EntityPlayer player = Minecraft.getMinecraft().thePlayer;

    private int centerX;
    private int centerY;

    private float buttonWidth = 20.0F;
    private float buttonHeight = 20.0F;
    
    private List<UIButtonWheel> buttons = new ArrayList<>();

    public void initGui() {
        centerX = width / 2;
        centerY = height / 2;

        float aspectRatio = (float) width / height;
        float buttonRadiusX = 0.055f * width; // Rayon horizontal
        float buttonRadiusY = 0.17f * height; // Rayon vertical

        // Ajoutez chaque bouton individuellement avec un angle de rotation ajusté
        //addButton("Button 1", buttonRadiusX, buttonRadiusY, 0, textureSizeY, textureSizeX, TexturePosY, TexturePosX);
        addButton("Button 1", buttonRadiusX, buttonRadiusY, 0, 90,200, 140, -23.0F, -15.0F);
        addButton("Button 2", buttonRadiusX, buttonRadiusY, 1, 90, 200, 200, -8.0F, -60.0F);
        addButton("Button 3", buttonRadiusX, buttonRadiusY, 2, 90, 145, 210, 2.0F, -44.0F);
        addButton("Button 4", buttonRadiusX, buttonRadiusY, 3, 90, 200, 200, -15.0F, -25.0F);
        addButton("Button 5", buttonRadiusX, buttonRadiusY, 4, 90, 200, 140, -32.0F, -17.0F);
        addButton("Button 6", buttonRadiusX, buttonRadiusY, 5, 90, 180, 180, -33.0F, -27.0F);
        addButton("Button 7", buttonRadiusX, buttonRadiusY, 6, 90, 140, 210, 3.0F, -55.0F);
        addButton("Button 8", buttonRadiusX, buttonRadiusY, 7, 90, 180, 200, -25.0F, -65.0F);
    }

    private void addButton(String buttonText, float buttonRadiusX, float buttonRadiusY, int index, float rotationAngle, int textureWidth, int textureHeight, float textureXOffset, float textureYOffset) {
        double angle = 2 * Math.PI * index / 8;

        float buttonX = (float) (centerX + buttonRadiusX * Math.cos(angle) - buttonWidth / 2);
        float buttonY = (float) (centerY + buttonRadiusY * Math.sin(angle) - buttonHeight / 2);

        buttons.add((UIButtonWheel) new UIButtonWheel(width(buttonX - 100), height(buttonY - 17), width(buttonWidth), height(buttonHeight),
                "mainmenu:textures/gui/main/button/wheel/button_" + index, Integer.toString(index), rotationAngle, textureWidth, textureHeight, textureXOffset, textureYOffset).setCallback(node -> SpellSelection.setSelectedSpell(ModSpell.ATTRACTIO))
            );
        
        
    }


    public void preDraw(int mouseX, int mouseY, float ticks) {
        GuiUtils.drawImageTransparent(width(35.0F), height(20.0F), this.background, width(30.0F), height(55.0F));
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
