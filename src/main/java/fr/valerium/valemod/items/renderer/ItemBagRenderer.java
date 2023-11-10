package fr.valerium.valemod.items.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ItemBagRenderer implements IItemRenderer {

	private final IModelCustom model;
    private final ResourceLocation texture;
    
    public ItemBagRenderer() {
        model = AdvancedModelLoader.loadModel(new ResourceLocation("valerium:textures/models/bag/bag.obj"));
        texture = new ResourceLocation("valerium:textures/models/bag/bag.png");
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

        switch (type) {
        case EQUIPPED:
            // Rotation et translation lorsque l'item est équipé.
            GL11.glTranslatef(0.0F, -1.0F, 0.0F); // Descendre le modèle et le reculer
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F); // Rotation vers la gauche
            GL11.glScalef(3.0F, 3.0F, 3.0F); // Rétablir la taille par défaut
            break;
        case EQUIPPED_FIRST_PERSON:
            // Rotation et translation lorsque l'item est équipé à la première personne.
            GL11.glTranslatef(0.0F, -1.0F, 0.5F); // Descendre le modèle et le reculer
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F); // Rotation vers la gauche
            GL11.glScalef(3.0F, 3.0F, 3.0F); // Rétablir la taille par défaut
            break;
            case INVENTORY:
                // Rotation et translation dans l'inventaire.
                GL11.glTranslatef(0.0F, -0.5F, 0.0F); // Ajustez la translation si nécessaire
                GL11.glScalef(2.0F, 2.0F, 2.0F); // Rétablir la taille par défaut
                break;
        }

        // Appliquez la texture.
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        // Affichez le modèle.
        model.renderAll();

        GL11.glPopMatrix();
    }

}
