package fr.valerium.valemod.crates.itemRender;

import org.lwjgl.opengl.GL11;

import fr.valerium.valemod.crates.renderers.TileEntityOasisCrateSpecialRender;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ItemRenderOasisCrate implements IItemRenderer {

	TileEntityOasisCrateSpecialRender render;
	ResourceLocation objModelLocation;
	IModelCustom model;

	private TileEntity entity;

	public ItemRenderOasisCrate(TileEntityOasisCrateSpecialRender render, TileEntity entity) {
		this.entity = entity;
		this.render = render;
		objModelLocation = new ResourceLocation("valerium:textures/models/crates/oasis/oasis_crate.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
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
	    GL11.glTranslatef(0.5F, 0.0F, 0.5F);
	    //GL11.glScalef(0.8F, 0.8F, 0.8F); // appliquer une mise à l'échelle de 0.5//

	    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("valerium:textures/models/crates/oasis/oasis_crate.png"));

	    GL11.glPushMatrix();
	    GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
	    model.renderAll();
	    GL11.glPopMatrix();

	    GL11.glPopMatrix();
	}

}
