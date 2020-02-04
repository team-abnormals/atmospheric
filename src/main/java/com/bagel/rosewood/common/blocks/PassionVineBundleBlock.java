package com.bagel.rosewood.common.blocks;

import javax.annotation.Nullable;

import com.bagel.rosewood.core.registry.RosewoodBlocks;

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

	public PassionVineBundleBlock(Properties properties) {
		super(properties);
	}
	
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		entityIn.fall(fallDistance, 0.2F);	
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
	      }
}

//if (PassionVineBlock.canAttachTo(RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, playerDirection.getOpposite()), iworldreader, pos.offset(Direction.DOWN))) {
//IWorldReader iworldreader = worldIn.getWorld();

//}