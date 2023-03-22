package com.teamabnormals.atmospheric.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.common.block.*;
import com.teamabnormals.atmospheric.common.block.grower.*;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericProperties;
import com.teamabnormals.atmospheric.core.registry.helper.AtmosphericBlockSubRegistryHelper;
import com.teamabnormals.blueprint.common.block.*;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.common.block.wood.*;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericBlocks {

	public static final AtmosphericBlockSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getBlockSubHelper();

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_ROSEWOOD_LOG = HELPER.createBlock("stripped_rosewood_log", () -> new StrippedLogBlock(AtmosphericProperties.ROSEWOOD.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD = HELPER.createBlock("stripped_rosewood", () -> new StrippedWoodBlock(AtmosphericProperties.ROSEWOOD.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LOG = HELPER.createBlock("rosewood_log", () -> new LogBlock(STRIPPED_ROSEWOOD_LOG, AtmosphericProperties.ROSEWOOD.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD = HELPER.createBlock("rosewood", () -> new WoodBlock(STRIPPED_ROSEWOOD, AtmosphericProperties.ROSEWOOD.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LEAVES = HELPER.createBlock("rosewood_leaves", () -> new BlueprintLeavesBlock(AtmosphericProperties.ROSEWOOD.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = HELPER.createBlock("rosewood_sapling", () -> new BlueprintSaplingBlock(new RosewoodTreeGrower(), AtmosphericProperties.ROSEWOOD.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_ROSEWOOD_SAPLING = HELPER.createBlockNoItem("potted_rosewood_sapling", () -> new FlowerPotBlock(ROSEWOOD_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> ROSEWOOD_PLANKS = HELPER.createBlock("rosewood_planks", () -> new PlanksBlock(AtmosphericProperties.ROSEWOOD.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_STAIRS = HELPER.createBlock("rosewood_stairs", () -> new WoodStairBlock(ROSEWOOD_PLANKS.get().defaultBlockState(), AtmosphericProperties.ROSEWOOD.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_SLAB = HELPER.createBlock("rosewood_slab", () -> new WoodSlabBlock(AtmosphericProperties.ROSEWOOD.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_PRESSURE_PLATE = HELPER.createBlock("rosewood_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.ROSEWOOD.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_BUTTON = HELPER.createBlock("rosewood_button", () -> new BlueprintWoodButtonBlock(AtmosphericProperties.ROSEWOOD.button()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_FENCE = HELPER.createFuelBlock("rosewood_fence", () -> new WoodFenceBlock(AtmosphericProperties.ROSEWOOD.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_FENCE_GATE = HELPER.createFuelBlock("rosewood_fence_gate", () -> new WoodFenceGateBlock(AtmosphericProperties.ROSEWOOD.planks()), 300, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_DOOR = HELPER.createBlock("rosewood_door", () -> new WoodDoorBlock(AtmosphericProperties.ROSEWOOD.door()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_TRAPDOOR = HELPER.createBlock("rosewood_trapdoor", () -> new WoodTrapDoorBlock(AtmosphericProperties.ROSEWOOD.trapdoor()), CreativeModeTab.TAB_REDSTONE);
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> ROSEWOOD_SIGNS = HELPER.createSignBlock("rosewood", MaterialColor.TERRACOTTA_MAGENTA);

	public static final RegistryObject<Block> ROSEWOOD_BOARDS = HELPER.createCompatFuelBlock("woodworks", "rosewood_boards", () -> new RotatedPillarBlock(AtmosphericProperties.ROSEWOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_BOOKSHELF = HELPER.createCompatFuelBlock("woodworks", "rosewood_bookshelf", () -> new BookshelfBlock(AtmosphericProperties.ROSEWOOD.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LADDER = HELPER.createCompatFuelBlock("woodworks", "rosewood_ladder", () -> new BlueprintLadderBlock(AtmosphericProperties.ROSEWOOD.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_BEEHIVE = HELPER.createCompatBlock("woodworks", "rosewood_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.ROSEWOOD.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_LEAF_PILE = HELPER.createCompatBlock("woodworks", "rosewood_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.ROSEWOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> ROSEWOOD_CHESTS = HELPER.createCompatChestBlocks("woodworks", "rosewood", MaterialColor.TERRACOTTA_MAGENTA);

	public static final RegistryObject<Block> VERTICAL_ROSEWOOD_PLANKS = HELPER.createCompatBlock("quark", "vertical_rosewood_planks", () -> new Block(AtmosphericProperties.ROSEWOOD.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "rosewood_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.ROSEWOOD.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD_POST = HELPER.createCompatFuelBlock("quark", "stripped_rosewood_post", () -> new WoodPostBlock(AtmosphericProperties.ROSEWOOD.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_POST = HELPER.createCompatFuelBlock("quark", "rosewood_post", () -> new WoodPostBlock(STRIPPED_ROSEWOOD_POST, AtmosphericProperties.ROSEWOOD.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LEAF_CARPET = HELPER.createCompatBlock("quark", "rosewood_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.ROSEWOOD.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_HEDGE = HELPER.createCompatFuelBlock("quark", "rosewood_hedge", () -> new HedgeBlock(AtmosphericProperties.ROSEWOOD.log()), 300, CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> STRIPPED_MORADO_LOG = HELPER.createBlock("stripped_morado_log", () -> new StrippedLogBlock(AtmosphericProperties.MORADO.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_MORADO_WOOD = HELPER.createBlock("stripped_morado_wood", () -> new StrippedWoodBlock(AtmosphericProperties.MORADO.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_LOG = HELPER.createBlock("morado_log", () -> new LogBlock(STRIPPED_MORADO_LOG, AtmosphericProperties.MORADO.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_WOOD = HELPER.createBlock("morado_wood", () -> new WoodBlock(STRIPPED_MORADO_WOOD, AtmosphericProperties.MORADO.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_LEAVES = HELPER.createBlock("morado_leaves", () -> new BlueprintLeavesBlock(AtmosphericProperties.MORADO.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_SAPLING = HELPER.createBlock("morado_sapling", () -> new BlueprintSaplingBlock(new MoradoTreeGrower(), AtmosphericProperties.MORADO.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_MORADO_SAPLING = HELPER.createBlockNoItem("potted_morado_sapling", () -> new FlowerPotBlock(MORADO_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> MORADO_PLANKS = HELPER.createBlock("morado_planks", () -> new PlanksBlock(AtmosphericProperties.MORADO.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_STAIRS = HELPER.createBlock("morado_stairs", () -> new WoodStairBlock(MORADO_PLANKS.get().defaultBlockState(), AtmosphericProperties.MORADO.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_SLAB = HELPER.createBlock("morado_slab", () -> new WoodSlabBlock(AtmosphericProperties.MORADO.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_PRESSURE_PLATE = HELPER.createBlock("morado_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.MORADO.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> MORADO_BUTTON = HELPER.createBlock("morado_button", () -> new BlueprintWoodButtonBlock(AtmosphericProperties.MORADO.button()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> MORADO_FENCE = HELPER.createFuelBlock("morado_fence", () -> new WoodFenceBlock(AtmosphericProperties.MORADO.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_FENCE_GATE = HELPER.createFuelBlock("morado_fence_gate", () -> new WoodFenceGateBlock(AtmosphericProperties.MORADO.planks()), 300, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> MORADO_DOOR = HELPER.createBlock("morado_door", () -> new WoodDoorBlock(AtmosphericProperties.MORADO.door()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> MORADO_TRAPDOOR = HELPER.createBlock("morado_trapdoor", () -> new WoodTrapDoorBlock(AtmosphericProperties.MORADO.trapdoor()), CreativeModeTab.TAB_REDSTONE);
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> MORADO_SIGNS = HELPER.createSignBlock("morado", MaterialColor.COLOR_RED);

	public static final RegistryObject<Block> MORADO_BOARDS = HELPER.createCompatFuelBlock("woodworks", "morado_boards", () -> new RotatedPillarBlock(AtmosphericProperties.MORADO.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_BOOKSHELF = HELPER.createCompatFuelBlock("woodworks", "morado_bookshelf", () -> new BookshelfBlock(AtmosphericProperties.MORADO.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_LADDER = HELPER.createCompatFuelBlock("woodworks", "morado_ladder", () -> new BlueprintLadderBlock(AtmosphericProperties.MORADO.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_BEEHIVE = HELPER.createCompatBlock("woodworks", "morado_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.MORADO.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_LEAF_PILE = HELPER.createCompatBlock("woodworks", "morado_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.MORADO.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> MORADO_CHESTS = HELPER.createCompatChestBlocks("woodworks", "morado", MaterialColor.COLOR_RED);

	public static final RegistryObject<Block> VERTICAL_MORADO_PLANKS = HELPER.createCompatBlock("quark", "vertical_morado_planks", () -> new Block(AtmosphericProperties.MORADO.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "morado_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.MORADO.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_MORADO_POST = HELPER.createCompatFuelBlock("quark", "stripped_morado_post", () -> new WoodPostBlock(AtmosphericProperties.MORADO.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_POST = HELPER.createCompatFuelBlock("quark", "morado_post", () -> new WoodPostBlock(STRIPPED_MORADO_POST, AtmosphericProperties.MORADO.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_LEAF_CARPET = HELPER.createCompatBlock("quark", "morado_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.MORADO.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_HEDGE = HELPER.createCompatFuelBlock("quark", "morado_hedge", () -> new HedgeBlock(AtmosphericProperties.MORADO.log()), 300, CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> FLOWERING_MORADO_LEAVES = HELPER.createBlock("flowering_morado_leaves", () -> new FloweringMoradoLeavesBlock(AtmosphericProperties.MORADO.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> FLOWERING_MORADO_LEAF_CARPET = HELPER.createCompatBlock("quark", "flowering_morado_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.MORADO.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> FLOWERING_MORADO_HEDGE = HELPER.createCompatFuelBlock("quark", "flowering_morado_hedge", () -> new HedgeBlock(AtmosphericProperties.MORADO.log()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> FLOWERING_MORADO_LEAF_PILE = HELPER.createCompatBlock("woodworks", "flowering_morado_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.MORADO.leafPile()), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> PASSION_VINE = HELPER.createBlock("passion_vine", () -> new PassionVineBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.VINE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PASSION_VINE_BUNDLE = HELPER.createBlock("passion_vine_bundle", () -> new PassionVineBundleBlock(Block.Properties.of(Material.GRASS, MaterialColor.COLOR_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS)), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> WATER_HYACINTH = HELPER.createBlockNoItem("water_hyacinth", () -> new WaterHyacinthBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0F).sound(SoundType.VINE)));

	public static final RegistryObject<Block> WARM_MONKEY_BRUSH = HELPER.createBlockNoItem("warm_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower()));
	public static final RegistryObject<Block> HOT_MONKEY_BRUSH = HELPER.createBlockNoItem("hot_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower()));
	public static final RegistryObject<Block> SCALDING_MONKEY_BRUSH = HELPER.createBlockNoItem("scalding_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower()));

	public static final RegistryObject<Block> WARM_WALL_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("warm_monkey_brush", "warm_wall_monkey_brush", WARM_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().lootFrom(WARM_MONKEY_BRUSH)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> HOT_WALL_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("hot_monkey_brush", "hot_wall_monkey_brush", HOT_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().lootFrom(HOT_MONKEY_BRUSH)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> SCALDING_WALL_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("scalding_monkey_brush", "scalding_wall_monkey_brush", SCALDING_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().lootFrom(SCALDING_MONKEY_BRUSH)), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> POTTED_WARM_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_warm_monkey_brush", () -> new FlowerPotBlock(WARM_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> POTTED_HOT_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_hot_monkey_brush", () -> new FlowerPotBlock(HOT_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> POTTED_SCALDING_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_scalding_monkey_brush", () -> new FlowerPotBlock(SCALDING_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> POTTED_WATER_HYACINTH = HELPER.createBlockNoItem("potted_water_hyacinth", () -> new FlowerPotBlock(WATER_HYACINTH.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> PASSIONFRUIT_CRATE = HELPER.createCompatBlock("quark", "passionfruit_crate", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> SHIMMERING_PASSIONFRUIT_CRATE = HELPER.createCompatBlock("quark", "shimmering_passionfruit_crate", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.GOLD).lightLevel((state) -> 7).strength(1.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> IVORY_TRAVERTINE = HELPER.createBlock("ivory_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.IVORY_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE = HELPER.createBlock("peach_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.PEACH_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE = HELPER.createBlock("persimmon_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE = HELPER.createBlock("saffron_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.SAFFRON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_IVORY_TRAVERTINE = HELPER.createBlock("chiseled_ivory_travertine", () -> new Block(AtmosphericProperties.IVORY_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_PEACH_TRAVERTINE = HELPER.createBlock("chiseled_peach_travertine", () -> new Block(AtmosphericProperties.PEACH_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_PERSIMMON_TRAVERTINE = HELPER.createBlock("chiseled_persimmon_travertine", () -> new Block(AtmosphericProperties.PERSIMMON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_SAFFRON_TRAVERTINE = HELPER.createBlock("chiseled_saffron_travertine", () -> new Block(AtmosphericProperties.SAFFRON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CUT_IVORY_TRAVERTINE = HELPER.createBlock("cut_ivory_travertine", () -> new Block(AtmosphericProperties.IVORY_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_PEACH_TRAVERTINE = HELPER.createBlock("cut_peach_travertine", () -> new Block(AtmosphericProperties.PEACH_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_PERSIMMON_TRAVERTINE = HELPER.createBlock("cut_persimmon_travertine", () -> new Block(AtmosphericProperties.PERSIMMON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_SAFFRON_TRAVERTINE = HELPER.createBlock("cut_saffron_travertine", () -> new Block(AtmosphericProperties.SAFFRON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_STAIRS = HELPER.createBlock("ivory_travertine_stairs", () -> new StairBlock(() -> IVORY_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.IVORY_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_STAIRS = HELPER.createBlock("peach_travertine_stairs", () -> new StairBlock(() -> PEACH_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.PEACH_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_STAIRS = HELPER.createBlock("persimmon_travertine_stairs", () -> new StairBlock(() -> PERSIMMON_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.PERSIMMON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_STAIRS = HELPER.createBlock("saffron_travertine_stairs", () -> new StairBlock(() -> SAFFRON_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.SAFFRON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_SLAB = HELPER.createBlock("ivory_travertine_slab", () -> new SlabBlock(AtmosphericProperties.IVORY_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_SLAB = HELPER.createBlock("peach_travertine_slab", () -> new SlabBlock(AtmosphericProperties.PEACH_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_SLAB = HELPER.createBlock("persimmon_travertine_slab", () -> new SlabBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_SLAB = HELPER.createBlock("saffron_travertine_slab", () -> new SlabBlock(AtmosphericProperties.SAFFRON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_WALL = HELPER.createBlock("ivory_travertine_wall", () -> new WallBlock(AtmosphericProperties.IVORY_TRAVERTINE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_WALL = HELPER.createBlock("peach_travertine_wall", () -> new WallBlock(AtmosphericProperties.PEACH_TRAVERTINE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_WALL = HELPER.createBlock("persimmon_travertine_wall", () -> new WallBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_WALL = HELPER.createBlock("saffron_travertine_wall", () -> new WallBlock(AtmosphericProperties.SAFFRON_TRAVERTINE), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "ivory_travertine_vertical_slab", () -> new DirectionalVerticalSlabBlock(AtmosphericProperties.IVORY_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "peach_travertine_vertical_slab", () -> new DirectionalVerticalSlabBlock(AtmosphericProperties.PEACH_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "persimmon_travertine_vertical_slab", () -> new DirectionalVerticalSlabBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "saffron_travertine_vertical_slab", () -> new DirectionalVerticalSlabBlock(AtmosphericProperties.SAFFRON_TRAVERTINE), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> DOLERITE = HELPER.createBlock("dolerite", () -> new InjectedBlock(Items.POLISHED_ANDESITE, AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> DOLERITE_STAIRS = HELPER.createBlock("dolerite_stairs", () -> new StairBlock(() -> DOLERITE.get().defaultBlockState(), AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> DOLERITE_SLAB = HELPER.createBlock("dolerite_slab", () -> new SlabBlock(AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> DOLERITE_WALL = HELPER.createBlock("dolerite_wall", () -> new WallBlock(AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DOLERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "dolerite_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLISHED_DOLERITE = HELPER.createBlock("polished_dolerite", () -> new InjectedBlock(Items.POLISHED_ANDESITE, AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLISHED_DOLERITE_STAIRS = HELPER.createBlock("polished_dolerite_stairs", () -> new StairBlock(() -> POLISHED_DOLERITE.get().defaultBlockState(), AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLISHED_DOLERITE_SLAB = HELPER.createBlock("polished_dolerite_slab", () -> new SlabBlock(AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLISHED_DOLERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_dolerite_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.DOLERITE), CreativeModeTab.TAB_BUILDING_BLOCKS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> ARID_SAND = HELPER.createBlock("arid_sand", () -> new AridSandBlock(14406560, AtmosphericProperties.ARID_SAND), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE = HELPER.createBlock("arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_SLAB = HELPER.createBlock("arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_STAIRS = HELPER.createBlock("arid_sandstone_stairs", () -> new StairBlock(() -> ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SANDSTONE_STAIRS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_WALL = HELPER.createBlock("arid_sandstone_wall", () -> new WallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL)), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE = HELPER.createBlock("smooth_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_SLAB = HELPER.createBlock("smooth_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_STAIRS = HELPER.createBlock("smooth_arid_sandstone_stairs", () -> new StairBlock(() -> SMOOTH_ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SMOOTH_SANDSTONE_STAIRS)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CUT_ARID_SANDSTONE = HELPER.createBlock("cut_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.CUT_SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_ARID_SANDSTONE_SLAB = HELPER.createBlock("cut_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.CUT_SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_ARID_SANDSTONE = HELPER.createBlock("chiseled_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.CHISELED_SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> RED_ARID_SAND = HELPER.createBlock("red_arid_sand", () -> new AridSandBlock(16241568, AtmosphericProperties.RED_ARID_SAND), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE = HELPER.createBlock("red_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("red_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_STAIRS = HELPER.createBlock("red_arid_sandstone_stairs", () -> new StairBlock(() -> RED_ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SANDSTONE_STAIRS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_WALL = HELPER.createBlock("red_arid_sandstone_wall", () -> new WallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL)), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE = HELPER.createBlock("smooth_red_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("smooth_red_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_STAIRS = HELPER.createBlock("smooth_red_arid_sandstone_stairs", () -> new StairBlock(() -> SMOOTH_RED_ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SMOOTH_SANDSTONE_STAIRS)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE = HELPER.createBlock("cut_red_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.CUT_SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("cut_red_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.CUT_SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_RED_ARID_SANDSTONE = HELPER.createBlock("chiseled_red_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.CHISELED_SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRIPPED_YUCCA_LOG = HELPER.createBlock("stripped_yucca_log", () -> new StrippedLogBlock(AtmosphericProperties.YUCCA.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_YUCCA_WOOD = HELPER.createBlock("stripped_yucca_wood", () -> new StrippedWoodBlock(AtmosphericProperties.YUCCA.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LOG = HELPER.createBlock("yucca_log", () -> new LogBlock(STRIPPED_YUCCA_LOG, AtmosphericProperties.YUCCA.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_WOOD = HELPER.createBlock("yucca_wood", () -> new WoodBlock(STRIPPED_YUCCA_WOOD, AtmosphericProperties.YUCCA.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LEAVES = HELPER.createBlock("yucca_leaves", () -> new YuccaLeavesBlock(AtmosphericProperties.YUCCA.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_SAPLING = HELPER.createBlock("yucca_sapling", () -> new YuccaSaplingBlock(new YuccaTreeGrower(), AtmosphericProperties.YUCCA.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_YUCCA_SAPLING = HELPER.createBlockNoItem("potted_yucca_sapling", () -> new FlowerPotBlock(YUCCA_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> YUCCA_PLANKS = HELPER.createBlock("yucca_planks", () -> new PlanksBlock(AtmosphericProperties.YUCCA.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_STAIRS = HELPER.createBlock("yucca_stairs", () -> new WoodStairBlock(YUCCA_PLANKS.get().defaultBlockState(), AtmosphericProperties.YUCCA.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_SLAB = HELPER.createBlock("yucca_slab", () -> new WoodSlabBlock(AtmosphericProperties.YUCCA.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_PRESSURE_PLATE = HELPER.createBlock("yucca_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.YUCCA.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> YUCCA_BUTTON = HELPER.createBlock("yucca_button", () -> new BlueprintWoodButtonBlock(AtmosphericProperties.YUCCA.button()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> YUCCA_FENCE = HELPER.createFuelBlock("yucca_fence", () -> new WoodFenceBlock(AtmosphericProperties.YUCCA.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_FENCE_GATE = HELPER.createFuelBlock("yucca_fence_gate", () -> new WoodFenceGateBlock(AtmosphericProperties.YUCCA.planks()), 300, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> YUCCA_DOOR = HELPER.createBlock("yucca_door", () -> new WoodDoorBlock(AtmosphericProperties.YUCCA.door()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> YUCCA_TRAPDOOR = HELPER.createBlock("yucca_trapdoor", () -> new WoodTrapDoorBlock(AtmosphericProperties.YUCCA.trapdoor()), CreativeModeTab.TAB_REDSTONE);
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> YUCCA_SIGNS = HELPER.createSignBlock("yucca", MaterialColor.COLOR_ORANGE);

	public static final RegistryObject<Block> YUCCA_BOARDS = HELPER.createCompatFuelBlock("woodworks", "yucca_boards", () -> new RotatedPillarBlock(AtmosphericProperties.YUCCA.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_BOOKSHELF = HELPER.createCompatFuelBlock("woodworks", "yucca_bookshelf", () -> new BookshelfBlock(AtmosphericProperties.YUCCA.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LADDER = HELPER.createCompatFuelBlock("woodworks", "yucca_ladder", () -> new BlueprintLadderBlock(AtmosphericProperties.YUCCA.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_BEEHIVE = HELPER.createCompatBlock("woodworks", "yucca_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.YUCCA.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_LEAF_PILE = HELPER.createCompatBlock("woodworks", "yucca_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.YUCCA.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> YUCCA_CHESTS = HELPER.createCompatChestBlocks("woodworks", "yucca", MaterialColor.COLOR_ORANGE);

	public static final RegistryObject<Block> VERTICAL_YUCCA_PLANKS = HELPER.createCompatBlock("quark", "vertical_yucca_planks", () -> new Block(AtmosphericProperties.YUCCA.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "yucca_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.YUCCA.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_YUCCA_POST = HELPER.createCompatFuelBlock("quark", "stripped_yucca_post", () -> new WoodPostBlock(AtmosphericProperties.YUCCA.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_POST = HELPER.createCompatFuelBlock("quark", "yucca_post", () -> new WoodPostBlock(STRIPPED_YUCCA_POST, AtmosphericProperties.YUCCA.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LEAF_CARPET = HELPER.createCompatBlock("quark", "yucca_leaf_carpet", () -> new YuccaLeafCarpetBlock(AtmosphericProperties.YUCCA.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_HEDGE = HELPER.createCompatFuelBlock("quark", "yucca_hedge", () -> new YuccaHedgeBlock(AtmosphericProperties.YUCCA.log()), 300, CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> YUCCA_BRANCH = HELPER.createBlock("yucca_branch", () -> new YuccaBranchBlock(Block.Properties.copy(Blocks.MELON_STEM).sound(SoundType.CROP).randomTicks()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_BUNDLE = HELPER.createBlock("yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.copy(Blocks.MELON).randomTicks()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROASTED_YUCCA_BUNDLE = HELPER.createBlock("roasted_yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.copy(Blocks.MELON).randomTicks()), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> YUCCA_GATEAU = HELPER.createBlockNoItem("yucca_gateau", () -> new YuccaGateauBlock(Block.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> YUCCA_FLOWER = HELPER.createBlock("yucca_flower", () -> new YuccaFlowerBlock(AtmosphericMobEffects.PERSISTENCE::get, 15, AtmosphericProperties.YUCCA_FLOWER), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_YUCCA_FLOWER = HELPER.createBlockNoItem("potted_yucca_flower", () -> new FlowerPotBlock(YUCCA_FLOWER.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> TALL_YUCCA_FLOWER = HELPER.createBlock("tall_yucca_flower", () -> new YuccaFlowerDoubleBlock(AtmosphericProperties.YUCCA_FLOWER), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> GILIA = HELPER.createBlock("gilia", () -> new DesertFlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_GILIA = HELPER.createBlockNoItem("potted_gilia", () -> new FlowerPotBlock(GILIA.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> ARID_SPROUTS = HELPER.createBlock("arid_sprouts", () -> new AridSproutsBlock(AtmosphericProperties.ARID_SPROUTS), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ALOE_VERA = HELPER.createBlockNoItem("aloe_vera", () -> new AloeVeraBlock(AtmosphericProperties.ALOE_VERA));
	public static final RegistryObject<Block> TALL_ALOE_VERA = HELPER.createBlockNoItem("tall_aloe_vera", () -> new AloeVeraTallBlock(AtmosphericProperties.ALOE_VERA));
	public static final RegistryObject<Block> ALOE_BUNDLE = HELPER.createBlock("aloe_bundle", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.DRIED_KELP_BLOCK)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ALOE_GEL_BLOCK = HELPER.createBlock("aloe_gel_block", () -> new AloeGelBlock(Block.Properties.copy(Blocks.SLIME_BLOCK).isSuffocating(PropertyUtil::never)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BARREL_CACTUS = HELPER.createBlockNoItem("barrel_cactus", () -> new BarrelCactusBlock(Block.Properties.copy(Blocks.CACTUS)));
	public static final RegistryObject<Block> POTTED_BARREL_CACTUS = HELPER.createBlockNoItem("potted_barrel_cactus", () -> new FlowerPotBlock(BARREL_CACTUS.get(), Block.Properties.copy(Blocks.POTTED_CACTUS)));

	public static final RegistryObject<Block> ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "cut_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "smooth_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> ARID_SANDSTONE_BRICKS = HELPER.createCompatBlock("quark", "arid_sandstone_bricks", () -> new Block(Block.Properties.copy(Blocks.SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_SLAB = HELPER.createCompatBlock("quark", "arid_sandstone_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_STAIRS = HELPER.createCompatBlock("quark", "arid_sandstone_brick_stairs", () -> new StairBlock(() -> ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SANDSTONE_STAIRS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_WALL = HELPER.createCompatBlock("quark", "arid_sandstone_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "arid_sandstone_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> RED_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "red_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "cut_red_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "smooth_red_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICKS = HELPER.createCompatBlock("quark", "red_arid_sandstone_bricks", () -> new Block(Block.Properties.copy(Blocks.SANDSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_SLAB = HELPER.createCompatBlock("quark", "red_arid_sandstone_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_STAIRS = HELPER.createCompatBlock("quark", "red_arid_sandstone_brick_stairs", () -> new StairBlock(() -> RED_ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SANDSTONE_STAIRS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_WALL = HELPER.createCompatBlock("quark", "red_arid_sandstone_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "red_arid_sandstone_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> YUCCA_CASK = HELPER.createCompatBlock("quark", "yucca_cask", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(1.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROASTED_YUCCA_CASK = HELPER.createCompatBlock("quark", "roasted_yucca_cask", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(1.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BARREL_CACTUS_BATCH = HELPER.createCompatBlock("quark", "barrel_cactus_batch", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_ASPEN_LOG = HELPER.createBlock("stripped_aspen_log", () -> new StrippedLogBlock(AtmosphericProperties.ASPEN.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ASPEN_WOOD = HELPER.createBlock("stripped_aspen_wood", () -> new StrippedWoodBlock(AtmosphericProperties.ASPEN.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LOG = HELPER.createBlock("aspen_log", () -> new LogBlock(STRIPPED_ASPEN_LOG, AtmosphericProperties.ASPEN.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_WOOD = HELPER.createBlock("aspen_wood", () -> new WoodBlock(STRIPPED_ASPEN_WOOD, AtmosphericProperties.ASPEN.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> WATCHFUL_ASPEN_LOG = HELPER.createBlock("watchful_aspen_log", () -> new LogBlock(ASPEN_LOG, AtmosphericProperties.ASPEN.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> WATCHFUL_ASPEN_WOOD = HELPER.createBlock("watchful_aspen_wood", () -> new WoodBlock(ASPEN_WOOD, AtmosphericProperties.ASPEN.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LEAVES = HELPER.createBlock("aspen_leaves", () -> new BlueprintLeavesBlock(AtmosphericProperties.ASPEN.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_SAPLING = HELPER.createBlock("aspen_sapling", () -> new BlueprintSaplingBlock(new AspenTreeGrower(), AtmosphericProperties.ASPEN.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_ASPEN_SAPLING = HELPER.createBlockNoItem("potted_aspen_sapling", () -> new FlowerPotBlock(ASPEN_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> ASPEN_PLANKS = HELPER.createBlock("aspen_planks", () -> new PlanksBlock(AtmosphericProperties.ASPEN.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_STAIRS = HELPER.createBlock("aspen_stairs", () -> new WoodStairBlock(ASPEN_PLANKS.get().defaultBlockState(), AtmosphericProperties.ASPEN.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_SLAB = HELPER.createBlock("aspen_slab", () -> new WoodSlabBlock(AtmosphericProperties.ASPEN.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_PRESSURE_PLATE = HELPER.createBlock("aspen_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.ASPEN.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> ASPEN_BUTTON = HELPER.createBlock("aspen_button", () -> new BlueprintWoodButtonBlock(AtmosphericProperties.ASPEN.button()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> ASPEN_FENCE = HELPER.createFuelBlock("aspen_fence", () -> new WoodFenceBlock(AtmosphericProperties.ASPEN.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_FENCE_GATE = HELPER.createFuelBlock("aspen_fence_gate", () -> new WoodFenceGateBlock(AtmosphericProperties.ASPEN.planks()), 300, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> ASPEN_DOOR = HELPER.createBlock("aspen_door", () -> new WoodDoorBlock(AtmosphericProperties.ASPEN.door()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> ASPEN_TRAPDOOR = HELPER.createBlock("aspen_trapdoor", () -> new WoodTrapDoorBlock(AtmosphericProperties.ASPEN.trapdoor()), CreativeModeTab.TAB_REDSTONE);
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> ASPEN_SIGNS = HELPER.createSignBlock("aspen", MaterialColor.GOLD);

	public static final RegistryObject<Block> ASPEN_BOARDS = HELPER.createCompatFuelBlock("woodworks", "aspen_boards", () -> new RotatedPillarBlock(AtmosphericProperties.ASPEN.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_BOOKSHELF = HELPER.createCompatFuelBlock("woodworks", "aspen_bookshelf", () -> new BookshelfBlock(AtmosphericProperties.ASPEN.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LADDER = HELPER.createCompatFuelBlock("woodworks", "aspen_ladder", () -> new BlueprintLadderBlock(AtmosphericProperties.ASPEN.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_BEEHIVE = HELPER.createCompatBlock("woodworks", "aspen_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.ASPEN.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_LEAF_PILE = HELPER.createCompatBlock("woodworks", "aspen_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.ASPEN.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> ASPEN_CHESTS = HELPER.createCompatChestBlocks("woodworks", "aspen", MaterialColor.GOLD);

	public static final RegistryObject<Block> VERTICAL_ASPEN_PLANKS = HELPER.createCompatBlock("quark", "vertical_aspen_planks", () -> new Block(AtmosphericProperties.ASPEN.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "aspen_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.ASPEN.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ASPEN_POST = HELPER.createCompatFuelBlock("quark", "stripped_aspen_post", () -> new WoodPostBlock(AtmosphericProperties.ASPEN.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_POST = HELPER.createCompatFuelBlock("quark", "aspen_post", () -> new WoodPostBlock(STRIPPED_ASPEN_POST, AtmosphericProperties.ASPEN.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LEAF_CARPET = HELPER.createCompatBlock("quark", "aspen_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.ASPEN.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_HEDGE = HELPER.createCompatFuelBlock("quark", "aspen_hedge", () -> new HedgeBlock(AtmosphericProperties.ASPEN.log()), 300, CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> AGAVE = HELPER.createBlock("agave", () -> new AgaveBlock(AtmosphericProperties.AGAVE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_AGAVE = HELPER.createBlockNoItem("potted_agave", () -> new FlowerPotBlock(AGAVE.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> GOLDEN_GROWTHS = HELPER.createBlock("golden_growths", () -> new GoldenGrowthsBlock(AtmosphericProperties.GOLDEN_GROWTHS), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_GOLDEN_GROWTHS = HELPER.createBlockNoItem("potted_golden_growths", () -> new FlowerPotBlock(GOLDEN_GROWTHS.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> CRUSTOSE = HELPER.createBlock("crustose", () -> new CrustoseBlock(Block.Properties.copy(Blocks.GRASS_BLOCK).randomTicks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRUSTOSE_PATH = HELPER.createBlock("crustose_path", () -> new DirtPathBlock(AtmosphericProperties.CRUSTOSE_PATH), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CRUSTOSE_LOG = HELPER.createBlock("crustose_log", () -> new CrustoseLogBlock(ASPEN_LOG::get, AtmosphericProperties.ASPEN.log().randomTicks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRUSTOSE_WOOD = HELPER.createBlock("crustose_wood", () -> new CrustoseWoodBlock(ASPEN_WOOD::get, AtmosphericProperties.ASPEN.log().randomTicks()), CreativeModeTab.TAB_BUILDING_BLOCKS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_LAUREL_LOG = HELPER.createBlock("stripped_laurel_log", () -> new StrippedLogBlock(AtmosphericProperties.LAUREL.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_LAUREL_WOOD = HELPER.createBlock("stripped_laurel_wood", () -> new StrippedWoodBlock(AtmosphericProperties.LAUREL.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_LOG = HELPER.createBlock("laurel_log", () -> new LogBlock(STRIPPED_LAUREL_LOG, AtmosphericProperties.LAUREL.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_WOOD = HELPER.createBlock("laurel_wood", () -> new WoodBlock(STRIPPED_LAUREL_WOOD, AtmosphericProperties.LAUREL.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_LEAVES = HELPER.createBlock("laurel_leaves", () -> new BlueprintLeavesBlock(AtmosphericProperties.LAUREL.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> LAUREL_SAPLING = HELPER.createBlock("laurel_sapling", () -> new BlueprintSaplingBlock(new LaurelTreeGrower(), AtmosphericProperties.LAUREL.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_LAUREL_SAPLING = HELPER.createBlockNoItem("potted_laurel_sapling", () -> new FlowerPotBlock(LAUREL_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> LAUREL_PLANKS = HELPER.createBlock("laurel_planks", () -> new PlanksBlock(AtmosphericProperties.LAUREL.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_STAIRS = HELPER.createBlock("laurel_stairs", () -> new WoodStairBlock(LAUREL_PLANKS.get().defaultBlockState(), AtmosphericProperties.LAUREL.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_SLAB = HELPER.createBlock("laurel_slab", () -> new WoodSlabBlock(AtmosphericProperties.LAUREL.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_PRESSURE_PLATE = HELPER.createBlock("laurel_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.LAUREL.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> LAUREL_BUTTON = HELPER.createBlock("laurel_button", () -> new BlueprintWoodButtonBlock(AtmosphericProperties.LAUREL.button()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> LAUREL_FENCE = HELPER.createFuelBlock("laurel_fence", () -> new WoodFenceBlock(AtmosphericProperties.LAUREL.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> LAUREL_FENCE_GATE = HELPER.createFuelBlock("laurel_fence_gate", () -> new WoodFenceGateBlock(AtmosphericProperties.LAUREL.planks()), 300, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> LAUREL_DOOR = HELPER.createBlock("laurel_door", () -> new WoodDoorBlock(AtmosphericProperties.LAUREL.door()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> LAUREL_TRAPDOOR = HELPER.createBlock("laurel_trapdoor", () -> new WoodTrapDoorBlock(AtmosphericProperties.LAUREL.trapdoor()), CreativeModeTab.TAB_REDSTONE);
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> LAUREL_SIGNS = HELPER.createSignBlock("laurel", MaterialColor.TERRACOTTA_CYAN);

	public static final RegistryObject<Block> LAUREL_BOARDS = HELPER.createCompatFuelBlock("woodworks", "laurel_boards", () -> new RotatedPillarBlock(AtmosphericProperties.LAUREL.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_BOOKSHELF = HELPER.createCompatFuelBlock("woodworks", "laurel_bookshelf", () -> new BookshelfBlock(AtmosphericProperties.LAUREL.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_LADDER = HELPER.createCompatFuelBlock("woodworks", "laurel_ladder", () -> new BlueprintLadderBlock(AtmosphericProperties.LAUREL.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> LAUREL_BEEHIVE = HELPER.createCompatBlock("woodworks", "laurel_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.LAUREL.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> LAUREL_LEAF_PILE = HELPER.createCompatBlock("woodworks", "laurel_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.LAUREL.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> LAUREL_CHESTS = HELPER.createCompatChestBlocks("woodworks", "laurel", MaterialColor.TERRACOTTA_CYAN);

	public static final RegistryObject<Block> VERTICAL_LAUREL_PLANKS = HELPER.createCompatBlock("quark", "vertical_laurel_planks", () -> new Block(AtmosphericProperties.LAUREL.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "laurel_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.LAUREL.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_LAUREL_POST = HELPER.createCompatFuelBlock("quark", "stripped_laurel_post", () -> new WoodPostBlock(AtmosphericProperties.LAUREL.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_POST = HELPER.createCompatFuelBlock("quark", "laurel_post", () -> new WoodPostBlock(STRIPPED_LAUREL_POST, AtmosphericProperties.LAUREL.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> LAUREL_LEAF_CARPET = HELPER.createCompatBlock("quark", "laurel_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.LAUREL.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> LAUREL_HEDGE = HELPER.createCompatFuelBlock("quark", "laurel_hedge", () -> new HedgeBlock(AtmosphericProperties.LAUREL.log()), 300, CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> DRY_LAUREL_LEAVES = HELPER.createBlock("dry_laurel_leaves", () -> new BlueprintLeavesBlock(AtmosphericProperties.DRY_LAUREL.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DRY_LAUREL_SAPLING = HELPER.createBlock("dry_laurel_sapling", () -> new BlueprintSaplingBlock(new DryLaurelTreeGrower(), AtmosphericProperties.DRY_LAUREL.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_DRY_LAUREL_SAPLING = HELPER.createBlockNoItem("potted_dry_laurel_sapling", () -> new FlowerPotBlock(DRY_LAUREL_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> DRY_LAUREL_LEAF_PILE = HELPER.createCompatBlock("woodworks", "dry_laurel_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.DRY_LAUREL.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DRY_LAUREL_LEAF_CARPET = HELPER.createCompatBlock("quark", "dry_laurel_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.DRY_LAUREL.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DRY_LAUREL_HEDGE = HELPER.createCompatFuelBlock("quark", "dry_laurel_hedge", () -> new HedgeBlock(AtmosphericProperties.DRY_LAUREL.log()), 300, CreativeModeTab.TAB_DECORATIONS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_KOUSA_LOG = HELPER.createBlock("stripped_kousa_log", () -> new StrippedLogBlock(AtmosphericProperties.KOUSA.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_KOUSA_WOOD = HELPER.createBlock("stripped_kousa_wood", () -> new StrippedWoodBlock(AtmosphericProperties.KOUSA.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LOG = HELPER.createBlock("kousa_log", () -> new LogBlock(STRIPPED_KOUSA_LOG, AtmosphericProperties.KOUSA.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_WOOD = HELPER.createBlock("kousa_wood", () -> new WoodBlock(STRIPPED_KOUSA_WOOD, AtmosphericProperties.KOUSA.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LEAVES = HELPER.createBlock("kousa_leaves", () -> new BlueprintLeavesBlock(AtmosphericProperties.KOUSA.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_SAPLING = HELPER.createBlock("kousa_sapling", () -> new BlueprintSaplingBlock(new KousaTreeGrower(), AtmosphericProperties.KOUSA.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_KOUSA_SAPLING = HELPER.createBlockNoItem("potted_kousa_sapling", () -> new FlowerPotBlock(KOUSA_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> KOUSA_PLANKS = HELPER.createBlock("kousa_planks", () -> new PlanksBlock(AtmosphericProperties.KOUSA.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_STAIRS = HELPER.createBlock("kousa_stairs", () -> new WoodStairBlock(KOUSA_PLANKS.get().defaultBlockState(), AtmosphericProperties.KOUSA.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_SLAB = HELPER.createBlock("kousa_slab", () -> new WoodSlabBlock(AtmosphericProperties.KOUSA.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_PRESSURE_PLATE = HELPER.createBlock("kousa_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.KOUSA.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> KOUSA_BUTTON = HELPER.createBlock("kousa_button", () -> new BlueprintWoodButtonBlock(AtmosphericProperties.KOUSA.button()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> KOUSA_FENCE = HELPER.createFuelBlock("kousa_fence", () -> new WoodFenceBlock(AtmosphericProperties.KOUSA.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_FENCE_GATE = HELPER.createFuelBlock("kousa_fence_gate", () -> new WoodFenceGateBlock(AtmosphericProperties.KOUSA.planks()), 300, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> KOUSA_DOOR = HELPER.createBlock("kousa_door", () -> new WoodDoorBlock(AtmosphericProperties.KOUSA.door()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> KOUSA_TRAPDOOR = HELPER.createBlock("kousa_trapdoor", () -> new WoodTrapDoorBlock(AtmosphericProperties.KOUSA.trapdoor()), CreativeModeTab.TAB_REDSTONE);
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> KOUSA_SIGNS = HELPER.createSignBlock("kousa", MaterialColor.TERRACOTTA_CYAN);

	public static final RegistryObject<Block> KOUSA_BOARDS = HELPER.createCompatFuelBlock("woodworks", "kousa_boards", () -> new RotatedPillarBlock(AtmosphericProperties.KOUSA.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_BOOKSHELF = HELPER.createCompatFuelBlock("woodworks", "kousa_bookshelf", () -> new BookshelfBlock(AtmosphericProperties.KOUSA.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LADDER = HELPER.createCompatFuelBlock("woodworks", "kousa_ladder", () -> new BlueprintLadderBlock(AtmosphericProperties.KOUSA.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_BEEHIVE = HELPER.createCompatBlock("woodworks", "kousa_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.KOUSA.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_LEAF_PILE = HELPER.createCompatBlock("woodworks", "kousa_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.KOUSA.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> KOUSA_CHESTS = HELPER.createCompatChestBlocks("woodworks", "kousa", MaterialColor.TERRACOTTA_CYAN);

	public static final RegistryObject<Block> VERTICAL_KOUSA_PLANKS = HELPER.createCompatBlock("quark", "vertical_kousa_planks", () -> new Block(AtmosphericProperties.KOUSA.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "kousa_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.KOUSA.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_KOUSA_POST = HELPER.createCompatFuelBlock("quark", "stripped_kousa_post", () -> new WoodPostBlock(AtmosphericProperties.KOUSA.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_POST = HELPER.createCompatFuelBlock("quark", "kousa_post", () -> new WoodPostBlock(STRIPPED_KOUSA_POST, AtmosphericProperties.KOUSA.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LEAF_CARPET = HELPER.createCompatBlock("quark", "kousa_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.KOUSA.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_HEDGE = HELPER.createCompatFuelBlock("quark", "kousa_hedge", () -> new HedgeBlock(AtmosphericProperties.KOUSA.log()), 300, CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> SNOWY_BAMBOO_SAPLING = HELPER.createBlockNoItem("snowy_bamboo_sapling", () -> new SnowyBambooSaplingBlock(Properties.of(Material.BAMBOO_SAPLING).randomTicks().instabreak().noCollission().strength(1.0F).sound(SoundType.BAMBOO_SAPLING).offsetType(OffsetType.XZ)));
	public static final RegistryObject<Block> SNOWY_BAMBOO = HELPER.createBlockNoItem("snowy_bamboo", () -> new SnowyBambooBlock(Properties.of(Material.BAMBOO, MaterialColor.PLANT).randomTicks().instabreak().strength(1.0F).sound(SoundType.BAMBOO).noOcclusion().dynamicShape().offsetType(OffsetType.XZ)));
	public static final RegistryObject<Block> POTTED_SNOWY_BAMBOO = HELPER.createBlockNoItem("potted_snowy_bamboo", () -> new PottedSnowyBambooBlock(SNOWY_BAMBOO.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> HANGING_CURRANT = HELPER.createBlock("hanging_currant", () -> new HangingCurrantBlock(Block.Properties.copy(Blocks.MELON_STEM).sound(SoundType.CROP).randomTicks()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CURRANT_STALK = HELPER.createFuelBlock("currant_stalk", () -> new CurrantStalkBlock(AtmosphericProperties.CURRANT.log()), 50, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CURRANT_STALK_BUNDLE = HELPER.createFuelBlock("currant_stalk_bundle", () -> new CurrantStalkBundleBlock(AtmosphericProperties.CURRANT.log()), 450, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CURRANT_LEAVES = HELPER.createBlock("currant_leaves", () -> new CurrantLeavesBlock(AtmosphericProperties.CURRANT.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CURRANT_SEEDLING = HELPER.createBlock("currant_seedling", () -> new CurrantSeedlingBlock(AtmosphericProperties.CURRANT.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_CURRANT_SEEDLING = HELPER.createBlockNoItem("potted_currant_seedling", () -> new FlowerPotBlock(CURRANT_SEEDLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> CURRANT_LEAF_PILE = HELPER.createCompatBlock("woodworks", "currant_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.CURRANT.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CURRANT_LEAF_CARPET = HELPER.createCompatBlock("quark", "currant_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.CURRANT.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CURRANT_HEDGE = HELPER.createCompatFuelBlock("quark", "currant_hedge", () -> new HedgeBlock(AtmosphericProperties.CURRANT.log()), 50, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CURRANT_BASKET = HELPER.createCompatBlock("quark", "currant_basket", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_CYAN).strength(1.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_GRIMWOOD_LOG = HELPER.createBlock("stripped_grimwood_log", () -> new StrippedLogBlock(AtmosphericProperties.GRIMWOOD.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_GRIMWOOD = HELPER.createBlock("stripped_grimwood", () -> new StrippedWoodBlock(AtmosphericProperties.GRIMWOOD.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_LOG = HELPER.createBlock("grimwood_log", () -> new LogBlock(STRIPPED_GRIMWOOD_LOG, AtmosphericProperties.GRIMWOOD.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD = HELPER.createBlock("grimwood", () -> new WoodBlock(STRIPPED_GRIMWOOD, AtmosphericProperties.GRIMWOOD.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_LEAVES = HELPER.createBlock("grimwood_leaves", () -> new BlueprintLeavesBlock(AtmosphericProperties.GRIMWOOD.leaves()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_SAPLING = HELPER.createBlock("grimwood_sapling", () -> new BlueprintSaplingBlock(new GrimwoodTreeGrower(), AtmosphericProperties.GRIMWOOD.sapling()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_GRIMWOOD_SAPLING = HELPER.createBlockNoItem("potted_grimwood_sapling", () -> new FlowerPotBlock(GRIMWOOD_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> GRIMWOOD_PLANKS = HELPER.createBlock("grimwood_planks", () -> new PlanksBlock(AtmosphericProperties.GRIMWOOD.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_STAIRS = HELPER.createBlock("grimwood_stairs", () -> new WoodStairBlock(GRIMWOOD_PLANKS.get().defaultBlockState(), AtmosphericProperties.GRIMWOOD.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_SLAB = HELPER.createBlock("grimwood_slab", () -> new WoodSlabBlock(AtmosphericProperties.GRIMWOOD.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_PRESSURE_PLATE = HELPER.createBlock("grimwood_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.GRIMWOOD.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> GRIMWOOD_BUTTON = HELPER.createBlock("grimwood_button", () -> new BlueprintWoodButtonBlock(AtmosphericProperties.GRIMWOOD.button()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> GRIMWOOD_FENCE = HELPER.createFuelBlock("grimwood_fence", () -> new WoodFenceBlock(AtmosphericProperties.GRIMWOOD.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_FENCE_GATE = HELPER.createFuelBlock("grimwood_fence_gate", () -> new WoodFenceGateBlock(AtmosphericProperties.GRIMWOOD.planks()), 300, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> GRIMWOOD_DOOR = HELPER.createBlock("grimwood_door", () -> new WoodDoorBlock(AtmosphericProperties.GRIMWOOD.door()), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> GRIMWOOD_TRAPDOOR = HELPER.createBlock("grimwood_trapdoor", () -> new WoodTrapDoorBlock(AtmosphericProperties.GRIMWOOD.trapdoor()), CreativeModeTab.TAB_REDSTONE);
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> GRIMWOOD_SIGNS = HELPER.createSignBlock("grimwood", MaterialColor.TERRACOTTA_BLACK);

	public static final RegistryObject<Block> GRIMWOOD_BOARDS = HELPER.createCompatFuelBlock("woodworks", "grimwood_boards", () -> new RotatedPillarBlock(AtmosphericProperties.GRIMWOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_BOOKSHELF = HELPER.createCompatFuelBlock("woodworks", "grimwood_bookshelf", () -> new BookshelfBlock(AtmosphericProperties.GRIMWOOD.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_LADDER = HELPER.createCompatFuelBlock("woodworks", "grimwood_ladder", () -> new BlueprintLadderBlock(AtmosphericProperties.GRIMWOOD.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_BEEHIVE = HELPER.createCompatBlock("woodworks", "grimwood_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.GRIMWOOD.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_LEAF_PILE = HELPER.createCompatBlock("woodworks", "grimwood_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.GRIMWOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> GRIMWOOD_CHESTS = HELPER.createCompatChestBlocks("woodworks", "grimwood", MaterialColor.TERRACOTTA_BLACK);

	public static final RegistryObject<Block> VERTICAL_GRIMWOOD_PLANKS = HELPER.createCompatBlock("quark", "vertical_grimwood_planks", () -> new Block(AtmosphericProperties.GRIMWOOD.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "grimwood_vertical_slab", () -> new VerticalSlabBlock(AtmosphericProperties.GRIMWOOD.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_GRIMWOOD_POST = HELPER.createCompatFuelBlock("quark", "stripped_grimwood_post", () -> new WoodPostBlock(AtmosphericProperties.GRIMWOOD.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_POST = HELPER.createCompatFuelBlock("quark", "grimwood_post", () -> new WoodPostBlock(STRIPPED_GRIMWOOD_POST, AtmosphericProperties.GRIMWOOD.log()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_LEAF_CARPET = HELPER.createCompatBlock("quark", "grimwood_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.GRIMWOOD.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_HEDGE = HELPER.createCompatFuelBlock("quark", "grimwood_hedge", () -> new HedgeBlock(AtmosphericProperties.GRIMWOOD.log()), 300, CreativeModeTab.TAB_DECORATIONS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> CARMINE_BLOCK = HELPER.createBlock("carmine_block", () -> new CarmineBlock(AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARMINE_SHINGLES = HELPER.createBlock("carmine_shingles", () -> new Block(AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARMINE_SHINGLE_STAIRS = HELPER.createBlock("carmine_shingle_stairs", () -> new StairBlock(() -> CARMINE_BLOCK.get().defaultBlockState(), AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARMINE_SHINGLE_SLAB = HELPER.createBlock("carmine_shingle_slab", () -> new SlabBlock(AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARMINE_SHINGLE_WALL = HELPER.createBlock("carmine_shingle_wall", () -> new WallBlock(AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CARMINE_SHINGLE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "carmine_shingle_vertical_slab", () -> new VerticalSlabBlock(Properties.copy(CARMINE_SHINGLES.get())), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_CARMINE_SHINGLES = HELPER.createBlock("chiseled_carmine_shingles", () -> new Block(AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARMINE_PAVEMENT = HELPER.createBlock("carmine_pavement", () -> new Block(AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARMINE_PAVEMENT_STAIRS = HELPER.createBlock("carmine_pavement_stairs", () -> new StairBlock(() -> CARMINE_BLOCK.get().defaultBlockState(), AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARMINE_PAVEMENT_SLAB = HELPER.createBlock("carmine_pavement_slab", () -> new SlabBlock(AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARMINE_PAVEMENT_WALL = HELPER.createBlock("carmine_pavement_wall", () -> new WallBlock(AtmosphericProperties.CARMINE_BLOCK), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CARMINE_PAVEMENT_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "carmine_pavement_vertical_slab", () -> new VerticalSlabBlock(Properties.copy(CARMINE_PAVEMENT.get())), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> FIRETHORN = HELPER.createBlock("firethorn", () -> new DesertFlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_FIRETHORN = HELPER.createBlockNoItem("potted_firethorn", () -> new FlowerPotBlock(FIRETHORN.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> FORSYTHIA = HELPER.createBlock("forsythia", () -> new DesertFlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_FORSYTHIA = HELPER.createBlockNoItem("potted_forsythia", () -> new FlowerPotBlock(FORSYTHIA.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> DRAGON_FRUIT_CRATE = HELPER.createCompatBlock("quark", "dragon_fruit_crate", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_MAGENTA).strength(1.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> GOLDEN_DRAGON_FRUIT_CRATE = HELPER.createCompatBlock("quark", "golden_dragon_fruit_crate", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.GOLD).strength(1.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);
}
