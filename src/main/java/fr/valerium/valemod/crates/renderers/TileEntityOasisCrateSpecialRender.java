package fr.valerium.valemod.crates.renderers;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.crates.tileentity.TileEntityOasisCrate;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class TileEntityOasisCrateSpecialRender extends TileEntitySpecialRenderer {

	ResourceLocation texture;
	ResourceLocation objModelLocation;
	IModelCustom model;

	public TileEntityOasisCrateSpecialRender() {
		texture = new ResourceLocation("valerium:textures/models/crates/oasis/oasis_crate.png");
		objModelLocation = new ResourceLocation("valerium:textures/models/crates/oasis/oasis_crate.obj");
		model = AdvancedModelLoader.loadModel(objModelLocation);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float timeSinceLastTick) {
		TileEntityOasisCrate te2 = (TileEntityOasisCrate) tileentity;

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.7D, y + 1.5D, z + 0.1D);
		switch(te2.getDirection()) {
		
		case 0:
			GL11.glTranslated(0.1D, 0.0D, -0.5D);
			
		case 1:
			GL11.glTranslated(0.9D, 0.0D, 1.5D);
			
		case 2:
			GL11.glTranslated(-0.4D, 0.0D, -0.2D);
			GL11.glRotatef((0F * te2.getDirection()) + 180F, 0.0F, 1.0F, 0.0F);
			
		case 3:
			GL11.glTranslated(0.3D, -1.0D, -0.1D);
			
		default:
		}
		GL11.glRotatef(0.0F,  0.0F,  0.0F, 1.0F);
		GL11.glRotatef((90F * te2.getDirection()) + 180F, 0.0F, 1.0F, 0.0F);
		this.bindTexture(texture);
		model.renderAll();
		GL11.glPopMatrix();
	}
}
