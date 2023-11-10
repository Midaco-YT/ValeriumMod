package fr.valerium.valemod.renderer;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.models.ModelDisenchantingTable;
import fr.valerium.valemod.tiles.TileEntityDisenchantingTable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityDisenchantingTableSpecialRender extends TileEntitySpecialRenderer {

	private final ModelDisenchantingTable model;
	protected static final ResourceLocation texture = new ResourceLocation(
			"valerium:textures/models/disenchanting_table.png");

	public TileEntityDisenchantingTableSpecialRender() {
		this.model = new ModelDisenchantingTable();
	}
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glPushMatrix();
		GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
		GL11.glPopMatrix();
	}
	
	
	public void renderTileEntityAtBlockDisenchantingTable(TileEntityDisenchantingTable tileentity, double x, double y, double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		this.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z,
			float scale) {
		this.renderTileEntityAtBlockDisenchantingTable((TileEntityDisenchantingTable)tileentity, x, y, z, scale);
		
	}
	
}
