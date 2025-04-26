package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import net.minecraft.world.phys.Vec2;

import java.util.OptionalInt;

public class ChiseledKousaBookShelfBlock extends BlueprintChiseledBookShelfBlock {

	public ChiseledKousaBookShelfBlock(Properties properties) {
		super(properties);
	}

	@Override
	public OptionalInt getHitSlot(Vec2 vec2) {
		float x = vec2.x;
		if (vec2.y >= 0.5F) {
			return OptionalInt.of(x < 0.25F ? 0 : x < 0.5F ? 1 : 2);
		} else {
			return OptionalInt.of(x < 0.25F ? 3 : x < 0.75F ? 4 : 5);
		}
	}
}
