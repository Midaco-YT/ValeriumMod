package fr.valerium.valemod.items.modifiers;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.items.ItemModifierBase;
import fr.valerium.valemod.utils.Modifier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class ItemFortuneModifier extends ItemModifierBase {
    public ItemFortuneModifier() {
        super(new Modifier("fortune", 0, 3));
        this.setUnlocalizedName("fortune_modifer");
        this.setCreativeTab(ModVale.tabMiner);
        this.setTextureName("valerium:fortune_modifier");
    }

    @Override
    public void addEnhancement(ItemStack stack) {
        super.addEnhancement(stack);
        stack.addEnchantment(Enchantment.fortune, this.getModifier().getLevel());
    }
}
