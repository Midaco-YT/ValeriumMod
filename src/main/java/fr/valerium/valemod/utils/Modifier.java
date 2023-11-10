package fr.valerium.valemod.utils;

import net.minecraft.item.ItemStack;

public class Modifier {
    private String name;
    private int id;
    private int level;

    public Modifier(String name, int id, int level) {
        this.name = name;
        this.id = id;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public void apply(ItemStack stack) {

    }
}
