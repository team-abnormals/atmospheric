package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.world.biome.AtmosphericBiomeBuilders;
import com.bagel.atmospheric.common.world.biome.dunes.DunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.DunesHillsBiome;
import com.bagel.atmospheric.common.world.biome.dunes.FlourishingDunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.PetrifiedDunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.RockyDunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.RockyDunesHillsBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodForestBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodForestPlateauBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodMountainsBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodPlateauBiome;
import com.bagel.atmospheric.core.Atmospheric;
import com.bagel.atmospheric.core.other.AtmosphericConfig;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericBiomes {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Atmospheric.MODID);
	
    public static final RegistryObject<Biome> ROSEWOOD_FOREST 			= BIOMES.register("rosewood_forest", () -> new RosewoodForestBiome(AtmosphericBiomeBuilders.RAINFOREST));
    public static final RegistryObject<Biome> ROSEWOOD_MOUNTAINS 		= BIOMES.register("rosewood_mountains", () -> new RosewoodMountainsBiome(AtmosphericBiomeBuilders.RAINFOREST_MOUNTAINS));
    public static final RegistryObject<Biome> ROSEWOOD_PLATEAU 			= BIOMES.register("rosewood_plateau", () -> new RosewoodPlateauBiome(AtmosphericBiomeBuilders.RAINFOREST_PLATEAU));
    public static final RegistryObject<Biome> ROSEWOOD_FOREST_PLATEAU 	= BIOMES.register("rosewood_forest_plateau", () -> new RosewoodForestPlateauBiome(AtmosphericBiomeBuilders.RAINFOREST_PLATEAU));
    
	public static final RegistryObject<Biome> DUNES 			= BIOMES.register("dunes", 				() -> new DunesBiome(AtmosphericBiomeBuilders.DUNES));
	public static final RegistryObject<Biome> DUNES_HILLS		= BIOMES.register("dunes_hills",		() -> new DunesHillsBiome(AtmosphericBiomeBuilders.DUNES_HILLS));
	public static final RegistryObject<Biome> FLOURISHING_DUNES	= BIOMES.register("flourishing_dunes",	() -> new FlourishingDunesBiome(AtmosphericBiomeBuilders.DUNES));

    public static final RegistryObject<Biome> ROCKY_DUNES 		= BIOMES.register("rocky_dunes",		() -> new RockyDunesBiome(AtmosphericBiomeBuilders.ROCKY_DUNES));
    public static final RegistryObject<Biome> ROCKY_DUNES_HILLS	= BIOMES.register("rocky_dunes_hills",	() -> new RockyDunesHillsBiome(AtmosphericBiomeBuilders.ROCKY_DUNES_HILLS));
	public static final RegistryObject<Biome> PETRIFIED_DUNES 	= BIOMES.register("petrified_dunes",	() -> new PetrifiedDunesBiome(AtmosphericBiomeBuilders.DUNES_HILLS));
    
    public static void registerBiomesToDictionary() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_FOREST.get(), AtmosphericConfig.ValuesHolder.getRosewoodForestWeight()));  
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(DUNES.get(), AtmosphericConfig.ValuesHolder.getDunesWeight()));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(ROCKY_DUNES.get(), AtmosphericConfig.ValuesHolder.getRockyDunesWeight()));
    }
    
    public static void addBiomeTypes() {
    	BiomeDictionary.addTypes(ROSEWOOD_FOREST.get(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(ROSEWOOD_MOUNTAINS.get(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(ROSEWOOD_PLATEAU.get(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(ROSEWOOD_FOREST_PLATEAU.get(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.OVERWORLD);
        
        BiomeDictionary.addTypes(DUNES.get(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(DUNES_HILLS.get(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(FLOURISHING_DUNES.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.LUSH,BiomeDictionary.Type.OVERWORLD);
     
        BiomeDictionary.addTypes(ROCKY_DUNES.get(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(ROCKY_DUNES_HILLS.get(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(PETRIFIED_DUNES.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
    }
}
