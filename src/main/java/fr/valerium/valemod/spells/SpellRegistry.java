package fr.valerium.valemod.spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellRegistry {
	private static final Map<Integer, Spell> SPELLS = new HashMap<>();
	
	
	public static void registerSpells(Spell spell) {
		SPELLS.put(spell.getSpellId(), spell);
	}
	
	public Spell getSpellById(int spellId) {
		return SPELLS.get(spellId);
	}
	
	public static List<Spell> getAllSpells(){
		return new ArrayList<>(SPELLS.values());
	}
}
