package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.common.block.CurrantStalkBundleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PistonBaseBlock.class)
public abstract class PistonBaseBlockMixin {

	@Redirect(method = "moveBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/piston/PistonBaseBlock;dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;)V"))
	private void moveBlocks(BlockState state, LevelAccessor level, BlockPos pos, BlockEntity entity) {
		Block.dropResources(state, level, pos, entity);
		if (state.getBlock() instanceof CurrantStalkBundleBlock block) {
			if (!level.isClientSide()) {
				CurrantStalkBundleBlock.breakLeaves(level, pos);
				block.breakStalks(state, (Level) level, pos);
			}
		}
	}
}