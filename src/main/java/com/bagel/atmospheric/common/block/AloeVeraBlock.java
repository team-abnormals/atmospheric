package com.bagel.atmospheric.common.block;

import java.util.Random;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class AloeVeraBlock extends BushBlock implements IGrowable {
	
	public static final VoxelShape SHAPE_SMALL = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
	public static final VoxelShape SHAPE_MEDIUM = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	public static final VoxelShape SHAPE_LARGE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;

	public AloeVeraBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getStateContainer().getBaseState().with(AGE, 0));
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int age = state.get(AGE);
		VoxelShape shape = age >= 4 ? SHAPE_LARGE : age >= 2 ? SHAPE_MEDIUM : SHAPE_SMALL;
		Vec3d vec3d = state.getOffset(worldIn, pos);
        return shape.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
	
	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}
	
	@Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block instanceof AridSandBlock;
    }

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}
	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		int age = state.get(AGE);
		if (age < 5) world.setBlockState(pos, state.with(AGE, age + 1));
		else if (world.getBlockState(pos.up()).isAir()) {
			placeAt(world, pos, 2);
		}
	}
	
	public void placeAt(IWorld world, BlockPos pos, int flags) {
		world.setBlockState(pos, AtmosphericBlocks.TALL_ALOE_VERA.get().getDefaultState().with(TallAloeVeraBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlockState(pos.up(), AtmosphericBlocks.TALL_ALOE_VERA.get().getDefaultState().with(TallAloeVeraBlock.HALF, DoubleBlockHalf.UPPER), flags);
	}
}
