package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.common.entity.BlueprintFallingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class HangingCurrantBlock extends BushBlock implements Fallable {
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);

	public HangingCurrantBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(AtmosphericBlocks.CURRANT_LEAVES.get());
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return this.mayPlaceOn(level.getBlockState(pos.above()), level, pos.above());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		if (!state.canSurvive(level, pos)){
			this.destroy(level, pos, state);
			return Blocks.AIR.defaultBlockState();
		}

		return super.updateShape(state, direction, facingState, level, pos, facingPos);
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
		if (player.getMainHandItem().is(Tags.Items.SHEARS)) {
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
	public void destroy(LevelAccessor levelAccessor, BlockPos pos, BlockState state) {
		if (!levelAccessor.isClientSide() && levelAccessor instanceof Level level) {
			BlueprintFallingBlockEntity.fall(level, pos, state).setAllowsPlacing(false);
		}
		super.destroy(levelAccessor, pos, state);
	}

	@Override
	public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity entity) {
		BlockState state = level.getBlockState(pos);
		if ((state.isAir() || state.getMaterial().isReplaceable()) && AtmosphericBlocks.CURRANT_SEEDLING.get().canSurvive(state, level, pos)) {
			level.setBlockAndUpdate(pos, AtmosphericBlocks.CURRANT_SEEDLING.get().defaultBlockState());
		} else {
			for (int i = 0; i < 2 + level.random.nextInt(3); i++) {
				popResource(level, pos, new ItemStack(AtmosphericItems.CURRANT.get()));
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		popResource(level, pos, new ItemStack(AtmosphericItems.CURRANT.get(), 2 + level.random.nextInt(3)));
		level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
		BlockState newState = Blocks.AIR.defaultBlockState();
		level.removeBlock(pos, true);
		level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}