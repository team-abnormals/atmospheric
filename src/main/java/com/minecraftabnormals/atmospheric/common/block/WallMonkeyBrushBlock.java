package com.minecraftabnormals.atmospheric.common.block;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.MonkeyBrushFeature;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericEffects;
import com.teamabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

public class WallMonkeyBrushBlock extends AbnormalsFlowerBlock implements IGrowable, IPlantable {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

	public WallMonkeyBrushBlock(Properties properties) {
		super(Effects.BLINDNESS, 120, properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND || block.getBlock().isIn(BlockTags.LOGS);
	}

	@Override
	public Effect getStewEffect() {
		return AtmosphericEffects.RELIEF.get();
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.offset(state.get(FACING).getOpposite());
		return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (context.getFace().getAxis().isVertical())
			return null;
		Direction direction = context.getFace();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = this.getDefaultState().with(FACING, context.getFace()).with(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(context.getHitVec().y - (double) blockpos.getY() > 0.5D)) ? Half.TOP : Half.BOTTOM);
		return blockstate;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getShapeForState(state);
	}

	public static VoxelShape getShapeForState(BlockState state) {
		return SHAPES.get(state.get(FACING));
	}

	public AbstractBlock.OffsetType getOffsetType() {
		return AbstractBlock.OffsetType.NONE;
	}

	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.up()).isAir();
	}

	@Override
	public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos blockPos, BlockState state) {
		label: for (int x = 0; x < 64; ++x) {
			BlockPos newBlockPos = blockPos;
			if (state.getBlock() == AtmosphericBlocks.WARM_WALL_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.WARM_MONKEY_BRUSH.get().getDefaultState();
			if (state.getBlock() == AtmosphericBlocks.HOT_WALL_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.HOT_MONKEY_BRUSH.get().getDefaultState();
			if (state.getBlock() == AtmosphericBlocks.SCALDING_WALL_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get().getDefaultState();

			for (int y = 0; y < x / 16; ++y) {
				newBlockPos = newBlockPos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
				if (state.isValidPosition(world, newBlockPos) && world.isAirBlock(newBlockPos)) {
					Direction randomD = Direction.func_239631_a_(random);
					while (!MonkeyBrushFeature.monkeyBrushState(state, randomD).isValidPosition(world, newBlockPos)) {
						randomD = Direction.func_239631_a_(random);
					}
					world.setBlockState(newBlockPos, MonkeyBrushFeature.monkeyBrushState(state, randomD), 2);
					break label;
				}
			}
		}
	}
}
