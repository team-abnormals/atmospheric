package com.minecraftabnormals.atmospheric.common.world.gen.structure;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.ArrayList;
import java.util.Random;

public class AridShrinePieces {
	private static final BlockPos STRUCTURE_OFFSET = new BlockPos(0, 0, 0);
	private static final ResourceLocation STRUCTURE = new ResourceLocation(Atmospheric.MOD_ID, "arid_shrine/arid_shrine");

	public static class Piece extends TemplateStructurePiece {
		private final Rotation rotation;

		public Piece(TemplateManager p_i48904_1_, BlockPos p_i48904_3_, Rotation p_i48904_4_) {
			super(AtmosphericStructures.Pieces.ARID_SHRINE_PIECE, 0);
			this.templatePosition = p_i48904_3_;
			this.rotation = p_i48904_4_;
			this.loadTemplate(p_i48904_1_);
		}

		public Piece(TemplateManager p_i50445_1_, CompoundNBT p_i50445_2_) {
			super(AtmosphericStructures.Pieces.ARID_SHRINE_PIECE, p_i50445_2_);
			this.rotation = Rotation.valueOf(p_i50445_2_.getString("Rot"));
			this.loadTemplate(p_i50445_1_);
		}

		@Override
		protected void addAdditionalSaveData(CompoundNBT tagCompound) {
			super.addAdditionalSaveData(tagCompound);
			tagCompound.putString("Rot", this.rotation.name());
		}

		private void loadTemplate(TemplateManager manager) {
			Template template = manager.getOrCreate(STRUCTURE);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(AridShrinePieces.STRUCTURE_OFFSET).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {
			if ("decor".equals(function)) {
				ArrayList<BlockState> stateList = new ArrayList<>();
				stateList.add(AtmosphericBlocks.ALOE_BUNDLE.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Axis.X));
				stateList.add(AtmosphericBlocks.ALOE_BUNDLE.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Axis.Y));
				stateList.add(AtmosphericBlocks.ALOE_BUNDLE.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Axis.Z));
				stateList.add(AtmosphericBlocks.ALOE_GEL_BLOCK.get().defaultBlockState());
				stateList.add(AtmosphericBlocks.POTTED_BARREL_CACTUS.get().defaultBlockState());
				stateList.add(AtmosphericBlocks.POTTED_GILIA.get().defaultBlockState());
				stateList.add(AtmosphericBlocks.POTTED_YUCCA_FLOWER.get().defaultBlockState());
				stateList.add(AtmosphericBlocks.POTTED_YUCCA_SAPLING.get().defaultBlockState());
				stateList.add(AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get().defaultBlockState());
				stateList.add(AtmosphericBlocks.YUCCA_BUNDLE.get().defaultBlockState());
				stateList.add(AtmosphericBlocks.YUCCA_GATEAU.get().defaultBlockState().setValue(HorizontalBlock.FACING, Direction.NORTH));
				stateList.add(AtmosphericBlocks.YUCCA_GATEAU.get().defaultBlockState().setValue(HorizontalBlock.FACING, Direction.EAST));
				stateList.add(AtmosphericBlocks.YUCCA_GATEAU.get().defaultBlockState().setValue(HorizontalBlock.FACING, Direction.SOUTH));
				stateList.add(AtmosphericBlocks.YUCCA_GATEAU.get().defaultBlockState().setValue(HorizontalBlock.FACING, Direction.WEST));

				worldIn.setBlock(pos, stateList.get(rand.nextInt(stateList.size())), 2);
			}
		}

		@Override
		public boolean postProcess(ISeedReader world, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
			return super.postProcess(world, manager, generator, random, boundingBox, chunkPos, blockPos);
		}
	}
} 