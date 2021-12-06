package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.*;

import java.util.Random;

public class SurfaceFossilFeature extends Feature<NoFeatureConfig> {
	private static final ResourceLocation STRUCTURE_SPINE_01 = new ResourceLocation("fossil/spine_1");
	private static final ResourceLocation STRUCTURE_SPINE_02 = new ResourceLocation("fossil/spine_2");
	private static final ResourceLocation STRUCTURE_SPINE_03 = new ResourceLocation("fossil/spine_3");
	private static final ResourceLocation STRUCTURE_SPINE_04 = new ResourceLocation("fossil/spine_4");
	private static final ResourceLocation STRUCTURE_SPINE_01_COAL = new ResourceLocation("fossil/spine_1_coal");
	private static final ResourceLocation STRUCTURE_SPINE_02_COAL = new ResourceLocation("fossil/spine_2_coal");
	private static final ResourceLocation STRUCTURE_SPINE_03_COAL = new ResourceLocation("fossil/spine_3_coal");
	private static final ResourceLocation STRUCTURE_SPINE_04_COAL = new ResourceLocation("fossil/spine_4_coal");
	private static final ResourceLocation STRUCTURE_SKULL_01 = new ResourceLocation("fossil/skull_1");
	private static final ResourceLocation STRUCTURE_SKULL_02 = new ResourceLocation("fossil/skull_2");
	private static final ResourceLocation STRUCTURE_SKULL_03 = new ResourceLocation("fossil/skull_3");
	private static final ResourceLocation STRUCTURE_SKULL_04 = new ResourceLocation("fossil/skull_4");
	private static final ResourceLocation STRUCTURE_SKULL_01_COAL = new ResourceLocation("fossil/skull_1_coal");
	private static final ResourceLocation STRUCTURE_SKULL_02_COAL = new ResourceLocation("fossil/skull_2_coal");
	private static final ResourceLocation STRUCTURE_SKULL_03_COAL = new ResourceLocation("fossil/skull_3_coal");
	private static final ResourceLocation STRUCTURE_SKULL_04_COAL = new ResourceLocation("fossil/skull_4_coal");
	private static final ResourceLocation[] FOSSILS = new ResourceLocation[]{STRUCTURE_SPINE_01, STRUCTURE_SPINE_02, STRUCTURE_SPINE_03, STRUCTURE_SPINE_04, STRUCTURE_SKULL_01, STRUCTURE_SKULL_02, STRUCTURE_SKULL_03, STRUCTURE_SKULL_04};
	private static final ResourceLocation[] FOSSILS_COAL = new ResourceLocation[]{STRUCTURE_SPINE_01_COAL, STRUCTURE_SPINE_02_COAL, STRUCTURE_SPINE_03_COAL, STRUCTURE_SPINE_04_COAL, STRUCTURE_SKULL_01_COAL, STRUCTURE_SKULL_02_COAL, STRUCTURE_SKULL_03_COAL, STRUCTURE_SKULL_04_COAL};

	public SurfaceFossilFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
		Rotation rotation = Rotation.getRandom(random);
		int i = random.nextInt(FOSSILS.length);
		TemplateManager templatemanager = world.getLevel().getServer().getStructureManager();
		Template template = templatemanager.getOrCreate(FOSSILS[i]);
		Template template1 = templatemanager.getOrCreate(FOSSILS_COAL[i]);
		ChunkPos chunkpos = new ChunkPos(pos);
		MutableBoundingBox mutableboundingbox = new MutableBoundingBox(chunkpos.getMinBlockX(), 0, chunkpos.getMinBlockZ(), chunkpos.getMaxBlockX(), 256, chunkpos.getMaxBlockZ());
		PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(mutableboundingbox).setRandom(random).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR);
		BlockPos blockpos = template.getSize(rotation);
		int j = random.nextInt(16 - blockpos.getX());
		int k = random.nextInt(16 - blockpos.getZ());
		int l = 256;

		for (int i1 = 0; i1 < blockpos.getX(); ++i1) {
			for (int j1 = 0; j1 < blockpos.getZ(); ++j1) {
				l = Math.min(l, world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos.getX() + i1 + j, pos.getZ() + j1 + k));
			}
		}

		int k1 = Math.max(l - 15 - random.nextInt(6), 65);
		BlockPos blockpos1 = template.getZeroPositionWithTransform(pos.offset(j, k1, k), Mirror.NONE, rotation);
		IntegrityProcessor integrityprocessor = new IntegrityProcessor(0.9F);
		placementsettings.clearProcessors().addProcessor(integrityprocessor);
		template.placeInWorld(world, blockpos1, blockpos1, placementsettings, random, 4);
		placementsettings.popProcessor(integrityprocessor);
		IntegrityProcessor integrityprocessor1 = new IntegrityProcessor(0.1F);
		placementsettings.clearProcessors().addProcessor(integrityprocessor1);
		template1.placeInWorld(world, blockpos1, blockpos1, placementsettings, random, 4);
		return true;
	}
}