package fr.valerium.valemod.client.gui;

import java.util.List;
import java.util.Random;

import fr.valerium.valemod.blocks.luckyblocks.ValeriumLuckyBlock;
import fr.valerium.valemod.events.ValeriumLuckyBlockEvent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiLuckyBlock extends GuiScreen {
    private final int guiWidth = 176;
    private final int guiHeight = 166;

    private List<ValeriumLuckyBlockEvent> events;
    private List<ResourceLocation> eventImages;
    private Random random;

    private int[] eventIndices;
    private int[] columnPositions;
    private int[] columnSpeeds;
    private int[] columnStops;
    private boolean isSpinning;

    private GuiButton spinButton;

    public GuiLuckyBlock(List<ResourceLocation> images) {
        events = ValeriumLuckyBlock.initializeEvents();
        eventImages = images;
        random = new Random();

        eventIndices = new int[3];
        columnPositions = new int[3];
        columnSpeeds = new int[3];
        columnStops = new int[3];

        isSpinning = false;
    }

    @Override
    public void initGui() {
        super.initGui();
        int centerX = (width - guiWidth) / 2;
        int centerY = (height - guiHeight) / 2;

        spinButton = new GuiButton(0, centerX + 63, centerY + 77, 50, 20, "Spin");
        buttonList.add(spinButton);

        resetColumns();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);

        if (button.id == spinButton.id && !isSpinning) {
            startSpinning();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int centerX = (width - guiWidth) / 2;
        int centerY = (height - guiHeight) / 2;

        drawGuiBackground(centerX, centerY);
        drawColumnImages(centerX, centerY);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawGuiBackground(int x, int y) {
        mc.getTextureManager().bindTexture(new ResourceLocation("valerium:textures/gui/luckyblock/LuckyBlock.png"));
        drawTexturedModalRect(x, y, 0, 0, guiWidth, guiHeight);
    }

    private void drawColumnImages(int x, int y) {
        for (int i = 0; i < 3; i++) {
            int posX = x + 27 + i * 48;
            int posY = y + 36;

            ResourceLocation image = eventImages.get(eventIndices[i]);
            mc.getTextureManager().bindTexture(image);
            drawTexturedModalRect(posX, posY, 0, 0, 18, 18);
        }
    }

    private void resetColumns() {
        for (int i = 0; i < 3; i++) {
            columnPositions[i] = random.nextInt(events.size());
            columnSpeeds[i] = 0;
            columnStops[i] = random.nextInt(40) + 80;
        }
    }

    private void startSpinning() {
        isSpinning = true;
        spinButton.enabled = false;

        resetColumns();
    }

    private void stopSpinning() {
        isSpinning = false;
        spinButton.enabled = true;

        checkResults();
    }

    private void checkResults() {
        int[] eventCounts = new int[events.size()];

        for (int i = 0; i < 3; i++) {
            eventCounts[columnPositions[i]]++;
        }

        for (int count : eventCounts) {
            if (count == 3) {
                executeEvent(columnPositions[0]);
                return;
            }
        }
    }

    private void executeEvent(int eventIndex) {
        ValeriumLuckyBlockEvent event = events.get(eventIndex);
        event.executeEvent(mc.thePlayer);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        if (isSpinning) {
            for (int i = 0; i < 3; i++) {
                columnSpeeds[i]++;

                if (columnSpeeds[i] >= columnStops[i]) {
                    columnSpeeds[i] = columnStops[i];
                }

                if (columnSpeeds[i] == columnStops[i]) {
                    if (columnPositions[i] == eventIndices[i]) {
                        columnSpeeds[i] = 0;
                    } else {
                        columnPositions[i] = (columnPositions[i] + 1) % events.size();
                    }
                }
            }

            if (columnSpeeds[0] == 0 && columnSpeeds[1] == 0 && columnSpeeds[2] == 0) {
                stopSpinning();
            }
        }
    }
}
