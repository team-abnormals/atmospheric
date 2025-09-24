package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;

import javax.annotation.Nullable;

public class YuccaSaplingBlock extends SaplingBlock implements YuccaPlant {

	public YuccaSaplingBlock(TreeGrower tree, Properties properties) {
		super(tree, properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.is(AtmosphericBlockTags.DESERT_PLANT_PLACEABLE) || state.is(Blocks.FARMLAND);
	}

	@Override
	public float getKnockbackForce() {
		return 0.35F;
	}

	@Override
	public ResourceKey<DamageType> getDamageTypeKey() {
		return AtmosphericDamageTypes.YUCCA_SAPLING;
	}

	@Nullable
	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return this.getYuccaPathType(entity);
	}

	@Nullable
	@Override
	public PathType getAdjacentBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity, PathType originalType) {
		return this.getYuccaAdjacentPathType(entity);
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		this.onYuccaCollision(state, worldIn, pos, entityIn);
	}
}
