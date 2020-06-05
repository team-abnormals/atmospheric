package com.bagel.atmospheric.common.world.gen.structure;

import java.util.Random;
import java.util.function.Function;

import com.bagel.atmospheric.core.Atmospheric;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AridShrineStructure extends Structure<NoFeatureConfig> {

	public AridShrineStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ)
	{
		int maxDistance = 15;
		int minDistance = 12;

		int xTemp = x + maxDistance * spacingOffsetsX;
		int ztemp = z + maxDistance * spacingOffsetsZ;
		int xTemp2 = xTemp < 0 ? xTemp - maxDistance + 1 : xTemp;
		int zTemp2 = ztemp < 0 ? ztemp - maxDistance + 1 : ztemp;
		int validChunkX = xTemp2 / maxDistance;
		int validChunkZ = zTemp2 / maxDistance;

		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), validChunkX, validChunkZ, this.getSeedModifier());
		validChunkX = validChunkX * maxDistance;
		validChunkZ = validChunkZ * maxDistance;
		validChunkX = validChunkX + random.nextInt(maxDistance - minDistance);
		validChunkZ = validChunkZ + random.nextInt(maxDistance - minDistance);

		return new ChunkPos(validChunkX, validChunkZ);
	}
	
	@Override
	public boolean func_225558_a_(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
		ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);
		if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
			if (generatorIn.hasStructure(biomeIn, this)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public IStartFactory getStartFactory() {
		return AridShrineStructure.Start::new;
	}

	@Override
	public String getStructureName() {
		return Atmospheric.MODID + ":arid_shrine";
	}

	@Override
	public int getSize() {
		return 0;
	}
	
	protected int getSeedModifier() {
		return 1244145112;
	}

	public static class Start extends StructureStart
	{
		public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
		{
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}


		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn)
		{
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];

			int x = (chunkX << 4) + 7;
			int z = (chunkZ << 4) + 7;

			int surfaceY = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
			BlockPos blockpos = new BlockPos(x, surfaceY, z);

			AridShrinePieces.start(templateManagerIn, blockpos, rotation, this.components, this.rand);

			this.recalculateStructureSize();

			System.out.println("Arid Shrine at " + (blockpos.getX()) + " " + blockpos.getY() + " " + (blockpos.getZ()));
		}

	}
}
