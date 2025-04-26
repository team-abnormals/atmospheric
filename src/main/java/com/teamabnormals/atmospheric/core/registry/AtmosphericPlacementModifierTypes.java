package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.levelgen.placement.InSquareCenterPlacement;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AtmosphericPlacementModifierTypes {
	public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPES = DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, Atmospheric.MOD_ID);

	public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<InSquareCenterPlacement>> IN_SQUARE_CENTER = PLACEMENT_MODIFIER_TYPES.register("in_square_center", () -> () -> InSquareCenterPlacement.CODEC);
}
