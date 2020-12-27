package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBiomes;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.villager.VillagerType;

public class AtmosphericVillagers {

	public static void setupVillagerTypes() {
		VillagerTrades.VILLAGER_DEFAULT_TRADES.isEmpty();
		VillagerType dunes = VillagerType.register(Atmospheric.MODID + ":dunes");

		VillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST.getKey(), VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_MOUNTAINS.getKey(), VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_PLATEAU.getKey(), VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.SPARSE_RAINFOREST_PLATEAU.getKey(), VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_BASIN.getKey(), VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.SPARSE_RAINFOREST_BASIN.getKey(), VillagerType.JUNGLE);

		VillagerType.BY_BIOME.put(AtmosphericBiomes.DUNES.getKey(), dunes);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.DUNES_HILLS.getKey(), dunes);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.FLOURISHING_DUNES.getKey(), dunes);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.ROCKY_DUNES.getKey(), dunes);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.ROCKY_DUNES_HILLS.getKey(), dunes);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.PETRIFIED_DUNES.getKey(), dunes);
	}
}