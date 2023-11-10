package fr.valerium.valemod.jobs;

import java.util.HashMap;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.network.packet.PacketJobs;
import fr.valerium.valemod.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class JobManager implements IExtendedEntityProperties {

	public final static String EXT_PROP_JOB = "ExtPropJob";

	private final EntityPlayer player;

	public HashMap<String, Integer> levels;
	public HashMap<String, Integer> experience;
	public HashMap<String, Long> maxExperience;

	public static final int MINER = 1;
	public static final int FARMER = 2;
	public static final int HUNTER = 3;
	public static final int ALCHIMIST = 4;

	public static final String MINER_NAME = "Miner";
	public static final String FARMER_NAME = "Farmer";
	public static final String HUNTER_NAME = "Hunter";
	public static final String ALCHIMIST_NAME = "Alchimist";

	public static final Long MAX_LEVEL = 200000L;

	public JobManager(EntityPlayer player) {
		this.player = player;

		this.levels = new HashMap<String, Integer>();
		this.experience = new HashMap<String, Integer>();
		this.maxExperience = new HashMap<String, Long>();

		this.levels.put(MINER_NAME, 1);
		this.experience.put(MINER_NAME, 10);
		this.maxExperience.put(MINER_NAME, MAX_LEVEL);

		this.levels.put(FARMER_NAME, 1);
		this.experience.put(FARMER_NAME, 0);
		this.maxExperience.put(FARMER_NAME, MAX_LEVEL);

		this.levels.put(HUNTER_NAME, 1);
		this.experience.put(HUNTER_NAME, 0);
		this.maxExperience.put(HUNTER_NAME, MAX_LEVEL);

		this.levels.put(ALCHIMIST_NAME, 1);
		this.experience.put(ALCHIMIST_NAME, 0);
		this.maxExperience.put(ALCHIMIST_NAME, MAX_LEVEL);
	}

	/*
	 * public void sync() { PacketJobs packet = new PacketJobs(levels, experience,
	 * maxExperience); ModVale.network.sendToAll((IMessage) packet); }
	 */

	public final void sync() {
		if (!player.worldObj.isRemote) {
			EntityPlayerMP player1 = (EntityPlayerMP) player;
			ModVale.network.sendTo(new PacketJobs(levels, experience, maxExperience), player1);
		} else {
			ModVale.network.sendToServer(new PacketJobs());
		}
	}

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(JobManager.EXT_PROP_JOB, new JobManager(player));
	}

	public static final JobManager get(EntityPlayer player) {
		return (JobManager) player.getExtendedProperties(EXT_PROP_JOB);

	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
	    NBTTagCompound properties = new NBTTagCompound();

	    for (String job : levels.keySet()) {
	        properties.setInteger(job + "Level", levels.get(job));
	        properties.setInteger(job + "Experience", experience.get(job));
	        properties.setLong(job + "MaxExperience", maxExperience.get(job));
	    }

	    compound.setTag(EXT_PROP_JOB, properties);

	    if (ModVale.network != null) {
	        sync();
	    }
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
	    NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_JOB);

	    for (String job : levels.keySet()) {
	        levels.put(job, properties.getInteger(job + "Level"));
	        experience.put(job, properties.getInteger(job + "Experience"));
	        maxExperience.put(job, properties.getLong(job + "MaxExperience"));
	    }

	    if (ModVale.network != null) {
	        sync();
	    }
	}


	@Override
	public void init(Entity entity, World world) {
	}

	private static String getSaveKey(EntityPlayer player) {
		return player.getDisplayName() + ":" + EXT_PROP_JOB;
	}

	public static void saveProxyData(EntityPlayer player) {
		JobManager playerData = JobManager.get(player);
		NBTTagCompound savedData = new NBTTagCompound();

		playerData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	public static void loadProxyData(EntityPlayer player) {
		JobManager playerData = JobManager.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));

		if (savedData != null) {
			playerData.loadNBTData(savedData);
		}
		playerData.sync();
	}

	public int getLevel(String job) {
		Integer level = levels.get(job);
		return level == null ? 0 : level;
	}

	public void setLevel(String job, int level) {
		levels.put(job, level);
		sync();
	}

	public int getExperience(String job) {
		Integer exp = experience.get(job);
		return exp == null ? 0 : exp;
	}

	public void setExperience(String job, int exp) {
		experience.put(job, exp);
		sync();
	}

	public long getMaxExperience(String job) {
		Long maxExp = maxExperience.get(job);
		return maxExp == null ? 0 : maxExp;
	}

	public void setMaxExperience(String job, long maxExp) {
		maxExperience.put(job, maxExp);
		sync();
	}




	public void addExperience(String job, int exp) {
	    // V�rifier que le job existe
	    if (!levels.containsKey(job)) {
	        return;
	    }

	    // V�rifier si le joueur poss�de l'avantage double XP
	    boolean hasDoubleXp = player.getEntityData().getBoolean("has_double_xp");

	    // Calculer l'exp�rience � ajouter
	    int xpToAdd = exp;

	    // Si le joueur poss�de l'avantage double XP, doubler l'exp�rience
	    if (hasDoubleXp) {
	        xpToAdd *= 2;
	    }

	    // Ajouter l'exp�rience
	    int currentExp = getExperience(job);
	    int newExp = currentExp + xpToAdd;
	    experience.put(job, newExp);

	    // V�rifier si le joueur a atteint le niveau maximum
	    if (newExp >= maxExperience.get(job)) {
	        int currentLevel = getLevel(job);

	        // Si le joueur a atteint le niveau maximum, ne pas ajouter d'exp�rience suppl�mentaire
	        if (currentLevel >= MAX_LEVEL) {
	            return;
	        }

	        // Augmenter le niveau du joueur
	        levels.put(job, currentLevel + 1);

	        // Calculer la nouvelle exp�rience maximale
	        long newMaxExp = Math.round(maxExperience.get(job) * 1.5);
	        maxExperience.put(job, newMaxExp);

	        // Donner une r�compense au joueur pour son passage de niveau (� impl�menter)
	        // giveReward(player, currentLevel + 1, job);

	    }

	    sync();
	}
	
}
