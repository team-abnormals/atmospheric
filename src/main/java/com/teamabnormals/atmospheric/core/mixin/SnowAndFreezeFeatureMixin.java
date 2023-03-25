package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SnowAndFreezeFeature.class)
public abstract class SnowAndFreezeFeatureMixin {

	@Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;shouldSnow(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z"))
	private boolean shouldSnow(Biome biome, LevelReader level, BlockPos pos) {
		if (!biome.warmEnoughToRain(pos)) {
			if (pos.getY() >= level.getMinBuildHeight() && pos.getY() < level.getMaxBuildHeight() && level.getBrightness(LightLayer.BLOCK, pos) < 10) {
				if (level.getBlockState(pos).isAir()) {
					BlockState belowState = level.getBlockState(pos.below());
					Block newBlock = belowState.is(Blocks.CACTUS) ? AtmosphericBlocks.SNOWY_CACTUS.get() : belowState.is(AtmosphericBlocks.BARREL_CACTUS.get()) ? AtmosphericBlocks.SNOWY_BARREL_CACTUS.get() : null;

					if (newBlock != null && level instanceof WorldGenLevel worldGenLevel) {
						worldGenLevel.setBlock(pos.below(), BlockUtil.transferAllBlockStates(belowState, newBlock.defaultBlockState()), 2);
					}
				}
			}
		}

		return biome.shouldSnow(level, pos);
	}
}