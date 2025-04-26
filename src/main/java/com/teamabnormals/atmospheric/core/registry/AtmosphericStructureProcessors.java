package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.levelgen.structure.processor.PreventWaterloggingSpreadProcessor;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AtmosphericStructureProcessors {
	public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, Atmospheric.MOD_ID);

	public static final DeferredHolder<StructureProcessorType<?>, StructureProcessorType<PreventWaterloggingSpreadProcessor>> PREVENT_WATERLOGGING_SPREAD = STRUCTURE_PROCESSORS.register("prevent_waterlogging_spread", () -> () -> PreventWaterloggingSpreadProcessor.CODEC);
}