package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.abnormals_core.common.blocks.*;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsTrappedChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.*;
import com.minecraftabnormals.atmospheric.common.block.*;
import com.minecraftabnormals.atmospheric.common.world.gen.trees.*;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericProperties;
import com.minecraftabnormals.atmospheric.core.registry.helper.AtmosphericBlockSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericBlocks {

	public static final AtmosphericBlockSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getBlockSubHelper();

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> ROSEWOOD_PLANKS = HELPER.createBlock("rosewood_planks", () -> new PlanksBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = HELPER.createBlock("rosewood_sapling", () -> new AbnormalsSaplingBlock(new RosewoodTree(), Block.Properties.copy(Blocks.ACACIA_SAPLING)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD_LOG = HELPER.createBlock("stripped_rosewood_log", () -> new StrippedLogBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LOG = HELPER.createBlock("rosewood_log", () -> new AbnormalsLogBlock(STRIPPED_ROSEWOOD_LOG, Block.Properties.copy(Blocks.OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD = HELPER.createBlock("stripped_rosewood", () -> new StrippedWoodBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD = HELPER.createBlock("rosewood", () -> new WoodBlock(STRIPPED_ROSEWOOD, Block.Properties.copy(Blocks.OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LEAVES = HELPER.createBlock("rosewood_leaves", () -> new AbnormalsLeavesBlock(AtmosphericProperties.createLeaves()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_SLAB = HELPER.createBlock("rosewood_slab", () -> new WoodSlabBlock(Block.Properties.copy(Blocks.OAK_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_STAIRS = HELPER.createBlock("rosewood_stairs", () -> new WoodStairsBlock(ROSEWOOD_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.OAK_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_PRESSURE_PLATE = HELPER.createBlock("rosewood_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_FENCE = HELPER.createFuelBlock("rosewood_fence", () -> new WoodFenceBlock(Block.Properties.copy(Blocks.OAK_FENCE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_TRAPDOOR = HELPER.createBlock("rosewood_trapdoor", () -> new WoodTrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_FENCE_GATE = HELPER.createFuelBlock("rosewood_fence_gate", () -> new WoodFenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE)), 300, ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_BUTTON = HELPER.createBlock("rosewood_button", () -> new AbnormalsWoodButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_DOOR = HELPER.createBlock("rosewood_door", () -> new WoodDoorBlock(Block.Properties.copy(Blocks.OAK_DOOR)), ItemGroup.TAB_REDSTONE);
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> ROSEWOOD_SIGNS = HELPER.createSignBlock("rosewood", MaterialColor.COLOR_PINK);

	public static final RegistryObject<Block> VERTICAL_ROSEWOOD_PLANKS = HELPER.createCompatBlock("quark", "vertical_rosewood_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "rosewood_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 150, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "rosewood_bookshelf", () -> new BookshelfBlock(Block.Properties.copy(Blocks.BOOKSHELF)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LADDER = HELPER.createCompatFuelBlock("quark", "rosewood_ladder", () -> new AbnormalsLadderBlock(Block.Properties.copy(Blocks.LADDER).harvestTool(ToolType.AXE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_LEAF_CARPET = HELPER.createCompatBlock("quark", "rosewood_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.createLeafCarpet()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "rosewood_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD_POST = HELPER.createCompatFuelBlock("quark", "stripped_rosewood_post", () -> new WoodPostBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_POST = HELPER.createCompatFuelBlock("quark", "rosewood_post", () -> new WoodPostBlock(STRIPPED_ROSEWOOD_POST, Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_HEDGE = HELPER.createCompatFuelBlock("quark", "rosewood_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> ROSEWOOD_CHESTS = HELPER.createCompatChestBlocks("quark", "rosewood", MaterialColor.COLOR_PINK);

	public static final RegistryObject<Block> MORADO_PLANKS = HELPER.createBlock("morado_planks", () -> new PlanksBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_SAPLING = HELPER.createBlock("morado_sapling", () -> new AbnormalsSaplingBlock(new MoradoTree(), Block.Properties.copy(Blocks.ACACIA_SAPLING)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_MORADO_LOG = HELPER.createBlock("stripped_morado_log", () -> new StrippedLogBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_LOG = HELPER.createBlock("morado_log", () -> new AbnormalsLogBlock(STRIPPED_MORADO_LOG, Block.Properties.copy(Blocks.OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_MORADO_WOOD = HELPER.createBlock("stripped_morado_wood", () -> new StrippedWoodBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_WOOD = HELPER.createBlock("morado_wood", () -> new WoodBlock(STRIPPED_MORADO_WOOD, Block.Properties.copy(Blocks.OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_LEAVES = HELPER.createBlock("morado_leaves", () -> new AbnormalsLeavesBlock(AtmosphericProperties.createLeaves()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_SLAB = HELPER.createBlock("morado_slab", () -> new WoodSlabBlock(Block.Properties.copy(Blocks.OAK_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_STAIRS = HELPER.createBlock("morado_stairs", () -> new WoodStairsBlock(MORADO_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.OAK_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_PRESSURE_PLATE = HELPER.createBlock("morado_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> MORADO_FENCE = HELPER.createFuelBlock("morado_fence", () -> new WoodFenceBlock(Block.Properties.copy(Blocks.OAK_FENCE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_TRAPDOOR = HELPER.createBlock("morado_trapdoor", () -> new WoodTrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> MORADO_FENCE_GATE = HELPER.createFuelBlock("morado_fence_gate", () -> new WoodFenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE)), 300, ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> MORADO_BUTTON = HELPER.createBlock("morado_button", () -> new AbnormalsWoodButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> MORADO_DOOR = HELPER.createBlock("morado_door", () -> new WoodDoorBlock(Block.Properties.copy(Blocks.OAK_DOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> POTTED_MORADO_SAPLING = HELPER.createBlockNoItem("potted_morado_sapling", () -> new FlowerPotBlock(MORADO_SAPLING.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> MORADO_SIGNS = HELPER.createSignBlock("morado", MaterialColor.TERRACOTTA_RED);

	public static final RegistryObject<Block> VERTICAL_MORADO_PLANKS = HELPER.createCompatBlock("quark", "vertical_morado_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "morado_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 150, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "morado_bookshelf", () -> new BookshelfBlock(Block.Properties.copy(Blocks.BOOKSHELF)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_LADDER = HELPER.createCompatFuelBlock("quark", "morado_ladder", () -> new AbnormalsLadderBlock(Block.Properties.copy(Blocks.LADDER).harvestTool(ToolType.AXE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_LEAF_CARPET = HELPER.createCompatBlock("quark", "morado_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.createLeafCarpet()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> MORADO_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "morado_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_MORADO_POST = HELPER.createCompatFuelBlock("quark", "stripped_morado_post", () -> new WoodPostBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_POST = HELPER.createCompatFuelBlock("quark", "morado_post", () -> new WoodPostBlock(STRIPPED_MORADO_POST, Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MORADO_HEDGE = HELPER.createCompatFuelBlock("quark", "morado_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> FLOWERING_MORADO_HEDGE = HELPER.createCompatFuelBlock("quark", "flowering_morado_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> MORADO_CHESTS = HELPER.createCompatChestBlocks("quark", "morado", MaterialColor.TERRACOTTA_RED);

	public static final RegistryObject<Block> FLOWERING_MORADO_LEAVES = HELPER.createBlock("flowering_morado_leaves", () -> new FloweringMoradoLeavesBlock(AtmosphericProperties.createLeaves()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> FLOWERING_MORADO_LEAF_CARPET = HELPER.createCompatBlock("quark", "flowering_morado_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.createLeafCarpet()), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> PASSION_VINE = HELPER.createBlock("passion_vine", () -> new PassionVineBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.VINE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> PASSION_VINE_BUNDLE = HELPER.createBlock("passion_vine_bundle", () -> new PassionVineBundleBlock(Block.Properties.of(Material.GRASS, MaterialColor.COLOR_GREEN).harvestTool(ToolType.AXE).strength(0.5F, 2.5F).sound(SoundType.GRASS)), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> WATER_HYACINTH = HELPER.createBlockNoItem("water_hyacinth", () -> new WaterHyacinthBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0F).sound(SoundType.VINE)));

	public static final RegistryObject<Block> WARM_WALL_MONKEY_BRUSH = HELPER.createBlockNoItem("warm_wall_monkey_brush", () -> new WallMonkeyBrushBlock(Block.Properties.copy(Blocks.ALLIUM)));
	public static final RegistryObject<Block> HOT_WALL_MONKEY_BRUSH = HELPER.createBlockNoItem("hot_wall_monkey_brush", () -> new WallMonkeyBrushBlock(Block.Properties.copy(Blocks.ALLIUM)));
	public static final RegistryObject<Block> SCALDING_WALL_MONKEY_BRUSH = HELPER.createBlockNoItem("scalding_wall_monkey_brush", () -> new WallMonkeyBrushBlock(Block.Properties.copy(Blocks.ALLIUM)));

	public static final RegistryObject<Block> WARM_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("warm_monkey_brush", () -> new MonkeyBrushBlock(Block.Properties.copy(Blocks.ALLIUM)), WARM_WALL_MONKEY_BRUSH::get, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> HOT_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("hot_monkey_brush", () -> new MonkeyBrushBlock(Block.Properties.copy(Blocks.ALLIUM)), HOT_WALL_MONKEY_BRUSH::get, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> SCALDING_MONKEY_BRUSH = HELPER.createWallOrVerticalBlock("scalding_monkey_brush", () -> new MonkeyBrushBlock(Block.Properties.copy(Blocks.ALLIUM)), SCALDING_WALL_MONKEY_BRUSH::get, ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> POTTED_WARM_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_warm_monkey_brush", () -> new FlowerPotBlock(WARM_MONKEY_BRUSH.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> POTTED_HOT_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_hot_monkey_brush", () -> new FlowerPotBlock(HOT_MONKEY_BRUSH.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> POTTED_SCALDING_MONKEY_BRUSH = HELPER.createBlockNoItem("potted_scalding_monkey_brush", () -> new FlowerPotBlock(SCALDING_MONKEY_BRUSH.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> POTTED_ROSEWOOD_SAPLING = HELPER.createBlockNoItem("potted_rosewood_sapling", () -> new FlowerPotBlock(ROSEWOOD_SAPLING.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> POTTED_WATER_HYACINTH = HELPER.createBlockNoItem("potted_water_hyacinth", () -> new FlowerPotBlock(WATER_HYACINTH.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject<Block> PASSIONFRUIT_CRATE = HELPER.createCompatBlock("quark", "passionfruit_crate", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.WOOD)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> SHIMMERING_PASSIONFRUIT_CRATE = HELPER.createCompatBlock("quark", "shimmering_passionfruit_crate", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.GOLD).lightLevel((state) -> 7).strength(1.5F).sound(SoundType.WOOD)), ItemGroup.TAB_DECORATIONS);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> IVORY_TRAVERTINE = HELPER.createBlock("ivory_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.IVORY_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE = HELPER.createBlock("peach_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.PEACH_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE = HELPER.createBlock("persimmon_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE = HELPER.createBlock("saffron_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.SAFFRON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_IVORY_TRAVERTINE = HELPER.createBlock("chiseled_ivory_travertine", () -> new Block(AtmosphericProperties.IVORY_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_PEACH_TRAVERTINE = HELPER.createBlock("chiseled_peach_travertine", () -> new Block(AtmosphericProperties.PEACH_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_PERSIMMON_TRAVERTINE = HELPER.createBlock("chiseled_persimmon_travertine", () -> new Block(AtmosphericProperties.PERSIMMON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_SAFFRON_TRAVERTINE = HELPER.createBlock("chiseled_saffron_travertine", () -> new Block(AtmosphericProperties.SAFFRON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CUT_IVORY_TRAVERTINE = HELPER.createBlock("cut_ivory_travertine", () -> new Block(AtmosphericProperties.IVORY_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_PEACH_TRAVERTINE = HELPER.createBlock("cut_peach_travertine", () -> new Block(AtmosphericProperties.PEACH_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_PERSIMMON_TRAVERTINE = HELPER.createBlock("cut_persimmon_travertine", () -> new Block(AtmosphericProperties.PERSIMMON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_SAFFRON_TRAVERTINE = HELPER.createBlock("cut_saffron_travertine", () -> new Block(AtmosphericProperties.SAFFRON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_STAIRS = HELPER.createBlock("ivory_travertine_stairs", () -> new AbnormalsStairsBlock(IVORY_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.IVORY_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_STAIRS = HELPER.createBlock("peach_travertine_stairs", () -> new AbnormalsStairsBlock(PEACH_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.PEACH_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_STAIRS = HELPER.createBlock("persimmon_travertine_stairs", () -> new AbnormalsStairsBlock(PERSIMMON_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.PERSIMMON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_STAIRS = HELPER.createBlock("saffron_travertine_stairs", () -> new AbnormalsStairsBlock(SAFFRON_TRAVERTINE.get().defaultBlockState(), AtmosphericProperties.SAFFRON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_SLAB = HELPER.createBlock("ivory_travertine_slab", () -> new SlabBlock(AtmosphericProperties.IVORY_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_SLAB = HELPER.createBlock("peach_travertine_slab", () -> new SlabBlock(AtmosphericProperties.PEACH_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_SLAB = HELPER.createBlock("persimmon_travertine_slab", () -> new SlabBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_SLAB = HELPER.createBlock("saffron_travertine_slab", () -> new SlabBlock(AtmosphericProperties.SAFFRON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_WALL = HELPER.createBlock("ivory_travertine_wall", () -> new WallBlock(AtmosphericProperties.IVORY_TRAVERTINE), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_WALL = HELPER.createBlock("peach_travertine_wall", () -> new WallBlock(AtmosphericProperties.PEACH_TRAVERTINE), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_WALL = HELPER.createBlock("persimmon_travertine_wall", () -> new WallBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_WALL = HELPER.createBlock("saffron_travertine_wall", () -> new WallBlock(AtmosphericProperties.SAFFRON_TRAVERTINE), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "ivory_travertine_vertical_slab", () -> new DirectionalVerticalSlabBlock(AtmosphericProperties.IVORY_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "peach_travertine_vertical_slab", () -> new DirectionalVerticalSlabBlock(AtmosphericProperties.PEACH_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "persimmon_travertine_vertical_slab", () -> new DirectionalVerticalSlabBlock(AtmosphericProperties.PERSIMMON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "saffron_travertine_vertical_slab", () -> new DirectionalVerticalSlabBlock(AtmosphericProperties.SAFFRON_TRAVERTINE), ItemGroup.TAB_BUILDING_BLOCKS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> ARID_SAND = HELPER.createBlock("arid_sand", () -> new AridSandBlock(14406560, AtmosphericProperties.ARID_SAND), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE = HELPER.createBlock("arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_SLAB = HELPER.createBlock("arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_STAIRS = HELPER.createBlock("arid_sandstone_stairs", () -> new AbnormalsStairsBlock(ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SANDSTONE_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_WALL = HELPER.createBlock("arid_sandstone_wall", () -> new WallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL)), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE = HELPER.createBlock("smooth_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_SLAB = HELPER.createBlock("smooth_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_STAIRS = HELPER.createBlock("smooth_arid_sandstone_stairs", () -> new AbnormalsStairsBlock(SMOOTH_ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SMOOTH_SANDSTONE_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CUT_ARID_SANDSTONE = HELPER.createBlock("cut_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.CUT_SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_ARID_SANDSTONE_SLAB = HELPER.createBlock("cut_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.CUT_SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_ARID_SANDSTONE = HELPER.createBlock("chiseled_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.CHISELED_SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> RED_ARID_SAND = HELPER.createBlock("red_arid_sand", () -> new AridSandBlock(16241568, AtmosphericProperties.RED_ARID_SAND), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE = HELPER.createBlock("red_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("red_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_STAIRS = HELPER.createBlock("red_arid_sandstone_stairs", () -> new AbnormalsStairsBlock(RED_ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SANDSTONE_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_WALL = HELPER.createBlock("red_arid_sandstone_wall", () -> new WallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL)), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE = HELPER.createBlock("smooth_red_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("smooth_red_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_STAIRS = HELPER.createBlock("smooth_red_arid_sandstone_stairs", () -> new AbnormalsStairsBlock(SMOOTH_RED_ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SMOOTH_SANDSTONE_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE = HELPER.createBlock("cut_red_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.CUT_SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE_SLAB = HELPER.createBlock("cut_red_arid_sandstone_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.CUT_SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_RED_ARID_SANDSTONE = HELPER.createBlock("chiseled_red_arid_sandstone", () -> new Block(Block.Properties.copy(Blocks.CHISELED_SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRIPPED_YUCCA_LOG = HELPER.createBlock("stripped_yucca_log", () -> new StrippedLogBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LOG = HELPER.createBlock("yucca_log", () -> new AbnormalsLogBlock(STRIPPED_YUCCA_LOG, Block.Properties.copy(Blocks.OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_YUCCA_WOOD = HELPER.createBlock("stripped_yucca_wood", () -> new StrippedWoodBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_WOOD = HELPER.createBlock("yucca_wood", () -> new WoodBlock(STRIPPED_YUCCA_WOOD, Block.Properties.copy(Blocks.OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LEAVES = HELPER.createBlock("yucca_leaves", () -> new YuccaLeavesBlock(AtmosphericProperties.createLeaves()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_PLANKS = HELPER.createBlock("yucca_planks", () -> new PlanksBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_STAIRS = HELPER.createBlock("yucca_stairs", () -> new WoodStairsBlock(YUCCA_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.OAK_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_SLAB = HELPER.createBlock("yucca_slab", () -> new WoodSlabBlock(Block.Properties.copy(Blocks.OAK_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_PRESSURE_PLATE = HELPER.createBlock("yucca_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> YUCCA_FENCE = HELPER.createFuelBlock("yucca_fence", () -> new WoodFenceBlock(Block.Properties.copy(Blocks.OAK_FENCE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_TRAPDOOR = HELPER.createBlock("yucca_trapdoor", () -> new WoodTrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> YUCCA_FENCE_GATE = HELPER.createFuelBlock("yucca_fence_gate", () -> new WoodFenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE)), 300, ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> YUCCA_BUTTON = HELPER.createBlock("yucca_button", () -> new AbnormalsWoodButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> YUCCA_DOOR = HELPER.createBlock("yucca_door", () -> new WoodDoorBlock(Block.Properties.copy(Blocks.OAK_DOOR)), ItemGroup.TAB_REDSTONE);
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> YUCCA_SIGNS = HELPER.createSignBlock("yucca", MaterialColor.TERRACOTTA_ORANGE);

	public static final RegistryObject<Block> YUCCA_SAPLING = HELPER.createBlock("yucca_sapling", () -> new YuccaSaplingBlock(new YuccaTree(), Block.Properties.copy(Blocks.ACACIA_SAPLING)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_YUCCA_SAPLING = HELPER.createBlockNoItem("potted_yucca_sapling", () -> new FlowerPotBlock(YUCCA_SAPLING.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject<Block> YUCCA_BRANCH = HELPER.createBlock("yucca_branch", () -> new YuccaBranchBlock(Block.Properties.copy(Blocks.MELON_STEM).sound(SoundType.CROP).randomTicks()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_BUNDLE = HELPER.createBlock("yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.copy(Blocks.MELON).randomTicks().harvestTool(ToolType.HOE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROASTED_YUCCA_BUNDLE = HELPER.createBlock("roasted_yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.copy(Blocks.MELON).randomTicks().harvestTool(ToolType.HOE)), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> YUCCA_GATEAU = HELPER.createBlockNoItem("yucca_gateau", () -> new YuccaGateauBlock(Block.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> YUCCA_FLOWER = HELPER.createBlock("yucca_flower", () -> new YuccaFlowerBlock(AtmosphericEffects.PERSISTENCE::get, 15, Block.Properties.copy(Blocks.POPPY)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_YUCCA_FLOWER = HELPER.createBlockNoItem("potted_yucca_flower", () -> new FlowerPotBlock(YUCCA_FLOWER.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> TALL_YUCCA_FLOWER = HELPER.createBlock("tall_yucca_flower", () -> new YuccaFlowerDoubleBlock(Block.Properties.copy(Blocks.ROSE_BUSH)), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> GILIA = HELPER.createBlock("gilia", () -> new GiliaBlock(() -> Effects.SLOW_FALLING, 9, Block.Properties.copy(Blocks.POPPY)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_GILIA = HELPER.createBlockNoItem("potted_gilia", () -> new FlowerPotBlock(GILIA.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject<Block> ARID_SPROUTS = HELPER.createBlock("arid_sprouts", () -> new AridSproutsBlock(AtmosphericProperties.ARID_SPROUTS), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ALOE_VERA = HELPER.createBlockNoItem("aloe_vera", () -> new AloeVeraBlock(AtmosphericProperties.ALOE_VERA));
	public static final RegistryObject<Block> TALL_ALOE_VERA = HELPER.createBlockNoItem("tall_aloe_vera", () -> new AloeVeraTallBlock(AtmosphericProperties.ALOE_VERA));
	public static final RegistryObject<Block> ALOE_BUNDLE = HELPER.createBlock("aloe_bundle", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.DRIED_KELP_BLOCK).harvestTool(ToolType.HOE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ALOE_GEL_BLOCK = HELPER.createBlock("aloe_gel_block", () -> new AloeGelBlock(Block.Properties.copy(Blocks.SLIME_BLOCK)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> BARREL_CACTUS = HELPER.createBlockNoItem("barrel_cactus", () -> new BarrelCactusBlock(Block.Properties.copy(Blocks.CACTUS)));
	public static final RegistryObject<Block> POTTED_BARREL_CACTUS = HELPER.createBlockNoItem("potted_barrel_cactus", () -> new FlowerPotBlock(BARREL_CACTUS.get(), Block.Properties.copy(Blocks.POTTED_CACTUS)));

	public static final RegistryObject<Block> ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "cut_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "smooth_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> ARID_SANDSTONE_BRICKS = HELPER.createCompatBlock("quark", "arid_sandstone_bricks", () -> new Block(Block.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_SLAB = HELPER.createCompatBlock("quark", "arid_sandstone_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_STAIRS = HELPER.createCompatBlock("quark", "arid_sandstone_brick_stairs", () -> new AbnormalsStairsBlock(ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SANDSTONE_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_WALL = HELPER.createCompatBlock("quark", "arid_sandstone_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "arid_sandstone_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> RED_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "red_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "cut_red_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "smooth_red_arid_sandstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICKS = HELPER.createCompatBlock("quark", "red_arid_sandstone_bricks", () -> new Block(Block.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_SLAB = HELPER.createCompatBlock("quark", "red_arid_sandstone_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_STAIRS = HELPER.createCompatBlock("quark", "red_arid_sandstone_brick_stairs", () -> new AbnormalsStairsBlock(RED_ARID_SANDSTONE.get().defaultBlockState(), Block.Properties.copy(Blocks.SANDSTONE_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_WALL = HELPER.createCompatBlock("quark", "red_arid_sandstone_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "red_arid_sandstone_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SANDSTONE_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> VERTICAL_YUCCA_PLANKS = HELPER.createCompatBlock("quark", "vertical_yucca_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "yucca_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 150, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "yucca_bookshelf", () -> new BookshelfBlock(Block.Properties.copy(Blocks.BOOKSHELF)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LADDER = HELPER.createCompatFuelBlock("quark", "yucca_ladder", () -> new AbnormalsLadderBlock(Block.Properties.copy(Blocks.LADDER).harvestTool(ToolType.AXE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_LEAF_CARPET = HELPER.createCompatBlock("quark", "yucca_leaf_carpet", () -> new YuccaLeafCarpetBlock(AtmosphericProperties.createLeafCarpet()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> YUCCA_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "yucca_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_YUCCA_POST = HELPER.createCompatFuelBlock("quark", "stripped_yucca_post", () -> new WoodPostBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_POST = HELPER.createCompatFuelBlock("quark", "yucca_post", () -> new WoodPostBlock(STRIPPED_YUCCA_POST, Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_HEDGE = HELPER.createCompatFuelBlock("quark", "yucca_hedge", () -> new YuccaHedgeBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> YUCCA_CHESTS = HELPER.createCompatChestBlocks("quark", "yucca", MaterialColor.COLOR_ORANGE);

	public static final RegistryObject<Block> YUCCA_CASK = HELPER.createCompatBlock("quark", "yucca_cask", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(1.5F).sound(SoundType.WOOD)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROASTED_YUCCA_CASK = HELPER.createCompatBlock("quark", "roasted_yucca_cask", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(1.5F).sound(SoundType.WOOD)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> BARREL_CACTUS_BATCH = HELPER.createCompatBlock("quark", "barrel_cactus_batch", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(0.5F).sound(SoundType.WOOD)), ItemGroup.TAB_DECORATIONS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> KOUSA_PLANKS = HELPER.createBlock("kousa_planks", () -> new PlanksBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_SAPLING = HELPER.createBlock("kousa_sapling", () -> new AbnormalsSaplingBlock(new KousaTree(), Block.Properties.copy(Blocks.ACACIA_SAPLING)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_KOUSA_LOG = HELPER.createBlock("stripped_kousa_log", () -> new StrippedLogBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LOG = HELPER.createBlock("kousa_log", () -> new AbnormalsLogBlock(STRIPPED_KOUSA_LOG, Block.Properties.copy(Blocks.OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_KOUSA_WOOD = HELPER.createBlock("stripped_kousa_wood", () -> new StrippedWoodBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_WOOD = HELPER.createBlock("kousa_wood", () -> new WoodBlock(STRIPPED_KOUSA_WOOD, Block.Properties.copy(Blocks.OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LEAVES = HELPER.createBlock("kousa_leaves", () -> new AbnormalsLeavesBlock(AtmosphericProperties.createLeaves()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_SLAB = HELPER.createBlock("kousa_slab", () -> new WoodSlabBlock(Block.Properties.copy(Blocks.OAK_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_STAIRS = HELPER.createBlock("kousa_stairs", () -> new WoodStairsBlock(KOUSA_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.OAK_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_PRESSURE_PLATE = HELPER.createBlock("kousa_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> KOUSA_FENCE = HELPER.createFuelBlock("kousa_fence", () -> new WoodFenceBlock(Block.Properties.copy(Blocks.OAK_FENCE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_TRAPDOOR = HELPER.createBlock("kousa_trapdoor", () -> new WoodTrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> KOUSA_FENCE_GATE = HELPER.createFuelBlock("kousa_fence_gate", () -> new WoodFenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE)), 300, ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> KOUSA_BUTTON = HELPER.createBlock("kousa_button", () -> new AbnormalsWoodButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> KOUSA_DOOR = HELPER.createBlock("kousa_door", () -> new WoodDoorBlock(Block.Properties.copy(Blocks.OAK_DOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> POTTED_KOUSA_SAPLING = HELPER.createBlockNoItem("potted_kousa_sapling", () -> new FlowerPotBlock(KOUSA_SAPLING.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> KOUSA_SIGNS = HELPER.createSignBlock("kousa", MaterialColor.COLOR_GREEN);

	public static final RegistryObject<Block> VERTICAL_KOUSA_PLANKS = HELPER.createCompatBlock("quark", "vertical_kousa_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "kousa_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 150, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "kousa_bookshelf", () -> new BookshelfBlock(Block.Properties.copy(Blocks.BOOKSHELF)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LADDER = HELPER.createCompatFuelBlock("quark", "kousa_ladder", () -> new AbnormalsLadderBlock(Block.Properties.copy(Blocks.LADDER).harvestTool(ToolType.AXE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_LEAF_CARPET = HELPER.createCompatBlock("quark", "kousa_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.createLeafCarpet()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> KOUSA_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "kousa_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_KOUSA_POST = HELPER.createCompatFuelBlock("quark", "stripped_kousa_post", () -> new WoodPostBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_POST = HELPER.createCompatFuelBlock("quark", "kousa_post", () -> new WoodPostBlock(STRIPPED_KOUSA_POST, Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_HEDGE = HELPER.createCompatFuelBlock("quark", "kousa_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> KOUSA_CHESTS = HELPER.createCompatChestBlocks("quark", "kousa", MaterialColor.COLOR_GREEN);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> ASPEN_PLANKS = HELPER.createBlock("aspen_planks", () -> new PlanksBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_SAPLING = HELPER.createBlock("aspen_sapling", () -> new AbnormalsSaplingBlock(new AspenTree(), Block.Properties.copy(Blocks.ACACIA_SAPLING)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_ASPEN_LOG = HELPER.createBlock("stripped_aspen_log", () -> new StrippedLogBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LOG = HELPER.createBlock("aspen_log", () -> new AbnormalsLogBlock(STRIPPED_ASPEN_LOG, Block.Properties.copy(Blocks.OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> WATCHFUL_ASPEN_LOG = HELPER.createBlock("watchful_aspen_log", () -> new AbnormalsLogBlock(STRIPPED_ASPEN_LOG, Block.Properties.copy(Blocks.OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ASPEN_WOOD = HELPER.createBlock("stripped_aspen_wood", () -> new StrippedWoodBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_WOOD = HELPER.createBlock("aspen_wood", () -> new WoodBlock(STRIPPED_ASPEN_WOOD, Block.Properties.copy(Blocks.OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> WATCHFUL_ASPEN_WOOD = HELPER.createBlock("watchful_aspen_wood", () -> new WoodBlock(STRIPPED_ASPEN_WOOD, Block.Properties.copy(Blocks.OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LEAVES = HELPER.createBlock("aspen_leaves", () -> new AbnormalsLeavesBlock(AtmosphericProperties.createLeaves()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_SLAB = HELPER.createBlock("aspen_slab", () -> new WoodSlabBlock(Block.Properties.copy(Blocks.OAK_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_STAIRS = HELPER.createBlock("aspen_stairs", () -> new WoodStairsBlock(ASPEN_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.OAK_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_PRESSURE_PLATE = HELPER.createBlock("aspen_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> ASPEN_FENCE = HELPER.createFuelBlock("aspen_fence", () -> new WoodFenceBlock(Block.Properties.copy(Blocks.OAK_FENCE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_TRAPDOOR = HELPER.createBlock("aspen_trapdoor", () -> new WoodTrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> ASPEN_FENCE_GATE = HELPER.createFuelBlock("aspen_fence_gate", () -> new WoodFenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE)), 300, ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> ASPEN_BUTTON = HELPER.createBlock("aspen_button", () -> new AbnormalsWoodButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> ASPEN_DOOR = HELPER.createBlock("aspen_door", () -> new WoodDoorBlock(Block.Properties.copy(Blocks.OAK_DOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> POTTED_ASPEN_SAPLING = HELPER.createBlockNoItem("potted_aspen_sapling", () -> new FlowerPotBlock(ASPEN_SAPLING.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> ASPEN_SIGNS = HELPER.createSignBlock("aspen", MaterialColor.COLOR_YELLOW);

	public static final RegistryObject<Block> VERTICAL_ASPEN_PLANKS = HELPER.createCompatBlock("quark", "vertical_aspen_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "aspen_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 150, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "aspen_bookshelf", () -> new BookshelfBlock(Block.Properties.copy(Blocks.BOOKSHELF)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LADDER = HELPER.createCompatFuelBlock("quark", "aspen_ladder", () -> new AbnormalsLadderBlock(Block.Properties.copy(Blocks.LADDER).harvestTool(ToolType.AXE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_LEAF_CARPET = HELPER.createCompatBlock("quark", "aspen_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.createLeafCarpet()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ASPEN_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "aspen_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_ASPEN_POST = HELPER.createCompatFuelBlock("quark", "stripped_aspen_post", () -> new WoodPostBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_POST = HELPER.createCompatFuelBlock("quark", "aspen_post", () -> new WoodPostBlock(STRIPPED_ASPEN_POST, Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_HEDGE = HELPER.createCompatFuelBlock("quark", "aspen_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> ASPEN_CHESTS = HELPER.createCompatChestBlocks("quark", "aspen", MaterialColor.COLOR_YELLOW);

	public static final RegistryObject<Block> CRUSTOSE = HELPER.createBlock("crustose", () -> new CrustoseBlock(Block.Properties.copy(Blocks.DIRT).randomTicks()), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRUSTOSE_PATH = HELPER.createBlock("crustose_path", () -> new GrassPathBlock(AtmosphericProperties.CRUSTOSE_PATH), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> CRUSTOSE_LOG = HELPER.createBlock("crustose_log", () -> new CrustoseLogBlock(ASPEN_LOG::get, Block.Properties.copy(Blocks.OAK_LOG).randomTicks()), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRUSTOSE_WOOD = HELPER.createBlock("crustose_wood", () -> new CrustoseLogBlock(ASPEN_WOOD::get, Block.Properties.copy(Blocks.OAK_LOG).randomTicks()), ItemGroup.TAB_BUILDING_BLOCKS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final RegistryObject<Block> GRIMWOOD_PLANKS = HELPER.createBlock("grimwood_planks", () -> new PlanksBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_SAPLING = HELPER.createBlock("grimwood_sapling", () -> new AbnormalsSaplingBlock(new GrimwoodTree(), Block.Properties.copy(Blocks.ACACIA_SAPLING)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_GRIMWOOD_LOG = HELPER.createBlock("stripped_grimwood_log", () -> new StrippedLogBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_LOG = HELPER.createBlock("grimwood_log", () -> new AbnormalsLogBlock(STRIPPED_GRIMWOOD_LOG, Block.Properties.copy(Blocks.OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_GRIMWOOD = HELPER.createBlock("stripped_grimwood", () -> new StrippedWoodBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD = HELPER.createBlock("grimwood", () -> new WoodBlock(STRIPPED_GRIMWOOD, Block.Properties.copy(Blocks.OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_LEAVES = HELPER.createBlock("grimwood_leaves", () -> new AbnormalsLeavesBlock(AtmosphericProperties.createLeaves()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_SLAB = HELPER.createBlock("grimwood_slab", () -> new WoodSlabBlock(Block.Properties.copy(Blocks.OAK_SLAB)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_STAIRS = HELPER.createBlock("grimwood_stairs", () -> new WoodStairsBlock(GRIMWOOD_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.OAK_STAIRS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_PRESSURE_PLATE = HELPER.createBlock("grimwood_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> GRIMWOOD_FENCE = HELPER.createFuelBlock("grimwood_fence", () -> new WoodFenceBlock(Block.Properties.copy(Blocks.OAK_FENCE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_TRAPDOOR = HELPER.createBlock("grimwood_trapdoor", () -> new WoodTrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> GRIMWOOD_FENCE_GATE = HELPER.createFuelBlock("grimwood_fence_gate", () -> new WoodFenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE)), 300, ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> GRIMWOOD_BUTTON = HELPER.createBlock("grimwood_button", () -> new AbnormalsWoodButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON)), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> GRIMWOOD_DOOR = HELPER.createBlock("grimwood_door", () -> new WoodDoorBlock(Block.Properties.copy(Blocks.OAK_DOOR)), ItemGroup.TAB_REDSTONE);
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> GRIMWOOD_SIGNS = HELPER.createSignBlock("grimwood", MaterialColor.COLOR_PINK);
	public static final RegistryObject<Block> POTTED_GRIMWOOD_SAPLING = HELPER.createBlockNoItem("potted_grimwood_sapling", () -> new FlowerPotBlock(GRIMWOOD_SAPLING.get(), Block.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject<Block> VERTICAL_GRIMWOOD_PLANKS = HELPER.createCompatBlock("quark", "vertical_grimwood_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "grimwood_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 150, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "grimwood_bookshelf", () -> new BookshelfBlock(Block.Properties.copy(Blocks.BOOKSHELF)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_LADDER = HELPER.createCompatFuelBlock("quark", "grimwood_ladder", () -> new AbnormalsLadderBlock(Block.Properties.copy(Blocks.LADDER).harvestTool(ToolType.AXE)), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_LEAF_CARPET = HELPER.createCompatBlock("quark", "grimwood_leaf_carpet", () -> new LeafCarpetBlock(AtmosphericProperties.createLeafCarpet()), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> GRIMWOOD_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "grimwood_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_GRIMWOOD_POST = HELPER.createCompatFuelBlock("quark", "stripped_grimwood_post", () -> new WoodPostBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_POST = HELPER.createCompatFuelBlock("quark", "grimwood_post", () -> new WoodPostBlock(STRIPPED_GRIMWOOD_POST, Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GRIMWOOD_HEDGE = HELPER.createCompatFuelBlock("quark", "grimwood_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> GRIMWOOD_CHESTS = HELPER.createCompatChestBlocks("quark", "grimwood", MaterialColor.TERRACOTTA_BLACK);
}
