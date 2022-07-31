package com.teamabnormals.atmospheric.common.item;

import com.teamabnormals.atmospheric.common.block.WaterHyacinthBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class WaterHyacinthItem extends BlockItem {
	public WaterHyacinthItem(Item.Properties builder) {
		super(AtmosphericBlocks.WATER_HYACINTH.get(), builder);
	}

	public InteractionResult useOn(UseOnContext context) {
		return InteractionResult.PASS;
	}

	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		HitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
		if (raytraceresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			if (raytraceresult.getType() == HitResult.Type.BLOCK) {
				BlockHitResult blockraytraceresult = (BlockHitResult) raytraceresult;
				BlockPos blockpos = blockraytraceresult.getBlockPos();
				Direction direction = blockraytraceresult.getDirection();
				if (!worldIn.mayInteract(playerIn, blockpos) || !playerIn.mayUseItemAt(blockpos.relative(direction), direction, itemstack)) {
					return InteractionResultHolder.fail(itemstack);
				}

				BlockPos blockpos1 = blockpos.above();
				if (worldIn.getBlockState(blockpos).is(Blocks.WATER) && worldIn.getBlockState(blockpos1).isAir()) {
					net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.create(worldIn.dimension(), worldIn, blockpos1);
					if (!worldIn.isClientSide())
						((WaterHyacinthBlock) AtmosphericBlocks.WATER_HYACINTH.get()).placeAt(worldIn, blockpos1, 18);
					if (net.minecraftforge.event.ForgeEventFactory.onBlockPlace(playerIn, blocksnapshot, Direction.UP)) {
						blocksnapshot.restore(true, false);
						return InteractionResultHolder.fail(itemstack);
					}

					if (playerIn instanceof ServerPlayer) {
						CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) playerIn, blockpos1, itemstack);
					}

					if (!playerIn.getAbilities().instabuild) {
						itemstack.shrink(1);
					}

					playerIn.awardStat(Stats.ITEM_USED.get(this));
					worldIn.playSound(playerIn, blockpos, SoundEvents.LILY_PAD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
					return InteractionResultHolder.success(itemstack);
				}
			}

			return InteractionResultHolder.fail(itemstack);
		}
	}
}
