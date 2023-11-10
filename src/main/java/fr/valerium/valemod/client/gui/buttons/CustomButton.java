package fr.valerium.valemod.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class CustomButton extends GuiButton {
    private ResourceLocation normalTexture;
    private ResourceLocation hoverTexture;
    private boolean isHovered = false;
    private int textureWidth;
    private int textureHeight;

    public CustomButton(int buttonId, int x, int y, int buttonWidth, int buttonHeight, String buttonText, ResourceLocation normalTexture, ResourceLocation hoverTexture, int textureWidth, int textureHeight) {
        super(buttonId, x, y, buttonWidth, buttonHeight, buttonText);
        this.normalTexture = normalTexture;
        this.hoverTexture = hoverTexture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public void setHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }

    public ResourceLocation getNormalTexture() {
        return normalTexture;
    }

    public ResourceLocation getHoverTexture() {
        return hoverTexture;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(isHovered ? hoverTexture : normalTexture);

            int i = isHovered ? 2 : 1;
            int j = 1;

            // Dessiner la texture à l'intérieur du bouton sans changer la taille du bouton
            int xOffset = (this.width - textureWidth) / 2;
            int yOffset = (this.height - textureHeight) / 2;

            func_146110_a(this.xPosition + xOffset, this.yPosition + yOffset, 0, 0, textureWidth, textureHeight, 50, 50);

            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
