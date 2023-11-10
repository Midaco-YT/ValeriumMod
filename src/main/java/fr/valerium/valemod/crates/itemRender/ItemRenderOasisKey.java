package fr.valerium.valemod.crates.itemRender;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ItemRenderOasisKey implements IItemRenderer {

	ResourceLocation objModelLocation;
	IModelCustom model;
	ResourceLocation texture;

	public ItemRenderOasisKey() {
		objModelLocation = new ResourceLocation("valerium:textures/models/keys/oasis/oasis_key.obj");
		model = AdvancedModelLoader.loadModel(objModelLocation);
		texture = new ResourceLocation("valerium:textures/models/keys/oasis/oasis_key.png");
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
        {
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            case ENTITY:
                return true;
            default:
                return false;
        }
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch (type) {
		case EQUIPPED: {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.5F, -0.5F, 0.0F);
			GL11.glRotatef(-180.0F, 1.0F, 0.0F, 1.0F);
			GL11.glScalef(2.0F, 2.0F, 2.0F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			model.renderAll();
			GL11.glPopMatrix();
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.5F, 0.5F, 1.0F);
			GL11.glRotatef(180.0F, 1.0F, -1.0F, 0.0F);
			GL11.glScalef(2.0F, 2.0F, 2.0F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			model.renderAll();
			GL11.glPopMatrix();
			break;
		}
		case ENTITY: {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, (float) Math.sin((double) (System.currentTimeMillis() / 500.0)) * 0.1F, 0.0F);
			GL11.glRotatef(180.0F, 1.0F, -1.0F, 0.0F);
			GL11.glRotatef((float) (System.currentTimeMillis() / 15 % 360), -1.0F, 0.0F, 0.0F);
			GL11.glScalef(2.0F, 2.0F, 2.0F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			model.renderAll();
			GL11.glPopMatrix();
			break;
		}
		default:
			break;
		}

	}

}
