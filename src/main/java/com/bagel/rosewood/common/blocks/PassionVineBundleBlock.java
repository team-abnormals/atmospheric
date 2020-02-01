package com.bagel.rosewood.common.blocks;

import com.bagel.rosewood.core.registry.RosewoodBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PassionVineBundleBlock extends Block {

	public PassionVineBundleBlock(Properties properties) {
		super(properties);
	}
	
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		entityIn.fall(fallDistance, 0.2F);	
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		
		int counter = 9;
		BlockPos nextPos = pos.offset(Direction.DOWN);
		Block nextBlock = worldIn.getBlockState(nextPos).getBlock();
		//Block nextSupporter = worldIn.getBlockState(nextPos.offset(player.getHorizontalFacing())).getBlock();
		BlockState vine = RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, player.getHorizontalFacing().getOpposite());
		
		//counter = 8;
		//worldIn.getPendingBlockTicks().scheduleTick(pos, RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, player.getHorizontalFacing().getOpposite()).getBlock(), 20); 
		while (counter > 0) {
			if (nextBlock == Blocks.AIR) {
				worldIn.setBlockState(nextPos, vine);
				counter = counter - 1;
				nextPos = nextPos.offset(Direction.DOWN);
				nextBlock = worldIn.getBlockState(nextPos).getBlock();
			} else {
				break;
			}
		}
		spawnAsEntity(worldIn, nextPos, new ItemStack(RosewoodBlocks.PASSION_VINE.get(), counter));
		super.onBlockHarvested(worldIn, pos, state, player);
	}
}

//if (PassionVineBlock.canAttachTo(RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, playerDirection.getOpposite()), iworldreader, pos.offset(Direction.DOWN))) {
//IWorldReader iworldreader = worldIn.getWorld();

//}