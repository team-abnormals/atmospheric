package com.teamabnormals.atmospheric.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.teamabnormals.atmospheric.common.block.AloeVeraBlock;
import com.teamabnormals.atmospheric.common.block.AloeVeraTallBlock;
import com.teamabnormals.atmospheric.common.block.BarrelCactusBlock;
import com.teamabnormals.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.teamabnormals.atmospheric.common.levelgen.feature.*;
import com.teamabnormals.atmospheric.common.levelgen.feature.configurations.LargeDiskConfiguration;
import com.teamabnormals.atmospheric.common.levelgen.feature.configurations.YuccaTreeConfiguration;
import com.teamabnormals.atmospheric.common.levelgen.feature.configurations.YuccaTreeConfiguration.YuccaTreeConfigurationBuilder;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FossilFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
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
	public static final RegistryObject<Feature<LargeDiskConfiguration>> COARSE_DIRT_PATCH = FEATURES.register("coarse_dirt_patch", () -> new CoarseDirtPatchFeature(LargeDiskConfiguration.CODEC));
	public static final RegistryObject<Feature<BlockStateConfiguration>> DUNE_ROCK = FEATURES.register("dune_rock", () -> new DuneRocksFeature(BlockStateConfiguration.CODEC));

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> WARM_MONKEY_BRUSH = FEATURES.register("warm_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 1));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> HOT_MONKEY_BRUSH = FEATURES.register("hot_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 2));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SCALDING_MONKEY_BRUSH = FEATURES.register("scalding_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 3));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> WATER_HYACINTH_PATCH = FEATURES.register("water_hyacinth_patch", () -> new WaterHyacinthPatchFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PASSION_VINE = FEATURES.register("passion_vine", () -> new PassionVineFeature(NoneFeatureConfiguration.CODEC));

	public static final RegistryObject<Feature<TreeConfiguration>> ROSEWOOD_TREE = FEATURES.register("rosewood_tree", () -> new RainforestTreeFeature(TreeConfiguration.CODEC, false));
	public static final RegistryObject<Feature<TreeConfiguration>> ROSEWOOD_WATER_TREE = FEATURES.register("rosewood_water_tree", () -> new RainforestTreeFeature(TreeConfiguration.CODEC, true));
	public static final RegistryObject<Feature<YuccaTreeConfiguration>> YUCCA_TREE = FEATURES.register("yucca_tree", () -> new YuccaTreeFeature(YuccaTreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> ASPEN_TREE = FEATURES.register("aspen_tree", () -> new AspenTreeFeature(TreeConfiguration.CODEC));

	public static final class Configs {
		private static final BeehiveDecorator BEEHIVE_0002 = new BeehiveDecorator(0.002F);
		private static final BeehiveDecorator BEEHIVE_005 = new BeehiveDecorator(0.05F);

		public static final TreeConfiguration ROSEWOOD = createRosewood().build();
		public static final TreeConfiguration ROSEWOOD_BEES_0002 = createRosewood().decorators(List.of(BEEHIVE_0002)).build();
		public static final TreeConfiguration ROSEWOOD_BEES_005 = createRosewood().decorators(List.of(BEEHIVE_005)).build();

		public static final TreeConfiguration MORADO = createMorado().build();
		public static final TreeConfiguration MORADO_BEES_0002 = createMorado().decorators(List.of(BEEHIVE_0002)).build();
		public static final TreeConfiguration MORADO_BEES_005 = createMorado().decorators(List.of(BEEHIVE_005)).build();

		public static final YuccaTreeConfiguration YUCCA = createYucca().build();
		public static final YuccaTreeConfiguration YUCCA_WITH_FLOWERS = createYucca().hasPatch().build();
		public static final YuccaTreeConfiguration YUCCA_BEES_005 = createYucca().decorators(List.of(BEEHIVE_005)).build();
		public static final YuccaTreeConfiguration YUCCA_BEES_005_WITH_FLOWERS = createYucca().hasPatch().decorators(List.of(BEEHIVE_005)).build();
		public static final YuccaTreeConfiguration BABY_YUCCA = createYucca().setBaby().build();
		public static final YuccaTreeConfiguration BABY_YUCCA_WITH_FLOWERS = createYucca().setBaby().hasPatch().build();

		public static final YuccaTreeConfiguration PETRIFIED_YUCCA = new YuccaTreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.ARID_SANDSTONE.get()), BlockStateProvider.simple(AtmosphericBlocks.ARID_SANDSTONE.get()), BlockStateProvider.simple(AtmosphericBlocks.ARID_SANDSTONE_WALL.get()), BlockStateProvider.simple(AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get()), BlockStateProvider.simple(AtmosphericBlocks.ARID_SANDSTONE_WALL.get()), BlockStateProvider.simple(AtmosphericBlocks.ARID_SANDSTONE_WALL.get()), BlockStateProvider.simple(AtmosphericBlocks.ARID_SANDSTONE_WALL.get())).isPetrified().build();
		public static final YuccaTreeConfiguration RED_PETRIFIED_YUCCA = new YuccaTreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.RED_ARID_SANDSTONE.get()), BlockStateProvider.simple(AtmosphericBlocks.RED_ARID_SANDSTONE.get()), BlockStateProvider.simple(AtmosphericBlocks.RED_ARID_SANDSTONE_WALL.get()), BlockStateProvider.simple(AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get()), BlockStateProvider.simple(AtmosphericBlocks.RED_ARID_SANDSTONE_WALL.get()), BlockStateProvider.simple(AtmosphericBlocks.RED_ARID_SANDSTONE_WALL.get()), BlockStateProvider.simple(AtmosphericBlocks.RED_ARID_SANDSTONE_WALL.get())).isPetrified().build();

		public static final TreeConfiguration ASPEN = createCustomTree(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.ASPEN_LOG.get().defaultBlockState(), 3).add(AtmosphericBlocks.WATCHFUL_ASPEN_LOG.get().defaultBlockState(), 2).build()), BlockStateProvider.simple(AtmosphericBlocks.ASPEN_LEAVES.get())).build();
		public static final TreeConfiguration GRIMWOOD = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.GRIMWOOD_LOG.get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(AtmosphericBlocks.GRIMWOOD_LEAVES.get()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build();
		public static final TreeConfiguration KOUSA = (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.KOUSA_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(AtmosphericBlocks.KOUSA_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build();

		private static TreeConfigurationBuilder createRosewood() {
			return createCustomTree(AtmosphericBlocks.ROSEWOOD_LOG.get().defaultBlockState(), AtmosphericBlocks.ROSEWOOD_LEAVES.get().defaultBlockState());
		}

		private static TreeConfigurationBuilder createMorado() {
			return createCustomTree(BlockStateProvider.simple(AtmosphericBlocks.MORADO_LOG.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.MORADO_LEAVES.get().defaultBlockState(), 2).add(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get().defaultBlockState(), 6).build()));
		}

		private static YuccaTreeConfigurationBuilder createYucca() {
			return new YuccaTreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.YUCCA_LOG.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_LEAVES.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_BRANCH.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_BUNDLE.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_FLOWER.get()), BlockStateProvider.simple(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER)), BlockStateProvider.simple(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER)));
		}

		private static TreeConfigurationBuilder createCustomTree(BlockStateProvider logProvider, BlockStateProvider leavesProvider) {
			return new TreeConfigurationBuilder(logProvider, new StraightTrunkPlacer(0, 0, 0), leavesProvider, new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines();
		}

		private static TreeConfigurationBuilder createCustomTree(BlockState logState, BlockState leavesState) {
			return new TreeConfigurationBuilder(BlockStateProvider.simple(logState), new StraightTrunkPlacer(0, 0, 0), BlockStateProvider.simple(leavesState), new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines();
		}
	}

	public static final class AtmosphericConfiguredFeatures {
		public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Atmospheric.MOD_ID);

		private static final List<ResourceLocation> FOSSIL_STRUCTURES = List.of(new ResourceLocation("fossil/spine_1"), new ResourceLocation("fossil/spine_2"), new ResourceLocation("fossil/spine_3"), new ResourceLocation("fossil/spine_4"), new ResourceLocation("fossil/skull_1"), new ResourceLocation("fossil/skull_2"), new ResourceLocation("fossil/skull_3"), new ResourceLocation("fossil/skull_4"));
		private static final List<ResourceLocation> FOSSIL_COAL_STRUCTURES = List.of(new ResourceLocation("fossil/spine_1_coal"), new ResourceLocation("fossil/spine_2_coal"), new ResourceLocation("fossil/spine_3_coal"), new ResourceLocation("fossil/spine_4_coal"), new ResourceLocation("fossil/skull_1_coal"), new ResourceLocation("fossil/skull_2_coal"), new ResourceLocation("fossil/skull_3_coal"), new ResourceLocation("fossil/skull_4_coal"));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD = register("rosewood", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD_BEES_0002 = register("rosewood_bees_0002", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD_BEES_0002));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD_BEES_005 = register("rosewood_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD_BEES_005));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> WATER_ROSEWOOD = register("water_rosewood", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_WATER_TREE.get(), Configs.ROSEWOOD));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO = register("morado", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO_BEES_0002 = register("morado_bees_0002", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO_BEES_0002));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO_BEES_005 = register("morado_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO_BEES_005));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> WATER_MORADO = register("water_morado", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_WATER_TREE.get(), Configs.MORADO));

		public static final RegistryObject<ConfiguredFeature<YuccaTreeConfiguration, ?>> YUCCA = register("yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeConfiguration, ?>> YUCCA_WITH_FLOWERS = register("yucca_with_flowers", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_WITH_FLOWERS));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeConfiguration, ?>> BABY_YUCCA = register("baby_yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.BABY_YUCCA));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeConfiguration, ?>> BABY_YUCCA_WITH_FLOWERS = register("baby_yucca_with_flowers", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.BABY_YUCCA_WITH_FLOWERS));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeConfiguration, ?>> YUCCA_BEES_005 = register("yucca_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_BEES_005));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeConfiguration, ?>> YUCCA_BEES_005_WITH_FLOWERS = register("yucca_bees_005_with_flowers", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_BEES_005_WITH_FLOWERS));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeConfiguration, ?>> PETRIFIED_YUCCA = register("petrified_yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.PETRIFIED_YUCCA));
		public static final RegistryObject<ConfiguredFeature<YuccaTreeConfiguration, ?>> RED_PETRIFIED_YUCCA = register("red_petrified_yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.RED_PETRIFIED_YUCCA));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ASPEN = register("aspen", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> GRIMWOOD = register("grimwood", () -> new ConfiguredFeature<>(Feature.TREE, Configs.GRIMWOOD));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> KOUSA = register("kousa", () -> new ConfiguredFeature<>(Feature.TREE, Configs.KOUSA));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> OAK_BUSH = register("jungle_bush", () -> new ConfiguredFeature<>(Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.OAK_LEAVES), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build()));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_RAINFOREST = register("trees_rainforest", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.FANCY_OAK_CHECKED, 0.03333334F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.OAK_BUSH.getHolder().get(), 0.166666667F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.MORADO_BEES_0002.getHolder().get(), 0.1F)), AtmosphericPlacedFeatures.ROSEWOOD_BEES_0002.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_SPARSE_RAINFOREST = register("trees_sparse_rainforest", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.MORADO_BEES_0002.getHolder().get(), 0.05F)), AtmosphericPlacedFeatures.ROSEWOOD_BEES_0002.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_RAINFOREST_BASIN = register("trees_rainforest_basin", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.FANCY_OAK_CHECKED, 0.03333334F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.OAK_BUSH.getHolder().get(), 0.166666667F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.WATER_MORADO.getHolder().get(), 0.1F)), AtmosphericPlacedFeatures.WATER_ROSEWOOD.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_SPARSE_RAINFOREST_BASIN = register("trees_sparse_rainforest_basin", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.OAK_BUSH.getHolder().get(), 0.5F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.WATER_MORADO.getHolder().get(), 0.05F)), AtmosphericPlacedFeatures.WATER_ROSEWOOD.getHolder().get())));

		public static final RegistryObject<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> PODZOL = register("podzol", () -> new ConfiguredFeature<>(AtmosphericFeatures.PODZOL.get(), new ProbabilityFeatureConfiguration(0.2F)));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> PASSION_VINES = register("passion_vines", () -> new ConfiguredFeature<>(AtmosphericFeatures.PASSION_VINE.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> PATCH_WATER_HYACINTH = register("patch_water_hyacinth", () -> new ConfiguredFeature<>(AtmosphericFeatures.WATER_HYACINTH_PATCH.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_WATERLILY_RAINFOREST = register("patch_waterlily_rainforest", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILY_PAD))))));

		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> WARM_MONKEY_BRUSH = register("warm_monkey_brush", () -> new ConfiguredFeature<>(AtmosphericFeatures.WARM_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> HOT_MONKEY_BRUSH = register("hot_monkey_brush", () -> new ConfiguredFeature<>(AtmosphericFeatures.HOT_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> SCALDING_MONKEY_BRUSH = register("scalding_monkey_brush", () -> new ConfiguredFeature<>(AtmosphericFeatures.SCALDING_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> MONKEY_BRUSH = register("monkey_brush", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.SCALDING_MONKEY_BRUSH.getHolder().get(), 0.166666667F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.HOT_MONKEY_BRUSH.getHolder().get(), 0.333333334F)), AtmosphericPlacedFeatures.WARM_MONKEY_BRUSH.getHolder().get())));

		public static final RegistryObject<ConfiguredFeature<BlockStateConfiguration, ?>> DUNE_ROCK = register("forest_rock", () -> new ConfiguredFeature<>(AtmosphericFeatures.DUNE_ROCK.get(), new BlockStateConfiguration(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState())));
		public static final RegistryObject<ConfiguredFeature<FossilFeatureConfiguration, ?>> SURFACE_FOSSIL = register("surface_fossil", () -> new ConfiguredFeature<>(AtmosphericFeatures.SURFACE_FOSSIL.get(), new FossilFeatureConfiguration(FOSSIL_STRUCTURES, FOSSIL_COAL_STRUCTURES, ProcessorLists.FOSSIL_ROT, ProcessorLists.FOSSIL_COAL, 4)));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_BARREL_CACTUS = register("patch_barrel_cactus", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new RandomizedIntStateProvider(BlockStateProvider.simple(AtmosphericBlocks.BARREL_CACTUS.get()), BarrelCactusBlock.AGE, UniformInt.of(0, 3))), List.of(AtmosphericBlocks.RED_ARID_SAND.get()))));
		public static final RegistryObject<ConfiguredFeature<LargeDiskConfiguration, ?>> PATCH_DUNE_GRASS = register("patch_dune_grass", () -> new ConfiguredFeature<>(AtmosphericFeatures.COARSE_DIRT_PATCH.get(), new LargeDiskConfiguration(Blocks.COARSE_DIRT.defaultBlockState(), UniformInt.of(1, 7), 2, Lists.newArrayList(AtmosphericBlocks.RED_ARID_SAND.get().defaultBlockState(), AtmosphericBlocks.ARID_SAND.get().defaultBlockState()))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_ARID_SPROUTS = register("patch_arid_sprouts", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(32, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.ARID_SPROUTS.get()))))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> FLOWER_FLOURISHING_DUNES = register("flower_flourishing_dunes", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.GILIA.get()))))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_MELON_DUNES = register("patch_melon_dunes", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(64, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.MELON)), BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlocks(List.of(AtmosphericBlocks.ARID_SAND.get(), AtmosphericBlocks.RED_ARID_SAND.get()), new BlockPos(0, -1, 0)))))));

		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_DUNES = register("trees_dunes", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.YUCCA_WITH_FLOWERS.getHolder().get(), 0.25F)), AtmosphericPlacedFeatures.YUCCA.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_PETRIFIED_DUNES = register("trees_petrified_dunes", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.RED_PETRIFIED_YUCCA.getHolder().get(), 0.25F)), AtmosphericPlacedFeatures.PETRIFIED_YUCCA.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_FLOURISHING_DUNES = register("trees_flourishing_dunes", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.BABY_YUCCA_WITH_FLOWERS.getHolder().get(), 0.25F)), AtmosphericPlacedFeatures.BABY_YUCCA.getHolder().get())));

		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_YUCCA_FLOWER = register("patch_yucca_flower", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.YUCCA_FLOWER.get().defaultBlockState(), 1).add(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState(), 2).build())), List.of(AtmosphericBlocks.ARID_SAND.get(), AtmosphericBlocks.RED_ARID_SAND.get()))));

		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_SHORT_ALOE_VERA = register("patch_short_aloe_vera", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 5))), List.of(AtmosphericBlocks.RED_ARID_SAND.get()))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_TALL_ALOE_VERA = register("patch_tall_aloe_vera", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.AGE, 8))), List.of(AtmosphericBlocks.ARID_SAND.get()))));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> PATCH_ALOE_VERA = register("patch_aloe_vera", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.PATCH_TALL_ALOE_VERA.getHolder().get(), 0.5F)), AtmosphericPlacedFeatures.PATCH_SHORT_ALOE_VERA.getHolder().get())));

		public static final RegistryObject<ConfiguredFeature<BlockStateConfiguration, ?>> HOT_SPRINGS_ROCK = register("hot_springs_rock", () -> new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(Blocks.MOSSY_COBBLESTONE.defaultBlockState())));


		private static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String name, Supplier<ConfiguredFeature<FC, F>> feature) {
			return CONFIGURED_FEATURES.register(name, feature);
		}
	}

	public static final class AtmosphericPlacedFeatures {
		public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Atmospheric.MOD_ID);

		public static final RegistryObject<PlacedFeature> OAK_BUSH = register("oak_bush", AtmosphericConfiguredFeatures.OAK_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
		public static final RegistryObject<PlacedFeature> ROSEWOOD_BEES_0002 = register("rosewood_bees_0002", AtmosphericConfiguredFeatures.ROSEWOOD_BEES_0002, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
		public static final RegistryObject<PlacedFeature> MORADO_BEES_0002 = register("morado_bees_0002", AtmosphericConfiguredFeatures.MORADO_BEES_0002, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
		public static final RegistryObject<PlacedFeature> WATER_ROSEWOOD = register("water_rosewood", AtmosphericConfiguredFeatures.WATER_ROSEWOOD, List.of());
		public static final RegistryObject<PlacedFeature> WATER_MORADO = register("water_morado", AtmosphericConfiguredFeatures.WATER_MORADO, List.of());

		public static final RegistryObject<PlacedFeature> YUCCA = register("yucca", AtmosphericConfiguredFeatures.YUCCA, List.of());
		public static final RegistryObject<PlacedFeature> YUCCA_WITH_FLOWERS = register("yucca_with_flowers", AtmosphericConfiguredFeatures.YUCCA_WITH_FLOWERS, List.of());
		public static final RegistryObject<PlacedFeature> BABY_YUCCA = register("baby_yucca", AtmosphericConfiguredFeatures.BABY_YUCCA, List.of());
		public static final RegistryObject<PlacedFeature> BABY_YUCCA_WITH_FLOWERS = register("baby_yucca_with_flowers", AtmosphericConfiguredFeatures.BABY_YUCCA_WITH_FLOWERS, List.of());
		public static final RegistryObject<PlacedFeature> PETRIFIED_YUCCA = register("petrified_yucca", AtmosphericConfiguredFeatures.PETRIFIED_YUCCA, List.of());
		public static final RegistryObject<PlacedFeature> RED_PETRIFIED_YUCCA = register("red_petrified_yucca", AtmosphericConfiguredFeatures.RED_PETRIFIED_YUCCA, List.of());

		public static final RegistryObject<PlacedFeature> TREES_RAINFOREST = register("trees_rainforest", AtmosphericConfiguredFeatures.TREES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(30, 0.1F, 1)));
		public static final RegistryObject<PlacedFeature> BUSHES_SPARSE_RAINFOREST = register("bushes_sparse_rainforest", AtmosphericConfiguredFeatures.OAK_BUSH, VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1F, 3)));
		public static final RegistryObject<PlacedFeature> TREES_SPARSE_RAINFOREST = register("trees_sparse_rainforest", AtmosphericConfiguredFeatures.TREES_SPARSE_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05F, 30)));
		public static final RegistryObject<PlacedFeature> TREES_RAINFOREST_BASIN = register("trees_rainforest_basin", AtmosphericConfiguredFeatures.TREES_RAINFOREST_BASIN, waterTreePlacement(PlacementUtils.countExtra(50, 0.1F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_SPARSE_RAINFOREST_BASIN = register("trees_sparse_rainforest_basin", AtmosphericConfiguredFeatures.TREES_SPARSE_RAINFOREST_BASIN, waterTreePlacement(PlacementUtils.countExtra(5, 0.1F, 5)));

		public static final RegistryObject<PlacedFeature> TREES_DUNES = register("trees_dunes", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_FLOURISHING_DUNES = register("trees_flourishing_dunes", AtmosphericConfiguredFeatures.TREES_FLOURISHING_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.05F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_ROCKY_DUNES = register("trees_rocky_dunes", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_PETRIFIED_DUNES = register("trees_petrified_dunes", AtmosphericConfiguredFeatures.TREES_PETRIFIED_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5F, 2)));

		public static final RegistryObject<PlacedFeature> FLOURISHING_DUNES_YUCCA_TREES = register("flourishing_dunes_yucca_trees", AtmosphericConfiguredFeatures.YUCCA_BEES_005_WITH_FLOWERS, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1)));
		public static final RegistryObject<PlacedFeature> DESERT_YUCCA_TREES = register("desert_yucca_trees", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.005F, 1)));
		public static final RegistryObject<PlacedFeature> WINDSWEPT_SAVANNA_YUCCA_TREES = register("windswept_savanna_yucca_trees", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.125F, 1)));

		public static final RegistryObject<PlacedFeature> WARM_MONKEY_BRUSH = register("warm_monkey_brush", AtmosphericConfiguredFeatures.WARM_MONKEY_BRUSH, List.of());
		public static final RegistryObject<PlacedFeature> HOT_MONKEY_BRUSH = register("hot_monkey_brush", AtmosphericConfiguredFeatures.HOT_MONKEY_BRUSH, List.of());
		public static final RegistryObject<PlacedFeature> SCALDING_MONKEY_BRUSH = register("scalding_monkey_brush", AtmosphericConfiguredFeatures.SCALDING_MONKEY_BRUSH, List.of());
		public static final RegistryObject<PlacedFeature> MONKEY_BRUSH = register("monkey_brush", AtmosphericConfiguredFeatures.MONKEY_BRUSH, List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		public static final RegistryObject<PlacedFeature> PODZOL = register("bamboo", AtmosphericConfiguredFeatures.PODZOL, List.of(NoiseBasedCountPlacement.of(160, 80.0D, 0.3D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PASSION_VINES = register("passion_vines", AtmosphericConfiguredFeatures.PASSION_VINES, List.of(CountPlacement.of(1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(192)), BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_WATER_HYACINTH = register("patch_water_hyacinth", AtmosphericConfiguredFeatures.PATCH_WATER_HYACINTH, List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_WATER_HYACINTH_SPARSE = register("patch_water_hyacinth_sparse", AtmosphericConfiguredFeatures.PATCH_WATER_HYACINTH, List.of(RarityFilter.onAverageOnceEvery(48), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_WATERLILY_RAINFOREST = register("patch_waterlily_rainforest", AtmosphericConfiguredFeatures.PATCH_WATERLILY_RAINFOREST, VegetationPlacements.worldSurfaceSquaredWithCount(1));

		public static final RegistryObject<PlacedFeature> DUNE_ROCK = register("dune_rock", AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> DUNE_ROCK_EXTRA = register("dune_rock_extra", AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> DUNE_ROCK_MANY = register("dune_rock_many", AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_DUNES = register("patch_barrel_cactus_dunes", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.1F, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_ROCKY_DUNES = register("patch_barrel_cactus_rocky_dunes", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_FLOURISHING_DUNES = register("patch_barrel_cactus_flourishing_dunes", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.5F, 4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_PETRIFIED_DUNES = register("patch_barrel_cactus_petrified_dunes", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_MELON_DUNES = register("patch_melon_dunes", AtmosphericConfiguredFeatures.PATCH_MELON_DUNES, List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		public static final RegistryObject<PlacedFeature> PATCH_DUNE_GRASS = register("patch_dune_grass", AtmosphericConfiguredFeatures.PATCH_DUNE_GRASS, List.of(PlacementUtils.countExtra(1, 0.05F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_ARID_SPROUTS = register("patch_arid_sprouts", AtmosphericConfiguredFeatures.PATCH_ARID_SPROUTS, VegetationPlacements.worldSurfaceSquaredWithCount(2));
		public static final RegistryObject<PlacedFeature> FLOWER_FLOURISHING_DUNES = register("flower_flourishing_dunes", AtmosphericConfiguredFeatures.FLOWER_FLOURISHING_DUNES, List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_YUCCA_FLOWER = register("patch_yucca_flower", AtmosphericConfiguredFeatures.PATCH_YUCCA_FLOWER, List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_YUCCA_FLOWER_EXTRA = register("patch_yucca_flower_extra", AtmosphericConfiguredFeatures.PATCH_YUCCA_FLOWER, List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_SHORT_ALOE_VERA = register("patch_short_aloe_vera", AtmosphericConfiguredFeatures.PATCH_SHORT_ALOE_VERA, List.of());
		public static final RegistryObject<PlacedFeature> PATCH_TALL_ALOE_VERA = register("patch_tall_aloe_vera", AtmosphericConfiguredFeatures.PATCH_TALL_ALOE_VERA, List.of());
		public static final RegistryObject<PlacedFeature> PATCH_ALOE_VERA = register("patch_aloe_vera", AtmosphericConfiguredFeatures.PATCH_ALOE_VERA, List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_ALOE_VERA_EXTRA = register("patch_aloe_vera_extra", AtmosphericConfiguredFeatures.PATCH_ALOE_VERA, List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		public static final RegistryObject<PlacedFeature> FOSSIL_DUNES = register("fossil_dunes", AtmosphericConfiguredFeatures.SURFACE_FOSSIL, List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(256)), BiomeFilter.biome()));

		public static final RegistryObject<PlacedFeature> HOT_SPRINGS_ROCK = register("hot_springs_rock", AtmosphericConfiguredFeatures.HOT_SPRINGS_ROCK, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlock(Blocks.GRASS_BLOCK, new BlockPos(0, -1, 0))), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		private static ImmutableList<PlacementModifier> waterTreePlacement(PlacementModifier modifier) {
			return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread()).add(SurfaceWaterDepthFilter.forMaxDepth(10)).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome()).build();
		}

		@SuppressWarnings("unchecked")
		private static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
			return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) feature.getHolder().get(), ImmutableList.copyOf(placementModifiers)));
		}
	}
}
