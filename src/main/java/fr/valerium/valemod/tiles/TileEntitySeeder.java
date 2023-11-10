package fr.valerium.valemod.tiles;

import fr.valerium.valemod.blocks.ModBlocks;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySeeder extends TileEntity implements IInventory {

    private static final int INVENTORY_SIZE = 9; // Taille de l'inventaire (3x3)
    private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];
    private String customName;
    private boolean isPlantingEnabled = false;

    public void setPlantingEnabled(boolean enabled) {
        this.isPlantingEnabled = enabled;
    }

    public boolean isPlantingEnabled() {
        return isPlantingEnabled;
    }

    @Override
    public int getSizeInventory() {
        return INVENTORY_SIZE;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount) {
        if (inventory[slotIndex] != null) {
            if (inventory[slotIndex].stackSize <= amount) {
                ItemStack stack = inventory[slotIndex];
                inventory[slotIndex] = null;
                markDirty();
                return stack;
            } else {
                ItemStack stack = inventory[slotIndex].splitStack(amount);
                if (inventory[slotIndex].stackSize == 0) {
                    inventory[slotIndex] = null;
                }
                markDirty();
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        if (inventory[slotIndex] != null) {
            ItemStack stack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return stack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack stack) {
        inventory[slotIndex] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return hasCustomInventoryName() ? customName : "Container.Seeder";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return customName != null && !customName.isEmpty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack stack) {
        // Votre logique pour vérifier si l'élément est valide pour l'emplacement.
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        // Écrivez les données personnalisées de votre TileEntity dans le tag NBT.
        NBTTagCompound inventoryTag = new NBTTagCompound();
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (inventory[i] != null) {
                NBTTagCompound slotTag = new NBTTagCompound();
                slotTag.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(slotTag);
                inventoryTag.setTag("Slot_" + i, slotTag);
            }
        }
        tag.setTag("Inventory", inventoryTag);

        if (hasCustomInventoryName()) {
            tag.setString("CustomName", customName);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        // Lisez les données personnalisées de votre TileEntity depuis le tag NBT.
        NBTTagCompound inventoryTag = tag.getCompoundTag("Inventory");
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (inventoryTag.hasKey("Slot_" + i)) {
                NBTTagCompound slotTag = inventoryTag.getCompoundTag("Slot_" + i);
                inventory[i] = ItemStack.loadItemStackFromNBT(slotTag);
            }
        }

        if (tag.hasKey("Seeder")) {
            customName = tag.getString("Seeder");
        }
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            return; // Ne rien faire sur le côté client.
        }

        // Vérifiez si le bloc est alimenté par un courant de redstone.
        boolean isPowered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);

        if (isPowered && isPlantingEnabled) {
            for (int xOffset = -4; xOffset <= 4; xOffset++) {
                for (int zOffset = -4; zOffset <= 4; zOffset++) {
                    int targetX = xCoord + xOffset;
                    int targetY = yCoord - 1;
                    int targetZ = zCoord + zOffset;

                    // Vérifiez si le bloc en dessous est de la terre (farmland) ou un autre bloc personnalisé.
                    if (isFarmlandOrCustomBlock(targetX, targetY, targetZ)) {
                        // Vérifiez s'il y a des graines dans l'inventaire.
                        int seedSlot = findSeedSlot();
                        if (seedSlot != -1) {
                            ItemStack seedStack = getStackInSlot(seedSlot);

                            // Plantez les graines en utilisant votre logique spécifique.
                            if (plantSeeds(targetX, targetY, targetZ, seedStack)) {
                                // Réduisez la taille de la pile de graines.
                                decrStackSize(seedSlot, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    private int findSeedSlot() {
        // Parcourez l'inventaire pour trouver l'emplacement contenant des graines.
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (stack != null && isSeed(stack)) {
                return i;
            }
        }
        return -1; // Aucun emplacement contenant des graines n'a été trouvé.
    }

    private boolean isSeed(ItemStack stack) {
        // Vérifiez ici si l'élément de l'inventaire est une graine.
        // Vous pouvez utiliser stack.getItem() pour obtenir l'objet de l'élément et le comparer à vos types de graines.
        // Par exemple :
        return stack.getItem() == Items.wheat_seeds;
    }

    private boolean plantSeeds(int x, int y, int z, ItemStack seedStack) {
        // Vérifiez si le bloc à la position (x, y, z) est remplaçable (par exemple, de l'air).
        if (worldObj.getBlock(x, y, z).isReplaceable(worldObj, x, y, z)) {
            // Vérifiez si les graines sont des graines de blé.
            if (isWheatSeeds(seedStack)) {
                // Vérifiez si le bloc en dessous est de la terre labourée (farmland) ou un bloc personnalisé.
                if (isFarmlandOrCustomBlock(x, y - 1, z)) {
                    // Plantez les graines sur la terre labourée ou le bloc personnalisé.
                    worldObj.setBlock(x, y, z, Blocks.wheat); // Remplacez Blocks.wheat par votre propre bloc de culture.
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isWheatSeeds(ItemStack stack) {
        // Vérifiez si l'élément de l'inventaire est une graine de blé.
        return stack != null && stack.getItem() == Items.wheat_seeds; // Utilisez Items.wheat_seeds pour les graines de blé.
    }

    private boolean isFarmlandOrCustomBlock(int x, int y, int z) {
        // Vérifiez si le bloc à la position (x, y, z) est de la terre labourée (farmland) ou un autre bloc que vous avez créé.
        return worldObj.getBlock(x, y, z) instanceof BlockFarmland || worldObj.getBlock(x, y, z) == ModBlocks.fertilized_dirt;
    }
}
