package fr.valerium.valemod.commands;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import fr.valerium.valemod.spells.Spell;
import fr.valerium.valemod.spells.SpellRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;

public class SpellInfoCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "spellinfo";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "spellinfo <spell_id>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("spellinfo");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.addChatMessage(new ChatComponentText("Utilisation : /spellinfo <spell_id>"));
            return;
        }

        int spellId = Integer.parseInt(args[0]);
        SpellRegistry spellRegistryId = new SpellRegistry();
        Spell spell = spellRegistryId.getSpellById(spellId);

        if (spell != null) {
            sender.addChatMessage(new ChatComponentText("Informations sur le sort :"));
            sender.addChatMessage(new ChatComponentText("Nom : " + spell.getSpellName()));
            sender.addChatMessage(new ChatComponentText("Description : " + spell.getDescription()));
            sender.addChatMessage(new ChatComponentText("Niveau maximum : " + spell.getMaxLevel()));
            sender.addChatMessage(new ChatComponentText("Temps de recharge : " + spell.getCooldown()));
            sender.addChatMessage(new ChatComponentText("Icône : " + spell.getSpellIcon()));
        } else {
            sender.addChatMessage(new ChatComponentText("Sort introuvable avec l'ID " + spellId));
            System.out.println("Sort introuvable avec l'ID " + spellId); // Ajoutez cette ligne pour journaliser l'ID du sort
        }

        // Vérifier si le fichier JSON existe
        String configFile = "spells/spell_" + spellId + ".json";
        ResourceLocation resourceLocation = new ResourceLocation("valerium", configFile);
        InputStream inputStream = null;

        try {
            inputStream = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();

            if (inputStream != null) {
                System.out.println("Fichier JSON trouvé : " + resourceLocation.toString());
                // Chargez le fichier JSON à partir de l'InputStream ici (utilisez Gson ou une autre bibliothèque).
            } else {
                System.out.println("Fichier JSON introuvable : " + resourceLocation.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        // Vous pouvez implémenter des vérifications d'autorisation ici, si nécessaire
        return sender instanceof EntityPlayer;
    }
}
