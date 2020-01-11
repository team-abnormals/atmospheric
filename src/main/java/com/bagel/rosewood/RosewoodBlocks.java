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
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = Rosewood.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RosewoodBlocks {
	
	public static Block ROSEWOOD_LOG = new LogBlock(null, Block.Properties.from(Blocks.OAK_LOG)).setRegistryName(Rosewood.MODID, "rosewood_log");
	public static Block ROSEWOOD = new LogBlock(null, Block.Properties.from(Blocks.OAK_WOOD)).setRegistryName(Rosewood.MODID, "rosewood");
	
	public static Block STRIPPED_ROSEWOOD_LOG = new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_LOG)).setRegistryName(Rosewood.MODID, "stripped_rosewood_log");
	public static Block STRIPPED_ROSEWOOD = new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)).setRegistryName(Rosewood.MODID, "stripped_rosewood");
	
	public static Block ROSEWOOD_PLANKS = new Block(Block.Properties.from(Blocks.OAK_PLANKS)).setRegistryName(Rosewood.MODID, "rosewood_planks");
	
	public static Block ROSEWOOD_STAIRS = new StairsBlock(ROSEWOOD_PLANKS.getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)).setRegistryName(Rosewood.MODID, "rosewood_planks");
	public static Block ROSEWOOD_SLAB = new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)).setRegistryName(Rosewood.MODID, "rosewood_planks");
	
	public static Block ROSEWOOD_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)).setRegistryName(Rosewood.MODID, "rosewood_pressure_plate");
	public static Block ROSEWOOD_BUTTON = new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)).setRegistryName(Rosewood.MODID, "rosewood_button");
	
	public static Block ROSEWOOD_DOOR = new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)).setRegistryName(Rosewood.MODID, "rosewood_door");
	public static Block ROSEWOOD_TRAPDOOR = new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)).setRegistryName(Rosewood.MODID, "rosewood_trapdoor");
	
	public static Block ROSEWOOD_FENCE = new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)).setRegistryName(Rosewood.MODID, "rosewood_fence");
	public static Block ROSEWOOD_FENCE_GATE = new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)).setRegistryName(Rosewood.MODID, "rosewood_fence_gate");
}
