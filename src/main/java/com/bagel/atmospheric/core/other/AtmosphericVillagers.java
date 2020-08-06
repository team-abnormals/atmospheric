package com.bagel.atmospheric.core.other;

import com.bagel.atmospheric.core.Atmospheric;
import com.bagel.atmospheric.core.registry.AtmosphericBiomes;

import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.villager.IVillagerType;

public class AtmosphericVillagers {

    public static void setupVillagerTypes() {
        VillagerTrades.VILLAGER_DEFAULT_TRADES.isEmpty();
        IVillagerType dunes = IVillagerType.register(Atmospheric.MODID + ":dunes");
        
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST.get(), IVillagerType.JUNGLE);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_MOUNTAINS.get(), IVillagerType.JUNGLE);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_PLATEAU.get(), IVillagerType.JUNGLE);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.SPARSE_RAINFOREST_PLATEAU.get(), IVillagerType.JUNGLE);

        IVillagerType.BY_BIOME.put(AtmosphericBiomes.DUNES.get(), dunes);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.DUNES_HILLS.get(), dunes);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.FLOURISHING_DUNES.get(), dunes);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.ROCKY_DUNES.get(), dunes);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.ROCKY_DUNES_HILLS.get(), dunes);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.PETRIFIED_DUNES.get(), dunes);
    }
}