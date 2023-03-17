package com.teamabnormals.atmospheric.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class CurrantStalkBundleBlock extends RotatedPillarBlock {

	public CurrantStalkBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
		if (!level.isClientSide()) {
			level.destroyBlock(pos, true);
			CurrantStalkBundleBlock.breakLeaves(level, pos);
			breakStalks(state, level, pos);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isClientSide()) {
			CurrantStalkBundleBlock.breakLeaves(level, pos);
			breakStalks(state, level, pos);
			level.destroyBlock(pos, true);
		}
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
		if (player.getMainHandItem().is(Tags.Items.SHEARS) || EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SILK_TOUCH, player.getMainHandItem()) != 0) {
			this.playerWillDestroy(level, pos, state, player);
			level.setBlock(pos, fluid.createLegacyBlock(), level.isClientSide ? 11 : 3);
			if (!player.isCreative()) {
				dropResources(state, level, pos, null, player, player.getMainHandItem());
			}
			return false;
		}

		return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
	}

	@Override
	public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
		if (!level.isClientSide()) {
			breakLeaves(level, pos);
			breakStalks(state, (Level) level, pos);
		}
		super.destroy(level, pos, state);
	}

	public static void breakLeaves(LevelAccessor level, BlockPos pos) {
		for (Direction direction : Direction.values()) {
			BlockPos offsetPos = pos.relative(direction);
			BlockState offsetState = level.getBlockState(offsetPos);
			if (offsetState.getBlock() instanceof CurrantLeavesBlock currantLeaves) {
				level.scheduleTick(offsetPos, currantLeaves, 2 + level.getRandom().nextInt(2));
			}
		}
	}

	public void breakStalks(BlockState state, Level level, BlockPos pos) {
		for (Direction direction : Direction.values()) {
			if (this.isValidDirection(state, direction)) {
				BlockPos offsetPos = pos.relative(direction);
				BlockState offsetState = level.getBlockState(offsetPos);
				level.scheduleTick(offsetPos, offsetState.getBlock(), 2 + level.getRandom().nextInt(2));
			}
		}
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.NORMAL;
	}

	public boolean isValidDirection(BlockState state, Direction direction) {
		return true;
	}
}
