package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.world.gen.feature.DirectionalFlowersFeature;
import com.bagel.atmospheric.common.world.gen.feature.DuneRocksFeature;
import com.bagel.atmospheric.common.world.gen.feature.MonkeyBrushTreeFeature;
import com.bagel.atmospheric.common.world.gen.feature.PassionVineFeature;
import com.bagel.atmospheric.common.world.gen.feature.PodzolFeature;
import com.bagel.atmospheric.common.world.gen.feature.RosewoodTreeFeature;
import com.bagel.atmospheric.common.world.gen.surfacebuilders.DunesSurfaceBuilder;
import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.FancyTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.ShrubFeature;
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
	
	public static final Feature<TreeFeatureConfig> ROSEWOOD_TREE = new RosewoodTreeFeature(TreeFeatureConfig::func_227338_a_, false);
	
	public static final Feature<TreeFeatureConfig> WARM_MONKEY_BRUSH_TREE = new MonkeyBrushTreeFeature(TreeFeatureConfig::func_227338_a_, false, 1);
	public static final Feature<TreeFeatureConfig> HOT_MONKEY_BRUSH_TREE = new MonkeyBrushTreeFeature(TreeFeatureConfig::func_227338_a_, false, 2);
	public static final Feature<TreeFeatureConfig> SCALDING_MONKEY_BRUSH_TREE = new MonkeyBrushTreeFeature(TreeFeatureConfig::func_227338_a_, false, 3);
	
	public static final Feature<NoFeatureConfig> PASSION_VINE = new PassionVineFeature(NoFeatureConfig::deserialize);
	
	public static final Feature<BlockBlobConfig> DUNE_ROCKS = new DuneRocksFeature(BlockBlobConfig::deserialize);
	public static final Feature<TreeFeatureConfig> YUCCA_TREE = new FancyTreeFeature(TreeFeatureConfig::func_227338_a_);
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	
    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().registerAll(
        		PODZOL.setRegistryName("podzol"), 
        		OAK_BUSH.setRegistryName("oak_bush"),
        		WARM_MONKEY_BRUSH.setRegistryName("warm_monkey_brush"), 
        		HOT_MONKEY_BRUSH.setRegistryName("hot_monkey_brush"), 
        		SCALDING_MONKEY_BRUSH.setRegistryName("scalding_monkey_brush"), 
        		
        		ROSEWOOD_TREE.setRegistryName("rosewood_tree"), 
        		WARM_MONKEY_BRUSH_TREE.setRegistryName("warm_monkey_brush_tree"), 
        		HOT_MONKEY_BRUSH_TREE.setRegistryName("hot_monkey_brush_tree"), 
        		SCALDING_MONKEY_BRUSH_TREE.setRegistryName("scalding_monkey_brush_tree"), 
        		
        		PASSION_VINE.setRegistryName("passion_vine"),
        		DUNE_ROCKS.setRegistryName("dune_rocks"), 
        		YUCCA_TREE.setRegistryName("yucca_tree")
        		);
    }
    
    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        event.getRegistry().register(
        		DUNES.setRegistryName("dunes_builder")
        		);
    }

}
