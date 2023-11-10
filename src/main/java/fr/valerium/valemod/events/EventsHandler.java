package fr.valerium.valemod.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valerium.valemod.jobs.JobManager;
import fr.valerium.valemod.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventsHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && JobManager.get((EntityPlayer) event.entity) == null) {
			JobManager.register((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			NBTTagCompound jobData = new NBTTagCompound();
			((JobManager) (event.entity.getExtendedProperties(JobManager.EXT_PROP_JOB))).saveNBTData(jobData);
			CommonProxy.storeEntityData(((EntityPlayer) event.entity).getDisplayName() + "_jobs", jobData);
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			NBTTagCompound jobData = CommonProxy
					.getEntityData(((EntityPlayer) event.entity).getDisplayName() + "_jobs");
			if (jobData != null) {
				((JobManager) event.entity.getExtendedProperties(JobManager.EXT_PROP_JOB)).loadNBTData(jobData);
			}
		}
	}

}
