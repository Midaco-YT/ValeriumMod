package fr.valerium.valemod.craftings;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MineralExtractorRecipies {

    private static final MineralExtractorRecipies INSTANCE = new MineralExtractorRecipies();
    private final Map<Integer, ItemStack> smeltingList = new HashMap<Integer, ItemStack>();

    private MineralExtractorRecipies() {}

    public static MineralExtractorRecipies getInstance() {
        return INSTANCE;
    }

    public void registerRecipe(ItemStack input, ItemStack output) {
        smeltingList.put(Item.getIdFromItem(input.getItem()), output);
    }

    public ItemStack getOutput(ItemStack input) {
        ItemStack output = smeltingList.get(Item.getIdFromItem(input.getItem()));
        if (output != null) {
            System.out.println("A recipe has been cooked successfully!");
            return output.copy();
        }
        System.out.println(smeltingList);
        System.out.println(input);
        System.out.println("null!");
        return null;
    }
}
