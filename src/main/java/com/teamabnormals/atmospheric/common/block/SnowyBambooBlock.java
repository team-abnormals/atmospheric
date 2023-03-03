package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BambooBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class SnowyBambooBlock extends BambooBlock {

	public SnowyBambooBlock(Properties properties) {
		super(properties);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		if (!fluidstate.isEmpty()) {
			return null;
		} else {
			BlockState state = context.getLevel().getBlockState(context.getClickedPos().below());
			if (state.is(AtmosphericBlockTags.SNOWY_BAMBOO_PLANTABLE_ON)) {
				if (state.is(AtmosphericBlocks.SNOWY_BAMBOO_SAPLING.get())) {
					return this.defaultBlockState().setValue(AGE, 0);
				} else if (state.is(AtmosphericBlocks.SNOWY_BAMBOO.get())) {
					int i = state.getValue(AGE) > 0 ? 1 : 0;
					return this.defaultBlockState().setValue(AGE, i);
				} else {
					BlockState aboveState = context.getLevel().getBlockState(context.getClickedPos().above());
					return aboveState.is(AtmosphericBlocks.SNOWY_BAMBOO.get()) ? this.defaultBlockState().setValue(AGE, aboveState.getValue(AGE)) : AtmosphericBlocks.SNOWY_BAMBOO_SAPLING.get().defaultBlockState();
				}
			} else {
				return null;
			}
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		return new ItemStack(Items.BAMBOO);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).is(AtmosphericBlockTags.SNOWY_BAMBOO_PLANTABLE_ON);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
		if (!state.canSurvive(level, pos)) {
			level.scheduleTick(pos, this, 1);
		}

		if (direction == Direction.UP && offsetState.is(AtmosphericBlocks.SNOWY_BAMBOO.get()) && offsetState.getValue(AGE) > state.getValue(AGE)) {
			level.setBlock(pos, state.cycle(AGE), 2);
		}

		return super.updateShape(state, direction, offsetState, level, pos, offsetPos);
	}

	@Override
	protected void growBamboo(BlockState state, Level level, BlockPos pos, RandomSource random, int height) {
		BlockState blockstate = level.getBlockState(pos.below());
		BlockPos blockpos = pos.below(2);
		BlockState blockstate1 = level.getBlockState(blockpos);
		BambooLeaves leaves = BambooLeaves.NONE;
		if (height >= 1) {
			if (blockstate.is(AtmosphericBlocks.SNOWY_BAMBOO.get()) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
				if (blockstate.is(AtmosphericBlocks.SNOWY_BAMBOO.get()) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
					leaves = BambooLeaves.LARGE;
					if (blockstate1.is(AtmosphericBlocks.SNOWY_BAMBOO.get())) {
						level.setBlock(pos.below(), blockstate.setValue(LEAVES, BambooLeaves.SMALL), 3);
						level.setBlock(blockpos, blockstate1.setValue(LEAVES, BambooLeaves.NONE), 3);
					}
				}
			} else {
				leaves = BambooLeaves.SMALL;
			}
		}

		int i = state.getValue(AGE) != 1 && !blockstate1.is(AtmosphericBlocks.SNOWY_BAMBOO.get()) ? 0 : 1;
		int j = (height < 11 || !(random.nextFloat() < 0.25F)) && height != 15 ? 0 : 1;
		level.setBlock(pos.above(), this.defaultBlockState().setValue(AGE, i).setValue(LEAVES, leaves).setValue(STAGE, j), 3);
	}

	@Override
	protected int getHeightAboveUpToMax(BlockGetter level, BlockPos pos) {
		int i;
		for (i = 0; i < 16 && level.getBlockState(pos.above(i + 1)).is(AtmosphericBlocks.SNOWY_BAMBOO.get()); ++i) {
		}

		return i;
	}

	@Override
	protected int getHeightBelowUpToMax(BlockGetter level, BlockPos pos) {
		int i;
		for (i = 0; i < 16 && level.getBlockState(pos.below(i + 1)).is(AtmosphericBlocks.SNOWY_BAMBOO.get()); ++i) {
		}

		return i;
	}
}
