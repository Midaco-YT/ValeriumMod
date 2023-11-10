package fr.valerium.valemod.blocks;

import java.util.Random;

import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class FertilizedDirtBlock extends Block {

	public FertilizedDirtBlock() {

		super(Material.ground);

		this.setTickRandomly(true);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGravel);
		this.setLightOpacity(255);
		setBlockName("fertilized_dirt");
		setCreativeTab(ModVale.tabFarmer);
		setBlockTextureName("valerium:fertilized_dirt");
	}

	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
	    EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
	    switch (plantType) {
	        case Desert:
	        case Cave:
	        case Plains:
	        	return true;
	        case Beach:
	            return true;
	        case Nether:
	        case Crop:
	        	return true;
	        case Water:
	            return false;
	        default:
	            return true;
	    }
	}


	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			Block toBoost = world.getBlock(x, y - 1, z);
			if (toBoost != null && toBoost != Blocks.air && toBoost instanceof IPlantable) {
				world.playAuxSFX(2005, x, y + 1, z, 0);
			}
			for (int i = 0; i < 5; i++) {
				toBoost = world.getBlock(x, y + 1, z);
				if (toBoost != null && toBoost != Blocks.air && toBoost instanceof IPlantable) {
					toBoost.updateTick(world, x, y + 1, z, rand);
				}
			}
		}
	}

}