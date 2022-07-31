package com.minecraftabnormals.atmospheric.common.levelgen.feature.config;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class YuccaTreeFeatureConfig implements FeatureConfiguration {
	public static final Codec<YuccaTreeFeatureConfig> CODEC = RecordCodecBuilder.create((p_236683_0_) -> {
		return p_236683_0_.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((p_236693_0_) -> {
			return p_236693_0_.trunkProvider;
		}), BlockStateProvider.CODEC.fieldOf("leaves_provider").forGetter((p_236692_0_) -> {
			return p_236692_0_.leavesProvider;
		}), BlockStateProvider.CODEC.fieldOf("branch_provider").forGetter((p_236692_0_) -> {
			return p_236692_0_.branchProvider;
		}), BlockStateProvider.CODEC.fieldOf("bundle_provider").forGetter((p_236692_0_) -> {
			return p_236692_0_.bundleProvider;
		}), BlockStateProvider.CODEC.fieldOf("flower_provider").forGetter((p_236692_0_) -> {
			return p_236692_0_.flowerProvider;
		}), BlockStateProvider.CODEC.fieldOf("tall_flower_top_provider").forGetter((p_236692_0_) -> {
			return p_236692_0_.tallFlowerTopProvider;
		}), BlockStateProvider.CODEC.fieldOf("tall_flower_bottom_provider").forGetter((p_236692_0_) -> {
			return p_236692_0_.tallFlowerBottomProvider;
		}), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((p_236688_0_) -> {
			return p_236688_0_.decorators;
		}), Codec.BOOL.fieldOf("petrified").orElse(false).forGetter((p_236686_0_) -> {
			return p_236686_0_.petrified;
		}), Codec.BOOL.fieldOf("patch").orElse(false).forGetter((p_236686_0_) -> {
			return p_236686_0_.patch;
		}), Codec.BOOL.fieldOf("baby").orElse(false).forGetter((p_236686_0_) -> {
			return p_236686_0_.baby;
		}), Heightmap.Types.CODEC.fieldOf("heightmap").forGetter((p_236684_0_) -> {
			return p_236684_0_.heightmap;
		})).apply(p_236683_0_, YuccaTreeFeatureConfig::new);
	});

	public final BlockStateProvider trunkProvider;
	public final BlockStateProvider leavesProvider;
	public final BlockStateProvider branchProvider;
	public final BlockStateProvider bundleProvider;
	public final BlockStateProvider flowerProvider;
	public final BlockStateProvider tallFlowerTopProvider;
	public final BlockStateProvider tallFlowerBottomProvider;

	public final List<TreeDecorator> decorators;
	public transient boolean forcePlacement;
	public final boolean petrified;
	public final boolean patch;
	public final boolean baby;
	public final Heightmap.Types heightmap;

	protected YuccaTreeFeatureConfig(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider branchProvider, BlockStateProvider bundleProvider, BlockStateProvider flowerProvider, BlockStateProvider tallFlowerTopProvider, BlockStateProvider tallFlowerBottomProvider, List<TreeDecorator> decorators, boolean petrified, boolean patch, boolean baby, Heightmap.Types p_i232020_9_) {
		this.trunkProvider = trunkProvider;
		this.leavesProvider = leavesProvider;
		this.branchProvider = branchProvider;
		this.bundleProvider = bundleProvider;
		this.flowerProvider = flowerProvider;
		this.tallFlowerTopProvider = tallFlowerTopProvider;
		this.tallFlowerBottomProvider = tallFlowerBottomProvider;
		this.decorators = decorators;
		this.petrified = petrified;
		this.patch = patch;
		this.baby = baby;
		this.heightmap = p_i232020_9_;
	}

	public void forcePlacement() {
		this.forcePlacement = true;
	}

	public YuccaTreeFeatureConfig withDecorators(List<TreeDecorator> p_236685_1_) {
		return new YuccaTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, p_236685_1_, this.petrified, this.patch, this.baby, this.heightmap);
	}

	public YuccaTreeFeatureConfig configureType(boolean isPetrified, boolean hasPatch, boolean isBaby) {
		return new YuccaTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, isPetrified, hasPatch, isBaby, this.heightmap);
	}

	public YuccaTreeFeatureConfig setPetrified() {
		return new YuccaTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, true, this.patch, this.baby, this.heightmap);
	}

	public YuccaTreeFeatureConfig setPatch() {
		return new YuccaTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, this.petrified, true, this.baby, this.heightmap);
	}

	public YuccaTreeFeatureConfig setBaby() {
		return new YuccaTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, this.petrified, this.patch, true, this.heightmap);
	}

	public static class Builder {
		public final BlockStateProvider trunkProvider;
		public final BlockStateProvider leavesProvider;
		public final BlockStateProvider branchProvider;
		public final BlockStateProvider bundleProvider;
		public final BlockStateProvider flowerProvider;
		public final BlockStateProvider tallFlowerTopProvider;
		public final BlockStateProvider tallFlowerBottomProvider;

		private List<TreeDecorator> decorators = ImmutableList.of();
		private boolean petrified;
		private boolean patch;
		private boolean baby;
		private Heightmap.Types heightmap = Heightmap.Types.OCEAN_FLOOR;

		public Builder(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider branchProvider, BlockStateProvider bundleProvider, BlockStateProvider flowerProvider, BlockStateProvider tallFlowerTopProvider, BlockStateProvider tallFlowerBottomProvider) {
			this.trunkProvider = trunkProvider;
			this.leavesProvider = leavesProvider;
			this.branchProvider = branchProvider;
			this.bundleProvider = bundleProvider;
			this.flowerProvider = flowerProvider;
			this.tallFlowerTopProvider = tallFlowerTopProvider;
			this.tallFlowerBottomProvider = tallFlowerBottomProvider;
		}

		public YuccaTreeFeatureConfig.Builder decorators(List<TreeDecorator> p_236703_1_) {
			this.decorators = p_236703_1_;
			return this;
		}

		public YuccaTreeFeatureConfig.Builder isPetrified() {
			this.petrified = true;
			return this;
		}

		public YuccaTreeFeatureConfig.Builder hasPatch() {
			this.patch = true;
			return this;
		}

		public YuccaTreeFeatureConfig.Builder setBaby() {
			this.baby = true;
			return this;
		}

		public YuccaTreeFeatureConfig.Builder setHeightmap(Heightmap.Types heightmap) {
			this.heightmap = heightmap;
			return this;
		}

		public YuccaTreeFeatureConfig build() {
			return new YuccaTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, this.petrified, this.patch, this.baby, this.heightmap);
		}
	}
}