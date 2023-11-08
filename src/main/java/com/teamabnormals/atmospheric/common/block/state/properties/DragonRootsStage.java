package com.teamabnormals.atmospheric.common.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum DragonRootsStage implements StringRepresentable {
	NONE("none"),
	ROOTS("roots"),
	FRUIT("fruit"),
	FLOWERING("flowering"),
	ENDER("ender"),
	FLOWERING_ENDER("flowering_ender");

	private final String name;

	DragonRootsStage(String name) {
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


