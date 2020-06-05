package com.bagel.atmospheric.common.world.gen.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.bagel.atmospheric.core.Atmospheric;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;
import com.google.common.collect.ImmutableMap;

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
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AridShrinePieces {
	
	private static final ResourceLocation MAIN = new ResourceLocation(Atmospheric.MODID + ":arid_shrine/arid_shrine");
	private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.of(MAIN, new BlockPos(0, -3, 0));

	public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random)
	{
		int x = pos.getX();
		int z = pos.getZ();

		BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(rotation);
		BlockPos blockpos = rotationOffSet.add(x, pos.getY(), z);
		pieceList.add(new AridShrinePieces.Piece(templateManager, MAIN, blockpos, rotation));
	}
	
	public static class Piece extends TemplateStructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;


		public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
			super(AtmosphericFeatures.ARID_SHRINE_PIECES, 0);
			this.resourceLocation = resourceLocationIn;
			BlockPos blockpos = AridShrinePieces.OFFSET.get(resourceLocation);
			this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
			this.rotation = rotationIn;
			this.setupPiece(templateManagerIn);
		}


		public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound) {
			super(AtmosphericFeatures.ARID_SHRINE_PIECES, tagCompound);
			this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
			this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
			this.setupPiece(templateManagerIn);
		}


		private void setupPiece(TemplateManager templateManager) {
			Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", this.resourceLocation.toString());
			tagCompound.putString("Rot", this.rotation.name());
		}
		
		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
			if ("shrine_decor".equals(function))
			{
				ArrayList<BlockState> stateList = new ArrayList<>();
				stateList.add(AtmosphericBlocks.ALOE_BUNDLE.get().getDefaultState().with(RotatedPillarBlock.AXIS, Axis.X));
				stateList.add(AtmosphericBlocks.ALOE_BUNDLE.get().getDefaultState().with(RotatedPillarBlock.AXIS, Axis.Y));
				stateList.add(AtmosphericBlocks.ALOE_BUNDLE.get().getDefaultState().with(RotatedPillarBlock.AXIS, Axis.Z));
				stateList.add(AtmosphericBlocks.ALOE_GEL_BLOCK.get().getDefaultState());
				stateList.add(AtmosphericBlocks.POTTED_BARREL_CACTUS.get().getDefaultState());
				stateList.add(AtmosphericBlocks.POTTED_GILIA.get().getDefaultState());
				stateList.add(AtmosphericBlocks.POTTED_YUCCA_FLOWER.get().getDefaultState());
				stateList.add(AtmosphericBlocks.POTTED_YUCCA_SAPLING.get().getDefaultState());
				stateList.add(AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get().getDefaultState());
				stateList.add(AtmosphericBlocks.YUCCA_BUNDLE.get().getDefaultState());
				stateList.add(AtmosphericBlocks.YUCCA_GATEAU.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.NORTH));
				stateList.add(AtmosphericBlocks.YUCCA_GATEAU.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.EAST));
				stateList.add(AtmosphericBlocks.YUCCA_GATEAU.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH));
				stateList.add(AtmosphericBlocks.YUCCA_GATEAU.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.WEST));
				
				worldIn.setBlockState(pos, stateList.get(rand.nextInt(stateList.size())), 2);
			}
		}


		@Override
		public boolean func_225577_a_(IWorld worldIn, ChunkGenerator<?> p_225577_2_, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
			BlockPos blockpos = AridShrinePieces.OFFSET.get(this.resourceLocation);
			this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(0 - blockpos.getX(), 0, 0 - blockpos.getZ())));

			return super.func_225577_a_(worldIn, p_225577_2_, randomIn, structureBoundingBoxIn, chunkPos);
		}
	}
}
