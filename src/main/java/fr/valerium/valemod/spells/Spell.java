package fr.valerium.valemod.spells;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public abstract class Spell {

    private String spellName;
    private final int spellId;
    private int spellLevel;
    private String description;
    private int maxLevel;
    private int cooldown;
    private String spellIcon;

    public Spell(int spellId, ResourceLocation configLocation) {
        this.spellId = spellId;
        this.spellLevel = 1;

        // Load spell properties from JSON configuration
        SpellConfig config = loadConfigFromResource(configLocation);
        if (config != null) {
            this.spellName = config.spellName;
            this.description = config.description;
            this.maxLevel = config.maxLevel;
            this.cooldown = config.cooldown;
            this.spellIcon = config.spellIcon;
        }
    }

    public String getSpellName() {
        return spellName;
    }

    public int getSpellId() {
        return spellId;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int level) {
        this.spellLevel = level;
    }
    
    public String getDescription() {
        return description;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCooldown() {
        return cooldown;
    }

    public String getSpellIcon() {
        return spellIcon;
    }


    public abstract boolean executeSpell(EntityPlayer p, int level); 	

    private SpellConfig loadConfigFromResource(ResourceLocation configLocation) {
        try {
            // Ouvre le fichier JSON à partir de la ResourceLocation
            InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(configLocation).getInputStream();
            String json = IOUtils.toString(inputStream, "UTF-8"); 
            IOUtils.closeQuietly(inputStream);

            Gson gson = new Gson();
            return gson.fromJson(json, SpellConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


 // Classe SpellConfig
    private static class SpellConfig {
        String spellName;
        String description;
        int maxLevel;
        int cooldown;
        String spellIcon;
    }
}