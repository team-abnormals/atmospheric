package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.FossilFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;
import java.util.Objects;

public class SurfaceFossilFeature extends Feature<FossilFeatureConfiguration> {

	public SurfaceFossilFeature(Codec<FossilFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<FossilFeatureConfiguration> context) {
		RandomSource random = context.random();
		WorldGenLevel level = context.level();
		BlockPos origin = context.origin();
		Rotation rotation = Rotation.getRandom(random);
		FossilFeatureConfiguration config = context.config();
		int fossilIndex = random.nextInt(config.fossilStructures.size());
		StructureTemplateManager structureManager = level.getLevel().getServer().getStructureManager();
		StructureTemplate fossilStructure = structureManager.getOrCreate(config.fossilStructures.get(fossilIndex));
		ChunkPos chunkPos = new ChunkPos(origin);
		BoundingBox boundingBox = new BoundingBox(chunkPos.getMinBlockX() - 16, level.getMinBuildHeight(), chunkPos.getMinBlockZ() - 16, chunkPos.getMaxBlockX() + 16, level.getMaxBuildHeight(), chunkPos.getMaxBlockZ() + 16);
		StructurePlaceSettings settings = (new StructurePlaceSettings()).setRotation(rotation).setBoundingBox(boundingBox).setRandom(random);
		Vec3i size = fossilStructure.getSize(rotation);
		BlockPos offsetPos = origin.offset(-size.getX() / 2, 0, -size.getZ() / 2);
		int y = level.getMaxBuildHeight();

		int i;
		for (i = 0; i < size.getX(); ++i) {
			for (int j = 0; j < size.getZ(); ++j) {
				y = Math.min(y, level.getHeight(Types.WORLD_SURFACE_WG, offsetPos.getX() + i, offsetPos.getZ() + j));
			}
		}

		i = Math.max(y - random.nextInt(10), level.getMinBuildHeight() + 64);
		BlockPos pos = fossilStructure.getZeroPositionWithTransform(offsetPos.atY(i), Mirror.NONE, rotation);

		if (countEmptyCorners(level, fossilStructure.getBoundingBox(settings, pos)) > config.maxEmptyCornersAllowed) {
			return false;
		} else {
			settings.clearProcessors();
			List<StructureProcessor> processors = config.fossilProcessors.value().list();
			Objects.requireNonNull(settings);
			processors.forEach(settings::addProcessor);
			fossilStructure.placeInWorld(level, pos, pos, settings, random, 4);
			return true;
		}
	}

	private static int countEmptyCorners(WorldGenLevel level, BoundingBox boundingBox) {
		MutableInt mutableInt = new MutableInt(0);
		boundingBox.forAllCorners((pos) -> {
			BlockState state = level.getBlockState(pos);
			if (state.isAir() || state.is(Blocks.LAVA) || state.is(Blocks.WATER)) {
				mutableInt.add(1);
			}

		});
		return mutableInt.getValue();
	}
}
