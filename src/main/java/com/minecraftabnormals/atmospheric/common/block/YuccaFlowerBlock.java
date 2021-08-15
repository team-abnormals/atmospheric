package com.minecraftabnormals.atmospheric.common.block;

import com.google.common.base.Supplier;
import com.minecraftabnormals.abnormals_core.common.advancement.EmptyTrigger;
import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import com.minecraftabnormals.atmospheric.common.block.api.IYuccaPlant;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.Effect;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class YuccaFlowerBlock extends AbnormalsFlowerBlock implements IGrowable, IYuccaPlant {

	public YuccaFlowerBlock(Supplier<Effect> effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState state2 = worldIn.getBlockState(pos.below());
		return state2.is(AtmosphericTags.YUCCA_PLANTABLE_ON) || state2.getBlock() == AtmosphericBlocks.YUCCA_LEAVES.get() || state2.getBlock() == Blocks.CACTUS;
	}

	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		if (AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().canSurvive(world, pos) && (world.isEmptyBlock(pos.above()))) {
			this.placeAt(world, pos, 2);
		}
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	public void placeAt(IWorld world, BlockPos pos, int flags) {
		world.setBlock(pos, AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlock(pos.above(), AtmosphericBlocks.TALL_YUCCA_FLOWER.get().defaultBlockState().setValue(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER), flags);
	}

	@Nullable
	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
		return PathNodeType.DAMAGE_CACTUS;
	}

	@Override
	public float getKnockbackForce() {
		return 0.5F;
	}

	@Override
	public DamageSource getDamageSource() {
		return AtmosphericDamageSources.YUCCA_FLOWER;
	}

	@Override
	public EmptyTrigger getCriteriaTrigger() {
		return AtmosphericCriteriaTriggers.YUCCA_FLOWER_PRICK;
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		this.onYuccaCollision(state, worldIn, pos, entityIn);
	}
}
