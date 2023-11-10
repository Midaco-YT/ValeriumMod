package fr.valerium.valemod.world.generation;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenRegister
{
    public static void mainRegsitry()
    {
        registerWorldGen(new OreGen(), 0);
    }
    
    public static void registerWorldGen(IWorldGenerator iGenerator, int modGenerationWeight)
    {
        GameRegistry.registerWorldGenerator(iGenerator, modGenerationWeight);
    }
}