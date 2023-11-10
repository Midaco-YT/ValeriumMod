package fr.valerium.valemod.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDisenchantingTable extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Border;
    ModelRenderer Border1;
    ModelRenderer Border2;
    ModelRenderer Border3;
  
    
    
  public ModelDisenchantingTable()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 16, 12, 16);
      Base.setRotationPoint(-8F, 12F, -8F);
      Base.setTextureSize(64, 64);
      Base.mirror = false;
      setRotation(Base, 0F, 0F, 0F);
      Border = new ModelRenderer(this, 0, 29);
      Border.addBox(0F, 0F, 0F, 5, 5, 5);
      Border.setRotationPoint(3.1F, 11.1F, 8.1F);
      Border.setTextureSize(64, 64);
      Border.mirror = false;
      setRotation(Border, 0F, 1.570796F, 0F);
      Border1 = new ModelRenderer(this, 0, 29);
      Border1.addBox(0F, 0F, 0F, 5, 5, 5);
      Border1.setRotationPoint(8.1F, 11.1F, -3.1F);
      Border1.setTextureSize(64, 64);
      Border1.mirror = false;
      setRotation(Border1, 0F, -3.141593F, 0F);
      Border2 = new ModelRenderer(this, 0, 29);
      Border2.addBox(0F, 0F, 0F, 5, 5, 5);
      Border2.setRotationPoint(-8.1F, 11.1F, 3.1F);
      Border2.setTextureSize(64, 64);
      Border2.mirror = false;
      setRotation(Border2, 0F, 0F, 0F);
      Border3 = new ModelRenderer(this, 0, 29);
      Border3.addBox(0F, 0F, 0F, 5, 5, 5);
      Border3.setRotationPoint(-3.1F, 11.1F, -8.1F);
      Border3.setTextureSize(64, 64);
      Border3.mirror = false;
      setRotation(Border3, 0F, -1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Border.render(f5);
    Border1.render(f5);
    Border2.render(f5);
    Border3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
