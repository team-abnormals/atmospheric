package com.minecraftabnormals.atmospheric.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.minecraftabnormals.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.*;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.LargeSphereReplaceConfig;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.OptionalInt;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Atmospheric.MOD_ID);

	public static final RegistryObject<Feature<ProbabilityConfig>> PODZOL = FEATURES.register("podzol", () -> new PodzolFeature(ProbabilityConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> SURFACE_FOSSIL = FEATURES.register("surface_fossil", () -> new SurfaceFossilFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<LargeSphereReplaceConfig>> COARSE_DIRT_PATCH = FEATURES.register("coarse_dirt_patch", () -> new CoarseDirtPatchFeature(LargeSphereReplaceConfig.CODEC));

	public static final RegistryObject<Feature<NoFeatureConfig>> WARM_MONKEY_BRUSH = FEATURES.register("warm_monkey_brush", () -> new MonkeyBrushFeature(NoFeatureConfig.CODEC, 1));
	public static final RegistryObject<Feature<NoFeatureConfig>> HOT_MONKEY_BRUSH = FEATURES.register("hot_monkey_brush", () -> new MonkeyBrushFeature(NoFeatureConfig.CODEC, 2));
	public static final RegistryObject<Feature<NoFeatureConfig>> SCALDING_MONKEY_BRUSH = FEATURES.register("scalding_monkey_brush", () -> new MonkeyBrushFeature(NoFeatureConfig.CODEC, 3));
	public static final RegistryObject<Feature<NoFeatureConfig>> WATER_HYACINTH_PATCH = FEATURES.register("water_hyacinth_patch", () -> new WaterHyacinthPatchFeature(NoFeatureConfig.CODEC));

	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> ROSEWOOD_TREE = FEATURES.register("rosewood_tree", () -> new RainforestTreeFeature(BaseTreeFeatureConfig.CODEC, false));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> ROSEWOOD_WATER_TREE = FEATURES.register("rosewood_water_tree", () -> new RainforestTreeFeature(BaseTreeFeatureConfig.CODEC, true));
	public static final RegistryObject<Feature<YuccaTreeFeatureConfig>> YUCCA_TREE = FEATURES.register("yucca_tree", () -> new YuccaTreeFeature(YuccaTreeFeatureConfig.CODEC));

	public static final RegistryObject<Feature<NoFeatureConfig>> PASSION_VINE = FEATURES.register("passion_vine", () -> new PassionVineFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> BARREL_CACTUS = FEATURES.register("barrel_cactus", () -> new BarrelCactusFeature(BlockClusterFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> ALOE_VERA = FEATURES.register("aloe_vera", () -> new AloeVeraFeature(BlockClusterFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> YUCCA_FLOWER = FEATURES.register("yucca_flower", () -> new YuccaFlowerFeature(BlockClusterFeatureConfig.CODEC));

	public static final RegistryObject<Feature<BlockStateFeatureConfig>> DUNE_ROCKS = FEATURES.register("dune_rocks", () -> new DuneRocksFeature(BlockStateFeatureConfig.CODEC));

	public static final class States {
		public static final BlockState ROSEWOOD_LOG = AtmosphericBlocks.ROSEWOOD_LOG.get().defaultBlockState();
		public static final BlockState ROSEWOOD_LEAVES = AtmosphericBlocks.ROSEWOOD_LEAVES.get().defaultBlockState();
		public static final BlockState MORADO_LOG = AtmosphericBlocks.MORADO_LOG.get().defaultBlockState();
		public static final BlockState MORADO_LEAVES = AtmosphericBlocks.MORADO_LEAVES.get().defaultBlockState();
		public static final BlockState FLOWERING_MORADO_LEAVES = AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get().defaultBlockState();
		public static final BlockState YUCCA_LOG = AtmosphericBlocks.YUCCA_LOG.get().defaultBlockState();
		public static final BlockState YUCCA_LEAVES = AtmosphericBlocks.YUCCA_LEAVES.get().defaultBlockState();
		public static final BlockState YUCCA_BRANCH = AtmosphericBlocks.YUCCA_BRANCH.get().defaultBlockState();
		public static final BlockState YUCCA_BUNDLE = AtmosphericBlocks.YUCCA_BUNDLE.get().defaultBlockState();
		public static final BlockState ROASTED_YUCCA_BUNDLE = AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get().defaultBlockState();
		public static final BlockState ARID_SANDSTONE = AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState();
		public static final BlockState ARID_SANDSTONE_WALL = AtmosphericBlocks.ARID_SANDSTONE_WALL.get().defaultBlockState();
		public static final BlockState RED_ARID_SANDSTONE = AtmosphericBlocks.RED_ARID_SANDSTONE.get().defaultBlockState();
		public static final BlockState RED_ARID_SANDSTONE_WALL = AtmosphericBlocks.RED_ARID_SANDSTONE_WALL.get().defaultBlockState();
		public static final BlockState ASPEN_LOG = AtmosphericBlocks.ASPEN_LOG.get().defaultBlockState();
		public static final BlockState ASPEN_LEAVES = AtmosphericBlocks.ASPEN_LEAVES.get().defaultBlockState();
		public static final BlockState KOUSA_LOG = AtmosphericBlocks.KOUSA_LOG.get().defaultBlockState();
		public static final BlockState KOUSA_LEAVES = AtmosphericBlocks.KOUSA_LEAVES.get().defaultBlockState();
		public static final BlockState GRIMWOOD_LOG = AtmosphericBlocks.GRIMWOOD_LOG.get().defaultBlockState();
		public static final BlockState GRIMWOOD_LEAVES = AtmosphericBlocks.GRIMWOOD_LEAVES.get().defaultBlockState();
		public static final BlockState GILIA = AtmosphericBlocks.GILIA.get().defaultBlockState();
		public static final BlockState BARREL_CACTUS = AtmosphericBlocks.BARREL_CACTUS.get().defaultBlockState();
		public static final BlockState CRUSTOSE = AtmosphericBlocks.CRUSTOSE.get().defaultBlockState();
		public static final BlockState ALOE_VERA = AtmosphericBlocks.ALOE_VERA.get().defaultBlockState();
		public static final BlockState YUCCA_FLOWER = AtmosphericBlocks.YUCCA_FLOWER.get().defaultBlockState();
		public static final BlockState TALL_YUCCA_FLOWER_TOP = AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER);
		public static final BlockState TALL_YUCCA_FLOWER_BOTTOM = AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER);
	}

	public static final class Configs {
		public static final YuccaTreeFeatureConfig.Builder YUCCA_TREE_BUILDER = new YuccaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.YUCCA_LOG), new SimpleBlockStateProvider(States.YUCCA_LEAVES), new SimpleBlockStateProvider(States.YUCCA_BRANCH), new SimpleBlockStateProvider(States.YUCCA_BUNDLE), new SimpleBlockStateProvider(States.YUCCA_FLOWER), new SimpleBlockStateProvider(States.TALL_YUCCA_FLOWER_TOP), new SimpleBlockStateProvider(States.TALL_YUCCA_FLOWER_BOTTOM));
		public static final YuccaTreeFeatureConfig.Builder ARID_YUCCA_TREE_BUILDER = new YuccaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.ARID_SANDSTONE), new SimpleBlockStateProvider(States.ARID_SANDSTONE), new SimpleBlockStateProvider(States.ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(States.ROASTED_YUCCA_BUNDLE), new SimpleBlockStateProvider(States.ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(States.ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(States.ARID_SANDSTONE_WALL));
		public static final YuccaTreeFeatureConfig.Builder RED_ARID_YUCCA_TREE_BUILDER = new YuccaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.RED_ARID_SANDSTONE), new SimpleBlockStateProvider(States.RED_ARID_SANDSTONE), new SimpleBlockStateProvider(States.RED_ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(States.ROASTED_YUCCA_BUNDLE), new SimpleBlockStateProvider(States.RED_ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(States.RED_ARID_SANDSTONE_WALL), new SimpleBlockStateProvider(States.RED_ARID_SANDSTONE_WALL));
		public static final BlockClusterFeatureConfig GILIA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.GILIA), new SimpleBlockPlacer())).tries(64).build();
		public static final BlockClusterFeatureConfig BARREL_CACTUS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.BARREL_CACTUS), new SimpleBlockPlacer())).tries(64).build();
		public static final BlockClusterFeatureConfig ALOE_VERA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.ALOE_VERA), new SimpleBlockPlacer())).tries(64).build();
		public static final BlockClusterFeatureConfig YUCCA_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.YUCCA_FLOWER), new SimpleBlockPlacer())).tries(64).build();
		public static final BlockClusterFeatureConfig ARID_SPROUTS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AtmosphericBlocks.ARID_SPROUTS.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();

		public static final BaseTreeFeatureConfig OAK_GROUND_BUSH_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.OAK_LEAVES.defaultBlockState()), new BushFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1), 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build();

		public static final YuccaTreeFeatureConfig YUCCA_TREE_CONFIG = YUCCA_TREE_BUILDER.setHeightmap(Heightmap.Type.MOTION_BLOCKING).build();
		public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_PATCH_CONFIG = YUCCA_TREE_CONFIG.setPatch();
		public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG = YUCCA_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005));
		public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_MORE_BEEHIVES_AND_PATCH_CONFIG = YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG.setPatch();
		public static final YuccaTreeFeatureConfig BABY_YUCCA_TREE_CONFIG = YUCCA_TREE_CONFIG.setBaby();
		public static final YuccaTreeFeatureConfig BABY_YUCCA_TREE_WITH_PATCH_CONFIG = BABY_YUCCA_TREE_CONFIG.setPatch();
		public static final YuccaTreeFeatureConfig ARID_YUCCA_TREE_CONFIG = ARID_YUCCA_TREE_BUILDER.isPetrified().setHeightmap(Heightmap.Type.MOTION_BLOCKING).build();
		public static final YuccaTreeFeatureConfig RED_ARID_YUCCA_TREE_CONFIG = RED_ARID_YUCCA_TREE_BUILDER.isPetrified().setHeightmap(Heightmap.Type.MOTION_BLOCKING).build();

		public static final BaseTreeFeatureConfig ROSEWOOD_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.ROSEWOOD_LOG), new SimpleBlockStateProvider(States.ROSEWOOD_LEAVES), new BlobFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), 0), new StraightTrunkPlacer(0, 0, 0), new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build();
		public static final BaseTreeFeatureConfig ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG = ROSEWOOD_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_0002));
		public static final BaseTreeFeatureConfig ROSEWOOD_TREE_WITH_MORE_BEEHIVES_CONFIG = ROSEWOOD_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005));
		public static final BaseTreeFeatureConfig MORADO_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.MORADO_LOG), new WeightedBlockStateProvider().add(States.MORADO_LEAVES, 2).add(States.FLOWERING_MORADO_LEAVES, 6), new BlobFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), 0), new StraightTrunkPlacer(0, 0, 0), new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build();
		public static final BaseTreeFeatureConfig MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG = MORADO_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_0002));
		public static final BaseTreeFeatureConfig MORADO_TREE_WITH_MORE_BEEHIVES_CONFIG = MORADO_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005));

		public static final BaseTreeFeatureConfig KOUSA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.KOUSA_LOG), new SimpleBlockStateProvider(States.KOUSA_LEAVES), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build();
		public static final BaseTreeFeatureConfig ASPEN_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.ASPEN_LOG), new SimpleBlockStateProvider(States.ASPEN_LEAVES), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build();
		public static final BaseTreeFeatureConfig MEGA_ASPEN_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.ASPEN_LOG), new SimpleBlockStateProvider(States.ASPEN_LEAVES), new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)), new GiantTrunkPlacer(13, 2, 14), new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(States.CRUSTOSE)))).build();
		public static final BaseTreeFeatureConfig GRIMWOOD_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.GRIMWOOD_LOG), new SimpleBlockStateProvider(States.GRIMWOOD_LEAVES), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build();
	}

	public static final class Configured {
		public static final ConfiguredFeature<?, ?> PODZOL = AtmosphericFeatures.PODZOL.get().configured(new ProbabilityConfig(0.2F)).decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(160, 80.0D, 0.3D))).decorated(Placement.TOP_SOLID_HEIGHTMAP.configured(IPlacementConfig.NONE));
		public static final ConfiguredFeature<?, ?> PASSION_VINES = AtmosphericFeatures.PASSION_VINE.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1);
		public static final ConfiguredFeature<?, ?> RAINFOREST_BUSH = Feature.TREE.configured(AtmosphericFeatures.Configs.OAK_GROUND_BUSH_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.1F, 1)));

		public static final ConfiguredFeature<?, ?> PATCH_WARM_MONKEY_BRUSH = AtmosphericFeatures.WARM_MONKEY_BRUSH.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(3);
		public static final ConfiguredFeature<?, ?> PATCH_HOT_MONKEY_BRUSH = AtmosphericFeatures.HOT_MONKEY_BRUSH.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(2);
		public static final ConfiguredFeature<?, ?> PATCH_SCALDING_MONKEY_BRUSH = AtmosphericFeatures.SCALDING_MONKEY_BRUSH.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1);
		public static final ConfiguredFeature<?, ?> PATCH_WATER_HYACINTH = AtmosphericFeatures.WATER_HYACINTH_PATCH.get().configured(NoFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(2);
		public static final ConfiguredFeature<?, ?> PATCH_WATER_HYACINTH_SPARSE = AtmosphericFeatures.WATER_HYACINTH_PATCH.get().configured(NoFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1);
		public static final ConfiguredFeature<?, ?> PATCH_WATERLILLY_RAINFOREST = Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1);

		public static final ConfiguredFeature<?, ?> OAK_TREE_RAINFOREST = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.FANCY_OAK_BEES_005.weighted(0.33333334F)), Features.OAK_BEES_005)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1)));
		public static final ConfiguredFeature<?, ?> ROSEWOOD_TREE = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
		public static final ConfiguredFeature<?, ?> ROSEWOOD_TREE_EXTRA = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(13, 0.1F, 1)));
		public static final ConfiguredFeature<?, ?> ROSEWOOD_TREE_SPARSE = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 4)));
		public static final ConfiguredFeature<?, ?> ROSEWOOD_WATER_TREE = AtmosphericFeatures.ROSEWOOD_WATER_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(32, 0, 0)));
		public static final ConfiguredFeature<?, ?> ROSEWOOD_WATER_TREE_SPARSE = AtmosphericFeatures.ROSEWOOD_WATER_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0, 0)));
		public static final ConfiguredFeature<?, ?> MORADO_TREE = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.05F, 1)));
		public static final ConfiguredFeature<?, ?> MORADO_TREE_SPARSE = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1)));
		public static final ConfiguredFeature<?, ?> MORADO_WATER_TREE = AtmosphericFeatures.ROSEWOOD_WATER_TREE.get().configured(AtmosphericFeatures.Configs.MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0, 0)));
		public static final ConfiguredFeature<?, ?> MORADO_WATER_TREE_SPARSE = AtmosphericFeatures.ROSEWOOD_WATER_TREE.get().configured(AtmosphericFeatures.Configs.MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0, 0)));

		public static final ConfiguredFeature<?, ?> DUNE_ROCK = AtmosphericFeatures.DUNE_ROCKS.get().configured(new BlockStateFeatureConfig(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(2);
		public static final ConfiguredFeature<?, ?> DUNE_ROCK_EXTRA = AtmosphericFeatures.DUNE_ROCKS.get().configured(new BlockStateFeatureConfig(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(3);
		public static final ConfiguredFeature<?, ?> DUNE_ROCK_MANY = AtmosphericFeatures.DUNE_ROCKS.get().configured(new BlockStateFeatureConfig(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(6);
		public static final ConfiguredFeature<?, ?> FOSSIL_SURFACE = AtmosphericFeatures.SURFACE_FOSSIL.get().configured(IFeatureConfig.NONE).chance(64);

		public static final ConfiguredFeature<?, ?> PATCH_YUCCA_FLOWER = AtmosphericFeatures.YUCCA_FLOWER.get().configured(AtmosphericFeatures.Configs.YUCCA_FLOWER_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(2);
		public static final ConfiguredFeature<?, ?> PATCH_YUCCA_FLOWER_EXTRA = AtmosphericFeatures.YUCCA_FLOWER.get().configured(AtmosphericFeatures.Configs.YUCCA_FLOWER_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(5);
		public static final ConfiguredFeature<?, ?> PATCH_GILIA = Feature.FLOWER.configured(AtmosphericFeatures.Configs.GILIA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(18);
		public static final ConfiguredFeature<?, ?> PATCH_ALOE_VERA = AtmosphericFeatures.ALOE_VERA.get().configured(AtmosphericFeatures.Configs.ALOE_VERA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(2);
		public static final ConfiguredFeature<?, ?> PATCH_ALOE_VERA_EXTRA = AtmosphericFeatures.ALOE_VERA.get().configured(AtmosphericFeatures.Configs.ALOE_VERA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(6);
		public static final ConfiguredFeature<?, ?> PATCH_BARREL_CACTUS_DUNES = AtmosphericFeatures.BARREL_CACTUS.get().configured(AtmosphericFeatures.Configs.BARREL_CACTUS_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.075F, 2)));
		public static final ConfiguredFeature<?, ?> PATCH_BARREL_CACTUS_ROCKY_DUNES = AtmosphericFeatures.BARREL_CACTUS.get().configured(AtmosphericFeatures.Configs.BARREL_CACTUS_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.075F, 1)));
		public static final ConfiguredFeature<?, ?> PATCH_BARREL_CACTUS_FLOURISHING_DUNES = AtmosphericFeatures.BARREL_CACTUS.get().configured(AtmosphericFeatures.Configs.BARREL_CACTUS_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.5F, 4)));
		public static final ConfiguredFeature<?, ?> PATCH_BARREL_CACTUS_PETRIFIED_DUNES = AtmosphericFeatures.BARREL_CACTUS.get().configured(AtmosphericFeatures.Configs.BARREL_CACTUS_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
		public static final ConfiguredFeature<?, ?> PATCH_DUNE_GRASS = AtmosphericFeatures.COARSE_DIRT_PATCH.get().configured(new LargeSphereReplaceConfig(Blocks.COARSE_DIRT.defaultBlockState(), FeatureSpread.of(7, 1), 2, Lists.newArrayList(AtmosphericBlocks.RED_ARID_SAND.get().defaultBlockState(), AtmosphericBlocks.ARID_SAND.get().defaultBlockState()))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.8F, 1)));
		public static final ConfiguredFeature<?, ?> PATCH_ARID_SPROUTS = Feature.RANDOM_PATCH.configured(Configs.ARID_SPROUTS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE);

		public static final ConfiguredFeature<?, ?> YUCCA_TREE = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.25F, 1)));
		public static final ConfiguredFeature<?, ?> YUCCA_TREE_BEEHIVE = AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_MORE_BEEHIVES_AND_PATCH_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.25F, 1)));
		public static final ConfiguredFeature<?, ?> YUCCA_TREE_BABY = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.BABY_YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.BABY_YUCCA_TREE_CONFIG))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.05F, 1)));
		public static final ConfiguredFeature<?, ?> YUCCA_TREE_ROCKY_DUNES = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
		public static final ConfiguredFeature<?, ?> YUCCA_TREE_SAVANNA = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.15F, 1)));
		public static final ConfiguredFeature<?, ?> YUCCA_TREE_DESERT = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.005F, 1)));
		public static final ConfiguredFeature<?, ?> YUCCA_TREE_PETRIFIED = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.RED_ARID_YUCCA_TREE_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.ARID_YUCCA_TREE_CONFIG))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.5F, 2)));

		private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Atmospheric.MOD_ID, name), configuredFeature);
		}

		public static void registerConfiguredFeatures() {
			register("podzol", PODZOL);
			register("passion_vines", PASSION_VINES);
			register("rainforest_bush", RAINFOREST_BUSH);

			register("patch_warm_monkey_brush", PATCH_WARM_MONKEY_BRUSH);
			register("patch_hot_monkey_brush", PATCH_HOT_MONKEY_BRUSH);
			register("patch_scalding_monkey_brush", PATCH_SCALDING_MONKEY_BRUSH);
			register("patch_water_hyacinth", PATCH_WATER_HYACINTH);
			register("patch_water_hyacinth_sparse", PATCH_WATER_HYACINTH_SPARSE);
			register("patch_waterlilly_rainforest", PATCH_WATERLILLY_RAINFOREST);

			register("oak_tree_rainforest", OAK_TREE_RAINFOREST);
			register("rosewood_tree", ROSEWOOD_TREE);
			register("rosewood_tree_extra", ROSEWOOD_TREE_EXTRA);
			register("rosewood_tree_sparse", ROSEWOOD_TREE_SPARSE);
			register("rosewood_water_tree", ROSEWOOD_WATER_TREE);
			register("rosewood_water_tree_sparse", ROSEWOOD_WATER_TREE_SPARSE);
			register("morado_tree", MORADO_TREE);
			register("morado_tree_sparse", MORADO_TREE_SPARSE);
			register("morado_water_tree", MORADO_WATER_TREE);
			register("morado_water_tree_sparse", MORADO_WATER_TREE_SPARSE);

			register("dune_rock", DUNE_ROCK);
			register("dune_rock_extra", DUNE_ROCK_EXTRA);
			register("dune_rock_many", DUNE_ROCK_MANY);
			register("fossil_surface", FOSSIL_SURFACE);

			register("patch_yucca_flower", PATCH_YUCCA_FLOWER);
			register("patch_yucca_flower_extra", PATCH_YUCCA_FLOWER_EXTRA);
			register("patch_gilia", PATCH_GILIA);
			register("patch_aloe_vera", PATCH_ALOE_VERA);
			register("patch_aloe_vera_extra", PATCH_ALOE_VERA_EXTRA);
			register("patch_barrel_cactus_dunes", PATCH_BARREL_CACTUS_DUNES);
			register("patch_barrel_cactus_rocky_dunes", PATCH_BARREL_CACTUS_ROCKY_DUNES);
			register("patch_barrel_cactus_flourishing_dunes", PATCH_BARREL_CACTUS_FLOURISHING_DUNES);
			register("patch_barrel_cactus_petrified_dunes", PATCH_BARREL_CACTUS_PETRIFIED_DUNES);
			register("patch_dune_grass", PATCH_DUNE_GRASS);
			register("patch_arid_sprouts", PATCH_ARID_SPROUTS);

			register("yucca_tree", YUCCA_TREE);
			register("yucca_tree_beehive", YUCCA_TREE_BEEHIVE);
			register("yucca_tree_baby", YUCCA_TREE_BABY);
			register("yucca_tree_rocky_dunes", YUCCA_TREE_ROCKY_DUNES);
			register("yucca_tree_savanna", YUCCA_TREE_SAVANNA);
			register("yucca_tree_desert", YUCCA_TREE_DESERT);
			register("yucca_tree_petrified", YUCCA_TREE_PETRIFIED);
		}
	}
}
