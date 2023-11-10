package fr.valerium.valemod.items;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.jobs.JobManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class ItemXpTest extends Item {
	public ItemXpTest() {
		setTextureName("valerium:item_test_xp");
		setCreativeTab(ModVale.tabValerium);
	}

	public int exp = 10;
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
			JobManager jobManager = JobManager.get(player);
			jobManager.setLevel(JobManager.FARMER_NAME, 3);
			int currentExperience = jobManager.experience.get(JobManager.MINER_NAME);
			int minerLevel = jobManager.getLevel(JobManager.MINER_NAME);
			if(!world.isRemote) {
				ChatComponentTranslation xp = new ChatComponentTranslation("tu as " + currentExperience + " XP");
				ChatComponentTranslation level = new ChatComponentTranslation("tu est level " + minerLevel + " Miner");
				player.addChatMessage(xp);
				player.addChatMessage(level);
			}
		return itemstack;
	}
}
