package fr.valerium.valemod.client.gui.buttons;

import org.lwjgl.opengl.GL11;

import fr.valerium.indium.nodes.abstracts.AClickableNode;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class UIButtonWheel extends AClickableNode {
    private String text;
    private ResourceLocation texture = null;
    private ResourceLocation hoveredTexture = null;
    private float rotationAngle = 0.0F;
    private int textureWidth;
    private int textureHeight;
	private float textureXOffset;
	private float textureYOffset;

    public UIButtonWheel(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public UIButtonWheel(double x, double y, double width, double height, String texture, String text, float rotationAngle, int textureWidth, int textureHeight, float textureXOffset, float textureYOffset) {
        super(x, y, width, height);
        this.text = text;
        setTexture(texture);
        setHoveredTexture(texture + "_hover");
        setRotationAngle(rotationAngle);
        setTextureSize(textureWidth, textureHeight);
        setTextureOffsets(textureXOffset, textureYOffset);
    }

    public void setTextureSize(int width, int height) {
        this.textureWidth = width;
        this.textureHeight = height;
    }

    public void setRotationAngle(float angle) {
        this.rotationAngle = angle;
    }

    public float getRotationAngle() {
        return this.rotationAngle;
    }
    
    public void setTextureOffsets(float textureXOffset, float textureYOffset) {
        this.textureXOffset = textureXOffset;
        this.textureYOffset = textureYOffset;
    }
    
    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Minecraft mc, int mouseX, int mouseY) {
        super.draw(mc, mouseX, mouseY);

        GL11.glPushMatrix();

        if (!isEnabled())
            GL11.glColor3f(0.4F, 0.4F, 0.4F);

        double centerX = this.x + this.width / 2.0;
        double centerY = this.y + this.height / 2.0;

        GL11.glTranslated(centerX, centerY, 0);
        GL11.glRotated(rotationAngle, 0, 0, 1); // Apply the rotation
        GL11.glTranslated(-centerX, -centerY, 0);

        // Draw the button (texture) with custom offsets
        GuiUtils.drawImageTransparent(this.x + textureXOffset, this.y + textureYOffset, this.texture, this.textureWidth, this.textureHeight);

        if (isEnabled()) {
            float opacity = animationValue(1.0F);
            if (opacity > 0.0F) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, opacity);
                // Draw the hovered texture with custom offsets
                GuiUtils.drawImageTransparent(this.x + textureXOffset, this.y + textureYOffset, this.hoveredTexture, this.textureWidth, this.textureHeight);
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();

        // Draw the text without rotation
        GuiUtils.drawCenteredStringWithCustomFont(mc, this.text, this.x + this.width / 2.0D,
            this.y + height(50.0F) - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BLACK.getFont(), 130) / 2),
            Color.red, Fonts.MONTSERRAT_BLACK.getFont(), 130);
    }



    public UIButtonWheel setTexture(String texture) {
        this.texture = new ResourceLocation(texture + ".png");
        this.hoveredTexture = new ResourceLocation(texture + "_hover.png");
        return this;
    }

    public UIButtonWheel setHoveredTexture(String hoveredTexture) {
        this.hoveredTexture = new ResourceLocation(hoveredTexture + ".png");
        return this;
    }
}
