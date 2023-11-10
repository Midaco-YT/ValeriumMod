package fr.valerium.valemod.spells;

public class SpellSelection {
	private static Spell selectedSpell;

	public static Spell getSelectedSpell() {
		return selectedSpell;
	}
	
	public static void setSelectedSpell(Spell spell) {
		selectedSpell = spell;
	}
}
