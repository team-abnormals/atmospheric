package com.teamabnormals.atmospheric.core;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.teamabnormals.atmospheric.core.data.client.AtmosphericBlockStateProvider;
import com.teamabnormals.atmospheric.core.data.client.AtmosphericItemModelProvider;
import com.teamabnormals.atmospheric.core.data.client.AtmosphericSpriteSourceProvider;
import com.teamabnormals.atmospheric.core.data.server.*;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericAdvancementModifierProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericChunkGeneratorModifierProvider;
import com.teamabnormals.atmospheric.core.data.server.modifiers.AtmosphericDataRemolderProvider;
import com.teamabnormals.atmospheric.core.data.server.tags.*;
import com.teamabnormals.atmospheric.core.other.AtmosphericClientCompat;
import com.teamabnormals.atmospheric.core.other.AtmosphericCompat;
import com.teamabnormals.atmospheric.core.registry.AtmosphericVillagerTypes;
import com.teamabnormals.atmospheric.core.registry.*;
import com.teamabnormals.atmospheric.core.registry.helper.AtmosphericBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.gallery.core.data.client.GalleryItemModelProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(Atmospheric.MOD_ID)
public class Atmospheric {
	public static final String MOD_ID = "atmospheric";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(Registries.BLOCK, new AtmosphericBlockSubRegistryHelper(helper)));

	public Atmospheric(IEventBus bus, ModContainer container) {
		AtmosphericBlocks.BLOCKS.register(bus);
		AtmosphericItems.ITEMS.register(bus);
		AtmosphericEntityTypes.ENTITY_TYPES.register(bus);
		AtmosphericSoundEvents.SOUND_EVENTS.register(bus);
		AtmosphericFeatures.FEATURES.register(bus);
		AtmosphericFeatures.TREE_DECORATOR_TYPES.register(bus);
		AtmosphericPlacementModifierTypes.PLACEMENT_MODIFIER_TYPES.register(bus);
		AtmosphericParticleTypes.PARTICLES.register(bus);
		AtmosphericMobEffects.EFFECTS.register(bus);
		AtmosphericMobEffects.POTIONS.register(bus);
		AtmosphericConditionSerializers.CONDITION_SERIALIZERS.register(bus);
		AtmosphericStructureProcessors.STRUCTURE_PROCESSORS.register(bus);
		AtmosphericDecoratedPotPatterns.DECORATED_POT_PATTERNS.register(bus);
		AtmosphericVillagerTypes.VILLAGER_TYPES.register(bus);
		AtmosphericDataSerializers.SERIALIZERS.register(bus);
		AtmosphericCriteriaTriggers.TRIGGERS.register(bus);

		bus.addListener(AtmosphericRegistries::registerRegistries);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		container.registerConfig(ModConfig.Type.COMMON, AtmosphericConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		Set<Block> validBlocks = Sets.newHashSet(BlockEntityType.BRUSHABLE_BLOCK.validBlocks);
		validBlocks.addAll(Sets.newHashSet(AtmosphericBlocks.SUSPICIOUS_ARID_SAND.get(), AtmosphericBlocks.SUSPICIOUS_RED_ARID_SAND.get()));
		BlockEntityType.BRUSHABLE_BLOCK.validBlocks = ImmutableSet.copyOf(validBlocks);

		event.enqueueWork(() -> {
			AtmosphericCompat.registerCompat();
			AtmosphericDecoratedPotPatterns.registerDecoratedPotPatterns();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(AtmosphericClientCompat::registerCompat);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();

		AtmosphericDatapackProvider datapack = new AtmosphericDatapackProvider(output, provider);
		generator.addProvider(server, datapack);
		provider = datapack.getRegistryProvider();

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
		generator.addProvider(server, new AtmosphericLootTableProvider(output, provider));
		generator.addProvider(server, new AtmosphericRecipeProvider(output, provider));
		generator.addProvider(server, AtmosphericAdvancementProvider.create(output, provider, helper));
		generator.addProvider(server, new AtmosphericAdvancementModifierProvider(output, provider));
		generator.addProvider(server, new AtmosphericDataRemolderProvider(output, provider));
		generator.addProvider(server, new AtmosphericChunkGeneratorModifierProvider(output, provider));
		generator.addProvider(server, new AtmosphericDataMapProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new AtmosphericBlockStateProvider(output, helper));
		generator.addProvider(client, new AtmosphericItemModelProvider(output, helper));
		generator.addProvider(client, new AtmosphericSpriteSourceProvider(output, provider, helper));

		generator.addProvider(client, new GalleryItemModelProvider(MOD_ID, output, helper, provider));
	}

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
