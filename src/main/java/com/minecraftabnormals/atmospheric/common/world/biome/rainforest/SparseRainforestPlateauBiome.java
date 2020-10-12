package com.minecraftabnormals.atmospheric.common.world.biome.rainforest;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class SparseRainforestPlateauBiome extends AbstractRainforestBiome {
    public SparseRainforestPlateauBiome(Biome.Builder builder) {
        super(builder);
    }

	@Override
	public void addFeatures() {
        DefaultBiomeFeatures.func_235196_b_(this); // MINESHAFTS & STRONGHOLDS

        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addTaigaRocks(this);
        
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addTaigaLargeFerns(this);
        AtmosphericBiomeFeatures.addSparseRainforestPlateauTrees(this, 0, 3, 0, 1);
        AtmosphericBiomeFeatures.addRainforestFlowers(this);
        AtmosphericBiomeFeatures.addRainforestFoliage(this);
        AtmosphericBiomeFeatures.addRainforestWaterFoliage(this);
        
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }
}
