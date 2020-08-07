package com.bagel.atmospheric.common.world.biome;

import java.util.OptionalInt;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class AtmosphericFeatureConfigs {
    
    private static final BeehiveTreeDecorator FEW_BEEHIVES = new BeehiveTreeDecorator(0.002F);
    private static final BeehiveTreeDecorator MANY_BEEHIVES = new BeehiveTreeDecorator(0.05F);
    
	public static final BlockState ROSEWOOD_LOG        = AtmosphericBlocks.ROSEWOOD_LOG.get().getDefaultState();
	public static final BlockState ROSEWOOD_LEAVES     = AtmosphericBlocks.ROSEWOOD_LEAVES.get().getDefaultState();
	
	public static final BlockState YUCCA_LOG           = AtmosphericBlocks.YUCCA_LOG.get().getDefaultState();
	public static final BlockState YUCCA_LEAVES        = AtmosphericBlocks.YUCCA_LEAVES.get().getDefaultState();
	
	public static final BlockState ASPEN_LOG           = AtmosphericBlocks.ASPEN_LOG.get().getDefaultState();
	public static final BlockState ASPEN_LEAVES        = AtmosphericBlocks.ASPEN_LEAVES.get().getDefaultState();
	
	public static final BlockState KOUSA_LOG           = AtmosphericBlocks.KOUSA_LOG.get().getDefaultState();
	public static final BlockState KOUSA_LEAVES        = AtmosphericBlocks.KOUSA_LEAVES.get().getDefaultState();
	
	public static final BlockState GRIMWOOD_LOG        = AtmosphericBlocks.GRIMWOOD_LOG.get().getDefaultState();
    public static final BlockState GRIMWOOD_LEAVES     = AtmosphericBlocks.GRIMWOOD_LEAVES.get().getDefaultState();

	public static final BlockState GILIA           = AtmosphericBlocks.GILIA.get().getDefaultState();
	public static final BlockState BARREL_CACTUS   = AtmosphericBlocks.BARREL_CACTUS.get().getDefaultState();
	public static final BlockState CRUSTOSE        = AtmosphericBlocks.CRUSTOSE.get().getDefaultState();
	public static final BlockState ALOE_VERA       = AtmosphericBlocks.ALOE_VERA.get().getDefaultState();
	public static final BlockState YUCCA_FLOWER	   = AtmosphericBlocks.YUCCA_FLOWER.get().getDefaultState();

	public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> ARID_SHRINE = AtmosphericFeatures.ARID_SHRINE.func_236391_a_(NoFeatureConfig.field_236559_b_);

	public static final BlockClusterFeatureConfig MELON_PATCH_CONFIG   = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.MELON.getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(AtmosphericBlocks.RED_ARID_SAND.get())).func_227317_b_().build();
	public static final BlockClusterFeatureConfig GILIA_CONFIG         = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GILIA), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig BARREL_CACTUS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BARREL_CACTUS), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig ALOE_VERA_CONFIG     = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ALOE_VERA), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig YUCCA_FLOWER_CONFIG  = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(YUCCA_FLOWER), new SimpleBlockPlacer())).tries(64).build();

	public static final SurfaceBuilderConfig DUNES = new SurfaceBuilderConfig(AtmosphericBlocks.ARID_SAND.get().getDefaultState(), AtmosphericBlocks.ARID_SAND.get().getDefaultState(), Blocks.GRAVEL.getDefaultState());

	public static final BaseTreeFeatureConfig OAK_GROUND_BUSH_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()), new BushFoliagePlacer(2, 0, 1, 0, 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(0, 0, 0))).func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build();

    public static final BaseTreeFeatureConfig KOUSA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(KOUSA_LOG), new SimpleBlockStateProvider(KOUSA_LEAVES), new FancyFoliagePlacer(2, 0, 4, 0, 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
    public static final BaseTreeFeatureConfig KOUSA_TREE_WITH_MORE_BEEHIVES_CONFIG = KOUSA_TREE_CONFIG.func_236685_a_(ImmutableList.of(MANY_BEEHIVES));
    
    public static final BaseTreeFeatureConfig YUCCA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(YUCCA_LOG), new SimpleBlockStateProvider(YUCCA_LEAVES), null, null, null)).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
    public static final BaseTreeFeatureConfig YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG = YUCCA_TREE_CONFIG.func_236685_a_(ImmutableList.of(MANY_BEEHIVES));
	
	public static final BaseTreeFeatureConfig ROSEWOOD_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ROSEWOOD_LOG), new SimpleBlockStateProvider(ROSEWOOD_LEAVES), null, null, null)).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
    public static final BaseTreeFeatureConfig ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG = ROSEWOOD_TREE_CONFIG.func_236685_a_(ImmutableList.of(FEW_BEEHIVES));
    public static final BaseTreeFeatureConfig ROSEWOOD_TREE_WITH_MORE_BEEHIVES_CONFIG = ROSEWOOD_TREE_CONFIG.func_236685_a_(ImmutableList.of(MANY_BEEHIVES));
	
	public static final BaseTreeFeatureConfig ASPEN_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ASPEN_LOG), new SimpleBlockStateProvider(ASPEN_LEAVES), new SpruceFoliagePlacer(2, 1, 0, 2, 1, 1), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build();
	public static final BaseTreeFeatureConfig MEGA_ASPEN_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ASPEN_LOG), new SimpleBlockStateProvider(ASPEN_LEAVES), new MegaPineFoliagePlacer(0, 0, 0, 0, 4, 13), new GiantTrunkPlacer(13, 2, 14), new TwoLayerFeature(1, 1, 2))).func_236703_a_(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(CRUSTOSE)))).build();

    public static final BaseTreeFeatureConfig GRIMWOOD_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GRIMWOOD_LOG), new SimpleBlockStateProvider(GRIMWOOD_LEAVES), new SpruceFoliagePlacer(2, 1, 0, 2, 1, 1), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build();
}
