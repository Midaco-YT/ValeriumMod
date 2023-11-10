package fr.valerium.valemod.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.jobs.gui.OverlayXP;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class OverlayPacketHandler implements IMessageHandler<PacketOverlay, IMessage> {

    @Override
    public IMessage onMessage(PacketOverlay message, MessageContext ctx) {
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        // Traitez le message ici (si n�cessaire)

        // Exemple : Envoyer un message dans le chat du joueur
        OverlayXP overlay = new OverlayXP();
        overlay.setText(message.message);
        overlay.show();

        return null;
    }
    
    public static void sendOverlayMessage(EntityPlayer player, int newXpDisplay, String jobName) {
        String message = String.format("Job: %d XP earned for %s", newXpDisplay, jobName);
        PacketOverlay overlayMessage = new PacketOverlay(message);
        ModVale.network.sendTo(overlayMessage, (EntityPlayerMP) player);
    }
}
