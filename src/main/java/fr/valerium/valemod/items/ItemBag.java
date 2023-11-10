package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBag extends Item {
  public ItemBag() {
    setMaxStackSize(1);
    setUnlocalizedName("bag");
    setCreativeTab(ModVale.tabValerium);
    setTextureName("valerium:bag");
  }
  
  @Override
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      player.openGui(ModVale.instance, 11, world, (int) player.posX, (int) player.posY, (int) player.posZ);
      return stack;
  }
}
