package com.teamabnormals.atmospheric.core;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.teamabnormals.atmospheric.client.model.CochinealModel;
import com.teamabnormals.atmospheric.client.model.DragonFruitModel;
import com.teamabnormals.atmospheric.client.model.PassionFruitSeedModel;
import com.teamabnormals.atmospheric.client.renderer.entity.CochinealRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.DragonFruitRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.PassionFruitSeedRenderer;
import com.teamabnormals.atmospheric.core.data.client.AtmosphericBlockStateProvider;
import com.teamabnormals.atmospheric.core.data.client.AtmosphericItemModelProvider;
import com.teamabnormals.atmospheric.core.data.client.AtmosphericSpriteSourceProvider;
import com.teamabnormals.atmospheric.core.data.server.AtmosphericAdvancementProvider;
import com.teamabnormals.atmospheric.core.data.server.AtmosphericDatapackBuiltinEntriesProvider;
import com.teamabnormals.atmospheric.core.data.server.AtmosphericLootTableProvider;
import com.teamabnormals.atmospheric.core.data.server.AtmosphericRecipeProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericAdvancementModifierProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericChunkGeneratorModifierProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericLootModifierProvider;
import com.teamabnormals.atmospheric.core.data.server.tags.*;
import com.teamabnormals.atmospheric.core.other.AtmosphericClientCompat;
import com.teamabnormals.atmospheric.core.other.AtmosphericCompat;
import com.teamabnormals.atmospheric.core.other.AtmosphericModelLayers;
import com.teamabnormals.atmospheric.core.other.AtmosphericVillagers;
import com.teamabnormals.atmospheric.core.registry.*;
import com.teamabnormals.atmospheric.core.registry.helper.AtmosphericBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.gallery.core.data.client.GalleryAssetsRemolderProvider;
import com.teamabnormals.gallery.core.data.client.GalleryItemModelProvider;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
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

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(Atmospheric.MOD_ID)
@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Atmospheric {
	public static final String MOD_ID = "atmospheric";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(ForgeRegistries.BLOCKS, new AtmosphericBlockSubRegistryHelper(helper)));

	public Atmospheric() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		AtmosphericFeatures.FEATURES.register(bus);
		AtmosphericFeatures.TREE_DECORATOR_TYPES.register(bus);
		AtmosphericPlacementModifierTypes.PLACEMENT_MODIFIER_TYPES.register(bus);
		AtmosphericParticleTypes.PARTICLES.register(bus);
		AtmosphericMobEffects.EFFECTS.register(bus);
		AtmosphericMobEffects.POTIONS.register(bus);
		AtmosphericLootConditions.LOOT_CONDITION_TYPES.register(bus);
		AtmosphericBannerPatterns.BANNER_PATTERNS.register(bus);
		AtmosphericStructureProcessors.STRUCTURE_PROCESSORS.register(bus);
		AtmosphericDecoratedPotPatterns.DECORATED_POT_PATTERNS.register(bus);
		AtmosphericPaintingVariants.PAINTING_VARIANTS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			AtmosphericItems.setupTabEditors();
			AtmosphericBlocks.setupTabEditors();
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		});

		context.registerConfig(ModConfig.Type.COMMON, AtmosphericConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		Set<Block> validBlocks = Sets.newHashSet(BlockEntityType.BRUSHABLE_BLOCK.validBlocks);
		validBlocks.addAll(Sets.newHashSet(AtmosphericBlocks.SUSPICIOUS_ARID_SAND.get(), AtmosphericBlocks.SUSPICIOUS_RED_ARID_SAND.get()));
		BlockEntityType.BRUSHABLE_BLOCK.validBlocks = ImmutableSet.copyOf(validBlocks);

		event.enqueueWork(() -> {
			AtmosphericVillagers.registerVillagerTypes();
			AtmosphericCompat.registerCompat();
			AtmosphericMobEffects.registerBrewingRecipes();
			AtmosphericDecoratedPotPatterns.registerDecoratedPotPatterns();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			AtmosphericClientCompat.registerCompat();
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();

		AtmosphericDatapackBuiltinEntriesProvider datapackEntries = new AtmosphericDatapackBuiltinEntriesProvider(output, provider);
		generator.addProvider(server, datapackEntries);
		provider = datapackEntries.getRegistryProvider();

		AtmosphericBlockTagsProvider blockTags = new AtmosphericBlockTagsProvider(output, provider, helper);
		generator.addProvider(server, blockTags);
		generator.addProvider(server, new AtmosphericItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(server, new AtmosphericEntityTypeTagsProvider(output, provider, helper));
		generator.addProvider(server, new AtmosphericBiomeTagsProvider(output, provider, helper));
		generator.addProvider(server, new AtmosphericBannerPatternTagsProvider(output, provider, helper));
		generator.addProvider(server, new AtmosphericTrimMaterialTagsProvider(output, provider, helper));
		generator.addProvider(server, new AtmosphericStructureTagsProvider(output, provider, helper));
		generator.addProvider(server, new AtmosphericDamageTypeTagsProvider(output, provider, helper));
		generator.addProvider(server, new AtmosphericPaintingVariantTagsProvider(output, provider, helper));
		generator.addProvider(server, new AtmosphericLootTableProvider(output));
		generator.addProvider(server, new AtmosphericRecipeProvider(output));
		generator.addProvider(server, AtmosphericAdvancementProvider.create(output, provider, helper));
		generator.addProvider(server, new AtmosphericAdvancementModifierProvider(output, provider));
		generator.addProvider(server, new AtmosphericLootModifierProvider(output, provider));
		generator.addProvider(server, new AtmosphericChunkGeneratorModifierProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new AtmosphericBlockStateProvider(output, helper));
		generator.addProvider(client, new AtmosphericItemModelProvider(output, helper));
		generator.addProvider(client, new AtmosphericSpriteSourceProvider(output, helper));

		generator.addProvider(client, new GalleryItemModelProvider(MOD_ID, output, helper));
		generator.addProvider(client, new GalleryAssetsRemolderProvider(MOD_ID, output, provider));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(AtmosphericModelLayers.COCHINEAL, () -> CochinealModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(AtmosphericModelLayers.COCHINEAL_SADDLE, () -> CochinealModel.createBodyLayer(new CubeDeformation(0.5F)));
		event.registerLayerDefinition(AtmosphericModelLayers.PASSION_FRUIT_SEED, PassionFruitSeedModel::createBodyLayer);
		event.registerLayerDefinition(AtmosphericModelLayers.DRAGON_FRUIT, DragonFruitModel::createBodyLayer);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(AtmosphericEntityTypes.COCHINEAL.get(), CochinealRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get(), PassionFruitSeedRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.DRAGON_FRUIT.get(), DragonFruitRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.ORANGE_VAPOR_CLOUD.get(), NoopRenderer::new);
	}

	public static ResourceLocation location(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
