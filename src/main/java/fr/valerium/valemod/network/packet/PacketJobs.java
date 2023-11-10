package fr.valerium.valemod.network.packet;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.valerium.valemod.jobs.JobManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketJobs implements IMessage {
	public HashMap<String, Integer> levels;
    public HashMap<String, Integer> experience;
    public HashMap<String, Long> maxExperience;

    public PacketJobs() {
    }

    public PacketJobs(HashMap<String, Integer> levels, HashMap<String, Integer> experience, HashMap<String, Long> maxExperience) {
        this.levels = levels;
        this.experience = experience;
        this.maxExperience = maxExperience;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        levels = new HashMap<>();
        experience = new HashMap<>();
        maxExperience = new HashMap<>();

        if (buf.readBoolean()) {
            int levelsSize = buf.readInt();
            for (int i = 0; i < levelsSize; i++) {
                String key = ByteBufUtils.readUTF8String(buf);
                int value = buf.readInt();
                levels.put(key, value);
            }
        }

        if (buf.readBoolean()) {
            int experienceSize = buf.readInt();
            for (int i = 0; i < experienceSize; i++) {
                String key = ByteBufUtils.readUTF8String(buf);
                int value = buf.readInt();
                experience.put(key, value);
            }
        }

        if (buf.readBoolean()) {
            int maxExperienceSize = buf.readInt();
            for (int i = 0; i < maxExperienceSize; i++) {
                String key = ByteBufUtils.readUTF8String(buf);
                long value = buf.readLong();
                maxExperience.put(key, value);
            }
        }
        
        System.out.println("Levels: " + levels);
        System.out.println("Experience: " + experience);
        System.out.println("MaxExperience: " + maxExperience);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(levels != null);
        if (levels != null) {
            buf.writeInt(levels.size());
            for (Map.Entry<String, Integer> entry : levels.entrySet()) {
                ByteBufUtils.writeUTF8String(buf, entry.getKey());
                buf.writeInt(entry.getValue());
            }
        }

        buf.writeBoolean(experience != null);
        if (experience != null) {
            buf.writeInt(experience.size());
            for (Map.Entry<String, Integer> entry : experience.entrySet()) {
                ByteBufUtils.writeUTF8String(buf, entry.getKey());
                buf.writeInt(entry.getValue());
            }
        }

        buf.writeBoolean(maxExperience != null);
        if (maxExperience != null) {
            buf.writeInt(maxExperience.size());
            for (Map.Entry<String, Long> entry : maxExperience.entrySet()) {
                ByteBufUtils.writeUTF8String(buf, entry.getKey());
                buf.writeLong(entry.getValue());
            }
        }
    }
    
    public void encode(ByteBuf buf) {
        buf.writeBoolean(levels != null);
        if (levels != null) {
            buf.writeInt(levels.size());
            for (Map.Entry<String, Integer> entry : levels.entrySet()) {
                ByteBufUtils.writeUTF8String(buf, entry.getKey());
                buf.writeInt(entry.getValue());
            }
        }

        buf.writeBoolean(experience != null);
        if (experience != null) {
            buf.writeInt(experience.size());
            for (Map.Entry<String, Integer> entry : experience.entrySet()) {
                ByteBufUtils.writeUTF8String(buf, entry.getKey());
                buf.writeInt(entry.getValue());
            }
        }

        buf.writeBoolean(maxExperience != null);
        if (maxExperience != null) {
            buf.writeInt(maxExperience.size());
            for (Map.Entry<String, Long> entry : maxExperience.entrySet()) {
                ByteBufUtils.writeUTF8String(buf, entry.getKey());
                buf.writeLong(entry.getValue());
            }
        }
    }

    public void decode(ByteBuf buf) {
        levels = new HashMap<>();
        experience = new HashMap<>();
        maxExperience = new HashMap<>();

        if (buf.readBoolean()) {
            int levelsSize = buf.readInt();
            for (int i = 0; i < levelsSize; i++) {
                String key = ByteBufUtils.readUTF8String(buf);
                int value = buf.readInt();
                levels.put(key, value);
            }
        }

        if (buf.readBoolean()) {
            int experienceSize = buf.readInt();
            for (int i = 0; i < experienceSize; i++) {
                String key = ByteBufUtils.readUTF8String(buf);
                int value = buf.readInt();
                experience.put(key, value);
            }
        }

        if (buf.readBoolean()) {
            int maxExperienceSize = buf.readInt();
            for (int i = 0; i < maxExperienceSize; i++) {
                String key = ByteBufUtils.readUTF8String(buf);
                long value = buf.readLong();
                maxExperience.put(key, value);
            }
        }
    }



    public void handleClientSide(EntityPlayer player)
    {
    	JobManager jobManager = JobManager.get(player);	
    	jobManager.levels = this.levels;
    	jobManager.experience = this.experience;
    	jobManager.maxExperience = this.maxExperience;

    }

 
    public void handleServerSide(EntityPlayer player) {
    	
    }
}
