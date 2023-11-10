package fr.valerium.valemod.items.particles;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;

public class ItemValeriumParticle extends Item {
	
	public ItemValeriumParticle() {
		setUnlocalizedName("valerium_particle");
		setCreativeTab(ModVale.tabMiner);
		setTextureName("valerium:particles/valerium_particle");
	}
}
