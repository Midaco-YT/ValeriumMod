package fr.valerium.valemod.items.rings;

import java.util.List;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.items.ModItems;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class ItemFortuneRing extends Item{

	public ItemFortuneRing() {
		setUnlocalizedName("fortune_ring");
		setCreativeTab(ModVale.tabAlchimist);
		setTextureName("valerium:rings/fortune_ring");
		setMaxStackSize(1);
	}
	
	Item[] items = new Item[] { ModItems.valerium_ingot, ModItems.titane_ingot, ModItems.amethyst_ingot, ModItems.sulfur_crystal};
	
	public ItemStack onItemRightClick(ItemStack itemstack,World world, EntityPlayer player)
    { 
		if(!itemstack.hasTagCompound())
        {
			itemstack.setTagCompound(new NBTTagCompound());
			itemstack.stackTagCompound.setInteger("timer", 0);
        }
		if(itemstack.stackTagCompound.getInteger("timer") == 0) {
			
			int random = world.rand.nextInt(items.length);
	        player.inventory.addItemStackToInventory(new ItemStack(items[random]));
	        itemstack.stackTagCompound.setInteger("timer", 1);
		}
		else {
			if(world.isRemote)
				player.addChatComponentMessage(new ChatComponentTranslation("Tu dois attendre que la pierre se recharge !"));
		}
		return itemstack;
    }
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
    }
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity player, int slotIndex, boolean inHand)
	{
	if(item.hasTagCompound())//Si ton item n'a pas de tag alors on ne fait rien
	{
	if(item.stackTagCompound.getInteger("timer") > 0)//si ton timer est supérieur à 0 (après un clic droit logiquement)
	{
	item.stackTagCompound.setInteger("timer", (int) (item.stackTagCompound.getInteger("timer") + 1));//On l'incrémente de 1 à chaque tick
	}
	if(item.stackTagCompound.getInteger("timer") >= (int) (6*20))//Remplace 6 par le nombre de secondes du timer souhaité
	{
	item.stackTagCompound.setInteger("timer", 0);//On remet à 0 si le timer est arrivé à la limite souhaitée
	}
	}
	super.onUpdate(item, world, player, slotIndex, inHand);
	}
	
	public void addDescription(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
	    super.addInformation(stack, player, list, b);
	    if (GuiScreen.isShiftKeyDown()) {
	    list.add("Cette pierre permet de drop un minerais aléatoire");
	    }
	  }
}
