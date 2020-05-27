package com.bagel.atmospheric.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TallAloeVeraBlock extends DoublePlantBlock implements IGrowable {
	public static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    public static final IntegerProperty AGE = IntegerProperty.create("age", 6, 8);
    
	public TallAloeVeraBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getStateContainer().getBaseState().with(AGE, 6).with(HALF, DoubleBlockHalf.LOWER));
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Vec3d vec3d = state.getOffset(worldIn, pos);
        return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, AGE);
    }
	
	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}
	
	@Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
		DoubleBlockHalf half = state.get(HALF);
		if (half == DoubleBlockHalf.UPPER) {
	        return block instanceof AridSandBlock || block instanceof TallAloeVeraBlock;
		} else {
	        return block instanceof AridSandBlock;
		}
    }

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return this.isValidGround(state, worldIn, pos);
	}
	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}
	
	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		int age = state.get(AGE);
		DoubleBlockHalf half = state.get(HALF);
		if (age < 8) {
			if (half == DoubleBlockHalf.LOWER) {
				world.setBlockState(pos, state.with(AGE, age + 1));
				world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER).with(AGE, age + 1));
			} else if (half == DoubleBlockHalf.UPPER) {
				world.setBlockState(pos, state.with(AGE, age + 1));
				world.setBlockState(pos.down(), state.with(HALF, DoubleBlockHalf.LOWER).with(AGE, age + 1));
			}
		}
	}
}
