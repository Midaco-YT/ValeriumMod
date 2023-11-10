package fr.valerium.valemod.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.tiles.TileEntityMineralExtractor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MineralExtractorBlock extends BlockContainer {

	private IIcon front;
	private IIcon top;
	private IIcon bottom;
	
    public MineralExtractorBlock() {
        super(Material.rock);
        setBlockName("mineral_extractor");
        setBlockTextureName("valerium:mineral_extractor");
        setCreativeTab(ModVale.tabMiner);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityMineralExtractor();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (!world.isRemote)
			player.openGui(ModVale.instance, 10, world, x, y, z);
		return true;
	}
    
    @SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iiconRegister) {
		this.blockIcon = iiconRegister.registerIcon("valerium:mineral_extractor_side");
		this.front = iiconRegister.registerIcon("valerium:mineral_extractor_front_on");
		this.top = iiconRegister.registerIcon("valerium:mineral_extractor_top");
		this.bottom = iiconRegister.registerIcon("valerium:mineral_extractor_top");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (side == 0)
			return this.bottom;
		else if (side == 1)
			return this.top;
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
}