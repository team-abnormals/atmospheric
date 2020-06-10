package com.bagel.atmospheric.core.registry;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.bagel.atmospheric.common.world.biome.AtmosphericBiomeFeatures;
import com.bagel.atmospheric.common.world.gen.feature.AloeVeraFeature;
import com.bagel.atmospheric.common.world.gen.feature.BabyYuccaTreeFeature;
import com.bagel.atmospheric.common.world.gen.feature.BarrelCactusFeature;
import com.bagel.atmospheric.common.world.gen.feature.CoarseDirtPatchFeature;
import com.bagel.atmospheric.common.world.gen.feature.DirectionalFlowersFeature;
import com.bagel.atmospheric.common.world.gen.feature.DuneRocksFeature;
import com.bagel.atmospheric.common.world.gen.feature.PassionVineFeature;
import com.bagel.atmospheric.common.world.gen.feature.PodzolFeature;
import com.bagel.atmospheric.common.world.gen.feature.RosewoodTreeFeature;
import com.bagel.atmospheric.common.world.gen.feature.SurfaceFossilFeature;
import com.bagel.atmospheric.common.world.gen.feature.YuccaFlowerFeature;
import com.bagel.atmospheric.common.world.gen.feature.YuccaTreeFeature;
import com.bagel.atmospheric.common.world.gen.structure.AridShrinePieces;
import com.bagel.atmospheric.common.world.gen.structure.AridShrineStructure;
import com.bagel.atmospheric.common.world.gen.surfacebuilders.DunesSurfaceBuilder;
import com.bagel.atmospheric.common.world.gen.surfacebuilders.WaveyDunesSurfaceBuilder;
import com.bagel.atmospheric.core.Atmospheric;

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
import net.minecraft.world.gen.feature.FancyTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MegaPineTree;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.ShrubFeature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
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
	
	public static final Feature<ProbabilityConfig> PODZOL = new PodzolFeature(ProbabilityConfig::deserialize);
	public static final Feature<NoFeatureConfig> SURFACE_FOSSIL = new SurfaceFossilFeature(NoFeatureConfig::deserialize);
	public static final Feature<BaseTreeFeatureConfig> OAK_BUSH = new ShrubFeature(BaseTreeFeatureConfig::deserialize);
	public static final Feature<SphereReplaceConfig> COARSE_DIRT_PATCH = new CoarseDirtPatchFeature(SphereReplaceConfig::deserialize);

	public static final Feature<NoFeatureConfig> WARM_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize, 1);
	public static final Feature<NoFeatureConfig> HOT_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize, 2);
	public static final Feature<NoFeatureConfig> SCALDING_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize, 3);
	
	public static final Feature<TreeFeatureConfig> ROSEWOOD_TREE = new RosewoodTreeFeature(TreeFeatureConfig::func_227338_a_);
	
	public static final Feature<TreeFeatureConfig> YUCCA_TREE = new YuccaTreeFeature(TreeFeatureConfig::func_227338_a_, false, false);
	public static final Feature<TreeFeatureConfig> PETRIFIED_YUCCA_TREE = new YuccaTreeFeature(TreeFeatureConfig::func_227338_a_, false, true);
	public static final Feature<TreeFeatureConfig> YUCCA_TREE_PATCH = new YuccaTreeFeature(TreeFeatureConfig::func_227338_a_, true, false);
	public static final Feature<TreeFeatureConfig> BABY_YUCCA_TREE = new BabyYuccaTreeFeature(TreeFeatureConfig::func_227338_a_, false);
	public static final Feature<TreeFeatureConfig> BABY_YUCCA_TREE_PATCH = new BabyYuccaTreeFeature(TreeFeatureConfig::func_227338_a_, true);

	public static final Feature<TreeFeatureConfig> KOUSA_TREE = new FancyTreeFeature(TreeFeatureConfig::func_227338_a_);
	public static final Feature<TreeFeatureConfig> ASPEN_TREE = new TreeFeature(TreeFeatureConfig::func_227338_a_);
	public static final Feature<HugeTreeFeatureConfig> MEGA_ASPEN_TREE = new MegaPineTree(HugeTreeFeatureConfig::deserializeSpruce);

	public static final Feature<NoFeatureConfig> PASSION_VINE = new PassionVineFeature(NoFeatureConfig::deserialize);
	public static final Feature<BlockClusterFeatureConfig> BARREL_CACTUS = new BarrelCactusFeature(BlockClusterFeatureConfig::deserialize);
	public static final Feature<BlockClusterFeatureConfig> ALOE_VERA = new AloeVeraFeature(BlockClusterFeatureConfig::deserialize);
	public static final Feature<BlockClusterFeatureConfig> YUCCA_FLOWER = new YuccaFlowerFeature(BlockClusterFeatureConfig::deserialize);

	public static final Feature<BlockBlobConfig> DUNE_ROCKS = new DuneRocksFeature(BlockBlobConfig::deserialize);
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> WAVEY_DUNES = new WaveyDunesSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	
	public static final Structure<NoFeatureConfig> ARID_SHRINE = new AridShrineStructure(NoFeatureConfig::deserialize);
	public static final IStructurePieceType ARID_SHRINE_PIECES = AridShrinePieces.Piece::new;

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
    	
        event.getRegistry().registerAll(
        	PODZOL.setRegistryName(Atmospheric.MODID, "podzol"), 
        	OAK_BUSH.setRegistryName(Atmospheric.MODID, "oak_bush"),
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

        	YUCCA_TREE.setRegistryName(Atmospheric.MODID, "yucca_tree"),
        	PETRIFIED_YUCCA_TREE.setRegistryName(Atmospheric.MODID, "petrified_yucca_tree"),
        	YUCCA_TREE_PATCH.setRegistryName(Atmospheric.MODID, "yucca_tree_patch"),		
        	BABY_YUCCA_TREE.setRegistryName(Atmospheric.MODID, "baby_yucca_tree"),
        	BABY_YUCCA_TREE_PATCH.setRegistryName(Atmospheric.MODID, "baby_yucca_tree_patch"),
        	ARID_SHRINE.setRegistryName(Atmospheric.MODID, "run_down_house")
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
            AtmosphericBiomeFeatures.addYuccaTrees(biome, 0, 0.005F, 1, false);
        }
        
        if (biome == Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU || biome == Biomes.WOODED_BADLANDS_PLATEAU) {
            AtmosphericBiomeFeatures.addYuccaTrees(biome, 0, 0.25F, 1, false);
        }
        
        if (biome == Biomes.SHATTERED_SAVANNA || biome == Biomes.SHATTERED_SAVANNA_PLATEAU) {
            AtmosphericBiomeFeatures.addYuccaTrees(biome, 0, 0.15F, 1, false);
        }
        if (biome == Biomes.MODIFIED_JUNGLE_EDGE) {
        	biome.addStructure(AtmosphericFeatures.ARID_SHRINE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }
    
    static IStructurePieceType register(IStructurePieceType structurePiece, String key) {
		return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), structurePiece);
	}
}
