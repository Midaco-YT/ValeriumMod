package fr.valerium.valemod.items.hammers;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.utils.ToolHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemHammerBase extends ItemPickaxe {
  protected static final Material[] MATERIALS = new Material[] { Material.rock, Material.iron, Material.ice, Material.glass, Material.piston, Material.anvil, Material.snow, Material.craftedSnow, Material.clay };
  
  public ItemHammerBase(Item.ToolMaterial material) {
    super(material);
    setCreativeTab(ModVale.tabValerium);
  }
  
  public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
    MovingObjectPosition raycast = ToolHandler.raytraceFromEntity(player.worldObj, (Entity)player, true, 10.0D);
    if (raycast != null) {
      breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.sideHit);
      stack.damageItem(1, (EntityLivingBase)player);
    } 
    return false;
  }
  
  public int getItemEnchantability() {
    return 0;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta) {
    return super.getDigSpeed(stack, block, meta) / 9.0F;
  }
  
  public void breakOtherBlock(EntityPlayer player, ItemStack stack, int x, int y, int z, int originX, int originY, int originZ, int side) {
    World world = player.worldObj;
    Material mat = world.getBlock(x, y, z).getMaterial();
    if (world.isAirBlock(x, y, z))
      return; 
    ForgeDirection direction = ForgeDirection.getOrientation(side);
    int range = Math.max(0, 1);
    int rangeY = Math.max(1, range);
    boolean doX = (direction.offsetX == 0);
    boolean doY = (direction.offsetY == 0);
    boolean doZ = (direction.offsetZ == 0);
    ToolHandler.removeBlocksInIteration(player, stack, world, x, y, z, doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0, doX ? (range + 1) : 1, doY ? (rangeY * 2) : 1, doZ ? (range + 1) : 1, null, MATERIALS, false, 0, true, false);
  }
}
