package fr.valerium.valemod.blocks;

import java.util.Random;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.jobs.JobLevelUtils;
import fr.valerium.valemod.jobs.JobManager;
import fr.valerium.valemod.utils.FixedRandom;
import fr.valerium.valemod.utils.StemFixedRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GrowthAcceleratorBlock extends Block {
	private FixedRandom prng;
	private StemFixedRandom s_prng;

	public GrowthAcceleratorBlock() {
		super(Material.rock);
		this.prng = new FixedRandom();
		this.s_prng = new StemFixedRandom();
		setTickRandomly(true);
		setHardness(0.2F);
		setStepSound(Block.soundTypePiston);
		setBlockName("growth_accelerator");
		setCreativeTab(ModVale.tabFarmer);
		setBlockTextureName("valerium:growth_accelerator");
	}

	public void updateTick(World world, int x, int y, int z, Random prng) {
		Block block_above = world.getBlock(x, y + 1, z);
		if (!world.blockExists(x, y + 1, z))
			return;
		if (block_above instanceof GrowthAcceleratorBlock) {
			block_above.updateTick(world, x, y + 1, z, prng);
			return;
		}
		if (!world.blockExists(x, y + 2, z))
			return;
		Block plant_block = world.getBlock(x, y + 2, z);
		if (plant_block instanceof net.minecraft.block.BlockStem) {
			if (world.getBlockMetadata(x, y + 2, z) >= 7) {
				plant_block.updateTick(world, x, y + 2, z, (Random) this.s_prng);
			} else {
				plant_block.updateTick(world, x, y + 2, z, (Random) this.prng);
			}
		} else if (plant_block instanceof net.minecraft.block.BlockReed
				|| plant_block instanceof net.minecraft.block.BlockCactus) {
			for (int l = 1; world.blockExists(x, y + 1 + l, z) && l < 3; l++)
				world.getBlock(x, y + 1 + l, z).updateTick(world, x, y + 1 + l, z, (Random) this.prng);
		} else if (plant_block instanceof net.minecraftforge.common.IPlantable) {
			plant_block.updateTick(world, x, y + 2, z, (Random) this.prng);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
	    // Vérifie que le joueur a au moins le niveau 3 en métier de farmer
	    EntityPlayer player = world.getClosestPlayer(x, y, z, -1);
	    return JobLevelUtils.canUseBlock(world, x, y, z, player, JobManager.FARMER_NAME, 3);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
	    // Vérifie que le joueur a au moins le niveau 3 en métier de farmer
	    EntityPlayer player = world.getClosestPlayer(x, y, z, -1);
	    return JobLevelUtils.canUseBlock(world, x, y, z, player, JobManager.FARMER_NAME, 3);
	}

	public boolean onBlockHarvested(World world, int x, int y, int z, EntityPlayer player) {
	    return JobLevelUtils.canPickupBlock(world, player, x, y, z, JobManager.FARMER_NAME ,3);
	}

}
