package fr.valerium.valemod.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.ModVale;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class RuneCrafter extends Block{

	@SideOnly(Side.CLIENT)
	private IIcon front;
	private IIcon top;
	private IIcon bottom;
	private IIcon side1;
	private IIcon side2;
	private IIcon back;
	
	public RuneCrafter(Material material) {
		super(Material.wood);
		
		this.setCreativeTab(ModVale.tabAlchimist);
		this.setBlockName("rune_crafter");
		this.setBlockTextureName("valerium:rune_crafter");
		this.setHardness(10);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iiconRegister) {
		this.blockIcon = iiconRegister.registerIcon("valerium:rune_crafter/rune_crafter_front");
		this.front = iiconRegister.registerIcon("valerium:rune_crafter/rune_crafter_front");
		this.top = iiconRegister.registerIcon("valerium:rune_crafter/rune_crafter_top2");
		this.bottom = iiconRegister.registerIcon("valerium:rune_crafter/rune_crafter_bottom");
		this.side1 = iiconRegister.registerIcon("valerium:rune_crafter/rune_crafter_side1");
		this.side2 = iiconRegister.registerIcon("valerium:rune_crafter/rune_crafter_side2");
		this.back = iiconRegister.registerIcon("valerium:rune_crafter/rune_crafter_back");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (side == 0)
			return this.bottom;
		else if (side == 1)
			return this.top;
		else if(side == 2) {
			return this.front;
		}
		else if(side == 3) {
			return this.back;
		}
		else if(side == 4) {
			return this.side1;
		}
		else if(side == 5) {
			return this.side2;
		}
		return (side != metadata) ? this.blockIcon : this.front;

	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		byte b0 = 3;
		b0 = rotateBlock(b0, entity);
		world.setBlockMetadataWithNotify(x, y, z, b0, 2);
	}

	public byte rotateBlock(byte b0, EntityLivingBase entity) {
		if ((entity.rotationYaw >= 135.0F && entity.rotationYaw <= 181.0F)
				|| (entity.rotationYaw <= -135.0F && entity.rotationYaw >= -181.0F)) {
			b0 = 3;
		} else if (entity.rotationYaw > -135.0F && entity.rotationYaw < -45.0F) {
			b0 = 4;
		} else if (entity.rotationYaw >= -45.0F && entity.rotationYaw <= 45.0F) {
			b0 = 2;
		} else if (entity.rotationYaw > 45.0F && entity.rotationYaw < 135.0F) {
			b0 = 5;
		} else if (entity.rotationYaw >= 181.0F) {
			entity.rotationYaw -= 360.0F;
			b0 = rotateBlock(b0, entity);
		} else if (entity.rotationYaw <= -181.0F) {
			entity.rotationYaw += 360.0F;
			b0 = rotateBlock(b0, entity);
		}
		return b0;
	}
	
//	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
//			float hitY, float hitZ) {
//		if (!world.isRemote)
//			player.openGui(ModVale.instance, 0, world, x, y, z);
//		return true;
//	}
}
