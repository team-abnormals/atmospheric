package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.world.biome.dunes.DunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.FlourishingDunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.PetrifiedDunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.RockyDunesBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodForestBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodForestPlateauBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodMountainsBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodPlateauBiome;
import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = "atmospheric", bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericBiomes {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Atmospheric.MODID);
	
    public static RegistryObject<Biome> ROSEWOOD_FOREST 		= BIOMES.register("rosewood_forest", () -> new RosewoodForestBiome());
    public static RegistryObject<Biome> ROSEWOOD_MOUNTAINS 		= BIOMES.register("rosewood_mountains", () -> new RosewoodMountainsBiome());
    public static RegistryObject<Biome> ROSEWOOD_PLATEAU 		= BIOMES.register("rosewood_plateau", () -> new RosewoodPlateauBiome());
    public static RegistryObject<Biome> ROSEWOOD_FOREST_PLATEAU = BIOMES.register("rosewood_forest_plateau", () -> new RosewoodForestPlateauBiome());
    
	public static RegistryObject<Biome> DUNES 				= BIOMES.register("dunes", 				() -> new DunesBiome());
    public static RegistryObject<Biome> ROCKY_DUNES 		= BIOMES.register("rocky_dunes",		() -> new RockyDunesBiome());
	public static RegistryObject<Biome> PETRIFIED_DUNES 	= BIOMES.register("petrified_dunes",	() -> new PetrifiedDunesBiome());
	public static RegistryObject<Biome> FLOURISHING_DUNES	= BIOMES.register("flourishing_dunes",	() -> new FlourishingDunesBiome());
    
    public static void registerBiomesToDictionary() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_FOREST.get(),1));
        BiomeDictionary.addTypes(ROSEWOOD_FOREST.get(), 
        		BiomeDictionary.Type.RARE, 
        		BiomeDictionary.Type.JUNGLE,  
        		BiomeDictionary.Type.FOREST, 
        		BiomeDictionary.Type.OVERWORLD);
        
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_MOUNTAINS.get(), 1));
        BiomeDictionary.addTypes(ROSEWOOD_MOUNTAINS.get(), 
        		BiomeDictionary.Type.RARE, 
        		BiomeDictionary.Type.JUNGLE, 
        		BiomeDictionary.Type.FOREST, 
        		BiomeDictionary.Type.MOUNTAIN, 
        		BiomeDictionary.Type.OVERWORLD);
       
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_PLATEAU.get(), 1));
        BiomeDictionary.addTypes(ROSEWOOD_PLATEAU.get(), 
        		BiomeDictionary.Type.RARE, 
        		BiomeDictionary.Type.JUNGLE, 
        		BiomeDictionary.Type.PLATEAU, 
        		BiomeDictionary.Type.PLAINS, 
        		BiomeDictionary.Type.OVERWORLD);
       
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_FOREST_PLATEAU.get(), 1));
        BiomeDictionary.addTypes(ROSEWOOD_FOREST_PLATEAU.get(), 
        		BiomeDictionary.Type.RARE, 
        		BiomeDictionary.Type.JUNGLE, 
        		BiomeDictionary.Type.FOREST, 
        		BiomeDictionary.Type.PLATEAU, 
        		BiomeDictionary.Type.PLAINS, 
        		BiomeDictionary.Type.OVERWORLD);
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(DUNES.get(), 1));
        BiomeDictionary.addTypes(DUNES.get(), 
        		BiomeDictionary.Type.DRY, 
        		BiomeDictionary.Type.HOT, 
        		BiomeDictionary.Type.WASTELAND,
        		BiomeDictionary.Type.OVERWORLD);
        
        //BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(ROCKY_DUNES.get(), 1));
        BiomeDictionary.addTypes(ROCKY_DUNES.get(), 
        		BiomeDictionary.Type.DRY, 
        		BiomeDictionary.Type.HOT,
        		BiomeDictionary.Type.WASTELAND,
        		BiomeDictionary.Type.OVERWORLD);
        
        //BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(PETRIFIED_DUNES.get(), 1));
        BiomeDictionary.addTypes(PETRIFIED_DUNES.get(), 
        		BiomeDictionary.Type.DRY, 
        		BiomeDictionary.Type.HOT, 
        		BiomeDictionary.Type.WASTELAND,
        		BiomeDictionary.Type.OVERWORLD);
        
        //BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(FLOURISHING_DUNES.get(), 1));
        BiomeDictionary.addTypes(FLOURISHING_DUNES.get(), 
        		BiomeDictionary.Type.DRY, 
        		BiomeDictionary.Type.HOT, 
        		BiomeDictionary.Type.LUSH,
        		BiomeDictionary.Type.OVERWORLD);
    }
}
