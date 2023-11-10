package fr.valerium.valemod.jobs.gui;

import java.util.ArrayDeque;
import java.util.Deque;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

public class OverlayXP {

	private String text;
    private Deque<String> messageQueue; // File d'attente des messages
    private boolean isDisplaying; // Indicateur si un message est en cours d'affichage
    private long startTime;
    private static final long DURATION = 3000; // Dur�e en millisecondes

    public OverlayXP() {
        messageQueue = new ArrayDeque<>();
        isDisplaying = false;
    }

    public void addMessage(String message) {
        messageQueue.add(message);

        if (!isDisplaying) {
            displayNextMessage();
        }
    }

    private void displayNextMessage() {
        if (messageQueue.isEmpty()) {
            isDisplaying = false;
            return;
        }

        String nextMessage = messageQueue.poll();
        setText(nextMessage);
        show();
    }

    public void setText(String text) {
        this.text = text;
    }

    public void show() {
        MinecraftForge.EVENT_BUS.register(this);
        startTime = System.currentTimeMillis(); // Enregistrer le temps de d�but
        isDisplaying = true;
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            Minecraft minecraft = Minecraft.getMinecraft();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            int width = minecraft.displayWidth;
            int height = minecraft.displayHeight;
            int x = 580; // D�calage horizontal � partir du coin sup�rieur gauche de l'�cran
            int y = 15; // D�calage vertical � partir du coin sup�rieur gauche de l'�cran
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            long remainingTime = Math.max(0, DURATION - elapsedTime); // Temps restant

            // V�rifier si le temps restant est sup�rieur � z�ro
            if (remainingTime > 0) {
                String formattedText = String.format("%s (%d)", text, remainingTime / 1000); // Formatage du texte avec le temps restant en secondes
                fontRenderer.drawStringWithShadow(formattedText, x - fontRenderer.getStringWidth(formattedText) / 2, y - 10, 0xFFFFFF); // Overlay text
            } else {
                // Le d�lai est �coul�, d�senregistrer cet �v�nement
                MinecraftForge.EVENT_BUS.unregister(this);
                isDisplaying = false;
                displayNextMessage(); // Afficher le prochain message dans la file d'attente
            }
        }
    }
}