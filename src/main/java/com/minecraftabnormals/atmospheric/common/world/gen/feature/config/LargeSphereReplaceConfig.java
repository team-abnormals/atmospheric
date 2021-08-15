package com.minecraftabnormals.atmospheric.common.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.List;

public class LargeSphereReplaceConfig implements IFeatureConfig {
	public static final Codec<LargeSphereReplaceConfig> CODEC = RecordCodecBuilder.create((p_236518_0_) -> {
		return p_236518_0_.group(BlockState.CODEC.fieldOf("state").forGetter((p_236521_0_) -> {
			return p_236521_0_.state;
		}), FeatureSpread.codec(0, Integer.MAX_VALUE, 4).fieldOf("radius").forGetter((p_236520_0_) -> {
			return p_236520_0_.radius;
		}), Codec.intRange(0, 4).fieldOf("half_height").forGetter((p_236519_0_) -> {
			return p_236519_0_.halfHeight;
		}), BlockState.CODEC.listOf().fieldOf("targets").forGetter((p_236517_0_) -> {
			return p_236517_0_.targets;
		})).apply(p_236518_0_, LargeSphereReplaceConfig::new);
	});
	public final BlockState state;
	public final FeatureSpread radius;
	public final int halfHeight;
	public final List<BlockState> targets;

	public LargeSphereReplaceConfig(BlockState p_i241986_1_, FeatureSpread p_i241986_2_, int p_i241986_3_, List<BlockState> p_i241986_4_) {
		this.state = p_i241986_1_;
		this.radius = p_i241986_2_;
		this.halfHeight = p_i241986_3_;
		this.targets = p_i241986_4_;
	}
}