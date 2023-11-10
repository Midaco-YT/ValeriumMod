package fr.valerium.valemod.crates.blocks;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.crates.tileentity.TileEntityOasisCrate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOasisCrate extends Block {

	public BlockOasisCrate(Material material) {
		super(material);
		this.setCreativeTab(ModVale.tabCrates);
		this.setBlockName("oasis_crate");
		this.setBlockTextureName("valerium:oasis_crate");
		this.setHardness(10);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		int direction = MathHelper.floor_double((double) (living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityOasisCrate) {
			((TileEntityOasisCrate) te).setDirection((byte) direction);
			world.markBlockForUpdate(x, y, z);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityOasisCrate();
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}
}
