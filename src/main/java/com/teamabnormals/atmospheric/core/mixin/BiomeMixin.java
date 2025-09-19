package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class BiomeMixin {

	@Shadow
	public abstract boolean warmEnoughToRain(BlockPos pos);

	@Inject(method = "shouldSnow", at = @At("HEAD"))
	private void shouldSnow(LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if (!this.warmEnoughToRain(pos)) {
			if (pos.getY() >= level.getMinBuildHeight() && pos.getY() < level.getMaxBuildHeight() && level.getBrightness(LightLayer.BLOCK, pos) < 10) {
				BlockState state = level.getBlockState(pos);
				if (state.is(AtmosphericBlocks.BARREL_CACTUS.get())) {
					state = level.getBlockState(pos.above());
					pos = pos.above();
				}

				if (state.isAir() || state.is(BlockTags.REPLACEABLE)) {
					BlockState belowState = level.getBlockState(pos.below());
					Block newBlock = belowState.is(Blocks.CACTUS) ? AtmosphericBlocks.SNOWY_CACTUS.get() : belowState.is(AtmosphericBlocks.BARREL_CACTUS.get()) ? AtmosphericBlocks.SNOWY_BARREL_CACTUS.get() : null;

					if (newBlock != null && level instanceof WorldGenLevel worldgenLevel) {
						worldgenLevel.setBlock(pos.below(), BlockUtil.transferAllBlockStates(belowState, newBlock.defaultBlockState()), 3);
					}
				}
			}
		}
	}
}