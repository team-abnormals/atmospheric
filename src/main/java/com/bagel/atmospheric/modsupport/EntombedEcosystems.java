package com.bagel.atmospheric.modsupport;

import com.bagel.atmospheric.common.world.biome.dunes.DunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.FlourishingDunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.PetrifiedDunesBiome;
import com.bagel.atmospheric.common.world.biome.dunes.RockyDunesBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodForestBiome;
import com.bagel.atmospheric.common.world.biome.rosewood.RosewoodPlateauBiome;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EntombedEcosystems {
    public static class EEFeatureAdder{

        public static void EEaddRosewoodForestTrees (Biome biomeIn, int count, int extraCountIn) {
            biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(new Feature[]{Feature.FANCY_TREE}, new IFeatureConfig[] {IFeatureConfig.NO_FEATURE_CONFIG}, new float[] {0.3F}, Feature.NORMAL_TREE, IFeatureConfig.NO_FEATURE_CONFIG), EEPlacerList.EETREEPLACER, new AtSurfaceWithExtraConfig(0, 0.3F, 1)));
            biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(AtmosphericFeatures.ROSEWOOD_TREE, IFeatureConfig.NO_FEATURE_CONFIG, EEPlacerList.EETREEPLACER, new AtSurfaceWithExtraConfig(count, 0.1F, extraCountIn)));
            biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(AtmosphericFeatures.OAK_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, EEPlacerList.EETREEPLACER, new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
        }

        public static void EEaddRosewoodPlateauTrees (Biome biomeIn, int count, int extraCountIn) {
            biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(AtmosphericFeatures.ROSEWOOD_TREE, IFeatureConfig.NO_FEATURE_CONFIG, EEPlacerList.EETREEPLACER, new AtSurfaceWithExtraConfig(count, 0.1F, extraCountIn)));
            biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(AtmosphericFeatures.OAK_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, EEPlacerList.EETREEPLACER, new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
        }

        public static void EEaddYuccaTrees(Biome biomeIn) {
            biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(AtmosphericFeatures.YUCCA_TREE, IFeatureConfig.NO_FEATURE_CONFIG, EEPlacerList.EETREEPLACER, new AtSurfaceWithExtraConfig(0, 0.05F, 3)));
        }

        public static void EEaddSparseYuccaTrees(Biome biomeIn) {
            biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(AtmosphericFeatures.YUCCA_TREE, IFeatureConfig.NO_FEATURE_CONFIG, EEPlacerList.EETREEPLACER, new AtSurfaceWithExtraConfig(0, 0.05F, 1)));
        }
    }
    public static class EETreePlacer extends Placement<AtSurfaceWithExtraConfig> {
        public EETreePlacer(Function<Dynamic<?>, ? extends AtSurfaceWithExtraConfig> configFactoryIn) {
            super(configFactoryIn);
        }

        public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, AtSurfaceWithExtraConfig chancesConfig, BlockPos pos) {
            int c = chancesConfig.count;
            if (random.nextFloat() < chancesConfig.extraChance) {
                c += chancesConfig.extraCount;
            }

            boolean airFlag = false;
            boolean airBlock = true;
            ArrayList<BlockPos> blockPosList = new ArrayList<BlockPos>();

            for (int i = 0; i < c; i++) {
                int x = random.nextInt(16);
                int z = random.nextInt(16);
                int height = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING, pos.add(x, 0, z)).getY() - 5;


                while (height > 15) {

                    airBlock = worldIn.isAirBlock(pos.add(x, height, z));

                    //if height is is an air block and previous block was a solid block, store the fact that we are in an air block now
                    if (!airFlag && airBlock) {
                        airFlag = true;
                    }


                    //if height is an solid block and last block was air block, we are now on the surface of a piece of land. Generate feature now
                    else if (airFlag && !airBlock) {

                        blockPosList.add(pos.add(x, height + 1, z));
                        airFlag = false;
                    }

                    //move down
                    height--;
                }

            }

            return IntStream.range(0, blockPosList.size()).mapToObj((p_215051_3_) -> {
                return blockPosList.remove(0);
            }).filter(Objects::nonNull);
        }
    }
    public static class EEPlacerList {
        public static final Placement<AtSurfaceWithExtraConfig> EETREEPLACER = new EETreePlacer(AtSurfaceWithExtraConfig::deserialize);

    }
    public static class AtmosphericBiomeList {
        public static Biome rosewoodForest = new RosewoodForestBiome();
        public static Biome rosewoodPlateau = new RosewoodPlateauBiome();
        public static Biome rockDunes = new RockyDunesBiome();
        public static Biome petrifiedDunes = new PetrifiedDunesBiome();
        public static Biome flourishingDunes = new FlourishingDunesBiome();
        public static Biome dunesBiomes = new DunesBiome();
    }
}

