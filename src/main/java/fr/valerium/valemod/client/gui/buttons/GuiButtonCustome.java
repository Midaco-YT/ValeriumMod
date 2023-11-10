package fr.valerium.valemod.client.gui.buttons;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiButtonCustome extends GuiButton {
   private boolean antiAlias;
   protected boolean red;
   protected static final ResourceLocation buttonTextures = new ResourceLocation("palamod:textures/gui/btn.png");

   public GuiButtonCustome(int i, int j, int k, String s) {
      this(i, j, k, 120, 20, s);
   }

   public GuiButtonCustome(int i, int j, int k, int l, int i1, String s) {
      super(i, j, k, l, i1, s);
      this.antiAlias = true;
      this.red = false;
      if (s.equals("PALADIUM")) {
         this.red = true;
      }

   }

   public GuiButtonCustome(int i, int j, int k, int l, int i1, String s, boolean red) {
      super(i, j, k, l, i1, s);
      this.antiAlias = true;
      this.red = false;
      this.red = red;
   }

   public int func_146114_a(boolean flag) {
      byte byte0 = 1;
      if (!this.enabled) {
         byte0 = 0;
      } else if (flag) {
         byte0 = 2;
      }

      return byte0;
   }

   public void func_146112_a(Minecraft mc, int mx, int my) {
      if (this.visible) {
         FontRenderer fontrenderer = mc.fontRenderer;
         fontrenderer.setUnicodeFlag(false);
         fontrenderer.FONT_HEIGHT = 60;
         mc.getTextureManager().bindTexture(buttonTextures);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_146123_n = mx >= this.xPosition && my >= this.yPosition && mx < this.xPosition + this.width && my < this.yPosition + this.height;
         int k = this.func_146114_a(this.field_146123_n);
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glBlendFunc(770, 771);
         this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
         this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
         this.mouseDragged(mc, mx, my);
         int l = 14737632;
         if (this.red) {
            l = 14486056;
         }

         if (!this.enabled) {
            l = 10526880;
         } else if (this.field_146123_n) {
            l = 16777120;
         }

         this.drawCenteredString(fontrenderer, EnumChatFormatting.BOLD.toString() + this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
      }

   }
}
