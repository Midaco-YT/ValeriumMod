package fr.valerium.valemod.common.gui;

import fr.valerium.valemod.tiles.TileEntitySeeder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerSeeder extends Container {

    public ContainerSeeder(InventoryPlayer playerInventory, TileEntitySeeder tileEntity) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // Ajoutez un emplacement pour chaque emplacement dans l'inventaire du bloc.
                this.addSlotToContainer(new Slot(tileEntity, col + row * 3, 62 + col * 18, 17 + row * 18));
            }
        }

        for (int playerInventoryRow = 0; playerInventoryRow < 3; playerInventoryRow++) {
            for (int playerInventoryCol = 0; playerInventoryCol < 9; playerInventoryCol++) {
                int playerInventorySlot = playerInventoryCol + playerInventoryRow * 9 + 9;
                this.addSlotToContainer(new Slot(playerInventory, playerInventorySlot, 8 + playerInventoryCol * 18, 84 + playerInventoryRow * 18));
            }
        }

        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
            this.addSlotToContainer(new Slot(playerInventory, hotbarSlot, 8 + hotbarSlot * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        // Vérifiez si le joueur peut interagir avec l'inventaire du bloc.
        return true; // Vous pouvez ajouter des vérifications supplémentaires ici si nécessaire.
    }
}
