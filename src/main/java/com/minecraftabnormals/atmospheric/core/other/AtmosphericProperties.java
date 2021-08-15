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

	public static final AbstractBlock.Properties ARID_SAND = AbstractBlock.Properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(0.5F).sound(SoundType.SAND);
	public static final AbstractBlock.Properties RED_ARID_SAND = AbstractBlock.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_ORANGE).harvestTool(ToolType.SHOVEL).strength(0.5F).sound(SoundType.SAND);

	public static final AbstractBlock.Properties CRUSTOSE_PATH = AbstractBlock.Properties.of(Material.DIRT, MaterialColor.GOLD).strength(0.65F).sound(SoundType.GRASS).isViewBlocking(AtmosphericProperties::isntSolid).isViewBlocking(AtmosphericProperties::isntSolid);
	public static final AbstractBlock.Properties ARID_SPROUTS = AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.SAND).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS);

	public static final AbstractBlock.Properties IVORY_TRAVERTINE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(1.5F, 6.0F);
	public static final AbstractBlock.Properties PEACH_TRAVERTINE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).strength(1.5F, 6.0F);
	public static final AbstractBlock.Properties PERSIMMON_TRAVERTINE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).strength(1.5F, 6.0F);
	public static final AbstractBlock.Properties SAFFRON_TRAVERTINE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(1.5F, 6.0F);

	public static final AbstractBlock.Properties ALOE_VERA = Block.Properties.of(Material.PLANT).noCollission().instabreak().randomTicks().sound(SoundType.CROP);

	public static AbstractBlock.Properties createLeaves() {
		return AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().harvestTool(ToolType.HOE).isValidSpawn(AtmosphericProperties::allowsSpawnOnLeaves).isSuffocating(AtmosphericProperties::isntSolid).isViewBlocking(AtmosphericProperties::isntSolid);
	}

	public static Block.Properties createLeafCarpet() {
		return Block.Properties.of(Material.CLOTH_DECORATION).noOcclusion().strength(0.0F).randomTicks().sound(SoundType.GRASS).harvestTool(ToolType.HOE);
	}

	private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}
}
