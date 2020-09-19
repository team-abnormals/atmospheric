package com.minecraftabnormals.atmospheric.core.other;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class AtmosphericProperties {

	public static final AbstractBlock.Properties ARID_SAND = AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.5F).sound(SoundType.SAND);
	public static final AbstractBlock.Properties RED_ARID_SAND = AbstractBlock.Properties.create(Material.SAND, MaterialColor.ORANGE_TERRACOTTA).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.5F).sound(SoundType.SAND);

	public static final AbstractBlock.Properties IVORY_TRAVERTINE = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F);
	public static final AbstractBlock.Properties PEACH_TRAVERTINE = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F);
	public static final AbstractBlock.Properties PERSIMMON_TRAVERTINE = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F);
	public static final AbstractBlock.Properties SAFFRON_TRAVERTINE = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(1.5F, 6.0F);

	public static final AbstractBlock.Properties ALOE_VERA = Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().tickRandomly().sound(SoundType.CROP);

	public static AbstractBlock.Properties createLeaves() {
		return AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().harvestTool(ToolType.HOE).setAllowsSpawn(AtmosphericProperties::allowsSpawnOnLeaves).setSuffocates(AtmosphericProperties::isntSolid).setBlocksVision(AtmosphericProperties::isntSolid);
	}
	
	private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}
	
	private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}
}
