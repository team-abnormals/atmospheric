package com.teamabnormals.atmospheric.core.data.client;

import com.teamabnormals.atmospheric.common.block.DragonRootsBlock;
import com.teamabnormals.atmospheric.common.block.OrangeBlock;
import com.teamabnormals.atmospheric.common.block.state.properties.DragonRootsStage;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericBlockFamilies;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.Plane;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericBlockStateProvider extends BlueprintBlockStateProvider {

	public AtmosphericBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Atmospheric.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.blockFamily(AtmosphericBlockFamilies.DOLERITE_FAMILY);
		this.blockFamily(AtmosphericBlockFamilies.POLISHED_DOLERITE_FAMILY);

		this.blockFamily(AtmosphericBlockFamilies.ROSEWOOD_PLANKS_FAMILY);
		this.logBlocks(ROSEWOOD_LOG, ROSEWOOD);
		this.logBlocks(STRIPPED_ROSEWOOD_LOG, STRIPPED_ROSEWOOD);
		this.leavesBlock(ROSEWOOD_LEAVES);
		this.crossBlockWithPot(ROSEWOOD_SAPLING, POTTED_ROSEWOOD_SAPLING);
		this.hangingSignBlocks(STRIPPED_ROSEWOOD_LOG, ROSEWOOD_HANGING_SIGNS);
		this.woodworksBlocks(ROSEWOOD_PLANKS, ROSEWOOD_BOARDS, ROSEWOOD_LADDER, ROSEWOOD_BOOKSHELF, ROSEWOOD_BEEHIVE, ROSEWOOD_CHEST, TRAPPED_ROSEWOOD_CHEST);
		this.chiseledBookshelfBlock(CHISELED_ROSEWOOD_BOOKSHELF, DEFAULT_BOOKSHELF_POSITIONS);
		this.leafPileBlock(ROSEWOOD_LEAVES, ROSEWOOD_LEAF_PILE);

		this.directionalBlock(PASSION_FRUIT_CRATE);
		this.directionalBlockSharedBottom(SHIMMERING_PASSION_FRUIT_CRATE, PASSION_FRUIT_CRATE);

		this.blockFamily(AtmosphericBlockFamilies.MORADO_PLANKS_FAMILY);
		this.logBlocks(MORADO_LOG, MORADO_WOOD);
		this.logBlocks(STRIPPED_MORADO_LOG, STRIPPED_MORADO_WOOD);
		this.leavesBlock(MORADO_LEAVES);
		this.crossBlockWithPot(MORADO_SAPLING, POTTED_MORADO_SAPLING);
		this.hangingSignBlocks(STRIPPED_MORADO_LOG, MORADO_HANGING_SIGNS);
		this.woodworksBlocks(MORADO_PLANKS, MORADO_BOARDS, MORADO_LADDER, MORADO_BOOKSHELF, MORADO_BEEHIVE, MORADO_CHEST, TRAPPED_MORADO_CHEST);
		this.chiseledBookshelfBlock(CHISELED_MORADO_BOOKSHELF, ALTERNATE_BOOKSHELF_POSITIONS);
		this.leafPileBlock(MORADO_LEAVES, MORADO_LEAF_PILE);

		this.blockFamily(AtmosphericBlockFamilies.YUCCA_PLANKS_FAMILY);
		this.logBlocks(YUCCA_LOG, YUCCA_WOOD);
		this.logBlocks(STRIPPED_YUCCA_LOG, STRIPPED_YUCCA_WOOD);
		this.leavesBlock(YUCCA_LEAVES);
		this.crossBlockWithPot(YUCCA_SAPLING, POTTED_YUCCA_SAPLING);
		this.hangingSignBlocks(STRIPPED_YUCCA_LOG, YUCCA_HANGING_SIGNS);
		this.woodworksBlocks(YUCCA_PLANKS, YUCCA_BOARDS, YUCCA_LADDER, YUCCA_BOOKSHELF, YUCCA_BEEHIVE, YUCCA_CHEST, TRAPPED_YUCCA_CHEST);
		this.chiseledBookshelfBlock(CHISELED_YUCCA_BOOKSHELF, DEFAULT_BOOKSHELF_POSITIONS);
		this.leafPileBlock(YUCCA_LEAVES, YUCCA_LEAF_PILE);

		this.directionalBlock(YUCCA_CASK);
		this.directionalBlockSharedSide(ROASTED_YUCCA_CASK, YUCCA_CASK);

		this.flowerPotBlock(POTTED_ALOE_VERA);

		this.brushableBlock(SUSPICIOUS_ARID_SAND);
		this.brushableBlock(SUSPICIOUS_RED_ARID_SAND);

//		this.blockFamily(AtmosphericBlockFamilies.ARID_SANDSTONE_FAMILY, ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.CUT_ARID_SANDSTONE_FAMILY, CUT_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.SMOOTH_ARID_SANDSTONE_FAMILY, SMOOTH_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.ARID_SANDSTONE_BRICKS_FAMILY, ARID_SANDSTONE_BRICK_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.RED_ARID_SANDSTONE_FAMILY, RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.CUT_RED_ARID_SANDSTONE_FAMILY, CUT_RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.SMOOTH_RED_ARID_SANDSTONE_FAMILY, SMOOTH_RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.RED_ARID_SANDSTONE_BRICKS_FAMILY, RED_ARID_SANDSTONE_BRICK_VERTICAL_SLAB.get());

		this.blockFamily(AtmosphericBlockFamilies.ASPEN_PLANKS_FAMILY);
		this.logBlocks(ASPEN_LOG, ASPEN_WOOD);
		this.logBlocks(STRIPPED_ASPEN_LOG, STRIPPED_ASPEN_WOOD);
		this.watchfulAspenLogBlocks(ASPEN_LOG, WATCHFUL_ASPEN_LOG, WATCHFUL_ASPEN_WOOD);
		this.leavesBlock(ASPEN_LEAVES);
		this.crossBlockWithPot(ASPEN_SAPLING, POTTED_ASPEN_SAPLING);
		this.hangingSignBlocks(STRIPPED_ASPEN_LOG, ASPEN_HANGING_SIGNS);
		this.woodworksBlocks(ASPEN_PLANKS, ASPEN_BOARDS, ASPEN_LADDER, ASPEN_BOOKSHELF, ASPEN_BEEHIVE, ASPEN_CHEST, TRAPPED_ASPEN_CHEST);
		this.chiseledBookshelfBlock(CHISELED_ASPEN_BOOKSHELF, ALTERNATE_BOOKSHELF_POSITIONS);
		this.leafPileBlock(ASPEN_LEAVES, ASPEN_LEAF_PILE);
		this.leavesBlock(GREEN_ASPEN_LEAVES);
		this.crossBlockWithPot(GREEN_ASPEN_SAPLING, POTTED_GREEN_ASPEN_SAPLING);
		this.leafPileBlock(GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE);

		this.crossBlockWithCustomPot(AGAVE, POTTED_AGAVE);
		this.crossBlockWithCustomPot(GOLDEN_GROWTHS, POTTED_GOLDEN_GROWTHS);

		this.blockFamily(AtmosphericBlockFamilies.LAUREL_PLANKS_FAMILY);
		this.logBlocks(LAUREL_LOG, LAUREL_WOOD);
		this.logBlocks(STRIPPED_LAUREL_LOG, STRIPPED_LAUREL_WOOD);
		this.leavesBlock(LAUREL_LEAVES);
		this.crossBlockWithPot(LAUREL_SAPLING, POTTED_LAUREL_SAPLING);
		this.hangingSignBlocks(STRIPPED_LAUREL_LOG, LAUREL_HANGING_SIGNS);
		this.woodworksBlocks(LAUREL_PLANKS, LAUREL_BOARDS, LAUREL_LADDER, LAUREL_BOOKSHELF, LAUREL_BEEHIVE, LAUREL_CHEST, TRAPPED_LAUREL_CHEST);
		this.chiseledBookshelfBlock(CHISELED_LAUREL_BOOKSHELF);
		this.leafPileBlock(LAUREL_LEAVES, LAUREL_LEAF_PILE);
		this.leavesBlock(DRY_LAUREL_LEAVES);
		this.crossBlockWithPot(DRY_LAUREL_SAPLING, POTTED_DRY_LAUREL_SAPLING);
		this.leafPileBlock(DRY_LAUREL_LEAVES, DRY_LAUREL_LEAF_PILE);

		this.orange(ORANGE);
		this.orange(BLOOD_ORANGE);
		this.directionalBlock(ORANGE_CRATE);
		this.directionalBlock(BLOOD_ORANGE_CRATE);

		this.blockFamily(AtmosphericBlockFamilies.KOUSA_PLANKS_FAMILY);
		this.logBlocks(KOUSA_LOG, KOUSA_WOOD);
		this.logBlocks(STRIPPED_KOUSA_LOG, STRIPPED_KOUSA_WOOD);
		this.leavesBlock(KOUSA_LEAVES);
		this.crossBlockWithPot(KOUSA_SAPLING, POTTED_KOUSA_SAPLING);
		this.hangingSignBlocks(STRIPPED_KOUSA_LOG, KOUSA_HANGING_SIGNS);
		this.woodworksBlocks(KOUSA_PLANKS, KOUSA_BOARDS, KOUSA_LADDER, KOUSA_BOOKSHELF, KOUSA_BEEHIVE, KOUSA_CHEST, TRAPPED_KOUSA_CHEST);
		this.chiseledBookshelfBlock(CHISELED_KOUSA_BOOKSHELF, DEFAULT_BOOKSHELF_POSITIONS);
		this.leafPileBlock(KOUSA_LEAVES, KOUSA_LEAF_PILE);

		this.crossBlock(HANGING_CURRANT);
		this.logBlock(CURRANT_STALK_BUNDLE);
		this.leavesBlock(CURRANT_LEAVES);
		this.crossBlockWithPot(CURRANT_SEEDLING, POTTED_CURRANT_SEEDLING);
		this.leafPileBlock(CURRANT_LEAVES, CURRANT_LEAF_PILE);
		this.directionalBlock(CURRANT_CRATE);

		this.blockFamily(AtmosphericBlockFamilies.GRIMWOOD_PLANKS_FAMILY);
		this.logBlocks(GRIMWOOD_LOG, GRIMWOOD);
		this.logBlocks(STRIPPED_GRIMWOOD_LOG, STRIPPED_GRIMWOOD);
		this.leavesBlock(GRIMWOOD_LEAVES);
		this.crossBlockWithPot(GRIMWOOD_SAPLING, POTTED_GRIMWOOD_SAPLING);
		this.hangingSignBlocks(STRIPPED_GRIMWOOD_LOG, GRIMWOOD_HANGING_SIGNS);
		this.woodworksBlocks(GRIMWOOD_PLANKS, GRIMWOOD_BOARDS, GRIMWOOD_LADDER, GRIMWOOD_BOOKSHELF, GRIMWOOD_BEEHIVE, GRIMWOOD_CHEST, TRAPPED_GRIMWOOD_CHEST);
		this.chiseledBookshelfBlock(CHISELED_GRIMWOOD_BOOKSHELF, ALTERNATE_BOOKSHELF_POSITIONS);
		this.leafPileBlock(GRIMWOOD_LEAVES, GRIMWOOD_LEAF_PILE);

		this.crossBlock(GRIMWEB);

		this.directionalBlock(CARMINE_BLOCK);
		this.blockFamily(AtmosphericBlockFamilies.CARMINE_SHINGLES_FAMILY);
		this.block(CHISELED_CARMINE_SHINGLES.get());
		this.blockFamily(AtmosphericBlockFamilies.CARMINE_PAVEMENT_FAMILY);
		this.crossBlockWithPot(FIRETHORN, POTTED_FIRETHORN);
		this.crossBlockWithPot(FORSYTHIA, POTTED_FORSYTHIA);
		this.directionalBlock(DRAGON_FRUIT_CRATE);
		this.directionalBlockSharedSide(GOLDEN_DRAGON_FRUIT_CRATE, DRAGON_FRUIT_CRATE);
		this.dragonRoots(DRAGON_ROOTS);

		this.block(ARID_GLASS);
		this.glassPaneBlock(ARID_GLASS_PANE, ARID_GLASS);
	}

	public void watchfulAspenLogBlocks(RegistryObject<Block> aspenLog, RegistryObject<Block> log, RegistryObject<Block> wood) {
		this.logBlock(log, blockTexture(log.get()), suffix(blockTexture(aspenLog.get()), "_top"));
		this.woodBlock(wood, log);
	}

	public void orange(RegistryObject<Block> block) {
		this.getVariantBuilder(block.get()).forAllStatesExcept(state -> {
			boolean horizontal = Plane.HORIZONTAL.test(state.getValue(BlockStateProperties.FACING));
			String addition = (state.getValue(OrangeBlock.ORANGES) == 2 ? "_double" : "") + (horizontal ? "_wall" : state.getValue(OrangeBlock.FACING) == Direction.DOWN ? "_ceiling" : "");
			return ConfiguredModel.builder()
					.modelFile(models().getBuilder(name(block.get()) + addition).parent(new UncheckedModelFile(Atmospheric.location("block/template_orange" + addition))).texture("orange", blockTexture(block.get())))
					.rotationY(horizontal ? (int) ((state.getValue(BlockStateProperties.FACING).toYRot() + 180) % 360) : 0)
					.build();
		}, BlockStateProperties.WATERLOGGED);
	}

	public void dragonRoots(RegistryObject<Block> block) {
		MultiPartBlockStateBuilder builder = this.getMultipartBuilder(block.get());
		DragonRootsBlock.FACING.getPossibleValues().forEach(dir -> {
			int yRot = (((int) dir.toYRot()) + 180) % 360;

			for (DragonRootsStage stage : DragonRootsStage.values()) {
				if (stage != DragonRootsStage.NONE) {
					builder.part().modelFile(new UncheckedModelFile(Atmospheric.location("block/dragon_roots_top"))).rotationY(yRot).addModel().condition(DragonRootsBlock.TOP_STAGE, stage).condition(DragonRootsBlock.FACING, dir);
					builder.part().modelFile(new UncheckedModelFile(Atmospheric.location("block/dragon_roots_bottom"))).rotationY(yRot).addModel().condition(DragonRootsBlock.BOTTOM_STAGE, stage).condition(DragonRootsBlock.FACING, dir);

					if (stage != DragonRootsStage.ROOTS) {
						boolean flowering = stage == DragonRootsStage.FLOWERING || stage == DragonRootsStage.FLOWERING_ENDER;
						boolean ender = stage == DragonRootsStage.ENDER || stage == DragonRootsStage.FLOWERING_ENDER;

						String name = (flowering ? "flowering_" : "") + (ender ? "ender_" : "") + "dragon_fruit";
						String texture = Atmospheric.location("block/" + name).toString();
						String parent = "block/template_" + (flowering ? "flowering_" : "") + "dragon_fruit";

						ModelBuilder<?> top = models().getBuilder(Atmospheric.location(name) + "_top").parent(new UncheckedModelFile(Atmospheric.location(parent + "_top"))).texture("fruit", texture);
						ModelBuilder<?> bottom = models().getBuilder(Atmospheric.location(name) + "_bottom").parent(new UncheckedModelFile(Atmospheric.location(parent + "_bottom"))).texture("fruit", texture);

						if (flowering) {
							ResourceLocation emissiveTexture = Atmospheric.location("block/flowering_dragon_fruit_emissive");
							top = top.texture("overlay", emissiveTexture).renderType("translucent");
							bottom = bottom.texture("overlay", emissiveTexture).renderType("translucent");
						}

						builder.part().modelFile(top).rotationY(yRot).addModel().condition(DragonRootsBlock.TOP_STAGE, stage).condition(DragonRootsBlock.FACING, dir);
						builder.part().modelFile(bottom).rotationY(yRot).addModel().condition(DragonRootsBlock.BOTTOM_STAGE, stage).condition(DragonRootsBlock.FACING, dir);
					}
				}
			}
		});
	}

	public void glassPaneBlock(RegistryObject<Block> pane, RegistryObject<Block> glass) {
		Block block = pane.get();
		String name = name(block);

		ResourceLocation texture = blockTexture(glass.get());
		ResourceLocation edgeTexture = texture.withSuffix("_pane_top");

		ModelFile post = glassPaneBlock(name, "post").texture("pane", texture).texture("edge", edgeTexture);
		ModelFile side = glassPaneBlock(name, "side").texture("pane", texture).texture("edge", edgeTexture);
		ModelFile sideAlt = glassPaneBlock(name, "side_alt").texture("pane", texture).texture("edge", edgeTexture);
		ModelFile noSide = glassPaneBlock(name, "noside").texture("pane", texture);
		ModelFile noSideAlt = glassPaneBlock(name, "noside_alt").texture("pane", texture);

		this.glassPaneBlock(block, post, side, sideAlt, noSide, noSideAlt);
		this.generatedItem(block, prefix("block/", BlueprintItemModelProvider.key(glass.get())));
	}

	public void glassPaneBlock(Block block, ModelFile post, ModelFile side, ModelFile sideAlt, ModelFile noSide, ModelFile noSideAlt) {
		MultiPartBlockStateBuilder builder = getMultipartBuilder(block).part().modelFile(post).addModel().end();
		PipeBlock.PROPERTY_BY_DIRECTION.forEach((dir, value) -> {
			if (dir.getAxis().isHorizontal()) {
				builder.part().modelFile(dir == Direction.SOUTH || dir == Direction.WEST ? sideAlt : side).rotationY(dir.getAxis() == Axis.X ? 90 : 0).addModel().condition(value, true).end();
				builder.part().modelFile(dir == Direction.SOUTH || dir == Direction.EAST ? noSideAlt : noSide).rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel().condition(value, false).end();
			}
		});
	}

	public BlockModelBuilder glassPaneBlock(String name, String suffix) {
		return models().getBuilder(name + "_" + suffix).parent(new UncheckedModelFile(new ResourceLocation("block/template_glass_pane_" + suffix)));
	}

	@Override
	public void slabBlock(Block block, Block slab) {
		if (slab instanceof SlabBlock slabBlock && slabBlock == POLISHED_DOLERITE_SLAB.get()) {
			ResourceLocation side = blockTexture(slab);
			ResourceLocation full = blockTexture(block);
			this.slabBlock(slabBlock, models().slab(name(slab), side, full, full), models().slabTop(name(slab) + "_top", side, full, full), models().cubeColumn(name(slab) + "_double", side, full));
			this.blockItem(slab);
		} else {
			super.slabBlock(block, slab);
		}
	}

	public void flowerPotBlock(RegistryObject<Block> flowerPot, ResourceLocation potTexture) {
		this.simpleBlock(flowerPot.get(), models().singleTexture(name(flowerPot.get()), new ResourceLocation("block/flower_pot_cross"), "plant", potTexture));
	}

	public void flowerPotBlock(RegistryObject<Block> flowerPot) {
		this.flowerPotBlock(flowerPot, blockTexture(flowerPot.get()));
	}
}
