package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.block.AridSandBlock;
import com.bagel.atmospheric.common.block.AtmosphericSaplingBlock;
import com.bagel.atmospheric.common.block.BarrelCactusBlock;
import com.bagel.atmospheric.common.block.BookshelfBlock;
import com.bagel.atmospheric.common.block.CrustoseBlock;
import com.bagel.atmospheric.common.block.CrustoseLogBlock;
import com.bagel.atmospheric.common.block.DirectionalVerticalSlabBlock;
import com.bagel.atmospheric.common.block.GiliaBlock;
import com.bagel.atmospheric.common.block.LeafCarpetBlock;
import com.bagel.atmospheric.common.block.MonkeyBrushBlock;
import com.bagel.atmospheric.common.block.PassionVineBlock;
import com.bagel.atmospheric.common.block.PassionVineBundleBlock;
import com.bagel.atmospheric.common.block.VerticalSlabBlock;
import com.bagel.atmospheric.common.block.YuccaBundleBlock;
import com.bagel.atmospheric.common.block.YuccaFlowerBlock;
import com.bagel.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.bagel.atmospheric.common.block.YuccaGateauBlock;
import com.bagel.atmospheric.common.block.YuccaSaplingBlock;
import com.bagel.atmospheric.common.block.trees.AspenTree;
import com.bagel.atmospheric.common.block.trees.KousaTree;
import com.bagel.atmospheric.common.block.trees.RosewoodTree;
import com.bagel.atmospheric.common.block.trees.YuccaTree;
import com.bagel.atmospheric.core.Atmospheric;
import com.bagel.atmospheric.core.data.AtmosphericProperties;
import com.bagel.atmospheric.core.util.RegistryUtils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.MagmaBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Atmospheric.MODID);
	
	//blocks
	public static final RegistryObject<Block> ROSEWOOD_PLANKS 			= RegistryUtils.createBlock("rosewood_planks", 			() -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_SAPLING 			= RegistryUtils.createBlock("rosewood_sapling", 		() -> new AtmosphericSaplingBlock(new RosewoodTree(), Block.Properties.from(Blocks.ACACIA_SAPLING)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_LOG 				= RegistryUtils.createBlock("rosewood_log", 			() -> new LogBlock(null, Block.Properties.from(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD_LOG 	= RegistryUtils.createBlock("stripped_rosewood_log", 	() -> new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD 					= RegistryUtils.createBlock("rosewood", 				() -> new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD 		= RegistryUtils.createBlock("stripped_rosewood", 		() -> new RotatedPillarBlock(Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LEAVES 			= RegistryUtils.createBlock("rosewood_leaves", 			() -> new LeavesBlock(Block.Properties.from(Blocks.ACACIA_LEAVES)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_SLAB 			= RegistryUtils.createBlock("rosewood_slab", 			() -> new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_STAIRS 			= RegistryUtils.createBlock("rosewood_stairs", 			() -> new StairsBlock(ROSEWOOD_PLANKS.get().getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_PRESSURE_PLATE 	= RegistryUtils.createBlock("rosewood_pressure_plate", 	() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_FENCE 			= RegistryUtils.createBlock("rosewood_fence", 			() -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_TRAPDOOR 		= RegistryUtils.createBlock("rosewood_trapdoor", 		() -> new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_FENCE_GATE 		= RegistryUtils.createBlock("rosewood_fence_gate", 		() -> new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_BUTTON 			= RegistryUtils.createBlock("rosewood_button", 			() -> new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_DOOR 			= RegistryUtils.createBlock("rosewood_door", 			() -> new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
	
	public static final RegistryObject<Block> PASSION_VINE 				= RegistryUtils.createBlock("passion_vine", 			() -> new PassionVineBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.PLANT)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PASSION_VINE_BUNDLE		= RegistryUtils.createBlock("passion_vine_bundle", 		() -> new PassionVineBundleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.GREEN).harvestTool(ToolType.AXE).hardnessAndResistance(0.5F, 2.5F).sound(SoundType.PLANT)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> MONKEY_BRUSH 				= RegistryUtils.createBlock("monkey_brush", 			() -> new MonkeyBrushBlock(Effects.ABSORPTION, 6, Block.Properties.from(Blocks.ALLIUM)), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> POTTED_MONKEY_BRUSH		= RegistryUtils.createBlockNoItem("potted_monkey_brush",		() -> new FlowerPotBlock(MONKEY_BRUSH.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> POTTED_ROSEWOOD_SAPLING	= RegistryUtils.createBlockNoItem("potted_rosewood_sapling",	() -> new FlowerPotBlock(ROSEWOOD_SAPLING.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));

	//quark compatability
	public static final RegistryObject<Block> VERTICAL_ROSEWOOD_PLANKS 	= RegistryUtils.createBlockCompat("quark", "vertical_rosewood_planks", () -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_VERTICAL_SLAB 	= RegistryUtils.createBlockCompat("quark", "rosewood_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_BOOKSHELF 		= RegistryUtils.createBlockCompat("quark", "rosewood_bookshelf", 		() -> new BookshelfBlock(Block.Properties.from(Blocks.BOOKSHELF)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LADDER 			= RegistryUtils.createBlockCompat("quark", "rosewood_ladder", 			() -> new LadderBlock(Block.Properties.from(Blocks.LADDER).harvestTool(ToolType.AXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_LEAF_CARPET 		= RegistryUtils.createBlockCompat("quark", "rosewood_leaf_carpet", 	() -> new LeafCarpetBlock(Block.Properties.from(ROSEWOOD_LEAVES.get())), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> PASSIONFRUIT_CRATE				= RegistryUtils.createBlockCompat("quark", "passionfruit_crate", 				() -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SHIMMERING_PASSIONFRUIT_CRATE		= RegistryUtils.createBlockCompat("quark", "shimmering_passionfruit_crate", 	() -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GOLD).lightValue(7).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), ItemGroup.DECORATIONS);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static final RegistryObject<Block> IVORY_TRAVERTINE 		= RegistryUtils.createBlock("ivory_travertine", 	() -> new RotatedPillarBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE 		= RegistryUtils.createBlock("peach_travertine", 	() -> new RotatedPillarBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE 	= RegistryUtils.createBlock("persimmon_travertine", () -> new RotatedPillarBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE 	= RegistryUtils.createBlock("saffron_travertine", 	() -> new RotatedPillarBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	
	public static final RegistryObject<Block> CHISELED_IVORY_TRAVERTINE 	= RegistryUtils.createBlock("chiseled_ivory_travertine", 		() -> new Block(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_PEACH_TRAVERTINE 	= RegistryUtils.createBlock("chiseled_peach_travertine", 		() -> new Block(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_PERSIMMON_TRAVERTINE = RegistryUtils.createBlock("chiseled_persimmon_travertine", 	() -> new Block(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_SAFFRON_TRAVERTINE 	= RegistryUtils.createBlock("chiseled_saffron_travertine", 		() -> new Block(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	
	public static final RegistryObject<Block> CUT_IVORY_TRAVERTINE 		= RegistryUtils.createBlock("cut_ivory_travertine", 	() -> new Block(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_PEACH_TRAVERTINE 		= RegistryUtils.createBlock("cut_peach_travertine", 	() -> new Block(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_PERSIMMON_TRAVERTINE 	= RegistryUtils.createBlock("cut_persimmon_travertine", () -> new Block(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_SAFFRON_TRAVERTINE 	= RegistryUtils.createBlock("cut_saffron_travertine", 	() -> new Block(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> IVORY_TRAVERTINE_STAIRS 		= RegistryUtils.createBlock("ivory_travertine_stairs", 		() -> new StairsBlock(IVORY_TRAVERTINE.get().getDefaultState(),AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_STAIRS 		= RegistryUtils.createBlock("peach_travertine_stairs", 		() -> new StairsBlock(PEACH_TRAVERTINE.get().getDefaultState(), AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_STAIRS 	= RegistryUtils.createBlock("persimmon_travertine_stairs", 	() -> new StairsBlock(PERSIMMON_TRAVERTINE.get().getDefaultState(), AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_STAIRS 	= RegistryUtils.createBlock("saffron_travertine_stairs", 	() -> new StairsBlock(SAFFRON_TRAVERTINE.get().getDefaultState(), AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	
	public static final RegistryObject<Block> IVORY_TRAVERTINE_SLAB 	= RegistryUtils.createBlock("ivory_travertine_slab", 	() -> new SlabBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_SLAB 	= RegistryUtils.createBlock("peach_travertine_slab", 	() -> new SlabBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_SLAB = RegistryUtils.createBlock("persimmon_travertine_slab",() -> new SlabBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_SLAB 	= RegistryUtils.createBlock("saffron_travertine_slab",	() -> new SlabBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	
	public static final RegistryObject<Block> IVORY_TRAVERTINE_WALL 	= RegistryUtils.createBlock("ivory_travertine_wall", 	() -> new WallBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_WALL 	= RegistryUtils.createBlock("peach_travertine_wall", 	() -> new WallBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_WALL	= RegistryUtils.createBlock("persimmon_travertine_wall",() -> new WallBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_WALL 	= RegistryUtils.createBlock("saffron_travertine_wall", 	() -> new WallBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> IVORY_FUMAROLE_BLOCK 		= RegistryUtils.createBlock("ivory_fumarole_block", 	() -> new MagmaBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_FUMAROLE_BLOCK 		= RegistryUtils.createBlock("peach_fumarole_block", 	() -> new MagmaBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_FUMAROLE_BLOCK 	= RegistryUtils.createBlock("persimmon_fumarole_block",() -> new MagmaBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_FUMAROLE_BLOCK 	= RegistryUtils.createBlock("saffron_fumarole_block", 	() -> new MagmaBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	
	//quark compatability
	public static final RegistryObject<Block> IVORY_TRAVERTINE_VERTICAL_SLAB 		= RegistryUtils.createBlockCompat("quark", "ivory_travertine_vertical_slab", 		() -> new DirectionalVerticalSlabBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PEACH_TRAVERTINE_VERTICAL_SLAB 		= RegistryUtils.createBlockCompat("quark", "peach_travertine_vertical_slab", 		() -> new DirectionalVerticalSlabBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PERSIMMON_TRAVERTINE_VERTICAL_SLAB 	= RegistryUtils.createBlockCompat("quark", "persimmon_travertine_vertical_slab", 	() -> new DirectionalVerticalSlabBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SAFFRON_TRAVERTINE_VERTICAL_SLAB 		= RegistryUtils.createBlockCompat("quark", "saffron_travertine_vertical_slab", 		() -> new DirectionalVerticalSlabBlock(AtmosphericProperties.TRAVERTINE), ItemGroup.BUILDING_BLOCKS);
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//blocks
	public static final RegistryObject<Block> ARID_SAND 					= RegistryUtils.createBlock("arid_sand", 					() -> new AridSandBlock(14406560, Block.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE 				= RegistryUtils.createBlock("arid_sandstone", 				() -> new Block(Block.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_SLAB 			= RegistryUtils.createBlock("arid_sandstone_slab", 			() -> new SlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_STAIRS 		= RegistryUtils.createBlock("arid_sandstone_stairs", 		() -> new StairsBlock(ARID_SANDSTONE.get().getDefaultState(), Block.Properties.from(Blocks.SANDSTONE_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_WALL 			= RegistryUtils.createBlock("arid_sandstone_wall", 			() -> new WallBlock(Block.Properties.from(Blocks.SANDSTONE_WALL)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE 		= RegistryUtils.createBlock("smooth_arid_sandstone", 		() -> new Block(Block.Properties.from(Blocks.SMOOTH_SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_SLAB 	= RegistryUtils.createBlock("smooth_arid_sandstone_slab", 	() -> new SlabBlock(Block.Properties.from(Blocks.SMOOTH_SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_STAIRS 	= RegistryUtils.createBlock("smooth_arid_sandstone_stairs", () -> new StairsBlock(SMOOTH_ARID_SANDSTONE.get().getDefaultState(), Block.Properties.from(Blocks.SMOOTH_SANDSTONE_STAIRS)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CUT_ARID_SANDSTONE 			= RegistryUtils.createBlock("cut_arid_sandstone", 			() -> new Block(Block.Properties.from(Blocks.CUT_SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_ARID_SANDSTONE_SLAB 		= RegistryUtils.createBlock("cut_arid_sandstone_slab", 		() -> new SlabBlock(Block.Properties.from(Blocks.CUT_SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_ARID_SANDSTONE 		= RegistryUtils.createBlock("chiseled_arid_sandstone", 		() -> new Block(Block.Properties.from(Blocks.CHISELED_SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
	
	public static final RegistryObject<Block> RED_ARID_SAND 					= RegistryUtils.createBlock("red_arid_sand", 					() -> new AridSandBlock(14406560, Block.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE 				= RegistryUtils.createBlock("red_arid_sandstone", 				() -> new Block(Block.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_SLAB 			= RegistryUtils.createBlock("red_arid_sandstone_slab", 			() -> new SlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_STAIRS 		= RegistryUtils.createBlock("red_arid_sandstone_stairs", 		() -> new StairsBlock(RED_ARID_SANDSTONE.get().getDefaultState(), Block.Properties.from(Blocks.SANDSTONE_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_WALL 			= RegistryUtils.createBlock("red_arid_sandstone_wall", 			() -> new WallBlock(Block.Properties.from(Blocks.SANDSTONE_WALL)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE 		= RegistryUtils.createBlock("smooth_red_arid_sandstone", 		() -> new Block(Block.Properties.from(Blocks.SMOOTH_SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_SLAB 	= RegistryUtils.createBlock("smooth_red_arid_sandstone_slab", 	() -> new SlabBlock(Block.Properties.from(Blocks.SMOOTH_SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_STAIRS 	= RegistryUtils.createBlock("smooth_red_arid_sandstone_stairs", () -> new StairsBlock(SMOOTH_RED_ARID_SANDSTONE.get().getDefaultState(), Block.Properties.from(Blocks.SMOOTH_SANDSTONE_STAIRS)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE 			= RegistryUtils.createBlock("cut_red_arid_sandstone", 			() -> new Block(Block.Properties.from(Blocks.CUT_SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE_SLAB 		= RegistryUtils.createBlock("cut_red_arid_sandstone_slab", 		() -> new SlabBlock(Block.Properties.from(Blocks.CUT_SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_RED_ARID_SANDSTONE 		= RegistryUtils.createBlock("chiseled_red_arid_sandstone", 		() -> new Block(Block.Properties.from(Blocks.CHISELED_SANDSTONE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> YUCCA_LOG 			= RegistryUtils.createBlock("yucca_log", 			() -> new LogBlock(null, Block.Properties.from(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_YUCCA_LOG 	= RegistryUtils.createBlock("stripped_yucca_log", 	() -> new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_WOOD 			= RegistryUtils.createBlock("yucca_wood",            () -> new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_YUCCA_WOOD	= RegistryUtils.createBlock("stripped_yucca_wood",   () -> new RotatedPillarBlock(Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LEAVES 			= RegistryUtils.createBlock("yucca_leaves", 			() -> new LeavesBlock(Block.Properties.from(Blocks.ACACIA_LEAVES)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YUCCA_PLANKS 			= RegistryUtils.createBlock("yucca_planks", 			() -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_STAIRS 			= RegistryUtils.createBlock("yucca_stairs", 			() -> new StairsBlock(YUCCA_PLANKS.get().getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_SLAB 			= RegistryUtils.createBlock("yucca_slab", 			() -> new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_PRESSURE_PLATE 	= RegistryUtils.createBlock("yucca_pressure_plate", 	() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> YUCCA_FENCE 			= RegistryUtils.createBlock("yucca_fence", 			() -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YUCCA_TRAPDOOR 		= RegistryUtils.createBlock("yucca_trapdoor", 		() -> new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> YUCCA_FENCE_GATE 		= RegistryUtils.createBlock("yucca_fence_gate", 		() -> new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> YUCCA_BUTTON 			= RegistryUtils.createBlock("yucca_button", 			() -> new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> YUCCA_DOOR 			= RegistryUtils.createBlock("yucca_door", 			() -> new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);

	public static final RegistryObject<Block> YUCCA_SAPLING 		= RegistryUtils.createBlock("yucca_sapling", 		() -> new YuccaSaplingBlock(new YuccaTree(), Block.Properties.from(Blocks.ACACIA_SAPLING)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POTTED_YUCCA_SAPLING	= RegistryUtils.createBlockNoItem("potted_yucca_sapling",	() -> new FlowerPotBlock(YUCCA_SAPLING.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject<Block> YUCCA_BUNDLE 			= RegistryUtils.createBlock("yucca_bundle", 	 	() -> new YuccaBundleBlock(Block.Properties.from(Blocks.MELON).tickRandomly()), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROASTED_YUCCA_BUNDLE	= RegistryUtils.createBlock("roasted_yucca_bundle", () -> new YuccaBundleBlock(Block.Properties.from(Blocks.MELON).tickRandomly()), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> YUCCA_GATEAU 			= RegistryUtils.createBlockNoItem("yucca_gateau", 	 () -> new YuccaGateauBlock(Block.Properties.from(Blocks.CAKE)));
	public static final RegistryObject<Block> YUCCA_FLOWER 			= RegistryUtils.createBlock("yucca_flower", 	 () -> new YuccaFlowerBlock(Effects.BAD_OMEN, 6, Block.Properties.from(Blocks.POPPY)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POTTED_YUCCA_FLOWER	= RegistryUtils.createBlockNoItem("potted_yucca_flower",	() -> new FlowerPotBlock(YUCCA_FLOWER.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> TALL_YUCCA_FLOWER 	= RegistryUtils.createBlock("tall_yucca_flower", () -> new YuccaFlowerDoubleBlock(Block.Properties.from(Blocks.POPPY)), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> GILIA 		= RegistryUtils.createBlock("gilia", 	 () -> new GiliaBlock(Effects.SPEED, 9, Block.Properties.from(Blocks.POPPY)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POTTED_GILIA	= RegistryUtils.createBlockNoItem("potted_gilia",	() -> new FlowerPotBlock(GILIA.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));
	
	public static final RegistryObject<Block> ALOE_BUNDLE 			= RegistryUtils.createBlock("aloe_bundle", 	 () -> new RotatedPillarBlock(Block.Properties.from(Blocks.DRIED_KELP_BLOCK)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BARREL_CACTUS 		= RegistryUtils.createBlock("barrel_cactus", 	 () -> new BarrelCactusBlock(Block.Properties.from(Blocks.CACTUS).tickRandomly()), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POTTED_BARREL_CACTUS	= RegistryUtils.createBlockNoItem("potted_barrel_cactus",	() -> new FlowerPotBlock(BARREL_CACTUS.get(), Block.Properties.from(Blocks.POTTED_CACTUS)));

	//quark compatability
	public static final RegistryObject<Block> ARID_SANDSTONE_VERTICAL_SLAB 			= RegistryUtils.createBlockCompat("quark", "arid_sandstone_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);		
	public static final RegistryObject<Block> CUT_ARID_SANDSTONE_VERTICAL_SLAB 		= RegistryUtils.createBlockCompat("quark", "cut_arid_sandstone_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);	
	public static final RegistryObject<Block> SMOOTH_ARID_SANDSTONE_VERTICAL_SLAB 	= RegistryUtils.createBlockCompat("quark", "smooth_arid_sandstone_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);	
	
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICKS 	    		= RegistryUtils.createBlockCompat("quark", "arid_sandstone_bricks", 	    () -> new Block(Block.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);	
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_SLAB 			= RegistryUtils.createBlockCompat("quark", "arid_sandstone_brick_slab", 	() -> new SlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);	
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_STAIRS			= RegistryUtils.createBlockCompat("quark", "arid_sandstone_brick_stairs", 	() -> new StairsBlock(ARID_SANDSTONE.get().getDefaultState(), Block.Properties.from(Blocks.SANDSTONE_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_WALL  			= RegistryUtils.createBlockCompat("quark", "arid_sandstone_brick_wall", 	() -> new WallBlock(Block.Properties.from(Blocks.SANDSTONE_WALL)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ARID_SANDSTONE_BRICK_VERTICAL_SLAB  	= RegistryUtils.createBlockCompat("quark", "arid_sandstone_brick_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);		

	public static final RegistryObject<Block> RED_ARID_SANDSTONE_VERTICAL_SLAB 			= RegistryUtils.createBlockCompat("quark", "red_arid_sandstone_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);		
	public static final RegistryObject<Block> CUT_RED_ARID_SANDSTONE_VERTICAL_SLAB 		= RegistryUtils.createBlockCompat("quark", "cut_red_arid_sandstone_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);	
	public static final RegistryObject<Block> SMOOTH_RED_ARID_SANDSTONE_VERTICAL_SLAB 	= RegistryUtils.createBlockCompat("quark", "smooth_red_arid_sandstone_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);	
	
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICKS 	    		= RegistryUtils.createBlockCompat("quark", "red_arid_sandstone_bricks", 	    () -> new Block(Block.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);	
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_SLAB 			= RegistryUtils.createBlockCompat("quark", "red_arid_sandstone_brick_slab", 	() -> new SlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);	
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_STAIRS			= RegistryUtils.createBlockCompat("quark", "red_arid_sandstone_brick_stairs", 	() -> new StairsBlock(RED_ARID_SANDSTONE.get().getDefaultState(), Block.Properties.from(Blocks.SANDSTONE_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_WALL  			= RegistryUtils.createBlockCompat("quark", "red_arid_sandstone_brick_wall", 	() -> new WallBlock(Block.Properties.from(Blocks.SANDSTONE_WALL)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_ARID_SANDSTONE_BRICK_VERTICAL_SLAB  	= RegistryUtils.createBlockCompat("quark", "red_arid_sandstone_brick_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), ItemGroup.BUILDING_BLOCKS);		
	
	public static final RegistryObject<Block> VERTICAL_YUCCA_PLANKS = RegistryUtils.createBlockCompat("quark", "vertical_yucca_planks", () -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_VERTICAL_SLAB 	= RegistryUtils.createBlockCompat("quark", "yucca_vertical_slab", 		() -> new VerticalSlabBlock(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_BOOKSHELF 		= RegistryUtils.createBlockCompat("quark", "yucca_bookshelf", 			() -> new BookshelfBlock(Block.Properties.from(Blocks.BOOKSHELF)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> YUCCA_LADDER 			= RegistryUtils.createBlockCompat("quark", "yucca_ladder", 				() -> new LadderBlock(Block.Properties.from(Blocks.LADDER).harvestTool(ToolType.AXE)), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> YUCCA_LEAF_CARPET 	= RegistryUtils.createBlockCompat("quark", "yucca_leaf_carpet", 	() -> new LeafCarpetBlock(Block.Properties.from(YUCCA_LEAVES.get())), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> YUCCA_CASK			= RegistryUtils.createBlockCompat("quark", "yucca_cask", 			() -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROASTED_YUCCA_CASK	= RegistryUtils.createBlockCompat("quark", "roasted_yucca_cask", 	() -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BARREL_CACTUS_BATCH	= RegistryUtils.createBlockCompat("quark", "barrel_cactus_batch", 	() -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).hardnessAndResistance(0.5F).sound(SoundType.WOOD)), ItemGroup.DECORATIONS);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//blocks
	public static final RegistryObject<Block> KOUSA_PLANKS 			= RegistryUtils.createBlock("kousa_planks", 		() -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_SAPLING 		= RegistryUtils.createBlock("kousa_sapling", 		() -> new AtmosphericSaplingBlock(new KousaTree(), Block.Properties.from(Blocks.ACACIA_SAPLING)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> KOUSA_LOG 			= RegistryUtils.createBlock("kousa_log", 			() -> new LogBlock(null, Block.Properties.from(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_KOUSA_LOG 	= RegistryUtils.createBlock("stripped_kousa_log", 	() -> new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_WOOD 			= RegistryUtils.createBlock("kousa_wood",           () -> new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_KOUSA_WOOD	= RegistryUtils.createBlock("stripped_kousa_wood",  () -> new RotatedPillarBlock(Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LEAVES 			= RegistryUtils.createBlock("kousa_leaves", 		() -> new LeavesBlock(Block.Properties.from(Blocks.ACACIA_LEAVES)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> KOUSA_SLAB 			= RegistryUtils.createBlock("kousa_slab", 			() -> new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_STAIRS 			= RegistryUtils.createBlock("kousa_stairs", 		() -> new StairsBlock(KOUSA_PLANKS.get().getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_PRESSURE_PLATE 	= RegistryUtils.createBlock("kousa_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> KOUSA_FENCE 			= RegistryUtils.createBlock("kousa_fence", 			() -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> KOUSA_TRAPDOOR 		= RegistryUtils.createBlock("kousa_trapdoor", 		() -> new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> KOUSA_FENCE_GATE 		= RegistryUtils.createBlock("kousa_fence_gate", 	() -> new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> KOUSA_BUTTON 			= RegistryUtils.createBlock("kousa_button", 		() -> new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> KOUSA_DOOR 			= RegistryUtils.createBlock("kousa_door", 			() -> new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
	
	public static final RegistryObject<Block> POTTED_KOUSA_SAPLING	= RegistryUtils.createBlockNoItem("potted_kousa_sapling",	() -> new FlowerPotBlock(KOUSA_SAPLING.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));

	//quark compatability
	public static final RegistryObject<Block> VERTICAL_KOUSA_PLANKS = RegistryUtils.createBlockCompat("quark", "vertical_kousa_planks", () -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_VERTICAL_SLAB 	= RegistryUtils.createBlockCompat("quark", "kousa_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_BOOKSHELF 		= RegistryUtils.createBlockCompat("quark", "kousa_bookshelf", 		() -> new BookshelfBlock(Block.Properties.from(Blocks.BOOKSHELF)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> KOUSA_LADDER 			= RegistryUtils.createBlockCompat("quark", "kousa_ladder", 			() -> new LadderBlock(Block.Properties.from(Blocks.LADDER).harvestTool(ToolType.AXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> KOUSA_LEAF_CARPET 	= RegistryUtils.createBlockCompat("quark", "kousa_leaf_carpet", 	() -> new LeafCarpetBlock(Block.Properties.from(KOUSA_LEAVES.get())), ItemGroup.DECORATIONS);
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//blocks
	public static final RegistryObject<Block> CRUSTOSE 			    = RegistryUtils.createBlock("crustose", 		    () -> new CrustoseBlock(Block.Properties.from(Blocks.DIRT).tickRandomly()), ItemGroup.BUILDING_BLOCKS);	
	public static final RegistryObject<Block> CRUSTOSE_LOG 			= RegistryUtils.createBlock("crustose_log", 		() -> new CrustoseLogBlock(null, Block.Properties.from(Blocks.OAK_LOG).tickRandomly()), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> ASPEN_PLANKS 			= RegistryUtils.createBlock("aspen_planks", 		() -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_SAPLING 		= RegistryUtils.createBlock("aspen_sapling", 		() -> new AtmosphericSaplingBlock(new AspenTree(), Block.Properties.from(Blocks.ACACIA_SAPLING)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ASPEN_LOG 			= RegistryUtils.createBlock("aspen_log", 			() -> new LogBlock(null, Block.Properties.from(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ASPEN_LOG 	= RegistryUtils.createBlock("stripped_aspen_log", 	() -> new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_WOOD 			= RegistryUtils.createBlock("aspen_wood",           () -> new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ASPEN_WOOD	= RegistryUtils.createBlock("stripped_aspen_wood",  () -> new RotatedPillarBlock(Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LEAVES 			= RegistryUtils.createBlock("aspen_leaves", 		() -> new LeavesBlock(Block.Properties.from(Blocks.ACACIA_LEAVES)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ASPEN_SLAB 			= RegistryUtils.createBlock("aspen_slab", 			() -> new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_STAIRS 			= RegistryUtils.createBlock("aspen_stairs", 		() -> new StairsBlock(ASPEN_PLANKS.get().getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_PRESSURE_PLATE 	= RegistryUtils.createBlock("aspen_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ASPEN_FENCE 			= RegistryUtils.createBlock("aspen_fence", 			() -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ASPEN_TRAPDOOR 		= RegistryUtils.createBlock("aspen_trapdoor", 		() -> new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ASPEN_FENCE_GATE 		= RegistryUtils.createBlock("aspen_fence_gate", 	() -> new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ASPEN_BUTTON 			= RegistryUtils.createBlock("aspen_button", 		() -> new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ASPEN_DOOR 			= RegistryUtils.createBlock("aspen_door", 			() -> new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
	
	public static final RegistryObject<Block> POTTED_ASPEN_SAPLING	= RegistryUtils.createBlockNoItem("potted_aspen_sapling",	() -> new FlowerPotBlock(ASPEN_SAPLING.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));

	//quark compatability
	public static final RegistryObject<Block> VERTICAL_ASPEN_PLANKS = RegistryUtils.createBlockCompat("quark", "vertical_aspen_planks", () -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_VERTICAL_SLAB 	= RegistryUtils.createBlockCompat("quark", "aspen_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_BOOKSHELF 		= RegistryUtils.createBlockCompat("quark", "aspen_bookshelf", 		() -> new BookshelfBlock(Block.Properties.from(Blocks.BOOKSHELF)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ASPEN_LADDER 			= RegistryUtils.createBlockCompat("quark", "aspen_ladder", 			() -> new LadderBlock(Block.Properties.from(Blocks.LADDER).harvestTool(ToolType.AXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ASPEN_LEAF_CARPET 	= RegistryUtils.createBlockCompat("quark", "aspen_leaf_carpet", 	() -> new LeafCarpetBlock(Block.Properties.from(ASPEN_LEAVES.get())), ItemGroup.DECORATIONS);
}
