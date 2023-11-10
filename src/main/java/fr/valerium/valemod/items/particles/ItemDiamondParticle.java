package fr.valerium.valemod.items.particles;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;

public class ItemDiamondParticle extends Item {
	
	public ItemDiamondParticle() {
		setUnlocalizedName("diamond_particle");
		setCreativeTab(ModVale.tabMiner);
		setTextureName("valerium:particles/diamond_particle");
	}
}
