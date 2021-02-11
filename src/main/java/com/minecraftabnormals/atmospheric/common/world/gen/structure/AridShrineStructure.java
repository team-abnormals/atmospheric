package com.minecraftabnormals.atmospheric.common.world.gen.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

public class AridShrineStructure extends Structure<NoFeatureConfig> {
	private static final List<MobSpawnInfo.Spawners> SPAWN_LIST = ImmutableList.of();
	private static final List<MobSpawnInfo.Spawners> CREATURE_SPAWN_LIST = ImmutableList.of();

	public AridShrineStructure(Codec<NoFeatureConfig> config) {
		super(config);
	}

	@Override
	public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
		return AridShrineStructure.Start::new;
	}

	@Override
	public GenerationStage.Decoration getDecorationStage() {
		return GenerationStage.Decoration.SURFACE_STRUCTURES;
	}

	@Override
	public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
		return SPAWN_LIST;
	}

	@Override
	public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
		return CREATURE_SPAWN_LIST;
	}

	public static class Start extends StructureStart<NoFeatureConfig> {
		public Start(Structure<NoFeatureConfig> structure, int p_i225817_2_, int p_i225817_3_, MutableBoundingBox p_i225817_4_, int p_i225817_5_, long p_i225817_6_) {
			super(structure, p_i225817_2_, p_i225817_3_, p_i225817_4_, p_i225817_5_, p_i225817_6_);
		}

		public void func_230364_a_(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkY, Biome biome, NoFeatureConfig config) {
			Rotation rotation = Rotation.randomRotation(this.rand);
			BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkY * 16);
			AridShrinePieces.func_204760_a(manager, blockpos, rotation, this.components, this.rand);
			this.recalculateStructureSize();
		}
	}
} 
