package com.minecraftabnormals.atmospheric.common.data;

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


