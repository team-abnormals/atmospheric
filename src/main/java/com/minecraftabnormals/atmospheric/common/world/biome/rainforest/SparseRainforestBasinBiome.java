package com.minecraftabnormals.atmospheric.common.world.biome.rainforest;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class SparseRainforestBasinBiome extends Biome {
    public SparseRainforestBasinBiome(Biome.Builder builder) {
        super(builder);
        
        DefaultBiomeFeatures.func_235196_b_(this); // MINESHAFTS & STRONGHOLDS

        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addSprings(this);
        AtmosphericBiomeFeatures.addPodzol(this);
        DefaultBiomeFeatures.addTaigaRocks(this);
        
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addTaigaLargeFerns(this);
        AtmosphericBiomeFeatures.addRainforestBasinTrees(this, 100);
        AtmosphericBiomeFeatures.addRainforestDoublePlants(this);
        AtmosphericBiomeFeatures.addRainforestFlowers(this);
        AtmosphericBiomeFeatures.addRainforestFoliage(this);
        AtmosphericBiomeFeatures.addSparseRainforestBasinWaterFoliage(this);
        
        DefaultBiomeFeatures.addFreezeTopLayer(this);
        
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 8, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.WOLF, 8, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PARROT, 40, 1, 2));
        
        addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
        
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.DROWNED, 5, 1, 1));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }
}
