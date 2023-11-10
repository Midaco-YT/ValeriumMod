package fr.valerium.valemod.spells;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class AttractioSpell extends Spell {

	public AttractioSpell(int id, ResourceLocation configFile) {
		super(id, configFile);
	}

	@Override
	public boolean executeSpell(EntityPlayer p, int level) {
		if (!p.worldObj.isRemote) {
			if (level == 1) {
				attractEntities(p.worldObj, p, 10.0, 3);
			} else if (level == 2) {
				attractEntities(p.worldObj, p, 15.0, 5);
			} else if (level == 3) {
				attractEntities(p.worldObj, p, 20.0, 7);
			} else if (level == 4) {
				attractEntities(p.worldObj, p, 30.0, 10);
			}
		}
		return true;
	}

	private void attractEntities(World world, EntityPlayer p, double range, int numberOfEntities) {
		if (!world.isRemote) {

			double playerX = p.posX;
			double playerY = p.posY;
			double playerZ = p.posZ;

			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(playerX - range, playerY - range, playerZ - range,
					playerX + range, playerY + range, playerZ + range);

			List<EntityLivingBase> entities = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, aabb);

			int entitiesAttracted = 0;

			for (Entity entity : entities) {
				if (entitiesAttracted < numberOfEntities) {
					double motionX = playerX - entity.posX;
					double motionY = playerY - entity.posY;
					double motionZ = playerZ - entity.posZ;

					double distance = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);

					motionX /= distance;
					motionY /= distance;
					motionZ /= distance;

					double speed = 0.5;
					entity.motionX = motionX * speed;
					entity.motionY = motionY * speed;
					entity.motionZ = motionZ * speed;

					entitiesAttracted++;
				} else {
					break;
				}
			}
		}
	}
}
