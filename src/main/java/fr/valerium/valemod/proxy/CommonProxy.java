package fr.valerium.valemod.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;

public class CommonProxy {

	private static final Map <String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
	
	public void registerRenders() {
		
	}

	public static void storeEntityData(String saveKey, NBTTagCompound savedData) {
		extendedEntityData.put(saveKey, savedData);
		
	}

	public static NBTTagCompound getEntityData(String saveKey) {
		 return extendedEntityData.remove(saveKey);
	}

	public void registerOverlays() {
		// TODO Auto-generated method stub
		
	}

	public void registerTileEntityRender() {
		// TODO Auto-generated method stub
		
	}
}

