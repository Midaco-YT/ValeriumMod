package fr.valerium.valemod.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ExplodeSpell extends Spell {

	public ExplodeSpell(int spellId, ResourceLocation configLocation) {
		super(spellId, configLocation);
	}

	@Override
	public boolean executeSpell(EntityPlayer p, int level) {
		if (!p.worldObj.isRemote) {
			p.worldObj.createExplosion(p, p.posX, p.posY, p.posZ, 2.0F, true);
			return true;
		}
		return true;
	}
}
