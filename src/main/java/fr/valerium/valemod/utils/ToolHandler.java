package fr.valerium.valemod.utils;

import java.util.ArrayList;
import java.util.List;

import fr.valerium.valemod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ToolHandler {
  protected static ThreadLocal<Boolean> captureDrops = new ThreadLocal<Boolean>() {
      protected Boolean initialValue() {
        return Boolean.valueOf(false);
      }
    };
  
  protected static ThreadLocal<List<ItemStack>> capturedDrops = new ThreadLocal<List<ItemStack>>() {
      protected List<ItemStack> initialValue() {
        return new ArrayList<ItemStack>();
      }
    };
  
  public static Block[] minerals = new Block[] { 
      (Block)ModBlocks.sulfur_ore, Blocks.coal_ore, Blocks.redstone_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.quartz_ore, Blocks.iron_ore, Blocks.lapis_ore, (Block)ModBlocks.valerium_ore, (Block)ModBlocks.titane_ore, 
      (Block)ModBlocks.amethyst_ore, (Block)ModBlocks.azurite_ore, Blocks.gold_ore, Blocks.lit_redstone_ore };
  
  public static void removeBlocksInIteration(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, Material[] materialsListing, boolean smelt, int fortune, boolean dispose, boolean obsidian) {
    float blockHardness = (block == null) ? 1.0F : block.getBlockHardness(world, x, y, z);
    for (int x1 = xs; x1 < xe; x1++) {
      for (int y1 = ys; y1 < ye; y1++) {
        for (int z1 = zs; z1 < ze; z1++) {
          if (world.getBlock(x1 + x, y1 + y, z1 + z).isToolEffective("pickaxe", 3) || world.getBlock(x1 + x, y1 + y, z1 + z) instanceof net.minecraft.block.BlockRedstoneOre || (obsidian && world.getBlock(x1 + x, y1 + y, z1 + z) instanceof net.minecraft.block.BlockObsidian))
            removeBlockWithDrops(player, stack, world, x1 + x, y1 + y, z1 + z, x, y, z, world.getBlock(x1 + x, y1 + y, z1 + z), materialsListing, smelt, fortune, blockHardness, dispose, obsidian); 
        } 
      } 
    } 
  }
  
  public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean smelt, int fortune, float blockHardness, boolean dispose, boolean obsidian) {
    removeBlockWithDrops(player, stack, world, x, y, z, bx, by, bz, block, materialsListing, smelt, fortune, blockHardness, dispose, true, obsidian);
  }
  
  public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean smelt, int fortune, float blockHardness, boolean dispose, boolean particles, boolean obsidian) {
    if (!world.blockExists(x, y, z))
      return; 
    Block blk = world.getBlock(x, y, z);
    int meta = world.getBlockMetadata(x, y, z);
    if (block != null && blk != block)
      return; 
    Material mat = world.getBlock(x, y, z).getMaterial();
    boolean flag = false;
    if (!world.isRemote && blk != null && !blk.isAir((IBlockAccess)world, x, y, z) && blk
      .getPlayerRelativeBlockHardness(player, world, x, y, z) > 0.0F) {
      if (!blk.canHarvestBlock(player, meta) || !isRightMaterial(blk, materialsListing) || (blk == Blocks.obsidian && !obsidian))
        return; 
      if (!player.capabilities.isCreativeMode) {
        int localMeta = world.getBlockMetadata(x, y, z);
        blk.onBlockHarvested(world, x, y, z, localMeta, player);
        if (blk.removedByPlayer(world, player, x, y, z, true))
          blk.onBlockDestroyedByPlayer(world, x, y, z, localMeta); 
        if (smelt) {
          ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(blk
              .getDrops(world, x, y, z, blk.getDamageValue(world, x, y, z), fortune).get(0));
          if (result == null) {
            blk.dropBlockAsItem(world, x, y, z, localMeta, fortune);
          } else {
            flag = true;
            ItemStack resultCopy = result.copy();
            float count = 1.0F;
            if (fortune > 0 && isMineral(block))
              count = (1 + (fortune - world.rand.nextInt(fortune)) / 2); 
            if (count <= 0.0F)
              count = 1.0F; 
            resultCopy.stackSize = (int)count;
            EntityItem entityItem = new EntityItem(world, x, y, z, resultCopy);
            world.spawnEntityInWorld((Entity)entityItem);
          } 
        } else {
          blk.dropBlockAsItem(world, x, y, z, localMeta, fortune);
        } 
      } else {
        int localMeta = world.getBlockMetadata(x, y, z);
        blk.dropBlockAsItem(world, x, y, z, localMeta, fortune);
      } 
      int xpDrop = blk.getExpDrop((IBlockAccess)world, blk.getDamageValue(world, x, y, z), fortune);
      EntityXPOrb xp = new EntityXPOrb(world, x, y, z, xpDrop);
      if (xpDrop > 0)
        world.spawnEntityInWorld((Entity)xp); 
    } else {
      world.setBlockToAir(x, y, z);
    } 
    world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(blk) + (meta << 12));
    world.setBlockToAir(x, y, z);
    if (flag) {
      world.spawnParticle("flame", x + 0.5D + 0.5D * world.rand.nextDouble(), y - 0.1D, z + 0.5D * world.rand
          .nextDouble(), 0.0D, 0.0D, 0.0D);
      world.spawnParticle("flame", x + 0.5D + 0.5D * world.rand.nextDouble(), y - 0.1D, z + 0.5D * world.rand
          .nextDouble(), 0.0D, 0.0D, 0.0D);
      world.spawnParticle("smoke", x + 0.5D + 0.5D * world.rand.nextDouble(), y - 0.1D, z + 0.5D * world.rand
          .nextDouble(), 0.0D, 0.0D, 0.0D);
    } 
  }
  
  public static boolean isMineral(Block block) {
    for (Block b : minerals) {
      if (b == block)
        return true; 
    } 
    return false;
  }
  
  public static boolean isRightMaterial(Block block, Material[] materialsListing) {
    Material material = block.getMaterial();
    for (Material mat : materialsListing) {
      if (material == mat)
        return true; 
    } 
    return false;
  }
  
  public static boolean isRightBlock(Block block, Block[] list) {
    for (Block blk : list) {
      if (block == blk)
        return false; 
    } 
    return true;
  }
  
  public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
    float f = 1.0F;
    float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
    float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
    double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
    double d1 = player.prevPosY + (player.posY - player.prevPosY) * f;
    if (!world.isRemote && player instanceof EntityPlayer)
      d1 += ((EntityPlayer)player).eyeHeight; 
    double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
    Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
    float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
    float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
    float f5 = -MathHelper.cos(-f1 * 0.017453292F);
    float f6 = MathHelper.sin(-f1 * 0.017453292F);
    float f7 = f4 * f5;
    float f8 = f3 * f5;
    double d3 = range;
    Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
    return world.rayTraceBlocks(vec3, vec31, par3);
  }
}
