package fr.valerium.valemod.shop.node;

import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FlatIconCallToActionNode extends FlatCallToActionNode {
  private ResourceLocation icon;
  
  private double iconFactor = 0.33D;
  
  public FlatIconCallToActionNode(double x, double y, double size, ResourceLocation icon) {
    super(x, y, size, size);
    this.icon = icon;
  }
  
  public void drawContent(Minecraft mc, int mouseX, int mouseY) {
    Color.WHITE.bind();
    if (isEnabled())
      (new Color(1.0F - animationValue(1.0F - FILL_COLOR.r), 1.0F - animationValue(1.0F - FILL_COLOR.g), 1.0F - animationValue(1.0F - FILL_COLOR.b))).bind(); 
    double iconWidth = this.width * this.iconFactor;
    double iconHeight = this.height * this.iconFactor;
    GuiUtils.drawImageTransparent(this.x + width(50.0F) - iconWidth / 2.0D, this.y + height(50.0F) - iconHeight / 2.0D, this.icon, iconWidth, iconHeight);
    Color.WHITE.bind();
  }
  
  public FlatIconCallToActionNode setIconFactor(double iconFactor) {
    this.iconFactor = iconFactor;
    return this;
  }
}
