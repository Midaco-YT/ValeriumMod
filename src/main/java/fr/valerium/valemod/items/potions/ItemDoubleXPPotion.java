package fr.valerium.valemod.items.potions;

import fr.valerium.valemod.ModVale;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemDoubleXPPotion extends Item {
    
    public ItemDoubleXPPotion() {
        super();
        this.setCreativeTab(ModVale.tabAlchimist);
        this.setUnlocalizedName("potion_double_xp");
        this.setTextureName("valerium:potion_double_xp");
        this.setMaxStackSize(1);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            stack.stackSize--;
        }
        
        // Appliquer l'effet de la potion
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(ModVale.potionDoubleXP.id, 200, 0));
        }
        
        return stack;
    }
    
    @Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
    }
}
