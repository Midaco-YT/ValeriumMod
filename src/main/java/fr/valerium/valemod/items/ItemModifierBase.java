package fr.valerium.valemod.items;

import fr.valerium.valemod.items.tools.ItemLegendaryPickaxe;
import fr.valerium.valemod.utils.Modifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ItemModifierBase extends Item {

	private Modifier modifier;

    public ItemModifierBase(Modifier modifier) {
        super();
        this.modifier = modifier;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void addEnhancement(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }
        NBTTagList enhancements = tag.getTagList(ItemLegendaryPickaxe.ENHANCEMENTS_TAG, Constants.NBT.TAG_COMPOUND);
        NBTTagCompound enhancementTag = new NBTTagCompound();
        enhancementTag.setString("name", modifier.getName());
        enhancementTag.setInteger("id", modifier.getId());
        enhancementTag.setInteger("level", modifier.getLevel());
        enhancements.appendTag(enhancementTag);
        modifier.apply(stack);
    }
}
