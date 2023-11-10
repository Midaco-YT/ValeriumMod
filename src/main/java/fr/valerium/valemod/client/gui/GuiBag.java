package fr.valerium.valemod.client.gui;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.common.gui.ContainerBag;
import fr.valerium.valemod.common.inventory.InventoryBag;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBag extends GuiContainer
{
    public static final ResourceLocation texture = new ResourceLocation("valerium:textures/gui/bag.png");
    protected InventoryBag inv;
    protected InventoryPlayer playerInv;
    public int rows;
 
    public GuiBag(InventoryPlayer playerInv, InventoryBag inv) {
        super(new ContainerBag(playerInv, inv));
        this.playerInv = playerInv;
        this.inv = inv;
        this.allowUserInput = false;
        // Calculate the number of rows
        this.rows = inv.getSizeInventory() / 9;
        // Height of the GUI using the number of rows
        this.ySize = 114 + this.rows * 18;
    }
 
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        this.fontRendererObj.drawString(I18n.format(this.inv.getInventoryName(), new Object[0]), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName(), new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }
 
    @Override
    protected void drawGuiContainerBackgroundLayer(float prt, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
 
        // Centering GUI
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
 
        // Drawing the first part of the GUI (slots of the backpack)
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.rows * 18 + 17);
        // And after the slots from the player's inventory
        this.drawTexturedModalRect(k, l + this.rows * 18 + 17, 0, 126, this.xSize, 96);
    }
}