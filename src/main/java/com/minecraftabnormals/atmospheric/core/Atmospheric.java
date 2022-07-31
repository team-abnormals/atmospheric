package com.minecraftabnormals.atmospheric.core;

import com.minecraftabnormals.atmospheric.client.model.PassionfruitSeedModel;
import com.minecraftabnormals.atmospheric.client.render.PassionfruitSeedRenderer;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericCompat;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericModelLayers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericRender;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericVillagers;
import com.minecraftabnormals.atmospheric.core.registry.*;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures;
import com.minecraftabnormals.atmospheric.core.registry.helper.AtmosphericBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Atmospheric.MOD_ID)
@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Atmospheric {
	public static final String MOD_ID = "atmospheric";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> {
		helper.putSubHelper(ForgeRegistries.BLOCKS, new AtmosphericBlockSubRegistryHelper(helper));
	});

	public Atmospheric() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();

		REGISTRY_HELPER.register(bus);
		AtmosphericFeatures.FEATURES.register(bus);
		AtmosphericConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
		AtmosphericPlacedFeatures.PLACED_FEATURES.register(bus);
		AtmosphericParticleTypes.PARTICLES.register(bus);
		AtmosphericMobEffects.EFFECTS.register(bus);
		AtmosphericMobEffects.POTIONS.register(bus);
		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		});

		context.registerConfig(ModConfig.Type.COMMON, AtmosphericConfig.COMMON_SPEC);
		context.registerConfig(ModConfig.Type.CLIENT, AtmosphericConfig.CLIENT_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AtmosphericBiomes.addBiomeTypes();
			AtmosphericBiomes.registerBiomesToDictionary();
			AtmosphericVillagers.setupVillagerTypes();
			AtmosphericCompat.registerCompat();
			AtmosphericMobEffects.registerBrewingRecipes();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		AtmosphericRender.registerEntityRenderers();
		event.enqueueWork(() -> {
			AtmosphericRender.registerBlockColors();
			AtmosphericRender.registerRenderLayers();
		});
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(AtmosphericModelLayers.PASSIONFRUIT_SEED, PassionfruitSeedModel::createBodyLayer);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(AtmosphericEntityTypes.PASSIONFRUIT_SEED.get(), PassionfruitSeedRenderer::new);
	}
}
