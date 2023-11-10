package fr.valerium.valemod.items.rings;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRandomRing extends Item{

	public ItemRandomRing() {
		setUnlocalizedName("random_ring");
		setCreativeTab(ModVale.tabAlchimist);
		setTextureName("valerium:rings/random_ring");
		setMaxStackSize(1);
		this.setMaxDamage(16);
	}
	
	Item[] items = new Item[] { ModItems.xp_ring, ModItems.fortune_ring};
	
	public ItemStack onItemRightClick(ItemStack itemstack,World world, EntityPlayer player)
    { 
			int random = world.rand.nextInt(items.length);
			player.inventory.addItemStackToInventory(new ItemStack(items[random]));
			itemstack.damageItem(1000, player);
			return itemstack;

    }
	
	
	
	@Override
    public boolean hasEffect(ItemStack stack)
    {
	    return true;
    }
}
