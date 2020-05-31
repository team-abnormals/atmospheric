package com.bagel.atmospheric.common.world.biome;

import java.util.HashSet;
import java.util.Set;

import com.bagel.atmospheric.core.registry.AtmosphericBiomes;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SeaGrassConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

public class AtmosphericBiomeFeatures {
	
	public static void addRainforestDoublePlants(Biome biomeIn) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.TALL_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(8))));
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.ROSE_BUSH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILAC_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.PEONY_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
	}
	
	public static void addRainforestFlowers(Biome biomeIn) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.WARM_MONKEY_BRUSH.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(3))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.HOT_MONKEY_BRUSH.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(2))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.SCALDING_MONKEY_BRUSH.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
	}
	
	public static void addGilias(Biome biomeIn, int freq) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(AtmosphericFeatureConfigs.GILIA_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(freq))));
	}
	
	public static void addAloeVera(Biome biomeIn, int freq) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.ALOE_VERA.withConfiguration(AtmosphericFeatureConfigs.ALOE_VERA_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(freq))));
	}
	
	public static void addYuccaFlower(Biome biomeIn, int freq) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.YUCCA_FLOWER.withConfiguration(AtmosphericFeatureConfigs.YUCCA_FLOWER_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(freq))));
	}
	
	public static void addBarrelCactus(Biome biomeIn, int count, float extraChance, int extraCount) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.BARREL_CACTUS.withConfiguration(AtmosphericFeatureConfigs.BARREL_CACTUS_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, extraChance, extraCount))));
	}
	
	public static void addPodzol(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.PODZOL.withConfiguration(new ProbabilityConfig(0.2F)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED.configure(new TopSolidWithNoiseConfig(160, 80.0D, 0.3D, Heightmap.Type.WORLD_SURFACE_WG))));
	}
	
	public static void addRainforestFoliage(Biome biomeIn) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LUSH_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(25))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.VINES.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHT_64.configure(new FrequencyConfig(50))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.PASSION_VINE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
	}
	
	public static void addDeadBushes(Biome biomeIn, int freq) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.DEAD_BUSH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(freq))));
	}
	
	public static void addRosewoodForestTrees (Biome biomeIn, int count, int extraCountIn) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Feature.FANCY_TREE.withConfiguration(DefaultBiomeFeatures.FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG).func_227227_a_(0.33333334F)), Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_WITH_MORE_BEEHIVES_CONFIG))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.ROSEWOOD_TREE.withConfiguration(AtmosphericFeatureConfigs.ROSEWOOD_TREE_BEEHIVES_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, 0.1F, extraCountIn))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.OAK_BUSH.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
	}
	
	public static void addRosewoodPlateauTrees (Biome biomeIn, int count, int extraCountIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.ROSEWOOD_TREE.withConfiguration(AtmosphericFeatureConfigs.ROSEWOOD_TREE_BEEHIVES_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, 0.1F, extraCountIn))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.OAK_BUSH.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
	}
	
	public static void addDuneRocks(Biome biomeIn, int size, int frequency) {
		biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, AtmosphericFeatures.DUNE_ROCKS.withConfiguration(new BlockBlobConfig(AtmosphericBlocks.ARID_SANDSTONE.get().getDefaultState(), size)).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(frequency))));
	}

	public static void addFossils(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.FOSSIL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_PASSTHROUGH.configure(new ChanceConfig(64))));
	}
	
	public static void addSurfaceFossils(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, AtmosphericFeatures.SURFACE_FOSSIL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_PASSTHROUGH.configure(new ChanceConfig(64))));
	}

	public static void addYuccaTrees(Biome biomeIn, int count, float extraChance, int extraCount, boolean petrified) {
		Feature<TreeFeatureConfig> feature = petrified ? AtmosphericFeatures.PETRIFIED_YUCCA_TREE : AtmosphericFeatures.YUCCA_TREE_PATCH;
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, feature.withConfiguration(AtmosphericFeatureConfigs.YUCCA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, extraChance, extraCount))));
	}
	
	public static void addBabyYuccaTrees(Biome biomeIn, int count, float extraChance, int extraCount) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.BABY_YUCCA_TREE_PATCH.withConfiguration(AtmosphericFeatureConfigs.YUCCA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, extraChance, extraCount))));
	}
	
	public static void addRainforestWaterFoliage(Biome biomeIn) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILY_PAD_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(64, 0.6D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	}
	
	public static void addDuneGrassPatches(Biome biomeIn) {
//		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.TALL_GRASS_CONFIG).withPlacement(Placement.NOISE_HEIGHTMAP_32.configure(new NoiseDependant(-0.8D, 0, 7))));
//		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.withConfiguration(new SphereReplaceConfig(Blocks.COARSE_DIRT.getDefaultState(), 7, 2, Lists.newArrayList(AtmosphericBlocks.RED_ARID_SAND.get().getDefaultState()))).withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(3))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(AtmosphericFeatureConfigs.MELON_PATCH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(3))));
	}
	
	public static void addCarvables() {
		Set<Block> allBlocksToCarve = new HashSet<Block>();
		
		allBlocksToCarve.add(AtmosphericBlocks.ARID_SAND.get());
		allBlocksToCarve.add(AtmosphericBlocks.ARID_SANDSTONE.get());
		allBlocksToCarve.add(AtmosphericBlocks.RED_ARID_SAND.get());
		allBlocksToCarve.add(AtmosphericBlocks.RED_ARID_SANDSTONE.get());
		
		for (Carving carverStage : GenerationStage.Carving.values())
		{
		    for (ConfiguredCarver<?> carver : AtmosphericBiomes.DUNES.get().getCarvers(carverStage))
		    {
		        allBlocksToCarve.addAll(carver.carver.carvableBlocks);
		        carver.carver.carvableBlocks = allBlocksToCarve;
		    }
		}
	}
}
