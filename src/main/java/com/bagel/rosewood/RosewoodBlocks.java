package com.bagel.rosewood;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = Rosewood.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RosewoodBlocks {
	
	public static Block ROSEWOOD_LOG = new LogBlock(null, Block.Properties.from(Blocks.OAK_LOG)).setRegistryName("rosewood_log");
	public static Block ROSEWOOD = new LogBlock(null, Block.Properties.from(Blocks.OAK_WOOD)).setRegistryName("rosewood");
	
	public static Block STRIPPED_ROSEWOOD_LOG = new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_LOG)).setRegistryName("stripped_rosewood_log");
	public static Block STRIPPED_ROSEWOOD = new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)).setRegistryName("stripped_rosewood");
	
	public static Block ROSEWOOD_PLANKS = new Block(Block.Properties.from(Blocks.OAK_PLANKS)).setRegistryName("rosewood_planks");
	
	public static Block ROSEWOOD_STAIRS = new StairsBlock(ROSEWOOD_PLANKS.getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)).setRegistryName("rosewood_stairs");
	public static Block ROSEWOOD_SLAB = new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)).setRegistryName("rosewood_slab");
	
	public static Block ROSEWOOD_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)).setRegistryName("rosewood_pressure_plate");
	public static Block ROSEWOOD_BUTTON = new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)).setRegistryName("rosewood_button");
	
	public static Block ROSEWOOD_DOOR = new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)).setRegistryName("rosewood_door");
	public static Block ROSEWOOD_TRAPDOOR = new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)).setRegistryName("rosewood_trapdoor");
	
	public static Block ROSEWOOD_FENCE = new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)).setRegistryName("rosewood_fence");
	public static Block ROSEWOOD_FENCE_GATE = new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)).setRegistryName("rosewood_fence_gate");

	
	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		final Block blocks[] = {
				ROSEWOOD_LOG, ROSEWOOD, STRIPPED_ROSEWOOD_LOG, STRIPPED_ROSEWOOD, 
				
				ROSEWOOD_PLANKS, ROSEWOOD_STAIRS, ROSEWOOD_SLAB,
				
				ROSEWOOD_PRESSURE_PLATE, ROSEWOOD_BUTTON, ROSEWOOD_DOOR, ROSEWOOD_TRAPDOOR, ROSEWOOD_FENCE, ROSEWOOD_FENCE_GATE
		};
		event.getRegistry().registerAll(blocks);
	}
	
	public static BlockItem itemBlock(Block block, ItemGroup itemGroup) {
		return (BlockItem) new BlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
	}
	
	@SubscribeEvent
	public static void onRegisterItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> itemblock = event.getRegistry();
		
		itemblock.register(itemBlock(ROSEWOOD_LOG, ItemGroup.BUILDING_BLOCKS));
		itemblock.register(itemBlock(ROSEWOOD, ItemGroup.BUILDING_BLOCKS));
		itemblock.register(itemBlock(STRIPPED_ROSEWOOD_LOG, ItemGroup.BUILDING_BLOCKS));
		itemblock.register(itemBlock(STRIPPED_ROSEWOOD, ItemGroup.BUILDING_BLOCKS));
		itemblock.register(itemBlock(ROSEWOOD_PLANKS, ItemGroup.BUILDING_BLOCKS));
		itemblock.register(itemBlock(ROSEWOOD_STAIRS, ItemGroup.BUILDING_BLOCKS));
		itemblock.register(itemBlock(ROSEWOOD_SLAB, ItemGroup.BUILDING_BLOCKS));
		itemblock.register(itemBlock(ROSEWOOD_PRESSURE_PLATE, ItemGroup.REDSTONE));
		itemblock.register(itemBlock(ROSEWOOD_BUTTON, ItemGroup.REDSTONE));
		itemblock.register(itemBlock(ROSEWOOD_DOOR, ItemGroup.REDSTONE));
		itemblock.register(itemBlock(ROSEWOOD_TRAPDOOR, ItemGroup.REDSTONE));
		itemblock.register(itemBlock(ROSEWOOD_FENCE, ItemGroup.DECORATIONS));
		itemblock.register(itemBlock(ROSEWOOD_FENCE_GATE, ItemGroup.REDSTONE));
	}
	
}

