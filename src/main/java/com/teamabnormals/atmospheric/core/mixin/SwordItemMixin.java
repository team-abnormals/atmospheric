package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {
		"net/minecraft/world/item/SwordItem",
		"net/minecraft/world/item/ShearsItem"
})
public abstract class SwordItemMixin {

	@Inject(method = "isCorrectToolForDrops", at = @At("RETURN"), cancellable = true)
	private void isCorrectToolForDrops(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		if (state.is(AtmosphericBlocks.GRIMWEB.get())) {
			cir.setReturnValue(true);
		}
	}
}