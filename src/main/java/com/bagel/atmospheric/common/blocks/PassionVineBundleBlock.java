package com.bagel.atmospheric.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PassionVineBundleBlock extends Block {
	protected final Random rand = new Random();
	
	public PassionVineBundleBlock(Properties properties) {
		super(properties);
	}
	
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		entityIn.fall(fallDistance, rand.nextFloat());	
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, state, te, stack);
	    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) != 0 || stack.getItem() == Items.SHEARS) {
	    	worldIn.removeBlock(pos, false);
	    	return;	
	    }
	    BlockPos nextPos = pos.offset(Direction.DOWN);
	    Block nextBlock = worldIn.getBlockState(nextPos).getBlock();
		int counter = 9;
		Direction direction = player.getHorizontalFacing();
		//Block blockreader = worldIn.getBlockState(pos.offset(direction)).getBlock();
		if (!worldIn.getBlockState(pos.offset(direction)).getBlock().isAir(worldIn.getBlockState(pos.offset(direction))) 
				&& AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite()).isValidPosition(worldIn, pos)
				) {
			BlockState vine = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite());
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
				spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));	
			}	
		} else {
			int k1 = 0;
			while(k1 < 3) {
			if (direction == Direction.NORTH) {
				direction = Direction.EAST;
			} else if (direction == Direction.EAST) {
				direction = Direction.SOUTH;
			} else if (direction == Direction.SOUTH) {
				direction = Direction.WEST;
			} else if (direction == Direction.WEST) {
				direction = Direction.NORTH;
			}
			k1 = k1 + 1;
			if (!worldIn.getBlockState(pos.offset(direction)).getBlock().isAir(worldIn.getBlockState(pos.offset(direction))) 
					&& AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite()).isValidPosition(worldIn, pos)
					) {
				BlockState vine = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite());
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
					spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));	
				}
				break;
			} else if (k1 >= 3){
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
				if (player.abilities.isCreativeMode == false) {
					spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 9));	
				}
				break;
			}
			}
		}	
	}
}