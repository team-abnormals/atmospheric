package com.bagel.rosewood.common.world.gen.biome;

import com.bagel.rosewood.core.registry.RosewoodFeatures;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

public class RosewoodForestBiome extends Biome {
    @SuppressWarnings("unchecked")
	public RosewoodForestBiome(Builder builder) {
        super(builder);
        this.addStructure(Feature.JUNGLE_TEMPLE, IFeatureConfig.NO_FEATURE_CONFIG);

        addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
        addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.WATER.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(4)));
        DefaultBiomeFeatures.addMonsterRooms(this);

        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DOUBLE_PLANT, new DoublePlantConfig(Blocks.TALL_GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(8)));
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DOUBLE_PLANT, new DoublePlantConfig(Blocks.PEONY.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DOUBLE_PLANT, new DoublePlantConfig(Blocks.ROSE_BUSH.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DOUBLE_PLANT, new DoublePlantConfig(Blocks.LILAC.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));

        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(RosewoodFeatures.MONKEY_BRUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(3)));
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.WATERLILY, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(40)));
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(RosewoodFeatures.PODZOL, new ProbabilityConfig(0.2F), Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(160, 80.0D, 0.3D, Heightmap.Type.WORLD_SURFACE_WG)));

        DefaultBiomeFeatures.addSedimentDisks(this);
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.JUNGLE_GRASS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(13)));
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.WATER.getDefaultState()), Placement.COUNT_BIASED_RANGE, new CountRangeConfig(50, 8, 8, 256)));
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.WATER.getDefaultState()), Placement.COUNT_BIASED_RANGE, new CountRangeConfig(90, 8, 8, 256)));

        DefaultBiomeFeatures.addFreezeTopLayer(this);
        DefaultBiomeFeatures.addTaigaRocks(this);
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(RosewoodFeatures.ROSEWOOD_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(17, 0.1F, 1)));

        addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.FOSSIL, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_PASSTHROUGH, new ChanceConfig(64)));
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.JUNGLE_GROUND_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(2, 0.1F, 1)));

        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 8, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.WOLF, 8, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PARROT, 40, 1, 2));

        addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));

        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.OCELOT, 2, 1, 1));


    }
}
