package fr.valerium.valemod.renderer.items;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.Reference;
import fr.valerium.valemod.models.ModelDisenchantingTable;
import fr.valerium.valemod.renderer.TileEntityDisenchantingTableSpecialRender;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderDisenchantinTable implements IItemRenderer {

	TileEntityDisenchantingTableSpecialRender render;

	private TileEntity entity;

	public ItemRenderDisenchantinTable(TileEntityDisenchantingTableSpecialRender render, TileEntity entity) {
		this.entity = entity;
		this.render = render;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 1.4F, 0.5F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/models/disenchanting_table.png"));

		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		ModelDisenchantingTable model = new ModelDisenchantingTable();
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();

		GL11.glPopMatrix();

	}

}
