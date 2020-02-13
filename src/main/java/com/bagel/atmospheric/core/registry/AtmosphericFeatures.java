package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.world.gen.feature.DirectionalFlowersFeature;
import com.bagel.atmospheric.common.world.gen.feature.PassionVineFeature;
import com.bagel.atmospheric.common.world.gen.feature.PodzolFeature;
import com.bagel.atmospheric.common.world.gen.feature.RosewoodTreeFeature;
import com.bagel.atmospheric.core.AtmosphericExpansion;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.ShrubFeature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AtmosphericExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("rawtypes")
public class AtmosphericFeatures {
	
	public static final Feature MONKEY_BRUSH = new DirectionalFlowersFeature(NoFeatureConfig::deserialize).setRegistryName("monkey_brush");
	public static final Feature PODZOL = new PodzolFeature(ProbabilityConfig::deserialize).setRegistryName("podzol");
	public static final Feature ROSEWOOD_TREE = new RosewoodTreeFeature(NoFeatureConfig::deserialize, false).setRegistryName("rosewood_tree");
	public static final Feature PASSION_VINE = new PassionVineFeature(NoFeatureConfig::deserialize).setRegistryName("passion_vine");
	public static final Feature OAK_BUSH = new ShrubFeature(NoFeatureConfig::deserialize, Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()).setRegistryName("oak_bush");
	
    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().registerAll(MONKEY_BRUSH, PODZOL, ROSEWOOD_TREE, PASSION_VINE, OAK_BUSH);
    }

}
