package fr.valerium.valemod.items.modifiers;

import fr.valerium.valemod.ModVale;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemFilter extends Item {

    public ItemFilter() {
        setUnlocalizedName("item_filter");
        setCreativeTab(ModVale.tabValerium);
        setTextureName("valerium:materials/amethyst_ingot");
    }

    public int getMultiplierForOreDict(ItemStack stack) {
        int multiplier = 0;
        int[] oreIDs = OreDictionary.getOreIDs(stack);
        for (int id : oreIDs) {
            String oreName = OreDictionary.getOreName(id);
            // set the multiplier based on the ore name
            if (oreName.startsWith("ore")) {
                multiplier = 2;
            } else if (oreName.startsWith("ingot")) {
                multiplier = 3;
            }
            // add more cases as needed
        }
        return multiplier;
    }
}
