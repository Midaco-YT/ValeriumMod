package fr.valerium.valemod.items.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionDoubleXP extends Potion {

	private static PotionDoubleXP instance;

    public static PotionDoubleXP getInstance() {
        if (instance == null) {
            instance = new PotionDoubleXP(28);
        }
        return instance;
    }
	
    public PotionDoubleXP(int id) {
        super(id, false, 0xFFFFFF);
        setPotionName("potion.doubleXP");
        setIconIndex(0, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.getActivePotionEffect(this) == null) {
                player.getEntityData().setBoolean("has_double_xp", false);
            } else {
                player.getEntityData().setBoolean("has_double_xp", true);
            }
        }
    }


    @Override
    public void affectEntity(EntityLivingBase entity, EntityLivingBase source, int amplifier, double health) {
        super.affectEntity(entity, source, amplifier, health);

        // Appliquer une durée personnalisée à l'effet de la potion
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            int duration = 200; // Durée personnalisée en ticks (par exemple : 10 secondes)
            player.addPotionEffect(new PotionEffect(this.id, duration, amplifier, true));
        }
    }

}
