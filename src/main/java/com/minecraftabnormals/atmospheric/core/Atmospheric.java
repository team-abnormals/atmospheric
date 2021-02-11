package com.minecraftabnormals.atmospheric.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericCompat;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericRender;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericVillagers;
import com.minecraftabnormals.atmospheric.core.registry.*;
import com.minecraftabnormals.atmospheric.core.registry.helper.AtmosphericBlockSubRegistryHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Atmospheric.MOD_ID)
@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Atmospheric {
	public static final String MOD_ID = "atmospheric";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> {
		helper.putSubHelper(ForgeRegistries.BLOCKS, new AtmosphericBlockSubRegistryHelper(helper));
	});

	public Atmospheric() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.register(bus);
		AtmosphericFeatures.FEATURES.register(bus);
		AtmosphericStructures.STRUCTURES.register(bus);
		AtmosphericParticles.PARTICLES.register(bus);
		AtmosphericEffects.EFFECTS.register(bus);
		AtmosphericEffects.POTIONS.register(bus);

		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AtmosphericConfig.COMMON_SPEC);
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AtmosphericFeatures.Configured.registerConfiguredFeatures();
			AtmosphericSurfaceBuilders.Configured.registerConfiguredSurfaceBuilders();
			AtmosphericStructures.Configured.registerConfiguredFeatures();
			AtmosphericStructures.registerNoiseSettings();
			AtmosphericBiomes.addBiomeTypes();
			AtmosphericBiomes.registerBiomesToDictionary();
			AtmosphericBiomes.addBiomeVariants();
			AtmosphericVillagers.setupVillagerTypes();
			AtmosphericCompat.registerCompostables();
			AtmosphericCompat.registerFlammables();
			AtmosphericCompat.registerDispenserBehaviors();
			AtmosphericCompat.registerLootInjectors();
			AtmosphericEffects.registerBrewingRecipes();
		});
	}

	private void clientSetup(final FMLClientSetupEvent event) {
		AtmosphericRender.registerEntityRenderers();
		event.enqueueWork(() -> {
			AtmosphericRender.registerBlockColors();
			AtmosphericRender.registerRenderLayers();
		});
	}
}
