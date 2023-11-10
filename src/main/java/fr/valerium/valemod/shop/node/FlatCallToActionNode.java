package fr.valerium.valemod.shop.node;

import fr.valerium.indium.nodes.abstracts.AClickableNode;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public abstract class FlatCallToActionNode extends AClickableNode {
  public static final Color FILL_COLOR = Color.decode("#EF3926");
  
  public static final Color FILL_COLOR_DISABLE = Color.decode("#1C1C1C");
  
  public FlatCallToActionNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    Color animatedFillColor = new Color(FILL_COLOR.r + animationValue(1.0F - FILL_COLOR.r), FILL_COLOR.g + animationValue(1.0F - FILL_COLOR.g), FILL_COLOR.b + animationValue(1.0F - FILL_COLOR.b));
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.enabled ? animatedFillColor : FILL_COLOR_DISABLE);
    drawContent(mc, mouseX, mouseY);
  }
  
  public abstract void drawContent(Minecraft paramMinecraft, int paramInt1, int paramInt2);
}
