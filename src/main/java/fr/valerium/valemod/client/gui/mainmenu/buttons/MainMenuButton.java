package fr.valerium.valemod.client.gui.mainmenu.buttons;

import org.lwjgl.opengl.GL11;

import fr.valerium.indium.nodes.abstracts.AClickableNode;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MainMenuButton extends AClickableNode {
	private String text;

	private ResourceLocation texture = null;
	
	private ResourceLocation icon;

	public ResourceLocation getTexture() {
		return this.texture;
	}

	private ResourceLocation hoveredTexture = null;

	public ResourceLocation getHoveredTexture() {
		return this.hoveredTexture;
	}

	public MainMenuButton(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public MainMenuButton(double x, double y, double width, double height, String texture, String text, String icon) {
		super(x, y, width, height);
		this.text = text;
		this.icon = new ResourceLocation(icon + ".png");
		setTexture(texture);
		setHoveredTexture(texture + "_hover");
	}

	public void draw(Minecraft mc, int mouseX, int mouseY) {
		super.draw(mc, mouseX, mouseY);
		GL11.glPushMatrix();
		if (!isEnabled())
			GL11.glColor3f(0.4F, 0.4F, 0.4F);
		GuiUtils.drawImageTransparent(this.x, this.y, this.texture, this.width, this.height);
		GuiUtils.drawCenteredStringWithCustomFont(mc, this.text, this.x + this.width / 2.0D,
				this.y + height(50.0F) - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BLACK.getFont(), 130) / 2),
				Color.white, Fonts.MONTSERRAT_BLACK.getFont(), 130);
		GuiUtils.drawImageTransparent(this.x + this.width + this.ui.width(1.6F) + animationValue(width(5.0F)), this.y + height(0.0F), this.icon, this.ui.width(4.0F), height(100.0F));
		if (isEnabled()) {
			float opacity = animationValue(1.0F);
			if (opacity > 0.0F) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, opacity);
				GuiUtils.drawImageTransparent(this.x, this.y, this.hoveredTexture, this.width, this.height);
				GuiUtils.drawCenteredStringWithCustomFont(mc, this.text, this.x + this.width / 2.0D,
						this.y + height(50.0F) - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BLACK.getFont(), 130) / 2),
						Color.black, Fonts.MONTSERRAT_BLACK.getFont(), 130);
				GuiUtils.drawImageTransparent(this.x + this.width + this.ui.width(1.6F) + animationValue(width(5.0F)), this.y + height(0.0F), this.icon, this.ui.width(4.0F), height(100.0F));
			}
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public MainMenuButton setTexture(String texture) {
		this.texture = new ResourceLocation(texture + ".png");
		this.hoveredTexture = new ResourceLocation(texture + ".png");
		return this;
	}

	public MainMenuButton setHoveredTexture(String hoveredTexture) {
		this.hoveredTexture = new ResourceLocation(hoveredTexture + ".png");
		return this;
	}
}
