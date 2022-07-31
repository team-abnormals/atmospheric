package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

public class YuccaGateauBlock extends HorizontalDirectionalBlock {
	public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 9);
	protected static final VoxelShape[] NORTH_SHAPES = new VoxelShape[]{
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(4.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(5.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(6.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(7.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(8.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(9.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(10.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(11.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(12.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D)};

	protected static final VoxelShape[] EAST_SHAPES = new VoxelShape[]{
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 4.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 5.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 6.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 7.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 8.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 9.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 10.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 11.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 12.0D, 13.0D, 6.0D, 13.0D)};

	protected static final VoxelShape[] SOUTH_SHAPES = new VoxelShape[]{
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 12.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 11.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 10.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 9.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 8.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 7.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 6.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 5.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 4.0D, 6.0D, 13.0D)};

	protected static final VoxelShape[] WEST_SHAPES = new VoxelShape[]{
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 12.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 11.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 10.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 9.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 8.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 7.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 6.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 5.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 4.0D)};

	public YuccaGateauBlock(Block.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		switch (state.getValue(FACING)) {
			case NORTH:
			default:
				return NORTH_SHAPES[state.getValue(BITES)];
			case EAST:
				return EAST_SHAPES[state.getValue(BITES)];
			case SOUTH:
				return SOUTH_SHAPES[state.getValue(BITES)];
			case WEST:
				return WEST_SHAPES[state.getValue(BITES)];
		}
	}

	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult p_225533_6_) {
		if (worldIn.isClientSide) {
			ItemStack itemstack = player.getItemInHand(handIn);
			if (this.eatCake(worldIn, pos, state, player) == InteractionResult.SUCCESS) {
				return InteractionResult.SUCCESS;
			}
			if (itemstack.isEmpty()) {
				return InteractionResult.CONSUME;
			}
		}
		return this.eatCake(worldIn, pos, state, player);
	}

	private InteractionResult eatCake(LevelAccessor worldIn, BlockPos pos, BlockState state, Player player) {
		if (!player.canEat(false)) {
			return InteractionResult.PASS;
		} else {
			player.awardStat(Stats.EAT_CAKE_SLICE);
			player.getFoodData().eat(1, 0.0F);
			player.addEffect(new MobEffectInstance(AtmosphericMobEffects.PERSISTENCE.get(), 320, 0, true, false, true));
			player.clearFire();
			int i = state.getValue(BITES);
			if (i < 9) {
				worldIn.setBlock(pos, state.setValue(BITES, i + 1), 3);
			} else {
				if (player instanceof ServerPlayer) {
					ServerPlayer serverplayerentity = (ServerPlayer) player;
					if (!player.getCommandSenderWorld().isClientSide()) {
						AtmosphericCriteriaTriggers.FINISH_GATEAU.trigger(serverplayerentity);
					}
				}
				worldIn.removeBlock(pos, false);
			}
			return InteractionResult.SUCCESS;
		}
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.below()).getMaterial().isSolid();
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BITES, FACING);
	}

	public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
		return (10 - blockState.getValue(BITES)) * 2;
	}

	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return false;
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
}
