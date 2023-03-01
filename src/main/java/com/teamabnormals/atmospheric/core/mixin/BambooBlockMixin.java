package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BambooBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BambooBlock.class)
public abstract class BambooBlockMixin {

	@Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
	private void getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
		if (cir.getReturnValue() == null) {
			BlockState state = context.getLevel().getBlockState(context.getClickedPos().below());
			if (state.is(AtmosphericBlockTags.SNOWY_BAMBOO_PLANTABLE_ON)) {
				cir.setReturnValue(AtmosphericBlocks.SNOWY_BAMBOO.get().getStateForPlacement(context));
			}
		}
	}
}