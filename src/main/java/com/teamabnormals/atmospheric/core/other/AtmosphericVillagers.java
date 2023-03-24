package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;

public class AtmosphericVillagers {

	public static void registerVillagerTypes() {
		VillagerTrades.TRADES.isEmpty();
		VillagerType shrubland = VillagerType.register(Atmospheric.MOD_ID + ":shrubland");

		VillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST.getKey(), VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.SPARSE_RAINFOREST.getKey(), VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_BASIN.getKey(), VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.SPARSE_RAINFOREST_BASIN.getKey(), VillagerType.JUNGLE);

		VillagerType.BY_BIOME.put(AtmosphericBiomes.SHRUBLAND.getKey(), shrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.DUNES.getKey(), shrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.FLOURISHING_DUNES.getKey(), shrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.ROCKY_DUNES.getKey(), shrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.PETRIFIED_DUNES.getKey(), shrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.SPINY_THICKET.getKey(), shrubland);
	}
}