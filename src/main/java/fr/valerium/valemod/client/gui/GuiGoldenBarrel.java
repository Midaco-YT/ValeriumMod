package fr.valerium.valemod.client.gui;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.client.gui.containers.ContainerGoldBarrel;
import fr.valerium.valemod.tiles.TileEntityGoldBarrel;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiGoldenBarrel extends GuiContainer {

    private static final ResourceLocation background = new ResourceLocation("valerium", "textures/gui/container/gold.png");
    private TileEntityGoldBarrel tile;

    public GuiGoldenBarrel(TileEntityGoldBarrel tile, InventoryPlayer inventory) {
        super((Container)new ContainerGoldBarrel(tile, inventory));
        this.tile = tile;
        this.ySize = 256;
        this.xSize = 256;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.5F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x + 36, y, 0, 0, xSize, ySize);
    }
}