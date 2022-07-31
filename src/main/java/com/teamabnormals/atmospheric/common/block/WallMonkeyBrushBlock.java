package com.teamabnormals.atmospheric.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.teamabnormals.atmospheric.common.levelgen.feature.MonkeyBrushFeature;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;

import java.util.Map;
import java.util.Random;

public class WallMonkeyBrushBlock extends BlueprintFlowerBlock implements BonemealableBlock, IPlantable {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

	public WallMonkeyBrushBlock(Properties properties) {
		super(AtmosphericMobEffects.RELIEF::get, 120, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND || state.is(BlockTags.LOGS);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.relative(state.getValue(FACING).getOpposite());
		return this.mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getClickedFace().getAxis().isVertical())
			return null;
		Direction direction = context.getClickedFace();
		BlockPos blockpos = context.getClickedPos();
		return this.defaultBlockState().setValue(FACING, context.getClickedFace()).setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double) blockpos.getY() > 0.5D)) ? Half.TOP : Half.BOTTOM);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return getShapeForState(state);
	}

	public static VoxelShape getShapeForState(BlockState state) {
		return SHAPES.get(state.getValue(FACING));
	}

	@Override
	public BlockBehaviour.OffsetType getOffsetType() {
		return BlockBehaviour.OffsetType.NONE;
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level world, Random random, BlockPos blockPos, BlockState blockState) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel world, Random random, BlockPos blockPos, BlockState state) {
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
