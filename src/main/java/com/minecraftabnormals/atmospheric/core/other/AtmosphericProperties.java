package com.minecraftabnormals.atmospheric.core.other;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class AtmosphericProperties {

	public static final BlockBehaviour.Properties ARID_SAND = BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND);
	public static final BlockBehaviour.Properties RED_ARID_SAND = BlockBehaviour.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_ORANGE).strength(0.5F).sound(SoundType.SAND);

	public static final BlockBehaviour.Properties CRUSTOSE_PATH = BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.GOLD).strength(0.65F).sound(SoundType.GRASS).isViewBlocking(AtmosphericProperties::isntSolid).isViewBlocking(AtmosphericProperties::isntSolid);
	public static final BlockBehaviour.Properties ARID_SPROUTS = BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.SAND).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS);

	public static final BlockBehaviour.Properties IVORY_TRAVERTINE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(1.5F, 6.0F);
	public static final BlockBehaviour.Properties PEACH_TRAVERTINE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).strength(1.5F, 6.0F);
	public static final BlockBehaviour.Properties PERSIMMON_TRAVERTINE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).strength(1.5F, 6.0F);
	public static final BlockBehaviour.Properties SAFFRON_TRAVERTINE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(1.5F, 6.0F);

	public static final BlockBehaviour.Properties ALOE_VERA = Block.Properties.of(Material.PLANT).noCollission().instabreak().randomTicks().sound(SoundType.CROP);

	public static BlockBehaviour.Properties createLeaves() {
		return BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(AtmosphericProperties::allowsSpawnOnLeaves).isSuffocating(AtmosphericProperties::isntSolid).isViewBlocking(AtmosphericProperties::isntSolid);
	}

	public static Block.Properties createLeafCarpet() {
		return Block.Properties.of(Material.CLOTH_DECORATION).noOcclusion().strength(0.0F).randomTicks().sound(SoundType.GRASS);
	}

	private static Boolean allowsSpawnOnLeaves(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
		return false;
	}
}
