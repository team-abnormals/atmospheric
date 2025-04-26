package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import net.minecraft.world.phys.Vec2;

import java.util.OptionalInt;

public class ChiseledMoradoBookShelfBlock extends BlueprintChiseledBookShelfBlock {

	public ChiseledMoradoBookShelfBlock(Properties properties) {
		super(properties);
	}

	@Override
	public OptionalInt getHitSlot(Vec2 vec2) {
		if (vec2.y < 0.6875F && vec2.y >= 0.3125F) {
			return OptionalInt.of(vec2.x < 0.75F ? 2 : 3);
		} else {
			int section = vec2.x < 0.25F ? 0 : 1;
			if (vec2.y < 0.3125F) {
				section += 4;
			}
			return OptionalInt.of(section);
		}
	}
}
