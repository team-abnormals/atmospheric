package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.levelgen.structure.processor.PreventWaterloggingSpreadProcessor;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AtmosphericStructureProcessors {
	public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, Atmospheric.MOD_ID);

	public static final RegistryObject<StructureProcessorType<PreventWaterloggingSpreadProcessor>> PREVENT_WATERLOGGING_SPREAD = STRUCTURE_PROCESSORS.register("prevent_waterlogging_spread", () -> () -> PreventWaterloggingSpreadProcessor.CODEC);
}