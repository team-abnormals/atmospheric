package com.bagel.atmospheric.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class AridSandBlock extends FallingBlock {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private final int color;

    public AridSandBlock(int color, Properties properties) {
        super(properties);
        this.color = color;

    }
    
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    	builder.add(FACING);
    }
    
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, 0.2F);
     }

    @Override
    public int getDustColor(BlockState state) {
        return this.color;
    }
    
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
    	return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

	@Override
    public boolean canSustainPlant(BlockState state, IBlockReader blockReader, BlockPos pos, Direction direction, IPlantable iPlantable) {
        final BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        final PlantType plantType = iPlantable.getPlantType(blockReader, plantPos);
        switch (plantType) {
            case Desert: {
                return true;
            }
            case Water: {
                return blockReader.getBlockState(pos).getMaterial() == Material.WATER && blockReader.getBlockState(pos) == getDefaultState();
            }
            case Beach: {
                return ((blockReader.getBlockState(pos.east()).getMaterial() == Material.WATER || blockReader.getBlockState(pos.east()).has(BlockStateProperties.WATERLOGGED))
                        || (blockReader.getBlockState(pos.west()).getMaterial() == Material.WATER || blockReader.getBlockState(pos.west()).has(BlockStateProperties.WATERLOGGED))
                        || (blockReader.getBlockState(pos.north()).getMaterial() == Material.WATER || blockReader.getBlockState(pos.north()).has(BlockStateProperties.WATERLOGGED))
                        || (blockReader.getBlockState(pos.south()).getMaterial() == Material.WATER || blockReader.getBlockState(pos.south()).has(BlockStateProperties.WATERLOGGED)));
            }
            case Plains: {
            	return false;
            }
		case Cave:
			return false;
		case Crop:
			return false;
		case Nether:
			return false;
		default:
			return false;
        }
    }
}