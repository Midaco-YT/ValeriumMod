package fr.valerium.valemod.shop.node;

import fr.valerium.indium.nodes.abstracts.AClickableNode;
import fr.valerium.indium.utils.Color;
import fr.valerium.indium.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class ItemSellNode extends AClickableNode {

    private static final Color COLOR = new Color(17, 16, 17);
    private static final Color HOVERED_COLOR = new Color(239, 57, 38);
    public static final Color GRAY = new Color(112, 112, 112);

    private ItemStack itemStackToDisplay;

    public ItemSellNode(double x, double y, double size, ItemStack itemStackToDisplay) {
        super(x, y, size, size);
        this.itemStackToDisplay = itemStackToDisplay;
    }

    @Override
    public void draw(Minecraft mc, int mouseX, int mouseY) {
        super.draw(mc, mouseX, mouseY);

        float borderRadius = 10.0F;
        Color animatedColor = new Color(COLOR.r + animationValue(HOVERED_COLOR.r - COLOR.r),
                                        COLOR.g + animationValue(HOVERED_COLOR.g - COLOR.g),
                                        COLOR.b + animationValue(HOVERED_COLOR.b - COLOR.b));

        animatedColor.bind();
        GuiUtils.drawRoundedRect(this.x, this.y, COLOR, this.width, this.height, borderRadius);
        GuiUtils.drawRoundedRect(this.x + 5, this.y + 5, GRAY, this.width - 10, this.height - 10, borderRadius);
        Color.WHITE.bind();

        // Afficher l'ItemStack dans le bouton
        double posX = this.x + 5; // Ajustez la position si nécessaire
        double posY = this.y + 5; // Ajustez la position si nécessaire
        GuiUtils.renderItemStackIntoGUI(this.itemStackToDisplay, posX, posY);
    }
}
