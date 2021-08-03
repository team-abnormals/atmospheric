package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericEffects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class YuccaGateauBlock extends HorizontalBlock {
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

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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

	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (worldIn.isClientSide) {
			ItemStack itemstack = player.getItemInHand(handIn);
			if (this.eatCake(worldIn, pos, state, player) == ActionResultType.SUCCESS) {
				return ActionResultType.SUCCESS;
			}
			if (itemstack.isEmpty()) {
				return ActionResultType.CONSUME;
			}
		}
		return this.eatCake(worldIn, pos, state, player);
	}

	private ActionResultType eatCake(IWorld worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!player.canEat(false)) {
			return ActionResultType.PASS;
		} else {
			player.awardStat(Stats.EAT_CAKE_SLICE);
			player.getFoodData().eat(1, 0.0F);
			player.addEffect(new EffectInstance(AtmosphericEffects.PERSISTENCE.get(), 320, 0, true, false, true));
			int i = state.getValue(BITES);
			if (i < 9) {
				worldIn.setBlock(pos, state.setValue(BITES, Integer.valueOf(i + 1)), 3);
			} else {
				if (player instanceof ServerPlayerEntity) {
					ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
					if (!player.getCommandSenderWorld().isClientSide()) {
						AtmosphericCriteriaTriggers.FINISH_GATEAU.trigger(serverplayerentity);
					}
				}
				worldIn.removeBlock(pos, false);
			}
			return ActionResultType.SUCCESS;
		}
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.below()).getMaterial().isSolid();
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BITES, FACING);
	}

	public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
		return (10 - blockState.getValue(BITES)) * 2;
	}

	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
}
