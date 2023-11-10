package fr.valerium.valemod.spells;

import net.minecraft.util.ResourceLocation;

public class ModSpell {

	public static final Spell ATTRACTIO = new AttractioSpell(1, new ResourceLocation("valerium:spells/attractio.json"));
	public static final Spell EXPLODE = new ExplodeSpell(2, new ResourceLocation("valerium:spells/explode.json"));
	public static final Spell GOLEMI = new GolemiSpell(3, new ResourceLocation("valerium:spells/golemi.json"));

	
	public static void registerSpell() {
		
		SpellRegistry.registerSpells(ATTRACTIO);
		SpellRegistry.registerSpells(EXPLODE);
		SpellRegistry.registerSpells(GOLEMI);
		
	}
}
