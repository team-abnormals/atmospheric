package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class WaterHyacinthBlock extends BlueprintFlowerBlock implements SimpleWaterloggedBlock {
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

	public WaterHyacinthBlock(Properties properties) {
		super(AtmosphericMobEffects.WORSENING, 6, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(HALF, DoubleBlockHalf.UPPER));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(worldIn, pos);
		return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
		if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.is(this) && facingState.getValue(HALF) != doubleblockhalf) {
			return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos blockpos = context.getClickedPos();
		return blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
	}

	@Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlock(pos.below(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER), 3);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
			return state.getValue(WATERLOGGED);
		} else {
			BlockState blockstate = worldIn.getBlockState(pos.below());
			if (state.getBlock() != this)
				return super.canSurvive(state, worldIn, pos);
			return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER && blockstate.getValue(WATERLOGGED);
		}
	}

	public void placeAt(LevelAccessor worldIn, BlockPos pos, int flags) {
		worldIn.setBlock(pos.below(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, true), flags);
		worldIn.setBlock(pos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), flags);
	}

	@Override
	public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
		DoubleBlockHalf half = state.getValue(HALF);
		BlockPos blockpos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		if (blockstate.getBlock() == this && blockstate.getValue(HALF) != half) {
			worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			if (!worldIn.isClientSide && !player.isCreative()) {
				dropResources(state, worldIn, pos, null, player, player.getMainHandItem());
				dropResources(blockstate, worldIn, pos, null, player, player.getMainHandItem());
			}
			if (blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				worldIn.destroyBlock(blockpos, false);
			}
		}

		super.playerWillDestroy(worldIn, pos, state, player);
	}

	@Override
	public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
		super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
	}

	protected static void preventCreativeDropFromBottomPart(Level world, BlockPos pos, BlockState state, Player player) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			BlockPos blockpos = pos.below();
			BlockState blockstate = world.getBlockState(blockpos);
			if (blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				world.setBlock(blockpos, Blocks.WATER.defaultBlockState(), 35);
				world.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			}
		}

	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HALF, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockBehaviour.OffsetType getOffsetType() {
		return BlockBehaviour.OffsetType.XZ;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}
}
