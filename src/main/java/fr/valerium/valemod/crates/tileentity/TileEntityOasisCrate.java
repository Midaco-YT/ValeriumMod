package fr.valerium.valemod.crates.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOasisCrate extends TileEntity {

	public byte direction;

	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);
		direction = nbtTag.getByte("direction");
	}

	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);
		for (int i = 0; i < 5; i++) {
			nbtTag.setByte("direction", direction);
		}
	}

	public void setDirection(byte direct) {
		direction = direct;
	}

	public byte getDirection() {
		return direction;
	}

	public Packet getDescriptionPacket() {
	    NBTTagCompound nbttagcompound = new NBTTagCompound();
	    this.writeToNBT(nbttagcompound);
	    return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 4, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
	    this.readFromNBT(pkt.func_148857_g());
	}

}
