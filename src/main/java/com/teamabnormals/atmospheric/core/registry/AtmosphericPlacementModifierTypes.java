package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.levelgen.placement.InSquareCenterPlacement;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AtmosphericPlacementModifierTypes {
	public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPES = DeferredRegister.create(Registry.PLACEMENT_MODIFIER_REGISTRY, Atmospheric.MOD_ID);

	public static final RegistryObject<PlacementModifierType<InSquareCenterPlacement>> IN_SQUARE_CENTER = PLACEMENT_MODIFIER_TYPES.register("in_square_center", () -> (PlacementModifierType<InSquareCenterPlacement>) () -> InSquareCenterPlacement.CODEC);
}
