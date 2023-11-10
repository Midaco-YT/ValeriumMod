package fr.valerium.valemod.items.particles;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;

public class ItemIronParticle extends Item {
	
	public ItemIronParticle() {
		setUnlocalizedName("iron_particle");
		setCreativeTab(ModVale.tabMiner);
		setTextureName("valerium:particles/iron_particle");
	}
}
