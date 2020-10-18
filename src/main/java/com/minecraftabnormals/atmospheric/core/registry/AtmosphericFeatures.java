package com.minecraftabnormals.atmospheric.core.registry;

import java.util.HashSet;
import java.util.Set;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.AloeVeraFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.BarrelCactusFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.CoarseDirtPatchFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.DuneRocksFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.MonkeyBrushFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.PassionVineFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.PodzolFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.RainforestTreeFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.SurfaceFossilFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.YuccaFlowerFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.YuccaTreeFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.DunesSurfaceBuilder;
import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.WaveyDunesSurfaceBuilder;
import com.minecraftabnormals.atmospheric.core.Atmospheric;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Atmospheric.MODID);

	public static final RegistryObject<Feature<ProbabilityConfig>> PODZOL = FEATURES.register("podzol", () -> new PodzolFeature(ProbabilityConfig.field_236576_b_));
	public static final RegistryObject<Feature<NoFeatureConfig>> SURFACE_FOSSIL = FEATURES.register("surface_fossil", () -> new SurfaceFossilFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<SphereReplaceConfig>> COARSE_DIRT_PATCH = FEATURES.register("coarse_dirt_patch", () -> new CoarseDirtPatchFeature(SphereReplaceConfig.field_236516_a_));

	public static final RegistryObject<Feature<NoFeatureConfig>> WARM_MONKEY_BRUSH = FEATURES.register("warm_monkey_brush", () -> new MonkeyBrushFeature(NoFeatureConfig.field_236558_a_, 1));
	public static final RegistryObject<Feature<NoFeatureConfig>> HOT_MONKEY_BRUSH = FEATURES.register("hot_monkey_brush", () -> new MonkeyBrushFeature(NoFeatureConfig.field_236558_a_, 2));
	public static final RegistryObject<Feature<NoFeatureConfig>> SCALDING_MONKEY_BRUSH = FEATURES.register("scalding_monkey_brush", () -> new MonkeyBrushFeature(NoFeatureConfig.field_236558_a_, 3));
	
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> ROSEWOOD_TREE = FEATURES.register("rosewood_tree", () -> new RainforestTreeFeature(BaseTreeFeatureConfig.CODEC_BASE_TREE_FEATURE_CONFIG, true));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> ROSEWOOD_WATER_TREE = FEATURES.register("rosewood_water_tree", () -> new RainforestTreeFeature(BaseTreeFeatureConfig.CODEC_BASE_TREE_FEATURE_CONFIG, false));
	public static final RegistryObject<Feature<YuccaTreeFeatureConfig>> YUCCA_TREE = FEATURES.register("yucca_tree", () -> new YuccaTreeFeature(YuccaTreeFeatureConfig.CODEC_YUCCA_TREE_FEATURE_CONFIG));

	public static final RegistryObject<Feature<NoFeatureConfig>> PASSION_VINE = FEATURES.register("passion_vine", () -> new PassionVineFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> BARREL_CACTUS = FEATURES.register("barrel_cactus", () -> new BarrelCactusFeature(BlockClusterFeatureConfig.field_236587_a_));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> ALOE_VERA = FEATURES.register("aloe_vera", () -> new AloeVeraFeature(BlockClusterFeatureConfig.field_236587_a_));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> YUCCA_FLOWER = FEATURES.register("yucca_flower", () -> new YuccaFlowerFeature(BlockClusterFeatureConfig.field_236587_a_));

	public static final RegistryObject<Feature<BlockBlobConfig>> DUNE_ROCKS = FEATURES.register("dune_rocks", () -> new DuneRocksFeature(BlockBlobConfig.field_236449_a_));
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);
	public static final SurfaceBuilder<SurfaceBuilderConfig> WAVEY_DUNES = new WaveyDunesSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);


	public static void addCarvables() {
		Set<Block> allBlocksToCarve = new HashSet<Block>();
		
		allBlocksToCarve.add(AtmosphericBlocks.ARID_SAND.get());
		allBlocksToCarve.add(AtmosphericBlocks.ARID_SANDSTONE.get());
		allBlocksToCarve.add(AtmosphericBlocks.RED_ARID_SAND.get());
		allBlocksToCarve.add(AtmosphericBlocks.RED_ARID_SANDSTONE.get());
		
		for (Carving carverStage : GenerationStage.Carving.values())
		{
		    for (ConfiguredCarver<?> carver : AtmosphericBiomes.DUNES.get().getCarvers(carverStage))
		    {
		        allBlocksToCarve.addAll(carver.carver.carvableBlocks);
		        carver.carver.carvableBlocks = allBlocksToCarve;
		    }
		}
	}
    
    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        event.getRegistry().registerAll(
        	DUNES.setRegistryName(Atmospheric.MODID, "dunes_builder"),
        	WAVEY_DUNES.setRegistryName(Atmospheric.MODID, "wavey_dunes")
        );
    }
    
    public static void generateFeatures() {
        ForgeRegistries.BIOMES.getValues().forEach(AtmosphericFeatures::generate);
    }

    public static void generate(Biome biome) {
        if (biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS) {
            AtmosphericBiomeFeatures.addYuccaTrees(biome, 0, 0.005F, 1);
        }
        
        if (biome == Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU || biome == Biomes.WOODED_BADLANDS_PLATEAU) {
            AtmosphericBiomeFeatures.addYuccaTrees(biome, 0, 0.25F, 1);
        }
        
        if (biome == Biomes.SHATTERED_SAVANNA || biome == Biomes.SHATTERED_SAVANNA_PLATEAU) {
            AtmosphericBiomeFeatures.addYuccaTrees(biome, 0, 0.15F, 1);
        }
        
//      if (biome == Biomes.MODIFIED_JUNGLE_EDGE) {
//          biome.func_235063_a_(AtmosphericFeatureConfigs.ARID_SHRINE);
//      }
    }
}
