package com.teamabnormals.atmospheric.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.common.block.*;
import com.teamabnormals.atmospheric.common.block.grower.*;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericConstants;
import com.teamabnormals.atmospheric.core.other.AtmosphericProperties;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Predicate;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericBlocks {

	public static final AtmosphericBlockSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getBlockSubHelper();

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_ROSEWOOD_LOG = HELPER.createBlock("stripped_rosewood_log", () -> new RotatedPillarBlock(AtmosphericProperties.ROSEWOOD.log()));
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD = HELPER.createBlock("stripped_rosewood", () -> new RotatedPillarBlock(AtmosphericProperties.ROSEWOOD.log()));
	public static final RegistryObject<Block> ROSEWOOD_LOG = HELPER.createBlock("rosewood_log", () -> new LogBlock(STRIPPED_ROSEWOOD_LOG, AtmosphericProperties.ROSEWOOD.log()));
	public static final RegistryObject<Block> ROSEWOOD = HELPER.createBlock("rosewood", () -> new LogBlock(STRIPPED_ROSEWOOD, AtmosphericProperties.ROSEWOOD.log()));
	public static final RegistryObject<Block> ROSEWOOD_LEAVES = HELPER.createBlock("rosewood_leaves", () -> new LeavesBlock(AtmosphericProperties.ROSEWOOD.leaves()));
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = HELPER.createBlock("rosewood_sapling", () -> new SaplingBlock(new RosewoodTreeGrower(), AtmosphericProperties.ROSEWOOD.sapling()));
	public static final RegistryObject<Block> POTTED_ROSEWOOD_SAPLING = HELPER.createBlockNoItem("potted_rosewood_sapling", () -> new FlowerPotBlock(ROSEWOOD_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> ROSEWOOD_PLANKS = HELPER.createBlock("rosewood_planks", () -> new Block(AtmosphericProperties.ROSEWOOD.planks()));
	public static final RegistryObject<Block> ROSEWOOD_STAIRS = HELPER.createBlock("rosewood_stairs", () -> new StairBlock(() -> ROSEWOOD_PLANKS.get().defaultBlockState(), AtmosphericProperties.ROSEWOOD.planks()));
	public static final RegistryObject<Block> ROSEWOOD_SLAB = HELPER.createBlock("rosewood_slab", () -> new SlabBlock(AtmosphericProperties.ROSEWOOD.planks()));
	public static final RegistryObject<Block> ROSEWOOD_PRESSURE_PLATE = HELPER.createBlock("rosewood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.ROSEWOOD.pressurePlate(), AtmosphericProperties.ROSEWOOD_BLOCK_SET));
	public static final RegistryObject<Block> ROSEWOOD_BUTTON = HELPER.createBlock("rosewood_button", () -> new ButtonBlock(AtmosphericProperties.ROSEWOOD.button(), AtmosphericProperties.ROSEWOOD_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> ROSEWOOD_FENCE = HELPER.createFuelBlock("rosewood_fence", () -> new FenceBlock(AtmosphericProperties.ROSEWOOD.planks()), 300);
	public static final RegistryObject<Block> ROSEWOOD_FENCE_GATE = HELPER.createFuelBlock("rosewood_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.ROSEWOOD.planks(), AtmosphericProperties.ROSEWOOD_WOOD_TYPE), 300);
	public static final RegistryObject<Block> ROSEWOOD_DOOR = HELPER.createBlock("rosewood_door", () -> new DoorBlock(AtmosphericProperties.ROSEWOOD.door(), AtmosphericProperties.ROSEWOOD_BLOCK_SET));
	public static final RegistryObject<Block> ROSEWOOD_TRAPDOOR = HELPER.createBlock("rosewood_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.ROSEWOOD.trapdoor(), AtmosphericProperties.ROSEWOOD_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> ROSEWOOD_SIGNS = HELPER.createSignBlock("rosewood", AtmosphericProperties.ROSEWOOD_WOOD_TYPE, AtmosphericProperties.ROSEWOOD.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> ROSEWOOD_HANGING_SIGNS = HELPER.createHangingSignBlock("rosewood", AtmosphericProperties.ROSEWOOD_WOOD_TYPE, AtmosphericProperties.ROSEWOOD.hangingSign());

	public static final RegistryObject<Block> ROSEWOOD_BOARDS = HELPER.createFuelBlock("rosewood_boards", () -> new RotatedPillarBlock(AtmosphericProperties.ROSEWOOD.planks()), 300);
	public static final RegistryObject<Block> ROSEWOOD_BOOKSHELF = HELPER.createFuelBlock("rosewood_bookshelf", () -> new Block(AtmosphericProperties.ROSEWOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_ROSEWOOD_BOOKSHELF = HELPER.createFuelBlock("chiseled_rosewood_bookshelf", () -> new ChiseledRosewoodBookShelfBlock(AtmosphericProperties.ROSEWOOD.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> ROSEWOOD_LADDER = HELPER.createFuelBlock("rosewood_ladder", () -> new LadderBlock(AtmosphericProperties.ROSEWOOD.ladder()), 300);
	public static final RegistryObject<Block> ROSEWOOD_BEEHIVE = HELPER.createBlock("rosewood_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.ROSEWOOD.beehive()));
	public static final RegistryObject<Block> ROSEWOOD_LEAF_PILE = HELPER.createBlock("rosewood_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.ROSEWOOD.leafPile()));
	public static final RegistryObject<BlueprintChestBlock> ROSEWOOD_CHEST = HELPER.createChestBlock("rosewood", AtmosphericProperties.ROSEWOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_ROSEWOOD_CHEST = HELPER.createTrappedChestBlockNamed("rosewood", AtmosphericProperties.ROSEWOOD.chest());

	public static final RegistryObject<Block> STRIPPED_MORADO_LOG = HELPER.createBlock("stripped_morado_log", () -> new RotatedPillarBlock(AtmosphericProperties.MORADO.log()));
	public static final RegistryObject<Block> STRIPPED_MORADO_WOOD = HELPER.createBlock("stripped_morado_wood", () -> new RotatedPillarBlock(AtmosphericProperties.MORADO.log()));
	public static final RegistryObject<Block> MORADO_LOG = HELPER.createBlock("morado_log", () -> new LogBlock(STRIPPED_MORADO_LOG, AtmosphericProperties.MORADO.log()));
	public static final RegistryObject<Block> MORADO_WOOD = HELPER.createBlock("morado_wood", () -> new LogBlock(STRIPPED_MORADO_WOOD, AtmosphericProperties.MORADO.log()));
	public static final RegistryObject<Block> MORADO_LEAVES = HELPER.createBlock("morado_leaves", () -> new LeavesBlock(AtmosphericProperties.MORADO.leaves()));
	public static final RegistryObject<Block> MORADO_SAPLING = HELPER.createBlock("morado_sapling", () -> new SaplingBlock(new MoradoTreeGrower(), AtmosphericProperties.MORADO.sapling()));
	public static final RegistryObject<Block> POTTED_MORADO_SAPLING = HELPER.createBlockNoItem("potted_morado_sapling", () -> new FlowerPotBlock(MORADO_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> MORADO_PLANKS = HELPER.createBlock("morado_planks", () -> new Block(AtmosphericProperties.MORADO.planks()));
	public static final RegistryObject<Block> MORADO_STAIRS = HELPER.createBlock("morado_stairs", () -> new StairBlock(() -> MORADO_PLANKS.get().defaultBlockState(), AtmosphericProperties.MORADO.planks()));
	public static final RegistryObject<Block> MORADO_SLAB = HELPER.createBlock("morado_slab", () -> new SlabBlock(AtmosphericProperties.MORADO.planks()));
	public static final RegistryObject<Block> MORADO_PRESSURE_PLATE = HELPER.createBlock("morado_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.MORADO.pressurePlate(), AtmosphericProperties.MORADO_BLOCK_SET));
	public static final RegistryObject<Block> MORADO_BUTTON = HELPER.createBlock("morado_button", () -> new ButtonBlock(AtmosphericProperties.MORADO.button(), AtmosphericProperties.MORADO_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> MORADO_FENCE = HELPER.createFuelBlock("morado_fence", () -> new FenceBlock(AtmosphericProperties.MORADO.planks()), 300);
	public static final RegistryObject<Block> MORADO_FENCE_GATE = HELPER.createFuelBlock("morado_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.MORADO.planks(), AtmosphericProperties.MORADO_WOOD_TYPE), 300);
	public static final RegistryObject<Block> MORADO_DOOR = HELPER.createBlock("morado_door", () -> new DoorBlock(AtmosphericProperties.MORADO.door(), AtmosphericProperties.MORADO_BLOCK_SET));
	public static final RegistryObject<Block> MORADO_TRAPDOOR = HELPER.createBlock("morado_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.MORADO.trapdoor(), AtmosphericProperties.MORADO_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> MORADO_SIGNS = HELPER.createSignBlock("morado", AtmosphericProperties.MORADO_WOOD_TYPE, AtmosphericProperties.MORADO.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> MORADO_HANGING_SIGNS = HELPER.createHangingSignBlock("morado", AtmosphericProperties.MORADO_WOOD_TYPE, AtmosphericProperties.MORADO.hangingSign());

	public static final RegistryObject<Block> MORADO_BOARDS = HELPER.createFuelBlock("morado_boards", () -> new RotatedPillarBlock(AtmosphericProperties.MORADO.planks()), 300);
	public static final RegistryObject<Block> MORADO_BOOKSHELF = HELPER.createFuelBlock("morado_bookshelf", () -> new Block(AtmosphericProperties.MORADO.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_MORADO_BOOKSHELF = HELPER.createFuelBlock("chiseled_morado_bookshelf", () -> new ChiseledMoradoBookShelfBlock(AtmosphericProperties.MORADO.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> MORADO_LADDER = HELPER.createFuelBlock("morado_ladder", () -> new LadderBlock(AtmosphericProperties.MORADO.ladder()), 300);
	public static final RegistryObject<Block> MORADO_BEEHIVE = HELPER.createBlock("morado_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.MORADO.beehive()));
	public static final RegistryObject<Block> MORADO_LEAF_PILE = HELPER.createBlock("morado_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.MORADO.leafPile()));
	public static final RegistryObject<BlueprintChestBlock> MORADO_CHEST = HELPER.createChestBlock("morado", AtmosphericProperties.MORADO.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_MORADO_CHEST = HELPER.createTrappedChestBlockNamed("morado", AtmosphericProperties.MORADO.chest());

	public static final RegistryObject<Block> FLOWERING_MORADO_LEAVES = HELPER.createBlock("flowering_morado_leaves", () -> new FloweringMoradoLeavesBlock(AtmosphericProperties.MORADO.leaves()));
	public static final RegistryObject<Block> FLOWERING_MORADO_LEAF_PILE = HELPER.createBlock("flowering_morado_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.MORADO.leafPile()));

	public static final RegistryObject<Block> PASSION_VINE = HELPER.createBlock("passion_vine", () -> new PassionVineBlock(Block.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.VINE).pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> PASSION_VINE_BUNDLE = HELPER.createBlock("passion_vine_bundle", () -> new PassionVineBundleBlock(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS)));

	public static final RegistryObject<Block> WATER_HYACINTH = HELPER.createBlockNoItem("water_hyacinth", () -> new WaterHyacinthBlock(Block.Properties.of().noCollission().instabreak().sound(SoundType.VINE).pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> WARM_MONKEY_BRUSH = HELPER.createBlockNoItem("warm_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH)));
	public static final RegistryObject<Block> HOT_MONKEY_BRUSH = HELPER.createBlockNoItem("hot_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH)));
	public static final RegistryObject<Block> SCALDING_MONKEY_BRUSH = HELPER.createBlockNoItem("scalding_monkey_brush", () -> new MonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH)));

	public static final RegistryObject<Block> WARM_WALL_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("warm_monkey_brush", "warm_wall_monkey_brush", WARM_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH).offsetType(OffsetType.NONE).lootFrom(WARM_MONKEY_BRUSH)));
	public static final RegistryObject<Block> HOT_WALL_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("hot_monkey_brush", "hot_wall_monkey_brush", HOT_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH).offsetType(OffsetType.NONE).lootFrom(HOT_MONKEY_BRUSH)));
	public static final RegistryObject<Block> SCALDING_WALL_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("scalding_monkey_brush", "scalding_wall_monkey_brush", SCALDING_MONKEY_BRUSH, () -> new WallMonkeyBrushBlock(PropertyUtil.flower().sound(AtmosphericSoundTypes.MONKEY_BRUSH).offsetType(OffsetType.NONE).lootFrom(SCALDING_MONKEY_BRUSH)));

	public static final RegistryObject<Block> POTTED_WARM_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_warm_monkey_brush", () -> new FlowerPotBlock(WARM_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> POTTED_HOT_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_hot_monkey_brush", () -> new FlowerPotBlock(HOT_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> POTTED_SCALDING_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_scalding_monkey_brush", () -> new FlowerPotBlock(SCALDING_MONKEY_BRUSH.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> POTTED_WATER_HYACINTH = HELPER.createBlockNoItem("potted_water_hyacinth", () -> new FlowerPotBlock(WATER_HYACINTH.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> PASSION_FRUIT_CRATE = HELPER.createBlock("passion_fruit_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> SHIMMERING_PASSION_FRUIT_CRATE = HELPER.createBlock("shimmering_passion_fruit_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.GOLD).lightLevel((state) -> 7).strength(1.5F).sound(SoundType.WOOD)));

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> IVORY_TRAVERTINE = HELPER.createBlock("ivory_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final RegistryObject<Block> PEACH_TRAVERTINE = HELPER.createBlock("peach_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE = HELPER.createBlock("persimmon_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE = HELPER.createBlock("saffron_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final RegistryObject<Block> CHISELED_IVORY_TRAVERTINE = HELPER.createBlock("chiseled_ivory_travertine", () -> new Block(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final RegistryObject<Block> CHISELED_PEACH_TRAVERTINE = HELPER.createBlock("chiseled_peach_travertine", () -> new Block(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final RegistryObject<Block> CHISELED_PERSIMMON_TRAVERTINE = HELPER.createBlock("chiseled_persimmon_travertine", () -> new Block(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final RegistryObject<Block> CHISELED_SAFFRON_TRAVERTINE = HELPER.createBlock("chiseled_saffron_travertine", () -> new Block(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final RegistryObject<Block> CUT_IVORY_TRAVERTINE = HELPER.createBlock("cut_ivory_travertine", () -> new Block(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final RegistryObject<Block> CUT_PEACH_TRAVERTINE = HELPER.createBlock("cut_peach_travertine", () -> new Block(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final RegistryObject<Block> CUT_PERSIMMON_TRAVERTINE = HELPER.createBlock("cut_persimmon_travertine", () -> new Block(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final RegistryObject<Block> CUT_SAFFRON_TRAVERTINE = HELPER.createBlock("cut_saffron_travertine", () -> new Block(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final RegistryObject<Block> IVORY_TRAVERTINE_STAIRS = HELPER.createBlock("ivory_travertine_stairs", () -> new StairBlock(() -> IVORY_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.IVORY_TRAVERTINE));
	public static final RegistryObject<Block> PEACH_TRAVERTINE_STAIRS = HELPER.createBlock("peach_travertine_stairs", () -> new StairBlock(() -> PEACH_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.PEACH_TRAVERTINE));
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_STAIRS = HELPER.createBlock("persimmon_travertine_stairs", () -> new StairBlock(() -> PERSIMMON_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_STAIRS = HELPER.createBlock("saffron_travertine_stairs", () -> new StairBlock(() -> SAFFRON_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final RegistryObject<Block> IVORY_TRAVERTINE_SLAB = HELPER.createBlock("ivory_travertine_slab", () -> new SlabBlock(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final RegistryObject<Block> PEACH_TRAVERTINE_SLAB = HELPER.createBlock("peach_travertine_slab", () -> new SlabBlock(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_SLAB = HELPER.createBlock("persimmon_travertine_slab", () -> new SlabBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_SLAB = HELPER.createBlock("saffron_travertine_slab", () -> new SlabBlock(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final RegistryObject<Block> IVORY_TRAVERTINE_WALL = HELPER.createBlock("ivory_travertine_wall", () -> new WallBlock(AtmosphericProperties.IVORY_TRAVERTINE));
	public static final RegistryObject<Block> PEACH_TRAVERTINE_WALL = HELPER.createBlock("peach_travertine_wall", () -> new WallBlock(AtmosphericProperties.PEACH_TRAVERTINE));
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_WALL = HELPER.createBlock("persimmon_travertine_wall", () -> new WallBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE));
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_WALL = HELPER.createBlock("saffron_travertine_wall", () -> new WallBlock(AtmosphericProperties.SAFFRON_TRAVERTINE));

	public static final RegistryObject<Block> DOLERITE = HELPER.createBlock("dolerite", () -> new Block(AtmosphericProperties.DOLERITE));
	public static final RegistryObject<Block> DOLERITE_STAIRS = HELPER.createBlock("dolerite_stairs", () -> new StairBlock(() -> DOLERITE.get().defaultBlockState(), AtmosphericProperties.DOLERITE));
	public static final RegistryObject<Block> DOLERITE_SLAB = HELPER.createBlock("dolerite_slab", () -> new SlabBlock(AtmosphericProperties.DOLERITE));
	public static final RegistryObject<Block> DOLERITE_WALL = HELPER.createBlock("dolerite_wall", () -> new WallBlock(AtmosphericProperties.DOLERITE));
	public static final RegistryObject<Block> POLISHED_DOLERITE = HELPER.createBlock("polished_dolerite", () -> new Block(AtmosphericProperties.DOLERITE));
	public static final RegistryObject<Block> POLISHED_DOLERITE_STAIRS = HELPER.createBlock("polished_dolerite_stairs", () -> new StairBlock(() -> POLISHED_DOLERITE.get().defaultBlockState(), AtmosphericProperties.DOLERITE));
	public static final RegistryObject<Block> POLISHED_DOLERITE_SLAB = HELPER.createBlock("polished_dolerite_slab", () -> new SlabBlock(AtmosphericProperties.DOLERITE));

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> ARID_SAND = HELPER.createBlock("arid_sand", () -> new AridSandBlock(14406560, AtmosphericProperties.ARID_SAND));
	public static final RegistryObject<Block> ARID_SANDSTONE = HELPER.createBlock("arid_sandstone", () -> new Block(AtmosphericProperties.aridSandstone()));
	public static final RegistryObject<Block> ARID_SANDSTONE_SLAB = HELPER.createBlock("arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothAridSandstone()));
	public static final RegistryObject<Block> ARID_SANDSTONE_STAIRS = HELPER.createBlock("arid_sandstone_stairs", () -> new StairBlock(() -> ARID_SANDSTONE.get().defaultBlockState(), AtmosphericProperties.aridSandstone()));
	public static final RegistryObject<Block> ARID_SANDSTONE_WALL = HELPER.createBlock("arid_sandstone_wall", () -> new WallBlock(AtmosphericProperties.aridSandstone().forceSolidOn()));

	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE = HELPER.createBlock("smooth_arid_sandstone", () -> new Block(AtmosphericProperties.smoothAridSandstone()));
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_SLAB = HELPER.createBlock("smooth_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothAridSandstone()));
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_STAIRS = HELPER.createBlock("smooth_arid_sandstone_stairs", () -> new StairBlock(() -> SMOOTH_ARID_SANDSTONE.get().defaultBlockState(), AtmosphericProperties.smoothAridSandstone()));
	public static final RegistryObject<Block> CUT_ARID_SANDSTONE = HELPER.createBlock("cut_arid_sandstone", () -> new Block(AtmosphericProperties.aridSandstone()));
	public static final RegistryObject<Block> CUT_ARID_SANDSTONE_SLAB = HELPER.createBlock("cut_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothAridSandstone()));
	public static final RegistryObject<Block> CHISELED_ARID_SANDSTONE = HELPER.createBlock("chiseled_arid_sandstone", () -> new Block(AtmosphericProperties.aridSandstone()));

	public static final RegistryObject<Block> RED_ARID_SAND = HELPER.createBlock("red_arid_sand", () -> new AridSandBlock(16241568, AtmosphericProperties.RED_ARID_SAND));
	public static final RegistryObject<Block> RED_ARID_SANDSTONE = HELPER.createBlock("red_arid_sandstone", () -> new Block(AtmosphericProperties.redAridSandstone()));
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("red_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothRedAridSandstone()));
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_STAIRS = HELPER.createBlock("red_arid_sandstone_stairs", () -> new StairBlock(() -> RED_ARID_SANDSTONE.get().defaultBlockState(), AtmosphericProperties.redAridSandstone()));
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_WALL = HELPER.createBlock("red_arid_sandstone_wall", () -> new WallBlock(AtmosphericProperties.redAridSandstone().forceSolidOn()));

	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE = HELPER.createBlock("smooth_red_arid_sandstone", () -> new Block(AtmosphericProperties.smoothRedAridSandstone()));
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("smooth_red_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothRedAridSandstone()));
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_STAIRS = HELPER.createBlock("smooth_red_arid_sandstone_stairs", () -> new StairBlock(() -> SMOOTH_RED_ARID_SANDSTONE.get().defaultBlockState(), AtmosphericProperties.smoothRedAridSandstone()));
	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE = HELPER.createBlock("cut_red_arid_sandstone", () -> new Block(AtmosphericProperties.redAridSandstone()));
	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("cut_red_arid_sandstone_slab", () -> new SlabBlock(AtmosphericProperties.smoothRedAridSandstone()));
	public static final RegistryObject<Block> CHISELED_RED_ARID_SANDSTONE = HELPER.createBlock("chiseled_red_arid_sandstone", () -> new Block(AtmosphericProperties.redAridSandstone()));

	public static final RegistryObject<Block> ARID_GLASS = HELPER.createBlock("arid_glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(AtmosphericSoundTypes.ARID_GLASS)));
	public static final RegistryObject<Block> ARID_GLASS_PANE = HELPER.createBlock("arid_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE).sound(AtmosphericSoundTypes.ARID_GLASS)));

	public static final RegistryObject<Block> SUSPICIOUS_ARID_SAND = HELPER.createBlock("suspicious_arid_sand", () -> new BrushableBlock(ARID_SAND.get(), BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(AtmosphericSoundTypes.SUSPICIOUS_ARID_SAND).pushReaction(PushReaction.DESTROY), SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED));
	public static final RegistryObject<Block> SUSPICIOUS_RED_ARID_SAND = HELPER.createBlock("suspicious_red_arid_sand", () -> new BrushableBlock(RED_ARID_SAND.get(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(AtmosphericSoundTypes.SUSPICIOUS_ARID_SAND).pushReaction(PushReaction.DESTROY), SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED));

	public static final RegistryObject<Block> STRIPPED_YUCCA_LOG = HELPER.createBlock("stripped_yucca_log", () -> new RotatedPillarBlock(AtmosphericProperties.YUCCA.log()));
	public static final RegistryObject<Block> STRIPPED_YUCCA_WOOD = HELPER.createBlock("stripped_yucca_wood", () -> new RotatedPillarBlock(AtmosphericProperties.YUCCA.log()));
	public static final RegistryObject<Block> YUCCA_LOG = HELPER.createBlock("yucca_log", () -> new LogBlock(STRIPPED_YUCCA_LOG, AtmosphericProperties.YUCCA.log()));
	public static final RegistryObject<Block> YUCCA_WOOD = HELPER.createBlock("yucca_wood", () -> new LogBlock(STRIPPED_YUCCA_WOOD, AtmosphericProperties.YUCCA.log()));
	public static final RegistryObject<Block> YUCCA_LEAVES = HELPER.createBlock("yucca_leaves", () -> new YuccaLeavesBlock(AtmosphericProperties.YUCCA.leaves()));
	public static final RegistryObject<Block> YUCCA_SAPLING = HELPER.createBlock("yucca_sapling", () -> new YuccaSaplingBlock(new YuccaTreeGrower(), AtmosphericProperties.YUCCA.sapling()));
	public static final RegistryObject<Block> POTTED_YUCCA_SAPLING = HELPER.createBlockNoItem("potted_yucca_sapling", () -> new FlowerPotBlock(YUCCA_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> YUCCA_PLANKS = HELPER.createBlock("yucca_planks", () -> new Block(AtmosphericProperties.YUCCA.planks()));
	public static final RegistryObject<Block> YUCCA_STAIRS = HELPER.createBlock("yucca_stairs", () -> new StairBlock(() -> YUCCA_PLANKS.get().defaultBlockState(), AtmosphericProperties.YUCCA.planks()));
	public static final RegistryObject<Block> YUCCA_SLAB = HELPER.createBlock("yucca_slab", () -> new SlabBlock(AtmosphericProperties.YUCCA.planks()));
	public static final RegistryObject<Block> YUCCA_PRESSURE_PLATE = HELPER.createBlock("yucca_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.YUCCA.pressurePlate(), AtmosphericProperties.YUCCA_BLOCK_SET));
	public static final RegistryObject<Block> YUCCA_BUTTON = HELPER.createBlock("yucca_button", () -> new ButtonBlock(AtmosphericProperties.YUCCA.button(), AtmosphericProperties.YUCCA_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> YUCCA_FENCE = HELPER.createFuelBlock("yucca_fence", () -> new FenceBlock(AtmosphericProperties.YUCCA.planks()), 300);
	public static final RegistryObject<Block> YUCCA_FENCE_GATE = HELPER.createFuelBlock("yucca_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.YUCCA.planks(), AtmosphericProperties.YUCCA_WOOD_TYPE), 300);
	public static final RegistryObject<Block> YUCCA_DOOR = HELPER.createBlock("yucca_door", () -> new DoorBlock(AtmosphericProperties.YUCCA.door(), AtmosphericProperties.YUCCA_BLOCK_SET));
	public static final RegistryObject<Block> YUCCA_TRAPDOOR = HELPER.createBlock("yucca_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.YUCCA.trapdoor(), AtmosphericProperties.YUCCA_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> YUCCA_SIGNS = HELPER.createSignBlock("yucca", AtmosphericProperties.YUCCA_WOOD_TYPE, AtmosphericProperties.YUCCA.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> YUCCA_HANGING_SIGNS = HELPER.createHangingSignBlock("yucca", AtmosphericProperties.YUCCA_WOOD_TYPE, AtmosphericProperties.YUCCA.hangingSign());

	public static final RegistryObject<Block> YUCCA_BOARDS = HELPER.createFuelBlock("yucca_boards", () -> new RotatedPillarBlock(AtmosphericProperties.YUCCA.planks()), 300);
	public static final RegistryObject<Block> YUCCA_BOOKSHELF = HELPER.createFuelBlock("yucca_bookshelf", () -> new Block(AtmosphericProperties.YUCCA.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_YUCCA_BOOKSHELF = HELPER.createFuelBlock("chiseled_yucca_bookshelf", () -> new ChiseledYuccaBookShelfBlock(AtmosphericProperties.YUCCA.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> YUCCA_LADDER = HELPER.createFuelBlock("yucca_ladder", () -> new LadderBlock(AtmosphericProperties.YUCCA.ladder()), 300);
	public static final RegistryObject<Block> YUCCA_BEEHIVE = HELPER.createBlock("yucca_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.YUCCA.beehive()));
	public static final RegistryObject<Block> YUCCA_LEAF_PILE = HELPER.createBlock("yucca_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.YUCCA.leafPile()));
	public static final RegistryObject<BlueprintChestBlock> YUCCA_CHEST = HELPER.createChestBlock("yucca", AtmosphericProperties.YUCCA.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_YUCCA_CHEST = HELPER.createTrappedChestBlockNamed("yucca", AtmosphericProperties.YUCCA.chest());

	public static final RegistryObject<Block> YUCCA_BRANCH = HELPER.createBlock("yucca_branch", () -> new YuccaBranchBlock(Block.Properties.copy(Blocks.MELON_STEM).sound(SoundType.CROP).randomTicks()));
	public static final RegistryObject<Block> YUCCA_BUNDLE = HELPER.createBlock("yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.copy(Blocks.MELON).randomTicks()));
	public static final RegistryObject<Block> ROASTED_YUCCA_BUNDLE = HELPER.createBlock("roasted_yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.copy(Blocks.MELON).randomTicks()));

	public static final RegistryObject<Block> YUCCA_GATEAU = HELPER.createBlockNoItem("yucca_gateau", () -> new YuccaGateauBlock(Block.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> YUCCA_FLOWER = HELPER.createBlock("yucca_flower", () -> new YuccaFlowerBlock(AtmosphericMobEffects.PERSISTENCE::get, 15, AtmosphericProperties.YUCCA_FLOWER));
	public static final RegistryObject<Block> POTTED_YUCCA_FLOWER = HELPER.createBlockNoItem("potted_yucca_flower", () -> new FlowerPotBlock(YUCCA_FLOWER.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> TALL_YUCCA_FLOWER = HELPER.createBlock("tall_yucca_flower", () -> new YuccaFlowerDoubleBlock(AtmosphericProperties.YUCCA_FLOWER));

	public static final RegistryObject<Block> GILIA = HELPER.createBlock("gilia", () -> new DesertFlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()));
	public static final RegistryObject<Block> POTTED_GILIA = HELPER.createBlockNoItem("potted_gilia", () -> new FlowerPotBlock(GILIA.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> ARID_SPROUTS = HELPER.createBlock("arid_sprouts", () -> new AridSproutsBlock(AtmosphericProperties.ARID_SPROUTS));
	public static final RegistryObject<Block> ALOE_VERA = HELPER.createBlockNoItem("aloe_vera", () -> new AloeVeraBlock(AtmosphericProperties.ALOE_VERA));
	public static final RegistryObject<Block> TALL_ALOE_VERA = HELPER.createBlockNoItem("tall_aloe_vera", () -> new AloeVeraTallBlock(AtmosphericProperties.ALOE_VERA));
	public static final RegistryObject<Block> ALOE_BUNDLE = HELPER.createBlock("aloe_bundle", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.DRIED_KELP_BLOCK)));
	public static final RegistryObject<Block> ALOE_GEL_BLOCK = HELPER.createBlock("aloe_gel_block", () -> new AloeGelBlock(Block.Properties.copy(Blocks.SLIME_BLOCK).isSuffocating(PropertyUtil::never)));
	public static final RegistryObject<Block> POTTED_ALOE_VERA = HELPER.createBlockNoItem("potted_aloe_vera", () -> new FlowerPotBlock(ALOE_VERA.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> BARREL_CACTUS = HELPER.createBlockNoItem("barrel_cactus", () -> new BarrelCactusBlock(Block.Properties.copy(Blocks.CACTUS)));
	public static final RegistryObject<Block> POTTED_BARREL_CACTUS = HELPER.createBlockNoItem("potted_barrel_cactus", () -> new FlowerPotBlock(BARREL_CACTUS.get(), Block.Properties.copy(Blocks.POTTED_CACTUS)));
	public static final RegistryObject<Block> SNOWY_BARREL_CACTUS = HELPER.createBlockNoItem("snowy_barrel_cactus", () -> new BarrelCactusBlock(Block.Properties.copy(Blocks.CACTUS)));
	public static final RegistryObject<Block> POTTED_SNOWY_BARREL_CACTUS = HELPER.createBlockNoItem("potted_snowy_barrel_cactus", () -> new SnowyFlowerPotBlock(SNOWY_BARREL_CACTUS.get(), () -> BARREL_CACTUS.get(), Block.Properties.copy(Blocks.POTTED_CACTUS)));
	public static final RegistryObject<Block> SNOWY_CACTUS = HELPER.createBlockNoItem("snowy_cactus", () -> new SnowyCactusBlock(Block.Properties.copy(Blocks.CACTUS)));
	public static final RegistryObject<Block> POTTED_SNOWY_CACTUS = HELPER.createBlockNoItem("potted_snowy_cactus", () -> new SnowyFlowerPotBlock(SNOWY_CACTUS.get(), () -> Blocks.CACTUS, Block.Properties.copy(Blocks.POTTED_CACTUS)));

	public static final RegistryObject<Block> YUCCA_CASK = HELPER.createBlock("yucca_cask", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));
	public static final RegistryObject<Block> ROASTED_YUCCA_CASK = HELPER.createBlock("roasted_yucca_cask", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));
	public static final RegistryObject<Block> BARREL_CACTUS_BATCH = HELPER.createBlock("barrel_cactus_batch", () -> new RotatedPillarBlock(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F).sound(SoundType.WOOD).ignitedByLava()));

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_ASPEN_LOG = HELPER.createBlock("stripped_aspen_log", () -> new RotatedPillarBlock(AtmosphericProperties.ASPEN.log()));
	public static final RegistryObject<Block> STRIPPED_ASPEN_WOOD = HELPER.createBlock("stripped_aspen_wood", () -> new RotatedPillarBlock(AtmosphericProperties.ASPEN.log()));
	public static final RegistryObject<Block> ASPEN_LOG = HELPER.createBlock("aspen_log", () -> new LogBlock(STRIPPED_ASPEN_LOG, AtmosphericProperties.ASPEN.log()));
	public static final RegistryObject<Block> ASPEN_WOOD = HELPER.createBlock("aspen_wood", () -> new LogBlock(STRIPPED_ASPEN_WOOD, AtmosphericProperties.ASPEN.log()));
	public static final RegistryObject<Block> WATCHFUL_ASPEN_LOG = HELPER.createBlock("watchful_aspen_log", () -> new LogBlock(ASPEN_LOG, AtmosphericProperties.ASPEN.log()));
	public static final RegistryObject<Block> WATCHFUL_ASPEN_WOOD = HELPER.createBlock("watchful_aspen_wood", () -> new LogBlock(ASPEN_WOOD, AtmosphericProperties.ASPEN.log()));
	public static final RegistryObject<Block> ASPEN_LEAVES = HELPER.createBlock("aspen_leaves", () -> new LeavesBlock(AtmosphericProperties.ASPEN.leaves()));
	public static final RegistryObject<Block> ASPEN_SAPLING = HELPER.createBlock("aspen_sapling", () -> new SaplingBlock(new AspenTreeGrower(), AtmosphericProperties.ASPEN.sapling()));
	public static final RegistryObject<Block> POTTED_ASPEN_SAPLING = HELPER.createBlockNoItem("potted_aspen_sapling", () -> new FlowerPotBlock(ASPEN_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> ASPEN_PLANKS = HELPER.createBlock("aspen_planks", () -> new Block(AtmosphericProperties.ASPEN.planks()));
	public static final RegistryObject<Block> ASPEN_STAIRS = HELPER.createBlock("aspen_stairs", () -> new StairBlock(() -> ASPEN_PLANKS.get().defaultBlockState(), AtmosphericProperties.ASPEN.planks()));
	public static final RegistryObject<Block> ASPEN_SLAB = HELPER.createBlock("aspen_slab", () -> new SlabBlock(AtmosphericProperties.ASPEN.planks()));
	public static final RegistryObject<Block> ASPEN_PRESSURE_PLATE = HELPER.createBlock("aspen_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.ASPEN.pressurePlate(), AtmosphericProperties.ASPEN_BLOCK_SET));
	public static final RegistryObject<Block> ASPEN_BUTTON = HELPER.createBlock("aspen_button", () -> new ButtonBlock(AtmosphericProperties.ASPEN.button(), AtmosphericProperties.ASPEN_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> ASPEN_FENCE = HELPER.createFuelBlock("aspen_fence", () -> new FenceBlock(AtmosphericProperties.ASPEN.planks()), 300);
	public static final RegistryObject<Block> ASPEN_FENCE_GATE = HELPER.createFuelBlock("aspen_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.ASPEN.planks(), AtmosphericProperties.ASPEN_WOOD_TYPE), 300);
	public static final RegistryObject<Block> ASPEN_DOOR = HELPER.createBlock("aspen_door", () -> new DoorBlock(AtmosphericProperties.ASPEN.door(), AtmosphericProperties.ASPEN_BLOCK_SET));
	public static final RegistryObject<Block> ASPEN_TRAPDOOR = HELPER.createBlock("aspen_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.ASPEN.trapdoor(), AtmosphericProperties.ASPEN_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> ASPEN_SIGNS = HELPER.createSignBlock("aspen", AtmosphericProperties.ASPEN_WOOD_TYPE, AtmosphericProperties.ASPEN.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> ASPEN_HANGING_SIGNS = HELPER.createHangingSignBlock("aspen", AtmosphericProperties.ASPEN_WOOD_TYPE, AtmosphericProperties.ASPEN.hangingSign());

	public static final RegistryObject<Block> ASPEN_BOARDS = HELPER.createFuelBlock("aspen_boards", () -> new RotatedPillarBlock(AtmosphericProperties.ASPEN.planks()), 300);
	public static final RegistryObject<Block> ASPEN_BOOKSHELF = HELPER.createFuelBlock("aspen_bookshelf", () -> new Block(AtmosphericProperties.ASPEN.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_ASPEN_BOOKSHELF = HELPER.createFuelBlock("chiseled_aspen_bookshelf", () -> new ChiseledAspenBookShelfBlock(AtmosphericProperties.ASPEN.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> ASPEN_LADDER = HELPER.createFuelBlock("aspen_ladder", () -> new LadderBlock(AtmosphericProperties.ASPEN.ladder()), 300);
	public static final RegistryObject<Block> ASPEN_BEEHIVE = HELPER.createBlock("aspen_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.ASPEN.beehive()));
	public static final RegistryObject<Block> ASPEN_LEAF_PILE = HELPER.createBlock("aspen_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.ASPEN.leafPile()));
	public static final RegistryObject<BlueprintChestBlock> ASPEN_CHEST = HELPER.createChestBlock("aspen", AtmosphericProperties.ASPEN.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_ASPEN_CHEST = HELPER.createTrappedChestBlockNamed("aspen", AtmosphericProperties.ASPEN.chest());

	public static final RegistryObject<Block> GREEN_ASPEN_LEAVES = HELPER.createBlock("green_aspen_leaves", () -> new LeavesBlock(AtmosphericProperties.GREEN_ASPEN.leaves()));
	public static final RegistryObject<Block> GREEN_ASPEN_SAPLING = HELPER.createBlock("green_aspen_sapling", () -> new SaplingBlock(new GreenAspenTreeGrower(), AtmosphericProperties.GREEN_ASPEN.sapling()));
	public static final RegistryObject<Block> POTTED_GREEN_ASPEN_SAPLING = HELPER.createBlockNoItem("potted_green_aspen_sapling", () -> new FlowerPotBlock(GREEN_ASPEN_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> GREEN_ASPEN_LEAF_PILE = HELPER.createBlock("green_aspen_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.GREEN_ASPEN.leafPile()));

	public static final RegistryObject<Block> AGAVE = HELPER.createBlock("agave", () -> new AgaveBlock(AtmosphericProperties.AGAVE));
	public static final RegistryObject<Block> POTTED_AGAVE = HELPER.createBlockNoItem("potted_agave", () -> new FlowerPotBlock(AGAVE.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> GOLDEN_GROWTHS = HELPER.createBlock("golden_growths", () -> new GoldenGrowthsBlock(AtmosphericProperties.GOLDEN_GROWTHS));
	public static final RegistryObject<Block> POTTED_GOLDEN_GROWTHS = HELPER.createBlockNoItem("potted_golden_growths", () -> new FlowerPotBlock(GOLDEN_GROWTHS.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> CRUSTOSE = HELPER.createBlock("crustose", () -> new CrustoseBlock(AtmosphericProperties.CRUSTOSE));
	public static final RegistryObject<Block> CRUSTOSE_PATH = HELPER.createBlock("crustose_path", () -> new DirtPathBlock(AtmosphericProperties.CRUSTOSE_PATH));
	public static final RegistryObject<Block> CRUSTOSE_LOG = HELPER.createBlock("crustose_log", () -> new CrustoseLogBlock(ASPEN_LOG::get, AtmosphericProperties.ASPEN.log().randomTicks()));
	public static final RegistryObject<Block> CRUSTOSE_WOOD = HELPER.createBlock("crustose_wood", () -> new CrustoseLogBlock(ASPEN_WOOD::get, AtmosphericProperties.ASPEN.log().randomTicks()));

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_LAUREL_LOG = HELPER.createBlock("stripped_laurel_log", () -> new RotatedPillarBlock(AtmosphericProperties.LAUREL.log()));
	public static final RegistryObject<Block> STRIPPED_LAUREL_WOOD = HELPER.createBlock("stripped_laurel_wood", () -> new RotatedPillarBlock(AtmosphericProperties.LAUREL.log()));
	public static final RegistryObject<Block> LAUREL_LOG = HELPER.createBlock("laurel_log", () -> new LogBlock(STRIPPED_LAUREL_LOG, AtmosphericProperties.LAUREL.log()));
	public static final RegistryObject<Block> LAUREL_WOOD = HELPER.createBlock("laurel_wood", () -> new LogBlock(STRIPPED_LAUREL_WOOD, AtmosphericProperties.LAUREL.log()));
	public static final RegistryObject<Block> LAUREL_LEAVES = HELPER.createBlock("laurel_leaves", () -> new LeavesBlock(AtmosphericProperties.LAUREL.leaves()));
	public static final RegistryObject<Block> LAUREL_SAPLING = HELPER.createBlock("laurel_sapling", () -> new LaurelSaplingBlock(new LaurelTreeGrower(), AtmosphericProperties.LAUREL.sapling()));
	public static final RegistryObject<Block> POTTED_LAUREL_SAPLING = HELPER.createBlockNoItem("potted_laurel_sapling", () -> new FlowerPotBlock(LAUREL_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> LAUREL_PLANKS = HELPER.createBlock("laurel_planks", () -> new Block(AtmosphericProperties.LAUREL.planks()));
	public static final RegistryObject<Block> LAUREL_STAIRS = HELPER.createBlock("laurel_stairs", () -> new StairBlock(() -> LAUREL_PLANKS.get().defaultBlockState(), AtmosphericProperties.LAUREL.planks()));
	public static final RegistryObject<Block> LAUREL_SLAB = HELPER.createBlock("laurel_slab", () -> new SlabBlock(AtmosphericProperties.LAUREL.planks()));
	public static final RegistryObject<Block> LAUREL_PRESSURE_PLATE = HELPER.createBlock("laurel_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.LAUREL.pressurePlate(), AtmosphericProperties.LAUREL_BLOCK_SET));
	public static final RegistryObject<Block> LAUREL_BUTTON = HELPER.createBlock("laurel_button", () -> new ButtonBlock(AtmosphericProperties.LAUREL.button(), AtmosphericProperties.LAUREL_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> LAUREL_FENCE = HELPER.createFuelBlock("laurel_fence", () -> new FenceBlock(AtmosphericProperties.LAUREL.planks()), 300);
	public static final RegistryObject<Block> LAUREL_FENCE_GATE = HELPER.createFuelBlock("laurel_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.LAUREL.planks(), AtmosphericProperties.LAUREL_WOOD_TYPE), 300);
	public static final RegistryObject<Block> LAUREL_DOOR = HELPER.createBlock("laurel_door", () -> new DoorBlock(AtmosphericProperties.LAUREL.door(), AtmosphericProperties.LAUREL_BLOCK_SET));
	public static final RegistryObject<Block> LAUREL_TRAPDOOR = HELPER.createBlock("laurel_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.LAUREL.trapdoor(), AtmosphericProperties.LAUREL_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> LAUREL_SIGNS = HELPER.createSignBlock("laurel", AtmosphericProperties.LAUREL_WOOD_TYPE, AtmosphericProperties.LAUREL.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> LAUREL_HANGING_SIGNS = HELPER.createHangingSignBlock("laurel", AtmosphericProperties.LAUREL_WOOD_TYPE, AtmosphericProperties.LAUREL.hangingSign());

	public static final RegistryObject<Block> LAUREL_BOARDS = HELPER.createFuelBlock("laurel_boards", () -> new RotatedPillarBlock(AtmosphericProperties.LAUREL.planks()), 300);
	public static final RegistryObject<Block> LAUREL_BOOKSHELF = HELPER.createFuelBlock("laurel_bookshelf", () -> new Block(AtmosphericProperties.LAUREL.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_LAUREL_BOOKSHELF = HELPER.createFuelBlock("chiseled_laurel_bookshelf", () -> new BlueprintChiseledBookShelfBlock(AtmosphericProperties.LAUREL.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> LAUREL_LADDER = HELPER.createFuelBlock("laurel_ladder", () -> new LadderBlock(AtmosphericProperties.LAUREL.ladder()), 300);
	public static final RegistryObject<Block> LAUREL_BEEHIVE = HELPER.createBlock("laurel_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.LAUREL.beehive()));
	public static final RegistryObject<Block> LAUREL_LEAF_PILE = HELPER.createBlock("laurel_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.LAUREL.leafPile()));
	public static final RegistryObject<BlueprintChestBlock> LAUREL_CHEST = HELPER.createChestBlock("laurel", AtmosphericProperties.LAUREL.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_LAUREL_CHEST = HELPER.createTrappedChestBlockNamed("laurel", AtmosphericProperties.LAUREL.chest());

	public static final RegistryObject<Block> DRY_LAUREL_LEAVES = HELPER.createBlock("dry_laurel_leaves", () -> new LeavesBlock(AtmosphericProperties.DRY_LAUREL.leaves()));
	public static final RegistryObject<Block> DRY_LAUREL_SAPLING = HELPER.createBlock("dry_laurel_sapling", () -> new LaurelSaplingBlock(new DryLaurelTreeGrower(), AtmosphericProperties.DRY_LAUREL.sapling()));
	public static final RegistryObject<Block> POTTED_DRY_LAUREL_SAPLING = HELPER.createBlockNoItem("potted_dry_laurel_sapling", () -> new FlowerPotBlock(DRY_LAUREL_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> DRY_LAUREL_LEAF_PILE = HELPER.createBlock("dry_laurel_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.DRY_LAUREL.leafPile()));

	public static final RegistryObject<Block> ORANGE = HELPER.createBlockNoItem("orange", () -> new OrangeBlock(AtmosphericProperties.ORANGE));
	public static final RegistryObject<Block> BLOOD_ORANGE = HELPER.createBlockNoItem("blood_orange", () -> new OrangeBlock(AtmosphericProperties.ORANGE));

	public static final RegistryObject<Block> ORANGE_CRATE = HELPER.createBlock("orange_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));
	public static final RegistryObject<Block> BLOOD_ORANGE_CRATE = HELPER.createBlock("blood_orange_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> STRIPPED_KOUSA_LOG = HELPER.createBlock("stripped_kousa_log", () -> new RotatedPillarBlock(AtmosphericProperties.KOUSA.log()));
	public static final RegistryObject<Block> STRIPPED_KOUSA_WOOD = HELPER.createBlock("stripped_kousa_wood", () -> new RotatedPillarBlock(AtmosphericProperties.KOUSA.log()));
	public static final RegistryObject<Block> KOUSA_LOG = HELPER.createBlock("kousa_log", () -> new LogBlock(STRIPPED_KOUSA_LOG, AtmosphericProperties.KOUSA.log()));
	public static final RegistryObject<Block> KOUSA_WOOD = HELPER.createBlock("kousa_wood", () -> new LogBlock(STRIPPED_KOUSA_WOOD, AtmosphericProperties.KOUSA.log()));
	public static final RegistryObject<Block> KOUSA_LEAVES = HELPER.createBlock("kousa_leaves", () -> new LeavesBlock(AtmosphericProperties.KOUSA.leaves()));
	public static final RegistryObject<Block> KOUSA_SAPLING = HELPER.createBlock("kousa_sapling", () -> new SaplingBlock(new KousaTreeGrower(), AtmosphericProperties.KOUSA.sapling()));
	public static final RegistryObject<Block> POTTED_KOUSA_SAPLING = HELPER.createBlockNoItem("potted_kousa_sapling", () -> new FlowerPotBlock(KOUSA_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> KOUSA_PLANKS = HELPER.createBlock("kousa_planks", () -> new Block(AtmosphericProperties.KOUSA.planks()));
	public static final RegistryObject<Block> KOUSA_STAIRS = HELPER.createBlock("kousa_stairs", () -> new StairBlock(() -> KOUSA_PLANKS.get().defaultBlockState(), AtmosphericProperties.KOUSA.planks()));
	public static final RegistryObject<Block> KOUSA_SLAB = HELPER.createBlock("kousa_slab", () -> new SlabBlock(AtmosphericProperties.KOUSA.planks()));
	public static final RegistryObject<Block> KOUSA_PRESSURE_PLATE = HELPER.createBlock("kousa_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.KOUSA.pressurePlate(), AtmosphericProperties.KOUSA_BLOCK_SET));
	public static final RegistryObject<Block> KOUSA_BUTTON = HELPER.createBlock("kousa_button", () -> new ButtonBlock(AtmosphericProperties.KOUSA.button(), AtmosphericProperties.KOUSA_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> KOUSA_FENCE = HELPER.createFuelBlock("kousa_fence", () -> new FenceBlock(AtmosphericProperties.KOUSA.planks()), 300);
	public static final RegistryObject<Block> KOUSA_FENCE_GATE = HELPER.createFuelBlock("kousa_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.KOUSA.planks(), AtmosphericProperties.KOUSA_WOOD_TYPE), 300);
	public static final RegistryObject<Block> KOUSA_DOOR = HELPER.createBlock("kousa_door", () -> new DoorBlock(AtmosphericProperties.KOUSA.door(), AtmosphericProperties.KOUSA_BLOCK_SET));
	public static final RegistryObject<Block> KOUSA_TRAPDOOR = HELPER.createBlock("kousa_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.KOUSA.trapdoor(), AtmosphericProperties.KOUSA_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> KOUSA_SIGNS = HELPER.createSignBlock("kousa", AtmosphericProperties.KOUSA_WOOD_TYPE, AtmosphericProperties.KOUSA.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> KOUSA_HANGING_SIGNS = HELPER.createHangingSignBlock("kousa", AtmosphericProperties.KOUSA_WOOD_TYPE, AtmosphericProperties.KOUSA.hangingSign());

	public static final RegistryObject<Block> KOUSA_BOARDS = HELPER.createFuelBlock("kousa_boards", () -> new RotatedPillarBlock(AtmosphericProperties.KOUSA.planks()), 300);
	public static final RegistryObject<Block> KOUSA_BOOKSHELF = HELPER.createFuelBlock("kousa_bookshelf", () -> new Block(AtmosphericProperties.KOUSA.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_KOUSA_BOOKSHELF = HELPER.createFuelBlock("chiseled_kousa_bookshelf", () -> new ChiseledKousaBookShelfBlock(AtmosphericProperties.KOUSA.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> KOUSA_LADDER = HELPER.createFuelBlock("kousa_ladder", () -> new LadderBlock(AtmosphericProperties.KOUSA.ladder()), 300);
	public static final RegistryObject<Block> KOUSA_BEEHIVE = HELPER.createBlock("kousa_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.KOUSA.beehive()));
	public static final RegistryObject<Block> KOUSA_LEAF_PILE = HELPER.createBlock("kousa_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.KOUSA.leafPile()));
	public static final RegistryObject<BlueprintChestBlock> KOUSA_CHEST = HELPER.createChestBlock("kousa", AtmosphericProperties.KOUSA.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_KOUSA_CHEST = HELPER.createTrappedChestBlockNamed("kousa", AtmosphericProperties.KOUSA.chest());

	public static final RegistryObject<Block> SNOWY_BAMBOO_SAPLING = HELPER.createBlockNoItem("snowy_bamboo_sapling", () -> new SnowyBambooSaplingBlock(Properties.of().randomTicks().instabreak().noCollission().strength(1.0F).sound(SoundType.BAMBOO_SAPLING).offsetType(OffsetType.XZ)));
	public static final RegistryObject<Block> SNOWY_BAMBOO = HELPER.createBlockNoItem("snowy_bamboo", () -> new SnowyBambooBlock(Properties.of().mapColor(MapColor.PLANT).randomTicks().instabreak().strength(1.0F).sound(SoundType.BAMBOO).noOcclusion().dynamicShape().offsetType(OffsetType.XZ)));
	public static final RegistryObject<Block> POTTED_SNOWY_BAMBOO = HELPER.createBlockNoItem("potted_snowy_bamboo", () -> new SnowyFlowerPotBlock(SNOWY_BAMBOO.get(), () -> Blocks.BAMBOO, PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> HANGING_CURRANT = HELPER.createBlock("hanging_currant", () -> new HangingCurrantBlock(Block.Properties.copy(Blocks.MELON_STEM).sound(SoundType.CROP).randomTicks()));
	public static final RegistryObject<Block> CURRANT_STALK = HELPER.createFuelBlock("currant_stalk", () -> new CurrantStalkBlock(AtmosphericProperties.CURRANT.log()), 50);
	public static final RegistryObject<Block> CURRANT_STALK_BUNDLE = HELPER.createFuelBlock("currant_stalk_bundle", () -> new CurrantStalkBundleBlock(AtmosphericProperties.CURRANT.log()), 200);
	public static final RegistryObject<Block> CURRANT_LEAVES = HELPER.createBlock("currant_leaves", () -> new CurrantLeavesBlock(AtmosphericProperties.CURRANT.leaves()));
	public static final RegistryObject<Block> CURRANT_SEEDLING = HELPER.createBlock("currant_seedling", () -> new CurrantSeedlingBlock(AtmosphericProperties.CURRANT.sapling()));
	public static final RegistryObject<Block> POTTED_CURRANT_SEEDLING = HELPER.createBlockNoItem("potted_currant_seedling", () -> new FlowerPotBlock(CURRANT_SEEDLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> CURRANT_LEAF_PILE = HELPER.createBlock("currant_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.CURRANT.leafPile()));
	public static final RegistryObject<Block> CURRANT_CRATE = HELPER.createBlock("currant_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> GRIMWEB = HELPER.createBlock("grimweb", () -> new WebBlock(BlockBehaviour.Properties.copy(Blocks.COBWEB)));

	public static final RegistryObject<Block> STRIPPED_GRIMWOOD_LOG = HELPER.createBlock("stripped_grimwood_log", () -> new RotatedPillarBlock(AtmosphericProperties.GRIMWOOD.log()));
	public static final RegistryObject<Block> STRIPPED_GRIMWOOD = HELPER.createBlock("stripped_grimwood", () -> new RotatedPillarBlock(AtmosphericProperties.GRIMWOOD.log()));
	public static final RegistryObject<Block> GRIMWOOD_LOG = HELPER.createBlock("grimwood_log", () -> new LogBlock(STRIPPED_GRIMWOOD_LOG, AtmosphericProperties.GRIMWOOD.log()));
	public static final RegistryObject<Block> GRIMWOOD = HELPER.createBlock("grimwood", () -> new LogBlock(STRIPPED_GRIMWOOD, AtmosphericProperties.GRIMWOOD.log()));
	public static final RegistryObject<Block> GRIMWOOD_LEAVES = HELPER.createBlock("grimwood_leaves", () -> new LeavesBlock(AtmosphericProperties.GRIMWOOD.leaves()));
	public static final RegistryObject<Block> GRIMWOOD_SAPLING = HELPER.createBlock("grimwood_sapling", () -> new SaplingBlock(new GrimwoodTreeGrower(), AtmosphericProperties.GRIMWOOD.sapling()));
	public static final RegistryObject<Block> POTTED_GRIMWOOD_SAPLING = HELPER.createBlockNoItem("potted_grimwood_sapling", () -> new FlowerPotBlock(GRIMWOOD_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> GRIMWOOD_PLANKS = HELPER.createBlock("grimwood_planks", () -> new Block(AtmosphericProperties.GRIMWOOD.planks()));
	public static final RegistryObject<Block> GRIMWOOD_STAIRS = HELPER.createBlock("grimwood_stairs", () -> new StairBlock(() -> GRIMWOOD_PLANKS.get().defaultBlockState(), AtmosphericProperties.GRIMWOOD.planks()));
	public static final RegistryObject<Block> GRIMWOOD_SLAB = HELPER.createBlock("grimwood_slab", () -> new SlabBlock(AtmosphericProperties.GRIMWOOD.planks()));
	public static final RegistryObject<Block> GRIMWOOD_PRESSURE_PLATE = HELPER.createBlock("grimwood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AtmosphericProperties.GRIMWOOD.pressurePlate(), AtmosphericProperties.GRIMWOOD_BLOCK_SET));
	public static final RegistryObject<Block> GRIMWOOD_BUTTON = HELPER.createBlock("grimwood_button", () -> new ButtonBlock(AtmosphericProperties.GRIMWOOD.button(), AtmosphericProperties.GRIMWOOD_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> GRIMWOOD_FENCE = HELPER.createFuelBlock("grimwood_fence", () -> new FenceBlock(AtmosphericProperties.GRIMWOOD.planks()), 300);
	public static final RegistryObject<Block> GRIMWOOD_FENCE_GATE = HELPER.createFuelBlock("grimwood_fence_gate", () -> new FenceGateBlock(AtmosphericProperties.GRIMWOOD.planks(), AtmosphericProperties.GRIMWOOD_WOOD_TYPE), 300);
	public static final RegistryObject<Block> GRIMWOOD_DOOR = HELPER.createBlock("grimwood_door", () -> new DoorBlock(AtmosphericProperties.GRIMWOOD.door(), AtmosphericProperties.GRIMWOOD_BLOCK_SET));
	public static final RegistryObject<Block> GRIMWOOD_TRAPDOOR = HELPER.createBlock("grimwood_trapdoor", () -> new TrapDoorBlock(AtmosphericProperties.GRIMWOOD.trapdoor(), AtmosphericProperties.GRIMWOOD_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> GRIMWOOD_SIGNS = HELPER.createSignBlock("grimwood", AtmosphericProperties.GRIMWOOD_WOOD_TYPE, AtmosphericProperties.GRIMWOOD.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> GRIMWOOD_HANGING_SIGNS = HELPER.createHangingSignBlock("grimwood", AtmosphericProperties.GRIMWOOD_WOOD_TYPE, AtmosphericProperties.GRIMWOOD.hangingSign());

	public static final RegistryObject<Block> GRIMWOOD_BOARDS = HELPER.createFuelBlock("grimwood_boards", () -> new RotatedPillarBlock(AtmosphericProperties.GRIMWOOD.planks()), 300);
	public static final RegistryObject<Block> GRIMWOOD_BOOKSHELF = HELPER.createFuelBlock("grimwood_bookshelf", () -> new Block(AtmosphericProperties.GRIMWOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_GRIMWOOD_BOOKSHELF = HELPER.createFuelBlock("chiseled_grimwood_bookshelf", () -> new ChiseledGrimwoodBookShelfBlock(AtmosphericProperties.GRIMWOOD.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> GRIMWOOD_LADDER = HELPER.createFuelBlock("grimwood_ladder", () -> new LadderBlock(AtmosphericProperties.GRIMWOOD.ladder()), 300);
	public static final RegistryObject<Block> GRIMWOOD_BEEHIVE = HELPER.createBlock("grimwood_beehive", () -> new BlueprintBeehiveBlock(AtmosphericProperties.GRIMWOOD.beehive()));
	public static final RegistryObject<Block> GRIMWOOD_LEAF_PILE = HELPER.createBlock("grimwood_leaf_pile", () -> new LeafPileBlock(AtmosphericProperties.GRIMWOOD.leafPile()));
	public static final RegistryObject<BlueprintChestBlock> GRIMWOOD_CHEST = HELPER.createChestBlock("grimwood", AtmosphericProperties.GRIMWOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_GRIMWOOD_CHEST = HELPER.createTrappedChestBlockNamed("grimwood", AtmosphericProperties.GRIMWOOD.chest());

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> CARMINE_BLOCK = HELPER.createBlock("carmine_block", () -> new CarmineBlock(AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CARMINE_SHINGLES = HELPER.createBlock("carmine_shingles", () -> new Block(AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CARMINE_SHINGLE_STAIRS = HELPER.createBlock("carmine_shingle_stairs", () -> new StairBlock(() -> CARMINE_BLOCK.get().defaultBlockState(), AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CARMINE_SHINGLE_SLAB = HELPER.createBlock("carmine_shingle_slab", () -> new SlabBlock(AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CARMINE_SHINGLE_WALL = HELPER.createBlock("carmine_shingle_wall", () -> new WallBlock(AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CHISELED_CARMINE_SHINGLES = HELPER.createBlock("chiseled_carmine_shingles", () -> new Block(AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CARMINE_PAVEMENT = HELPER.createBlock("carmine_pavement", () -> new Block(AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CARMINE_PAVEMENT_STAIRS = HELPER.createBlock("carmine_pavement_stairs", () -> new StairBlock(() -> CARMINE_BLOCK.get().defaultBlockState(), AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CARMINE_PAVEMENT_SLAB = HELPER.createBlock("carmine_pavement_slab", () -> new SlabBlock(AtmosphericProperties.CARMINE_BLOCK));
	public static final RegistryObject<Block> CARMINE_PAVEMENT_WALL = HELPER.createBlock("carmine_pavement_wall", () -> new WallBlock(AtmosphericProperties.CARMINE_BLOCK));

	public static final RegistryObject<Block> DRAGON_ROOTS = HELPER.createBlock("dragon_roots", () -> new DragonRootsBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1.5F).randomTicks().noCollission().sound(SoundType.AZALEA_LEAVES).pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> FIRETHORN = HELPER.createBlock("firethorn", () -> new DesertFlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()));
	public static final RegistryObject<Block> POTTED_FIRETHORN = HELPER.createBlockNoItem("potted_firethorn", () -> new FlowerPotBlock(FIRETHORN.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> FORSYTHIA = HELPER.createBlock("forsythia", () -> new DesertFlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 9, PropertyUtil.flower()));
	public static final RegistryObject<Block> POTTED_FORSYTHIA = HELPER.createBlockNoItem("potted_forsythia", () -> new FlowerPotBlock(FORSYTHIA.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> DRAGON_FRUIT_CRATE = HELPER.createBlock("dragon_fruit_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));
	public static final RegistryObject<Block> GOLDEN_DRAGON_FRUIT_CRATE = HELPER.createBlock("golden_dragon_fruit_crate", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.GOLD).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));

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
						DOLERITE, DOLERITE_STAIRS, DOLERITE_SLAB, DOLERITE_WALL, POLISHED_DOLERITE, POLISHED_DOLERITE_STAIRS, POLISHED_DOLERITE_SLAB
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
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) ? of(ForgeRegistries.ITEMS.getValue(location)) : of(fallback)).test(stack);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(ForgeRegistries.ITEMS.getValue(location)).test(stack));
	}

}
