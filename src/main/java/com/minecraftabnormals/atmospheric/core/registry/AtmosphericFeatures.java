package com.minecraftabnormals.atmospheric.core.registry;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.minecraftabnormals.atmospheric.common.levelgen.feature.*;
import com.minecraftabnormals.atmospheric.common.levelgen.feature.config.LargeSphereReplaceConfig;
import com.minecraftabnormals.atmospheric.common.levelgen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FossilFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Atmospheric.MOD_ID);

	public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> PODZOL = FEATURES.register("podzol", () -> new PodzolFeature(ProbabilityFeatureConfiguration.CODEC));
	public static final RegistryObject<SurfaceFossilFeature> SURFACE_FOSSIL = FEATURES.register("surface_fossil", () -> new SurfaceFossilFeature(FossilFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<LargeSphereReplaceConfig>> COARSE_DIRT_PATCH = FEATURES.register("coarse_dirt_patch", () -> new CoarseDirtPatchFeature(LargeSphereReplaceConfig.CODEC));
	public static final RegistryObject<Feature<BlockStateConfiguration>> DUNE_ROCKS = FEATURES.register("dune_rocks", () -> new DuneRocksFeature(BlockStateConfiguration.CODEC));

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> WARM_MONKEY_BRUSH = FEATURES.register("warm_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 1));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> HOT_MONKEY_BRUSH = FEATURES.register("hot_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 2));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SCALDING_MONKEY_BRUSH = FEATURES.register("scalding_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 3));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> WATER_HYACINTH_PATCH = FEATURES.register("water_hyacinth_patch", () -> new WaterHyacinthPatchFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PASSION_VINE = FEATURES.register("passion_vine", () -> new PassionVineFeature(NoneFeatureConfiguration.CODEC));

	public static final RegistryObject<Feature<TreeConfiguration>> ROSEWOOD_TREE = FEATURES.register("rosewood_tree", () -> new RainforestTreeFeature(TreeConfiguration.CODEC, false));
	public static final RegistryObject<Feature<TreeConfiguration>> ROSEWOOD_WATER_TREE = FEATURES.register("rosewood_water_tree", () -> new RainforestTreeFeature(TreeConfiguration.CODEC, true));
	public static final RegistryObject<Feature<YuccaTreeFeatureConfig>> YUCCA_TREE = FEATURES.register("yucca_tree", () -> new YuccaTreeFeature(YuccaTreeFeatureConfig.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> ASPEN_TREE = FEATURES.register("aspen_tree", () -> new AspenTreeFeature(TreeConfiguration.CODEC));

	public static final class Configs {
		private static final BeehiveDecorator BEEHIVE_0002 = new BeehiveDecorator(0.002F);
		private static final BeehiveDecorator BEEHIVE_002 = new BeehiveDecorator(0.02F);
		private static final BeehiveDecorator BEEHIVE_005 = new BeehiveDecorator(0.05F);
		private static final BeehiveDecorator BEEHIVE = new BeehiveDecorator(1.0F);

		public static final TreeConfiguration ROSEWOOD = createRosewood().build();
		public static final TreeConfiguration ROSEWOOD_BEES_0002 = createRosewood().decorators(List.of(BEEHIVE_0002)).build();
		public static final TreeConfiguration ROSEWOOD_BEES_005 = createRosewood().decorators(List.of(BEEHIVE_005)).build();

		public static final TreeConfiguration MORADO = createMorado().build();
		public static final TreeConfiguration MORADO_BEES_0002 = createMorado().decorators(List.of(BEEHIVE_0002)).build();
		public static final TreeConfiguration MORADO_BEES_005 = createMorado().decorators(List.of(BEEHIVE_005)).build();

		public static final YuccaTreeFeatureConfig YUCCA = createYucca().build();
		public static final YuccaTreeFeatureConfig YUCCA_BEES_005 = createYucca().decorators(List.of(BEEHIVE_005)).build();
		public static final YuccaTreeFeatureConfig BABY_YUCCA = createYucca().setBaby().build();

		public static final TreeConfiguration ASPEN = createCustomTree(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.ASPEN_LOG.get().defaultBlockState(), 3).add(AtmosphericBlocks.WATCHFUL_ASPEN_LOG.get().defaultBlockState(), 2).build()), BlockStateProvider.simple(AtmosphericBlocks.ASPEN_LEAVES.get())).build();
		public static final TreeConfiguration GRIMWOOD = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.GRIMWOOD_LOG.get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(AtmosphericBlocks.GRIMWOOD_LEAVES.get()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build();
		public static final TreeConfiguration KOUSA = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.KOUSA_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(AtmosphericBlocks.KOUSA_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build();

		private static TreeConfigurationBuilder createRosewood() {
			return createCustomTree(AtmosphericBlocks.ROSEWOOD_LOG.get().defaultBlockState(), AtmosphericBlocks.ROSEWOOD_LEAVES.get().defaultBlockState());
		}

		private static TreeConfigurationBuilder createMorado() {
			return createCustomTree(BlockStateProvider.simple(AtmosphericBlocks.MORADO_LOG.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.MORADO_LEAVES.get().defaultBlockState(), 2).add(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get().defaultBlockState(), 6).build()));
		}

		private static YuccaTreeFeatureConfig.Builder createYucca() {
			return new YuccaTreeFeatureConfig.Builder(BlockStateProvider.simple(AtmosphericBlocks.YUCCA_LOG.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_LEAVES.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_BRANCH.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_BUNDLE.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_FLOWER.get()), BlockStateProvider.simple(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER)), BlockStateProvider.simple(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER)));
		}

		private static TreeConfigurationBuilder createCustomTree(BlockStateProvider logProvider, BlockStateProvider leavesProvider) {
			return new TreeConfigurationBuilder(logProvider, new StraightTrunkPlacer(0, 0, 0), leavesProvider, new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines();
		}

		private static TreeConfigurationBuilder createCustomTree(BlockState logState, BlockState leavesState) {
			return new TreeConfigurationBuilder(BlockStateProvider.simple(logState), new StraightTrunkPlacer(0, 0, 0), BlockStateProvider.simple(leavesState), new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines();
		}

//		public static final YuccaTreeFeatureConfig.Builder ARID_YUCCA_TREE_BUILDER = new YuccaTreeFeatureConfig.Builder(BlockStateProvider.simple(States.ARID_SANDSTONE), BlockStateProvider.simple(States.ARID_SANDSTONE), BlockStateProvider.simple(States.ARID_SANDSTONE_WALL), BlockStateProvider.simple(States.ROASTED_YUCCA_BUNDLE), BlockStateProvider.simple(States.ARID_SANDSTONE_WALL), BlockStateProvider.simple(States.ARID_SANDSTONE_WALL), BlockStateProvider.simple(States.ARID_SANDSTONE_WALL));
//		public static final YuccaTreeFeatureConfig.Builder RED_ARID_YUCCA_TREE_BUILDER = new YuccaTreeFeatureConfig.Builder(BlockStateProvider.simple(States.RED_ARID_SANDSTONE), BlockStateProvider.simple(States.RED_ARID_SANDSTONE), BlockStateProvider.simple(States.RED_ARID_SANDSTONE_WALL), BlockStateProvider.simple(States.ROASTED_YUCCA_BUNDLE), BlockStateProvider.simple(States.RED_ARID_SANDSTONE_WALL), BlockStateProvider.simple(States.RED_ARID_SANDSTONE_WALL), BlockStateProvider.simple(States.RED_ARID_SANDSTONE_WALL));
//
//		public static final TreeConfiguration OAK_GROUND_BUSH_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()), BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState()), new BushFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(1), 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES).build();
//
//		public static final YuccaTreeFeatureConfig YUCCA_TREE_CONFIG = YUCCA_TREE_BUILDER.setHeightmap(Heightmap.Types.MOTION_BLOCKING).build();
//		public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_PATCH_CONFIG = YUCCA_TREE_CONFIG.setPatch();
//		public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG = YUCCA_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Decorators.BEEHIVE_005));
//		public static final YuccaTreeFeatureConfig YUCCA_TREE_WITH_MORE_BEEHIVES_AND_PATCH_CONFIG = YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG.setPatch();
//		public static final YuccaTreeFeatureConfig BABY_YUCCA_TREE_CONFIG = YUCCA_TREE_CONFIG.setBaby();
//		public static final YuccaTreeFeatureConfig BABY_YUCCA_TREE_WITH_PATCH_CONFIG = BABY_YUCCA_TREE_CONFIG.setPatch();
//		public static final YuccaTreeFeatureConfig ARID_YUCCA_TREE_CONFIG = ARID_YUCCA_TREE_BUILDER.isPetrified().setHeightmap(Heightmap.Types.MOTION_BLOCKING).build();
//		public static final YuccaTreeFeatureConfig RED_ARID_YUCCA_TREE_CONFIG = RED_ARID_YUCCA_TREE_BUILDER.isPetrified().setHeightmap(Heightmap.Types.MOTION_BLOCKING).build();
//
//		public static final TreeConfiguration ROSEWOOD_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(States.ROSEWOOD_LOG), BlockStateProvider.simple(States.ROSEWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.fixed(0), UniformInt.fixed(0), 0), new StraightTrunkPlacer(0, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().heightmap(Heightmap.Types.MOTION_BLOCKING).build();
//		public static final TreeConfiguration ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG = ROSEWOOD_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Decorators.BEEHIVE_0002));
//		public static final TreeConfiguration ROSEWOOD_TREE_WITH_MORE_BEEHIVES_CONFIG = ROSEWOOD_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Decorators.BEEHIVE_005));
//		public static final TreeConfiguration MORADO_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(States.MORADO_LOG), new WeightedStateProvider().add(States.MORADO_LEAVES, 2).add(States.FLOWERING_MORADO_LEAVES, 6), new BlobFoliagePlacer(UniformInt.fixed(0), UniformInt.fixed(0), 0), new StraightTrunkPlacer(0, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().heightmap(Heightmap.Types.MOTION_BLOCKING).build();
//		public static final TreeConfiguration MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG = MORADO_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Decorators.BEEHIVE_0002));
//		public static final TreeConfiguration MORADO_TREE_WITH_MORE_BEEHIVES_CONFIG = MORADO_TREE_CONFIG.withDecorators(ImmutableList.of(Features.Decorators.BEEHIVE_005));
//
//		public static final TreeConfiguration KOUSA_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(States.KOUSA_LOG), BlockStateProvider.simple(States.KOUSA_LEAVES), new FancyFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Types.MOTION_BLOCKING).build();
//		public static final TreeConfiguration ASPEN_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(new WeightedStateProvider().add(States.ASPEN_LOG, 3).add(States.WATCHFUL_ASPEN_LOG, 2), BlockStateProvider.simple(States.ASPEN_LEAVES), new SpruceFoliagePlacer(UniformInt.of(0, 0), UniformInt.of(0, 0), UniformInt.of(0, 0)), new StraightTrunkPlacer(0, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().heightmap(Heightmap.Types.MOTION_BLOCKING).build();
//		public static final TreeConfiguration GRIMWOOD_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(States.GRIMWOOD_LOG), BlockStateProvider.simple(States.GRIMWOOD_LEAVES), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build();
	}

	public static final class AtmosphericConfiguredFeatures {
		public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Atmospheric.MOD_ID);

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD = register("rosewood", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD_BEES_0002 = register("rosewood_bees_0002", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD_BEES_0002));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD_BEES_005 = register("rosewood_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD_BEES_005));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO = register("morado", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO_BEES_0002 = register("morado_bees_0002", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO_BEES_0002));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO_BEES_005 = register("morado_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO_BEES_005));

		public static final RegistryObject<ConfiguredFeature<YuccaTreeFeatureConfig, ?>> YUCCA = register("yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeFeatureConfig, ?>> BABY_YUCCA = register("baby_yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.BABY_YUCCA));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeFeatureConfig, ?>> YUCCA_BEES_005 = register("yucca_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_BEES_005));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ASPEN = register("aspen", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> GRIMWOOD = register("grimwood", () -> new ConfiguredFeature<>(Feature.TREE, Configs.GRIMWOOD));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> KOUSA = register("kousa", () -> new ConfiguredFeature<>(Feature.TREE, Configs.KOUSA));

//		public static final ConfiguredFeature<?, ?> PODZOL = AtmosphericFeatures.PODZOL.get().configured(new ProbabilityFeatureConfiguration(0.2F)).decorated(FeatureDecorator.COUNT_NOISE_BIASED.configured(new NoiseCountFactorDecoratorConfiguration(160, 80.0D, 0.3D))).decorated(FeatureDecorator.TOP_SOLID_HEIGHTMAP.configured(DecoratorConfiguration.NONE));
//		public static final ConfiguredFeature<?, ?> PASSION_VINES = AtmosphericFeatures.PASSION_VINE.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(1);
//		public static final ConfiguredFeature<?, ?> RAINFOREST_BUSH = Feature.TREE.configured(AtmosphericFeatures.Configs.OAK_GROUND_BUSH_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(2, 0.1F, 1)));
//
//		public static final ConfiguredFeature<?, ?> PATCH_WARM_MONKEY_BRUSH = AtmosphericFeatures.WARM_MONKEY_BRUSH.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(3);
//		public static final ConfiguredFeature<?, ?> PATCH_HOT_MONKEY_BRUSH = AtmosphericFeatures.HOT_MONKEY_BRUSH.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(2);
//		public static final ConfiguredFeature<?, ?> PATCH_SCALDING_MONKEY_BRUSH = AtmosphericFeatures.SCALDING_MONKEY_BRUSH.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(1);
//		public static final ConfiguredFeature<?, ?> PATCH_WATER_HYACINTH = AtmosphericFeatures.WATER_HYACINTH_PATCH.get().configured(NoneFeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(2);
//		public static final ConfiguredFeature<?, ?> PATCH_WATER_HYACINTH_SPARSE = AtmosphericFeatures.WATER_HYACINTH_PATCH.get().configured(NoneFeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(1);
//		public static final ConfiguredFeature<?, ?> PATCH_WATERLILLY_RAINFOREST = Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(BlockStateProvider.simple(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(1);
//
//		public static final ConfiguredFeature<?, ?> OAK_TREE_RAINFOREST = Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(Features.FANCY_OAK_BEES_005.weighted(0.33333334F)), Features.OAK_BEES_005)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.05F, 1)));
//		public static final ConfiguredFeature<?, ?> ROSEWOOD_TREE = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1)));
//		public static final ConfiguredFeature<?, ?> ROSEWOOD_TREE_EXTRA = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(13, 0.1F, 1)));
//		public static final ConfiguredFeature<?, ?> ROSEWOOD_TREE_SPARSE = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 4)));
//		public static final ConfiguredFeature<?, ?> ROSEWOOD_WATER_TREE = AtmosphericFeatures.ROSEWOOD_WATER_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(32, 0, 0)));
//		public static final ConfiguredFeature<?, ?> ROSEWOOD_WATER_TREE_SPARSE = AtmosphericFeatures.ROSEWOOD_WATER_TREE.get().configured(AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(2, 0, 0)));
//		public static final ConfiguredFeature<?, ?> MORADO_TREE = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.05F, 1)));
//		public static final ConfiguredFeature<?, ?> MORADO_TREE_SPARSE = AtmosphericFeatures.ROSEWOOD_TREE.get().configured(AtmosphericFeatures.Configs.MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.05F, 1)));
//		public static final ConfiguredFeature<?, ?> MORADO_WATER_TREE = AtmosphericFeatures.ROSEWOOD_WATER_TREE.get().configured(AtmosphericFeatures.Configs.MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(2, 0, 0)));
//		public static final ConfiguredFeature<?, ?> MORADO_WATER_TREE_SPARSE = AtmosphericFeatures.ROSEWOOD_WATER_TREE.get().configured(AtmosphericFeatures.Configs.MORADO_TREE_WITH_FEW_BEEHIVES_CONFIG).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0, 0)));
//
//		public static final ConfiguredFeature<?, ?> DUNE_ROCK = AtmosphericFeatures.DUNE_ROCKS.get().configured(new BlockStateConfiguration(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState())).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(2);
//		public static final ConfiguredFeature<?, ?> DUNE_ROCK_EXTRA = AtmosphericFeatures.DUNE_ROCKS.get().configured(new BlockStateConfiguration(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState())).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(3);
//		public static final ConfiguredFeature<?, ?> DUNE_ROCK_MANY = AtmosphericFeatures.DUNE_ROCKS.get().configured(new BlockStateConfiguration(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState())).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(6);
//		public static final ConfiguredFeature<?, ?> FOSSIL_SURFACE = AtmosphericFeatures.SURFACE_FOSSIL.get().configured(FeatureConfiguration.NONE).chance(64);
//
//		public static final ConfiguredFeature<?, ?> PATCH_YUCCA_FLOWER = AtmosphericFeatures.YUCCA_FLOWER.get().configured(AtmosphericFeatures.Configs.YUCCA_FLOWER_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(2);
//		public static final ConfiguredFeature<?, ?> PATCH_YUCCA_FLOWER_EXTRA = AtmosphericFeatures.YUCCA_FLOWER.get().configured(AtmosphericFeatures.Configs.YUCCA_FLOWER_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(5);
//		public static final ConfiguredFeature<?, ?> PATCH_GILIA = Feature.FLOWER.configured(AtmosphericFeatures.Configs.GILIA_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(18);
//		public static final ConfiguredFeature<?, ?> PATCH_ALOE_VERA = AtmosphericFeatures.ALOE_VERA.get().configured(AtmosphericFeatures.Configs.ALOE_VERA_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(2);
//		public static final ConfiguredFeature<?, ?> PATCH_ALOE_VERA_EXTRA = AtmosphericFeatures.ALOE_VERA.get().configured(AtmosphericFeatures.Configs.ALOE_VERA_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(6);
//		public static final ConfiguredFeature<?, ?> PATCH_BARREL_CACTUS_DUNES = AtmosphericFeatures.BARREL_CACTUS.get().configured(AtmosphericFeatures.Configs.BARREL_CACTUS_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.075F, 2)));
//		public static final ConfiguredFeature<?, ?> PATCH_BARREL_CACTUS_ROCKY_DUNES = AtmosphericFeatures.BARREL_CACTUS.get().configured(AtmosphericFeatures.Configs.BARREL_CACTUS_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.075F, 1)));
//		public static final ConfiguredFeature<?, ?> PATCH_BARREL_CACTUS_FLOURISHING_DUNES = AtmosphericFeatures.BARREL_CACTUS.get().configured(AtmosphericFeatures.Configs.BARREL_CACTUS_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.5F, 4)));
//		public static final ConfiguredFeature<?, ?> PATCH_BARREL_CACTUS_PETRIFIED_DUNES = AtmosphericFeatures.BARREL_CACTUS.get().configured(AtmosphericFeatures.Configs.BARREL_CACTUS_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1)));
//		public static final ConfiguredFeature<?, ?> PATCH_DUNE_GRASS = AtmosphericFeatures.COARSE_DIRT_PATCH.get().configured(new LargeSphereReplaceConfig(Blocks.COARSE_DIRT.defaultBlockState(), UniformInt.of(7, 1), 2, Lists.newArrayList(AtmosphericBlocks.RED_ARID_SAND.get().defaultBlockState(), AtmosphericBlocks.ARID_SAND.get().defaultBlockState()))).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.8F, 1)));
//		public static final ConfiguredFeature<?, ?> PATCH_ARID_SPROUTS = Feature.RANDOM_PATCH.configured(Configs.ARID_SPROUTS_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE);
//
//		public static final ConfiguredFeature<?, ?> YUCCA_TREE = Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG))).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.25F, 1)));
//		public static final ConfiguredFeature<?, ?> YUCCA_TREE_BEEHIVE = AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_MORE_BEEHIVES_AND_PATCH_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.25F, 1)));
//		public static final ConfiguredFeature<?, ?> YUCCA_TREE_BABY = Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.BABY_YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.BABY_YUCCA_TREE_CONFIG))).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(2, 0.05F, 1)));
//		public static final ConfiguredFeature<?, ?> YUCCA_TREE_ROCKY_DUNES = Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG))).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1)));
//		public static final ConfiguredFeature<?, ?> YUCCA_TREE_SAVANNA = Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG))).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.15F, 1)));
//		public static final ConfiguredFeature<?, ?> YUCCA_TREE_DESERT = Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_WITH_PATCH_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG))).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.005F, 1)));
//		public static final ConfiguredFeature<?, ?> YUCCA_TREE_PETRIFIED = Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.RED_ARID_YUCCA_TREE_CONFIG).weighted(0.25F)), AtmosphericFeatures.YUCCA_TREE.get().configured(AtmosphericFeatures.Configs.ARID_YUCCA_TREE_CONFIG))).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.5F, 2)));

		private static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String name, Supplier<ConfiguredFeature<FC, F>> feature) {
			return CONFIGURED_FEATURES.register(name, feature);
		}
	}

	public static final class AtmosphericPlacedFeatures {
		public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Atmospheric.MOD_ID);

		@SuppressWarnings("unchecked")
		private static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
			return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) feature.getHolder().get(), ImmutableList.copyOf(placementModifiers)));
		}
	}
}
