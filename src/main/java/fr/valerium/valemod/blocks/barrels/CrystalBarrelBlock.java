package fr.valerium.valemod.blocks.barrels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.tiles.TileEntityCrystalBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrystalBarrelBlock extends BarrelBlockBase {

	@SideOnly(Side.CLIENT)
	private IIcon top, bottom, top1;
	private Entity entityitem;
	private boolean activate = false;

	public CrystalBarrelBlock(Material material) {
		super(Material.glass);

		this.setCreativeTab(ModVale.tabBarrel);
		this.setBlockName("crystal_barrel");
		this.setBlockTextureName("valerium:crystal_barrel");
		this.setHardness(10);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (!world.isRemote)
			player.openGui(ModVale.instance, 7, world, x, y, z);
		return activate = true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return (TileEntity) new TileEntityCrystalBarrel();
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		TileEntity tileentity = world.getTileEntity(x, y, z);
	    if (tileentity instanceof IInventory) {
	      IInventory inv = (IInventory)tileentity;
	      for (int i1 = 0; i1 < inv.getSizeInventory(); i1++) {
	        ItemStack itemstack = inv.getStackInSlot(i1);
	        if (itemstack != null) {
	          float f = world.rand.nextFloat() * 0.8F + 0.1F;
	          float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
	          EntityItem entityitem;
	          
	          for(float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
	            int j1 = world.rand.nextInt(21) + 10;
	            if (j1 > itemstack.stackSize)
	              j1 = itemstack.stackSize; 
	            itemstack.stackSize -= j1;
	            entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
	            float f3 = 0.05F;
	            entityitem.motionX = ((float)world.rand.nextGaussian() * f3);
	            entityitem.motionY = ((float)world.rand.nextGaussian() * f3 + 0.2F);
	            entityitem.motionZ = ((float)world.rand.nextGaussian() * f3);
	            if (itemstack.hasTagCompound())
	              entityitem.getEntityItem()
	                .setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy()); 
	          } 
	        } 
	      } 
	      world.func_147453_f(x, y, z, block);
	    } 
	    super.breakBlock(world, x, y, z, block, metadata);
	  }
	
	public void registerBlockIcons(IIconRegister iiconRegister) {
		this.blockIcon = iiconRegister.registerIcon("valerium:crystal_barrel_side");
		this.top = iiconRegister.registerIcon("valerium:crystal_barrel_top");
		this.bottom = iiconRegister.registerIcon("valerium:crystal_barrel_bottom");
		this.top1 = iiconRegister.registerIcon("valerium:crystal_barrel_top_open");
	}

	public IIcon getIcon(int side, int metadata)
    {
        if(side == 0)
        {
            return this.bottom;
        }
        else if(side == 1)
        {
            return this.top;
        }
        return this.blockIcon;
    }
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
	 return true;
	}
}
