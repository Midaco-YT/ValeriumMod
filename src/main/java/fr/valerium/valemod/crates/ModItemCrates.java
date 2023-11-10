package fr.valerium.valemod.crates;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valerium.valemod.crates.items.ItemKeyOasis;

public class ModItemCrates {

	
	public static ItemKeyOasis oasis_key;
	
	public static void init() {
		
		oasis_key = new ItemKeyOasis();
	}
	
	public static void register() {
		
		GameRegistry.registerItem(oasis_key, "oasis_key");
	}
}
