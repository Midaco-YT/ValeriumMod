package fr.valerium.valemod.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelRendererFloat extends ModelRenderer {

	public ModelRendererFloat(ModelBase model, int texOffX, int texOffY) {
		super(model, texOffX, texOffY);
	}

	public void addBox(float x, float y, float z, float w, float h, float d) {
		// Appelle la méthode addBox() de la classe parent en convertissant les nombres à virgule flottante en entiers
		super.addBox((int)(x * 1000), (int)(y * 1000), (int)(z * 1000), (int)(w * 1000), (int)(h * 1000), (int)(d * 1000));
	}

}
