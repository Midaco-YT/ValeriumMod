package fr.valerium.valemod.blocks.barrels;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BarrelBlockBase extends BlockContainer {

	protected BarrelBlockBase(Material material) {
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return null;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
				return false;
		
	}
	
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		
	}
	
	public void registerBlockIcons(IIconRegister iiconRegister) {
		
	}
	
	public IIcon getIcon(int side, int metadata) {
		return blockIcon;
		
	}
}
