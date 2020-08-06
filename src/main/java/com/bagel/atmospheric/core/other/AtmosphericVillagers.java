package com.bagel.atmospheric.core.other;

import com.bagel.atmospheric.core.registry.AtmosphericBiomes;

import net.minecraft.entity.villager.IVillagerType;

public class AtmosphericVillagers {
    //public static final IVillagerType DUNES = IVillagerType.register(Atmospheric.MODID + ":dunes");

    public static void setupVillagerTypes() {

        IVillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST.get(), IVillagerType.JUNGLE);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_MOUNTAINS.get(), IVillagerType.JUNGLE);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_PLATEAU.get(), IVillagerType.JUNGLE);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.SPARSE_RAINFOREST_PLATEAU.get(), IVillagerType.JUNGLE);

        IVillagerType.BY_BIOME.put(AtmosphericBiomes.DUNES.get(), IVillagerType.DESERT);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.DUNES_HILLS.get(), IVillagerType.DESERT);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.FLOURISHING_DUNES.get(), IVillagerType.DESERT);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.ROCKY_DUNES.get(), IVillagerType.DESERT);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.ROCKY_DUNES_HILLS.get(), IVillagerType.DESERT);
        IVillagerType.BY_BIOME.put(AtmosphericBiomes.PETRIFIED_DUNES.get(), IVillagerType.DESERT);
    }
}
