package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.world.gen.feature.DirectionalFlowersFeature;
import com.bagel.atmospheric.common.world.gen.feature.DuneRocksFeature;
import com.bagel.atmospheric.common.world.gen.feature.PassionVineFeature;
import com.bagel.atmospheric.common.world.gen.feature.PodzolFeature;
import com.bagel.atmospheric.common.world.gen.feature.RosewoodTreeFeature;
import com.bagel.atmospheric.common.world.gen.feature.YuccaTreeFeature;
import com.bagel.atmospheric.common.world.gen.surfacebuilders.DunesSurfaceBuilder;
import com.bagel.atmospheric.common.world.gen.surfacebuilders.WaveyDunesSurfaceBuilder;
import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.FancyTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.MegaPineTree;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.ShrubFeature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericFeatures {
	
	public static final Feature<ProbabilityConfig> PODZOL = new PodzolFeature(ProbabilityConfig::deserialize);
	public static final Feature<BaseTreeFeatureConfig> OAK_BUSH = new ShrubFeature(BaseTreeFeatureConfig::deserialize);
	
	public static final Feature<NoFeatureConfig> WARM_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize, 1);
	public static final Feature<NoFeatureConfig> HOT_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize, 2);
	public static final Feature<NoFeatureConfig> SCALDING_MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize, 3);
	
	public static final Feature<TreeFeatureConfig> ROSEWOOD_TREE = new RosewoodTreeFeature(TreeFeatureConfig::func_227338_a_);
	
	public static final Feature<TreeFeatureConfig> YUCCA_TREE = new YuccaTreeFeature(TreeFeatureConfig::func_227338_a_, false, false);
	public static final Feature<TreeFeatureConfig> PETRIFIED_YUCCA_TREE = new YuccaTreeFeature(TreeFeatureConfig::func_227338_a_, false, true);
	public static final Feature<TreeFeatureConfig> YUCCA_TREE_PATCH = new YuccaTreeFeature(TreeFeatureConfig::func_227338_a_, true, false);
	
	public static final Feature<TreeFeatureConfig> KOUSA_TREE = new FancyTreeFeature(TreeFeatureConfig::func_227338_a_);
	public static final Feature<TreeFeatureConfig> ASPEN_TREE = new TreeFeature(TreeFeatureConfig::func_227338_a_);
	public static final Feature<HugeTreeFeatureConfig> MEGA_ASPEN_TREE = new MegaPineTree(HugeTreeFeatureConfig::deserializeSpruce);

	public static final Feature<NoFeatureConfig> PASSION_VINE = new PassionVineFeature(NoFeatureConfig::deserialize);
	
	public static final Feature<BlockBlobConfig> DUNE_ROCKS = new DuneRocksFeature(BlockBlobConfig::deserialize);
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> WAVEY_DUNES = new WaveyDunesSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().registerAll(
        		PODZOL.setRegistryName(Atmospheric.MODID, "podzol"), 
        		OAK_BUSH.setRegistryName(Atmospheric.MODID, "oak_bush"),
        		
        		WARM_MONKEY_BRUSH.setRegistryName(Atmospheric.MODID, "warm_monkey_brush"), 
        		HOT_MONKEY_BRUSH.setRegistryName(Atmospheric.MODID, "hot_monkey_brush"), 
        		SCALDING_MONKEY_BRUSH.setRegistryName(Atmospheric.MODID, "scalding_monkey_brush"), 
        		
        		ROSEWOOD_TREE.setRegistryName("rosewood_tree"), 
        		
        		PASSION_VINE.setRegistryName(Atmospheric.MODID, "passion_vine"),
        		DUNE_ROCKS.setRegistryName(Atmospheric.MODID, "dune_rocks"), 
        		
        		YUCCA_TREE.setRegistryName(Atmospheric.MODID, "yucca_tree"),
        		PETRIFIED_YUCCA_TREE.setRegistryName(Atmospheric.MODID, "petrified_yucca_tree"),
        		YUCCA_TREE_PATCH.setRegistryName(Atmospheric.MODID, "yucca_tree_patch")
        		);
    }
    
    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        event.getRegistry().registerAll(
        		DUNES.setRegistryName(Atmospheric.MODID, "dunes_builder"),
        		WAVEY_DUNES.setRegistryName(Atmospheric.MODID, "wavey_dunes")
        		);
    }

}
