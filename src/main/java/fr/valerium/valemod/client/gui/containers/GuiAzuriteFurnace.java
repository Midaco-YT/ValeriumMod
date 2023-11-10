package fr.valerium.valemod.client.gui.containers;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.common.gui.ContainerAzuriteFurnace;
import fr.valerium.valemod.tiles.TileEntityAzuriteFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAzuriteFurnace extends GuiContainer {
  ResourceLocation background = new ResourceLocation("valerium", "textures/gui/azurite_furnace.png");
  
  TileEntityAzuriteFurnace tile;
  
  FontRenderer fr;
  
  public GuiAzuriteFurnace(TileEntityAzuriteFurnace tile, InventoryPlayer inventory) {
    super(new ContainerAzuriteFurnace(tile, inventory));
    this.tile = tile;
    this.ySize = 166;
    this.xSize = 176;
    this.fr = (Minecraft.getMinecraft()).fontRenderer;
  }
  
  protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
    if (this.tile == null)
      return; 
    GL11.glColor4f(1.0F, 1.5F, 1.0F, 1.0F);
    this.mc.getTextureManager().bindTexture(this.background);
    int k = (this.width - this.xSize) / 2;
    int l = (this.height - this.ySize) / 2;
    drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    int i1 = this.tile.getBurnTimeRemainingScaled(13) - 1;
    drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
    if (this.tile.isBurning()) {
      i1 = this.tile.getCookProgressScaled(24);
      drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    } 
  }
}