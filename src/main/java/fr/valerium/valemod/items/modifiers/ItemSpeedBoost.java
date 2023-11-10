package fr.valerium.valemod.items.modifiers;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;

public class ItemSpeedBoost extends Item {

	private float multiplier;
	
	public ItemSpeedBoost(float multiplier) {
		this.multiplier = multiplier;
		setUnlocalizedName("speed_boost");
		setCreativeTab(ModVale.tabValerium);
		setTextureName("valerium:speed_boost");
	}
	
	public float getMultiplier() {
        return multiplier;
    }
}
