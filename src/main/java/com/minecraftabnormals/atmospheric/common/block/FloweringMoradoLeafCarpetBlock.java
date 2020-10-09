package com.minecraftabnormals.atmospheric.common.block;

import java.util.Random;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.abnormals_core.common.blocks.LeafCarpetBlock;

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

public class FloweringMoradoLeafCarpetBlock extends LeafCarpetBlock {
	public FloweringMoradoLeafCarpetBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		Random rand = new Random();
		if (player.getHeldItem(handIn).getItem() == Items.SHEARS) {
			player.getHeldItem(handIn).damageItem(1, player, (onBroken) -> {
				onBroken.sendBreakAnimation(handIn);
			});
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, AtmosphericBlocks.MORADO_LEAF_CARPET.get().getDefaultState());
			spawnAsEntity(worldIn, pos, new ItemStack(AtmosphericItems.YELLOW_BLOSSOMS.get(), 1 + rand.nextInt(3)));

			return ActionResultType.SUCCESS;
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}
}
