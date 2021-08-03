package com.minecraftabnormals.atmospheric.common.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AridShrineStructure extends Structure<NoFeatureConfig> {

	public AridShrineStructure(Codec<NoFeatureConfig> config) {
		super(config);
	}

	@Override
	public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
		return AridShrineStructure.Start::new;
	}

	@Override
	public GenerationStage.Decoration step() {
		return GenerationStage.Decoration.SURFACE_STRUCTURES;
	}

	public static class Start extends StructureStart<NoFeatureConfig> {
		public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkY, MutableBoundingBox boundingBox, int p_i225817_5_, long p_i225817_6_) {
			super(structure, chunkX, chunkY, boundingBox, p_i225817_5_, p_i225817_6_);
		}

		@Override
		public void generatePieces(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkY, Biome biome, NoFeatureConfig config) {
			ChunkPos chunkpos = new ChunkPos(chunkX, chunkY);
			int structureX = chunkpos.getMinBlockX() + this.random.nextInt(16);
			int structureZ = chunkpos.getMinBlockZ() + this.random.nextInt(16);
			int seaLevel = generator.getSeaLevel();
			int structureY = seaLevel + this.random.nextInt(generator.getGenDepth() - seaLevel);
			IBlockReader reader = generator.getBaseColumn(structureX, structureZ);

			for (BlockPos.Mutable pos = new BlockPos.Mutable(structureX, structureY, structureZ); structureY > seaLevel; --structureY) {
				BlockState blockstate = reader.getBlockState(pos);
				pos.move(Direction.DOWN);
				BlockState state = reader.getBlockState(pos);
				if (blockstate.isAir() && state.isFaceSturdy(reader, pos, Direction.UP)) {
					break;
				}
			}

			if (structureY > seaLevel) {
				Rotation rotation = Rotation.getRandom(this.random);
				this.pieces.add(new AridShrinePieces.Piece(manager, new BlockPos(structureX, structureY - 5, structureZ), rotation));
				this.calculateBoundingBox();
			}
		}
	}
} 
