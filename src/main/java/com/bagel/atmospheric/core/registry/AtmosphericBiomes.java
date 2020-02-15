package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.world.biome.dunes.RockyDunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.SandyDunesBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodForestBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodMountainsBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodPlateauBiome;
import com.bagel.atmospheric.core.Atmospheric;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unchecked")
@Mod.EventBusSubscriber(modid = "atmospheric", bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericBiomes {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Atmospheric.MODID);
	
    public static RegistryObject<Biome> ROSEWOOD_FOREST = BIOMES.register(
    		"rosewood_forest", () -> new RosewoodForestBiome(new Biome.Builder()
    				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
    				.precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST)
    				.depth(0.1F)
    				.scale(0.2F)
    				.temperature(0.90F)
    				.downfall(0.8F)
    				.waterColor(4159204)
    				.waterFogColor(329011)
    				.parent(null)));
    
    public static RegistryObject<Biome> ROSEWOOD_MOUNTAINS = BIOMES.register(
    		"rosewood_mountains", () -> new RosewoodMountainsBiome(new Biome.Builder()
    				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
    				.precipitation(Biome.RainType.RAIN)
    				.category(Biome.Category.FOREST)
    				.depth(0.2825F).scale(1.225F)
    				.temperature(0.90F)
    				.downfall(0.8F)
    				.waterColor(4159204)
    				.waterFogColor(329011)
    				.parent("atmospheric:rosewood_forest")));
    
    public static RegistryObject<Biome> ROSEWOOD_PLATEAU = BIOMES.register(
    		"rosewood_plateau", () -> new RosewoodPlateauBiome(new Biome.Builder()
    				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
    				.precipitation(Biome.RainType.RAIN)
    				.category(Biome.Category.FOREST)
    				.depth(1.5F)
    				.scale(0.025F)
    				.temperature(0.90F)
    				.downfall(0.8F)
    				.waterColor(4159204)
    				.waterFogColor(329011)
    				.parent("atmospheric:rosewood_forest")));
    
    public static RegistryObject<Biome> ROSEWOOD_FOREST_PLATEAU = BIOMES.register(
    		"rosewood_forest_plateau", () -> new RosewoodForestBiome(new Biome.Builder()
    				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
    				.precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST)
    				.depth(1.5F)
    				.scale(0.025F)
    				.temperature(0.90F)
    				.downfall(0.8F)
    				.waterColor(4159204)
    				.waterFogColor(329011)
    				.parent("atmospheric:rosewood_plateau")));
    
	public static RegistryObject<Biome> SANDY_DUNES = BIOMES.register(
    		"sandy_dunes", () -> new SandyDunesBiome(new Biome.Builder()
    				.surfaceBuilder(AtmosphericFeatures.DUNES, new SurfaceBuilderConfig(
    						AtmosphericBlocks.ARID_SAND.get().getDefaultState(), 
    						AtmosphericBlocks.ARID_SAND.get().getDefaultState(), 
    						Blocks.GRAVEL.getDefaultState()))
    				.precipitation(Biome.RainType.NONE)
    				.category(Biome.Category.DESERT)
    				.depth(0.35F).scale(0.25F)
    				.temperature(2.0F)
    				.downfall(0.0F)
    				.waterColor(4159204)
    				.waterFogColor(329011)
    				.parent(null)));
    
    public static RegistryObject<Biome> ROCKY_DUNES = BIOMES.register(
    		"rocky_dunes", () -> new RockyDunesBiome(new Biome.Builder()
    				.surfaceBuilder(AtmosphericFeatures.DUNES, new SurfaceBuilderConfig(
    						AtmosphericBlocks.ARID_SAND.get().getDefaultState(), 
    						AtmosphericBlocks.ARID_SAND.get().getDefaultState(), 
    						Blocks.GRAVEL.getDefaultState()))
    				.precipitation(Biome.RainType.NONE)
    				.category(Biome.Category.DESERT)
    				.depth(0.35F)
    				.scale(0.25F)
    				.temperature(2.0F)
    				.downfall(0.0F)
    				.waterColor(4159204)
    				.waterFogColor(329011)
    				.parent(null)));
    
    public static void registerBiomesToDictionary() {
        BiomeDictionary.addTypes(ROSEWOOD_FOREST.get(), 
        		BiomeDictionary.Type.RARE, 
        		BiomeDictionary.Type.JUNGLE,  
        		BiomeDictionary.Type.FOREST, 
        		BiomeDictionary.Type.OVERWORLD);
        
        BiomeDictionary.addTypes(ROSEWOOD_MOUNTAINS.get(), 
        		BiomeDictionary.Type.RARE, 
        		BiomeDictionary.Type.JUNGLE, 
        		BiomeDictionary.Type.FOREST, 
        		BiomeDictionary.Type.MOUNTAIN, 
        		BiomeDictionary.Type.OVERWORLD);
        
        BiomeDictionary.addTypes(ROSEWOOD_PLATEAU.get(), 
        		BiomeDictionary.Type.RARE, 
        		BiomeDictionary.Type.JUNGLE, 
        		BiomeDictionary.Type.PLATEAU, 
        		BiomeDictionary.Type.PLAINS, 
        		BiomeDictionary.Type.OVERWORLD);
        
        BiomeDictionary.addTypes(ROSEWOOD_FOREST_PLATEAU.get(), 
        		BiomeDictionary.Type.RARE, 
        		BiomeDictionary.Type.JUNGLE, 
        		BiomeDictionary.Type.FOREST, 
        		BiomeDictionary.Type.PLATEAU, 
        		BiomeDictionary.Type.PLAINS, 
        		BiomeDictionary.Type.OVERWORLD);
        
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_FOREST.get(), 2));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_MOUNTAINS.get(), 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_PLATEAU.get(), 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_FOREST_PLATEAU.get(), 1));
    }
}
