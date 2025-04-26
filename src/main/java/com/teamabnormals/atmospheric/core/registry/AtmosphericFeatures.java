package com.teamabnormals.atmospheric.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.teamabnormals.atmospheric.common.block.AloeVeraBlock;
import com.teamabnormals.atmospheric.common.block.AloeVeraTallBlock;
import com.teamabnormals.atmospheric.common.block.BarrelCactusBlock;
import com.teamabnormals.atmospheric.common.block.YuccaBranchBlock;
import com.teamabnormals.atmospheric.common.levelgen.feature.*;
import com.teamabnormals.atmospheric.common.levelgen.feature.configurations.LargeDiskConfiguration;
import com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators.*;
import com.teamabnormals.atmospheric.common.levelgen.placement.InSquareCenterPlacement;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
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
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class AtmosphericFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Atmospheric.MOD_ID);
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Atmospheric.MOD_ID);

	public static final DeferredHolder<Feature<?>, Feature<ProbabilityFeatureConfiguration>> PODZOL = FEATURES.register("podzol", () -> new PodzolFeature(ProbabilityFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, SurfaceFossilFeature> SURFACE_FOSSIL = FEATURES.register("surface_fossil", () -> new SurfaceFossilFeature(FossilFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<LargeDiskConfiguration>> COARSE_DIRT_PATCH = FEATURES.register("coarse_dirt_patch", () -> new CoarseDirtPatchFeature(LargeDiskConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> DUNE_ROCK = FEATURES.register("dune_rock", () -> new DuneRocksFeature(BlockStateConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> WARM_MONKEY_BRUSH = FEATURES.register("warm_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 1));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> HOT_MONKEY_BRUSH = FEATURES.register("hot_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 2));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SCALDING_MONKEY_BRUSH = FEATURES.register("scalding_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 3));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> WATER_HYACINTH_PATCH = FEATURES.register("water_hyacinth_patch", () -> new WaterHyacinthPatchFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PASSION_VINE = FEATURES.register("passion_vine", () -> new PassionVineFeature(NoneFeatureConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DRAGON_ROOTS = FEATURES.register("dragon_roots", () -> new DragonRootsFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SUSPICIOUS_ARID_SAND = FEATURES.register("suspicious_arid_sand", () -> new SuspiciousAridSandFeature(NoneFeatureConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> ROSEWOOD_TREE = FEATURES.register("rosewood_tree", () -> new RainforestTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> YUCCA_TREE = FEATURES.register("yucca_tree", () -> new YuccaTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> BABY_YUCCA_TREE = FEATURES.register("baby_yucca_tree", () -> new BabyYuccaTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> ASPEN_TREE = FEATURES.register("aspen_tree", () -> new AspenTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> KOUSA_TREE = FEATURES.register("kousa_tree", () -> new KousaTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> BABY_KOUSA_TREE = FEATURES.register("baby_kousa_tree", () -> new BabyKousaTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> GRIMWOOD_TREE = FEATURES.register("grimwood_tree", () -> new GrimwoodTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> LAUREL_TREE = FEATURES.register("laurel_tree", () -> new LaurelTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> LARGE_LAUREL_TREE = FEATURES.register("large_laurel_tree", () -> new LargeLaurelTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> GIANT_LAUREL_TREE = FEATURES.register("giant_laurel_tree", () -> new GiantLaurelTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> CURRANT_TREE = FEATURES.register("currant_tree", () -> new CurrantTreeFeature(TreeConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> SMALL_BUSH = FEATURES.register("small_bush", () -> new SmallBushFeature(TreeConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<ProbabilityFeatureConfiguration>> CRUSTOSE = FEATURES.register("crustose", () -> new CrustoseFeature(ProbabilityFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<SimpleBlockConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", () -> new FallenLogFeature(SimpleBlockConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<ProbabilityFeatureConfiguration>> SNOWY_BAMBOO = FEATURES.register("snowy_bamboo", () -> new SnowyBambooFeature(ProbabilityFeatureConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<ProbabilityFeatureConfiguration>> COARSE_DIRT = FEATURES.register("coarse_dirt", () -> new CoarseDirtFeature(ProbabilityFeatureConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> OCEAN_FLOOR_RAISER = FEATURES.register("ocean_floor_raiser", () -> new OceanFloorRaiserFeature(NoneFeatureConfiguration.CODEC));

	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> MONKEY_BRUSH = TREE_DECORATOR_TYPES.register("monkey_brush", () -> new TreeDecoratorType<>(MonkeyBrushDecorator.CODEC));
	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> HANGING_CURRANT = TREE_DECORATOR_TYPES.register("hanging_currant", () -> new TreeDecoratorType<>(HangingCurrantDecorator.CODEC));
	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> COBWEB = TREE_DECORATOR_TYPES.register("cobweb", () -> new TreeDecoratorType<>(CobwebDecorator.CODEC));
	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> ORANGES = TREE_DECORATOR_TYPES.register("oranges", () -> new TreeDecoratorType<>(OrangesDecorator.CODEC));
	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> YUCCA_BUNDLE = TREE_DECORATOR_TYPES.register("yucca_bundle", () -> new TreeDecoratorType<>(YuccaBundleDecorator.CODEC));
	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> YUCCA_FLOWERS = TREE_DECORATOR_TYPES.register("yucca_flowers", () -> new TreeDecoratorType<>(YuccaFlowersDecorator.CODEC));
	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> YUCCA_FLOWER_PATCH = TREE_DECORATOR_TYPES.register("yucca_flower_patch", () -> new TreeDecoratorType<>(YuccaFlowerPatchDecorator.CODEC));
	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> ROASTED_YUCCA_BUNDLE = TREE_DECORATOR_TYPES.register("roasted_yucca_bundle", () -> new TreeDecoratorType<>(RoastedYuccaBundleDecorator.CODEC));
	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<?>> EXTEND_PETRIFIED_YUCCA_TREE = TREE_DECORATOR_TYPES.register("extend_petrified_yucca_tree", () -> new TreeDecoratorType<>(ExtendPetrifiedYuccaTreeDecorator.CODEC));

	public static final class Configs {
		private static final MonkeyBrushDecorator MONKEY_BRUSH = new MonkeyBrushDecorator(0.004F);
		private static final BeehiveDecorator BEEHIVE_0002 = new BeehiveDecorator(0.002F);
		private static final BeehiveDecorator BEEHIVE_005 = new BeehiveDecorator(0.05F);

		private static final OrangesDecorator ORANGES_0005 = orangesDecorator(0.005F, false);
		private static final OrangesDecorator ORANGES_08 = orangesDecorator(0.8F, false);
		private static final OrangesDecorator BLOOD_ORANGES_08 = orangesDecorator(0.8F, true);

		private static final YuccaBundleDecorator YUCCA_BUNDLE = new YuccaBundleDecorator(0.0625F, BlockStateProvider.simple(AtmosphericBlocks.YUCCA_BUNDLE.get()), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_BRANCH.get().defaultBlockState().setValue(YuccaBranchBlock.SNAPPED, false)));
		private static final YuccaFlowersDecorator YUCCA_FLOWERS = new YuccaFlowersDecorator(0.125F, new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.YUCCA_FLOWER.get().defaultBlockState(), 3).add(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState(), 1).build()));
		private static final YuccaFlowerPatchDecorator YUCCA_FLOWER_PATCH = new YuccaFlowerPatchDecorator(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.YUCCA_FLOWER.get().defaultBlockState(), 3).add(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState(), 1).build()));
		private static final RoastedYuccaBundleDecorator ROASTED_YUCCA_BUNDLE = new RoastedYuccaBundleDecorator(0.125F, BlockStateProvider.simple(AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get()));

		private static final ExtendPetrifiedYuccaTreeDecorator PETRIFIED_YUCCA_TREE_EXTENDER = new ExtendPetrifiedYuccaTreeDecorator(BlockStateProvider.simple(AtmosphericBlocks.ARID_SANDSTONE.get()));
		private static final ExtendPetrifiedYuccaTreeDecorator RED_PETRIFIED_YUCCA_TREE_EXTENDER = new ExtendPetrifiedYuccaTreeDecorator(BlockStateProvider.simple(AtmosphericBlocks.RED_ARID_SANDSTONE.get()));

		private static OrangesDecorator orangesDecorator(float probability, boolean blood) {
			return new OrangesDecorator(probability, BlockStateProvider.simple(blood ? AtmosphericBlocks.BLOOD_ORANGE.get() : AtmosphericBlocks.ORANGE.get()), (float) 0.25, (float) 0.3);
		}

		public static final TreeConfiguration ROSEWOOD = createRosewood().decorators(List.of(MONKEY_BRUSH)).build();
		public static final TreeConfiguration ROSEWOOD_BEES_0002 = createRosewood().decorators(List.of(BEEHIVE_0002, MONKEY_BRUSH)).build();
		public static final TreeConfiguration ROSEWOOD_BEES_005 = createRosewood().decorators(List.of(BEEHIVE_005, MONKEY_BRUSH)).build();

		public static final TreeConfiguration MORADO = createMorado().decorators(List.of(MONKEY_BRUSH)).build();
		public static final TreeConfiguration MORADO_BEES_0002 = createMorado().decorators(List.of(BEEHIVE_0002, MONKEY_BRUSH)).build();
		public static final TreeConfiguration MORADO_BEES_005 = createMorado().decorators(List.of(BEEHIVE_005, MONKEY_BRUSH)).build();

		public static final TreeConfiguration YUCCA = createYucca().decorators(List.of(YUCCA_FLOWERS, YUCCA_BUNDLE)).build();
		public static final TreeConfiguration YUCCA_WITH_FLOWERS = createYucca().decorators(List.of(YUCCA_FLOWERS, YUCCA_BUNDLE, YUCCA_FLOWER_PATCH)).build();
		public static final TreeConfiguration YUCCA_BEES_005 = createYucca().decorators(List.of(YUCCA_FLOWERS, YUCCA_BUNDLE, BEEHIVE_005)).build();
		public static final TreeConfiguration YUCCA_BEES_005_WITH_FLOWERS = createYucca().decorators(List.of(YUCCA_FLOWERS, YUCCA_BUNDLE, YUCCA_FLOWER_PATCH, BEEHIVE_005)).build();
		public static final TreeConfiguration BABY_YUCCA = createBabyYucca().decorators(List.of(YUCCA_FLOWERS)).build();
		public static final TreeConfiguration BABY_YUCCA_WITH_FLOWERS = createBabyYucca().decorators(List.of(YUCCA_FLOWERS, YUCCA_FLOWER_PATCH)).build();

		public static final TreeConfiguration PETRIFIED_YUCCA = createCustomTree(BlockStateProvider.simple(AtmosphericBlocks.ARID_SANDSTONE.get()), new StraightTrunkPlacer(2, 1, 1), BlockStateProvider.simple(Blocks.AIR)).decorators(List.of(PETRIFIED_YUCCA_TREE_EXTENDER, ROASTED_YUCCA_BUNDLE)).build();
		public static final TreeConfiguration RED_PETRIFIED_YUCCA = createCustomTree(BlockStateProvider.simple(AtmosphericBlocks.RED_ARID_SANDSTONE.get()), new StraightTrunkPlacer(2, 1, 1), BlockStateProvider.simple(Blocks.AIR)).decorators(List.of(RED_PETRIFIED_YUCCA_TREE_EXTENDER, ROASTED_YUCCA_BUNDLE)).build();

		public static final TreeConfiguration ASPEN = createAspen().build();
		public static final TreeConfiguration ASPEN_BEES_0002 = createAspen().decorators(List.of(BEEHIVE_0002)).build();
		public static final TreeConfiguration ASPEN_BEES_005 = createAspen().decorators(List.of(BEEHIVE_005)).build();
		public static final TreeConfiguration ASPEN_WITH_VINES = createAspen().decorators(ImmutableList.of(BEEHIVE_0002, new LeaveVineDecorator(0.25F))).build();

		public static final TreeConfiguration GREEN_ASPEN = createGreenAspen().build();
		public static final TreeConfiguration GREEN_ASPEN_BEES_0002 = createGreenAspen().decorators(List.of(BEEHIVE_0002)).build();
		public static final TreeConfiguration GREEN_ASPEN_BEES_005 = createGreenAspen().decorators(List.of(BEEHIVE_005)).build();
		public static final TreeConfiguration GREEN_ASPEN_WITH_VINES = createGreenAspen().decorators(ImmutableList.of(BEEHIVE_0002, new LeaveVineDecorator(0.25F))).build();

		public static final TreeConfiguration KOUSA = createCustomTree(AtmosphericBlocks.KOUSA_LOG.get(), new StraightTrunkPlacer(4, 2, 1), AtmosphericBlocks.KOUSA_LEAVES.get()).build();
		public static final TreeConfiguration BABY_KOUSA = createCustomTree(AtmosphericBlocks.KOUSA_LOG.get(), new StraightTrunkPlacer(2, 1, 1), AtmosphericBlocks.KOUSA_LEAVES.get()).build();
		public static final TreeConfiguration CURRANT = createCustomTree(AtmosphericBlocks.CURRANT_STALK.get(), new StraightTrunkPlacer(3, 0, 0), AtmosphericBlocks.CURRANT_LEAVES.get()).decorators(List.of(new HangingCurrantDecorator(0.20F))).build();
		public static final TreeConfiguration DEAD_CURRANT = createCustomTree(AtmosphericBlocks.CURRANT_STALK.get(), new StraightTrunkPlacer(1, 1, 1), Blocks.AIR).build();

		public static final TreeConfiguration GRIMWOOD = createCustomTree(AtmosphericBlocks.GRIMWOOD_LOG.get(), new StraightTrunkPlacer(2, 1, 0), AtmosphericBlocks.GRIMWOOD_LEAVES.get()).build();
		public static final TreeConfiguration DEAD_GRIMWOOD = createCustomTree(AtmosphericBlocks.GRIMWOOD_LOG.get(), new StraightTrunkPlacer(2, 1, 0), Blocks.AIR).decorators(List.of(new CobwebDecorator(0.025F))).build();

		public static final TreeConfiguration LAUREL = createLaurel(AtmosphericBlocks.LAUREL_LEAVES.get()).build();
		public static final TreeConfiguration LAUREL_ORANGES_0005 = createLaurel(AtmosphericBlocks.LAUREL_LEAVES.get()).decorators(ImmutableList.of(ORANGES_0005)).build();
		public static final TreeConfiguration LAUREL_ORANGES_08 = createLaurel(AtmosphericBlocks.LAUREL_LEAVES.get()).decorators(ImmutableList.of(ORANGES_08)).build();
		public static final TreeConfiguration LAUREL_WITH_VINES = createLaurel(AtmosphericBlocks.LAUREL_LEAVES.get()).decorators(ImmutableList.of(new LeaveVineDecorator(0.15F))).build();
		public static final TreeConfiguration LAUREL_BLOOD_ORANGES_08 = createLaurel(AtmosphericBlocks.LAUREL_LEAVES.get()).decorators(ImmutableList.of(BLOOD_ORANGES_08)).build();

		public static final TreeConfiguration DRY_LAUREL = createLaurel(AtmosphericBlocks.DRY_LAUREL_LEAVES.get()).build();
		public static final TreeConfiguration DRY_LAUREL_ORANGES_0005 = createLaurel(AtmosphericBlocks.DRY_LAUREL_LEAVES.get()).decorators(ImmutableList.of(ORANGES_0005)).build();
		public static final TreeConfiguration DRY_LAUREL_ORANGES_08 = createLaurel(AtmosphericBlocks.DRY_LAUREL_LEAVES.get()).decorators(ImmutableList.of(ORANGES_08)).build();
		public static final TreeConfiguration DRY_LAUREL_WITH_VINES = createLaurel(AtmosphericBlocks.DRY_LAUREL_LEAVES.get()).decorators(ImmutableList.of(new LeaveVineDecorator(0.15F))).build();
		public static final TreeConfiguration DRY_LAUREL_BLOOD_ORANGES_08 = createLaurel(AtmosphericBlocks.DRY_LAUREL_LEAVES.get()).decorators(ImmutableList.of(BLOOD_ORANGES_08)).build();

		public static final TreeConfiguration DRY_LAUREL_BUSH = createCustomTree(AtmosphericBlocks.LAUREL_LOG.get(), new StraightTrunkPlacer(1, 0, 0), AtmosphericBlocks.DRY_LAUREL_LEAVES.get()).build();

		private static TreeConfigurationBuilder createLaurel(Block leaves) {
			return createCustomTree(AtmosphericBlocks.LAUREL_LOG.get(), new StraightTrunkPlacer(3, 1, 0), leaves);
		}

		private static TreeConfigurationBuilder createRosewood() {
			return createCustomTree(AtmosphericBlocks.ROSEWOOD_LOG.get(), new StraightTrunkPlacer(4, 2, 2), AtmosphericBlocks.ROSEWOOD_LEAVES.get());
		}

		private static TreeConfigurationBuilder createMorado() {
			return createCustomTree(BlockStateProvider.simple(AtmosphericBlocks.MORADO_LOG.get()), new StraightTrunkPlacer(2, 1, 0), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.MORADO_LEAVES.get().defaultBlockState(), 2).add(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get().defaultBlockState(), 6).build()));
		}

		private static TreeConfigurationBuilder createYucca() {
			return createCustomTree(BlockStateProvider.simple(AtmosphericBlocks.YUCCA_LOG.get()), new StraightTrunkPlacer(4, 1, 1), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_LEAVES.get()));
		}

		private static TreeConfigurationBuilder createBabyYucca() {
			return createCustomTree(BlockStateProvider.simple(AtmosphericBlocks.YUCCA_LOG.get()), new StraightTrunkPlacer(3, 1, 0), BlockStateProvider.simple(AtmosphericBlocks.YUCCA_LEAVES.get()));
		}

		private static TreeConfigurationBuilder createAspen(Block leaves) {
			return createCustomTree(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.ASPEN_LOG.get().defaultBlockState(), 4).add(AtmosphericBlocks.WATCHFUL_ASPEN_LOG.get().defaultBlockState(), 1).build()), new StraightTrunkPlacer(13, 5, 6), BlockStateProvider.simple(leaves));
		}

		private static TreeConfigurationBuilder createAspen() {
			return createAspen(AtmosphericBlocks.ASPEN_LEAVES.get());
		}

		private static TreeConfigurationBuilder createGreenAspen() {
			return createAspen(AtmosphericBlocks.GREEN_ASPEN_LEAVES.get());
		}

		private static TreeConfigurationBuilder createCustomTree(Block log, TrunkPlacer trunkPlacer, Block leaves) {
			return createCustomTree(BlockStateProvider.simple(log), trunkPlacer, BlockStateProvider.simple(leaves));
		}

		private static TreeConfigurationBuilder createCustomTree(BlockStateProvider logProvider, TrunkPlacer trunkPlacer, BlockStateProvider leavesProvider) {
			return new TreeConfigurationBuilder(logProvider, trunkPlacer, leavesProvider, new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines();
		}

		private static WeightedStateProvider ominousGrimwoodsBlocks() {
			SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
			builder.add(Blocks.TORCH.defaultBlockState(), 12);
			builder.add(Blocks.LANTERN.defaultBlockState(), 12);

			for (Direction direction : Plane.HORIZONTAL) {
				builder.add(Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.FACING, direction), 1);
				builder.add(Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, false).setValue(CampfireBlock.FACING, direction), 2);
				builder.add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CampfireBlock.FACING, direction), 1);
				builder.add(Blocks.JACK_O_LANTERN.defaultBlockState().setValue(CampfireBlock.FACING, direction), 2);
			}

			return new WeightedStateProvider(builder.build());
		}
	}

	public static final class AtmosphericConfiguredFeatures {
		public static final ResourceKey<ConfiguredFeature<?, ?>> ROSEWOOD = createKey("rosewood");
		public static final ResourceKey<ConfiguredFeature<?, ?>> ROSEWOOD_BEES_0002 = createKey("rosewood_bees_0002");
		public static final ResourceKey<ConfiguredFeature<?, ?>> ROSEWOOD_BEES_005 = createKey("rosewood_bees_005");

		public static final ResourceKey<ConfiguredFeature<?, ?>> MORADO = createKey("morado");
		public static final ResourceKey<ConfiguredFeature<?, ?>> MORADO_BEES_0002 = createKey("morado_bees_0002");
		public static final ResourceKey<ConfiguredFeature<?, ?>> MORADO_BEES_005 = createKey("morado_bees_005");

		public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA = createKey("yucca");
		public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA_WITH_FLOWERS = createKey("yucca_with_flowers");
		public static final ResourceKey<ConfiguredFeature<?, ?>> BABY_YUCCA = createKey("baby_yucca");
		public static final ResourceKey<ConfiguredFeature<?, ?>> BABY_YUCCA_WITH_FLOWERS = createKey("baby_yucca_with_flowers");
		public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA_BEES_005 = createKey("yucca_bees_005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA_BEES_005_WITH_FLOWERS = createKey("yucca_bees_005_with_flowers");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_YUCCA = createKey("petrified_yucca");
		public static final ResourceKey<ConfiguredFeature<?, ?>> RED_PETRIFIED_YUCCA = createKey("red_petrified_yucca");

		public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN = createKey("aspen");
		public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_BEES_0002 = createKey("aspen_bees_0002");
		public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_BEES_005 = createKey("aspen_bees_005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_WITH_VINES = createKey("aspen_with_vines");

		public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ASPEN = createKey("green_aspen");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ASPEN_BEES_0002 = createKey("green_aspen_bees_0002");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ASPEN_BEES_005 = createKey("green_aspen_bees_005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ASPEN_WITH_VINES = createKey("green_aspen_with_vines");

		public static final ResourceKey<ConfiguredFeature<?, ?>> LAUREL = createKey("laurel");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LAUREL_ORANGES_0005 = createKey("laurel_oranges_0005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LAUREL_ORANGES_08 = createKey("laurel_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LAUREL_WITH_VINES = createKey("laurel_with_vines");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LAUREL_BLOOD_ORANGES_08 = createKey("laurel_blood_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_LAUREL = createKey("large_laurel");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_LAUREL_ORANGES_0005 = createKey("large_laurel_oranges_0005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_LAUREL_ORANGES_08 = createKey("large_laurel_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_LAUREL_WITH_VINES = createKey("large_laurel_with_vines");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_LAUREL_BLOOD_ORANGES_08 = createKey("large_laurel_blood_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_LAUREL = createKey("giant_laurel");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_LAUREL_ORANGES_0005 = createKey("giant_laurel_oranges_0005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_LAUREL_ORANGES_08 = createKey("giant_laurel_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_LAUREL_WITH_VINES = createKey("giant_laurel_with_vines");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_LAUREL_BLOOD_ORANGES_08 = createKey("giant_laurel_blood_oranges_08");

		public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_LAUREL = createKey("dry_laurel");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_LAUREL_ORANGES_0005 = createKey("dry_laurel_oranges_0005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_LAUREL_ORANGES_08 = createKey("dry_laurel_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_LAUREL_WITH_VINES = createKey("dry_laurel_with_vines");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_LAUREL_BLOOD_ORANGES_08 = createKey("dry_laurel_blood_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_DRY_LAUREL = createKey("large_dry_laurel");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_DRY_LAUREL_ORANGES_0005 = createKey("large_dry_laurel_oranges_0005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_DRY_LAUREL_ORANGES_08 = createKey("large_dry_laurel_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_DRY_LAUREL_WITH_VINES = createKey("large_dry_laurel_with_vines");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_DRY_LAUREL_BLOOD_ORANGES_08 = createKey("large_dry_laurel_blood_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_DRY_LAUREL = createKey("giant_dry_laurel");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_DRY_LAUREL_ORANGES_0005 = createKey("giant_dry_laurel_oranges_0005");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_DRY_LAUREL_ORANGES_08 = createKey("giant_dry_laurel_oranges_08");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_DRY_LAUREL_WITH_VINES = createKey("giant_dry_laurel_with_vines");
		public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_DRY_LAUREL_BLOOD_ORANGES_08 = createKey("giant_dry_laurel_blood_oranges_08");

		public static final ResourceKey<ConfiguredFeature<?, ?>> KOUSA = createKey("kousa");
		public static final ResourceKey<ConfiguredFeature<?, ?>> BABY_KOUSA = createKey("baby_kousa");
		public static final ResourceKey<ConfiguredFeature<?, ?>> CURRANT = createKey("currant");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_CURRANT = createKey("dead_currant");

		public static final ResourceKey<ConfiguredFeature<?, ?>> GRIMWOOD = createKey("grimwood");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_GRIMWOOD = createKey("dead_grimwood");

		public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BUSH = createKey("oak_bush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK_BUSH = createKey("dark_oak_bush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> MORADO_BUSH = createKey("morado_bush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_LAUREL_BUSH = createKey("dry_laurel_bush");

		// Rainforest

		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_RAINFOREST = createKey("trees_rainforest");
		public static final ResourceKey<ConfiguredFeature<?, ?>> BUSHES_RAINFOREST = createKey("bushes_rainforest");

		public static final ResourceKey<ConfiguredFeature<?, ?>> PODZOL = createKey("podzol");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PASSION_VINES = createKey("passion_vines");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_WATER_HYACINTH = createKey("patch_water_hyacinth");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_WATERLILY = createKey("patch_waterlily_rainforest");

		public static final ResourceKey<ConfiguredFeature<?, ?>> WARM_MONKEY_BRUSH = createKey("warm_monkey_brush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> HOT_MONKEY_BRUSH = createKey("hot_monkey_brush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> SCALDING_MONKEY_BRUSH = createKey("scalding_monkey_brush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> MONKEY_BRUSH = createKey("monkey_brush");

		public static final ResourceKey<ConfiguredFeature<?, ?>> OCEAN_FLOOR_RAISER = createKey("ocean_floor_raiser");

		// Dunes

		public static final ResourceKey<ConfiguredFeature<?, ?>> DUNE_ROCK = createKey("forest_rock");
		public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_FOSSIL = createKey("surface_fossil");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BARREL_CACTUS = createKey("patch_barrel_cactus");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DUNE_GRASS = createKey("patch_dune_grass");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ARID_SPROUTS = createKey("patch_arid_sprouts");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_FLOURISHING_DUNES = createKey("flower_flourishing_dunes");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_MELON_DUNES = createKey("patch_melon_dunes");

		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_DUNES = createKey("trees_dunes");
		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_PETRIFIED_DUNES = createKey("trees_petrified_dunes");
		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_FLOURISHING_DUNES = createKey("trees_flourishing_dunes");

		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_YUCCA_FLOWER = createKey("patch_yucca_flower");

		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SHORT_ALOE_VERA = createKey("patch_short_aloe_vera");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TALL_ALOE_VERA = createKey("patch_tall_aloe_vera");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ALOE_VERA = createKey("patch_aloe_vera");

		public static final ResourceKey<ConfiguredFeature<?, ?>> SUSPICIOUS_ARID_SAND = createKey("suspicious_arid_sand");

		// Aspen Parkland

		public static final ResourceKey<ConfiguredFeature<?, ?>> CRUSTOSE = createKey("crustose");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_CRUSTOSE_LOG = createKey("fallen_crustose_log");
		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_ASPEN_PARKLAND = createKey("trees_aspen_parkland");
		public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_AGAVE = createKey("single_agave");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_AGAVE_LARGE = createKey("patch_agave_large");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_GOLDEN_GROWTHS = createKey("patch_golden_growths");

		// Laurel Forest

		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_LAUREL_FOREST = createKey("trees_laurel_forest");
		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_LAUREL_FOREST_LARGE = createKey("trees_laurel_forest_large");
		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_LAUREL_FOREST_GIANT = createKey("trees_laurel_forest_giant");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_GRASS_LAUREL_FOREST = createKey("patch_grass_laurel_forest");

		// Kousa Jungle

		public static final ResourceKey<ConfiguredFeature<?, ?>> SNOWY_BAMBOO = createKey("snowy_bamboo");
		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_KOUSA_JUNGLE = createKey("trees_kousa_jungle");

		// Grimwoods

		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_GRIMWOODS = createKey("trees_grimwoods");
		public static final ResourceKey<ConfiguredFeature<?, ?>> OMINOUS_BLOCK = createKey("ominous_block");

		// Spiny Thicket

		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SPINY_THICKET = createKey("trees_spiny_thicket");
		public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_YUCCA_FLOWER = createKey("single_yucca_flower");

		// Scrubland

		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SCRUBLAND = createKey("trees_scrubland");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_SCRUBLAND = createKey("flower_scrubland");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DRAGON_ROOTS = createKey("dragon_roots");

		// Hot Springs

		public static final ResourceKey<ConfiguredFeature<?, ?>> HOT_SPRINGS_ROCK = createKey("hot_springs_rock");

		// Generic

		public static final ResourceKey<ConfiguredFeature<?, ?>> COARSE_DIRT = createKey("coarse_dirt");

		public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_BUSH = createKey("birch_bush");

		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DEAD_BUSH = createKey("patch_dead_bush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_LARGE_FERN = createKey("patch_large_fern");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CACTUS = createKey("patch_cactus");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CACTUS_TALL = createKey("patch_cactus_tall");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CACTUS_VERY_TALL = createKey("patch_cactus_very_tall");

		private static final List<ResourceLocation> FOSSIL_STRUCTURES = List.of(ResourceLocation.withDefaultNamespace("fossil/spine_1"), ResourceLocation.withDefaultNamespace("fossil/spine_2"), ResourceLocation.withDefaultNamespace("fossil/spine_3"), ResourceLocation.withDefaultNamespace("fossil/spine_4"), ResourceLocation.withDefaultNamespace("fossil/skull_1"), ResourceLocation.withDefaultNamespace("fossil/skull_2"), ResourceLocation.withDefaultNamespace("fossil/skull_3"), ResourceLocation.withDefaultNamespace("fossil/skull_4"));
		private static final List<ResourceLocation> FOSSIL_COAL_STRUCTURES = List.of(ResourceLocation.withDefaultNamespace("fossil/spine_1_coal"), ResourceLocation.withDefaultNamespace("fossil/spine_2_coal"), ResourceLocation.withDefaultNamespace("fossil/spine_3_coal"), ResourceLocation.withDefaultNamespace("fossil/spine_4_coal"), ResourceLocation.withDefaultNamespace("fossil/skull_1_coal"), ResourceLocation.withDefaultNamespace("fossil/skull_2_coal"), ResourceLocation.withDefaultNamespace("fossil/skull_3_coal"), ResourceLocation.withDefaultNamespace("fossil/skull_4_coal"));

		public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
			HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
			HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

			register(context, ROSEWOOD, AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD);
			register(context, ROSEWOOD_BEES_0002, AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD_BEES_0002);
			register(context, ROSEWOOD_BEES_005, AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD_BEES_005);

			register(context, MORADO, AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO);
			register(context, MORADO_BEES_0002, AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO_BEES_0002);
			register(context, MORADO_BEES_005, AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO_BEES_005);

			register(context, YUCCA, AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA);
			register(context, YUCCA_WITH_FLOWERS, AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_WITH_FLOWERS);
			register(context, BABY_YUCCA, AtmosphericFeatures.BABY_YUCCA_TREE.get(), Configs.BABY_YUCCA);
			register(context, BABY_YUCCA_WITH_FLOWERS, AtmosphericFeatures.BABY_YUCCA_TREE.get(), Configs.BABY_YUCCA_WITH_FLOWERS);
			register(context, YUCCA_BEES_005, AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_BEES_005);
			register(context, YUCCA_BEES_005_WITH_FLOWERS, AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_BEES_005_WITH_FLOWERS);
			register(context, PETRIFIED_YUCCA, AtmosphericFeatures.YUCCA_TREE.get(), Configs.PETRIFIED_YUCCA);
			register(context, RED_PETRIFIED_YUCCA, AtmosphericFeatures.YUCCA_TREE.get(), Configs.RED_PETRIFIED_YUCCA);

			register(context, ASPEN, AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN);
			register(context, ASPEN_BEES_0002, AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN_BEES_0002);
			register(context, ASPEN_BEES_005, AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN_BEES_005);
			register(context, ASPEN_WITH_VINES, AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN_WITH_VINES);

			register(context, GREEN_ASPEN, AtmosphericFeatures.ASPEN_TREE.get(), Configs.GREEN_ASPEN);
			register(context, GREEN_ASPEN_BEES_0002, AtmosphericFeatures.ASPEN_TREE.get(), Configs.GREEN_ASPEN_BEES_0002);
			register(context, GREEN_ASPEN_BEES_005, AtmosphericFeatures.ASPEN_TREE.get(), Configs.GREEN_ASPEN_BEES_005);
			register(context, GREEN_ASPEN_WITH_VINES, AtmosphericFeatures.ASPEN_TREE.get(), Configs.GREEN_ASPEN_WITH_VINES);

			register(context, LAUREL, AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL);
			register(context, LAUREL_ORANGES_0005, AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL_ORANGES_0005);
			register(context, LAUREL_ORANGES_08, AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL_ORANGES_08);
			register(context, LAUREL_WITH_VINES, AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL_WITH_VINES);
			register(context, LAUREL_BLOOD_ORANGES_08, AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL_BLOOD_ORANGES_08);
			register(context, LARGE_LAUREL, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.LAUREL);
			register(context, LARGE_LAUREL_ORANGES_0005, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.LAUREL_ORANGES_0005);
			register(context, LARGE_LAUREL_ORANGES_08, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.LAUREL_ORANGES_08);
			register(context, LARGE_LAUREL_WITH_VINES, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.LAUREL_WITH_VINES);
			register(context, LARGE_LAUREL_BLOOD_ORANGES_08, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.LAUREL_BLOOD_ORANGES_08);
			register(context, GIANT_LAUREL, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.LAUREL);
			register(context, GIANT_LAUREL_ORANGES_0005, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.LAUREL_ORANGES_0005);
			register(context, GIANT_LAUREL_ORANGES_08, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.LAUREL_ORANGES_08);
			register(context, GIANT_LAUREL_WITH_VINES, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.LAUREL_WITH_VINES);
			register(context, GIANT_LAUREL_BLOOD_ORANGES_08, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.LAUREL_BLOOD_ORANGES_08);

			register(context, DRY_LAUREL, AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL);
			register(context, DRY_LAUREL_ORANGES_0005, AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL_ORANGES_0005);
			register(context, DRY_LAUREL_ORANGES_08, AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL_ORANGES_08);
			register(context, DRY_LAUREL_WITH_VINES, AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL_WITH_VINES);
			register(context, DRY_LAUREL_BLOOD_ORANGES_08, AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL_BLOOD_ORANGES_08);
			register(context, LARGE_DRY_LAUREL, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.DRY_LAUREL);
			register(context, LARGE_DRY_LAUREL_ORANGES_0005, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.DRY_LAUREL_ORANGES_0005);
			register(context, LARGE_DRY_LAUREL_ORANGES_08, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.DRY_LAUREL_ORANGES_08);
			register(context, LARGE_DRY_LAUREL_WITH_VINES, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.DRY_LAUREL_WITH_VINES);
			register(context, LARGE_DRY_LAUREL_BLOOD_ORANGES_08, AtmosphericFeatures.LARGE_LAUREL_TREE.get(), Configs.DRY_LAUREL_BLOOD_ORANGES_08);
			register(context, GIANT_DRY_LAUREL, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.DRY_LAUREL);
			register(context, GIANT_DRY_LAUREL_ORANGES_0005, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.DRY_LAUREL_ORANGES_0005);
			register(context, GIANT_DRY_LAUREL_ORANGES_08, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.DRY_LAUREL_ORANGES_08);
			register(context, GIANT_DRY_LAUREL_WITH_VINES, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.DRY_LAUREL_WITH_VINES);
			register(context, GIANT_DRY_LAUREL_BLOOD_ORANGES_08, AtmosphericFeatures.GIANT_LAUREL_TREE.get(), Configs.DRY_LAUREL_BLOOD_ORANGES_08);

			register(context, KOUSA, AtmosphericFeatures.KOUSA_TREE.get(), Configs.KOUSA);
			register(context, BABY_KOUSA, AtmosphericFeatures.BABY_KOUSA_TREE.get(), Configs.BABY_KOUSA);
			register(context, CURRANT, AtmosphericFeatures.CURRANT_TREE.get(), Configs.CURRANT);
			register(context, DEAD_CURRANT, AtmosphericFeatures.CURRANT_TREE.get(), Configs.DEAD_CURRANT);

			register(context, GRIMWOOD, AtmosphericFeatures.GRIMWOOD_TREE.get(), Configs.GRIMWOOD);
			register(context, DEAD_GRIMWOOD, AtmosphericFeatures.GRIMWOOD_TREE.get(), Configs.DEAD_GRIMWOOD);

			register(context, OAK_BUSH, Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.OAK_LEAVES), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build());
			register(context, DARK_OAK_BUSH, Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build());
			register(context, MORADO_BUSH, Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.MORADO_LOG.get()), new StraightTrunkPlacer(1, 0, 0), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.MORADO_LEAVES.get().defaultBlockState(), 2).add(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get().defaultBlockState(), 6).build()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build());
			register(context, DRY_LAUREL_BUSH, AtmosphericFeatures.SMALL_BUSH.get(), Configs.DRY_LAUREL_BUSH);

			// Rainforest

			register(context, TREES_RAINFOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.MORADO_BEES_0002), 0.025F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.ROSEWOOD_BEES_0002)));
			register(context, BUSHES_RAINFOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FANCY_OAK_CHECKED), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.MORADO_BUSH), 0.15F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.OAK_BUSH)));

			register(context, PODZOL, AtmosphericFeatures.PODZOL.get(), new ProbabilityFeatureConfiguration(0.2F));
			register(context, PASSION_VINES, AtmosphericFeatures.PASSION_VINE.get(), NoneFeatureConfiguration.INSTANCE);
			register(context, PATCH_WATER_HYACINTH, AtmosphericFeatures.WATER_HYACINTH_PATCH.get(), NoneFeatureConfiguration.INSTANCE);
			register(context, PATCH_WATERLILY, Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILY_PAD)))));

			register(context, WARM_MONKEY_BRUSH, AtmosphericFeatures.WARM_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE);
			register(context, HOT_MONKEY_BRUSH, AtmosphericFeatures.HOT_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE);
			register(context, SCALDING_MONKEY_BRUSH, AtmosphericFeatures.SCALDING_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE);
			register(context, MONKEY_BRUSH, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.SCALDING_MONKEY_BRUSH), 0.166666667F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.HOT_MONKEY_BRUSH), 0.333333334F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.WARM_MONKEY_BRUSH)));

			register(context, OCEAN_FLOOR_RAISER, AtmosphericFeatures.OCEAN_FLOOR_RAISER.get(), NoneFeatureConfiguration.INSTANCE);

			// Dunes

			register(context, DUNE_ROCK, AtmosphericFeatures.DUNE_ROCK.get(), new BlockStateConfiguration(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState()));
			register(context, SURFACE_FOSSIL, AtmosphericFeatures.SURFACE_FOSSIL.get(), new FossilFeatureConfiguration(FOSSIL_STRUCTURES, FOSSIL_COAL_STRUCTURES, processors.getOrThrow(ProcessorLists.FOSSIL_ROT), processors.getOrThrow(ProcessorLists.FOSSIL_COAL), 4));
			register(context, PATCH_BARREL_CACTUS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new RandomizedIntStateProvider(BlockStateProvider.simple(AtmosphericBlocks.BARREL_CACTUS.get()), BarrelCactusBlock.AGE, UniformInt.of(0, 3))), List.of(Blocks.SAND, Blocks.RED_SAND, AtmosphericBlocks.ARID_SAND.get(), AtmosphericBlocks.RED_ARID_SAND.get())));
			register(context, PATCH_DUNE_GRASS, AtmosphericFeatures.COARSE_DIRT_PATCH.get(), new LargeDiskConfiguration(Blocks.COARSE_DIRT.defaultBlockState(), UniformInt.of(1, 7), 2, Lists.newArrayList(AtmosphericBlocks.RED_ARID_SAND.get().defaultBlockState(), AtmosphericBlocks.ARID_SAND.get().defaultBlockState())));
			register(context, PATCH_ARID_SPROUTS, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(32, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.ARID_SPROUTS.get())))));
			register(context, FLOWER_FLOURISHING_DUNES, Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.GILIA.get())))));
			register(context, PATCH_MELON_DUNES, Feature.RANDOM_PATCH, new RandomPatchConfiguration(64, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.MELON)), BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), List.of(AtmosphericBlocks.ARID_SAND.get(), AtmosphericBlocks.RED_ARID_SAND.get()))))));

			register(context, TREES_DUNES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.YUCCA_WITH_FLOWERS), 0.25F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.YUCCA)));
			register(context, TREES_PETRIFIED_DUNES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.RED_PETRIFIED_YUCCA), 0.25F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.PETRIFIED_YUCCA)));
			register(context, TREES_FLOURISHING_DUNES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.BABY_YUCCA_WITH_FLOWERS), 0.25F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.BABY_YUCCA)));

			register(context, PATCH_YUCCA_FLOWER, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.YUCCA_FLOWER.get().defaultBlockState(), 1).add(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState(), 2).build())), List.of(AtmosphericBlocks.ARID_SAND.get(), AtmosphericBlocks.RED_ARID_SAND.get())));

			register(context, PATCH_SHORT_ALOE_VERA, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 5))), List.of(AtmosphericBlocks.RED_ARID_SAND.get())));
			register(context, PATCH_TALL_ALOE_VERA, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.AGE, 8))), List.of(AtmosphericBlocks.ARID_SAND.get())));
			register(context, PATCH_ALOE_VERA, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.PATCH_TALL_ALOE_VERA), 0.5F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.PATCH_SHORT_ALOE_VERA)));

			register(context, SUSPICIOUS_ARID_SAND, AtmosphericFeatures.SUSPICIOUS_ARID_SAND.get(), NoneFeatureConfiguration.INSTANCE);

			// Aspen Parkland

			register(context, CRUSTOSE, AtmosphericFeatures.CRUSTOSE.get(), new ProbabilityFeatureConfiguration(0.1F));
			register(context, FALLEN_CRUSTOSE_LOG, AtmosphericFeatures.FALLEN_LOG.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.CRUSTOSE_LOG.get())));
			register(context, TREES_ASPEN_PARKLAND, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.MORADO_BUSH), 0.1F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.DRY_LAUREL), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.ASPEN_WITH_VINES), 0.075F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.GREEN_ASPEN_WITH_VINES), 0.025F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.GREEN_ASPEN_BEES_0002), 0.2F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.ASPEN_BEES_0002)));
			register(context, SINGLE_AGAVE, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.AGAVE.get())));
			register(context, PATCH_AGAVE_LARGE, Feature.RANDOM_PATCH, new RandomPatchConfiguration(512, 12, 5, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.AGAVE.get())))));
			register(context, PATCH_GOLDEN_GROWTHS, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(32, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.GOLDEN_GROWTHS.get())))));

			// Laurel Forest

			register(context, TREES_LAUREL_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.DRY_LAUREL_WITH_VINES), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.DRY_LAUREL), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.LAUREL_WITH_VINES), 0.45F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.LAUREL)));
			register(context, TREES_LAUREL_FOREST_LARGE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.LARGE_DRY_LAUREL_WITH_VINES), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.LARGE_DRY_LAUREL), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.LARGE_LAUREL_WITH_VINES), 0.45F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.LARGE_LAUREL)));
			register(context, TREES_LAUREL_FOREST_GIANT, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.GIANT_DRY_LAUREL_WITH_VINES), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.GIANT_DRY_LAUREL), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.GIANT_LAUREL_WITH_VINES), 0.45F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.GIANT_LAUREL)));
			register(context, PATCH_GRASS_LAUREL_FOREST, Feature.RANDOM_PATCH, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.SHORT_GRASS.defaultBlockState(), 1).add(Blocks.FERN.defaultBlockState(), 6)), 32));

			// Kousa Jungle

			register(context, SNOWY_BAMBOO, AtmosphericFeatures.SNOWY_BAMBOO.get(), new ProbabilityFeatureConfiguration(1.0F));
			register(context, TREES_KOUSA_JUNGLE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.BIRCH_CHECKED), 0.3F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.KOUSA)));

			// Grimwoods

			register(context, TREES_GRIMWOODS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.DARK_OAK_BUSH), 0.1F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.GRIMWOOD), 0.0001F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.DEAD_GRIMWOOD)));
			register(context, OMINOUS_BLOCK, Feature.RANDOM_PATCH, new RandomPatchConfiguration(1, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(Configs.ominousGrimwoodsBlocks()), BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT)))));

			// Spiny Thicket

			register(context, TREES_SPINY_THICKET, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.ACACIA_CHECKED), 0.25F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.ROSEWOOD_BEES_0002)));
			register(context, SINGLE_YUCCA_FLOWER, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.YUCCA_FLOWER.get())));

			// Scrubland

			register(context, TREES_SCRUBLAND, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.BABY_YUCCA_WITH_FLOWERS), 0.05F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.MORADO_BUSH_SAND), 0.2F), new WeightedPlacedFeature(placedFeatures.getOrThrow(AtmosphericPlacedFeatures.DRY_LAUREL), 0.08F)), placedFeatures.getOrThrow(AtmosphericPlacedFeatures.DRY_LAUREL_BUSH)));
			register(context, FLOWER_SCRUBLAND, Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.FIRETHORN.get().defaultBlockState(), 1).add(AtmosphericBlocks.FORSYTHIA.get().defaultBlockState(), 1).build())))));
			register(context, DRAGON_ROOTS, AtmosphericFeatures.DRAGON_ROOTS.get(), NoneFeatureConfiguration.INSTANCE);

			// Hot Springs

			register(context, HOT_SPRINGS_ROCK, Feature.FOREST_ROCK, new BlockStateConfiguration(Blocks.MOSSY_COBBLESTONE.defaultBlockState()));

			// Generic

			register(context, COARSE_DIRT, AtmosphericFeatures.COARSE_DIRT.get(), new ProbabilityFeatureConfiguration(0.1F));

			register(context, BIRCH_BUSH, Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.BIRCH_LOG), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.BIRCH_LEAVES), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build());

			register(context, PATCH_DEAD_BUSH, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(Blocks.DEAD_BUSH), 4));
			register(context, PATCH_LARGE_FERN, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LARGE_FERN))));
			register(context, PATCH_CACTUS, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(10, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 3), BlockStateProvider.simple(Blocks.CACTUS)), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO))))));
			register(context, PATCH_CACTUS_TALL, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(10, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(3, 6), BlockStateProvider.simple(Blocks.CACTUS)), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO))))));
			register(context, PATCH_CACTUS_VERY_TALL, Feature.RANDOM_PATCH, new RandomPatchConfiguration(70, 12, 4, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(9, 12), BlockStateProvider.simple(Blocks.CACTUS)), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO))))));
		}

		private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
			return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
		}

		public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
			return ResourceKey.create(Registries.CONFIGURED_FEATURE, Atmospheric.location(name));
		}

		public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
			context.register(key, new ConfiguredFeature<>(feature, config));
		}
	}

	public static final class AtmosphericPlacedFeatures {
		public static final ResourceKey<PlacedFeature> ROSEWOOD_BEES_0002 = createKey("rosewood_bees_0002");
		public static final ResourceKey<PlacedFeature> MORADO_BEES_0002 = createKey("morado_bees_0002");
		public static final ResourceKey<PlacedFeature> YUCCA = createKey("yucca");
		public static final ResourceKey<PlacedFeature> YUCCA_WITH_FLOWERS = createKey("yucca_with_flowers");
		public static final ResourceKey<PlacedFeature> BABY_YUCCA = createKey("baby_yucca");
		public static final ResourceKey<PlacedFeature> BABY_YUCCA_WITH_FLOWERS = createKey("baby_yucca_with_flowers");
		public static final ResourceKey<PlacedFeature> PETRIFIED_YUCCA = createKey("petrified_yucca");
		public static final ResourceKey<PlacedFeature> RED_PETRIFIED_YUCCA = createKey("red_petrified_yucca");
		public static final ResourceKey<PlacedFeature> ASPEN_BEES_0002 = createKey("aspen_bees_0002");
		public static final ResourceKey<PlacedFeature> ASPEN_WITH_VINES = createKey("aspen_with_vines");
		public static final ResourceKey<PlacedFeature> GREEN_ASPEN_BEES_0002 = createKey("green_aspen_bees_0002");
		public static final ResourceKey<PlacedFeature> GREEN_ASPEN_WITH_VINES = createKey("green_aspen_with_vines");
		public static final ResourceKey<PlacedFeature> LAUREL = createKey("laurel");
		public static final ResourceKey<PlacedFeature> LAUREL_WITH_VINES = createKey("laurel_with_vines");
		public static final ResourceKey<PlacedFeature> LARGE_LAUREL = createKey("large_laurel");
		public static final ResourceKey<PlacedFeature> LARGE_LAUREL_WITH_VINES = createKey("large_laurel_with_vines");
		public static final ResourceKey<PlacedFeature> GIANT_LAUREL = createKey("giant_laurel");
		public static final ResourceKey<PlacedFeature> GIANT_LAUREL_WITH_VINES = createKey("giant_laurel_with_vines");
		public static final ResourceKey<PlacedFeature> DRY_LAUREL = createKey("dry_laurel");
		public static final ResourceKey<PlacedFeature> DRY_LAUREL_WITH_VINES = createKey("dry_laurel_with_vines");
		public static final ResourceKey<PlacedFeature> LARGE_DRY_LAUREL = createKey("large_dry_laurel");
		public static final ResourceKey<PlacedFeature> LARGE_DRY_LAUREL_WITH_VINES = createKey("large_dry_laurel_with_vines");
		public static final ResourceKey<PlacedFeature> GIANT_DRY_LAUREL = createKey("giant_dry_laurel");
		public static final ResourceKey<PlacedFeature> GIANT_DRY_LAUREL_WITH_VINES = createKey("giant_dry_laurel_with_vines");
		public static final ResourceKey<PlacedFeature> KOUSA = createKey("kousa");
		public static final ResourceKey<PlacedFeature> GRIMWOOD = createKey("grimwood");
		public static final ResourceKey<PlacedFeature> DEAD_GRIMWOOD = createKey("dead_grimwood");
		public static final ResourceKey<PlacedFeature> DEAD_CURRANT = createKey("dead_currant");

		public static final ResourceKey<PlacedFeature> OAK_BUSH = createKey("oak_bush");
		public static final ResourceKey<PlacedFeature> DARK_OAK_BUSH = createKey("dark_oak_bush");
		public static final ResourceKey<PlacedFeature> MORADO_BUSH = createKey("morado_bush");
		public static final ResourceKey<PlacedFeature> MORADO_BUSH_SAND = createKey("morado_bush_sand");
		public static final ResourceKey<PlacedFeature> DRY_LAUREL_BUSH = createKey("dry_laurel_bush");

		// Rainforest

		public static final ResourceKey<PlacedFeature> TREES_RAINFOREST = createKey("trees_rainforest");
		public static final ResourceKey<PlacedFeature> TREES_SPARSE_RAINFOREST = createKey("trees_sparse_rainforest");
		public static final ResourceKey<PlacedFeature> TREES_RAINFOREST_BASIN = createKey("trees_rainforest_basin");
		public static final ResourceKey<PlacedFeature> TREES_SPARSE_RAINFOREST_BASIN = createKey("trees_sparse_rainforest_basin");

		public static final ResourceKey<PlacedFeature> BUSHES_RAINFOREST = createKey("bushes_rainforest");
		public static final ResourceKey<PlacedFeature> BUSHES_SPARSE_RAINFOREST = createKey("bushes_sparse_rainforest");

		public static final ResourceKey<PlacedFeature> PODZOL = createKey("podzol");
		public static final ResourceKey<PlacedFeature> PASSION_VINES = createKey("passion_vines");
		public static final ResourceKey<PlacedFeature> PATCH_WATER_HYACINTH = createKey("patch_water_hyacinth");
		public static final ResourceKey<PlacedFeature> PATCH_WATER_HYACINTH_SPARSE = createKey("patch_water_hyacinth_sparse");
		public static final ResourceKey<PlacedFeature> PATCH_WATERLILY_RAINFOREST = createKey("patch_waterlily_rainforest");
		public static final ResourceKey<PlacedFeature> PATCH_WATERLILY_RAINFOREST_BASIN = createKey("patch_waterlily_rainforest_basin");

		public static final ResourceKey<PlacedFeature> WARM_MONKEY_BRUSH = createKey("warm_monkey_brush");
		public static final ResourceKey<PlacedFeature> HOT_MONKEY_BRUSH = createKey("hot_monkey_brush");
		public static final ResourceKey<PlacedFeature> SCALDING_MONKEY_BRUSH = createKey("scalding_monkey_brush");
		public static final ResourceKey<PlacedFeature> MONKEY_BRUSH = createKey("monkey_brush");

		public static final ResourceKey<PlacedFeature> OCEAN_FLOOR_RAISER = createKey("ocean_floor_raiser");

		// Dunes

		public static final ResourceKey<PlacedFeature> TREES_DUNES = createKey("trees_dunes");
		public static final ResourceKey<PlacedFeature> TREES_FLOURISHING_DUNES = createKey("trees_flourishing_dunes");
		public static final ResourceKey<PlacedFeature> TREES_ROCKY_DUNES = createKey("trees_rocky_dunes");
		public static final ResourceKey<PlacedFeature> TREES_PETRIFIED_DUNES = createKey("trees_petrified_dunes");
		public static final ResourceKey<PlacedFeature> FLOURISHING_DUNES_YUCCA_TREES = createKey("flourishing_dunes_yucca_trees");

		public static final ResourceKey<PlacedFeature> WOODED_BADLANDS_YUCCA_TREES = createKey("badlands_yucca_trees");
		public static final ResourceKey<PlacedFeature> DESERT_YUCCA_TREES = createKey("desert_yucca_trees");
		public static final ResourceKey<PlacedFeature> WINDSWEPT_SAVANNA_YUCCA_TREES = createKey("windswept_savanna_yucca_trees");

		public static final ResourceKey<PlacedFeature> DUNE_ROCK = createKey("dune_rock");
		public static final ResourceKey<PlacedFeature> DUNE_ROCK_EXTRA = createKey("dune_rock_extra");
		public static final ResourceKey<PlacedFeature> DUNE_ROCK_MANY = createKey("dune_rock_many");

		public static final ResourceKey<PlacedFeature> PATCH_BARREL_CACTUS_DUNES = createKey("patch_barrel_cactus_dunes");
		public static final ResourceKey<PlacedFeature> PATCH_BARREL_CACTUS_ROCKY_DUNES = createKey("patch_barrel_cactus_rocky_dunes");
		public static final ResourceKey<PlacedFeature> PATCH_BARREL_CACTUS_FLOURISHING_DUNES = createKey("patch_barrel_cactus_flourishing_dunes");
		public static final ResourceKey<PlacedFeature> PATCH_BARREL_CACTUS_SPINY_THICKET = createKey("patch_barrel_cactus_spiny_thicket");
		public static final ResourceKey<PlacedFeature> PATCH_BARREL_CACTUS_SCRUBLAND = createKey("patch_barrel_cactus_scrubland");
		public static final ResourceKey<PlacedFeature> PATCH_MELON_DUNES = createKey("patch_melon_dunes");

		public static final ResourceKey<PlacedFeature> PATCH_DUNE_GRASS = createKey("patch_dune_grass");
		public static final ResourceKey<PlacedFeature> PATCH_ARID_SPROUTS = createKey("patch_arid_sprouts");
		public static final ResourceKey<PlacedFeature> FLOWER_FLOURISHING_DUNES = createKey("flower_flourishing_dunes");
		public static final ResourceKey<PlacedFeature> PATCH_YUCCA_FLOWER = createKey("patch_yucca_flower");
		public static final ResourceKey<PlacedFeature> PATCH_YUCCA_FLOWER_EXTRA = createKey("patch_yucca_flower_extra");
		public static final ResourceKey<PlacedFeature> PATCH_SHORT_ALOE_VERA = createKey("patch_short_aloe_vera");
		public static final ResourceKey<PlacedFeature> PATCH_TALL_ALOE_VERA = createKey("patch_tall_aloe_vera");
		public static final ResourceKey<PlacedFeature> PATCH_ALOE_VERA = createKey("patch_aloe_vera");
		public static final ResourceKey<PlacedFeature> PATCH_ALOE_VERA_EXTRA = createKey("patch_aloe_vera_extra");

		public static final ResourceKey<PlacedFeature> FOSSIL_DUNES = createKey("fossil_dunes");

		public static final ResourceKey<PlacedFeature> SUSPICIOUS_ARID_SAND = createKey("suspicious_arid_sand");

		// Aspen Parkland

		public static final ResourceKey<PlacedFeature> CRUSTOSE = createKey("crustose");
		public static final ResourceKey<PlacedFeature> FALLEN_CRUSTOSE_LOG = createKey("fallen_crustose_log");
		public static final ResourceKey<PlacedFeature> SINGLE_AGAVE = createKey("single_agave");
		public static final ResourceKey<PlacedFeature> PATCH_AGAVE_LARGE = createKey("patch_agave_large");
		public static final ResourceKey<PlacedFeature> PATCH_AGAVE_WOODED_BADLANDS = createKey("patch_agave_wooded_badlands");
		public static final ResourceKey<PlacedFeature> PATCH_GOLDEN_GROWTHS = createKey("patch_golden_growths");
		public static final ResourceKey<PlacedFeature> TREES_ASPEN_PARKLAND = createKey("trees_aspen_parkland");

		// Laurel Forest

		public static final ResourceKey<PlacedFeature> TREES_LAUREL_FOREST = createKey("trees_laurel_forest");
		public static final ResourceKey<PlacedFeature> TREES_LAUREL_FOREST_LARGE = createKey("trees_laurel_forest_large");
		public static final ResourceKey<PlacedFeature> TREES_LAUREL_FOREST_GIANT = createKey("trees_laurel_forest_giant");
		public static final ResourceKey<PlacedFeature> COARSE_DIRT = createKey("coarse_dirt");
		public static final ResourceKey<PlacedFeature> COARSE_DIRT_LAUREL_FOREST = createKey("coarse_dirt_laurel_forest");
		public static final ResourceKey<PlacedFeature> PATCH_GRASS_LAUREL_FOREST = createKey("patch_grass_laurel_forest");
		public static final ResourceKey<PlacedFeature> PATCH_DEAD_BUSH_LAUREL_FOREST = createKey("patch_dead_bush_laurel_forest");

		// Kousa Jungle

		public static final ResourceKey<PlacedFeature> SNOWY_BAMBOO = createKey("snowy_bamboo");
		public static final ResourceKey<PlacedFeature> TREES_KOUSA_JUNGLE = createKey("trees_kousa_jungle");
		public static final ResourceKey<PlacedFeature> CURRANT = createKey("currant");
		public static final ResourceKey<PlacedFeature> PATCH_LARGE_FERN_KOUSA = createKey("patch_large_fern_kousa");
		public static final ResourceKey<PlacedFeature> BIRCH_BUSH = createKey("birch_bush");

		// Spiny Thicket

		public static final ResourceKey<PlacedFeature> TREES_SPINY_THICKET = createKey("trees_spiny_thicket");
		public static final ResourceKey<PlacedFeature> SINGLE_YUCCA_FLOWER = createKey("single_yucca_flower");
		public static final ResourceKey<PlacedFeature> PATCH_CACTUS_SCRUBLAND = createKey("patch_cactus_scrubland");
		public static final ResourceKey<PlacedFeature> PATCH_CACTUS_SPINY_THICKET = createKey("patch_cactus_spiny_thicket");
		public static final ResourceKey<PlacedFeature> PATCH_CACTUS_SPINIER_THICKET = createKey("patch_cactus_spinier_thicket");

		// Scrubland

		public static final ResourceKey<PlacedFeature> TREES_SCRUBLAND = createKey("trees_scrubland");
		public static final ResourceKey<PlacedFeature> FLOWER_SCRUBLAND = createKey("flower_scrubland");
		public static final ResourceKey<PlacedFeature> PATCH_ARID_SPROUTS_RARE = createKey("patch_arid_sprouts_rare");
		public static final ResourceKey<PlacedFeature> DRAGON_ROOTS = createKey("dragon_roots");

		// Grimwoods

		public static final ResourceKey<PlacedFeature> TREES_GRIMWOODS = createKey("trees_grimwoods");
		public static final ResourceKey<PlacedFeature> OMINOUS_BLOCK = createKey("ominous_block");

		// Hot Springs

		public static final ResourceKey<PlacedFeature> HOT_SPRINGS_ROCK = createKey("hot_springs_rock");

		public static void bootstrap(BootstrapContext<PlacedFeature> context) {
			register(context, ROSEWOOD_BEES_0002, AtmosphericConfiguredFeatures.ROSEWOOD_BEES_0002, List.of());
			register(context, MORADO_BEES_0002, AtmosphericConfiguredFeatures.MORADO_BEES_0002, List.of());
			register(context, YUCCA, AtmosphericConfiguredFeatures.YUCCA, List.of());
			register(context, YUCCA_WITH_FLOWERS, AtmosphericConfiguredFeatures.YUCCA_WITH_FLOWERS, List.of());
			register(context, BABY_YUCCA, AtmosphericConfiguredFeatures.BABY_YUCCA, List.of());
			register(context, BABY_YUCCA_WITH_FLOWERS, AtmosphericConfiguredFeatures.BABY_YUCCA_WITH_FLOWERS, List.of());
			register(context, PETRIFIED_YUCCA, AtmosphericConfiguredFeatures.PETRIFIED_YUCCA, List.of());
			register(context, RED_PETRIFIED_YUCCA, AtmosphericConfiguredFeatures.RED_PETRIFIED_YUCCA, List.of());
			register(context, ASPEN_BEES_0002, AtmosphericConfiguredFeatures.ASPEN_BEES_0002, List.of());
			register(context, ASPEN_WITH_VINES, AtmosphericConfiguredFeatures.ASPEN_WITH_VINES, List.of());
			register(context, GREEN_ASPEN_BEES_0002, AtmosphericConfiguredFeatures.GREEN_ASPEN_BEES_0002, List.of());
			register(context, GREEN_ASPEN_WITH_VINES, AtmosphericConfiguredFeatures.GREEN_ASPEN_WITH_VINES, List.of());
			register(context, LAUREL, AtmosphericConfiguredFeatures.LAUREL_ORANGES_0005, List.of());
			register(context, LAUREL_WITH_VINES, AtmosphericConfiguredFeatures.LAUREL_WITH_VINES, List.of());
			register(context, LARGE_LAUREL, AtmosphericConfiguredFeatures.LARGE_LAUREL_ORANGES_0005, List.of());
			register(context, LARGE_LAUREL_WITH_VINES, AtmosphericConfiguredFeatures.LARGE_LAUREL_WITH_VINES, List.of());
			register(context, GIANT_LAUREL, AtmosphericConfiguredFeatures.GIANT_LAUREL_ORANGES_0005, List.of());
			register(context, GIANT_LAUREL_WITH_VINES, AtmosphericConfiguredFeatures.GIANT_LAUREL_WITH_VINES, List.of());
			register(context, DRY_LAUREL, AtmosphericConfiguredFeatures.DRY_LAUREL_ORANGES_0005, List.of());
			register(context, DRY_LAUREL_WITH_VINES, AtmosphericConfiguredFeatures.DRY_LAUREL_WITH_VINES, List.of());
			register(context, LARGE_DRY_LAUREL, AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL_ORANGES_0005, List.of());
			register(context, LARGE_DRY_LAUREL_WITH_VINES, AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL_WITH_VINES, List.of());
			register(context, GIANT_DRY_LAUREL, AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL_ORANGES_0005, List.of());
			register(context, GIANT_DRY_LAUREL_WITH_VINES, AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL_WITH_VINES, List.of());
			register(context, KOUSA, AtmosphericConfiguredFeatures.KOUSA, List.of());
			register(context, GRIMWOOD, AtmosphericConfiguredFeatures.GRIMWOOD, List.of());
			register(context, DEAD_GRIMWOOD, AtmosphericConfiguredFeatures.DEAD_GRIMWOOD, List.of());
			register(context, DEAD_CURRANT, AtmosphericConfiguredFeatures.DEAD_CURRANT, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.2F, 15)));

			register(context, OAK_BUSH, AtmosphericConfiguredFeatures.OAK_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
			register(context, DARK_OAK_BUSH, AtmosphericConfiguredFeatures.DARK_OAK_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING)));
			register(context, MORADO_BUSH, AtmosphericConfiguredFeatures.MORADO_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
			register(context, MORADO_BUSH_SAND, AtmosphericConfiguredFeatures.MORADO_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.DEAD_BUSH)));
			register(context, DRY_LAUREL_BUSH, AtmosphericConfiguredFeatures.DRY_LAUREL_BUSH, List.of());

			// Rainforest

			register(context, TREES_RAINFOREST, AtmosphericConfiguredFeatures.TREES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(30, 0.1F, 1)));
			register(context, TREES_SPARSE_RAINFOREST, AtmosphericConfiguredFeatures.TREES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 30)));
			register(context, TREES_RAINFOREST_BASIN, AtmosphericConfiguredFeatures.TREES_RAINFOREST, waterTreePlacement(PlacementUtils.countExtra(50, 0.1F, 1)));
			register(context, TREES_SPARSE_RAINFOREST_BASIN, AtmosphericConfiguredFeatures.TREES_RAINFOREST, waterTreePlacement(PlacementUtils.countExtra(3, 0.1F, 5)));

			register(context, BUSHES_RAINFOREST, AtmosphericConfiguredFeatures.BUSHES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(12, 0.1F, 1)));
			register(context, BUSHES_SPARSE_RAINFOREST, AtmosphericConfiguredFeatures.BUSHES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1F, 3)));

			register(context, PODZOL, AtmosphericConfiguredFeatures.PODZOL, List.of(NoiseBasedCountPlacement.of(160, 80.0D, 0.3D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, PASSION_VINES, AtmosphericConfiguredFeatures.PASSION_VINES, List.of(CountPlacement.of(1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(192)), BiomeFilter.biome()));
			register(context, PATCH_WATER_HYACINTH, AtmosphericConfiguredFeatures.PATCH_WATER_HYACINTH, List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, PATCH_WATER_HYACINTH_SPARSE, AtmosphericConfiguredFeatures.PATCH_WATER_HYACINTH, List.of(RarityFilter.onAverageOnceEvery(48), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, PATCH_WATERLILY_RAINFOREST, AtmosphericConfiguredFeatures.PATCH_WATERLILY, VegetationPlacements.worldSurfaceSquaredWithCount(1));
			register(context, PATCH_WATERLILY_RAINFOREST_BASIN, AtmosphericConfiguredFeatures.PATCH_WATERLILY, VegetationPlacements.worldSurfaceSquaredWithCount(4));

			register(context, WARM_MONKEY_BRUSH, AtmosphericConfiguredFeatures.WARM_MONKEY_BRUSH, List.of());
			register(context, HOT_MONKEY_BRUSH, AtmosphericConfiguredFeatures.HOT_MONKEY_BRUSH, List.of());
			register(context, SCALDING_MONKEY_BRUSH, AtmosphericConfiguredFeatures.SCALDING_MONKEY_BRUSH, List.of());
			register(context, MONKEY_BRUSH, AtmosphericConfiguredFeatures.MONKEY_BRUSH, List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

			register(context, OCEAN_FLOOR_RAISER, AtmosphericConfiguredFeatures.OCEAN_FLOOR_RAISER, List.of(InSquareCenterPlacement.INSTANCE, PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));

			// Dunes

			register(context, TREES_DUNES, AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1)));
			register(context, TREES_FLOURISHING_DUNES, AtmosphericConfiguredFeatures.TREES_FLOURISHING_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.05F, 1)));
			register(context, TREES_ROCKY_DUNES, AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
			register(context, TREES_PETRIFIED_DUNES, AtmosphericConfiguredFeatures.TREES_PETRIFIED_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5F, 2)));
			register(context, FLOURISHING_DUNES_YUCCA_TREES, AtmosphericConfiguredFeatures.YUCCA_BEES_005_WITH_FLOWERS, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1)));

			register(context, WOODED_BADLANDS_YUCCA_TREES, AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1), Blocks.OAK_SAPLING));
			register(context, DESERT_YUCCA_TREES, AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.005F, 1)));
			register(context, WINDSWEPT_SAVANNA_YUCCA_TREES, AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.125F, 1)));

			register(context, DUNE_ROCK, AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, DUNE_ROCK_EXTRA, AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, DUNE_ROCK_MANY, AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

			register(context, PATCH_BARREL_CACTUS_DUNES, AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.05F, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_BARREL_CACTUS_ROCKY_DUNES, AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.025F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_BARREL_CACTUS_FLOURISHING_DUNES, AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.5F, 3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_BARREL_CACTUS_SPINY_THICKET, AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.5F, 4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_BARREL_CACTUS_SCRUBLAND, AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.1F, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_MELON_DUNES, AtmosphericConfiguredFeatures.PATCH_MELON_DUNES, List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

			register(context, PATCH_DUNE_GRASS, AtmosphericConfiguredFeatures.PATCH_DUNE_GRASS, List.of(PlacementUtils.countExtra(1, 0.05F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_ARID_SPROUTS, AtmosphericConfiguredFeatures.PATCH_ARID_SPROUTS, VegetationPlacements.worldSurfaceSquaredWithCount(2));
			register(context, FLOWER_FLOURISHING_DUNES, AtmosphericConfiguredFeatures.FLOWER_FLOURISHING_DUNES, List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_YUCCA_FLOWER, AtmosphericConfiguredFeatures.PATCH_YUCCA_FLOWER, List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_YUCCA_FLOWER_EXTRA, AtmosphericConfiguredFeatures.PATCH_YUCCA_FLOWER, List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_SHORT_ALOE_VERA, AtmosphericConfiguredFeatures.PATCH_SHORT_ALOE_VERA, List.of());
			register(context, PATCH_TALL_ALOE_VERA, AtmosphericConfiguredFeatures.PATCH_TALL_ALOE_VERA, List.of());
			register(context, PATCH_ALOE_VERA, AtmosphericConfiguredFeatures.PATCH_ALOE_VERA, List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_ALOE_VERA_EXTRA, AtmosphericConfiguredFeatures.PATCH_ALOE_VERA, List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

			register(context, FOSSIL_DUNES, AtmosphericConfiguredFeatures.SURFACE_FOSSIL, List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(256)), BiomeFilter.biome()));

			register(context, SUSPICIOUS_ARID_SAND, AtmosphericConfiguredFeatures.SUSPICIOUS_ARID_SAND, List.of(PlacementUtils.HEIGHTMAP));

			// Aspen Parkland

			register(context, CRUSTOSE, AtmosphericConfiguredFeatures.CRUSTOSE, List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, FALLEN_CRUSTOSE_LOG, AtmosphericConfiguredFeatures.FALLEN_CRUSTOSE_LOG, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, SINGLE_AGAVE, AtmosphericConfiguredFeatures.SINGLE_AGAVE, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_AGAVE_LARGE, AtmosphericConfiguredFeatures.PATCH_AGAVE_LARGE, List.of(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_AGAVE_WOODED_BADLANDS, AtmosphericConfiguredFeatures.PATCH_AGAVE_LARGE, List.of(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_GOLDEN_GROWTHS, AtmosphericConfiguredFeatures.PATCH_GOLDEN_GROWTHS, VegetationPlacements.worldSurfaceSquaredWithCount(4));
			register(context, TREES_ASPEN_PARKLAND, AtmosphericConfiguredFeatures.TREES_ASPEN_PARKLAND, VegetationPlacements.treePlacement(PlacementUtils.countExtra(12, 0.1F, 1)));

			// Laurel Forest

			register(context, TREES_LAUREL_FOREST, AtmosphericConfiguredFeatures.TREES_LAUREL_FOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
			register(context, TREES_LAUREL_FOREST_LARGE, AtmosphericConfiguredFeatures.TREES_LAUREL_FOREST_LARGE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
			register(context, TREES_LAUREL_FOREST_GIANT, AtmosphericConfiguredFeatures.TREES_LAUREL_FOREST_GIANT, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5F, 1)));
			register(context, COARSE_DIRT, AtmosphericConfiguredFeatures.COARSE_DIRT, List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, COARSE_DIRT_LAUREL_FOREST, AtmosphericConfiguredFeatures.COARSE_DIRT, List.of(CountPlacement.of(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, PATCH_GRASS_LAUREL_FOREST, AtmosphericConfiguredFeatures.PATCH_GRASS_LAUREL_FOREST, List.of(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, PATCH_DEAD_BUSH_LAUREL_FOREST, AtmosphericConfiguredFeatures.PATCH_DEAD_BUSH, VegetationPlacements.worldSurfaceSquaredWithCount(8));

			// Kousa Jungle

			register(context, SNOWY_BAMBOO, AtmosphericConfiguredFeatures.SNOWY_BAMBOO, List.of(NoiseBasedCountPlacement.of(120, 2.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, TREES_KOUSA_JUNGLE, AtmosphericConfiguredFeatures.TREES_KOUSA_JUNGLE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(20, 0.1F, 3)));
			register(context, CURRANT, AtmosphericConfiguredFeatures.CURRANT, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.025F, 8)));
			register(context, PATCH_LARGE_FERN_KOUSA, AtmosphericConfiguredFeatures.PATCH_LARGE_FERN, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, BIRCH_BUSH, AtmosphericConfiguredFeatures.BIRCH_BUSH, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1), Blocks.BIRCH_SAPLING));

			// Spiny Thicket

			register(context, TREES_SPINY_THICKET, AtmosphericConfiguredFeatures.TREES_SPINY_THICKET, VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.1F, 5)));
			register(context, SINGLE_YUCCA_FLOWER, AtmosphericConfiguredFeatures.SINGLE_YUCCA_FLOWER, List.of(CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_CACTUS_SCRUBLAND, AtmosphericConfiguredFeatures.PATCH_CACTUS, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_CACTUS_SPINY_THICKET, AtmosphericConfiguredFeatures.PATCH_CACTUS_TALL, List.of(CountPlacement.of(9), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_CACTUS_SPINIER_THICKET, AtmosphericConfiguredFeatures.PATCH_CACTUS_VERY_TALL, List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

			// Scrubland

			register(context, TREES_SCRUBLAND, AtmosphericConfiguredFeatures.TREES_SCRUBLAND, VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.25F, 2)));
			register(context, FLOWER_SCRUBLAND, AtmosphericConfiguredFeatures.FLOWER_SCRUBLAND, List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
			register(context, PATCH_ARID_SPROUTS_RARE, AtmosphericConfiguredFeatures.PATCH_ARID_SPROUTS, List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
			register(context, DRAGON_ROOTS, AtmosphericConfiguredFeatures.DRAGON_ROOTS, List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

			// Grimwoods

			register(context, TREES_GRIMWOODS, AtmosphericConfiguredFeatures.TREES_GRIMWOODS, VegetationPlacements.treePlacement(PlacementUtils.countExtra(40, 0.1F, 3)));
			register(context, OMINOUS_BLOCK, AtmosphericConfiguredFeatures.OMINOUS_BLOCK, List.of(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

			// Hot Springs

			register(context, HOT_SPRINGS_ROCK, AtmosphericConfiguredFeatures.HOT_SPRINGS_ROCK, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK)), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		}

		private static ImmutableList<PlacementModifier> waterTreePlacement(PlacementModifier modifier) {
			return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread()).add(SurfaceWaterDepthFilter.forMaxDepth(10)).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome()).build();
		}

		public static ResourceKey<PlacedFeature> createKey(String name) {
			return ResourceKey.create(Registries.PLACED_FEATURE, Atmospheric.location(name));
		}

		public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
			context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature), modifiers));
		}

		public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
			register(context, key, feature, List.of(modifiers));
		}

	}
}
