package com.bagel.atmospheric.common.world.biome;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;

public class AtmosphericBiomeFeatures {
	public static BlockState ROSEWOOD_LOG 	= AtmosphericBlocks.ROSEWOOD_LOG.get().getDefaultState();
	public static BlockState ROSEWOOD_LEAVES= AtmosphericBlocks.ROSEWOOD_LEAVES.get().getDefaultState();
	public static BlockState YUCCA_LOG 		= AtmosphericBlocks.YUCCA_LOG.get().getDefaultState();
	public static BlockState YUCCA_LEAVES 	= AtmosphericBlocks.YUCCA_LEAVES.get().getDefaultState();
	public static BlockState ASPEN_LOG 		= AtmosphericBlocks.ASPEN_LOG.get().getDefaultState();
	public static BlockState ASPEN_LEAVES 	= AtmosphericBlocks.ASPEN_LEAVES.get().getDefaultState();
	public static BlockState KOUSA_LOG 		= AtmosphericBlocks.KOUSA_LOG.get().getDefaultState();
	public static BlockState KOUSA_LEAVES 	= AtmosphericBlocks.KOUSA_LEAVES.get().getDefaultState();

	public static final HugeTreeFeatureConfig MEGA_ASPEN_TREE_CONFIG = (
			new HugeTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ASPEN_LOG), 
					new SimpleBlockStateProvider(ASPEN_LEAVES))).baseHeight(13).func_227283_b_(15).func_227284_c_(13).func_227282_a_(ImmutableList.of(
							new AlterGroundTreeDecorator(new SimpleBlockStateProvider(Blocks.PODZOL.getDefaultState())))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ASPEN_SAPLING.get()).build();
	
	public static final TreeFeatureConfig KOUSA_TREE_CONFIG = (
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(
							AtmosphericBiomeFeatures.KOUSA_LOG), 
					new SimpleBlockStateProvider(
							AtmosphericBiomeFeatures.KOUSA_LEAVES), 
					new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.KOUSA_SAPLING.get()).build();
	
	public static final TreeFeatureConfig YUCCA_TREE_CONFIG = (
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(
							AtmosphericBiomeFeatures.YUCCA_LOG), 
					new SimpleBlockStateProvider(
							AtmosphericBiomeFeatures.YUCCA_LEAVES), 
					new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.YUCCA_SAPLING.get()).build();
	
	public static final TreeFeatureConfig ROSEWOOD_TREE_CONFIG = (
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ROSEWOOD_LOG), 
					new SimpleBlockStateProvider(ROSEWOOD_LEAVES), 
					new BlobFoliagePlacer(2, 0)))
			.setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ROSEWOOD_SAPLING.get())
			.build();
	
	public static final TreeFeatureConfig ROSEWOOD_TREE_BEEHIVES_CONFIG = (
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ROSEWOOD_LOG), 
					new SimpleBlockStateProvider(ROSEWOOD_LEAVES), 
					new BlobFoliagePlacer(2, 0)))
			.decorators(ImmutableList.of(new BeehiveTreeDecorator(0.002F)))
			.setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ROSEWOOD_SAPLING.get())
			.build();

	public static final TreeFeatureConfig ROSEWOOD_TREE_MORE_BEEHIVES_CONFIG = (
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(
							AtmosphericBiomeFeatures.ROSEWOOD_LOG), 
					new SimpleBlockStateProvider(
							AtmosphericBiomeFeatures.ROSEWOOD_LEAVES), 
					new BlobFoliagePlacer(0, 0)))
			.decorators(ImmutableList.of(new BeehiveTreeDecorator(0.05F)))
			.setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ROSEWOOD_SAPLING.get())
			.build();
	
	public static final TreeFeatureConfig ASPEN_TREE_CONFIG = (
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ASPEN_LOG), 
					new SimpleBlockStateProvider(ASPEN_LEAVES), 
					new SpruceFoliagePlacer(2, 1))).baseHeight(6).heightRandA(3).trunkHeight(1).trunkHeightRandom(1).trunkTopOffsetRandom(2).ignoreVines().setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ASPEN_SAPLING.get()).build();

	public static final HugeTreeFeatureConfig MEGA_ASPEN_PINE_TREE_CONFIG = (
			new HugeTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ASPEN_LOG), 
					new SimpleBlockStateProvider(ASPEN_LEAVES))).baseHeight(13).func_227283_b_(15).func_227284_c_(3).func_227282_a_(ImmutableList.of(
							new AlterGroundTreeDecorator(new SimpleBlockStateProvider(Blocks.PODZOL.getDefaultState())))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ASPEN_SAPLING.get()).build();

	
	public static void addDoublePlants(Biome biomeIn) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.TALL_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(8))));
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.ROSE_BUSH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILAC_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.PEONY_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
	}
	
	public static void addFlowers(Biome biomeIn) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.WARM_MONKEY_BRUSH.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(3))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.HOT_MONKEY_BRUSH.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(2))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.SCALDING_MONKEY_BRUSH.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));

	}
	
	public static void addPodzol(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.PODZOL.withConfiguration(new ProbabilityConfig(0.2F)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED.configure(new TopSolidWithNoiseConfig(160, 80.0D, 0.3D, Heightmap.Type.WORLD_SURFACE_WG))));
	}
	
	public static void addFoliage(Biome biomeIn) {
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILY_PAD_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4))));
	    biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LUSH_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(25))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.VINES.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHT_64.configure(new FrequencyConfig(50))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.PASSION_VINE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
	}
	
	public static void addRosewoodForestTrees (Biome biomeIn, int count, int extraCountIn) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Feature.FANCY_TREE.withConfiguration(DefaultBiomeFeatures.FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG).func_227227_a_(0.33333334F)), Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_WITH_MORE_BEEHIVES_CONFIG))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.ROSEWOOD_TREE.withConfiguration(ROSEWOOD_TREE_BEEHIVES_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, 0.1F, extraCountIn))));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.OAK_BUSH.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
	}
	
	public static void addRosewoodPlateauTrees (Biome biomeIn, int count, int extraCountIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.ROSEWOOD_TREE.withConfiguration(ROSEWOOD_TREE_BEEHIVES_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, 0.1F, extraCountIn))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.OAK_BUSH.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
	}
	
	public static void addDuneRocks(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, AtmosphericFeatures.DUNE_ROCKS.withConfiguration(new BlockBlobConfig(AtmosphericBlocks.ARID_SANDSTONE.get().getDefaultState(), 2)).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
	}
	
	public static void addFossils(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.FOSSIL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_PASSTHROUGH.configure(new ChanceConfig(64))));
	}
	
	public static void addYuccaTrees(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.YUCCA_TREE.withConfiguration(YUCCA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.05F, 3))));
	}
	
	public static void addSparseYuccaTrees(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.YUCCA_TREE.withConfiguration(YUCCA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));
	}
}
