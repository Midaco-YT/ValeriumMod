package fr.valerium.valemod.events;

import java.util.Random;

import fr.valerium.valemod.enchant.EnchantmentMending;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

public class MendingEvent {

	public static void onPickupXP(PlayerPickupXpEvent event) {
		ItemStack held = event.entityPlayer.getHeldItem();
		ItemStack Armor0 = event.entityPlayer.getCurrentArmor(0);
		ItemStack Armor1 = event.entityPlayer.getCurrentArmor(1);
		ItemStack Armor2 = event.entityPlayer.getCurrentArmor(2);
		ItemStack Armor3 = event.entityPlayer.getCurrentArmor(3);
		int IDHeld = EnchantmentHelper.getEnchantmentLevel(EnchantmentMending.MendingID, held);
		int IDArmor0 = EnchantmentHelper.getEnchantmentLevel(EnchantmentMending.MendingID, Armor0);
		int IDArmor1 = EnchantmentHelper.getEnchantmentLevel(EnchantmentMending.MendingID, Armor1);
		int IDArmor2 = EnchantmentHelper.getEnchantmentLevel(EnchantmentMending.MendingID, Armor2);
		int IDArmor3 = EnchantmentHelper.getEnchantmentLevel(EnchantmentMending.MendingID, Armor3);
		ItemStack FinalSlot = event.entityPlayer.getHeldItem();
		ItemStack slotNumber1st = null;
		ItemStack slotNumber2nd = null;
		ItemStack slotNumber3rd = null;
		ItemStack slotNumber4th = null;
		ItemStack slotNumber5th = null;
		int slotAmount = 0;
		if (IDHeld > 0 && held.getItemDamage() > 0) {
			slotAmount++;
			slotNumber1st = held;
		}
		if (IDArmor0 > 0 && Armor0.getItemDamage() > 0) {
			slotAmount++;
			if (slotNumber1st == null) {
				slotNumber1st = Armor0;
			} else {
				slotNumber2nd = Armor0;
			}
		}
		if (IDArmor1 > 0 && Armor1.getItemDamage() > 0) {
			slotAmount++;
			if (slotNumber1st == null) {
				slotNumber1st = Armor1;
			} else if (slotNumber2nd == null) {
				slotNumber2nd = Armor1;
			} else {
				slotNumber3rd = Armor1;
			}
		}
		if (IDArmor2 > 0 && Armor2.getItemDamage() > 0) {
			slotAmount++;
			if (slotNumber1st == null) {
				slotNumber1st = Armor2;
			} else if (slotNumber2nd == null) {
				slotNumber2nd = Armor2;
			} else if (slotNumber3rd == null) {
				slotNumber3rd = Armor2;
			} else {
				slotNumber4th = Armor2;
			}
		}
		if (IDArmor3 > 0 && Armor3.getItemDamage() > 0) {
			slotAmount++;
			if (slotNumber1st == null) {
				slotNumber1st = Armor3;
			} else if (slotNumber2nd == null) {
				slotNumber2nd = Armor3;
			} else if (slotNumber3rd == null) {
				slotNumber3rd = Armor3;
			} else if (slotNumber4th == null) {
				slotNumber4th = Armor3;
			} else {
				slotNumber5th = Armor3;
			}
		}
		Random random = new Random();
		int rand = random.nextInt(slotAmount);

		if (rand == 0) {
			slotNumber1st.setItemDamage(slotNumber1st.getItemDamage() - 2 * event.orb.getXpValue());
		} else if (rand == 1) {
			slotNumber2nd.setItemDamage(slotNumber2nd.getItemDamage() - 2 * event.orb.getXpValue());
		} else if (rand == 2) {
			slotNumber3rd.setItemDamage(slotNumber3rd.getItemDamage() - 2 * event.orb.getXpValue());
		} else if (rand == 3) {
			slotNumber4th.setItemDamage(slotNumber4th.getItemDamage() - 2 * event.orb.getXpValue());
		} else if (rand == 4) {
			slotNumber5th.setItemDamage(slotNumber5th.getItemDamage() - 2 * event.orb.getXpValue());
		}
		
		event.entityPlayer.addExperience(-event.orb.getXpValue());
	}
}
