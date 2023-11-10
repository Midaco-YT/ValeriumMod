package fr.valerium.valemod.blocks.luckyblocks;

import java.util.ArrayList;
import java.util.List;

import fr.valerium.valemod.client.gui.GuiLuckyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LuckyBlockBase extends Block {
    
    public LuckyBlockBase() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            List<ResourceLocation> images = initializeImages(); // Create a list of images for the Lucky Block GUI
            Minecraft.getMinecraft().displayGuiScreen(new GuiLuckyBlock(images));
        }
        
        return true;
    }
    
    private List<ResourceLocation> initializeImages() {
        List<ResourceLocation> images = new ArrayList<>();

        ResourceLocation chestImage = new ResourceLocation("valerium:textures/gui/luckyblock/valeriumevent/chest.png");
        
        return images;
    }
}
