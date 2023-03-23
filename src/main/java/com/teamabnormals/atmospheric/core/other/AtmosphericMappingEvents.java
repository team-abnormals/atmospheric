package com.teamabnormals.atmospheric.core.other;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.MissingMappingsEvent.Mapping;

import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericMappingEvents {

	@SubscribeEvent
	public static void onMissingMappings(MissingMappingsEvent event) {
		List<Mapping<Block>> blockMappings = event.getMappings(ForgeRegistries.Keys.BLOCKS, Atmospheric.MOD_ID);

		Map<ResourceLocation, Block> blockRemapping = ImmutableMap.<ResourceLocation, Block>builder()
				.put(Atmospheric.location("passionfruit_crate"), AtmosphericBlocks.PASSION_FRUIT_CRATE.get())
				.put(Atmospheric.location("shimmering_passionfruit_crate"), AtmosphericBlocks.SHIMMERING_PASSION_FRUIT_CRATE.get())
				.build();

		for (Mapping<Block> mapping : blockMappings) {
			Block block = blockRemapping.get(mapping.getKey());
			if (block != null && ForgeRegistries.BLOCKS.getKey(block) != null) {
				mapping.remap(block);
			}
		}

		List<Mapping<Item>> itemMappings = event.getMappings(ForgeRegistries.Keys.ITEMS, Atmospheric.MOD_ID);

		Map<ResourceLocation, Item> itemRemapping = ImmutableMap.<ResourceLocation, Item>builder()
				.put(Atmospheric.location("passionfruit"), AtmosphericItems.PASSION_FRUIT.get())
				.put(Atmospheric.location("shimmering_passionfruit"), AtmosphericItems.SHIMMERING_PASSION_FRUIT.get())
				.put(Atmospheric.location("passionfruit_tart"), AtmosphericItems.PASSION_FRUIT_TART.get())
				.put(Atmospheric.location("passionfruit_sorbet"), AtmosphericItems.PASSION_FRUIT_SORBET.get())
				.build();

		for (Mapping<Item> mapping : itemMappings) {
			Item item = itemRemapping.get(mapping.getKey());
			if (item != null && ForgeRegistries.ITEMS.getKey(item) != null) {
				mapping.remap(item);
			}
		}
	}
}