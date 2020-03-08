package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.world.gen.feature.*;
import com.bagel.atmospheric.common.world.gen.surfacebuilders.DunesSurfaceBuilder;
import com.bagel.atmospheric.core.Atmospheric;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("rawtypes")
public class AtmosphericFeatures {

	public static final Feature<ProbabilityConfig> PODZOL = new PodzolFeature(ProbabilityConfig::deserialize);
	public static final AbstractTreeFeature<NoFeatureConfig> OAK_BUSH = new ShrubFeature(NoFeatureConfig::deserialize, Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState());
	public static final Feature<NoFeatureConfig> MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize);
	public static final AbstractTreeFeature<NoFeatureConfig> ROSEWOOD_TREE = new RosewoodTreeFeature(NoFeatureConfig::deserialize, false);
	public static final Feature<NoFeatureConfig> PASSION_VINE = new PassionVineFeature(NoFeatureConfig::deserialize);
	public static final Feature<BlockBlobConfig> DUNE_ROCKS = new DuneRocksFeature(BlockBlobConfig::deserialize);
	public static final AbstractTreeFeature<NoFeatureConfig> YUCCA_TREE = new YuccaTreeFeature(NoFeatureConfig::deserialize, false);
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	
    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().registerAll(
				PODZOL.setRegistryName("podzol"),
				OAK_BUSH.setRegistryName("oak_bush"),
				MONKEY_BRUSH.setRegistryName("monkey_brush"),
				ROSEWOOD_TREE.setRegistryName("rosewood_tree"),
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
