package fr.valerium.valemod.client.gui.mainmenu.buttons;

import org.lwjgl.opengl.GL11;

import fr.valerium.indium.nodes.abstracts.AClickableNode;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class InGameDecoButton extends AClickableNode {
	private String text;
	private Runnable onClickCallback; // Le callback à exécuter lors du clic

	private ResourceLocation texture = null;

	public ResourceLocation getTexture() {
		return this.texture;
	}

	private ResourceLocation hoveredTexture = null;

	public ResourceLocation getHoveredTexture() {
		return this.hoveredTexture;
	}

	public InGameDecoButton(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	// Constructeur mis à jour pour prendre en charge le texte et le callback
	public InGameDecoButton(double x, double y, double width, double height, String texture, String text, Runnable onClickCallback) {
	    super(x, y, width, height);
	    this.text = text;
	    setTexture(texture);
	    setHoveredTexture(texture + "_hover");
	    this.onClickCallback = onClickCallback;
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
		if (isEnabled()) {
			float opacity = animationValue(1.0F);
			if (opacity > 0.0F) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, opacity);
				GuiUtils.drawImageTransparent(this.x, this.y, this.hoveredTexture, this.width, this.height);
				GuiUtils.drawCenteredStringWithCustomFont(mc, this.text, this.x + this.width / 2.0D,
						this.y + height(50.0F) - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BLACK.getFont(), 130) / 2),
						Color.black, Fonts.MONTSERRAT_BLACK.getFont(), 130);
			}
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public InGameDecoButton setTexture(String texture) {
		this.texture = new ResourceLocation(texture + ".png");
		this.hoveredTexture = new ResourceLocation(texture + "_hover.png");
		return this;
	}
	
	public InGameDecoButton setHoveredTexture(String hoveredTexture) {
		this.hoveredTexture = new ResourceLocation(hoveredTexture + ".png");
		return this;
	}

	// Méthode pour exécuter le callback lors du clic
	public void onClick() {
		if (onClickCallback != null) {
			onClickCallback.run();
		}
	}
}
