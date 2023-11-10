package fr.valerium.valemod.common;


import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import fr.valerium.valemod.client.gui.GuiSpellSelection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;


public class KeyHandler {

	private final KeyBinding openSelectedSpellGui = new KeyBinding("key.openGui", Keyboard.KEY_K, "key.categories.spell");
	
	public KeyHandler() {
		ClientRegistry.registerKeyBinding(openSelectedSpellGui);
	}
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if(openSelectedSpellGui.isPressed()) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiSpellSelection());
		}
	}
}
