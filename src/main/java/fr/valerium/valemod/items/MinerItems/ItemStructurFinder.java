package fr.valerium.valemod.items.MinerItems;

import java.util.List;

import fr.valerium.valemod.ModVale;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemStructurFinder extends Item {

	public ItemStructurFinder() {
		setUnlocalizedName("structure_finder");
		setCreativeTab(ModVale.tabValerium);
		setTextureName("valerium:structure_finder");
		setMaxStackSize(1);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (world.isRemote) {
            // Open the GUI on the client side
            player.openGui(ModVale.instance, 8, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
        }
        return itemStack;
    }
	
	@SuppressWarnings("unchecked")
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        list.add(EnumChatFormatting.GREEN + I18n.format("Find nearest Structure."));
    }
}
