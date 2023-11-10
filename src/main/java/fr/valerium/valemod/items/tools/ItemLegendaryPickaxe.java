package fr.valerium.valemod.items.tools;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Constants;

public class ItemLegendaryPickaxe extends ItemTool {

	private static final float DIG_SPEED_WEB = 15.0f;

	private static final float DIG_SPEED_SWORD = 1.5f;

	private static final float DIG_SPEED_DEFAULT = 1.0f;

	public static final String ENHANCEMENTS_TAG = "enhancements";

	private static final int MAX_ENHANCEMENTS = 3;

	public ItemLegendaryPickaxe(ToolMaterial toolMaterial) {
		super(4.0f, toolMaterial, Collections.EMPTY_SET);
		setHarvestLevel("pickaxe", toolMaterial.getHarvestLevel());
		setHarvestLevel("axe", toolMaterial.getHarvestLevel());
		setHarvestLevel("shovel", toolMaterial.getHarvestLevel());
		setUnlocalizedName("legendary_pickaxe");
		setTextureName("valerium:legendary_pickaxe");

		setHarvestLevel("sword", toolMaterial.getHarvestLevel());

		setCreativeTab(ModVale.tabValerium);
	}

	private static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(
			// Pickaxe
			Material.rock, Material.iron, Material.ice, Material.glass, Material.piston, Material.anvil,
			Material.circuits,

			// Axe
			Material.wood, Material.gourd, Material.plants, Material.vine,

			// Shovel
			Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay);

	private static final Set<Material> SWORD_MATERIALS = ImmutableSet.of(Material.plants, Material.vine, Material.coral,
			Material.leaves, Material.gourd);

	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemStack) {
		return EFFECTIVE_MATERIALS.contains(block.getMaterial()) || block == Blocks.web;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		if (block == Blocks.web) {
			return DIG_SPEED_WEB;
		}

		if (ForgeHooks.isToolEffective(stack, block, meta) || EFFECTIVE_MATERIALS.contains(block.getMaterial())) {
			return efficiencyOnProperMaterial;
		}

		if (SWORD_MATERIALS.contains(block.getMaterial())) {
			return DIG_SPEED_SWORD;
		}

		return DIG_SPEED_DEFAULT;
	}

	public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) {
		itemStack.damageItem(1, attacker);
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
		super.addInformation(stack, player, list, advanced);
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null) {
			NBTTagList enhancements = tag.getTagList(ENHANCEMENTS_TAG, Constants.NBT.TAG_COMPOUND);
			for (int i = 0; i < enhancements.tagCount(); i++) {
				NBTTagCompound enhancementTag = enhancements.getCompoundTagAt(i);
				String name = enhancementTag.getString("name");
				list.add("-" + name);
			}
		}
	}

}