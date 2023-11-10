package fr.valerium.valemod.events;

import java.util.Random;

import fr.valerium.valemod.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ValeriumLuckyBlockEvent {

	private String name;
	private ResourceLocation image;

	public ValeriumLuckyBlockEvent(String name, ResourceLocation image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public ResourceLocation getImage() {
		return image;
	}

	public void executeEvent(EntityPlayer player) {
		// Implémentez le code de l'événement ici
		// Par exemple, vous pouvez utiliser des instructions conditionnelles pour
		// exécuter différentes actions en fonction de l'événement
		if (name.equals("Spawn Chest")) {
			spawnChestEvent(player);
		} else if (name.equals("Spawn Ore")) {
			spawnOreEvent(player);
		} else if (name.equals("Repair Items")) {
			repairItemsEvent(player);
		} else if (name.equals("TNT Explosion")) {
			tntExplosionEvent(player);
		} else if (name.equals("Spawn Zombie")) {
			spawnZombieEvent(player);
		} else if (name.equals("Double Exp Potion")) {
			doubleExpPotionEvent(player);
		} else if (name.equals("Teleport")) {
			teleportEvent(player);
		} else if (name.equals("Slowness Effect")) {
			slownessEffectEvent(player);
		}
	}

	private void repairItemsEvent(EntityPlayer player) {
		// Repair all items in the player's inventory
		for (ItemStack stack : player.inventory.mainInventory) {
			if (stack != null && stack.isItemStackDamageable()) {
				stack.setItemDamage(0);
			}
		}
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Tous vos objets ont été réparés."));
	}

	private void spawnOreEvent(EntityPlayer player) {
		// Spawn ore blocks around the player
		World world = player.worldObj;
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			int posX = (int) player.posX + random.nextInt(16) - 8;
			int posY = (int) player.posY + random.nextInt(8) - 4;
			int posZ = (int) player.posZ + random.nextInt(16) - 8;

			if (world.isAirBlock(posX, posY, posZ) && world.getBlock(posX, posY - 1, posZ).isOpaqueCube()) {
				// Generate a random ore block
				Block oreBlock = getRandomOreBlock(random);
				world.setBlock(posX, posY, posZ, oreBlock);
			}
		}
	}

	private Block getRandomOreBlock(Random random) {
		// Add your desired ore blocks here
		Block[] oreBlocks = { Blocks.iron_ore, Blocks.gold_ore, Blocks.diamond_ore, Blocks.emerald_ore };
		return oreBlocks[random.nextInt(oreBlocks.length)];
	}

	private void spawnChestEvent(EntityPlayer player) {
		// Spawn a chest with items inside
		// You can customize the items inside the chest as desired
		// Example:
		ItemStack diamondStack = new ItemStack(Items.diamond, 5);
		ItemStack emeraldStack = new ItemStack(Items.emerald, 3);
		ItemStack goldIngotStack = new ItemStack(Items.gold_ingot, 10);
		ItemStack chestplateStack = new ItemStack(Items.diamond_chestplate);
		ItemStack swordStack = new ItemStack(Items.diamond_sword);

		ItemStack[] loot = { diamondStack, emeraldStack, goldIngotStack, chestplateStack, swordStack };

	}

	private void tntExplosionEvent(EntityPlayer player) {
		player.worldObj.createExplosion(null, player.posX, player.posY, player.posZ, 0F, false);
	}

	private void spawnZombieEvent(EntityPlayer player) {
		// Spawn a zombie with diamond armor and a diamond sword
		EntityZombie zombie = new EntityZombie(player.worldObj);
		zombie.setCanPickUpLoot(true);
		zombie.setEquipmentDropChance(0, 0.5F);
		zombie.setEquipmentDropChance(1, 0.5F);
		zombie.setEquipmentDropChance(2, 0.5F);
		zombie.setEquipmentDropChance(3, 0.5F);
		zombie.setEquipmentDropChance(4, 0.5F);
		zombie.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_sword));
		zombie.setCurrentItemOrArmor(1, new ItemStack(Items.diamond_boots));
		zombie.setCurrentItemOrArmor(2, new ItemStack(Items.diamond_leggings));
		zombie.setCurrentItemOrArmor(3, new ItemStack(Items.diamond_chestplate));
		zombie.setCurrentItemOrArmor(4, new ItemStack(Items.diamond_helmet));
		zombie.setPosition(player.posX, player.posY, player.posZ);
		player.worldObj.spawnEntityInWorld(zombie);
	}

	private void doubleExpPotionEvent(EntityPlayer player) {
		// Give the player a double experience potion
		ItemStack doubleExpPotion = new ItemStack(ModItems.doubleXPPotion);
		player.inventory.addItemStackToInventory(doubleExpPotion);
	}

	private void teleportEvent(EntityPlayer player) {
		// Teleport the player 100 blocks above their current position
		player.setPosition(player.posX, player.posY + 100, player.posZ);
		// Give the player a water bucket
		ItemStack waterBucket = new ItemStack(Items.water_bucket);
		player.inventory.addItemStackToInventory(waterBucket);
	}

	private void slownessEffectEvent(EntityPlayer player) {
		// Apply a slowness effect to the player
		player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
	}
}
