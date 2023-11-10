package fr.valerium.valemod.blocks.luckyblocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.events.ValeriumLuckyBlockEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ValeriumLuckyBlock extends LuckyBlockBase {
    private List<ValeriumLuckyBlockEvent> events;

    public ValeriumLuckyBlock() {
        setCreativeTab(ModVale.tabMiner);
        this.setBlockName("valerium_luckyblock");
        this.setBlockTextureName("valerium:valerium_luckyblock");
        this.setHardness(10);

        events = initializeEvents();
    }

    public static List<ValeriumLuckyBlockEvent> initializeEvents() {
        List<ValeriumLuckyBlockEvent> events = new ArrayList<>();

        // Création des événements du bloc chanceux avec leurs noms et leurs images
        ResourceLocation chestImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/chest.png");
        ValeriumLuckyBlockEvent spawnChestEvent = new ValeriumLuckyBlockEvent("Spawn Chest", chestImage);
        events.add(spawnChestEvent);

        ResourceLocation oreImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/mineral_rain.png");
        ValeriumLuckyBlockEvent spawnOreEvent = new ValeriumLuckyBlockEvent("Spawn Ore", oreImage);
        events.add(spawnOreEvent);

        ResourceLocation repairImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/repair.png");
        ValeriumLuckyBlockEvent repairItemsEvent = new ValeriumLuckyBlockEvent("Repair Items", repairImage);
        events.add(repairItemsEvent);

        // Ajoutez d'autres événements avec leurs noms et leurs images correspondantes
        ResourceLocation tntImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/explosion.png");
        ValeriumLuckyBlockEvent tntExplosionEvent = new ValeriumLuckyBlockEvent("TNT Explosion", tntImage);
        events.add(tntExplosionEvent);

        ResourceLocation zombieImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/zombie_killer.png");
        ValeriumLuckyBlockEvent spawnZombieEvent = new ValeriumLuckyBlockEvent("Spawn Zombie", zombieImage);
        events.add(spawnZombieEvent);

        ResourceLocation potionImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/double_xp.png");
        ValeriumLuckyBlockEvent doubleExpPotionEvent = new ValeriumLuckyBlockEvent("Double Exp Potion", potionImage);
        events.add(doubleExpPotionEvent);

        ResourceLocation teleportImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/money.png");
        ValeriumLuckyBlockEvent teleportEvent = new ValeriumLuckyBlockEvent("Teleport", teleportImage);
        events.add(teleportEvent);

        ResourceLocation slownessImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/slowness.png");
        ValeriumLuckyBlockEvent slownessEffectEvent = new ValeriumLuckyBlockEvent("Slowness Effect", slownessImage);
        events.add(slownessEffectEvent);

        return events;
    }

    public void openLuckyBlock(World world, int x, int y, int z, EntityPlayer player) {
        Random rand = new Random();
        int eventIndex = rand.nextInt(events.size());
        ValeriumLuckyBlockEvent event = events.get(eventIndex);

        // Afficher le résultat à l'écran
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "[Lucky Block Valerium]"));
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "- " + event.getName()));

        // Exécuter l'événement
        event.executeEvent(player);
    }
}
