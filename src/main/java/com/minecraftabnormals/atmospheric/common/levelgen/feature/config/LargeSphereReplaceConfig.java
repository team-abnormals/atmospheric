package com.minecraftabnormals.atmospheric.common.levelgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public record LargeSphereReplaceConfig(BlockState state, IntProvider radius, int halfHeight, List<BlockState> targets) implements FeatureConfiguration {
	public static final Codec<LargeSphereReplaceConfig> CODEC = RecordCodecBuilder.create((p_191250_) -> {
		return p_191250_.group(BlockState.CODEC.fieldOf("state").forGetter(LargeSphereReplaceConfig::state), IntProvider.codec(0, Integer.MAX_VALUE).fieldOf("radius").forGetter(LargeSphereReplaceConfig::radius), Codec.intRange(0, 4).fieldOf("half_height").forGetter(LargeSphereReplaceConfig::halfHeight), BlockState.CODEC.listOf().fieldOf("targets").forGetter(LargeSphereReplaceConfig::targets)).apply(p_191250_, LargeSphereReplaceConfig::new);
	});

	public LargeSphereReplaceConfig(BlockState state, IntProvider radius, int halfHeight, List<BlockState> targets) {
		this.state = state;
		this.radius = radius;
		this.halfHeight = halfHeight;
		this.targets = targets;
	}

	public BlockState state() {
		return this.state;
	}

	public IntProvider radius() {
		return this.radius;
	}

	public int halfHeight() {
		return this.halfHeight;
	}

	public List<BlockState> targets() {
		return this.targets;
	}
}