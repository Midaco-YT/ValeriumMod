package fr.valerium.valemod.spells;

import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GolemiSpell extends Spell {

	public GolemiSpell(int spellId, ResourceLocation configLocation) {
		super(spellId, configLocation);
	}

	@Override
	public boolean executeSpell(EntityPlayer p, int level) {
		for (int i = 0; i < getGolemNumber(level); i++) {
			EntityIronGolem golem = new EntityIronGolem(p.worldObj);
			golem.setPosition(p.posX + 3, p.posY, p.posZ + 3);
			golem.setCustomNameTag("Golem de " + p.getDisplayName());

			// Assurez-vous que le golem ne cible pas le joueur
			golem.setRevengeTarget(null);

			// Réglez le golem pour attaquer les autres entités
			if (p.worldObj.rand.nextFloat() < 0.5) {
				golem.setAttackTarget(p);
			}

			p.worldObj.spawnEntityInWorld(golem);
		}
		return true;
	}

	public boolean canAttackEntity(EntityPlayer p) {
		return !p.getDisplayName().equals(p.getDisplayName());
	}

	public int getGolemNumber(int level) {
		switch (level) {
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		default:
			return 0;
		}
	}
}
