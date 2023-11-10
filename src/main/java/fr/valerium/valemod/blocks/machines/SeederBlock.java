package fr.valerium.valemod.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.tiles.TileEntitySeeder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class SeederBlock extends BlockContainer {
    private IIcon front;
    private IIcon top;
    private IIcon bottom;

    public SeederBlock() {
        super(Material.rock);
        setResistance(8.0F);
        setHarvestLevel("pickaxe", 2);
        setCreativeTab(ModVale.tabValerium);
        setBlockName("seeder");
        setHardness(12.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntitySeeder();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileEntity tileentity = world.getTileEntity(x, y, z);
        if (tileentity instanceof IInventory) {
            IInventory inv = (IInventory) tileentity;
            for (int i1 = 0; i1 < inv.getSizeInventory(); i1++) {
                ItemStack itemstack = inv.getStackInSlot(i1);
                if (itemstack != null) {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
                    while (itemstack.stackSize > 0) {
                        int j1 = world.rand.nextInt(21) + 10;
                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (float) world.rand.nextGaussian() * f3;
                        entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }
            super.breakBlock(world, x, y, z, block, metadata);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(ModVale.instance, 12, world, x, y, z);
        }

        TileEntitySeeder tileEntity = (TileEntitySeeder) world.getTileEntity(x, y, z);
        boolean isPowered = world.isBlockIndirectlyGettingPowered(x, y, z);
        tileEntity.setPlantingEnabled(isPowered);

        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("valerium:seeder_side");
        this.front = iconRegister.registerIcon("valerium:seeder_front");
        this.top = iconRegister.registerIcon("valerium:seeder_top");
        this.bottom = iconRegister.registerIcon("valerium:seeder_top");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int metadata) {
        if (side == 0) {
            return this.bottom;
        } else if (side == 1) {
            return this.top;
        }
        return (side != metadata) ? this.blockIcon : this.front;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        byte b0 = 3;
        b0 = rotateBlock(b0, entity);
        world.setBlockMetadataWithNotify(x, y, z, b0, 2);
    }

    private byte rotateBlock(byte b0, EntityLivingBase entity) {
        float yaw = entity.rotationYaw;
        if (yaw < 0) {
            yaw += 360;
        }
        if (yaw >= 45 && yaw < 135) {
            b0 = 5;
        } else if (yaw >= 135 && yaw < 225) {
            b0 = 3;
        } else if (yaw >= 225 && yaw < 315) {
            b0 = 4;
        } else {
            b0 = 2;
        }
        return b0;
    }
}