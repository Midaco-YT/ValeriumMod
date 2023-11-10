package fr.valerium.valemod.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentMending extends Enchantment
{
	public static int MendingID = 200;

	public EnchantmentMending(int effectId, int Rarity) {
		super(effectId, Rarity, EnumEnchantmentType.breakable);
		this.setName("Mending");
	}
	
	public int getMinEnchantability(int enchantmentLevel)
    {
        return enchantmentLevel * 25;
    }

    public int func_77317_b(int p_77317_1_)
    {
        return this.getMinEnchantability(p_77317_1_) + 50;
    }

    public boolean isTreasureEnchantment()
    {
        return true;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 1;
    }

}
