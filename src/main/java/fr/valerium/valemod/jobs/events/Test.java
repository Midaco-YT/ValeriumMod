package fr.valerium.valemod.jobs.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.notifications.Notifications;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class Test {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onDisplayNotification(RenderGameOverlayEvent.Pre event) {

		Notifications notif = new Notifications();
		notif.displayAchievement("Test", "desc");
		notif.updateAchievementWindow();
	}
}
