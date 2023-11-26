package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

	@Redirect(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;shouldSnow(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z"))
	private boolean shouldSnow(Biome biome, LevelReader level, BlockPos pos) {
		if (!biome.warmEnoughToRain(pos)) {
			if (pos.getY() >= level.getMinBuildHeight() && pos.getY() < level.getMaxBuildHeight() && level.getBrightness(LightLayer.BLOCK, pos) < 10) {
				BlockState state = level.getBlockState(pos);
				if (state.isAir() || state.is(BlockTags.REPLACEABLE_PLANTS)) {
					BlockState belowState = level.getBlockState(pos.below());
					Block newBlock = belowState.is(Blocks.CACTUS) ? AtmosphericBlocks.SNOWY_CACTUS.get() : belowState.is(AtmosphericBlocks.BARREL_CACTUS.get()) ? AtmosphericBlocks.SNOWY_BARREL_CACTUS.get() : null;

					if (newBlock != null && level instanceof ServerLevel serverLevel) {
						serverLevel.setBlockAndUpdate(pos.below(), BlockUtil.transferAllBlockStates(belowState, newBlock.defaultBlockState()));
					}
				}
			}
		}

		return biome.shouldSnow(level, pos);
	}
}