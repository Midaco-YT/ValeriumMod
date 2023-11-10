package fr.valerium.valemod.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityAzuriteFurnace extends TileEntity implements IInventory, ISidedInventory {
  private ItemStack[] content = new ItemStack[4];
  
  public int timeNeeded = 200;
  
  public int workedTime;
  
  public int timeNeededBase = 200;
  
  public int furnaceBurnTime;
  
  public int currentItemBurnTime;
  
  public int facing;
  
  public int getSizeInventory() {
    return this.content.length;
  }
  
  public ItemStack getStackInSlot(int slot) {
    return this.content[slot];
  }
  
  public ItemStack decrStackSize(int slotIndex, int amount) {
    if (this.content[slotIndex] != null) {
      if ((this.content[slotIndex]).stackSize <= amount) {
        ItemStack itemStack = this.content[slotIndex];
        this.content[slotIndex] = null;
        markDirty();
        return itemStack;
      } 
      ItemStack itemstack = this.content[slotIndex].splitStack(amount);
      if ((this.content[slotIndex]).stackSize == 0)
        this.content[slotIndex] = null; 
      markDirty();
      return itemstack;
    } 
    return null;
  }
  
  public ItemStack getStackInSlotOnClosing(int slotIndex) {
    if (this.content[slotIndex] != null) {
      ItemStack itemstack = this.content[slotIndex];
      this.content[slotIndex] = null;
      return itemstack;
    } 
    return null;
  }
  
  public void setInventorySlotContents(int slotIndex, ItemStack stack) {
    this.content[slotIndex] = stack;
    if (stack != null && stack.stackSize > getInventoryStackLimit())
      stack.stackSize = getInventoryStackLimit(); 
    markDirty();
  }
  
  public String getInventoryName() {
    return "TileEntity.AzuriteFurnace";
  }
  
  public boolean hasCustomInventoryName() {
    return false;
  }
  
  public int getInventoryStackLimit() {
    return 64;
  }
  
  public boolean isUseableByPlayer(EntityPlayer player) {
    return (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false : (
      (player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
  }
  
  public void openInventory() {}
  
  public void closeInventory() {}
  
  @SideOnly(Side.CLIENT)
  public int getCookProgress() {
    return this.workedTime * 16 / this.timeNeeded;
  }
  
  public boolean isItemValidForSlot(int slot, ItemStack stack) {
    return (slot == 2) ? false : ((slot == 2) ? TileEntityFurnace.isItemFuel(stack) : true);
  }
  
  public void writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.content.length; i++) {
      if (this.content[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setByte("Slot", (byte)i);
        this.content[i].writeToNBT(nbttagcompound1);
        nbttaglist.appendTag((NBTBase)nbttagcompound1);
      } 
    } 
    compound.setTag("Items", (NBTBase)nbttaglist);
    compound.setShort("workingTime", (short)this.workedTime);
    compound.setShort("workingTimeNeeded", (short)this.timeNeeded);
  }
  
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    NBTTagList nbttaglist = compound.getTagList("Items", 10);
    this.content = new ItemStack[getSizeInventory()];
    for (int i = 0; i < nbttaglist.tagCount(); i++) {
      NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
      int j = nbttagcompound1.getByte("Slot") & 0xFF;
      if (j >= 0 && j < this.content.length)
        this.content[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1); 
    } 
    this.workedTime = compound.getShort("workingTime");
    this.timeNeeded = compound.getShort("workingTimeNeeded");
  }
  
  public void updateEntity() {
    int modifier;
    boolean flag = (this.furnaceBurnTime > 0);
    boolean flag1 = false;
    if (this.content[0] != null && (this.content[0]).stackSize > 0) {
      modifier = (this.content[0]).stackSize;
    } else {
      modifier = 1;
    } 
    if (this.furnaceBurnTime > 0)
      this.furnaceBurnTime -= modifier; 
    this.timeNeeded = this.timeNeededBase / modifier;
    if ((this.furnaceBurnTime != 0 || (this.content[1] != null && this.content[2] != null)) && 
      this.furnaceBurnTime <= 0 && canSmelt()) {
      this.currentItemBurnTime = this.furnaceBurnTime = TileEntityFurnace.getItemBurnTime(this.content[2]);
      if (this.furnaceBurnTime > 0) {
        flag1 = true;
        (this.content[2]).stackSize--;
        if ((this.content[2]).stackSize <= 0)
          this.content[2] = null; 
      } 
    } 
    if (!isBurning() && canSmelt())
      this.workedTime = 1; 
    if (isBurning() && canSmelt() && this.furnaceBurnTime != 0) {
      if (this.workedTime >= this.timeNeeded) {
        this.workedTime = 0;
        smeltItem();
        flag1 = true;
      } 
    } else {
      this.workedTime = 0;
    } 
    if (flag1) {
    	markDirty();
      this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public int getBurnTimeRemainingScaled(int value) {
    if (this.currentItemBurnTime == 0)
      this.currentItemBurnTime = 200; 
    return this.furnaceBurnTime * value / this.currentItemBurnTime;
  }
  
  @SideOnly(Side.CLIENT)
  public int getCookProgressScaled(int value) {
    return this.workedTime * value / this.timeNeeded;
  }
  
  public void smeltItem() {
    if (canSmelt()) {
      ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.content[1]);
      if (this.content[3] == null) {
        this.content[3] = itemstack.copy();
      } else if (this.content[3].getItem() == itemstack.getItem()) {
        (this.content[3]).stackSize += itemstack.stackSize;
      } 
      (this.content[1]).stackSize--;
      if ((this.content[1]).stackSize <= 0)
        this.content[1] = null; 
    } 
  }
  
  public boolean isBurning() {
    return (this.workedTime > 0);
  }
  
  private boolean canSmelt() {
    if (this.content[1] == null)
      return false; 
    ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.content[1]);
    if (itemstack == null)
      return false; 
    if (this.content[3] == null)
      return true; 
    if (!this.content[3].isItemEqual(itemstack))
      return false; 
    int result = (this.content[3]).stackSize + itemstack.stackSize;
    return (result <= getInventoryStackLimit() && result <= this.content[3].getMaxStackSize());
  }
  
  public int[] getAccessibleSlotsFromSide(int side) {
    return new int[0];
  }
  
  public boolean canInsertItem(int side, ItemStack stack, int slot) {
    boolean flag = false;
    if (side == 1 && 
      FurnaceRecipes.smelting().getSmeltingResult(stack) != null)
      flag = true; 
    if (side >= 2 && 
      TileEntityFurnace.isItemFuel(stack))
      flag = true; 
    return flag;
  }
  
  public boolean canExtractItem(int side, ItemStack stack, int slot) {
    return false;
  }
}
