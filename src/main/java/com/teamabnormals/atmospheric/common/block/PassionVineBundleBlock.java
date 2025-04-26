package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;

public class PassionVineBundleBlock extends Block {
	protected final RandomSource rand = RandomSource.create();

	public PassionVineBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
		entityIn.causeFallDamage(fallDistance, rand.nextFloat(), worldIn.damageSources().fall());
	}

	@Override
	public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
		super.playerDestroy(worldIn, player, pos, state, te, stack);
		if (EnchantmentHelper.hasTag(stack, EnchantmentTags.PREVENTS_BEE_SPAWNS_WHEN_MINING) || stack.is(Tags.Items.TOOLS_SHEAR)) {
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
				direction = direction.getClockWise();
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