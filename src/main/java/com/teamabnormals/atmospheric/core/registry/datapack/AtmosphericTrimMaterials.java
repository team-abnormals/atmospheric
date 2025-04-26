package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class AtmosphericTrimMaterials {
	public static final ResourceKey<TrimMaterial> CARMINE = createKey("carmine");

	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, CARMINE, AtmosphericItems.CARMINE_HUSK.get(), Style.EMPTY.withColor(0xDB4F4A), Map.of());
	}

	private static ResourceKey<TrimMaterial> createKey(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, Atmospheric.location(name));
	}

	private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Item item, Style style, Map<Holder<ArmorMaterial>, String> overrides) {
		ResourceLocation location = key.location();
		context.register(key, new TrimMaterial(location.getNamespace() + "_" + location.getPath(), BuiltInRegistries.ITEM.wrapAsHolder(item), -1.0F, overrides, Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style)));
	}
}