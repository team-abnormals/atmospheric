package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.registry.AtmosphericStructureProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureTemplate.class)
public class StructureTemplateMixin {

	@Inject(method = "placeInWorld(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;Lnet/minecraft/util/RandomSource;I)Z", at = @At(value = "HEAD"))
	private void preventWaterloggingSpread(ServerLevelAccessor level, BlockPos pos1, BlockPos pos2, StructurePlaceSettings settings, RandomSource random, int flag, CallbackInfoReturnable<Boolean> cir) {
		if (settings.getProcessors().stream().anyMatch(processor -> ((StructureProcessorAccessor) processor).callGetType() == AtmosphericStructureProcessors.PREVENT_WATERLOGGING_SPREAD.get())) {
			settings.setKeepLiquids(false);
		}
	}
}