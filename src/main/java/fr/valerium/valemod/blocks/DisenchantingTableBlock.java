package fr.valerium.valemod.blocks;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.tiles.TileEntityDisenchantingTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DisenchantingTableBlock extends BlockContainer {
	
	protected DisenchantingTableBlock(Material p_i45394_1_) {
		super(p_i45394_1_);

		this.setCreativeTab(ModVale.tabAlchimist);
		this.setBlockName("disenchanting_table");
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (!world.isRemote)
			player.openGui(ModVale.instance, 9, world, x, y, z);
		return true;
	}
	
	public TileEntity createNewTileEntity(World world, int metadata) {
		return (TileEntity) new TileEntityDisenchantingTable();
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
	
	@Override
	public int getRenderType() {
		return -1;
	}
}
