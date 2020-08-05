package com.bagel.atmospheric.common.world.biome;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.HugeBaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class AtmosphericFeatureConfigs {
	public static BlockState ROSEWOOD_LOG 	= AtmosphericBlocks.ROSEWOOD_LOG.get().getDefaultState();
	public static BlockState ROSEWOOD_LEAVES= AtmosphericBlocks.ROSEWOOD_LEAVES.get().getDefaultState();
	public static BlockState YUCCA_LOG 		= AtmosphericBlocks.YUCCA_LOG.get().getDefaultState();
	public static BlockState YUCCA_LEAVES 	= AtmosphericBlocks.YUCCA_LEAVES.get().getDefaultState();
	public static BlockState ASPEN_LOG 		= AtmosphericBlocks.ASPEN_LOG.get().getDefaultState();
	public static BlockState ASPEN_LEAVES 	= AtmosphericBlocks.ASPEN_LEAVES.get().getDefaultState();
	public static BlockState KOUSA_LOG 		= AtmosphericBlocks.KOUSA_LOG.get().getDefaultState();
	public static BlockState KOUSA_LEAVES 	= AtmosphericBlocks.KOUSA_LEAVES.get().getDefaultState();

	public static BlockState GILIA 	= AtmosphericBlocks.GILIA.get().getDefaultState();
	public static BlockState BARREL_CACTUS 	= AtmosphericBlocks.BARREL_CACTUS.get().getDefaultState();
	public static BlockState CRUSTOSE 	= AtmosphericBlocks.CRUSTOSE.get().getDefaultState();
	public static BlockState ALOE_VERA 	= AtmosphericBlocks.ALOE_VERA.get().getDefaultState();
	public static BlockState YUCCA_FLOWER	= AtmosphericBlocks.YUCCA_FLOWER.get().getDefaultState();

	public static final BaseTreeFeatureConfig OAK_GROUND_BUSH_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()), new BushFoliagePlacer(2, 0, 1, 0, 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(0, 0, 0))).func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build();

	public static final BlockClusterFeatureConfig MELON_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.MELON.getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(AtmosphericBlocks.RED_ARID_SAND.get())).func_227317_b_().build();
	public static final BlockClusterFeatureConfig GILIA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GILIA), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig BARREL_CACTUS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BARREL_CACTUS), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig ALOE_VERA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ALOE_VERA), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig YUCCA_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(YUCCA_FLOWER), new SimpleBlockPlacer())).tries(64).build();

	public static final BaseTreeFeatureConfig KOUSA_TREE_CONFIG = (
			new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(KOUSA_LOG), new SimpleBlockStateProvider(KOUSA_LEAVES), 
					new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.KOUSA_SAPLING.get()).build();
	
	public static final BaseTreeFeatureConfig FLOURISHING_KOUSA_TREE_CONFIG = (
			new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(KOUSA_LOG), new SimpleBlockStateProvider(KOUSA_LEAVES), 
					new BlobFoliagePlacer(0, 0))).decorators(ImmutableList.of(new BeehiveTreeDecorator(0.05F))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.KOUSA_SAPLING.get()).build();
	
	public static final BaseTreeFeatureConfig YUCCA_TREE_CONFIG = (
			new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(YUCCA_LOG), new SimpleBlockStateProvider(YUCCA_LEAVES), 
					new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.YUCCA_SAPLING.get()).build();
	
	public static final BaseTreeFeatureConfig ROSEWOOD_TREE_CONFIG = (
			new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ROSEWOOD_LOG), 
					new SimpleBlockStateProvider(ROSEWOOD_LEAVES), 
					new BlobFoliagePlacer(2, 0))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ROSEWOOD_SAPLING.get()).build();
	
	public static final BaseTreeFeatureConfig ROSEWOOD_TREE_BEEHIVES_CONFIG = (
			new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ROSEWOOD_LOG), 
					new SimpleBlockStateProvider(ROSEWOOD_LEAVES), 
					new BlobFoliagePlacer(2, 0))).decorators(ImmutableList.of(new BeehiveTreeDecorator(0.002F))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ROSEWOOD_SAPLING.get()).build();

	public static final BaseTreeFeatureConfig ROSEWOOD_TREE_MORE_BEEHIVES_CONFIG = (
			new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ROSEWOOD_LOG), 
					new SimpleBlockStateProvider(ROSEWOOD_LEAVES), 
					new BlobFoliagePlacer(0, 0))).decorators(ImmutableList.of(new BeehiveTreeDecorator(0.05F))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ROSEWOOD_SAPLING.get()).build();
	
	public static final BaseTreeFeatureConfig ASPEN_TREE_CONFIG = (
			new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ASPEN_LOG), 
					new SimpleBlockStateProvider(ASPEN_LEAVES), 
					new SpruceFoliagePlacer(2, 1))).baseHeight(6).heightRandA(3).trunkHeight(1).trunkHeightRandom(1).trunkTopOffsetRandom(2).ignoreVines().setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ASPEN_SAPLING.get()).build();

	public static final HugeBaseTreeFeatureConfig MEGA_ASPEN_TREE_CONFIG = (
			new HugeBaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ASPEN_LOG), 
					new SimpleBlockStateProvider(ASPEN_LEAVES))).baseHeight(13).heightInterval(15).crownHeight(3).decorators(ImmutableList.of(
							new AlterGroundTreeDecorator(new SimpleBlockStateProvider(CRUSTOSE)))).setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ASPEN_SAPLING.get()).build();

}
