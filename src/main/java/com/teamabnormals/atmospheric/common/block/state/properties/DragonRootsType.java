package com.teamabnormals.atmospheric.common.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum DragonRootsType implements StringRepresentable {
	TOP("top"),
	BOTTOM("bottom"),
	DOUBLE("double");

	private final String name;

	DragonRootsType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}
}


