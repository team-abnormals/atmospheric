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
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Atmospheric.MOD_ID);
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Atmospheric.MOD_ID);

	public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> PODZOL = FEATURES.register("podzol", () -> new PodzolFeature(ProbabilityFeatureConfiguration.CODEC));
	public static final RegistryObject<SurfaceFossilFeature> SURFACE_FOSSIL = FEATURES.register("surface_fossil", () -> new SurfaceFossilFeature(FossilFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<LargeDiskConfiguration>> COARSE_DIRT_PATCH = FEATURES.register("coarse_dirt_patch", () -> new CoarseDirtPatchFeature(LargeDiskConfiguration.CODEC));
	public static final RegistryObject<Feature<BlockStateConfiguration>> DUNE_ROCK = FEATURES.register("dune_rock", () -> new DuneRocksFeature(BlockStateConfiguration.CODEC));

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> WARM_MONKEY_BRUSH = FEATURES.register("warm_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 1));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> HOT_MONKEY_BRUSH = FEATURES.register("hot_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 2));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SCALDING_MONKEY_BRUSH = FEATURES.register("scalding_monkey_brush", () -> new MonkeyBrushFeature(NoneFeatureConfiguration.CODEC, 3));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> WATER_HYACINTH_PATCH = FEATURES.register("water_hyacinth_patch", () -> new WaterHyacinthPatchFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PASSION_VINE = FEATURES.register("passion_vine", () -> new PassionVineFeature(NoneFeatureConfiguration.CODEC));

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> DRAGON_ROOTS = FEATURES.register("dragon_roots", () -> new DragonRootsFeature(NoneFeatureConfiguration.CODEC));

	public static final RegistryObject<Feature<TreeConfiguration>> ROSEWOOD_TREE = FEATURES.register("rosewood_tree", () -> new RainforestTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> YUCCA_TREE = FEATURES.register("yucca_tree", () -> new YuccaTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> BABY_YUCCA_TREE = FEATURES.register("baby_yucca_tree", () -> new BabyYuccaTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> ASPEN_TREE = FEATURES.register("aspen_tree", () -> new AspenTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> KOUSA_TREE = FEATURES.register("kousa_tree", () -> new KousaTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> BABY_KOUSA_TREE = FEATURES.register("baby_kousa_tree", () -> new BabyKousaTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> GRIMWOOD_TREE = FEATURES.register("grimwood_tree", () -> new GrimwoodTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> LAUREL_TREE = FEATURES.register("laurel_tree", () -> new LaurelTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> CURRANT_TREE = FEATURES.register("currant_tree", () -> new CurrantTreeFeature(TreeConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> SMALL_BUSH = FEATURES.register("small_bush", () -> new SmallBushFeature(TreeConfiguration.CODEC));

	public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> CRUSTOSE = FEATURES.register("crustose", () -> new CrustoseFeature(ProbabilityFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<SimpleBlockConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", () -> new FallenLogFeature(SimpleBlockConfiguration.CODEC));

	public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> SNOWY_BAMBOO = FEATURES.register("snowy_bamboo", () -> new SnowyBambooFeature(ProbabilityFeatureConfiguration.CODEC));

	public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> COARSE_DIRT = FEATURES.register("coarse_dirt", () -> new CoarseDirtFeature(ProbabilityFeatureConfiguration.CODEC));

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> OCEAN_FLOOR_RAISER = FEATURES.register("ocean_floor_raiser", () -> new OceanFloorRaiserFeature(NoneFeatureConfiguration.CODEC));

	public static final RegistryObject<TreeDecoratorType<?>> MONKEY_BRUSH = TREE_DECORATOR_TYPES.register("monkey_brush", () -> new TreeDecoratorType<>(MonkeyBrushDecorator.CODEC));
	public static final RegistryObject<TreeDecoratorType<?>> HANGING_CURRANT = TREE_DECORATOR_TYPES.register("hanging_currant", () -> new TreeDecoratorType<>(HangingCurrantDecorator.CODEC));
	public static final RegistryObject<TreeDecoratorType<?>> COBWEB = TREE_DECORATOR_TYPES.register("cobweb", () -> new TreeDecoratorType<>(CobwebDecorator.CODEC));
	public static final RegistryObject<TreeDecoratorType<?>> ORANGES = TREE_DECORATOR_TYPES.register("oranges", () -> new TreeDecoratorType<>(OrangesDecorator.CODEC));
	public static final RegistryObject<TreeDecoratorType<?>> YUCCA_BUNDLE = TREE_DECORATOR_TYPES.register("yucca_bundle", () -> new TreeDecoratorType<>(YuccaBundleDecorator.CODEC));
	public static final RegistryObject<TreeDecoratorType<?>> YUCCA_FLOWERS = TREE_DECORATOR_TYPES.register("yucca_flowers", () -> new TreeDecoratorType<>(YuccaFlowersDecorator.CODEC));
	public static final RegistryObject<TreeDecoratorType<?>> YUCCA_FLOWER_PATCH = TREE_DECORATOR_TYPES.register("yucca_flower_patch", () -> new TreeDecoratorType<>(YuccaFlowerPatchDecorator.CODEC));
	public static final RegistryObject<TreeDecoratorType<?>> ROASTED_YUCCA_BUNDLE = TREE_DECORATOR_TYPES.register("roasted_yucca_bundle", () -> new TreeDecoratorType<>(RoastedYuccaBundleDecorator.CODEC));
	public static final RegistryObject<TreeDecoratorType<?>> EXTEND_PETRIFIED_YUCCA_TREE = TREE_DECORATOR_TYPES.register("extend_petrified_yucca_tree", () -> new TreeDecoratorType<>(ExtendPetrifiedYuccaTreeDecorator.CODEC));

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
			return new OrangesDecorator(probability, BlockStateProvider.simple(blood ? AtmosphericBlocks.STEMMED_BLOOD_ORANGE.get() : AtmosphericBlocks.STEMMED_ORANGE.get()), (float) 0.25, (float) 0.3);
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
		public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Atmospheric.MOD_ID);

		private static final List<ResourceLocation> FOSSIL_STRUCTURES = List.of(new ResourceLocation("fossil/spine_1"), new ResourceLocation("fossil/spine_2"), new ResourceLocation("fossil/spine_3"), new ResourceLocation("fossil/spine_4"), new ResourceLocation("fossil/skull_1"), new ResourceLocation("fossil/skull_2"), new ResourceLocation("fossil/skull_3"), new ResourceLocation("fossil/skull_4"));
		private static final List<ResourceLocation> FOSSIL_COAL_STRUCTURES = List.of(new ResourceLocation("fossil/spine_1_coal"), new ResourceLocation("fossil/spine_2_coal"), new ResourceLocation("fossil/spine_3_coal"), new ResourceLocation("fossil/spine_4_coal"), new ResourceLocation("fossil/skull_1_coal"), new ResourceLocation("fossil/skull_2_coal"), new ResourceLocation("fossil/skull_3_coal"), new ResourceLocation("fossil/skull_4_coal"));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD = register("rosewood", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD_BEES_0002 = register("rosewood_bees_0002", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD_BEES_0002));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD_BEES_005 = register("rosewood_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.ROSEWOOD_BEES_005));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO = register("morado", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO_BEES_0002 = register("morado_bees_0002", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO_BEES_0002));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO_BEES_005 = register("morado_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.ROSEWOOD_TREE.get(), Configs.MORADO_BEES_005));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> YUCCA = register("yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> YUCCA_WITH_FLOWERS = register("yucca_with_flowers", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_WITH_FLOWERS));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> BABY_YUCCA = register("baby_yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.BABY_YUCCA_TREE.get(), Configs.BABY_YUCCA));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> BABY_YUCCA_WITH_FLOWERS = register("baby_yucca_with_flowers", () -> new ConfiguredFeature<>(AtmosphericFeatures.BABY_YUCCA_TREE.get(), Configs.BABY_YUCCA_WITH_FLOWERS));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> YUCCA_BEES_005 = register("yucca_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_BEES_005));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> YUCCA_BEES_005_WITH_FLOWERS = register("yucca_bees_005_with_flowers", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.YUCCA_BEES_005_WITH_FLOWERS));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> PETRIFIED_YUCCA = register("petrified_yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.PETRIFIED_YUCCA));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> RED_PETRIFIED_YUCCA = register("red_petrified_yucca", () -> new ConfiguredFeature<>(AtmosphericFeatures.YUCCA_TREE.get(), Configs.RED_PETRIFIED_YUCCA));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ASPEN = register("aspen", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ASPEN_BEES_0002 = register("aspen_bees_0002", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN_BEES_0002));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ASPEN_BEES_005 = register("aspen_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN_BEES_005));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> ASPEN_WITH_VINES = register("aspen_with_vines", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.ASPEN_WITH_VINES));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> GREEN_ASPEN = register("green_aspen", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.GREEN_ASPEN));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> GREEN_ASPEN_BEES_0002 = register("green_aspen_bees_0002", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.GREEN_ASPEN_BEES_0002));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> GREEN_ASPEN_BEES_005 = register("green_aspen_bees_005", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.GREEN_ASPEN_BEES_005));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> GREEN_ASPEN_WITH_VINES = register("green_aspen_with_vines", () -> new ConfiguredFeature<>(AtmosphericFeatures.ASPEN_TREE.get(), Configs.GREEN_ASPEN_WITH_VINES));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> LAUREL = register("laurel_grown", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> LAUREL_ORANGES_0005 = register("laurel", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL_ORANGES_0005));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> LAUREL_ORANGES_08 = register("laurel_boosted", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL_ORANGES_08));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> LAUREL_WITH_VINES = register("laurel_with_vines", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL_WITH_VINES));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> LAUREL_BLOOD_ORANGES_08 = register("laurel_nether_boosted", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.LAUREL_BLOOD_ORANGES_08));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DRY_LAUREL = register("dry_laurel_grown", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DRY_LAUREL_ORANGES_0005 = register("dry_laurel", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL_ORANGES_0005));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DRY_LAUREL_ORANGES_08 = register("dry_laurel_boosted", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL_ORANGES_08));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DRY_LAUREL_WITH_VINES = register("dry_laurel_with_vines", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL_WITH_VINES));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DRY_LAUREL_BLOOD_ORANGES_08 = register("dry_laurel_nether_grown", () -> new ConfiguredFeature<>(AtmosphericFeatures.LAUREL_TREE.get(), Configs.DRY_LAUREL_BLOOD_ORANGES_08));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> KOUSA = register("kousa", () -> new ConfiguredFeature<>(AtmosphericFeatures.KOUSA_TREE.get(), Configs.KOUSA));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> BABY_KOUSA = register("baby_kousa", () -> new ConfiguredFeature<>(AtmosphericFeatures.BABY_KOUSA_TREE.get(), Configs.BABY_KOUSA));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> CURRANT = register("currant", () -> new ConfiguredFeature<>(AtmosphericFeatures.CURRANT_TREE.get(), Configs.CURRANT));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DEAD_CURRANT = register("dead_currant", () -> new ConfiguredFeature<>(AtmosphericFeatures.CURRANT_TREE.get(), Configs.DEAD_CURRANT));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> GRIMWOOD = register("grimwood", () -> new ConfiguredFeature<>(AtmosphericFeatures.GRIMWOOD_TREE.get(), Configs.GRIMWOOD));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DEAD_GRIMWOOD = register("dead_grimwood", () -> new ConfiguredFeature<>(AtmosphericFeatures.GRIMWOOD_TREE.get(), Configs.DEAD_GRIMWOOD));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> OAK_BUSH = register("oak_bush", () -> new ConfiguredFeature<>(Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.OAK_LEAVES), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build()));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DARK_OAK_BUSH = register("dark_oak_bush", () -> new ConfiguredFeature<>(Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build()));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> MORADO_BUSH = register("morado_bush", () -> new ConfiguredFeature<>(Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(AtmosphericBlocks.MORADO_LOG.get()), new StraightTrunkPlacer(1, 0, 0), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.MORADO_LEAVES.get().defaultBlockState(), 2).add(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get().defaultBlockState(), 6).build()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build()));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> DRY_LAUREL_BUSH = register("dry_laurel_bush", () -> new ConfiguredFeature<>(AtmosphericFeatures.SMALL_BUSH.get(), Configs.DRY_LAUREL_BUSH));

		// Rainforest

		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_RAINFOREST = register("trees_rainforest", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.MORADO_BEES_0002.getHolder().get(), 0.025F)), AtmosphericPlacedFeatures.ROSEWOOD_BEES_0002.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> BUSHES_RAINFOREST = register("bushes_rainforest", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.FANCY_OAK_CHECKED, 0.05F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.MORADO_BUSH.getHolder().get(), 0.15F)), AtmosphericPlacedFeatures.OAK_BUSH.getHolder().get())));

		public static final RegistryObject<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> PODZOL = register("podzol", () -> new ConfiguredFeature<>(AtmosphericFeatures.PODZOL.get(), new ProbabilityFeatureConfiguration(0.2F)));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> PASSION_VINES = register("passion_vines", () -> new ConfiguredFeature<>(AtmosphericFeatures.PASSION_VINE.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> PATCH_WATER_HYACINTH = register("patch_water_hyacinth", () -> new ConfiguredFeature<>(AtmosphericFeatures.WATER_HYACINTH_PATCH.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_WATERLILY = register("patch_waterlily_rainforest", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILY_PAD))))));

		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> WARM_MONKEY_BRUSH = register("warm_monkey_brush", () -> new ConfiguredFeature<>(AtmosphericFeatures.WARM_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> HOT_MONKEY_BRUSH = register("hot_monkey_brush", () -> new ConfiguredFeature<>(AtmosphericFeatures.HOT_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> SCALDING_MONKEY_BRUSH = register("scalding_monkey_brush", () -> new ConfiguredFeature<>(AtmosphericFeatures.SCALDING_MONKEY_BRUSH.get(), NoneFeatureConfiguration.INSTANCE));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> MONKEY_BRUSH = register("monkey_brush", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.SCALDING_MONKEY_BRUSH.getHolder().get(), 0.166666667F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.HOT_MONKEY_BRUSH.getHolder().get(), 0.333333334F)), AtmosphericPlacedFeatures.WARM_MONKEY_BRUSH.getHolder().get())));

		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> OCEAN_FLOOR_RAISER = register("ocean_floor_raiser", () -> new ConfiguredFeature<>(AtmosphericFeatures.OCEAN_FLOOR_RAISER.get(), NoneFeatureConfiguration.INSTANCE));

		// Dunes

		public static final RegistryObject<ConfiguredFeature<BlockStateConfiguration, ?>> DUNE_ROCK = register("forest_rock", () -> new ConfiguredFeature<>(AtmosphericFeatures.DUNE_ROCK.get(), new BlockStateConfiguration(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState())));
		public static final RegistryObject<ConfiguredFeature<FossilFeatureConfiguration, ?>> SURFACE_FOSSIL = register("surface_fossil", () -> new ConfiguredFeature<>(AtmosphericFeatures.SURFACE_FOSSIL.get(), new FossilFeatureConfiguration(FOSSIL_STRUCTURES, FOSSIL_COAL_STRUCTURES, ProcessorLists.FOSSIL_ROT, ProcessorLists.FOSSIL_COAL, 4)));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_BARREL_CACTUS = register("patch_barrel_cactus", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new RandomizedIntStateProvider(BlockStateProvider.simple(AtmosphericBlocks.BARREL_CACTUS.get()), BarrelCactusBlock.AGE, UniformInt.of(0, 3))), List.of(Blocks.SAND, Blocks.RED_SAND, AtmosphericBlocks.ARID_SAND.get(), AtmosphericBlocks.RED_ARID_SAND.get()))));
		public static final RegistryObject<ConfiguredFeature<LargeDiskConfiguration, ?>> PATCH_DUNE_GRASS = register("patch_dune_grass", () -> new ConfiguredFeature<>(AtmosphericFeatures.COARSE_DIRT_PATCH.get(), new LargeDiskConfiguration(Blocks.COARSE_DIRT.defaultBlockState(), UniformInt.of(1, 7), 2, Lists.newArrayList(AtmosphericBlocks.RED_ARID_SAND.get().defaultBlockState(), AtmosphericBlocks.ARID_SAND.get().defaultBlockState()))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_ARID_SPROUTS = register("patch_arid_sprouts", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(32, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.ARID_SPROUTS.get()))))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> FLOWER_FLOURISHING_DUNES = register("flower_flourishing_dunes", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.GILIA.get()))))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_MELON_DUNES = register("patch_melon_dunes", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(64, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.MELON)), BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), List.of(AtmosphericBlocks.ARID_SAND.get(), AtmosphericBlocks.RED_ARID_SAND.get())))))));

		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_DUNES = register("trees_dunes", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.YUCCA_WITH_FLOWERS.getHolder().get(), 0.25F)), AtmosphericPlacedFeatures.YUCCA.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_PETRIFIED_DUNES = register("trees_petrified_dunes", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.RED_PETRIFIED_YUCCA.getHolder().get(), 0.25F)), AtmosphericPlacedFeatures.PETRIFIED_YUCCA.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_FLOURISHING_DUNES = register("trees_flourishing_dunes", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.BABY_YUCCA_WITH_FLOWERS.getHolder().get(), 0.25F)), AtmosphericPlacedFeatures.BABY_YUCCA.getHolder().get())));

		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_YUCCA_FLOWER = register("patch_yucca_flower", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.YUCCA_FLOWER.get().defaultBlockState(), 1).add(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState(), 2).build())), List.of(AtmosphericBlocks.ARID_SAND.get(), AtmosphericBlocks.RED_ARID_SAND.get()))));

		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_SHORT_ALOE_VERA = register("patch_short_aloe_vera", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 5))), List.of(AtmosphericBlocks.RED_ARID_SAND.get()))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_TALL_ALOE_VERA = register("patch_tall_aloe_vera", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.AGE, 8))), List.of(AtmosphericBlocks.ARID_SAND.get()))));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> PATCH_ALOE_VERA = register("patch_aloe_vera", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.PATCH_TALL_ALOE_VERA.getHolder().get(), 0.5F)), AtmosphericPlacedFeatures.PATCH_SHORT_ALOE_VERA.getHolder().get())));

		// Aspen Parkland

		public static final RegistryObject<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CRUSTOSE = register("crustose", () -> new ConfiguredFeature<>(AtmosphericFeatures.CRUSTOSE.get(), new ProbabilityFeatureConfiguration(0.1F)));
		public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> FALLEN_CRUSTOSE_LOG = register("fallen_crustose_log", () -> new ConfiguredFeature<>(AtmosphericFeatures.FALLEN_LOG.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.CRUSTOSE_LOG.get()))));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_ASPEN_PARKLAND = register("trees_aspen_parkland", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.MORADO_BUSH.getHolder().get(), 0.1F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.DRY_LAUREL.getHolder().get(), 0.05F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.ASPEN_WITH_VINES.getHolder().get(), 0.075F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.GREEN_ASPEN_WITH_VINES.getHolder().get(), 0.025F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.GREEN_ASPEN_BEES_0002.getHolder().get(), 0.2F)), AtmosphericPlacedFeatures.ASPEN_BEES_0002.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> SINGLE_AGAVE = register("single_agave", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.AGAVE.get()))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_AGAVE_LARGE = register("patch_agave_large", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(512, 12, 5, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.AGAVE.get()))))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_GOLDEN_GROWTHS = register("patch_golden_growths", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(32, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.GOLDEN_GROWTHS.get()))))));

		// Laurel Forest

		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_LAUREL_FOREST = register("trees_laurel_forest", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.DRY_LAUREL_WITH_VINES.getHolder().get(), 0.05F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.DRY_LAUREL.getHolder().get(), 0.05F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.LAUREL_WITH_VINES.getHolder().get(), 0.45F)), AtmosphericPlacedFeatures.LAUREL.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_GRASS_LAUREL_FOREST = register("patch_grass_laurel_forest", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.GRASS.defaultBlockState(), 1).add(Blocks.FERN.defaultBlockState(), 4).add(AtmosphericBlocks.BRACKEN.get().defaultBlockState(), 4)), 32)));

		// Kousa Jungle

		public static final RegistryObject<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> SNOWY_BAMBOO = register("snowy_bamboo", () -> new ConfiguredFeature<>(AtmosphericFeatures.SNOWY_BAMBOO.get(), new ProbabilityFeatureConfiguration(1.0F)));
		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_KOUSA_JUNGLE = register("trees_kousa_jungle", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.BIRCH_CHECKED, 0.3F)), AtmosphericPlacedFeatures.KOUSA.getHolder().get())));

		// Grimwoods

		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_GRIMWOODS = register("trees_grimwoods", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.DARK_OAK_BUSH.getHolder().get(), 0.1F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.GRIMWOOD.getHolder().get(), 0.0001F)), AtmosphericPlacedFeatures.DEAD_GRIMWOOD.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> OMINOUS_BLOCK = register("ominous_block", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(1, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(Configs.ominousGrimwoodsBlocks()), BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT))))));

		// Spiny Thicket

		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_SPINY_THICKET = register("trees_spiny_thicket", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.ACACIA_CHECKED, 0.25F)), AtmosphericPlacedFeatures.ROSEWOOD_BEES_0002.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> SINGLE_YUCCA_FLOWER = register("single_yucca_flower", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AtmosphericBlocks.YUCCA_FLOWER.get()))));

		// Scrubland

		public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_SCRUBLAND = register("trees_scrubland", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(AtmosphericPlacedFeatures.BABY_YUCCA_WITH_FLOWERS.getHolder().get(), 0.05F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.MORADO_BUSH_SAND.getHolder().get(), 0.2F), new WeightedPlacedFeature(AtmosphericPlacedFeatures.DRY_LAUREL.getHolder().get(), 0.08F)), AtmosphericPlacedFeatures.DRY_LAUREL_BUSH.getHolder().get())));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> FLOWER_SCRUBLAND = register("flower_scrubland", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AtmosphericBlocks.FIRETHORN.get().defaultBlockState(), 1).add(AtmosphericBlocks.FORSYTHIA.get().defaultBlockState(), 1).build()))))));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> DRAGON_ROOTS = register("dragon_roots", () -> new ConfiguredFeature<>(AtmosphericFeatures.DRAGON_ROOTS.get(), NoneFeatureConfiguration.INSTANCE));

		// Hot Springs

		public static final RegistryObject<ConfiguredFeature<BlockStateConfiguration, ?>> HOT_SPRINGS_ROCK = register("hot_springs_rock", () -> new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(Blocks.MOSSY_COBBLESTONE.defaultBlockState())));

		// Generic

		public static final RegistryObject<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> COARSE_DIRT = register("coarse_dirt", () -> new ConfiguredFeature<>(AtmosphericFeatures.COARSE_DIRT.get(), new ProbabilityFeatureConfiguration(0.1F)));

		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> BIRCH_BUSH = register("birch_bush", () -> new ConfiguredFeature<>(Feature.TREE, (new TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.BIRCH_LOG), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.BIRCH_LEAVES), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build()));

		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_DEAD_BUSH = register("patch_dead_bush", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(Blocks.DEAD_BUSH), 4)));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_LARGE_FERN = register("patch_large_fern", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LARGE_FERN)))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_CACTUS = register("patch_cactus", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(10, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 3), BlockStateProvider.simple(Blocks.CACTUS)), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO)))))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_CACTUS_TALL = register("patch_cactus_tall", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(10, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(3, 6), BlockStateProvider.simple(Blocks.CACTUS)), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO)))))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_CACTUS_VERY_TALL = register("patch_cactus_very_tall", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(70, 12, 4, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(9, 12), BlockStateProvider.simple(Blocks.CACTUS)), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO)))))));

		private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
			return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
		}

		private static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String name, Supplier<ConfiguredFeature<FC, F>> feature) {
			return CONFIGURED_FEATURES.register(name, feature);
		}
	}

	public static final class AtmosphericPlacedFeatures {
		public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Atmospheric.MOD_ID);

		public static final RegistryObject<PlacedFeature> ROSEWOOD_BEES_0002 = register("rosewood_bees_0002", AtmosphericConfiguredFeatures.ROSEWOOD_BEES_0002, List.of());
		public static final RegistryObject<PlacedFeature> MORADO_BEES_0002 = register("morado_bees_0002", AtmosphericConfiguredFeatures.MORADO_BEES_0002, List.of());
		public static final RegistryObject<PlacedFeature> YUCCA = register("yucca", AtmosphericConfiguredFeatures.YUCCA, List.of());
		public static final RegistryObject<PlacedFeature> YUCCA_WITH_FLOWERS = register("yucca_with_flowers", AtmosphericConfiguredFeatures.YUCCA_WITH_FLOWERS, List.of());
		public static final RegistryObject<PlacedFeature> BABY_YUCCA = register("baby_yucca", AtmosphericConfiguredFeatures.BABY_YUCCA, List.of());
		public static final RegistryObject<PlacedFeature> BABY_YUCCA_WITH_FLOWERS = register("baby_yucca_with_flowers", AtmosphericConfiguredFeatures.BABY_YUCCA_WITH_FLOWERS, List.of());
		public static final RegistryObject<PlacedFeature> PETRIFIED_YUCCA = register("petrified_yucca", AtmosphericConfiguredFeatures.PETRIFIED_YUCCA, List.of());
		public static final RegistryObject<PlacedFeature> RED_PETRIFIED_YUCCA = register("red_petrified_yucca", AtmosphericConfiguredFeatures.RED_PETRIFIED_YUCCA, List.of());
		public static final RegistryObject<PlacedFeature> ASPEN_BEES_0002 = register("aspen_bees_0002", AtmosphericConfiguredFeatures.ASPEN_BEES_0002, List.of());
		public static final RegistryObject<PlacedFeature> ASPEN_WITH_VINES = register("aspen_with_vines", AtmosphericConfiguredFeatures.ASPEN_WITH_VINES, List.of());
		public static final RegistryObject<PlacedFeature> GREEN_ASPEN_BEES_0002 = register("green_aspen_bees_0002", AtmosphericConfiguredFeatures.GREEN_ASPEN_BEES_0002, List.of());
		public static final RegistryObject<PlacedFeature> GREEN_ASPEN_WITH_VINES = register("green_aspen_with_vines", AtmosphericConfiguredFeatures.GREEN_ASPEN_WITH_VINES, List.of());
		public static final RegistryObject<PlacedFeature> LAUREL = register("laurel", AtmosphericConfiguredFeatures.LAUREL_ORANGES_0005, List.of());
		public static final RegistryObject<PlacedFeature> LAUREL_WITH_VINES = register("laurel_with_vines", AtmosphericConfiguredFeatures.LAUREL_WITH_VINES, List.of());
		public static final RegistryObject<PlacedFeature> DRY_LAUREL = register("dry_laurel", AtmosphericConfiguredFeatures.DRY_LAUREL_ORANGES_0005, List.of());
		public static final RegistryObject<PlacedFeature> DRY_LAUREL_WITH_VINES = register("dry_laurel_with_vines", AtmosphericConfiguredFeatures.DRY_LAUREL_WITH_VINES, List.of());
		public static final RegistryObject<PlacedFeature> KOUSA = register("kousa", AtmosphericConfiguredFeatures.KOUSA, List.of());
		public static final RegistryObject<PlacedFeature> GRIMWOOD = register("grimwood", AtmosphericConfiguredFeatures.GRIMWOOD, List.of());
		public static final RegistryObject<PlacedFeature> DEAD_GRIMWOOD = register("dead_grimwood", AtmosphericConfiguredFeatures.DEAD_GRIMWOOD, List.of());
		public static final RegistryObject<PlacedFeature> DEAD_CURRANT = register("dead_currant", AtmosphericConfiguredFeatures.DEAD_CURRANT, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.2F, 15)));

		public static final RegistryObject<PlacedFeature> OAK_BUSH = register("oak_bush", AtmosphericConfiguredFeatures.OAK_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
		public static final RegistryObject<PlacedFeature> DARK_OAK_BUSH = register("dark_oak_bush", AtmosphericConfiguredFeatures.DARK_OAK_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING)));
		public static final RegistryObject<PlacedFeature> MORADO_BUSH = register("morado_bush", AtmosphericConfiguredFeatures.MORADO_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
		public static final RegistryObject<PlacedFeature> MORADO_BUSH_SAND = register("morado_bush_sand", AtmosphericConfiguredFeatures.MORADO_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.DEAD_BUSH)));
		public static final RegistryObject<PlacedFeature> DRY_LAUREL_BUSH = register("dry_laurel_bush", AtmosphericConfiguredFeatures.DRY_LAUREL_BUSH, List.of());

		// Rainforest

		public static final RegistryObject<PlacedFeature> TREES_RAINFOREST = register("trees_rainforest", AtmosphericConfiguredFeatures.TREES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(30, 0.1F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_SPARSE_RAINFOREST = register("trees_sparse_rainforest", AtmosphericConfiguredFeatures.TREES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 30)));
		public static final RegistryObject<PlacedFeature> TREES_RAINFOREST_BASIN = register("trees_rainforest_basin", AtmosphericConfiguredFeatures.TREES_RAINFOREST, waterTreePlacement(PlacementUtils.countExtra(50, 0.1F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_SPARSE_RAINFOREST_BASIN = register("trees_sparse_rainforest_basin", AtmosphericConfiguredFeatures.TREES_RAINFOREST, waterTreePlacement(PlacementUtils.countExtra(3, 0.1F, 5)));

		public static final RegistryObject<PlacedFeature> BUSHES_RAINFOREST = register("bushes_rainforest", AtmosphericConfiguredFeatures.BUSHES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(12, 0.1F, 1)));
		public static final RegistryObject<PlacedFeature> BUSHES_SPARSE_RAINFOREST = register("bushes_sparse_rainforest", AtmosphericConfiguredFeatures.BUSHES_RAINFOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1F, 3)));

		public static final RegistryObject<PlacedFeature> PODZOL = register("podzol", AtmosphericConfiguredFeatures.PODZOL, List.of(NoiseBasedCountPlacement.of(160, 80.0D, 0.3D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PASSION_VINES = register("passion_vines", AtmosphericConfiguredFeatures.PASSION_VINES, List.of(CountPlacement.of(1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(192)), BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_WATER_HYACINTH = register("patch_water_hyacinth", AtmosphericConfiguredFeatures.PATCH_WATER_HYACINTH, List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_WATER_HYACINTH_SPARSE = register("patch_water_hyacinth_sparse", AtmosphericConfiguredFeatures.PATCH_WATER_HYACINTH, List.of(RarityFilter.onAverageOnceEvery(48), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_WATERLILY_RAINFOREST = register("patch_waterlily_rainforest", AtmosphericConfiguredFeatures.PATCH_WATERLILY, VegetationPlacements.worldSurfaceSquaredWithCount(1));
		public static final RegistryObject<PlacedFeature> PATCH_WATERLILY_RAINFOREST_BASIN = register("patch_waterlily_rainforest_basin", AtmosphericConfiguredFeatures.PATCH_WATERLILY, VegetationPlacements.worldSurfaceSquaredWithCount(4));

		public static final RegistryObject<PlacedFeature> WARM_MONKEY_BRUSH = register("warm_monkey_brush", AtmosphericConfiguredFeatures.WARM_MONKEY_BRUSH, List.of());
		public static final RegistryObject<PlacedFeature> HOT_MONKEY_BRUSH = register("hot_monkey_brush", AtmosphericConfiguredFeatures.HOT_MONKEY_BRUSH, List.of());
		public static final RegistryObject<PlacedFeature> SCALDING_MONKEY_BRUSH = register("scalding_monkey_brush", AtmosphericConfiguredFeatures.SCALDING_MONKEY_BRUSH, List.of());
		public static final RegistryObject<PlacedFeature> MONKEY_BRUSH = register("monkey_brush", AtmosphericConfiguredFeatures.MONKEY_BRUSH, List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		public static final RegistryObject<PlacedFeature> OCEAN_FLOOR_RAISER = register("ocean_floor_raiser", AtmosphericConfiguredFeatures.OCEAN_FLOOR_RAISER, List.of(InSquareCenterPlacement.INSTANCE, PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));

		// Dunes

		public static final RegistryObject<PlacedFeature> TREES_DUNES = register("trees_dunes", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_FLOURISHING_DUNES = register("trees_flourishing_dunes", AtmosphericConfiguredFeatures.TREES_FLOURISHING_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.05F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_ROCKY_DUNES = register("trees_rocky_dunes", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
		public static final RegistryObject<PlacedFeature> TREES_PETRIFIED_DUNES = register("trees_petrified_dunes", AtmosphericConfiguredFeatures.TREES_PETRIFIED_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5F, 2)));
		public static final RegistryObject<PlacedFeature> FLOURISHING_DUNES_YUCCA_TREES = register("flourishing_dunes_yucca_trees", AtmosphericConfiguredFeatures.YUCCA_BEES_005_WITH_FLOWERS, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1)));

		public static final RegistryObject<PlacedFeature> WOODED_BADLANDS_YUCCA_TREES = register("badlands_yucca_trees", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1), Blocks.OAK_SAPLING));
		public static final RegistryObject<PlacedFeature> DESERT_YUCCA_TREES = register("desert_yucca_trees", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.005F, 1)));
		public static final RegistryObject<PlacedFeature> WINDSWEPT_SAVANNA_YUCCA_TREES = register("windswept_savanna_yucca_trees", AtmosphericConfiguredFeatures.TREES_DUNES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.125F, 1)));

		public static final RegistryObject<PlacedFeature> DUNE_ROCK = register("dune_rock", AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> DUNE_ROCK_EXTRA = register("dune_rock_extra", AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> DUNE_ROCK_MANY = register("dune_rock_many", AtmosphericConfiguredFeatures.DUNE_ROCK, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_DUNES = register("patch_barrel_cactus_dunes", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.05F, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_ROCKY_DUNES = register("patch_barrel_cactus_rocky_dunes", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.025F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_FLOURISHING_DUNES = register("patch_barrel_cactus_flourishing_dunes", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.5F, 3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_SPINY_THICKET = register("patch_barrel_cactus_spiny_thicket", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.5F, 4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_BARREL_CACTUS_SCRUBLAND = register("patch_barrel_cactus_scrubland", AtmosphericConfiguredFeatures.PATCH_BARREL_CACTUS, List.of(PlacementUtils.countExtra(0, 0.1F, 2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
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

		// Aspen Parkland

		public static final RegistryObject<PlacedFeature> CRUSTOSE = register("crustose", AtmosphericConfiguredFeatures.CRUSTOSE, List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> FALLEN_CRUSTOSE_LOG = register("fallen_crustose_log", AtmosphericConfiguredFeatures.FALLEN_CRUSTOSE_LOG, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> SINGLE_AGAVE = register("single_agave", AtmosphericConfiguredFeatures.SINGLE_AGAVE, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_AGAVE_LARGE = register("patch_agave_large", AtmosphericConfiguredFeatures.PATCH_AGAVE_LARGE, List.of(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_AGAVE_WOODED_BADLANDS = register("patch_agave_wooded_badlands", AtmosphericConfiguredFeatures.PATCH_AGAVE_LARGE, List.of(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_GOLDEN_GROWTHS = register("patch_golden_growths", AtmosphericConfiguredFeatures.PATCH_GOLDEN_GROWTHS, VegetationPlacements.worldSurfaceSquaredWithCount(4));
		public static final RegistryObject<PlacedFeature> TREES_ASPEN_PARKLAND = register("trees_aspen_parkland", AtmosphericConfiguredFeatures.TREES_ASPEN_PARKLAND, VegetationPlacements.treePlacement(PlacementUtils.countExtra(12, 0.1F, 1)));

		// Laurel Forest

		public static final RegistryObject<PlacedFeature> TREES_LAUREL_FOREST = register("trees_laurel_forest", AtmosphericConfiguredFeatures.TREES_LAUREL_FOREST, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
		public static final RegistryObject<PlacedFeature> COARSE_DIRT = register("coarse_dirt", AtmosphericConfiguredFeatures.COARSE_DIRT, List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> COARSE_DIRT_LAUREL_FOREST = register("coarse_dirt_laurel_forest", AtmosphericConfiguredFeatures.COARSE_DIRT, List.of(CountPlacement.of(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_GRASS_LAUREL_FOREST = register("patch_grass_laurel_forest", AtmosphericConfiguredFeatures.PATCH_GRASS_LAUREL_FOREST, List.of(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_DEAD_BUSH_LAUREL_FOREST = register("patch_dead_bush_laurel_forest", AtmosphericConfiguredFeatures.PATCH_DEAD_BUSH, VegetationPlacements.worldSurfaceSquaredWithCount(8));

		// Kousa Jungle

		public static final RegistryObject<PlacedFeature> SNOWY_BAMBOO = register("snowy_bamboo", AtmosphericConfiguredFeatures.SNOWY_BAMBOO, List.of(NoiseBasedCountPlacement.of(120, 2.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> TREES_KOUSA_JUNGLE = register("trees_kousa_jungle", AtmosphericConfiguredFeatures.TREES_KOUSA_JUNGLE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(20, 0.1F, 3)));
		public static final RegistryObject<PlacedFeature> CURRANT = register("currant", AtmosphericConfiguredFeatures.CURRANT, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.025F, 8)));
		public static final RegistryObject<PlacedFeature> PATCH_LARGE_FERN_KOUSA = register("patch_large_fern_kousa", AtmosphericConfiguredFeatures.PATCH_LARGE_FERN, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> BIRCH_BUSH = register("birch_bush", AtmosphericConfiguredFeatures.BIRCH_BUSH, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1), Blocks.BIRCH_SAPLING));

		// Spiny Thicket

		public static final RegistryObject<PlacedFeature> TREES_SPINY_THICKET = register("trees_spiny_thicket", AtmosphericConfiguredFeatures.TREES_SPINY_THICKET, VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.1F, 5)));
		public static final RegistryObject<PlacedFeature> SINGLE_YUCCA_FLOWER = register("single_yucca_flower", AtmosphericConfiguredFeatures.SINGLE_YUCCA_FLOWER, List.of(CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_CACTUS_SCRUBLAND = register("patch_cactus_scrubland", AtmosphericConfiguredFeatures.PATCH_CACTUS, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_CACTUS_SPINY_THICKET = register("patch_cactus_spiny_thicket", AtmosphericConfiguredFeatures.PATCH_CACTUS_TALL, List.of(CountPlacement.of(9), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_CACTUS_SPINIER_THICKET = register("patch_cactus_spinier_thicket", AtmosphericConfiguredFeatures.PATCH_CACTUS_VERY_TALL, List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		// Scrubland

		public static final RegistryObject<PlacedFeature> TREES_SCRUBLAND = register("trees_scrubland", AtmosphericConfiguredFeatures.TREES_SCRUBLAND, VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.25F, 2)));
		public static final RegistryObject<PlacedFeature> FLOWER_SCRUBLAND = register("flower_scrubland", AtmosphericConfiguredFeatures.FLOWER_SCRUBLAND, List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> PATCH_ARID_SPROUTS_RARE = register("patch_arid_sprouts_rare", AtmosphericConfiguredFeatures.PATCH_ARID_SPROUTS, List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final RegistryObject<PlacedFeature> DRAGON_ROOTS = register("dragon_roots", AtmosphericConfiguredFeatures.DRAGON_ROOTS, List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		// Grimwoods

		public static final RegistryObject<PlacedFeature> TREES_GRIMWOODS = register("trees_grimwoods", AtmosphericConfiguredFeatures.TREES_GRIMWOODS, VegetationPlacements.treePlacement(PlacementUtils.countExtra(40, 0.1F, 3)));
		public static final RegistryObject<PlacedFeature> OMINOUS_BLOCK = register("ominous_block", AtmosphericConfiguredFeatures.OMINOUS_BLOCK, List.of(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		// Hot Springs

		public static final RegistryObject<PlacedFeature> HOT_SPRINGS_ROCK = register("hot_springs_rock", AtmosphericConfiguredFeatures.HOT_SPRINGS_ROCK, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK)), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		private static ImmutableList<PlacementModifier> waterTreePlacement(PlacementModifier modifier) {
			return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread()).add(SurfaceWaterDepthFilter.forMaxDepth(10)).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome()).build();
		}

		@SuppressWarnings("unchecked")
		private static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
			return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) feature.getHolder().get(), ImmutableList.copyOf(placementModifiers)));
		}
	}
}
