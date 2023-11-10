package fr.valerium.valemod.common;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiCustomButton extends Gui {
/*  17 */   protected static final ResourceLocation buttonTextures = new ResourceLocation("valerium:textures/gui/widgets.png");
/*     */   
/*     */   public int width;
/*     */   
/*     */   public int height;
/*     */   
/*     */   public int xPosition;
/*     */   
/*     */   public int yPosition;
/*     */   
/*     */   public String displayString;
/*     */   
/*     */   public int id;
/*     */   
/*     */   public boolean enabled;
/*     */   public boolean visible;
/*     */   protected boolean field_146123_n;
/*     */   private static final String __OBFID = "CL_00000668";
/*     */   public int packedFGColour;
/*     */   
/*     */   public GuiCustomButton(int p_i1020_1_, int p_i1020_2_, int p_i1020_3_, String p_i1020_4_) {
/*  38 */     this(p_i1020_1_, p_i1020_2_, p_i1020_3_, 200, 20, p_i1020_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiCustomButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
/*  43 */     this.width = 200;
/*  44 */     this.height = 20;
/*  45 */     this.enabled = true;
/*  46 */     this.visible = true;
/*  47 */     this.id = p_i1021_1_;
/*  48 */     this.xPosition = p_i1021_2_;
/*  49 */     this.yPosition = p_i1021_3_;
/*  50 */     this.width = p_i1021_4_;
/*  51 */     this.height = p_i1021_5_;
/*  52 */     this.displayString = p_i1021_6_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHoverState(boolean p_146114_1_) {
/*  60 */     byte b0 = 1;
/*     */     
/*  62 */     if (!this.enabled) {
/*  63 */       b0 = 0;
/*  64 */     } else if (p_146114_1_) {
/*  65 */       b0 = 2;
/*     */     } 
/*     */     
/*  68 */     return b0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
/*  75 */     if (this.visible) {
/*  76 */       FontRenderer fontrenderer = p_146112_1_.fontRenderer;
/*  77 */       p_146112_1_.getTextureManager().bindTexture(buttonTextures);
/*  78 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  79 */       this.field_146123_n = (p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height);
/*     */       
/*  81 */       int k = getHoverState(this.field_146123_n);
/*  82 */       GL11.glEnable(3042);
/*  83 */       OpenGlHelper.glBlendFunc(770, 771, 1, 0);
/*  84 */       GL11.glBlendFunc(770, 771);
/*  85 */       drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
/*  86 */       drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
/*     */       
/*  88 */       mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
/*  89 */       int l = 14737632;
/*     */       
/*  91 */       if (this.packedFGColour != 0) {
/*  92 */         l = this.packedFGColour;
/*  93 */       } else if (!this.enabled) {
/*  94 */         l = 10526880;
/*  95 */       } else if (this.field_146123_n) {
/*  96 */         l = 16777120;
/*     */       } 
/*     */       
/*  99 */       drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int p_146118_1_, int p_146118_2_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
/* 123 */     return (this.enabled && this.visible && p_146116_2_ >= this.xPosition && p_146116_3_ >= this.yPosition && p_146116_2_ < this.xPosition + this.width && p_146116_3_ < this.yPosition + this.height);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_146115_a() {
/* 128 */     return this.field_146123_n;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146111_b(int p_146111_1_, int p_146111_2_) {}
/*     */   
/*     */   public void func_146113_a(SoundHandler p_146113_1_) {
/* 135 */     p_146113_1_.playSound((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
/*     */   }
/*     */   
/*     */   public int getButtonWidth() {
/* 139 */     return this.width;
/*     */   }
/*     */   
/*     */   public int func_154310_c() {
/* 143 */     return this.height;
/*     */   }
/*     */ }


/* Location:              C:\Users\Dell\AppData\Roaming\.minecraft\mods\valerium-1.0.0-deobf.jar!\com\mod\valerium\gui\GuiButtonCustom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */