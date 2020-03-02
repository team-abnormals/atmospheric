package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.world.gen.feature.DirectionalFlowersFeature;
import com.bagel.atmospheric.common.world.gen.feature.DuneRocksFeature;
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
@SuppressWarnings("rawtypes")
public class AtmosphericFeatures {
	
	public static final Feature PODZOL = new PodzolFeature(ProbabilityConfig::deserialize).setRegistryName("podzol");
	public static final Feature OAK_BUSH = new ShrubFeature(BaseTreeFeatureConfig::deserialize).setRegistryName("oak_bush");
	
	public static final Feature MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize).setRegistryName("monkey_brush");
	public static final Feature ROSEWOOD_TREE = new RosewoodTreeFeature(TreeFeatureConfig::func_227338_a_, false).setRegistryName("rosewood_tree");
	public static final Feature PASSION_VINE = new PassionVineFeature(NoFeatureConfig::deserialize).setRegistryName("passion_vine");
	
	public static final Feature DUNE_ROCKS = new DuneRocksFeature(BlockBlobConfig::deserialize).setRegistryName("dune_rocks");
	public static final Feature YUCCA_TREE = new FancyTreeFeature(TreeFeatureConfig::func_227338_a_).setRegistryName("yucca_tree");
	
	public static final SurfaceBuilder DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig::deserialize).setRegistryName("dunes_builder");
	
    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().registerAll(
        		PODZOL, OAK_BUSH,
        		MONKEY_BRUSH, ROSEWOOD_TREE, PASSION_VINE,
        		DUNE_ROCKS, YUCCA_TREE);
    }
    
    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        event.getRegistry().register(DUNES);
    }

}
