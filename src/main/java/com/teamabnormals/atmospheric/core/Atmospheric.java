package com.teamabnormals.atmospheric.core;

import com.teamabnormals.atmospheric.client.renderer.entity.PassionfruitSeedRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.model.PassionfruitSeedModel;
import com.teamabnormals.atmospheric.core.data.server.AtmosphericAdvancementProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericAdvancementModifierProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericChunkGeneratorModifierProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericLootModifierProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericModdedBiomeSliceProvider;
import com.teamabnormals.atmospheric.core.data.server.tags.AtmosphericBlockTagsProvider;
import com.teamabnormals.atmospheric.core.data.server.tags.AtmosphericEntityTypeTagsProvider;
import com.teamabnormals.atmospheric.core.data.server.tags.AtmosphericItemTagsProvider;
import com.teamabnormals.atmospheric.core.other.AtmosphericClientCompat;
import com.teamabnormals.atmospheric.core.other.AtmosphericCompat;
import com.teamabnormals.atmospheric.core.other.AtmosphericModelLayers;
import com.teamabnormals.atmospheric.core.other.AtmosphericVillagers;
import com.teamabnormals.atmospheric.core.registry.*;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures;
import com.teamabnormals.atmospheric.core.registry.helper.AtmosphericBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
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
		AtmosphericNoiseParameters.NOISE_PARAMETERS.register(bus);
		AtmosphericPlacementModifierTypes.PLACEMENT_MODIFIER_TYPES.register(bus);
		AtmosphericParticleTypes.PARTICLES.register(bus);
		AtmosphericMobEffects.EFFECTS.register(bus);
		AtmosphericMobEffects.POTIONS.register(bus);
		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

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
			AtmosphericVillagers.setupVillagerTypes();
			AtmosphericCompat.registerCompat();
			AtmosphericMobEffects.registerBrewingRecipes();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			AtmosphericClientCompat.registerBlockColors();
			AtmosphericClientCompat.registerRenderLayers();
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		if (includeServer) {
			AtmosphericBlockTagsProvider blockTags = new AtmosphericBlockTagsProvider(generator, existingFileHelper);
			generator.addProvider(blockTags);
			generator.addProvider(new AtmosphericItemTagsProvider(generator, blockTags, existingFileHelper));
			generator.addProvider(new AtmosphericEntityTypeTagsProvider(generator, existingFileHelper));
			generator.addProvider(new AtmosphericAdvancementProvider(generator, existingFileHelper));
			generator.addProvider(new AtmosphericAdvancementModifierProvider(generator));
			generator.addProvider(new AtmosphericLootModifierProvider(generator));
			generator.addProvider(new AtmosphericChunkGeneratorModifierProvider(generator));
			generator.addProvider(new AtmosphericModdedBiomeSliceProvider(generator));
		}
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
