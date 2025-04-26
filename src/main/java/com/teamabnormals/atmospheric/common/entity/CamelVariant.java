package com.teamabnormals.atmospheric.common.entity;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum CamelVariant implements StringRepresentable {
	DESERT(0, "minecraft", "camel"),
	ARID(1, Atmospheric.MOD_ID, "camel_arid"),
	HYBRID(2, Atmospheric.MOD_ID, "camel_hybrid");

	private static final IntFunction<CamelVariant> BY_ID = ByIdMap.sparse(CamelVariant::id, values(), DESERT);
	public static final Codec<CamelVariant> CODEC = StringRepresentable.fromEnum(CamelVariant::values);
	private final int id;
	private final ResourceLocation location;
	private final LazyLoadedValue<ResourceLocation> texture = new LazyLoadedValue<>(() -> ResourceLocation.fromNamespaceAndPath(this.location().getNamespace(), "textures/entity/camel/" + this.location().getPath() + ".png"));

	CamelVariant(int id, String modid, String name) {
		this(id, ResourceLocation.fromNamespaceAndPath(modid, name));
	}

	CamelVariant(int id, ResourceLocation location) {
		this.id = id;
		this.location = location;
	}

	@Override
	public String getSerializedName() {
		return this.location.toString();
	}

	public int id() {
		return this.id;
	}

	public ResourceLocation location() {
		return this.location;
	}

	public ResourceLocation texture() {
		return this.texture.get();
	}

	public static CamelVariant byId(int id) {
		return BY_ID.apply(id);
	}
}