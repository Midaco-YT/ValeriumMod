package fr.valerium.valemod.shop.node;

import fr.valerium.indium.nodes.abstracts.AClickableNode;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FlatCloseNode extends AClickableNode {
  private static final ResourceLocation TEXTURE = new ResourceLocation("shop", "textures/gui/navbar/close.png");
  
  private static final Color COLOR = new Color(240, 240, 240);
  
  private static final Color HOVERED_COLOR = new Color(239, 57, 38);
  
  public FlatCloseNode(double x, double y, double size) {
    super(x, y, size, size);
    setCallback(n -> Minecraft.getMinecraft().displayGuiScreen(null));
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    Color animatedColor = new Color(COLOR.r + animationValue(HOVERED_COLOR.r - COLOR.r), COLOR.g + animationValue(HOVERED_COLOR.g - COLOR.g), COLOR.b + animationValue(HOVERED_COLOR.b - COLOR.b));
    animatedColor.bind();
    GuiUtils.drawImageTransparent(this.x, this.y, TEXTURE, this.width, this.height);
    Color.WHITE.bind();
  }
}
