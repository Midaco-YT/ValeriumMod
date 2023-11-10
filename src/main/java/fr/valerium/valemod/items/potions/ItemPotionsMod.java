package fr.valerium.valemod.items.potions;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemPotionsMod {
    
    public static Item doubleXPPotion;
    
    public static void registerItems() {
        doubleXPPotion = new ItemDoubleXPPotion();
        GameRegistry.registerItem(doubleXPPotion, "double_xp_potion");
    }
}
