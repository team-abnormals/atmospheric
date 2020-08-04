package com.bagel.atmospheric.common.block;

import java.util.Map;
import java.util.Random;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

public class MonkeyBrushBlock extends FlowerBlock implements IGrowable, IPlantable {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));
	private final Supplier<Effect> stewEffect;
	
	public MonkeyBrushBlock(Supplier<Effect> effect, int effectDuration, Properties properties) {
		super(Effects.BLINDNESS, effectDuration, properties);
	    this.stewEffect = effect;
	    this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP));
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND || block.getBlock().isIn(BlockTags.LOGS);      
	}
	
	public Effect getStewEffect() {
		return this.stewEffect.get();
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction direction = state.get(FACING); 
		if (direction == Direction.DOWN) {
			return false;
		}
		BlockPos blockpos = pos.offset(direction.getOpposite());
	    return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);    
	}
	
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));      
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getFace());
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Vector3d vec3d = state.getOffset(worldIn, pos);
		switch(state.get(FACING)) {
		default:
			return SHAPE;//S.get(state.get(FACING));
		case UP:
			return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
		}	
	}
	
	public static VoxelShape func_220289_j(BlockState state) {
		return SHAPES.get(state.get(FACING));
	}
	
	@SuppressWarnings("deprecation")
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.up()).isAir();
	}
	
	public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return true;
    }
	
	public void grow(ServerWorld world, Random random, BlockPos blockPos, BlockState state) {
		label:
			for(int x = 0; x < 64; ++x) {
                BlockPos newBlockPos = blockPos;

                for(int y = 0; y < x / 16; ++y) {
                    newBlockPos = newBlockPos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                    if (state.isValidPosition(world, newBlockPos) && world.isAirBlock(newBlockPos)) {
                    	Direction randomD = Direction.func_239631_a_(random);
        	        	while (randomD == Direction.DOWN || !state.with(MonkeyBrushBlock.FACING, randomD).isValidPosition(world, newBlockPos)) {
        		        	randomD = Direction.func_239631_a_(random);
        	        	}
                        world.setBlockState(newBlockPos, state.with(FACING, randomD));
                        break label;
                    }
                }
            }  
    }
	   
//	@Override
//	public Vector3d getOffset(IBlockReader access, BlockPos pos) {
//		Block.OffsetType block$offsettype = this.getOffsetType();
//		BlockState state = access.getBlockState(pos);
//		if (state.get(FACING) == Direction.UP) {
//			long i = MathHelper.getCoordinateRandom(pos.getX(), 0, pos.getZ());
//			return new Vector3d(((double)((float)(i & 15L) / 15.0F) - 0.5D) * 0.5D, block$offsettype == Block.OffsetType.XYZ ? ((double)((float)(i >> 4 & 15L) / 15.0F) - 1.0D) * 0.2D : 0.0D, ((double)((float)(i >> 8 & 15L) / 15.0F) - 0.5D) * 0.5D);
//		} else {
//			return Vector3d.ZERO;
//		}
//	}
}
