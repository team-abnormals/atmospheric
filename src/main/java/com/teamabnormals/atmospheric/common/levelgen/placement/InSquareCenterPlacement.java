package com.teamabnormals.atmospheric.common.levelgen.placement;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericPlacementModifierTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.Random;
import java.util.stream.Stream;

public class InSquareCenterPlacement extends PlacementModifier {
	public static final InSquareCenterPlacement INSTANCE = new InSquareCenterPlacement();
	public static final Codec<InSquareCenterPlacement> CODEC = Codec.unit(() -> INSTANCE);

	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
		return Stream.of(new BlockPos(pos.getX() + 8, pos.getY(), pos.getZ() + 8));
	}

	public PlacementModifierType<?> type() {
		return AtmosphericPlacementModifierTypes.IN_SQUARE_CENTER.get();
	}
}
