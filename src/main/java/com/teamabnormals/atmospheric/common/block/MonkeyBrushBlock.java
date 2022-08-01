package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.common.levelgen.feature.MonkeyBrushFeature;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class MonkeyBrushBlock extends BlueprintFlowerBlock implements BonemealableBlock, IPlantable {
	public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.VERTICAL);

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

	public MonkeyBrushBlock(Properties properties) {
		super(AtmosphericMobEffects.RELIEF, 6, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.is(AtmosphericBlockTags.MONKEY_BRUSH_PLACEABLE);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		return this.mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return context.getClickedFace().getAxis().isVertical() ? this.defaultBlockState().setValue(FACING, context.getClickedFace()) : this.defaultBlockState();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(worldIn, pos);
		return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
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
