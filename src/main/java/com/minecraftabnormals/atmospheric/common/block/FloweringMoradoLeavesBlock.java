package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsLeavesBlock;
import com.minecraftabnormals.abnormals_core.core.util.BlockUtil;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericParticles;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class FloweringMoradoLeavesBlock extends AbnormalsLeavesBlock {
	public FloweringMoradoLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		Random rand = new Random();
		if (player.getHeldItem(handIn).getItem() == Items.SHEARS) {
			player.getHeldItem(handIn).damageItem(1, player, (onBroken) -> {
				onBroken.sendBreakAnimation(handIn);
			});
			worldIn.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, BlockUtil.transferAllBlockStates(state, AtmosphericBlocks.MORADO_LEAVES.get().getDefaultState()));
			spawnAsEntity(worldIn, pos, new ItemStack(AtmosphericItems.YELLOW_BLOSSOMS.get(), 1 + rand.nextInt(3)));

			return ActionResultType.SUCCESS;
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		super.animateTick(stateIn, worldIn, pos, rand);

		int color = worldIn.getBiome(pos).getFoliageColor();

		double d0 = (color >> 16 & 255) / 255.0F;
		double d1 = (color >> 8 & 255) / 255.0F;
		double d2 = (color & 255) / 255.0F;

		if (rand.nextInt(40) == 0) {
			BlockPos blockpos = pos.down();
			if (worldIn.isAirBlock(blockpos)) {
				double d3 = ((float) pos.getX() + rand.nextFloat());
				double d4 = (double) pos.getY() - 0.05D;
				double d6 = ((float) pos.getZ() + rand.nextFloat());
				worldIn.addParticle(AtmosphericParticles.MORADO_BLOSSOM.get(), d3, d4, d6, d0, d1, d2);
			}
		}
	}
}
