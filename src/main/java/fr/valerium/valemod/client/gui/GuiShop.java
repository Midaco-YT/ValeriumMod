package fr.valerium.valemod.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.utils.CustomItemStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiShop extends GuiScreen {

	private List<CustomItemStack> itemsForSale = new ArrayList<>();
	private RenderItem itemRender = new RenderItem();
	private int xSize = 176;
	private int ySize = 166;
	private int guiLeft;
	private int guiTop;
	private static final ResourceLocation GUI_BACKGROUND = new ResourceLocation("valerium:textures/gui/adminshop.png");

	public GuiShop() {
		// Ajouter des articles à vendre
		itemsForSale.add(new CustomItemStack(new ItemStack(Blocks.diamond_block), 1000, 500));
		itemsForSale.add(new CustomItemStack(new ItemStack(Items.gold_ingot), 100, 50));
		itemsForSale.add(new CustomItemStack(new ItemStack(Items.apple), 10, 5));
	}

	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(GUI_BACKGROUND);
		func_146110_a(guiLeft + 172, guiTop + 56, 0, 0, 300, 300, 300, 300);
		for (int i = 0; i < itemsForSale.size(); i++) {
			CustomItemStack stack = itemsForSale.get(i);
			ItemStack itemStack = stack.getItemStack();
			int x = width / 2 - 100 + i % 2 * 120;
			int y = height / 2 - 60 + i / 2 * 60;
			drawItemStack(itemStack, x + 20, y + 20, "" + stack.getSellPrice());
			if (isMouseOverItem(mouseX, mouseY, x + 20, y + 20)) {
				drawHoveringText(Arrays.asList(itemStack.getDisplayName(), "Sell Price: " + stack.getSellPrice(),
						"Buy Price: " + stack.getBuyPrice()), mouseX, mouseY, fontRendererObj);
			}
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private boolean isMouseOverItem(int mouseX, int mouseY, int x, int y) {
		return mouseX >= x && mouseX <= x + 16 && mouseY >= y && mouseY <= y + 16;
	}

	private void drawItemStack(ItemStack stack, int x, int y, String price) {
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		itemRender.zLevel = 200.0F;
		FontRenderer font = null;
		if (stack != null)
			font = stack.getItem().getFontRenderer(stack);
		if (font == null)
			font = fontRendererObj;
		itemRender.renderItemIntoGUI(fontRendererObj, mc.getTextureManager(), stack, x, y);
		itemRender.renderItemOverlayIntoGUI(fontRendererObj, mc.getTextureManager(), stack, x, y);
		this.zLevel = 0.0F;
		itemRender.zLevel = 0.0F;
	}
}