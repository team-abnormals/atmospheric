package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeBuilders;
import com.minecraftabnormals.atmospheric.common.world.biome.dunes.DunesBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.dunes.DunesHillsBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.dunes.FlourishingDunesBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.dunes.PetrifiedDunesBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.dunes.RockyDunesBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.dunes.RockyDunesHillsBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.rainforest.RainforestBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.rainforest.RainforestMountainsBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.rainforest.RainforestPlateauBiome;
import com.minecraftabnormals.atmospheric.common.world.biome.rainforest.SparseRainforestPlateauBiome;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericConfig;

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
	
    public static final RegistryObject<Biome> RAINFOREST 			    = BIOMES.register("rainforest", () -> new RainforestBiome(AtmosphericBiomeBuilders.RAINFOREST));
    public static final RegistryObject<Biome> RAINFOREST_MOUNTAINS      = BIOMES.register("rainforest_mountains", () -> new RainforestMountainsBiome(AtmosphericBiomeBuilders.RAINFOREST_MOUNTAINS));
    public static final RegistryObject<Biome> RAINFOREST_PLATEAU        = BIOMES.register("rainforest_plateau", () -> new RainforestPlateauBiome(AtmosphericBiomeBuilders.RAINFOREST_PLATEAU));
    public static final RegistryObject<Biome> SPARSE_RAINFOREST_PLATEAU = BIOMES.register("sparse_rainforest_plateau", () -> new SparseRainforestPlateauBiome(AtmosphericBiomeBuilders.RAINFOREST_PLATEAU));
    
	public static final RegistryObject<Biome> DUNES 			= BIOMES.register("dunes", 				() -> new DunesBiome(AtmosphericBiomeBuilders.DUNES));
	public static final RegistryObject<Biome> DUNES_HILLS		= BIOMES.register("dunes_hills",		() -> new DunesHillsBiome(AtmosphericBiomeBuilders.DUNES_HILLS));
	public static final RegistryObject<Biome> FLOURISHING_DUNES	= BIOMES.register("flourishing_dunes",	() -> new FlourishingDunesBiome(AtmosphericBiomeBuilders.DUNES));

    public static final RegistryObject<Biome> ROCKY_DUNES 		= BIOMES.register("rocky_dunes",		() -> new RockyDunesBiome(AtmosphericBiomeBuilders.ROCKY_DUNES));
    public static final RegistryObject<Biome> ROCKY_DUNES_HILLS	= BIOMES.register("rocky_dunes_hills",	() -> new RockyDunesHillsBiome(AtmosphericBiomeBuilders.ROCKY_DUNES_HILLS));
	public static final RegistryObject<Biome> PETRIFIED_DUNES 	= BIOMES.register("petrified_dunes",	() -> new PetrifiedDunesBiome(AtmosphericBiomeBuilders.DUNES_HILLS));
    
    public static void registerBiomesToDictionary() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RAINFOREST.get(), AtmosphericConfig.ValuesHolder.getRosewoodForestWeight()));  
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(DUNES.get(), AtmosphericConfig.ValuesHolder.getDunesWeight()));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(ROCKY_DUNES.get(), AtmosphericConfig.ValuesHolder.getRockyDunesWeight()));
    }
    
    public static void addBiomeTypes() {
    	BiomeDictionary.addTypes(RAINFOREST.get(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(RAINFOREST_MOUNTAINS.get(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(SPARSE_RAINFOREST_PLATEAU.get(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(RAINFOREST_PLATEAU.get(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.OVERWORLD);
        
        BiomeDictionary.addTypes(DUNES.get(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(DUNES_HILLS.get(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(FLOURISHING_DUNES.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.LUSH,BiomeDictionary.Type.OVERWORLD);
     
        BiomeDictionary.addTypes(ROCKY_DUNES.get(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(ROCKY_DUNES_HILLS.get(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(PETRIFIED_DUNES.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
    }
}
