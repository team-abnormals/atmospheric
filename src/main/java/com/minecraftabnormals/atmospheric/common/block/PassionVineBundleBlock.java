package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Random;

public class PassionVineBundleBlock extends Block {
	protected final Random rand = new Random();

	public PassionVineBundleBlock(Properties properties) {
		super(properties);
	}

	public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
		entityIn.causeFallDamage(fallDistance, rand.nextFloat(), DamageSource.FALL);
	}

	@Override
	public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
		super.playerDestroy(worldIn, player, pos, state, te, stack);
		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) != 0 || stack.getItem() == Items.SHEARS) {
			worldIn.removeBlock(pos, false);
			return;
		}
		BlockPos nextPos = pos.relative(Direction.DOWN);
		BlockState nextBlock = worldIn.getBlockState(nextPos);
		int counter = 9;
		Direction direction = player.getDirection();
		//Block blockreader = worldIn.getBlockState(pos.offset(direction)).getBlock();
		if (!worldIn.getBlockState(pos.relative(direction)).isAir()
				&& AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction.getOpposite()).canSurvive(worldIn, pos)
		) {
			BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction.getOpposite());
			worldIn.setBlockAndUpdate(pos, vine);
			counter = 8;
			while (counter > 0) {
				if (nextBlock.isAir()) {
					worldIn.setBlockAndUpdate(nextPos, vine);
					counter = counter - 1;
					nextPos = nextPos.relative(Direction.DOWN);
					nextBlock = worldIn.getBlockState(nextPos);
				} else {
					break;
				}
			}

			if (!player.getAbilities().instabuild) {
				popResource(worldIn, nextPos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));
			}
		} else {
			int k1 = 0;
			while (k1 < 3) {
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
				if (!worldIn.getBlockState(pos.relative(direction)).isAir()
						&& AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction.getOpposite()).canSurvive(worldIn, pos)
				) {
					BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction.getOpposite());
					worldIn.setBlockAndUpdate(pos, vine);
					counter = 8;
					while (counter > 0) {
						if (nextBlock.isAir()) {
							worldIn.setBlockAndUpdate(nextPos, vine);
							counter = counter - 1;
							nextPos = nextPos.relative(Direction.DOWN);
							nextBlock = worldIn.getBlockState(nextPos);
						} else {
							break;
						}
					}
					if (!player.getAbilities().instabuild) {
						popResource(worldIn, nextPos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));
					}
					break;
				} else if (k1 >= 3) {
					worldIn.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
					if (!player.getAbilities().instabuild) {
						popResource(worldIn, nextPos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 9));
					}
					break;
				}
			}
		}
	}
}