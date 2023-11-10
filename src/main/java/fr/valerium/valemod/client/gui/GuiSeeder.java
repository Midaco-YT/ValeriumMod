package fr.valerium.valemod.client.gui;


import fr.valerium.valemod.common.gui.ContainerSeeder;
import fr.valerium.valemod.tiles.TileEntitySeeder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSeeder extends GuiContainer {
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
	
	public GuiSeeder(EntityPlayer player, TileEntitySeeder tileEntity) {
        super(new ContainerSeeder(player.inventory, tileEntity));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(GUI_TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
