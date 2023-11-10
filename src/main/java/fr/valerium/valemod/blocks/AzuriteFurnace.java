package fr.valerium.valemod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.tiles.TileEntityAzuriteFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class AzuriteFurnace extends BlockContainer {
  public static final Entity Entity = null;
private IIcon front;
  
  protected AzuriteFurnace() {
    super(Material.rock);
    setResistance(8.0F);
    setHarvestLevel("pickaxe", 2);
    setCreativeTab(ModVale.tabValerium);
    setBlockName("azurite_furnace");
    setHardness(12.0F);
  }
  
  public TileEntity createNewTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityAzuriteFurnace();
  }
  
  public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
    TileEntity tileentity = world.getTileEntity(x, y, z);
    if (tileentity instanceof IInventory) {
      IInventory inv = (IInventory)tileentity;
      for (int i1 = 0; i1 < inv.getSizeInventory(); i1++) {
        ItemStack itemstack = inv.getStackInSlot(i1);
        if (itemstack != null) {
          float f = world.rand.nextFloat() * 0.8F + 0.1F;
          float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
          for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world
            .spawnEntityInWorld((Entity))) {
            int j1 = world.rand.nextInt(21) + 10;
            if (j1 > itemstack.stackSize)
              j1 = itemstack.stackSize; 
            itemstack.stackSize -= j1;
            EntityItem entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
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
  }
  
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.isRemote)
      player.openGui(ModVale.instance, 3, world, x, y, z); 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iiconRegister) {
    this.blockIcon = iiconRegister.registerIcon("valerium:azurite_furnace_Side");
    this.front = iiconRegister.registerIcon("valerium:azurite_furnace_Front");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int side, int metadata) {
    if (metadata == 0 && side == 3)
      return this.front; 
    return (side != metadata) ? this.blockIcon : this.front;
  }
  
  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    super.onBlockPlacedBy(world, x, y, z, entity, stack);
    byte b0 = 3;
    b0 = rotateBlock(b0, entity);
    world.setBlockMetadataWithNotify(x, y, z, b0, 2);
  }
  
  public byte rotateBlock(byte b0, EntityLivingBase entity) {
    if ((entity.rotationYaw >= 135.0F && entity.rotationYaw <= 181.0F) || (entity.rotationYaw <= -135.0F && entity.rotationYaw >= -181.0F)) {
      b0 = 3;
    } else if (entity.rotationYaw > -135.0F && entity.rotationYaw < -45.0F) {
      b0 = 4;
    } else if (entity.rotationYaw >= -45.0F && entity.rotationYaw <= 45.0F) {
      b0 = 2;
    } else if (entity.rotationYaw > 45.0F && entity.rotationYaw < 135.0F) {
      b0 = 5;
    } else if (entity.rotationYaw >= 181.0F) {
      entity.rotationYaw -= 360.0F;
      b0 = rotateBlock(b0, entity);
    } else if (entity.rotationYaw <= -181.0F) {
      entity.rotationYaw += 360.0F;
      b0 = rotateBlock(b0, entity);
    } 
    return b0;
  }
}
