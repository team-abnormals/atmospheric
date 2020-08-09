package com.minecraftabnormals.atmospheric.core.registry;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.AloeVeraFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.BarrelCactusFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.CoarseDirtPatchFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.DirectionalFlowersFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.DuneRocksFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.PassionVineFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.PodzolFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.RosewoodTreeFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.RosewoodWaterTreeFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.SurfaceFossilFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.YuccaFlowerFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.YuccaTreeFeature;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.common.world.gen.structure.AridShrinePieces;
import com.minecraftabnormals.atmospheric.common.world.gen.structure.AridShrineStructure;
import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.DunesSurfaceBuilder;
import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.WaveyDunesSurfaceBuilder;
import com.minecraftabnormals.atmospheric.core.Atmospheric;

import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
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
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericFeatures {
	
	public static final Feature<ProbabilityConfig> PODZOL = new PodzolFeature(ProbabilityConfig.field_236576_b_);
	public static final Feature<NoFeatureConfig> SURFACE_FOSSIL = new SurfaceFossilFeature(NoFeatureConfig.field_236558_a_);
	public static final Feature<SphereReplaceConfig> COARSE_DIRT_PATCH = new CoarseDirtPatchFeature(SphereReplaceConfig.field_236516_a_);

	public static final Feature<NoFeatureConfig> WARM_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig.field_236558_a_, 1);
	public static final Feature<NoFeatureConfig> HOT_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig.field_236558_a_, 2);
	public static final Feature<NoFeatureConfig> SCALDING_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig.field_236558_a_, 3);
	
	public static final Feature<BaseTreeFeatureConfig> ROSEWOOD_TREE = new RosewoodTreeFeature(BaseTreeFeatureConfig.CODEC_BASE_TREE_FEATURE_CONFIG);
	public static final Feature<BaseTreeFeatureConfig> ROSEWOOD_WATER_TREE = new RosewoodWaterTreeFeature(BaseTreeFeatureConfig.CODEC_BASE_TREE_FEATURE_CONFIG);
	public static final Feature<YuccaTreeFeatureConfig> YUCCA_TREE = new YuccaTreeFeature(YuccaTreeFeatureConfig.CODEC_YUCCA_TREE_FEATURE_CONFIG);

//	public static final Feature<BaseTreeFeatureConfig> KOUSA_TREE = new TreeFeature(BaseTreeFeatureConfig.CODEC_BASE_TREE_FEATURE_CONFIG);
//	public static final Feature<BaseTreeFeatureConfig> ASPEN_TREE = new TreeFeature(BaseTreeFeatureConfig.CODEC_BASE_TREE_FEATURE_CONFIG);
//	public static final Feature<HugeBaseTreeFeatureConfig> MEGA_ASPEN_TREE = new MegaPineTree(HugeBaseTreeFeatureConfig.); 
	
	public static final Feature<NoFeatureConfig> PASSION_VINE = new PassionVineFeature(NoFeatureConfig.field_236558_a_);
	public static final Feature<BlockClusterFeatureConfig> BARREL_CACTUS = new BarrelCactusFeature(BlockClusterFeatureConfig.field_236587_a_);
	public static final Feature<BlockClusterFeatureConfig> ALOE_VERA = new AloeVeraFeature(BlockClusterFeatureConfig.field_236587_a_);
	public static final Feature<BlockClusterFeatureConfig> YUCCA_FLOWER = new YuccaFlowerFeature(BlockClusterFeatureConfig.field_236587_a_);

	public static final Feature<BlockBlobConfig> DUNE_ROCKS = new DuneRocksFeature(BlockBlobConfig.field_236449_a_);
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);
	public static final SurfaceBuilder<SurfaceBuilderConfig> WAVEY_DUNES = new WaveyDunesSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);
	
	public static final Structure<NoFeatureConfig> ARID_SHRINE = new AridShrineStructure(NoFeatureConfig.field_236558_a_);
	public static final IStructurePieceType ARID_SHRINE_PIECES = AridShrinePieces.Piece::new;

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().registerAll(
        	PODZOL.setRegistryName(Atmospheric.MODID, "podzol"),
        	DUNE_ROCKS.setRegistryName(Atmospheric.MODID, "dune_rocks"),
        	SURFACE_FOSSIL.setRegistryName(Atmospheric.MODID, "surface_fossil"),
        	COARSE_DIRT_PATCH.setRegistryName(Atmospheric.MODID, "coarse_dirt_patch"),

        	WARM_MONKEY_BRUSH.setRegistryName(Atmospheric.MODID, "warm_monkey_brush"),
        	HOT_MONKEY_BRUSH.setRegistryName(Atmospheric.MODID, "hot_monkey_brush"),
        	SCALDING_MONKEY_BRUSH.setRegistryName(Atmospheric.MODID, "scalding_monkey_brush"),
        	PASSION_VINE.setRegistryName(Atmospheric.MODID, "passion_vine"),
        	BARREL_CACTUS.setRegistryName(Atmospheric.MODID, "barrel_cactus"),
        	ALOE_VERA.setRegistryName(Atmospheric.MODID, "aloe_vera"),
        	YUCCA_FLOWER.setRegistryName(Atmospheric.MODID, "yucca_flower"),

        	ROSEWOOD_TREE.setRegistryName(Atmospheric.MODID, "rosewood_tree"),
        	ROSEWOOD_WATER_TREE.setRegistryName(Atmospheric.MODID, "rosewood_water_tree"),
        	YUCCA_TREE.setRegistryName(Atmospheric.MODID, "yucca_tree")
        ); 
    }
    
    @SubscribeEvent
    public static void registerStructures(RegistryEvent.Register<Structure<?>> event) {
        event.getRegistry().registerAll(
            ARID_SHRINE.setRegistryName(Atmospheric.MODID, "arid_shrine")
        ); 
        register(ARID_SHRINE_PIECES, "ARID_SHRINE_PIECES");
    }
    
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
    
    static IStructurePieceType register(IStructurePieceType structurePiece, String key) {
		return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), structurePiece);
	}
}
