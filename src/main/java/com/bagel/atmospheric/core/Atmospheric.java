package com.bagel.atmospheric.core;

import com.bagel.atmospheric.core.data.AtmosphericColors;
import com.bagel.atmospheric.core.data.AtmosphericCompostables;
import com.bagel.atmospheric.core.data.AtmosphericDispenserBehaviors;
import com.bagel.atmospheric.core.data.AtmosphericFlammables;
import com.bagel.atmospheric.core.data.AtmosphericRenderLayers;
import com.bagel.atmospheric.core.other.AtmosphericConfig;
import com.bagel.atmospheric.core.registry.AtmosphericBiomes;
import com.bagel.atmospheric.core.registry.AtmosphericEffects;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;
import com.bagel.atmospheric.core.registry.AtmosphericParticles;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("deprecation")
@Mod(Atmospheric.MODID)
@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Atmospheric
{
    public static final String MODID = "atmospheric";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public Atmospheric() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
    	REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
    	
    	AtmosphericBiomes.BIOMES.register(modEventBus);
    	AtmosphericParticles.PARTICLES.register(modEventBus);
        AtmosphericEffects.EFFECTS.register(modEventBus);
        AtmosphericEffects.POTIONS.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
        
        modEventBus.addListener((ModConfig.ModConfigEvent event) -> {
			final ModConfig config = event.getConfig();
			if(config.getSpec() == AtmosphericConfig.COMMON_SPEC) {
				AtmosphericConfig.ValuesHolder.updateCommonValuesFromConfig(config);
			}
		});
        
        modEventBus.addListener(this::setup);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
        	modEventBus.addListener(this::clientSetup);
        });
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AtmosphericConfig.COMMON_SPEC);
    }
    
	private void setup(final FMLCommonSetupEvent event)
	{
		// Note: This was deprecated too early by Forge. There is no replacement yet, so it can be safely disregarded.
		DeferredWorkQueue.runLater(() -> {
			AtmosphericFeatures.generateFeatures();
			AtmosphericFeatures.addCarvables();
			AtmosphericBiomes.registerBiomesToDictionary();
			AtmosphericCompostables.registerCompostables();
			AtmosphericFlammables.registerFlammables();
			AtmosphericEffects.registerBrewingRecipes();
			AtmosphericDispenserBehaviors.registerDispenserBehaviors();
		});
	}
    
    private void clientSetup(final FMLClientSetupEvent event) 
    {
    	AtmosphericColors.registerBlockColors();
    	AtmosphericRenderLayers.setupRenderLayer();
    }
}
