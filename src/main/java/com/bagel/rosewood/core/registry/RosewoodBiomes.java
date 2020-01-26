package com.bagel.rosewood.core.registry;

import com.bagel.rosewood.common.world.gen.biome.RosewoodForestBiome;
import com.bagel.rosewood.common.world.gen.biome.RosewoodMountainsBiome;
import com.bagel.rosewood.common.world.gen.biome.RosewoodPlateauBiome;
import com.bagel.rosewood.core.Rosewood;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = "rosewood", bus = Mod.EventBusSubscriber.Bus.MOD)
public class RosewoodBiomes {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Rosewood.MODID);
	
    public static RegistryObject<Biome> ROSEWOOD_FOREST 		= BIOMES.register("rosewood_forest", 			() -> new RosewoodForestBiome(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.1F).scale(0.2F).temperature(0.90F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent(null)));
    public static RegistryObject<Biome> ROSEWOOD_MOUNTAINS 		= BIOMES.register("rosewood_mountains", 		() -> new RosewoodMountainsBiome(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.2825F).scale(1.225F).temperature(0.90F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent("rosewood:rosewood_forest")));
    public static RegistryObject<Biome> ROSEWOOD_PLATEAU 		= BIOMES.register("rosewood_plateau", 			() -> new RosewoodPlateauBiome(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(1.5F).scale(0.025F).temperature(0.90F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent("rosewood:rosewood_forest")));
    public static RegistryObject<Biome> ROSEWOOD_FOREST_PLATEAU = BIOMES.register("rosewood_forest_plateau", 	() -> new RosewoodForestBiome(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(1.5F).scale(0.025F).temperature(0.90F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent("rosewood:rosewood_plateau")));
    
    public static void registerBiomesToDictionary() {
        //BiomeDictionary.addTypes(ROSEWOOD_FOREST.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.JUNGLE,  BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        //BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_FOREST.get(), 2));
        
        //BiomeDictionary.addTypes(ROSEWOOD_MOUNTAINS.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD);
        //BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_MOUNTAINS.get(), 1));
        
        //BiomeDictionary.addTypes(ROSEWOOD_PLATEAU.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
        //BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_PLATEAU.get(), 2));
        
        BiomeDictionary.addTypes(ROSEWOOD_FOREST_PLATEAU.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ROSEWOOD_FOREST_PLATEAU.get(), 2));
    }
}
