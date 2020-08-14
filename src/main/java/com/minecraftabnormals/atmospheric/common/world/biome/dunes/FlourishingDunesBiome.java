package com.minecraftabnormals.atmospheric.common.world.biome.dunes;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public final class FlourishingDunesBiome extends Biome {
	public FlourishingDunesBiome(Biome.Builder builder) {
        super(builder);
		
        this.func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL_DESERT);
        DefaultBiomeFeatures.func_235196_b_(this); // MINESHAFTS & STRONGHOLDS

        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addMonsterRooms(this);

		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		AtmosphericBiomeFeatures.addFossils(this);
		AtmosphericBiomeFeatures.addDuneGrassPatches(this);

		AtmosphericBiomeFeatures.addDeadBushes(this, 4);
		AtmosphericBiomeFeatures.addMelons(this);
		DefaultBiomeFeatures.addMushrooms(this);
		AtmosphericBiomeFeatures.addBeehiveYuccaTrees(this, 0, 0.25F, 1);
		AtmosphericBiomeFeatures.addBabyYuccaTrees(this, 2, 0.05F, 1);
		AtmosphericBiomeFeatures.addGilias(this, 18);
		AtmosphericBiomeFeatures.addAloeVera(this, 6);
		AtmosphericBiomeFeatures.addBarrelCactus(this, 0, 0.5F, 4);
		AtmosphericBiomeFeatures.addDuneRocks(this, 1, 3);
		AtmosphericBiomeFeatures.addYuccaFlower(this, 5);

		DefaultBiomeFeatures.addFreezeTopLayer(this);

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 19, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 1, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.HUSK, 80, 4, 4));
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public int getSkyColor() {
		return 14988944;
	}
}
