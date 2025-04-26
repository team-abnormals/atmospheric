package com.teamabnormals.atmospheric.core.mixin;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = {
		"net/minecraft/world/item/SwordItem",
		"net/minecraft/world/item/ShearsItem"
})
public abstract class SwordItemMixin {

	// TODO: Grr arg
//	@Inject(method = "isCorrectToolForDrops", at = @At("RETURN"), cancellable = true)
//	private void isCorrectToolForDrops(BlockState state, CallbackInfoReturnable<Boolean> cir) {
//		if (state.is(AtmosphericBlocks.GRIMWEB.get())) {
//			cir.setReturnValue(true);
//		}
//	}
//
//	@Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
//	private void getDestroySpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
//		if (state.is(AtmosphericBlocks.GRIMWEB.get())) {
//			cir.setReturnValue(15.0F);
//		}
//	}
}