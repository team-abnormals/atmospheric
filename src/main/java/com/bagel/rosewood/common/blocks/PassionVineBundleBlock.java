package com.bagel.rosewood.common.blocks;

import com.bagel.rosewood.core.registry.RosewoodBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class PassionVineBundleBlock extends Block {

	public PassionVineBundleBlock(Properties properties) {
		super(properties);
	}
	
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		entityIn.fall(fallDistance, 0.2F);	
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		
		
		BlockPos nextPos = pos.offset(Direction.DOWN);
		Block nextBlock = worldIn.getBlockState(nextPos).getBlock();
		int counter = 9;
		
  	    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);

		if (!worldIn.getBlockState(pos.offset(player.getHorizontalFacing())).getBlock().isAir(worldIn.getBlockState(pos.offset(player.getHorizontalFacing())))) {
			BlockState vine = RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, player.getHorizontalFacing().getOpposite());
			worldIn.setBlockState(pos, vine);
			counter = 8;
			while (counter > 0) {
				if (nextBlock.isAir(worldIn.getBlockState(nextPos))) {
					worldIn.setBlockState(nextPos, vine);
					counter = counter - 1;
					nextPos = nextPos.offset(Direction.DOWN);
					nextBlock = worldIn.getBlockState(nextPos).getBlock();
				} else {
					break;
				}
			}
			if (player.abilities.isCreativeMode == false) {
				spawnAsEntity(worldIn, nextPos, new ItemStack(RosewoodBlocks.PASSION_VINE.get(), counter));
			}
			
		} else {
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			if (player.abilities.isCreativeMode == false) {
				spawnAsEntity(worldIn, nextPos, new ItemStack(RosewoodBlocks.PASSION_VINE.get(), 9));
			}
		}
		return true;	
	
	}
}

//if (PassionVineBlock.canAttachTo(RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, playerDirection.getOpposite()), iworldreader, pos.offset(Direction.DOWN))) {
//IWorldReader iworldreader = worldIn.getWorld();

//}