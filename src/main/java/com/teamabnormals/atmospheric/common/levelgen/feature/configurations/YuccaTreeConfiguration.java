package com.teamabnormals.atmospheric.common.levelgen.feature.configurations;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class YuccaTreeConfiguration implements FeatureConfiguration {
	public static final Codec<YuccaTreeConfiguration> CODEC = RecordCodecBuilder.create((p_236683_0_) -> {
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
		})).apply(p_236683_0_, (BlockStateProvider trunkProvider1, BlockStateProvider leavesProvider1, BlockStateProvider branchProvider1, BlockStateProvider bundleProvider1, BlockStateProvider flowerProvider1, BlockStateProvider tallFlowerTopProvider1, BlockStateProvider tallFlowerBottomProvider1, List<TreeDecorator> decorators1, Boolean petrified1, Boolean patch1, Boolean baby1) -> new YuccaTreeConfiguration(trunkProvider1, leavesProvider1, branchProvider1, bundleProvider1, flowerProvider1, tallFlowerTopProvider1, tallFlowerBottomProvider1, decorators1, petrified1, patch1, baby1));
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

	protected YuccaTreeConfiguration(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider branchProvider, BlockStateProvider bundleProvider, BlockStateProvider flowerProvider, BlockStateProvider tallFlowerTopProvider, BlockStateProvider tallFlowerBottomProvider, List<TreeDecorator> decorators, boolean petrified, boolean patch, boolean baby) {
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
	}

	public void forcePlacement() {
		this.forcePlacement = true;
	}

	public YuccaTreeConfiguration withDecorators(List<TreeDecorator> p_236685_1_) {
		return new YuccaTreeConfiguration(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, p_236685_1_, this.petrified, this.patch, this.baby);
	}

	public YuccaTreeConfiguration configureType(boolean isPetrified, boolean hasPatch, boolean isBaby) {
		return new YuccaTreeConfiguration(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, isPetrified, hasPatch, isBaby);
	}

	public YuccaTreeConfiguration setPetrified() {
		return new YuccaTreeConfiguration(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, true, this.patch, this.baby);
	}

	public YuccaTreeConfiguration setPatch() {
		return new YuccaTreeConfiguration(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, this.petrified, true, this.baby);
	}

	public YuccaTreeConfiguration setBaby() {
		return new YuccaTreeConfiguration(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, this.petrified, this.patch, true);
	}

	public static class YuccaTreeConfigurationBuilder {
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

		public YuccaTreeConfigurationBuilder(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider branchProvider, BlockStateProvider bundleProvider, BlockStateProvider flowerProvider, BlockStateProvider tallFlowerTopProvider, BlockStateProvider tallFlowerBottomProvider) {
			this.trunkProvider = trunkProvider;
			this.leavesProvider = leavesProvider;
			this.branchProvider = branchProvider;
			this.bundleProvider = bundleProvider;
			this.flowerProvider = flowerProvider;
			this.tallFlowerTopProvider = tallFlowerTopProvider;
			this.tallFlowerBottomProvider = tallFlowerBottomProvider;
		}

		public YuccaTreeConfigurationBuilder decorators(List<TreeDecorator> p_236703_1_) {
			this.decorators = p_236703_1_;
			return this;
		}

		public YuccaTreeConfigurationBuilder isPetrified() {
			this.petrified = true;
			return this;
		}

		public YuccaTreeConfigurationBuilder hasPatch() {
			this.patch = true;
			return this;
		}

		public YuccaTreeConfigurationBuilder setBaby() {
			this.baby = true;
			return this;
		}

		public YuccaTreeConfiguration build() {
			return new YuccaTreeConfiguration(this.trunkProvider, this.leavesProvider, this.branchProvider, this.bundleProvider, this.flowerProvider, this.tallFlowerTopProvider, this.tallFlowerBottomProvider, this.decorators, this.petrified, this.patch, this.baby);
		}
	}
}