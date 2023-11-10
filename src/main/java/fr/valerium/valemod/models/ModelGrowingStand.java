package fr.valerium.valemod.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGrowingStand extends ModelBase
{
  //fields
    ModelRenderer Bar;
    ModelRenderer Body;
    ModelRenderer Head;
    ModelRenderer LeftArm;
    ModelRenderer RightArm;
    ModelRenderer MancheDroite;
    ModelRenderer Manche;
  
  public ModelGrowingStand()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Bar = new ModelRenderer(this, 0, 16);
      Bar.addBox(0F, 0F, 0F, 2, 20, 2);
      Bar.setRotationPoint(-1F, 4F, -1F);
      Bar.setTextureSize(64, 64);
      Bar.mirror = true;
      setRotation(Bar, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 8, 16);
      Body.addBox(0F, 0F, 0F, 8, 12, 4);
      Body.setRotationPoint(-4F, 4F, -2F);
      Body.setTextureSize(64, 64);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(0F, 0F, 0F, 8, 8, 8);
      Head.setRotationPoint(-4F, -4F, -4F);
      Head.setTextureSize(64, 64);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      LeftArm = new ModelRenderer(this, 32, 0);
      LeftArm.addBox(0F, 0F, 0F, 12, 2, 2);
      LeftArm.setRotationPoint(4F, 5F, -1F);
      LeftArm.setTextureSize(64, 64);
      LeftArm.mirror = true;
      setRotation(LeftArm, 0F, 0F, 0F);
      RightArm = new ModelRenderer(this, 32, 0);
      RightArm.addBox(0F, 0F, 0F, 12, 2, 2);
      RightArm.setRotationPoint(-16F, 5F, -1F);
      RightArm.setTextureSize(64, 64);
      RightArm.mirror = true;
      setRotation(RightArm, 0F, 0F, 0F);
      MancheDroite = new ModelRenderer(this, 33, 8);
      MancheDroite.addBox(0F, 0F, 0F, 5, 4, 4);
      MancheDroite.setRotationPoint(4F, 4F, -2F);
      MancheDroite.setTextureSize(64, 64);
      MancheDroite.mirror = true;
      setRotation(MancheDroite, 0F, 0F, 0F);
      Manche = new ModelRenderer(this, 33, 8);
      Manche.addBox(0F, -8F, -2F, 5, 4, 4);
      Manche.setRotationPoint(-4F, 0F, 0F);
      Manche.setTextureSize(64, 64);
      Manche.mirror = true;
      setRotation(Manche, 3.141593F, -3.141593F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Bar.render(f5);
    Body.render(f5);
    Head.render(f5);
    LeftArm.render(f5);
    RightArm.render(f5);
    MancheDroite.render(f5);
    Manche.render(f5);
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
