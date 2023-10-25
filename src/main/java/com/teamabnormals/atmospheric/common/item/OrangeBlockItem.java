package com.teamabnormals.atmospheric.common.item;

import com.teamabnormals.atmospheric.common.block.StemmedOrangeBlock;
import com.teamabnormals.atmospheric.common.entity.OrangeVaporCloud;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class OrangeBlockItem extends ItemNameBlockItem {

	public OrangeBlockItem(Block block, Properties builder) {
		super(block, builder);
	}

	@Override
	public InteractionResult place(BlockPlaceContext context) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos());
		return context.getPlayer().isSecondaryUseActive() || state.is(this.getBlock()) && state.getValue(StemmedOrangeBlock.ORANGES) < 2 ? super.place(context) : InteractionResult.FAIL;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (!level.isClientSide) {
			Vec3 pos = entity.position();
			createVaporCloud(level, pos);
		}

		return super.finishUsingItem(stack, level, entity);
	}

	public static void createVaporCloud(Level level, Vec3 pos) {
		OrangeVaporCloud cloud = new OrangeVaporCloud(level, pos.x(), pos.y(), pos.z());
		cloud.setRadius(1.5F);
		cloud.setDuration(600);
		cloud.setRadiusPerTick(-cloud.getRadius() / (float) cloud.getDuration());
		level.addFreshEntity(cloud);
	}
}
