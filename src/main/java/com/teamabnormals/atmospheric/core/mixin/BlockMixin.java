package com.teamabnormals.atmospheric.core.mixin;

import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public abstract class BlockMixin {

	// TODO: Make sure it works
//	@Inject(method = "canSustainPlant", at = @At("RETURN"), cancellable = true, remap = false)
//	private void canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable, CallbackInfoReturnable<Boolean> cir) {
//		if (plantable.getPlant(world, pos.relative(facing)).getBlock() == Blocks.CACTUS && state.is(AtmosphericBlocks.SNOWY_CACTUS.get())) {
//			cir.setReturnValue(true);
//		}
//	}
}