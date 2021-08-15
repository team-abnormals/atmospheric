package com.minecraftabnormals.atmospheric.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.MonkeyBrushFeature;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericEffects;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
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

import java.util.Map;
import java.util.Random;

public class WallMonkeyBrushBlock extends AbnormalsFlowerBlock implements IGrowable, IPlantable {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

	public WallMonkeyBrushBlock(Properties properties) {
		super(AtmosphericEffects.RELIEF::get, 120, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND || block.getBlock().is(BlockTags.LOGS);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.relative(state.getValue(FACING).getOpposite());
		return this.mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (context.getClickedFace().getAxis().isVertical())
			return null;
		Direction direction = context.getClickedFace();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = this.defaultBlockState().setValue(FACING, context.getClickedFace()).setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double) blockpos.getY() > 0.5D)) ? Half.TOP : Half.BOTTOM);
		return blockstate;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getShapeForState(state);
	}

	public static VoxelShape getShapeForState(BlockState state) {
		return SHAPES.get(state.getValue(FACING));
	}

	public AbstractBlock.OffsetType getOffsetType() {
		return AbstractBlock.OffsetType.NONE;
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(World world, Random random, BlockPos blockPos, BlockState blockState) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld world, Random random, BlockPos blockPos, BlockState state) {
		for (int x = 0; x < 64; ++x) {
			if (state.getBlock() == AtmosphericBlocks.WARM_WALL_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.WARM_MONKEY_BRUSH.get().defaultBlockState();
			if (state.getBlock() == AtmosphericBlocks.HOT_WALL_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.HOT_MONKEY_BRUSH.get().defaultBlockState();
			if (state.getBlock() == AtmosphericBlocks.SCALDING_WALL_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get().defaultBlockState();

			for (int y = 0; y < x / 16; ++y) {
				blockPos = blockPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
				if (state.canSurvive(world, blockPos) && world.isEmptyBlock(blockPos)) {
					Direction randomD = Direction.getRandom(random);
					while (!MonkeyBrushFeature.monkeyBrushState(state, randomD).canSurvive(world, blockPos)) {
						randomD = Direction.getRandom(random);
					}
					world.setBlock(blockPos, MonkeyBrushFeature.monkeyBrushState(state, randomD), 2);
					return;
				}
			}
		}
	}
}
