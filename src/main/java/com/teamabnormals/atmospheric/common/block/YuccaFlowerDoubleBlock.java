package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class YuccaFlowerDoubleBlock extends TallFlowerBlock implements YuccaPlant {
	public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

	public YuccaFlowerDoubleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState state2 = worldIn.getBlockState(pos.below());
		if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER) {
			return state2.is(AtmosphericBlockTags.YUCCA_FLOWER_PLACEABLE) || state2.getBlock() == AtmosphericBlocks.TALL_YUCCA_FLOWER.get();
		}
		return state2.is(AtmosphericBlockTags.YUCCA_FLOWER_PLACEABLE);
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(worldIn, pos);
		return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public float getKnockbackForce() {
		return 0.65F;
	}

	@Override
	public ResourceKey<DamageType> getDamageTypeKey() {
		return AtmosphericDamageTypes.YUCCA_FLOWER;
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		this.onYuccaCollision(state, worldIn, pos, entityIn);
	}
}
