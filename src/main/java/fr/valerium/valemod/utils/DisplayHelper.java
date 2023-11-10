package fr.valerium.valemod.utils;

import net.minecraft.client.renderer.Tessellator;

public class DisplayHelper {

	public static void drawTexturedQuadFit(double x, double y, double width, double height, double minU, double minV,
			double maxU, double maxV, int imageWidth, int imageHeight) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, 0, minU / imageWidth, maxV / imageHeight);
		tessellator.addVertexWithUV(x + width, y + height, 0, maxU / imageWidth, maxV / imageHeight);
		tessellator.addVertexWithUV(x + width, y + 0, 0, maxU / imageWidth, minV / imageHeight);
		tessellator.addVertexWithUV(x + 0, y + 0, 0, minU / imageWidth, minV / imageHeight);
		tessellator.draw();
	}
}
