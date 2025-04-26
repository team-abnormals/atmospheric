package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericParticleTypes;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.Tags;

public class FloweringMoradoLeavesBlock extends LeavesBlock {

	public FloweringMoradoLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (stack.is(Tags.Items.TOOLS_SHEAR)) {
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
			level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(state, AtmosphericBlocks.MORADO_LEAVES.get().defaultBlockState()));
			popResource(level, pos, new ItemStack(AtmosphericItems.YELLOW_BLOSSOMS.get(), 1 + player.getRandom().nextInt(3)));
			return ItemInteractionResult.SUCCESS;
		} else {
			return super.useItemOn(stack, state, level, pos, player, hand, hit);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
		super.animateTick(stateIn, worldIn, pos, rand);

		int color = worldIn.getBiome(pos).value().getFoliageColor();

		double d0 = (color >> 16 & 255) / 255.0F;
		double d1 = (color >> 8 & 255) / 255.0F;
		double d2 = (color & 255) / 255.0F;

		if (rand.nextInt(40) == 0) {
			BlockPos blockpos = pos.below();
			if (worldIn.isEmptyBlock(blockpos)) {
				double d3 = ((float) pos.getX() + rand.nextFloat());
				double d4 = (double) pos.getY() - 0.05D;
				double d6 = ((float) pos.getZ() + rand.nextFloat());
				worldIn.addParticle(AtmosphericParticleTypes.MORADO_BLOSSOM.get(), d3, d4, d6, d0, d1, d2);
			}
		}
	}
}
