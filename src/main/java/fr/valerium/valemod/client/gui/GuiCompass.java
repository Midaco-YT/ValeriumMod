package fr.valerium.valemod.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GuiCompass {

	@SubscribeEvent
	  public void renderOverlay(RenderGameOverlayEvent.Post event) {
	    // Vérifiez si le type d'événement est "éléments en surbrillance"
	    if (event.type != ElementType.ALL) {
	      return;
	    }

	    // Obtenez l'instance du jeu
	    Minecraft mc = Minecraft.getMinecraft();

	    // Obtenez la position du joueur et le nord par rapport à lui
	    EntityPlayer player = mc.thePlayer;
	    double angle = Math.toRadians(player.rotationYaw);
	    double cos = Math.cos(angle);
	    double sin = Math.sin(angle);

	    // Définissez les coordonnées du compas (en haut à gauche de l'écran)
	    int x = 5;
	    int y = 5;

	    // Définissez la taille du compas
	    int size = 32;

	    // Définissez l'épaisseur de la bordure du compas
	    int border = 2;

	    // Définissez les couleurs du compas et de la bordure
	    int colorCompass = 0xFFFFFF;
	    int colorBorder = 0x000000;

	    // Début du dessin en mode "blend"
	    GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
	    GL11.glEnable(GL11.GL_BLEND);
	    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

	    // Dessinez la bordure du compas
	    drawRect(x - border, y - border, x + size + border, y + size + border, colorBorder);

	    // Dessinez le compas
	    drawRect(x, y, x + size, y + size, colorCompass);

	    // Dessinez la flèche du compas en utilisant les coordonnées polaires
	    drawArrow(x + size / 2, y + size / 2, cos, sin, size / 2 - border, colorCompass);
	    
	    GL11.glPopAttrib();
	}

	private void drawRect(int x1, int y1, int x2, int y2, int color) {
		  // Convertit la couleur en composantes RVB
		  float red = (color >> 16 & 255) / 255.0F;
		  float green = (color >> 8 & 255) / 255.0F;
		  float blue = (color & 255) / 255.0F;

		  // Définit la couleur de dessin
		  GL11.glColor4f(red, green, blue, 1.0F);

		  // Dessine le rectangle
		  GL11.glBegin(GL11.GL_QUADS);
		  GL11.glVertex2i(x1, y1);
		  GL11.glVertex2i(x2, y1);
		  GL11.glVertex2i(x2, y2);
		  GL11.glVertex2i(x1, y2);
		  GL11.glEnd();
		}
	
	private void drawArrow(int x, int y, double cos, double sin, int length, int color) {
		  // Convertit la couleur en composantes RVB
		  float red = (color >> 16 & 255) / 255.0F;
		  float green = (color >> 8 & 255) / 255.0F;
		  float blue = (color & 255) / 255.0F;

		  // Définit la couleur de dessin
		  GL11.glColor4f(red, green, blue, 1.0F);

		  // Dessine la tête de la flèche
		  GL11.glBegin(GL11.GL_TRIANGLES);
		  GL11.glVertex2d(x + cos * length, y + sin * length);
		  GL11.glVertex2d(x + cos * length - sin * 4, y + sin * length + cos * 4);
		  GL11.glVertex2d(x + cos * length + sin * 4, y + sin * length - cos * 4);
		  GL11.glEnd();

		  // Dessine le corps de la flèche
		  GL11.glBegin(GL11.GL_LINES);
		  GL11.glVertex2i(x, y);
		  GL11.glVertex2d(x + cos * length, y + sin * length);
		  GL11.glEnd();
		}
}
