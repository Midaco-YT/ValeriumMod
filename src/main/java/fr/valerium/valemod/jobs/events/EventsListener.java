package fr.valerium.valemod.jobs.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import fr.valerium.valemod.items.potions.PotionDoubleXP;
import fr.valerium.valemod.jobs.JobManager;
import fr.valerium.valemod.jobs.Tasks.Tasks;
import fr.valerium.valemod.jobs.Tasks.Tasks.TaskType;
import fr.valerium.valemod.jobs.gui.OverlayXP;
import fr.valerium.valemod.network.packet.OverlayPacketHandler;
import fr.valerium.valemod.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class EventsListener {

	@SubscribeEvent
    public void onBlockBreak(BreakEvent event) {
        World world = event.world;
        Block block = event.block;
        int x = event.x;
        int y = event.y;
        int z = event.z;
        EntityPlayer player = event.getPlayer();
        JobManager jobManager = JobManager.get(player);
        OverlayXP overlay = new OverlayXP();

        Tasks task = null;
        for (Tasks t : Utils.tasks.values()) {
            if (t.block == block) {
                task = t;
                break;
            }
        }

        if (task != null && (task.blockMetadata == -1 || world.getBlockMetadata(x, y, z) == task.blockMetadata)) {
            if (task.taskType == TaskType.MINING && !hasMinedBlock(player, task.id)) {
                int newXp = task.newXp;
                int xp = task.baseXp;
                int newXPDisplay = newXp;
                int XpDisplay = xp;
                
                // V�rifier si le joueur poss�de l'avantage double XP
                boolean hasDoubleXp = player.getEntityData().getBoolean("has_double_xp");

                if (player.isPotionActive(PotionDoubleXP.getInstance()) && player.getActivePotionEffect(PotionDoubleXP.getInstance()).getDuration() <= 0) {
                    hasDoubleXp = false;
                }
                
                // Si le joueur poss�de l'avantage double XP, doubler l'exp�rience gagn�e
                if (hasDoubleXp) {
                    
                	newXPDisplay *= 2;
                	XpDisplay *= 2;
                }

                if (player.experienceLevel >= task.minLevel) {
                    JobManager.get(player).addExperience(task.jobName, newXp);
                    setMinedBlock(player, task.id);

                    OverlayPacketHandler.sendOverlayMessage(player, newXPDisplay, task.jobName);

                } else {
                    JobManager.get(player).addExperience(task.jobName, xp);
                    setMinedBlock(player, task.id);
                    OverlayPacketHandler.sendOverlayMessage(player, XpDisplay, task.jobName);
                }

            }
        }
    }

    private boolean hasMinedBlock(EntityPlayer player, int taskId) {
        // V�rifie si le joueur a d�j� extrait le bloc correspondant � la t�che
        // Impl�mentez votre propre logique de v�rification ici, par exemple, utilisez
        // les NBT pour stocker l'information
        return false;
    }

    private void setMinedBlock(EntityPlayer player, int taskId) {
        // Marque le bloc correspondant � la t�che comme ayant �t� extrait par le joueur
        // Impl�mentez votre propre logique de stockage ici, par exemple, utilisez les
        // NBT pour stocker l'information
    }
    
    @SubscribeEvent
    public void onCraftItem(ItemCraftedEvent event) {
        // R�cup�rer les informations pertinentes de l'�v�nement
        Item craftedItem = event.crafting.getItem();
        EntityPlayer player = event.player;

        // V�rifier si l'item craft� correspond � une t�che de crafting
        Tasks task = getCraftingTask(craftedItem);
        if (task != null) {
            // V�rifier si le joueur remplit les conditions de niveau de m�tier
            if (player.experienceLevel >= task.minLevel) {
                // Ajouter l'exp�rience au m�tier
                JobManager.get(player).addExperience(task.jobName, task.newXp);

                // Afficher un message au joueur
                String message = String.format("Vous avez gagn� %d XP pour le m�tier de %s.", task.newXp, task.jobName);
                player.addChatMessage(new ChatComponentText(message));
            } else {
                // Ajouter l'exp�rience de base au m�tier
                JobManager.get(player).addExperience(task.jobName, task.baseXp);

                // Afficher un message au joueur
                String message = String.format("Vous avez gagn� %d XP pour le m�tier de %s.", task.baseXp, task.jobName);
                player.addChatMessage(new ChatComponentText(message));
            }
        }
    }

    private Tasks getCraftingTask(Item item) {
        // Parcourir les t�ches pour trouver celle associ�e � l'item craft�
        for (Tasks task : Utils.tasks.values()) {
            if (task.taskType == TaskType.CRAFTING && task.block == null && task.item == item) {
                return task;
            }
        }
        return null; // Aucune t�che de crafting associ�e � cet item
    }
   
}
