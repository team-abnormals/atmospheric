package com.teamabnormals.atmospheric.common.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public record LargeDiskConfiguration(BlockState state, IntProvider radius, int halfHeight, List<BlockState> targets) implements FeatureConfiguration {
	public static final Codec<LargeDiskConfiguration> CODEC = RecordCodecBuilder.create((p_191250_) -> {
		return p_191250_.group(BlockState.CODEC.fieldOf("state").forGetter(LargeDiskConfiguration::state), IntProvider.codec(0, Integer.MAX_VALUE).fieldOf("radius").forGetter(LargeDiskConfiguration::radius), Codec.intRange(0, 4).fieldOf("half_height").forGetter(LargeDiskConfiguration::halfHeight), BlockState.CODEC.listOf().fieldOf("targets").forGetter(LargeDiskConfiguration::targets)).apply(p_191250_, LargeDiskConfiguration::new);
	});

	public LargeDiskConfiguration(BlockState state, IntProvider radius, int halfHeight, List<BlockState> targets) {
		this.state = state;
		this.radius = radius;
		this.halfHeight = halfHeight;
		this.targets = targets;
	}

	@Override
	public BlockState state() {
		return this.state;
	}

	@Override
	public IntProvider radius() {
		return this.radius;
	}

	@Override
	public int halfHeight() {
		return this.halfHeight;
	}

	@Override
	public List<BlockState> targets() {
		return this.targets;
	}
}