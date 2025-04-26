package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class AtmosphericLootTables {
	public static final ResourceKey<LootTable> ARID_GARDEN = create("chests/arid_garden");
	public static final ResourceKey<LootTable> KOUSA_SANCTUM_FIRE = create("chests/kousa_sanctum_fire");
	public static final ResourceKey<LootTable> KOUSA_SANCTUM_TRAP = create("chests/kousa_sanctum_trap");
	public static final ResourceKey<LootTable> KOUSA_SANCTUM = create("chests/kousa_sanctum");
	public static final ResourceKey<LootTable> VILLAGE_SCRUBLAND_HOUSE = create("chests/village/village_scrubland");
	public static final ResourceKey<LootTable> VILLAGE_COCHINEAL_FARM_DROPPER = create("chests/cochineal_farm_dropper");

	public static final ResourceKey<LootTable> ARID_GARDEN_ARCHAEOLOGY_COMMON = create("archaeology/arid_garden_common");
	public static final ResourceKey<LootTable> ARID_GARDEN_ARCHAEOLOGY_RARE = create("archaeology/arid_garden_rare");

	private static ResourceKey<LootTable> create(String name) {
		return ResourceKey.create(Registries.LOOT_TABLE, Atmospheric.location(name));
	}
}
