package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericParticleTypes;
import com.teamabnormals.blueprint.common.block.wood.BlueprintLeavesBlock;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class FloweringMoradoLeavesBlock extends BlueprintLeavesBlock {
	public FloweringMoradoLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		Random rand = new Random();
		if (player.getItemInHand(handIn).getItem() == Items.SHEARS) {
			player.getItemInHand(handIn).hurtAndBreak(1, player, (onBroken) -> {
				onBroken.broadcastBreakEvent(handIn);
			});
			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(state, AtmosphericBlocks.MORADO_LEAVES.get().defaultBlockState()));
			popResource(worldIn, pos, new ItemStack(AtmosphericItems.YELLOW_BLOSSOMS.get(), 1 + rand.nextInt(3)));

			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
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
