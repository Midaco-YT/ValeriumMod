package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMoney extends Item {

    public ItemMoney() {
        setCreativeTab(ModVale.tabMoney);
        setUnlocalizedName("money");
        setTextureName("valerium:money");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
        	
                return itemStack;
            }
        

        return super.onItemRightClick(itemStack, world, player);
    }
}
