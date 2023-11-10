package fr.valerium.valemod.blocks;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.tiles.TileEntityGrowingStand;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GrowingStandBlock extends Block {

	protected GrowingStandBlock(Material p_i45394_1_) {
		super(p_i45394_1_);

		this.setCreativeTab(ModVale.tabFarmer);
		this.setBlockName("growing_stand");
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
	    int direction = MathHelper.floor_double((double)(entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

	    world.setBlockMetadataWithNotify(x, y, z, direction, 1);
	}

	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityGrowingStand();
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
 
    public boolean isOpaqueCube()
    {
        return false;
    }

}
