package com.minecraftabnormals.atmospheric.common.world.biome;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.minecraftabnormals.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
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
	public static final BlockState YUCCA_BRANCH        = AtmosphericBlocks.YUCCA_BRANCH.get().getDefaultState();
	
    public static final BlockState YUCCA_BUNDLE         = AtmosphericBlocks.YUCCA_BUNDLE.get().getDefaultState();
    public static final BlockState ROASTED_YUCCA_BUNDLE = AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get().getDefaultState();

    public static final BlockState ARID_SANDSTONE           = AtmosphericBlocks.ARID_SANDSTONE.get().getDefaultState();
    public static final BlockState ARID_SANDSTONE_WALL      = AtmosphericBlocks.ARID_SANDSTONE_WALL.get().getDefaultState();
    public static final BlockState RED_ARID_SANDSTONE       = AtmosphericBlocks.RED_ARID_SANDSTONE.get().getDefaultState();
    public static final BlockState RED_ARID_SANDSTONE_WALL  = AtmosphericBlocks.RED_ARID_SANDSTONE_WALL.get().getDefaultState();
    
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
	public static final BlockState TALL_YUCCA_FLOWER_TOP    = AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER);
    public static final BlockState TALL_YUCCA_FLOWER_BOTTOM = AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER);

    public static final YuccaTreeFeatureConfig.Builder YUCCA_TREE_BUILDER           = new YuccaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(YUCCA_LOG), new SimpleBlockStateProvider(YUCCA_LEAVES), new SimpleBlockStateProvider(YUCCA_BRANCH), new SimpleBlockStateProvider(YUCCA_BUNDLE), new SimpleBlockStateProvider(YUCCA_FLOWER), new SimpleBlockStateProvider(TALL_YUCCA_FLOWER_TOP), new SimpleBlockStateProvider(TALL_YUCCA_FLOWER_BOTTOM));
    public static final YuccaTreeFeatureConfig.Builder ARID_YUCCA_TREE_BUILDER      = new YuccaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ARID_SANDSTONE), new SimpleBlockStateProvider(ARID_SANDSTONE), new SimpleBlockStateProvider(ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(ROASTED_YUCCA_BUNDLE), new SimpleBlockStateProvider(ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(ARID_SANDSTONE_WALL));
    public static final YuccaTreeFeatureConfig.Builder RED_ARID_YUCCA_TREE_BUILDER  = new YuccaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(RED_ARID_SANDSTONE), new SimpleBlockStateProvider(RED_ARID_SANDSTONE), new SimpleBlockStateProvider(RED_ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(ROASTED_YUCCA_BUNDLE), new SimpleBlockStateProvider(RED_ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(RED_ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(RED_ARID_SANDSTONE_WALL));

	public static final BlockClusterFeatureConfig MELON_PATCH_CONFIG   = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.MELON.getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(AtmosphericBlocks.RED_ARID_SAND.get())).func_227317_b_().build();
	public static final BlockClusterFeatureConfig GILIA_CONFIG         = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GILIA), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig BARREL_CACTUS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BARREL_CACTUS), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig ALOE_VERA_CONFIG     = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ALOE_VERA), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig YUCCA_FLOWER_CONFIG  = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(YUCCA_FLOWER), new SimpleBlockPlacer())).tries(64).build();

	public static final SurfaceBuilderConfig DUNES = new SurfaceBuilderConfig(AtmosphericBlocks.ARID_SAND.get().getDefaultState(), AtmosphericBlocks.ARID_SAND.get().getDefaultState(), Blocks.GRAVEL.getDefaultState());

	public static final BaseTreeFeatureConfig OAK_GROUND_BUSH_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()), new BushFoliagePlacer(2, 0, 1, 0, 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(0, 0, 0))).func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build();

    public static final BaseTreeFeatureConfig KOUSA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(KOUSA_LOG), new SimpleBlockStateProvider(KOUSA_LEAVES), new FancyFoliagePlacer(2, 0, 4, 0, 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
    public static final BaseTreeFeatureConfig KOUSA_TREE_WITH_MORE_BEEHIVES_CONFIG = KOUSA_TREE_CONFIG.func_236685_a_(ImmutableList.of(MANY_BEEHIVES));
    
    public static final YuccaTreeFeatureConfig YUCCA_TREE_CONFIG                                = YUCCA_TREE_BUILDER.setHeightmap(Heightmap.Type.MOTION_BLOCKING).build();
    public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_PATCH_CONFIG                     = YUCCA_TREE_CONFIG.setPatch();
    public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG             = YUCCA_TREE_CONFIG.func_236685_a_(ImmutableList.of(MANY_BEEHIVES));
    public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_MORE_BEEHIVES_AND_PATCH_CONFIG   = YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG.setPatch();
    
    public static final YuccaTreeFeatureConfig BABY_YUCCA_TREE_CONFIG               = YUCCA_TREE_CONFIG.setBaby();
    public static final YuccaTreeFeatureConfig BABY_YUCCA_TREE_WITH_PATCH_CONFIG    = BABY_YUCCA_TREE_CONFIG.setPatch();
    
    public static final YuccaTreeFeatureConfig ARID_YUCCA_TREE_CONFIG       = ARID_YUCCA_TREE_BUILDER.isPetrified().setHeightmap(Heightmap.Type.MOTION_BLOCKING).build();
    public static final YuccaTreeFeatureConfig RED_ARID_YUCCA_TREE_CONFIG   = RED_ARID_YUCCA_TREE_BUILDER.isPetrified().setHeightmap(Heightmap.Type.MOTION_BLOCKING).build();
	
	public static final BaseTreeFeatureConfig ROSEWOOD_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ROSEWOOD_LOG), new SimpleBlockStateProvider(ROSEWOOD_LEAVES), null, null, null)).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
    public static final BaseTreeFeatureConfig ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG = ROSEWOOD_TREE_CONFIG.func_236685_a_(ImmutableList.of(FEW_BEEHIVES));
    public static final BaseTreeFeatureConfig ROSEWOOD_TREE_WITH_MORE_BEEHIVES_CONFIG = ROSEWOOD_TREE_CONFIG.func_236685_a_(ImmutableList.of(MANY_BEEHIVES));

	public static final BaseTreeFeatureConfig ASPEN_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ASPEN_LOG), new SimpleBlockStateProvider(ASPEN_LEAVES), new SpruceFoliagePlacer(2, 1, 0, 2, 1, 1), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build();
	public static final BaseTreeFeatureConfig MEGA_ASPEN_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ASPEN_LOG), new SimpleBlockStateProvider(ASPEN_LEAVES), new MegaPineFoliagePlacer(0, 0, 0, 0, 4, 13), new GiantTrunkPlacer(13, 2, 14), new TwoLayerFeature(1, 1, 2))).func_236703_a_(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(CRUSTOSE)))).build();

    public static final BaseTreeFeatureConfig GRIMWOOD_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GRIMWOOD_LOG), new SimpleBlockStateProvider(GRIMWOOD_LEAVES), new SpruceFoliagePlacer(2, 1, 0, 2, 1, 1), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build();
}
