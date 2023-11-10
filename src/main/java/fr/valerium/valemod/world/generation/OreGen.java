package fr.valerium.valemod.world.generation;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import fr.valerium.valemod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class OreGen implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.dimensionId)
        {
            case -1:
                GenerateNether(world, chunkX * 16, chunkZ * 16, random);
                break;
         
            case 0:
                GenerateOverWorld(world, chunkX * 16, chunkZ * 16, random);
                break;

            case 1:
                GenerateEnd(world, chunkX * 16, chunkZ * 16, random);
                break;
        }
    }
    
    private void addOre(Block block, Block blockSpawn, Random random, World world, int posX, int posZ, int minY, int maxY, int minV, int maxV, int spawnChance)
    {
        for(int i = 0; i < spawnChance; i++)
        {
            int chunkSize = 16;
            int Xpos = posX + random.nextInt(chunkSize);
            int Ypos = minY + random.nextInt(maxY - minY);
            int Zpos = posZ + random.nextInt(chunkSize);
            
            new WorldGenMinable(block, maxV, blockSpawn).generate(world, random, Xpos, Ypos, Zpos);
        }
    }
    
    private void addStructure(String string, Random random, World world, int posX, int posZ, int minY, int maxY, int spawnChance)
    {
        for(int i = 0; i < spawnChance ; i++)
        {
            int chunkSize = 16;
            int Xpos = posX + random.nextInt(chunkSize);
            int Ypos = minY + random.nextInt(maxY - minY);
            int Zpos = posZ + random.nextInt(chunkSize);
            
        }
    }

    private void GenerateNether(World world, int i, int j, Random random)
    {

    }

    private void GenerateOverWorld(World world, int i, int j, Random random)
    {
    	addOre(ModBlocks.valerium_ore, Blocks.stone, random, world, i, j, 2, 15, 1, 5, 1);
    	addOre(ModBlocks.azurite_ore, Blocks.stone, random, world, i, j, 2, 10, 1, 2, 1);
    	addOre(ModBlocks.titane_ore, Blocks.stone, random, world, i, j, 2, 20, 1, 5, 5);
    	addOre(ModBlocks.amethyst_ore, Blocks.stone, random, world, i, j, 2, 25, 1, 7, 7);
    	addOre(ModBlocks.sulfur_ore, Blocks.stone, random, world, i, j, 2, 10, 1, 1, 3);
    }

    private void GenerateEnd(World world, int i, int j, Random random)
    {
        
    }
}