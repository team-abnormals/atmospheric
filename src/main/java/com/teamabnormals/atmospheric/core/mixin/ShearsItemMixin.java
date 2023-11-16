package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.common.block.YuccaBundleBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public abstract class ShearsItemMixin {

	@Inject(method = "mineBlock", at = @At("RETURN"), cancellable = true)
	private void mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
		if (state.is(AtmosphericBlocks.GRIMWEB.get())) {
			cir.setReturnValue(true);
		}
	}

	@Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
	private void getDestroySpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
		if (state.is(AtmosphericBlocks.PASSION_VINE_BUNDLE.get()) || state.is(AtmosphericBlocks.YUCCA_FLOWER.get()) || state.is(AtmosphericBlocks.TALL_YUCCA_FLOWER.get()) || state.is(AtmosphericBlocks.DRAGON_ROOTS.get())) {
			cir.setReturnValue(15.0F);
		}

		if (state.is(AtmosphericBlocks.CURRANT_STALK_BUNDLE.get()) || state.is(AtmosphericBlocks.CURRANT_STALK_BUNDLE.get()) || state.getBlock() instanceof YuccaBundleBlock) {
			cir.setReturnValue(5.0F);
		}
	}
}