package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class AtmosphericStructures {
	public static final ResourceKey<Structure> ARID_GARDEN = createKey("arid_garden");
	public static final ResourceKey<Structure> KOUSA_SANCTUM = createKey("kousa_sanctum");
	public static final ResourceKey<Structure> VILLAGE_SCRUBLAND = createKey("village_scrubland");

	private static ResourceKey<Structure> createKey(String id) {
		return ResourceKey.create(Registry.STRUCTURE_REGISTRY, Atmospheric.location(id));
	}
}
