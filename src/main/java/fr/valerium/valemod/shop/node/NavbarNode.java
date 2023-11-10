package fr.valerium.valemod.shop.node;

import java.util.Arrays;

import fr.valerium.indium.nodes.abstracts.AClickableNode;
import fr.valerium.indium.ui.AbstractUI;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.Fonts;
import fr.valerium.indium.utils.GuiUtils;
import fr.valerium.indium.utils.HoverObject;
import fr.valerium.translate.common.texttotranslate.TTT;
import fr.valerium.valemod.shop.utils.ShopPage;
import net.minecraft.client.Minecraft;

public class NavbarNode extends AClickableNode {
	  public static final Color COLOR = new Color(240, 240, 240);
	  
	  public static final Color SELECTED_COLOR = new Color(139, 0, 139);
	  
	  private ShopPage page;
	  
	  private boolean selected;
	  
	  public NavbarNode(ShopPage page, double x, double y, double height, boolean selected) {
	    super(x, y, 0.0D, height);
	    this.page = page;
	    this.selected = selected;
	  }
	  
	  public void draw(Minecraft mc, int mouseX, int mouseY) {
	    super.draw(mc, mouseX, mouseY);
	    Color animatedColor = new Color(COLOR.r + animationValue(SELECTED_COLOR.r - COLOR.r), COLOR.g + animationValue(SELECTED_COLOR.g - COLOR.g), COLOR.b + animationValue(SELECTED_COLOR.b - COLOR.b));
	    if (this.page.getIcon() != null) {
	      if (!this.selected) {
	        animatedColor.bind();
	      } else {
	        SELECTED_COLOR.bind();
	      } 
	      GuiUtils.drawImageTransparent(this.x, this.y + this.height / 2.0D - this.width / 2.0D, this.page.getIcon(), this.width, this.width, true);
	      Color.WHITE.bind();
	      if (isHovered())
	        GuiUtils.hovers.add(new HoverObject((int)(this.x - width(10.0F) - (GuiUtils.getStringWidth(mc, TTT.format(this.page.getName(), new Object[0]).toUpperCase(), Fonts.MONTSERRAT_BOLD.getFont(), 20) / 2)), (int)(this.y + height(90.0F)), Arrays.asList(new String[] { TTT.format(this.page.getName(), new Object[0]).toUpperCase() }), Fonts.MONTSERRAT_BOLD.getFont(), SELECTED_COLOR)); 
	    } else {
	      GuiUtils.drawStringWithCustomFont(mc, TTT.format(this.page.getName(), new Object[0]).toUpperCase(), this.x, this.y + this.height / 2.0D - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BOLD.getFont(), 10) / 2), this.selected ? SELECTED_COLOR : animatedColor, Fonts.MONTSERRAT_BOLD.getFont(), 10);
	    } 
	  }
	  
	  public void setUi(AbstractUI ui) {
	    super.setUi(ui);
	    if (this.page.getIcon() != null) {
	      setWidth(ui.width(1.927F));
	    } else {
	      setWidth(GuiUtils.getStringWidth(ui.mc, TTT.format(this.page.getName(), new Object[0]).toUpperCase(), Fonts.MONTSERRAT_BOLD.getFont(), 10));
	    } 
	  }
	  
	  public boolean isHovered(int mouseX, int mouseY) {
	    return super.isHovered(mouseX, mouseY);
	  }
	}
