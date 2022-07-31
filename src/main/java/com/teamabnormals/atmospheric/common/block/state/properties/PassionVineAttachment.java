package com.teamabnormals.atmospheric.common.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum PassionVineAttachment implements StringRepresentable {
	NONE("none"),
	TOP("top"),
	BOTTOM("bottom"),
	MIDDLE("middle");

	private final String name;

	PassionVineAttachment(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}
}


