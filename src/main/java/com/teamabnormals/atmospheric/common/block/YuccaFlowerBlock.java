package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.pathfinder.PathType;

import javax.annotation.Nullable;

public class YuccaFlowerBlock extends FlowerBlock implements BonemealableBlock, YuccaPlant {

	public YuccaFlowerBlock(Holder<MobEffect> effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState state2 = worldIn.getBlockState(pos.below());
		return state2.is(AtmosphericBlockTags.YUCCA_FLOWER_PLACEABLE);
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state) {
		if (AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().canSurvive(world, pos) && (world.isEmptyBlock(pos.above()))) {
			this.placeAt(world, pos, 2);
		}
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	public void placeAt(LevelAccessor world, BlockPos pos, int flags) {
		world.setBlock(pos, AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlock(pos.above(), AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER), flags);
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
	public float getKnockbackForce() {
		return 0.5F;
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
