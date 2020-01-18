package com.bagel.rosewood.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.potion.Effect;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class MonkeyBrushBlock extends FlowerBlock implements IGrowable {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	public MonkeyBrushBlock(Effect effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
	      Block block = state.getBlock();
	      return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND || block.getBlock().isIn(BlockTags.LOGS) ;
	   }
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      Vec3d vec3d = state.getOffset(worldIn, pos);
	      return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
	   }
	
	@SuppressWarnings("deprecation")
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
	      return worldIn.getBlockState(pos.up()).isAir();
	   }
	
	public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return true;
    }
	
	public void grow(World world, Random random, BlockPos blockPos, BlockState state) {
            label:
            for(int x = 0; x < 64; ++x) {
                BlockPos newBlockPos = blockPos;

                for(int y = 0; y < x / 16; ++y) {
                    newBlockPos = newBlockPos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                    if (state.isValidPosition(world, newBlockPos) && world.isAirBlock(newBlockPos)) {
                        world.setBlockState(newBlockPos, state);
                        break label;
                    }
                }
            }
        
    }
}
