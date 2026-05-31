package com.teamabnormals.atmospheric.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.common.block.*;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericConstants;
import com.teamabnormals.atmospheric.core.other.AtmosphericProperties;
import com.teamabnormals.atmospheric.core.other.AtmosphericTreeGrowers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericSoundEvents.AtmosphericSoundTypes;
import com.teamabnormals.atmospheric.core.registry.helper.AtmosphericBlockSubRegistryHelper;
import com.teamabnormals.blueprint.common.block.*;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Predicate;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class AtmosphericBlocks {

	public static final AtmosphericBlockSubRegistryHelper BLOCKS = Atmospheric.REGISTRY_HELPER.getBlockSubHelper();

	/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final DeferredBlock<Block> STRIPPED_ROSEWOOD_LOG = BLOCKS.createBlock("stripped_rosewood_log", () -> new RotatedPillarBlock(AtmosphericProperties.ROSEWOOD.log()));
	public static final DeferredBlock<Block> STRIPPED_ROSEWOOD = BLOCKS.createBlock("stripped_rosewood", () -> new RotatedPillarBlock(AtmosphericProperties.ROSEWOOD.log()));
	public static final DeferredBlock<Block> ROSEWOOD_LOG = BLOCKS.createBlock("rosewood_log", () -> new LogBlock(STRIPPED_ROSEWOOD_LOG, AtmosphericProperties.ROSEWOOD.log()));
	public static final DeferredBlock<Block> ROSEWOOD = BLOCKS.createBlock("rosewood", () -> new LogBlock(STRIPPED_ROSEWOOD, AtmosphericProperties.ROSEWOOD.log()));
	public static final DeferredBlock<Block> ROSEWOOD_LEAVES = BLOCKS.createBlock("rosewood_leaves", () -> new LeavesBlock(AtmosphericProperties.ROSEWOOD.leaves()));
	public static final DeferredBlock<Block> ROSEWOOD_SAPLING = BLOCKS.createBlock("rosewood_sapling", () -> new SaplingBlock(AtmosphericTreeGrowers.ROSEWOOD, AtmosphericProperties.ROSEWOOD.sapling()));
	public static final DeferredBlock<Block> POTTED_ROSEWOOD_SAPLING = BLOCKS.createBlockNoItem("potted_rosewood_sapling", () -> new FlowerPotBlock(ROSEWOOD_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> ROSEWOOD_PLANKS = BLOCKS.createBlock("rosewood_planks", () -> new Block(AtmosphericProperties.ROSEWOOD.planks()));
	public static final DeferredBlock<Block> ROSEWOOD_STAIRS = BLOCKS.createBlock("rosewood_stairs", () -> new StairBlock(ROSEWOOD_PLANKS.get().defaultBlockState(), AtmosphericProperties.ROSEWOOD.planks()));
	public static final DeferredBlock<Block> ROSEWOOD_SLAB = BLOCKS.createBlock("rosewood_slab", () -> new SlabBlock(AtmosphericProperties.ROSEWOOD.planks()));
	public static final DeferredBlock<Block> ROSEWOOD_PRESSURE_PLATE = BLOCKS.createBlock("rosewood_pressure_plate", () -> new PressurePlateBlock(AtmosphericProperties.ROSEWOOD_BLOCK_SET, AtmosphericProperties.ROSEWOOD.pressurePlate()));
	public static final DeferredBlock<Block> ROSEWOOD_BUTTON = BLOCKS.createBlock("rosewood_button", () -> new ButtonBlock(AtmosphericProperties.ROSEWOOD_BLOCK_SET, 30, AtmosphericProperties.ROSEWOOD.button()));
	public static final DeferredBlock<Block> ROSEWOOD_FENCE = BLOCKS.createBlock("rosewood_fence", () -> new FenceBlock(AtmosphericProperties.ROSEWOOD.planks()));
	public static final DeferredBlock<Block> ROSEWOOD_FENCE_GATE = BLOCKS.createBlock("rosewood_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.ROSEWOOD_WOOD_TYPE, AtmosphericProperties.ROSEWOOD.planks()));
	public static final DeferredBlock<Block> ROSEWOOD_DOOR = BLOCKS.createBlock("rosewood_door", () -> new DoorBlock(AtmosphericProperties.ROSEWOOD_BLOCK_SET, AtmosphericProperties.ROSEWOOD.door()));
	public static final DeferredBlock<Block> ROSEWOOD_TRAPDOOR = BLOCKS.createBlock("rosewood_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.ROSEWOOD_BLOCK_SET, AtmosphericProperties.ROSEWOOD.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> ROSEWOOD_SIGNS = BLOCKS.createSignBlock("rosewood", AtmosphericProperties.ROSEWOOD_WOOD_TYPE, AtmosphericProperties.ROSEWOOD.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> ROSEWOOD_HANGING_SIGNS = BLOCKS.createHangingSignBlock("rosewood", AtmosphericProperties.ROSEWOOD_WOOD_TYPE, AtmosphericProperties.ROSEWOOD.hangingSign());

	public static final DeferredBlock<Block> ROSEWOOD_BOARDS = BLOCKS.createBlock("rosewood_boards", () -> new RotatedPillarBlock(AtmosphericProperties.ROSEWOOD.planks()));
	public static final DeferredBlock<Block> ROSEWOOD_BOOKSHELF = BLOCKS.createBlock("rosewood_bookshelf", () -> new Block(AtmosphericProperties.ROSEWOOD.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_ROSEWOOD_BOOKSHELF = BLOCKS.createBlock("chiseled_rosewood_bookshelf", () -> new ChiseledRosewoodBookShelfBlock(AtmosphericProperties.ROSEWOOD.chiseledBookshelf()));
	public static final DeferredBlock<Block> ROSEWOOD_LADDER = BLOCKS.createBlock("rosewood_ladder", () -> new LadderBlock(AtmosphericProperties.ROSEWOOD.ladder()));
	public static final DeferredBlock<Block> ROSEWOOD_BEEHIVE = BLOCKS.createBlock("rosewood_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.ROSEWOOD.beehive()));
	public static final DeferredBlock<Block> ROSEWOOD_LEAF_PILE = BLOCKS.createBlock("rosewood_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.ROSEWOOD.leafPile()));
	public static final DeferredBlock<BlueprintChestBlock> ROSEWOOD_CHEST = BLOCKS.createChestBlock("rosewood", AtmosphericProperties.ROSEWOOD.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_ROSEWOOD_CHEST = BLOCKS.createTrappedChestBlock("rosewood", AtmosphericProperties.ROSEWOOD.chest());

	public static final DeferredBlock<Block> STRIPPED_MORADO_LOG = BLOCKS.createBlock("stripped_morado_log", () -> new RotatedPillarBlock(AtmosphericProperties.MORADO.log()));
	public static final DeferredBlock<Block> STRIPPED_MORADO_WOOD = BLOCKS.createBlock("stripped_morado_wood", () -> new RotatedPillarBlock(AtmosphericProperties.MORADO.log()));
	public static final DeferredBlock<Block> MORADO_LOG = BLOCKS.createBlock("morado_log", () -> new LogBlock(STRIPPED_MORADO_LOG, AtmosphericProperties.MORADO.log()));
	public static final DeferredBlock<Block> MORADO_WOOD = BLOCKS.createBlock("morado_wood", () -> new LogBlock(STRIPPED_MORADO_WOOD, AtmosphericProperties.MORADO.log()));
	public static final DeferredBlock<Block> MORADO_LEAVES = BLOCKS.createBlock("morado_leaves", () -> new LeavesBlock(AtmosphericProperties.MORADO.leaves()));
	public static final DeferredBlock<Block> MORADO_SAPLING = BLOCKS.createBlock("morado_sapling", () -> new SaplingBlock(AtmosphericTreeGrowers.MORADO, AtmosphericProperties.MORADO.sapling()));
	public static final DeferredBlock<Block> POTTED_MORADO_SAPLING = BLOCKS.createBlockNoItem("potted_morado_sapling", () -> new FlowerPotBlock(MORADO_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> MORADO_PLANKS = BLOCKS.createBlock("morado_planks", () -> new Block(AtmosphericProperties.MORADO.planks()));
	public static final DeferredBlock<Block> MORADO_STAIRS = BLOCKS.createBlock("morado_stairs", () -> new StairBlock(MORADO_PLANKS.get().defaultBlockState(), AtmosphericProperties.MORADO.planks()));
	public static final DeferredBlock<Block> MORADO_SLAB = BLOCKS.createBlock("morado_slab", () -> new SlabBlock(AtmosphericProperties.MORADO.planks()));
	public static final DeferredBlock<Block> MORADO_PRESSURE_PLATE = BLOCKS.createBlock("morado_pressure_plate", () -> new PressurePlateBlock(AtmosphericProperties.MORADO_BLOCK_SET, AtmosphericProperties.MORADO.pressurePlate()));
	public static final DeferredBlock<Block> MORADO_BUTTON = BLOCKS.createBlock("morado_button", () -> new ButtonBlock(AtmosphericProperties.MORADO_BLOCK_SET, 30, AtmosphericProperties.MORADO.button()));
	public static final DeferredBlock<Block> MORADO_FENCE = BLOCKS.createBlock("morado_fence", () -> new FenceBlock(AtmosphericProperties.MORADO.planks()));
	public static final DeferredBlock<Block> MORADO_FENCE_GATE = BLOCKS.createBlock("morado_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.MORADO_WOOD_TYPE, AtmosphericProperties.MORADO.planks()));
	public static final DeferredBlock<Block> MORADO_DOOR = BLOCKS.createBlock("morado_door", () -> new DoorBlock(AtmosphericProperties.MORADO_BLOCK_SET, AtmosphericProperties.MORADO.door()));
	public static final DeferredBlock<Block> MORADO_TRAPDOOR = BLOCKS.createBlock("morado_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.MORADO_BLOCK_SET, AtmosphericProperties.MORADO.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> MORADO_SIGNS = BLOCKS.createSignBlock("morado", AtmosphericProperties.MORADO_WOOD_TYPE, AtmosphericProperties.MORADO.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> MORADO_HANGING_SIGNS = BLOCKS.createHangingSignBlock("morado", AtmosphericProperties.MORADO_WOOD_TYPE, AtmosphericProperties.MORADO.hangingSign());

	public static final DeferredBlock<Block> MORADO_BOARDS = BLOCKS.createBlock("morado_boards", () -> new RotatedPillarBlock(AtmosphericProperties.MORADO.planks()));
	public static final DeferredBlock<Block> MORADO_BOOKSHELF = BLOCKS.createBlock("morado_bookshelf", () -> new Block(AtmosphericProperties.MORADO.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_MORADO_BOOKSHELF = BLOCKS.createBlock("chiseled_morado_bookshelf", () -> new ChiseledMoradoBookShelfBlock(AtmosphericProperties.MORADO.chiseledBookshelf()));
	public static final DeferredBlock<Block> MORADO_LADDER = BLOCKS.createBlock("morado_ladder", () -> new LadderBlock(AtmosphericProperties.MORADO.ladder()));
	public static final DeferredBlock<Block> MORADO_BEEHIVE = BLOCKS.createBlock("morado_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.MORADO.beehive()));
	public static final DeferredBlock<Block> MORADO_LEAF_PILE = BLOCKS.createBlock("morado_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.MORADO.leafPile()));
	public static final DeferredBlock<BlueprintChestBlock> MORADO_CHEST = BLOCKS.createChestBlock("morado", AtmosphericProperties.MORADO.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_MORADO_CHEST = BLOCKS.createTrappedChestBlock("morado", AtmosphericProperties.MORADO.chest());

	public static final DeferredBlock<Block> FLOWERING_MORADO_LEAVES = BLOCKS.createBlock("flowering_morado_leaves", () -> new FloweringMoradoLeavesBlock(AtmosphericProperties.MORADO.leaves()));
	public static final DeferredBlock<Block> FLOWERING_MORADO_LEAF_PILE = BLOCKS.createBlock("flowering_morado_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.MORADO.leafPile()));

	public static final DeferredBlock<Block> PASSION_VINE = BLOCKS.createBlock("passion_vine", () -> new PassionVineBlock(Block.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.VINE).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> PASSION_VINE_BUNDLE = BLOCKS.createBlock("passion_vine_bundle", () -> new PassionVineBundleBlock(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS)));

	public static final DeferredBlock<Block> WATER_HYACINTH = BLOCKS.createBlockNoItem("water_hyacinth", () -> new WaterHyacinthBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.WATER_HYACINTH)));

	public static final DeferredBlock<Block> WARM_MONKEY_BRUSH = BLOCKS.createBlockNoItem("warm_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH)));
	public static final DeferredBlock<Block> HOT_MONKEY_BRUSH = BLOCKS.createBlockNoItem("hot_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH)));
	public static final DeferredBlock<Block> SCALDING_MONKEY_BRUSH = BLOCKS.createBlockNoItem("scalding_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH)));

	public static final DeferredBlock<Block> WARM_WALL_MONKEY_BRUSH = BLOCKS.createWallOrVerticalBlock("warm_monkey_brush", "warm_wall_monkey_brush", WARM_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH).offsetType(OffsetType.NONE).lootFrom(WARM_MONKEY_BRUSH)));
	public static final DeferredBlock<Block> HOT_WALL_MONKEY_BRUSH = BLOCKS.createWallOrVerticalBlock("hot_monkey_brush", "hot_wall_monkey_brush", HOT_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH).offsetType(OffsetType.NONE).lootFrom(HOT_MONKEY_BRUSH)));
	public static final DeferredBlock<Block> SCALDING_WALL_MONKEY_BRUSH = BLOCKS.createWallOrVerticalBlock("scalding_monkey_brush", "scalding_wall_monkey_brush", SCALDING_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH).offsetType(OffsetType.NONE).lootFrom(SCALDING_MONKEY_BRUSH)));

	public static final DeferredBlock<Block> POTTED_WARM_MONKEY_BRUSH = BLOCKS.createBlockNoItem("potted_warm_monkey_brush", () -> new FlowerPotBlock(WARM_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> POTTED_HOT_MONKEY_BRUSH = BLOCKS.createBlockNoItem("potted_hot_monkey_brush", () -> new FlowerPotBlock(HOT_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> POTTED_SCALDING_MONKEY_BRUSH = BLOCKS.createBlockNoItem("potted_scalding_monkey_brush", () -> new FlowerPotBlock(SCALDING_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> POTTED_WATER_HYACINTH = BLOCKS.createBlockNoItem("potted_water_hyacinth", () -> new FlowerPotBlock(WATER_HYACINTH.get(), PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> PASSION_FRUIT_CRATE = BLOCKS.createBlock("passion_fruit_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> SHIMMERING_PASSION_FRUIT_CRATE = BLOCKS.createBlock("shimmering_passion_fruit_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.GOLD).lightLevel((state) -> 7).strength(1.5F).sound(SoundType.WOOD)));

	/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final DeferredBlock<Block> IVORY_TRAVERTINE = BLOCKS.createBlock("ivory_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final DeferredBlock<Block> PEACH_TRAVERTINE = BLOCKS.createBlock("peach_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final DeferredBlock<Block> PERSIMMON_TRAVERTINE = BLOCKS.createBlock("persimmon_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final DeferredBlock<Block> SAFFRON_TRAVERTINE = BLOCKS.createBlock("saffron_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final DeferredBlock<Block> CHISELED_IVORY_TRAVERTINE = BLOCKS.createBlock("chiseled_ivory_travertine", () -> new Block(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final DeferredBlock<Block> CHISELED_PEACH_TRAVERTINE = BLOCKS.createBlock("chiseled_peach_travertine", () -> new Block(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final DeferredBlock<Block> CHISELED_PERSIMMON_TRAVERTINE = BLOCKS.createBlock("chiseled_persimmon_travertine", () -> new Block(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final DeferredBlock<Block> CHISELED_SAFFRON_TRAVERTINE = BLOCKS.createBlock("chiseled_saffron_travertine", () -> new Block(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final DeferredBlock<Block> CUT_IVORY_TRAVERTINE = BLOCKS.createBlock("cut_ivory_travertine", () -> new Block(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final DeferredBlock<Block> CUT_PEACH_TRAVERTINE = BLOCKS.createBlock("cut_peach_travertine", () -> new Block(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final DeferredBlock<Block> CUT_PERSIMMON_TRAVERTINE = BLOCKS.createBlock("cut_persimmon_travertine", () -> new Block(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final DeferredBlock<Block> CUT_SAFFRON_TRAVERTINE = BLOCKS.createBlock("cut_saffron_travertine", () -> new Block(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final DeferredBlock<Block> IVORY_TRAVERTINE_STAIRS = BLOCKS.createBlock("ivory_travertine_stairs", () -> new StairBlock(IVORY_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.IVORY_TRAVERTINE));
	public static final DeferredBlock<Block> PEACH_TRAVERTINE_STAIRS = BLOCKS.createBlock("peach_travertine_stairs", () -> new StairBlock(PEACH_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.PEACH_TRAVERTINE));
	public static final DeferredBlock<Block> PERSIMMON_TRAVERTINE_STAIRS = BLOCKS.createBlock("persimmon_travertine_stairs", () -> new StairBlock(PERSIMMON_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final DeferredBlock<Block> SAFFRON_TRAVERTINE_STAIRS = BLOCKS.createBlock("saffron_travertine_stairs", () -> new StairBlock(SAFFRON_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final DeferredBlock<Block> IVORY_TRAVERTINE_SLAB = BLOCKS.createBlock("ivory_travertine_slab", () -> new SlabBlock(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final DeferredBlock<Block> PEACH_TRAVERTINE_SLAB = BLOCKS.createBlock("peach_travertine_slab", () -> new SlabBlock(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final DeferredBlock<Block> PERSIMMON_TRAVERTINE_SLAB = BLOCKS.createBlock("persimmon_travertine_slab", () -> new SlabBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final DeferredBlock<Block> SAFFRON_TRAVERTINE_SLAB = BLOCKS.createBlock("saffron_travertine_slab", () -> new SlabBlock(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final DeferredBlock<Block> IVORY_TRAVERTINE_WALL = BLOCKS.createBlock("ivory_travertine_wall", () -> new WallBlock(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final DeferredBlock<Block> PEACH_TRAVERTINE_WALL = BLOCKS.createBlock("peach_travertine_wall", () -> new WallBlock(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final DeferredBlock<Block> PERSIMMON_TRAVERTINE_WALL = BLOCKS.createBlock("persimmon_travertine_wall", () -> new WallBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final DeferredBlock<Block> SAFFRON_TRAVERTINE_WALL = BLOCKS.createBlock("saffron_travertine_wall", () -> new WallBlock(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final DeferredBlock<Block> DOLERITE = BLOCKS.createBlock("dolerite", () -> new Block(AtmosphericProperties.DOLERITE));
	public static final DeferredBlock<Block> DOLERITE_STAIRS = BLOCKS.createBlock("dolerite_stairs", () -> new StairBlock(DOLERITE.get().defaultBlockState(), AtmosphericProperties.DOLERITE));
	public static final DeferredBlock<Block> DOLERITE_SLAB = BLOCKS.createBlock("dolerite_slab", () -> new SlabBlock(AtmosphericProperties.DOLERITE));
	public static final DeferredBlock<Block> DOLERITE_WALL = BLOCKS.createBlock("dolerite_wall", () -> new WallBlock(AtmosphericProperties.DOLERITE));
	public static final DeferredBlock<Block> POLISHED_DOLERITE = BLOCKS.createBlock("polished_dolerite", () -> new Block(AtmosphericProperties.POLISHED_DOLERITE));
	public static final DeferredBlock<Block> POLISHED_DOLERITE_STAIRS = BLOCKS.createBlock("polished_dolerite_stairs", () -> new StairBlock(POLISHED_DOLERITE.get().defaultBlockState(), AtmosphericProperties.POLISHED_DOLERITE));
	public static final DeferredBlock<Block> POLISHED_DOLERITE_SLAB = BLOCKS.createBlock("polished_dolerite_slab", () -> new SlabBlock(AtmosphericProperties.POLISHED_DOLERITE));
	public static final DeferredBlock<Block> POLISHED_DOLERITE_WALL = BLOCKS.createBlock("polished_dolerite_wall", () -> new WallBlock(AtmosphericProperties.POLISHED_DOLERITE));

	/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final DeferredBlock<Block> ARID_SAND = BLOCKS.createBlock("arid_sand", () -> new AridSandBlock(14406560, AtmosphericProperties.ARID_SAND));
	public static final DeferredBlock<Block> ARID_SANDSTONE = BLOCKS.createBlock("arid_sandstone", () -> new Block(AtmosphericProperties.aridSandstone()));
	public static final DeferredBlock<Block> ARID_SANDSTONE_SLAB = BLOCKS.createBlock("arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothAridSandstone()));
	public static final DeferredBlock<Block> ARID_SANDSTONE_STAIRS = BLOCKS.createBlock("arid_sandstone_stairs", () -> new StairBlock(ARID_SANDSTONE.get().defaultBlockState(), AtmosphericProperties.aridSandstone()));
	public static final DeferredBlock<Block> ARID_SANDSTONE_WALL = BLOCKS.createBlock("arid_sandstone_wall", () -> new WallBlock(AtmosphericProperties.aridSandstone().forceSolidOn()));

	public static final DeferredBlock<Block> SMOOTH_ARID_SANDSTONE = BLOCKS.createBlock("smooth_arid_sandstone", () -> new Block(AtmosphericProperties.smoothAridSandstone()));
	public static final DeferredBlock<Block> SMOOTH_ARID_SANDSTONE_SLAB = BLOCKS.createBlock("smooth_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothAridSandstone()));
	public static final DeferredBlock<Block> SMOOTH_ARID_SANDSTONE_STAIRS = BLOCKS.createBlock("smooth_arid_sandstone_stairs", () -> new StairBlock(SMOOTH_ARID_SANDSTONE.get().defaultBlockState(), AtmosphericProperties.smoothAridSandstone()));
	public static final DeferredBlock<Block> CUT_ARID_SANDSTONE = BLOCKS.createBlock("cut_arid_sandstone", () -> new Block(AtmosphericProperties.aridSandstone()));
	public static final DeferredBlock<Block> CUT_ARID_SANDSTONE_SLAB = BLOCKS.createBlock("cut_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothAridSandstone()));
	public static final DeferredBlock<Block> CHISELED_ARID_SANDSTONE = BLOCKS.createBlock("chiseled_arid_sandstone", () -> new Block(AtmosphericProperties.aridSandstone()));

	public static final DeferredBlock<Block> RED_ARID_SAND = BLOCKS.createBlock("red_arid_sand", () -> new AridSandBlock(16241568, AtmosphericProperties.RED_ARID_SAND));
	public static final DeferredBlock<Block> RED_ARID_SANDSTONE = BLOCKS.createBlock("red_arid_sandstone", () -> new Block(AtmosphericProperties.redAridSandstone()));
	public static final DeferredBlock<Block> RED_ARID_SANDSTONE_SLAB = BLOCKS.createBlock("red_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothRedAridSandstone()));
	public static final DeferredBlock<Block> RED_ARID_SANDSTONE_STAIRS = BLOCKS.createBlock("red_arid_sandstone_stairs", () -> new StairBlock(RED_ARID_SANDSTONE.get().defaultBlockState(), AtmosphericProperties.redAridSandstone()));
	public static final DeferredBlock<Block> RED_ARID_SANDSTONE_WALL = BLOCKS.createBlock("red_arid_sandstone_wall", () -> new WallBlock(AtmosphericProperties.redAridSandstone().forceSolidOn()));

	public static final DeferredBlock<Block> SMOOTH_RED_ARID_SANDSTONE = BLOCKS.createBlock("smooth_red_arid_sandstone", () -> new Block(AtmosphericProperties.smoothRedAridSandstone()));
	public static final DeferredBlock<Block> SMOOTH_RED_ARID_SANDSTONE_SLAB = BLOCKS.createBlock("smooth_red_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothRedAridSandstone()));
	public static final DeferredBlock<Block> SMOOTH_RED_ARID_SANDSTONE_STAIRS = BLOCKS.createBlock("smooth_red_arid_sandstone_stairs", () -> new StairBlock(SMOOTH_RED_ARID_SANDSTONE.get().defaultBlockState(), AtmosphericProperties.smoothRedAridSandstone()));
	public static final DeferredBlock<Block> CUT_RED_ARID_SANDSTONE = BLOCKS.createBlock("cut_red_arid_sandstone", () -> new Block(AtmosphericProperties.redAridSandstone()));
	public static final DeferredBlock<Block> CUT_RED_ARID_SANDSTONE_SLAB = BLOCKS.createBlock("cut_red_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothRedAridSandstone()));
	public static final DeferredBlock<Block> CHISELED_RED_ARID_SANDSTONE = BLOCKS.createBlock("chiseled_red_arid_sandstone", () -> new Block(AtmosphericProperties.redAridSandstone()));

	public static final DeferredBlock<Block> ARID_GLASS = BLOCKS.createBlock("arid_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).sound(AtmosphericSoundTypes.ARID_GLASS)));
	public static final DeferredBlock<Block> ARID_GLASS_PANE = BLOCKS.createBlock("arid_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE).sound(AtmosphericSoundTypes.ARID_GLASS)));

	public static final DeferredBlock<Block> SUSPICIOUS_ARID_SAND = BLOCKS.createBlock("suspicious_arid_sand", () -> new BrushableBlock(ARID_SAND.get(), SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(AtmosphericSoundTypes.SUSPICIOUS_ARID_SAND).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> SUSPICIOUS_RED_ARID_SAND = BLOCKS.createBlock("suspicious_red_arid_sand", () -> new BrushableBlock(RED_ARID_SAND.get(), SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(AtmosphericSoundTypes.SUSPICIOUS_ARID_SAND).pushReaction(PushReaction.DESTROY)));

	public static final DeferredBlock<Block> STRIPPED_YUCCA_LOG = BLOCKS.createBlock("stripped_yucca_log", () -> new RotatedPillarBlock(AtmosphericProperties.YUCCA.log()));
	public static final DeferredBlock<Block> STRIPPED_YUCCA_WOOD = BLOCKS.createBlock("stripped_yucca_wood", () -> new RotatedPillarBlock(AtmosphericProperties.YUCCA.log()));
	public static final DeferredBlock<Block> YUCCA_LOG = BLOCKS.createBlock("yucca_log", () -> new LogBlock(STRIPPED_YUCCA_LOG, AtmosphericProperties.YUCCA.log()));
	public static final DeferredBlock<Block> YUCCA_WOOD = BLOCKS.createBlock("yucca_wood", () -> new LogBlock(STRIPPED_YUCCA_WOOD, AtmosphericProperties.YUCCA.log()));
	public static final DeferredBlock<Block> YUCCA_LEAVES = BLOCKS.createBlock("yucca_leaves", () -> new YuccaLeavesBlock(AtmosphericProperties.YUCCA.leaves()));
	public static final DeferredBlock<Block> YUCCA_SAPLING = BLOCKS.createBlock("yucca_sapling", () -> new YuccaSaplingBlock(AtmosphericTreeGrowers.YUCCA, AtmosphericProperties.YUCCA.sapling().sound(AtmosphericSoundTypes.YUCCA_BRANCH)));
	public static final DeferredBlock<Block> POTTED_YUCCA_SAPLING = BLOCKS.createBlockNoItem("potted_yucca_sapling", () -> new FlowerPotBlock(YUCCA_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> YUCCA_PLANKS = BLOCKS.createBlock("yucca_planks", () -> new Block(AtmosphericProperties.YUCCA.planks()));
	public static final DeferredBlock<Block> YUCCA_STAIRS = BLOCKS.createBlock("yucca_stairs", () -> new StairBlock(YUCCA_PLANKS.get().defaultBlockState(), AtmosphericProperties.YUCCA.planks()));
	public static final DeferredBlock<Block> YUCCA_SLAB = BLOCKS.createBlock("yucca_slab", () -> new SlabBlock(AtmosphericProperties.YUCCA.planks()));
	public static final DeferredBlock<Block> YUCCA_PRESSURE_PLATE = BLOCKS.createBlock("yucca_pressure_plate", () -> new PressurePlateBlock(AtmosphericProperties.YUCCA_BLOCK_SET, AtmosphericProperties.YUCCA.pressurePlate()));
	public static final DeferredBlock<Block> YUCCA_BUTTON = BLOCKS.createBlock("yucca_button", () -> new ButtonBlock(AtmosphericProperties.YUCCA_BLOCK_SET, 30, AtmosphericProperties.YUCCA.button()));
	public static final DeferredBlock<Block> YUCCA_FENCE = BLOCKS.createBlock("yucca_fence", () -> new FenceBlock(AtmosphericProperties.YUCCA.planks()));
	public static final DeferredBlock<Block> YUCCA_FENCE_GATE = BLOCKS.createBlock("yucca_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.YUCCA_WOOD_TYPE, AtmosphericProperties.YUCCA.planks()));
	public static final DeferredBlock<Block> YUCCA_DOOR = BLOCKS.createBlock("yucca_door", () -> new DoorBlock(AtmosphericProperties.YUCCA_BLOCK_SET, AtmosphericProperties.YUCCA.door()));
	public static final DeferredBlock<Block> YUCCA_TRAPDOOR = BLOCKS.createBlock("yucca_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.YUCCA_BLOCK_SET, AtmosphericProperties.YUCCA.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> YUCCA_SIGNS = BLOCKS.createSignBlock("yucca", AtmosphericProperties.YUCCA_WOOD_TYPE, AtmosphericProperties.YUCCA.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> YUCCA_HANGING_SIGNS = BLOCKS.createHangingSignBlock("yucca", AtmosphericProperties.YUCCA_WOOD_TYPE, AtmosphericProperties.YUCCA.hangingSign());

	public static final DeferredBlock<Block> YUCCA_BOARDS = BLOCKS.createBlock("yucca_boards", () -> new RotatedPillarBlock(AtmosphericProperties.YUCCA.planks()));
	public static final DeferredBlock<Block> YUCCA_BOOKSHELF = BLOCKS.createBlock("yucca_bookshelf", () -> new Block(AtmosphericProperties.YUCCA.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_YUCCA_BOOKSHELF = BLOCKS.createBlock("chiseled_yucca_bookshelf", () -> new ChiseledYuccaBookShelfBlock(AtmosphericProperties.YUCCA.chiseledBookshelf()));
	public static final DeferredBlock<Block> YUCCA_LADDER = BLOCKS.createBlock("yucca_ladder", () -> new LadderBlock(AtmosphericProperties.YUCCA.ladder()));
	public static final DeferredBlock<Block> YUCCA_BEEHIVE = BLOCKS.createBlock("yucca_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.YUCCA.beehive()));
	public static final DeferredBlock<Block> YUCCA_LEAF_PILE = BLOCKS.createBlock("yucca_leaf_pile", () -> new YuccaLeafPileBlock(AtmosphericProperties.YUCCA.leafPile()));
	public static final DeferredBlock<BlueprintChestBlock> YUCCA_CHEST = BLOCKS.createChestBlock("yucca", AtmosphericProperties.YUCCA.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_YUCCA_CHEST = BLOCKS.createTrappedChestBlock("yucca", AtmosphericProperties.YUCCA.chest());

	public static final DeferredBlock<Block> YUCCA_BRANCH = BLOCKS.createBlock("yucca_branch", () -> new YuccaBranchBlock(Block.Properties.ofFullCopy(Blocks.MELON_STEM).sound(AtmosphericSoundTypes.YUCCA_BRANCH).randomTicks()));
	public static final DeferredBlock<Block> YUCCA_BUNDLE = BLOCKS.createBlock("yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.ofFullCopy(Blocks.MELON).sound(AtmosphericSoundTypes.YUCCA_FRUIT).randomTicks()));
	public static final DeferredBlock<Block> ROASTED_YUCCA_BUNDLE = BLOCKS.createBlock("roasted_yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.ofFullCopy(Blocks.MELON).sound(AtmosphericSoundTypes.YUCCA_FRUIT).randomTicks()));

	public static final DeferredBlock<Block> YUCCA_GATEAU = BLOCKS.createBlockNoItem("yucca_gateau", () -> new YuccaGateauBlock(AtmosphericProperties.YUCCA_GATEAU));
	public static final DeferredBlock<Block> YUCCA_FLOWER = BLOCKS.createBlock("yucca_flower", () -> new YuccaFlowerBlock(AtmosphericMobEffects.PERSISTENCE, 15, AtmosphericProperties.YUCCA_FLOWER));
	public static final DeferredBlock<Block> POTTED_YUCCA_FLOWER = BLOCKS.createBlockNoItem("potted_yucca_flower", () -> new FlowerPotBlock(YUCCA_FLOWER.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> TALL_YUCCA_FLOWER = BLOCKS.createBlock("tall_yucca_flower", () -> new YuccaFlowerDoubleBlock(AtmosphericProperties.YUCCA_FLOWER));

	public static final DeferredBlock<Block> CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> WHITE_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("white_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.WHITE_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> ORANGE_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("orange_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.ORANGE_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> MAGENTA_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("magenta_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.MAGENTA_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> LIGHT_BLUE_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("light_blue_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.LIGHT_BLUE_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> YELLOW_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("yellow_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.YELLOW_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> LIME_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("lime_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.LIME_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> PINK_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("pink_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.PINK_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> GRAY_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("gray_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.GRAY_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> LIGHT_GRAY_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("light_gray_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.LIGHT_GRAY_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> CYAN_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("cyan_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.CYAN_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> PURPLE_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("purple_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.PURPLE_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> BLUE_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("blue_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.BLUE_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> BROWN_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("brown_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.BROWN_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> GREEN_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("green_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.GREEN_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> RED_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("red_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.RED_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));
	public static final DeferredBlock<Block> BLACK_CANDLE_YUCCA_GATEAU = BLOCKS.createBlockNoItem("black_candle_yucca_gateau", () -> new CandleGateauBlock(Blocks.BLACK_CANDLE, AtmosphericProperties.CANDLE_YUCCA_GATEAU));

	public static final DeferredBlock<Block> GILIA = BLOCKS.createBlock("gilia", () -> new DesertFlowerBlock(MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()));
	public static final DeferredBlock<Block> POTTED_GILIA = BLOCKS.createBlockNoItem("potted_gilia", () -> new FlowerPotBlock(GILIA.get(), PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> ARID_SPROUTS = BLOCKS.createBlock("arid_sprouts", () -> new AridSproutsBlock(AtmosphericProperties.ARID_SPROUTS));
	public static final DeferredBlock<Block> ALOE_VERA = BLOCKS.createBlockNoItem("aloe_vera", () -> new AloeVeraBlock(AtmosphericProperties.ALOE_VERA));
	public static final DeferredBlock<Block> TALL_ALOE_VERA = BLOCKS.createBlockNoItem("tall_aloe_vera", () -> new AloeVeraTallBlock(AtmosphericProperties.ALOE_VERA));
	public static final DeferredBlock<Block> ALOE_BUNDLE = BLOCKS.createBlock("aloe_bundle", () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.DRIED_KELP_BLOCK).sound(AtmosphericSoundTypes.ALOE_VERA)));
	public static final DeferredBlock<Block> ALOE_GEL_BLOCK = BLOCKS.createBlock("aloe_gel_block", () -> new AloeGelBlock(Block.Properties.ofFullCopy(Blocks.SLIME_BLOCK).sound(AtmosphericSoundTypes.ALOE_GEL).isSuffocating(PropertyUtil::never)));
	public static final DeferredBlock<Block> POTTED_ALOE_VERA = BLOCKS.createBlockNoItem("potted_aloe_vera", () -> new FlowerPotBlock(ALOE_VERA.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> BARREL_CACTUS = BLOCKS.createBlockNoItem("barrel_cactus", () -> new BarrelCactusBlock(Block.Properties.ofFullCopy(Blocks.CACTUS).sound(AtmosphericSoundTypes.BARREL_CACTUS)));
	public static final DeferredBlock<Block> POTTED_BARREL_CACTUS = BLOCKS.createBlockNoItem("potted_barrel_cactus", () -> new FlowerPotBlock(BARREL_CACTUS.get(), Block.Properties.ofFullCopy(Blocks.POTTED_CACTUS)));
	public static final DeferredBlock<Block> SNOWY_BARREL_CACTUS = BLOCKS.createBlockNoItem("snowy_barrel_cactus", () -> new BarrelCactusBlock(Block.Properties.ofFullCopy(Blocks.CACTUS).sound(AtmosphericSoundTypes.BARREL_CACTUS)));
	public static final DeferredBlock<Block> POTTED_SNOWY_BARREL_CACTUS = BLOCKS.createBlockNoItem("potted_snowy_barrel_cactus", () -> new SnowyFlowerPotBlock(SNOWY_BARREL_CACTUS.get(), () -> BARREL_CACTUS.get(), Block.Properties.ofFullCopy(Blocks.POTTED_CACTUS)));
	public static final DeferredBlock<Block> SNOWY_CACTUS = BLOCKS.createBlockNoItem("snowy_cactus", () -> new SnowyCactusBlock(Block.Properties.ofFullCopy(Blocks.CACTUS)));
	public static final DeferredBlock<Block> POTTED_SNOWY_CACTUS = BLOCKS.createBlockNoItem("potted_snowy_cactus", () -> new SnowyFlowerPotBlock(SNOWY_CACTUS.get(), () -> Blocks.CACTUS, Block.Properties.ofFullCopy(Blocks.POTTED_CACTUS)));

	public static final DeferredBlock<Block> YUCCA_CASK = BLOCKS.createBlock("yucca_cask", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));
	public static final DeferredBlock<Block> ROASTED_YUCCA_CASK = BLOCKS.createBlock("roasted_yucca_cask", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));
	public static final DeferredBlock<Block> BARREL_CACTUS_BATCH = BLOCKS.createBlock("barrel_cactus_batch", () -> new RotatedPillarBlock(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F).sound(SoundType.WOOD).ignitedByLava()));

	/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final DeferredBlock<Block> STRIPPED_ASPEN_LOG = BLOCKS.createBlock("stripped_aspen_log", () -> new RotatedPillarBlock(AtmosphericProperties.ASPEN.log()));
	public static final DeferredBlock<Block> STRIPPED_ASPEN_WOOD = BLOCKS.createBlock("stripped_aspen_wood", () -> new RotatedPillarBlock(AtmosphericProperties.ASPEN.log()));
	public static final DeferredBlock<Block> ASPEN_LOG = BLOCKS.createBlock("aspen_log", () -> new LogBlock(STRIPPED_ASPEN_LOG, AtmosphericProperties.ASPEN.log()));
	public static final DeferredBlock<Block> ASPEN_WOOD = BLOCKS.createBlock("aspen_wood", () -> new LogBlock(STRIPPED_ASPEN_WOOD, AtmosphericProperties.ASPEN.log()));
	public static final DeferredBlock<Block> WATCHFUL_ASPEN_LOG = BLOCKS.createBlock("watchful_aspen_log", () -> new LogBlock(ASPEN_LOG, AtmosphericProperties.ASPEN.log()));
	public static final DeferredBlock<Block> WATCHFUL_ASPEN_WOOD = BLOCKS.createBlock("watchful_aspen_wood", () -> new LogBlock(ASPEN_WOOD, AtmosphericProperties.ASPEN.log()));
	public static final DeferredBlock<Block> ASPEN_LEAVES = BLOCKS.createBlock("aspen_leaves", () -> new LeavesBlock(AtmosphericProperties.ASPEN.leaves()));
	public static final DeferredBlock<Block> ASPEN_SAPLING = BLOCKS.createBlock("aspen_sapling", () -> new SaplingBlock(AtmosphericTreeGrowers.ASPEN, AtmosphericProperties.ASPEN.sapling()));
	public static final DeferredBlock<Block> POTTED_ASPEN_SAPLING = BLOCKS.createBlockNoItem("potted_aspen_sapling", () -> new FlowerPotBlock(ASPEN_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> ASPEN_PLANKS = BLOCKS.createBlock("aspen_planks", () -> new Block(AtmosphericProperties.ASPEN.planks()));
	public static final DeferredBlock<Block> ASPEN_STAIRS = BLOCKS.createBlock("aspen_stairs", () -> new StairBlock(ASPEN_PLANKS.get().defaultBlockState(), AtmosphericProperties.ASPEN.planks()));
	public static final DeferredBlock<Block> ASPEN_SLAB = BLOCKS.createBlock("aspen_slab", () -> new SlabBlock(AtmosphericProperties.ASPEN.planks()));
	public static final DeferredBlock<Block> ASPEN_PRESSURE_PLATE = BLOCKS.createBlock("aspen_pressure_plate", () -> new PressurePlateBlock(AtmosphericProperties.ASPEN_BLOCK_SET, AtmosphericProperties.ASPEN.pressurePlate()));
	public static final DeferredBlock<Block> ASPEN_BUTTON = BLOCKS.createBlock("aspen_button", () -> new ButtonBlock(AtmosphericProperties.ASPEN_BLOCK_SET, 30, AtmosphericProperties.ASPEN.button()));
	public static final DeferredBlock<Block> ASPEN_FENCE = BLOCKS.createBlock("aspen_fence", () -> new FenceBlock(AtmosphericProperties.ASPEN.planks()));
	public static final DeferredBlock<Block> ASPEN_FENCE_GATE = BLOCKS.createBlock("aspen_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.ASPEN_WOOD_TYPE, AtmosphericProperties.ASPEN.planks()));
	public static final DeferredBlock<Block> ASPEN_DOOR = BLOCKS.createBlock("aspen_door", () -> new DoorBlock(AtmosphericProperties.ASPEN_BLOCK_SET, AtmosphericProperties.ASPEN.door()));
	public static final DeferredBlock<Block> ASPEN_TRAPDOOR = BLOCKS.createBlock("aspen_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.ASPEN_BLOCK_SET, AtmosphericProperties.ASPEN.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> ASPEN_SIGNS = BLOCKS.createSignBlock("aspen", AtmosphericProperties.ASPEN_WOOD_TYPE, AtmosphericProperties.ASPEN.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> ASPEN_HANGING_SIGNS = BLOCKS.createHangingSignBlock("aspen", AtmosphericProperties.ASPEN_WOOD_TYPE, AtmosphericProperties.ASPEN.hangingSign());

	public static final DeferredBlock<Block> ASPEN_BOARDS = BLOCKS.createBlock("aspen_boards", () -> new RotatedPillarBlock(AtmosphericProperties.ASPEN.planks()));
	public static final DeferredBlock<Block> ASPEN_BOOKSHELF = BLOCKS.createBlock("aspen_bookshelf", () -> new Block(AtmosphericProperties.ASPEN.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_ASPEN_BOOKSHELF = BLOCKS.createBlock("chiseled_aspen_bookshelf", () -> new ChiseledAspenBookShelfBlock(AtmosphericProperties.ASPEN.chiseledBookshelf()));
	public static final DeferredBlock<Block> ASPEN_LADDER = BLOCKS.createBlock("aspen_ladder", () -> new LadderBlock(AtmosphericProperties.ASPEN.ladder()));
	public static final DeferredBlock<Block> ASPEN_BEEHIVE = BLOCKS.createBlock("aspen_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.ASPEN.beehive()));
	public static final DeferredBlock<Block> ASPEN_LEAF_PILE = BLOCKS.createBlock("aspen_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.ASPEN.leafPile()));
	public static final DeferredBlock<BlueprintChestBlock> ASPEN_CHEST = BLOCKS.createChestBlock("aspen", AtmosphericProperties.ASPEN.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_ASPEN_CHEST = BLOCKS.createTrappedChestBlock("aspen", AtmosphericProperties.ASPEN.chest());

	public static final DeferredBlock<Block> GREEN_ASPEN_LEAVES = BLOCKS.createBlock("green_aspen_leaves", () -> new LeavesBlock(AtmosphericProperties.GREEN_ASPEN.leaves()));
	public static final DeferredBlock<Block> GREEN_ASPEN_SAPLING = BLOCKS.createBlock("green_aspen_sapling", () -> new SaplingBlock(AtmosphericTreeGrowers.GREEN_ASPEN, AtmosphericProperties.GREEN_ASPEN.sapling()));
	public static final DeferredBlock<Block> POTTED_GREEN_ASPEN_SAPLING = BLOCKS.createBlockNoItem("potted_green_aspen_sapling", () -> new FlowerPotBlock(GREEN_ASPEN_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> GREEN_ASPEN_LEAF_PILE = BLOCKS.createBlock("green_aspen_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.GREEN_ASPEN.leafPile()));

	public static final DeferredBlock<Block> AGAVE = BLOCKS.createBlock("agave", () -> new AgaveBlock(AtmosphericProperties.AGAVE));
	public static final DeferredBlock<Block> POTTED_AGAVE = BLOCKS.createBlockNoItem("potted_agave", () -> new FlowerPotBlock(AGAVE.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> GOLDEN_GROWTHS = BLOCKS.createBlock("golden_growths", () -> new GoldenGrowthsBlock(AtmosphericProperties.GOLDEN_GROWTHS));
	public static final DeferredBlock<Block> POTTED_GOLDEN_GROWTHS = BLOCKS.createBlockNoItem("potted_golden_growths", () -> new FlowerPotBlock(GOLDEN_GROWTHS.get(), PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> CRUSTOSE = BLOCKS.createBlock("crustose", () -> new CrustoseBlock(AtmosphericProperties.CRUSTOSE));
	public static final DeferredBlock<Block> CRUSTOSE_PATH = BLOCKS.createBlock("crustose_path", () -> new DirtPathBlock(AtmosphericProperties.CRUSTOSE_PATH));
	public static final DeferredBlock<Block> CRUSTOSE_LOG = BLOCKS.createBlock("crustose_log", () -> new CrustoseLogBlock(ASPEN_LOG::get, AtmosphericProperties.ASPEN.log().randomTicks()));
	public static final DeferredBlock<Block> CRUSTOSE_WOOD = BLOCKS.createBlock("crustose_wood", () -> new CrustoseLogBlock(ASPEN_WOOD::get, AtmosphericProperties.ASPEN.log().randomTicks()));

	/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final DeferredBlock<Block> STRIPPED_LAUREL_LOG = BLOCKS.createBlock("stripped_laurel_log", () -> new RotatedPillarBlock(AtmosphericProperties.LAUREL.log()));
	public static final DeferredBlock<Block> STRIPPED_LAUREL_WOOD = BLOCKS.createBlock("stripped_laurel_wood", () -> new RotatedPillarBlock(AtmosphericProperties.LAUREL.log()));
	public static final DeferredBlock<Block> LAUREL_LOG = BLOCKS.createBlock("laurel_log", () -> new LogBlock(STRIPPED_LAUREL_LOG, AtmosphericProperties.LAUREL.log()));
	public static final DeferredBlock<Block> LAUREL_WOOD = BLOCKS.createBlock("laurel_wood", () -> new LogBlock(STRIPPED_LAUREL_WOOD, AtmosphericProperties.LAUREL.log()));
	public static final DeferredBlock<Block> LAUREL_LEAVES = BLOCKS.createBlock("laurel_leaves", () -> new LeavesBlock(AtmosphericProperties.LAUREL.leaves()));
	public static final DeferredBlock<Block> LAUREL_SAPLING = BLOCKS.createBlock("laurel_sapling", () -> new LaurelSaplingBlock(AtmosphericTreeGrowers.LAUREL, AtmosphericTreeGrowers.LAUREL_ORANGES, AtmosphericTreeGrowers.LAUREL_BLOOD_ORANGES, AtmosphericProperties.LAUREL.sapling()));
	public static final DeferredBlock<Block> POTTED_LAUREL_SAPLING = BLOCKS.createBlockNoItem("potted_laurel_sapling", () -> new FlowerPotBlock(LAUREL_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> LAUREL_PLANKS = BLOCKS.createBlock("laurel_planks", () -> new Block(AtmosphericProperties.LAUREL.planks()));
	public static final DeferredBlock<Block> LAUREL_STAIRS = BLOCKS.createBlock("laurel_stairs", () -> new StairBlock(LAUREL_PLANKS.get().defaultBlockState(), AtmosphericProperties.LAUREL.planks()));
	public static final DeferredBlock<Block> LAUREL_SLAB = BLOCKS.createBlock("laurel_slab", () -> new SlabBlock(AtmosphericProperties.LAUREL.planks()));
	public static final DeferredBlock<Block> LAUREL_PRESSURE_PLATE = BLOCKS.createBlock("laurel_pressure_plate", () -> new PressurePlateBlock(AtmosphericProperties.LAUREL_BLOCK_SET, AtmosphericProperties.LAUREL.pressurePlate()));
	public static final DeferredBlock<Block> LAUREL_BUTTON = BLOCKS.createBlock("laurel_button", () -> new ButtonBlock(AtmosphericProperties.LAUREL_BLOCK_SET, 30, AtmosphericProperties.LAUREL.button()));
	public static final DeferredBlock<Block> LAUREL_FENCE = BLOCKS.createBlock("laurel_fence", () -> new FenceBlock(AtmosphericProperties.LAUREL.planks()));
	public static final DeferredBlock<Block> LAUREL_FENCE_GATE = BLOCKS.createBlock("laurel_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.LAUREL_WOOD_TYPE, AtmosphericProperties.LAUREL.planks()));
	public static final DeferredBlock<Block> LAUREL_DOOR = BLOCKS.createBlock("laurel_door", () -> new DoorBlock(AtmosphericProperties.LAUREL_BLOCK_SET, AtmosphericProperties.LAUREL.door()));
	public static final DeferredBlock<Block> LAUREL_TRAPDOOR = BLOCKS.createBlock("laurel_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.LAUREL_BLOCK_SET, AtmosphericProperties.LAUREL.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> LAUREL_SIGNS = BLOCKS.createSignBlock("laurel", AtmosphericProperties.LAUREL_WOOD_TYPE, AtmosphericProperties.LAUREL.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> LAUREL_HANGING_SIGNS = BLOCKS.createHangingSignBlock("laurel", AtmosphericProperties.LAUREL_WOOD_TYPE, AtmosphericProperties.LAUREL.hangingSign());

	public static final DeferredBlock<Block> LAUREL_BOARDS = BLOCKS.createBlock("laurel_boards", () -> new RotatedPillarBlock(AtmosphericProperties.LAUREL.planks()));
	public static final DeferredBlock<Block> LAUREL_BOOKSHELF = BLOCKS.createBlock("laurel_bookshelf", () -> new Block(AtmosphericProperties.LAUREL.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_LAUREL_BOOKSHELF = BLOCKS.createBlock("chiseled_laurel_bookshelf", () -> new BlueprintChiseledBookShelfBlock(AtmosphericProperties.LAUREL.chiseledBookshelf()));
	public static final DeferredBlock<Block> LAUREL_LADDER = BLOCKS.createBlock("laurel_ladder", () -> new LadderBlock(AtmosphericProperties.LAUREL.ladder()));
	public static final DeferredBlock<Block> LAUREL_BEEHIVE = BLOCKS.createBlock("laurel_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.LAUREL.beehive()));
	public static final DeferredBlock<Block> LAUREL_LEAF_PILE = BLOCKS.createBlock("laurel_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.LAUREL.leafPile()));
	public static final DeferredBlock<BlueprintChestBlock> LAUREL_CHEST = BLOCKS.createChestBlock("laurel", AtmosphericProperties.LAUREL.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_LAUREL_CHEST = BLOCKS.createTrappedChestBlock("laurel", AtmosphericProperties.LAUREL.chest());

	public static final DeferredBlock<Block> DRY_LAUREL_LEAVES = BLOCKS.createBlock("dry_laurel_leaves", () -> new LeavesBlock(AtmosphericProperties.DRY_LAUREL.leaves()));
	public static final DeferredBlock<Block> DRY_LAUREL_SAPLING = BLOCKS.createBlock("dry_laurel_sapling", () -> new LaurelSaplingBlock(AtmosphericTreeGrowers.DRY_LAUREL, AtmosphericTreeGrowers.DRY_LAUREL_ORANGES, AtmosphericTreeGrowers.DRY_LAUREL_BLOOD_ORANGES, AtmosphericProperties.DRY_LAUREL.sapling()));
	public static final DeferredBlock<Block> POTTED_DRY_LAUREL_SAPLING = BLOCKS.createBlockNoItem("potted_dry_laurel_sapling", () -> new FlowerPotBlock(DRY_LAUREL_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> DRY_LAUREL_LEAF_PILE = BLOCKS.createBlock("dry_laurel_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.DRY_LAUREL.leafPile()));

	public static final DeferredBlock<Block> ORANGE = BLOCKS.createBlockNoItem("orange", () -> new OrangeBlock(AtmosphericProperties.ORANGE));
	public static final DeferredBlock<Block> BLOOD_ORANGE = BLOCKS.createBlockNoItem("blood_orange", () -> new OrangeBlock(AtmosphericProperties.ORANGE));

	public static final DeferredBlock<Block> ORANGE_CRATE = BLOCKS.createBlock("orange_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));
	public static final DeferredBlock<Block> BLOOD_ORANGE_CRATE = BLOCKS.createBlock("blood_orange_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));

	/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final DeferredBlock<Block> STRIPPED_KOUSA_LOG = BLOCKS.createBlock("stripped_kousa_log", () -> new RotatedPillarBlock(AtmosphericProperties.KOUSA.log()));
	public static final DeferredBlock<Block> STRIPPED_KOUSA_WOOD = BLOCKS.createBlock("stripped_kousa_wood", () -> new RotatedPillarBlock(AtmosphericProperties.KOUSA.log()));
	public static final DeferredBlock<Block> KOUSA_LOG = BLOCKS.createBlock("kousa_log", () -> new LogBlock(STRIPPED_KOUSA_LOG, AtmosphericProperties.KOUSA.log()));
	public static final DeferredBlock<Block> KOUSA_WOOD = BLOCKS.createBlock("kousa_wood", () -> new LogBlock(STRIPPED_KOUSA_WOOD, AtmosphericProperties.KOUSA.log()));
	public static final DeferredBlock<Block> KOUSA_LEAVES = BLOCKS.createBlock("kousa_leaves", () -> new LeavesBlock(AtmosphericProperties.KOUSA.leaves()));
	public static final DeferredBlock<Block> KOUSA_SAPLING = BLOCKS.createBlock("kousa_sapling", () -> new SaplingBlock(AtmosphericTreeGrowers.KOUSA, AtmosphericProperties.KOUSA.sapling()));
	public static final DeferredBlock<Block> POTTED_KOUSA_SAPLING = BLOCKS.createBlockNoItem("potted_kousa_sapling", () -> new FlowerPotBlock(KOUSA_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> KOUSA_PLANKS = BLOCKS.createBlock("kousa_planks", () -> new Block(AtmosphericProperties.KOUSA.planks()));
	public static final DeferredBlock<Block> KOUSA_STAIRS = BLOCKS.createBlock("kousa_stairs", () -> new StairBlock(KOUSA_PLANKS.get().defaultBlockState(), AtmosphericProperties.KOUSA.planks()));
	public static final DeferredBlock<Block> KOUSA_SLAB = BLOCKS.createBlock("kousa_slab", () -> new SlabBlock(AtmosphericProperties.KOUSA.planks()));
	public static final DeferredBlock<Block> KOUSA_PRESSURE_PLATE = BLOCKS.createBlock("kousa_pressure_plate", () -> new PressurePlateBlock(AtmosphericProperties.KOUSA_BLOCK_SET, AtmosphericProperties.KOUSA.pressurePlate()));
	public static final DeferredBlock<Block> KOUSA_BUTTON = BLOCKS.createBlock("kousa_button", () -> new ButtonBlock(AtmosphericProperties.KOUSA_BLOCK_SET, 30, AtmosphericProperties.KOUSA.button()));
	public static final DeferredBlock<Block> KOUSA_FENCE = BLOCKS.createBlock("kousa_fence", () -> new FenceBlock(AtmosphericProperties.KOUSA.planks()));
	public static final DeferredBlock<Block> KOUSA_FENCE_GATE = BLOCKS.createBlock("kousa_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.KOUSA_WOOD_TYPE, AtmosphericProperties.KOUSA.planks()));
	public static final DeferredBlock<Block> KOUSA_DOOR = BLOCKS.createBlock("kousa_door", () -> new DoorBlock(AtmosphericProperties.KOUSA_BLOCK_SET, AtmosphericProperties.KOUSA.door()));
	public static final DeferredBlock<Block> KOUSA_TRAPDOOR = BLOCKS.createBlock("kousa_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.KOUSA_BLOCK_SET, AtmosphericProperties.KOUSA.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> KOUSA_SIGNS = BLOCKS.createSignBlock("kousa", AtmosphericProperties.KOUSA_WOOD_TYPE, AtmosphericProperties.KOUSA.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> KOUSA_HANGING_SIGNS = BLOCKS.createHangingSignBlock("kousa", AtmosphericProperties.KOUSA_WOOD_TYPE, AtmosphericProperties.KOUSA.hangingSign());

	public static final DeferredBlock<Block> KOUSA_BOARDS = BLOCKS.createBlock("kousa_boards", () -> new RotatedPillarBlock(AtmosphericProperties.KOUSA.planks()));
	public static final DeferredBlock<Block> KOUSA_BOOKSHELF = BLOCKS.createBlock("kousa_bookshelf", () -> new Block(AtmosphericProperties.KOUSA.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_KOUSA_BOOKSHELF = BLOCKS.createBlock("chiseled_kousa_bookshelf", () -> new ChiseledKousaBookShelfBlock(AtmosphericProperties.KOUSA.chiseledBookshelf()));
	public static final DeferredBlock<Block> KOUSA_LADDER = BLOCKS.createBlock("kousa_ladder", () -> new LadderBlock(AtmosphericProperties.KOUSA.ladder()));
	public static final DeferredBlock<Block> KOUSA_BEEHIVE = BLOCKS.createBlock("kousa_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.KOUSA.beehive()));
	public static final DeferredBlock<Block> KOUSA_LEAF_PILE = BLOCKS.createBlock("kousa_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.KOUSA.leafPile()));
	public static final DeferredBlock<BlueprintChestBlock> KOUSA_CHEST = BLOCKS.createChestBlock("kousa", AtmosphericProperties.KOUSA.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_KOUSA_CHEST = BLOCKS.createTrappedChestBlock("kousa", AtmosphericProperties.KOUSA.chest());

	public static final DeferredBlock<Block> SNOWY_BAMBOO_SAPLING = BLOCKS.createBlockNoItem("snowy_bamboo_sapling", () -> new SnowyBambooSaplingBlock(Properties.of().randomTicks().instabreak().noCollission().strength(1.0F).sound(SoundType.BAMBOO_SAPLING).offsetType(OffsetType.XZ)));
	public static final DeferredBlock<Block> SNOWY_BAMBOO = BLOCKS.createBlockNoItem("snowy_bamboo", () -> new SnowyBambooBlock(Properties.of().mapColor(MapColor.PLANT).randomTicks().instabreak().strength(1.0F).sound(SoundType.BAMBOO).noOcclusion().dynamicShape().offsetType(OffsetType.XZ)));
	public static final DeferredBlock<Block> POTTED_SNOWY_BAMBOO = BLOCKS.createBlockNoItem("potted_snowy_bamboo", () -> new SnowyFlowerPotBlock(SNOWY_BAMBOO.get(), () -> Blocks.BAMBOO, PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> HANGING_CURRANT = BLOCKS.createBlock("hanging_currant", () -> new HangingCurrantBlock(Block.Properties.ofFullCopy(Blocks.MELON_STEM).sound(AtmosphericSoundTypes.CURRANT_LEAVES).randomTicks()));
	public static final DeferredBlock<Block> CURRANT_STALK = BLOCKS.createBlock("currant_stalk", () -> new CurrantStalkBlock(AtmosphericProperties.CURRANT.log()));
	public static final DeferredBlock<Block> CURRANT_STALK_BUNDLE = BLOCKS.createBlock("currant_stalk_bundle", () -> new CurrantStalkBundleBlock(AtmosphericProperties.CURRANT.log()));
	public static final DeferredBlock<Block> CURRANT_LEAVES = BLOCKS.createBlock("currant_leaves", () -> new CurrantLeavesBlock(AtmosphericProperties.CURRANT.leaves()));
	public static final DeferredBlock<Block> CURRANT_SEEDLING = BLOCKS.createBlock("currant_seedling", () -> new CurrantSeedlingBlock(AtmosphericProperties.CURRANT.sapling()));
	public static final DeferredBlock<Block> POTTED_CURRANT_SEEDLING = BLOCKS.createBlockNoItem("potted_currant_seedling", () -> new FlowerPotBlock(CURRANT_SEEDLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> CURRANT_LEAF_PILE = BLOCKS.createBlock("currant_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.CURRANT.leafPile()));
	public static final DeferredBlock<Block> CURRANT_CRATE = BLOCKS.createBlock("currant_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));

	/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final DeferredBlock<Block> GRIMWEB = BLOCKS.createBlock("grimweb", () -> new WebBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBWEB)));

	public static final DeferredBlock<Block> STRIPPED_GRIMWOOD_LOG = BLOCKS.createBlock("stripped_grimwood_log", () -> new RotatedPillarBlock(AtmosphericProperties.GRIMWOOD.log()));
	public static final DeferredBlock<Block> STRIPPED_GRIMWOOD = BLOCKS.createBlock("stripped_grimwood", () -> new RotatedPillarBlock(AtmosphericProperties.GRIMWOOD.log()));
	public static final DeferredBlock<Block> GRIMWOOD_LOG = BLOCKS.createBlock("grimwood_log", () -> new LogBlock(STRIPPED_GRIMWOOD_LOG, AtmosphericProperties.GRIMWOOD.log()));
	public static final DeferredBlock<Block> GRIMWOOD = BLOCKS.createBlock("grimwood", () -> new LogBlock(STRIPPED_GRIMWOOD, AtmosphericProperties.GRIMWOOD.log()));
	public static final DeferredBlock<Block> GRIMWOOD_LEAVES = BLOCKS.createBlock("grimwood_leaves", () -> new LeavesBlock(AtmosphericProperties.GRIMWOOD.leaves()));
	public static final DeferredBlock<Block> GRIMWOOD_SAPLING = BLOCKS.createBlock("grimwood_sapling", () -> new SaplingBlock(AtmosphericTreeGrowers.GRIMWOOD, AtmosphericProperties.GRIMWOOD.sapling()));
	public static final DeferredBlock<Block> POTTED_GRIMWOOD_SAPLING = BLOCKS.createBlockNoItem("potted_grimwood_sapling", () -> new FlowerPotBlock(GRIMWOOD_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> GRIMWOOD_PLANKS = BLOCKS.createBlock("grimwood_planks", () -> new Block(AtmosphericProperties.GRIMWOOD.planks()));
	public static final DeferredBlock<Block> GRIMWOOD_STAIRS = BLOCKS.createBlock("grimwood_stairs", () -> new StairBlock(GRIMWOOD_PLANKS.get().defaultBlockState(), AtmosphericProperties.GRIMWOOD.planks()));
	public static final DeferredBlock<Block> GRIMWOOD_SLAB = BLOCKS.createBlock("grimwood_slab", () -> new SlabBlock(AtmosphericProperties.GRIMWOOD.planks()));
	public static final DeferredBlock<Block> GRIMWOOD_PRESSURE_PLATE = BLOCKS.createBlock("grimwood_pressure_plate", () -> new PressurePlateBlock(AtmosphericProperties.GRIMWOOD_BLOCK_SET, AtmosphericProperties.GRIMWOOD.pressurePlate()));
	public static final DeferredBlock<Block> GRIMWOOD_BUTTON = BLOCKS.createBlock("grimwood_button", () -> new ButtonBlock(AtmosphericProperties.GRIMWOOD_BLOCK_SET, 30, AtmosphericProperties.GRIMWOOD.button()));
	public static final DeferredBlock<Block> GRIMWOOD_FENCE = BLOCKS.createBlock("grimwood_fence", () -> new FenceBlock(AtmosphericProperties.GRIMWOOD.planks()));
	public static final DeferredBlock<Block> GRIMWOOD_FENCE_GATE = BLOCKS.createBlock("grimwood_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.GRIMWOOD_WOOD_TYPE, AtmosphericProperties.GRIMWOOD.planks()));
	public static final DeferredBlock<Block> GRIMWOOD_DOOR = BLOCKS.createBlock("grimwood_door", () -> new DoorBlock(AtmosphericProperties.GRIMWOOD_BLOCK_SET, AtmosphericProperties.GRIMWOOD.door()));
	public static final DeferredBlock<Block> GRIMWOOD_TRAPDOOR = BLOCKS.createBlock("grimwood_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.GRIMWOOD_BLOCK_SET, AtmosphericProperties.GRIMWOOD.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> GRIMWOOD_SIGNS = BLOCKS.createSignBlock("grimwood", AtmosphericProperties.GRIMWOOD_WOOD_TYPE, AtmosphericProperties.GRIMWOOD.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> GRIMWOOD_HANGING_SIGNS = BLOCKS.createHangingSignBlock("grimwood", AtmosphericProperties.GRIMWOOD_WOOD_TYPE, AtmosphericProperties.GRIMWOOD.hangingSign());

	public static final DeferredBlock<Block> GRIMWOOD_BOARDS = BLOCKS.createBlock("grimwood_boards", () -> new RotatedPillarBlock(AtmosphericProperties.GRIMWOOD.planks()));
	public static final DeferredBlock<Block> GRIMWOOD_BOOKSHELF = BLOCKS.createBlock("grimwood_bookshelf", () -> new Block(AtmosphericProperties.GRIMWOOD.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_GRIMWOOD_BOOKSHELF = BLOCKS.createBlock("chiseled_grimwood_bookshelf", () -> new ChiseledGrimwoodBookShelfBlock(AtmosphericProperties.GRIMWOOD.chiseledBookshelf()));
	public static final DeferredBlock<Block> GRIMWOOD_LADDER = BLOCKS.createBlock("grimwood_ladder", () -> new LadderBlock(AtmosphericProperties.GRIMWOOD.ladder()));
	public static final DeferredBlock<Block> GRIMWOOD_BEEHIVE = BLOCKS.createBlock("grimwood_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.GRIMWOOD.beehive()));
	public static final DeferredBlock<Block> GRIMWOOD_LEAF_PILE = BLOCKS.createBlock("grimwood_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.GRIMWOOD.leafPile()));
	public static final DeferredBlock<BlueprintChestBlock> GRIMWOOD_CHEST = BLOCKS.createChestBlock("grimwood", AtmosphericProperties.GRIMWOOD.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_GRIMWOOD_CHEST = BLOCKS.createTrappedChestBlock("grimwood", AtmosphericProperties.GRIMWOOD.chest());

	/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final DeferredBlock<Block> CARMINE_BLOCK = BLOCKS.createBlock("carmine_block", () -> new CarmineBlock(AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CARMINE_SHINGLES = BLOCKS.createBlock("carmine_shingles", () -> new Block(AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CARMINE_SHINGLE_STAIRS = BLOCKS.createBlock("carmine_shingle_stairs", () -> new StairBlock(CARMINE_BLOCK.get().defaultBlockState(), AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CARMINE_SHINGLE_SLAB = BLOCKS.createBlock("carmine_shingle_slab", () -> new SlabBlock(AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CARMINE_SHINGLE_WALL = BLOCKS.createBlock("carmine_shingle_wall", () -> new WallBlock(AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CHISELED_CARMINE_SHINGLES = BLOCKS.createBlock("chiseled_carmine_shingles", () -> new Block(AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CARMINE_PAVEMENT = BLOCKS.createBlock("carmine_pavement", () -> new Block(AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CARMINE_PAVEMENT_STAIRS = BLOCKS.createBlock("carmine_pavement_stairs", () -> new StairBlock(CARMINE_BLOCK.get().defaultBlockState(), AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CARMINE_PAVEMENT_SLAB = BLOCKS.createBlock("carmine_pavement_slab", () -> new SlabBlock(AtmosphericProperties.CARMINE_BLOCK));
	public static final DeferredBlock<Block> CARMINE_PAVEMENT_WALL = BLOCKS.createBlock("carmine_pavement_wall", () -> new WallBlock(AtmosphericProperties.CARMINE_BLOCK));

	public static final DeferredBlock<Block> DRAGON_ROOTS = BLOCKS.createBlock("dragon_roots", () -> new DragonRootsBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1.5F).randomTicks().noCollission().sound(AtmosphericSoundTypes.DRAGON_ROOTS).pushReaction(PushReaction.DESTROY)));

	public static final DeferredBlock<Block> FIRETHORN = BLOCKS.createBlock("firethorn", () -> new DesertFlowerBlock(MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()));
	public static final DeferredBlock<Block> POTTED_FIRETHORN = BLOCKS.createBlockNoItem("potted_firethorn", () -> new FlowerPotBlock(FIRETHORN.get(), PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> FORSYTHIA = BLOCKS.createBlock("forsythia", () -> new DesertFlowerBlock(MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()));
	public static final DeferredBlock<Block> POTTED_FORSYTHIA = BLOCKS.createBlockNoItem("potted_forsythia", () -> new FlowerPotBlock(FORSYTHIA.get(), PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> DRAGON_FRUIT_CRATE = BLOCKS.createBlock("dragon_fruit_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));
	public static final DeferredBlock<Block> GOLDEN_DRAGON_FRUIT_CRATE = BLOCKS.createBlock("golden_dragon_fruit_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.GOLD).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(Atmospheric.MOD_ID)
				.tab(BUILDING_BLOCKS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK), ROSEWOOD_LOG, ROSEWOOD, STRIPPED_ROSEWOOD_LOG, STRIPPED_ROSEWOOD, ROSEWOOD_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), ROSEWOOD_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK),
						ROSEWOOD_STAIRS, ROSEWOOD_SLAB, ROSEWOOD_FENCE, ROSEWOOD_FENCE_GATE, ROSEWOOD_DOOR, ROSEWOOD_TRAPDOOR, ROSEWOOD_PRESSURE_PLATE, ROSEWOOD_BUTTON,
						MORADO_LOG, MORADO_WOOD, STRIPPED_MORADO_LOG, STRIPPED_MORADO_WOOD, MORADO_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), MORADO_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK),
						MORADO_STAIRS, MORADO_SLAB, MORADO_FENCE, MORADO_FENCE_GATE, MORADO_DOOR, MORADO_TRAPDOOR, MORADO_PRESSURE_PLATE, MORADO_BUTTON,
						YUCCA_LOG, YUCCA_WOOD, STRIPPED_YUCCA_LOG, STRIPPED_YUCCA_WOOD, YUCCA_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), YUCCA_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK),
						YUCCA_STAIRS, YUCCA_SLAB, YUCCA_FENCE, YUCCA_FENCE_GATE, YUCCA_DOOR, YUCCA_TRAPDOOR, YUCCA_PRESSURE_PLATE, YUCCA_BUTTON,
						LAUREL_LOG, LAUREL_WOOD, STRIPPED_LAUREL_LOG, STRIPPED_LAUREL_WOOD, LAUREL_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), LAUREL_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK),
						LAUREL_STAIRS, LAUREL_SLAB, LAUREL_FENCE, LAUREL_FENCE_GATE, LAUREL_DOOR, LAUREL_TRAPDOOR, LAUREL_PRESSURE_PLATE, LAUREL_BUTTON,
						ASPEN_LOG, ASPEN_WOOD, WATCHFUL_ASPEN_LOG, WATCHFUL_ASPEN_WOOD, CRUSTOSE_LOG, CRUSTOSE_WOOD, STRIPPED_ASPEN_LOG, STRIPPED_ASPEN_WOOD, ASPEN_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), ASPEN_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK),
						ASPEN_STAIRS, ASPEN_SLAB, ASPEN_FENCE, ASPEN_FENCE_GATE, ASPEN_DOOR, ASPEN_TRAPDOOR, ASPEN_PRESSURE_PLATE, ASPEN_BUTTON,
						KOUSA_LOG, KOUSA_WOOD, STRIPPED_KOUSA_LOG, STRIPPED_KOUSA_WOOD, KOUSA_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), KOUSA_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK),
						KOUSA_STAIRS, KOUSA_SLAB, KOUSA_FENCE, KOUSA_FENCE_GATE, KOUSA_DOOR, KOUSA_TRAPDOOR, KOUSA_PRESSURE_PLATE, KOUSA_BUTTON,
						GRIMWOOD_LOG, GRIMWOOD, STRIPPED_GRIMWOOD_LOG, STRIPPED_GRIMWOOD, GRIMWOOD_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), GRIMWOOD_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK),
						GRIMWOOD_STAIRS, GRIMWOOD_SLAB, GRIMWOOD_FENCE, GRIMWOOD_FENCE_GATE, GRIMWOOD_DOOR, GRIMWOOD_TRAPDOOR, GRIMWOOD_PRESSURE_PLATE, GRIMWOOD_BUTTON)
				.addItemsAfter(of(Blocks.CUT_RED_SANDSTONE_SLAB),
						ARID_SANDSTONE, ARID_SANDSTONE_STAIRS, ARID_SANDSTONE_SLAB, ARID_SANDSTONE_WALL,
						CHISELED_ARID_SANDSTONE, SMOOTH_ARID_SANDSTONE, SMOOTH_ARID_SANDSTONE_STAIRS, SMOOTH_ARID_SANDSTONE_SLAB,
						CUT_ARID_SANDSTONE, CUT_ARID_SANDSTONE_SLAB,
						RED_ARID_SANDSTONE, RED_ARID_SANDSTONE_STAIRS, RED_ARID_SANDSTONE_SLAB, RED_ARID_SANDSTONE_WALL,
						CHISELED_RED_ARID_SANDSTONE, SMOOTH_RED_ARID_SANDSTONE, SMOOTH_RED_ARID_SANDSTONE_STAIRS, SMOOTH_RED_ARID_SANDSTONE_SLAB,
						CUT_RED_ARID_SANDSTONE, CUT_RED_ARID_SANDSTONE_SLAB,
						CARMINE_BLOCK, CARMINE_SHINGLES, CARMINE_SHINGLE_STAIRS, CARMINE_SHINGLE_SLAB, CARMINE_SHINGLE_WALL, CHISELED_CARMINE_SHINGLES,
						CARMINE_PAVEMENT, CARMINE_PAVEMENT_STAIRS, CARMINE_PAVEMENT_SLAB, CARMINE_PAVEMENT_WALL
				)
				.addItemsBefore(of(Blocks.BRICKS),
						IVORY_TRAVERTINE, CHISELED_IVORY_TRAVERTINE, CUT_IVORY_TRAVERTINE, IVORY_TRAVERTINE_STAIRS, IVORY_TRAVERTINE_SLAB, IVORY_TRAVERTINE_WALL,
						PEACH_TRAVERTINE, CHISELED_PEACH_TRAVERTINE, CUT_PEACH_TRAVERTINE, PEACH_TRAVERTINE_STAIRS, PEACH_TRAVERTINE_SLAB, PEACH_TRAVERTINE_WALL,
						PERSIMMON_TRAVERTINE, CHISELED_PERSIMMON_TRAVERTINE, CUT_PERSIMMON_TRAVERTINE, PERSIMMON_TRAVERTINE_STAIRS, PERSIMMON_TRAVERTINE_SLAB, PERSIMMON_TRAVERTINE_WALL,
						SAFFRON_TRAVERTINE, CHISELED_SAFFRON_TRAVERTINE, CUT_SAFFRON_TRAVERTINE, SAFFRON_TRAVERTINE_STAIRS, SAFFRON_TRAVERTINE_SLAB, SAFFRON_TRAVERTINE_WALL,
						DOLERITE, DOLERITE_STAIRS, DOLERITE_SLAB, DOLERITE_WALL, POLISHED_DOLERITE, POLISHED_DOLERITE_STAIRS, POLISHED_DOLERITE_SLAB, POLISHED_DOLERITE_WALL
				)
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsBefore(of(Blocks.BAMBOO_SIGN),
						ROSEWOOD_SIGNS.getFirst(), ROSEWOOD_HANGING_SIGNS.getFirst(),
						MORADO_SIGNS.getFirst(), MORADO_HANGING_SIGNS.getFirst(),
						YUCCA_SIGNS.getFirst(), YUCCA_HANGING_SIGNS.getFirst(),
						LAUREL_SIGNS.getFirst(), LAUREL_HANGING_SIGNS.getFirst(),
						ASPEN_SIGNS.getFirst(), ASPEN_HANGING_SIGNS.getFirst(),
						KOUSA_SIGNS.getFirst(), KOUSA_HANGING_SIGNS.getFirst(),
						GRIMWOOD_SIGNS.getFirst(), GRIMWOOD_HANGING_SIGNS.getFirst()
				)
				.addItemsAfter(of(Blocks.SUSPICIOUS_SAND), SUSPICIOUS_ARID_SAND, SUSPICIOUS_RED_ARID_SAND)
				.tab(NATURAL_BLOCKS)
				.addItemsBefore(of(Blocks.DIRT_PATH), CRUSTOSE)
				.addItemsBefore(of(Blocks.DIRT), CRUSTOSE_PATH)
				.addItemsBefore(of(Blocks.ICE), ARID_SAND, ARID_SANDSTONE, RED_ARID_SAND, RED_ARID_SANDSTONE)
				.addItemsBefore(of(Blocks.MUSHROOM_STEM), ROSEWOOD_LOG, MORADO_LOG, YUCCA_LOG, LAUREL_LOG, ASPEN_LOG, WATCHFUL_ASPEN_LOG, CRUSTOSE_LOG, KOUSA_LOG, GRIMWOOD_LOG)
				.addItemsBefore(of(Blocks.TORCHFLOWER), WARM_MONKEY_BRUSH, HOT_MONKEY_BRUSH, SCALDING_MONKEY_BRUSH, YUCCA_FLOWER, GILIA, FIRETHORN, FORSYTHIA)
				.addItemsBefore(of(Blocks.PITCHER_PLANT), TALL_YUCCA_FLOWER)
				.addItemsBefore(of(Blocks.LILY_PAD), YUCCA_BRANCH, DRAGON_ROOTS, HANGING_CURRANT)
				.addItemsAfter(of(Blocks.LILY_PAD), WATER_HYACINTH)
				.addItemsAfter(of(Blocks.VINE), PASSION_VINE)
				.addItemsAfter(of(Blocks.FERN), AGAVE, GOLDEN_GROWTHS, ARID_SPROUTS)
				.addItemsAfter(of(Blocks.CACTUS), BARREL_CACTUS)
				.addItemsAfter(modLoaded(Blocks.HAY_BLOCK, "quark"), BARREL_CACTUS_BATCH, PASSION_FRUIT_CRATE, SHIMMERING_PASSION_FRUIT_CRATE, ORANGE_CRATE, BLOOD_ORANGE_CRATE, DRAGON_FRUIT_CRATE, GOLDEN_DRAGON_FRUIT_CRATE, YUCCA_CASK, ROASTED_YUCCA_CASK, CURRANT_CRATE)
				.addItemsAfter(of(Blocks.HAY_BLOCK), PASSION_VINE_BUNDLE, YUCCA_BUNDLE, ROASTED_YUCCA_BUNDLE, ALOE_BUNDLE)
				.addItemsAfter(of(Blocks.COBWEB), GRIMWEB)
				.addItemsBefore(of(Blocks.OAK_LEAVES), CURRANT_STALK, CURRANT_STALK_BUNDLE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), ROSEWOOD_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), ROSEWOOD_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), MORADO_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), MORADO_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), FLOWERING_MORADO_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), FLOWERING_MORADO_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), YUCCA_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), YUCCA_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), LAUREL_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), LAUREL_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), DRY_LAUREL_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), DRY_LAUREL_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), ASPEN_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), ASPEN_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), GREEN_ASPEN_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), GREEN_ASPEN_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), KOUSA_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), KOUSA_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), CURRANT_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), CURRANT_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), GRIMWOOD_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), GRIMWOOD_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA), ROSEWOOD_SAPLING, MORADO_SAPLING, YUCCA_SAPLING, LAUREL_SAPLING, DRY_LAUREL_SAPLING, ASPEN_SAPLING, GREEN_ASPEN_SAPLING, KOUSA_SAPLING, CURRANT_SEEDLING, GRIMWOOD_SAPLING)
				.addItemsAfter(of(Blocks.HONEY_BLOCK), ALOE_GEL_BLOCK)
				.tab(COLORED_BLOCKS)
				.addItemsAfter(of(Blocks.GLASS), ARID_GLASS)
				.addItemsAfter(of(Blocks.GLASS_PANE), ARID_GLASS_PANE)
				.tab(REDSTONE_BLOCKS)
				.addItemsAfter(of(Blocks.HONEY_BLOCK), ALOE_GEL_BLOCK);

		CreativeModeTabContentsPopulator.mod("woodworks_1")
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsBefore(ofID(AtmosphericConstants.BAMBOO_LADDER), ROSEWOOD_LADDER, MORADO_LADDER, YUCCA_LADDER, LAUREL_LADDER, ASPEN_LADDER, KOUSA_LADDER, GRIMWOOD_LADDER)
				.addItemsBefore(ofID(AtmosphericConstants.BAMBOO_BEEHIVE), ROSEWOOD_BEEHIVE, MORADO_BEEHIVE, YUCCA_BEEHIVE, LAUREL_BEEHIVE, ASPEN_BEEHIVE, KOUSA_BEEHIVE, GRIMWOOD_BEEHIVE)
				.addItemsBefore(ofID(AtmosphericConstants.BAMBOO_BOOKSHELF), ROSEWOOD_BOOKSHELF, CHISELED_ROSEWOOD_BOOKSHELF, MORADO_BOOKSHELF, CHISELED_MORADO_BOOKSHELF, YUCCA_BOOKSHELF, CHISELED_YUCCA_BOOKSHELF, LAUREL_BOOKSHELF, CHISELED_LAUREL_BOOKSHELF, ASPEN_BOOKSHELF, CHISELED_ASPEN_BOOKSHELF, KOUSA_BOOKSHELF, CHISELED_KOUSA_BOOKSHELF, GRIMWOOD_BOOKSHELF, CHISELED_GRIMWOOD_BOOKSHELF)
				.addItemsBefore(ofID(AtmosphericConstants.BAMBOO_CLOSET), ROSEWOOD_CHEST, MORADO_CHEST, YUCCA_CHEST, LAUREL_CHEST, ASPEN_CHEST, KOUSA_CHEST, GRIMWOOD_CHEST)
				.tab(REDSTONE_BLOCKS)
				.addItemsBefore(ofID(AtmosphericConstants.TRAPPED_BAMBOO_CLOSET), TRAPPED_ROSEWOOD_CHEST, TRAPPED_MORADO_CHEST, TRAPPED_YUCCA_CHEST, TRAPPED_LAUREL_CHEST, TRAPPED_ASPEN_CHEST, TRAPPED_KOUSA_CHEST, TRAPPED_GRIMWOOD_CHEST);
	}

	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, ItemLike fallback, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) ? of(BuiltInRegistries.ITEM.get(location)) : of(fallback)).test(stack);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(BuiltInRegistries.ITEM.get(location)).test(stack));
	}

}
