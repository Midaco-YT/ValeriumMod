package fr.valerium.valemod.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.tiles.TileEntityStoneCrafter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
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
import net.minecraft.world.World;

public class StoneCrafter extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon top;
	private Entity entityitem;

	public StoneCrafter(Material material) {
		super(Material.wood);

		this.setCreativeTab(ModVale.tabAlchimist);
		this.setBlockName("stone_crafter");
		this.setBlockTextureName("valerium:stone_crafter");
		this.setHardness(10);
	}

	public TileEntity createNewTileEntity(World world, int metadata) {
		return (TileEntity) new TileEntityStoneCrafter();
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (!world.isRemote)
			player.openGui(ModVale.instance, 0, world, x, y, z);
		return true;
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof IInventory) {
			IInventory inv = (IInventory) tileentity;
			for (int i1 = 0; i1 < inv.getSizeInventory(); i1++) {
				ItemStack itemstack = inv.getStackInSlot(i1);
				if (itemstack != null) {
					float f = world.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world
							.spawnEntityInWorld((Entity) entityitem)) {
						int j1 = world.rand.nextInt(21) + 10;
						if (j1 > itemstack.stackSize)
							j1 = itemstack.stackSize;
						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2),
								new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = ((float) world.rand.nextGaussian() * f3);
						entityitem.motionY = ((float) world.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = ((float) world.rand.nextGaussian() * f3);
						if (itemstack.hasTagCompound())
							entityitem.getEntityItem()
									.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
					}
				}
			}
			world.func_147453_f(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, metadata);
	}

	public void registerBlockIcons(IIconRegister iiconRegister) {
		this.blockIcon = iiconRegister.registerIcon("valerium:stone_crafter_side");
		this.top = iiconRegister.registerIcon("valerium:stone_crafter_top");
	}

	public IIcon getIcon(int side, int metadata) {
		if (side == 1)
			return this.top;
		return this.blockIcon;
	}
}
