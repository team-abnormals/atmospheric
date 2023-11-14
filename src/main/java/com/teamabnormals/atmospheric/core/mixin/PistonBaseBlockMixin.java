package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.common.block.CurrantStalkBundleBlock;
import com.teamabnormals.atmospheric.common.block.DragonRootsBlock;
import com.teamabnormals.atmospheric.common.block.OrangeBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PistonBaseBlock.class)
public abstract class PistonBaseBlockMixin {

	@Redirect(method = "moveBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/piston/PistonBaseBlock;dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;)V"))
	private void moveBlocks(BlockState state, LevelAccessor levelAccessor, BlockPos pos, BlockEntity entity) {
		if (!(state.getBlock() instanceof OrangeBlock)) {
			Block.dropResources(state, levelAccessor, pos, entity);
		}
		if (levelAccessor instanceof Level level) {
			if (state.getBlock() instanceof CurrantStalkBundleBlock block) {
				CurrantStalkBundleBlock.breakLeaves(level, pos);
				block.breakStalks(state, level, pos);
			}

			if (state.getBlock() instanceof OrangeBlock) {
				OrangeBlock.createVaporCloud(level, new Vec3(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F), state.is(AtmosphericBlocks.BLOOD_ORANGE.get()));
			}

			if (state.is(AtmosphericBlocks.DRAGON_ROOTS.get()) && DragonRootsBlock.hasFruit(state)) {
				DragonRootsBlock.dropFruits(state, level, pos);
			}
		}
	}
}