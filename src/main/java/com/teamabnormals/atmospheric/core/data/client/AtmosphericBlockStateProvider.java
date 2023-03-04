package com.teamabnormals.atmospheric.core.data.client;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericBlockFamilies;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock.VerticalSlabType;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.Blueprint;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericBlockStateProvider extends BlockStateProvider {

	public AtmosphericBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Atmospheric.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.blockFamily(AtmosphericBlockFamilies.ROSEWOOD_PLANKS_FAMILY, ROSEWOOD_VERTICAL_SLAB.get());
		this.logBlocks(ROSEWOOD_LOG.get(), ROSEWOOD.get());
		this.logBlocks(STRIPPED_ROSEWOOD_LOG.get(), STRIPPED_ROSEWOOD.get());
		this.leavesBlock(ROSEWOOD_LEAVES.get());
		this.crossBlockWithPot(ROSEWOOD_SAPLING.get(), POTTED_ROSEWOOD_SAPLING.get());
		this.planksCompat(ROSEWOOD_PLANKS.get(), ROSEWOOD_BOARDS.get(), ROSEWOOD_LADDER.get(), ROSEWOOD_BOOKSHELF.get(), ROSEWOOD_BEEHIVE.get(), ROSEWOOD_CHESTS, VERTICAL_ROSEWOOD_PLANKS.get());
		this.logCompat(ROSEWOOD_LOG.get(), STRIPPED_ROSEWOOD_LOG.get(), ROSEWOOD_POST.get(), STRIPPED_ROSEWOOD_POST.get());
		this.leavesCompat(ROSEWOOD_LEAVES.get(), ROSEWOOD_LOG.get(), ROSEWOOD_LEAF_PILE.get(), ROSEWOOD_HEDGE.get(), ROSEWOOD_LEAF_CARPET.get());

		this.blockFamily(AtmosphericBlockFamilies.MORADO_PLANKS_FAMILY, MORADO_VERTICAL_SLAB.get());
		this.logBlocks(MORADO_LOG.get(), MORADO_WOOD.get());
		this.logBlocks(STRIPPED_MORADO_LOG.get(), STRIPPED_MORADO_WOOD.get());
		this.leavesBlock(MORADO_LEAVES.get());
		this.crossBlockWithPot(MORADO_SAPLING.get(), POTTED_MORADO_SAPLING.get());
		this.planksCompat(MORADO_PLANKS.get(), MORADO_BOARDS.get(), MORADO_LADDER.get(), MORADO_BOOKSHELF.get(), MORADO_BEEHIVE.get(), MORADO_CHESTS, VERTICAL_MORADO_PLANKS.get());
		this.logCompat(MORADO_LOG.get(), STRIPPED_MORADO_LOG.get(), MORADO_POST.get(), STRIPPED_MORADO_POST.get());
		this.leavesCompat(MORADO_LEAVES.get(), MORADO_LOG.get(), MORADO_LEAF_PILE.get(), MORADO_HEDGE.get(), MORADO_LEAF_CARPET.get());

		this.blockFamily(AtmosphericBlockFamilies.YUCCA_PLANKS_FAMILY, YUCCA_VERTICAL_SLAB.get());
		this.logBlocks(YUCCA_LOG.get(), YUCCA_WOOD.get());
		this.logBlocks(STRIPPED_YUCCA_LOG.get(), STRIPPED_YUCCA_WOOD.get());
		this.leavesBlock(YUCCA_LEAVES.get());
		this.crossBlockWithPot(YUCCA_SAPLING.get(), POTTED_YUCCA_SAPLING.get());
		this.planksCompat(YUCCA_PLANKS.get(), YUCCA_BOARDS.get(), YUCCA_LADDER.get(), YUCCA_BOOKSHELF.get(), YUCCA_BEEHIVE.get(), YUCCA_CHESTS, VERTICAL_YUCCA_PLANKS.get());
		this.logCompat(YUCCA_LOG.get(), STRIPPED_YUCCA_LOG.get(), YUCCA_POST.get(), STRIPPED_YUCCA_POST.get());
		this.leavesCompat(YUCCA_LEAVES.get(), YUCCA_LOG.get(), YUCCA_LEAF_PILE.get(), YUCCA_HEDGE.get(), YUCCA_LEAF_CARPET.get());

//		this.blockFamily(AtmosphericBlockFamilies.ARID_SANDSTONE_FAMILY, ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.CUT_ARID_SANDSTONE_FAMILY, CUT_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.SMOOTH_ARID_SANDSTONE_FAMILY, SMOOTH_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.ARID_SANDSTONE_BRICKS_FAMILY, ARID_SANDSTONE_BRICK_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.RED_ARID_SANDSTONE_FAMILY, RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.CUT_RED_ARID_SANDSTONE_FAMILY, CUT_RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.SMOOTH_RED_ARID_SANDSTONE_FAMILY, SMOOTH_RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.RED_ARID_SANDSTONE_BRICKS_FAMILY, RED_ARID_SANDSTONE_BRICK_VERTICAL_SLAB.get());

		this.blockFamily(AtmosphericBlockFamilies.ASPEN_PLANKS_FAMILY, ASPEN_VERTICAL_SLAB.get());
		this.logBlocks(ASPEN_LOG.get(), ASPEN_WOOD.get());
		this.logBlocks(STRIPPED_ASPEN_LOG.get(), STRIPPED_ASPEN_WOOD.get());
		this.logBlocks(WATCHFUL_ASPEN_LOG.get(), WATCHFUL_ASPEN_WOOD.get());
		this.leavesBlock(ASPEN_LEAVES.get());
		this.crossBlockWithPot(ASPEN_SAPLING.get(), POTTED_ASPEN_SAPLING.get());
		this.planksCompat(ASPEN_PLANKS.get(), ASPEN_BOARDS.get(), ASPEN_LADDER.get(), ASPEN_BOOKSHELF.get(), ASPEN_BEEHIVE.get(), ASPEN_CHESTS, VERTICAL_ASPEN_PLANKS.get());
		this.logCompat(ASPEN_LOG.get(), STRIPPED_ASPEN_LOG.get(), ASPEN_POST.get(), STRIPPED_ASPEN_POST.get());
		this.leavesCompat(ASPEN_LEAVES.get(), ASPEN_LOG.get(), ASPEN_LEAF_PILE.get(), ASPEN_HEDGE.get(), ASPEN_LEAF_CARPET.get());

		this.crossBlockWithCustomPot(AGAVE.get(), POTTED_AGAVE.get());
		this.crossBlock(CRUSTOSE_SPROUTS.get());

		this.blockFamily(AtmosphericBlockFamilies.LAUREL_PLANKS_FAMILY, LAUREL_VERTICAL_SLAB.get());
		this.logBlocks(LAUREL_LOG.get(), LAUREL_WOOD.get());
		this.logBlocks(STRIPPED_LAUREL_LOG.get(), STRIPPED_LAUREL_WOOD.get());
		this.leavesBlock(LAUREL_LEAVES.get());
		this.crossBlockWithPot(LAUREL_SAPLING.get(), POTTED_LAUREL_SAPLING.get());
		this.planksCompat(LAUREL_PLANKS.get(), LAUREL_BOARDS.get(), LAUREL_LADDER.get(), LAUREL_BOOKSHELF.get(), LAUREL_BEEHIVE.get(), LAUREL_CHESTS, VERTICAL_LAUREL_PLANKS.get());
		this.logCompat(LAUREL_LOG.get(), STRIPPED_LAUREL_LOG.get(), LAUREL_POST.get(), STRIPPED_LAUREL_POST.get());
		this.leavesCompat(LAUREL_LEAVES.get(), LAUREL_LOG.get(), LAUREL_LEAF_PILE.get(), LAUREL_HEDGE.get(), LAUREL_LEAF_CARPET.get());
		this.leavesBlock(DRY_LAUREL_LEAVES.get());
		this.crossBlockWithPot(DRY_LAUREL_SAPLING.get(), POTTED_DRY_LAUREL_SAPLING.get());
		this.leavesCompat(DRY_LAUREL_LEAVES.get(), LAUREL_LOG.get(), DRY_LAUREL_LEAF_PILE.get(), DRY_LAUREL_HEDGE.get(), DRY_LAUREL_LEAF_CARPET.get());

		this.blockFamily(AtmosphericBlockFamilies.KOUSA_PLANKS_FAMILY, KOUSA_VERTICAL_SLAB.get());
		this.logBlocks(KOUSA_LOG.get(), KOUSA_WOOD.get());
		this.logBlocks(STRIPPED_KOUSA_LOG.get(), STRIPPED_KOUSA_WOOD.get());
		this.leavesBlock(KOUSA_LEAVES.get());
		this.crossBlockWithPot(KOUSA_SAPLING.get(), POTTED_KOUSA_SAPLING.get());
		this.planksCompat(KOUSA_PLANKS.get(), KOUSA_BOARDS.get(), KOUSA_LADDER.get(), KOUSA_BOOKSHELF.get(), KOUSA_BEEHIVE.get(), KOUSA_CHESTS, VERTICAL_KOUSA_PLANKS.get());
		this.logCompat(KOUSA_LOG.get(), STRIPPED_KOUSA_LOG.get(), KOUSA_POST.get(), STRIPPED_KOUSA_POST.get());
		this.leavesCompat(KOUSA_LEAVES.get(), KOUSA_LOG.get(), KOUSA_LEAF_PILE.get(), KOUSA_HEDGE.get(), KOUSA_LEAF_CARPET.get());

		this.crossBlock(HANGING_CURRANT.get());
		this.logBlock(CURRANT_STALK_BUNDLE.get());
		this.leavesBlock(CURRANT_LEAVES.get());
		this.crossBlockWithPot(CURRANT_SEEDLING.get(), POTTED_CURRANT_SEEDLING.get());
		this.leavesCompat(CURRANT_LEAVES.get(), CURRANT_STALK.get(), CURRANT_LEAF_PILE.get(), CURRANT_HEDGE.get(), CURRANT_LEAF_CARPET.get());

		this.blockFamily(AtmosphericBlockFamilies.GRIMWOOD_PLANKS_FAMILY, GRIMWOOD_VERTICAL_SLAB.get());
		this.logBlocks(GRIMWOOD_LOG.get(), GRIMWOOD.get());
		this.logBlocks(STRIPPED_GRIMWOOD_LOG.get(), STRIPPED_GRIMWOOD.get());
		this.leavesBlock(GRIMWOOD_LEAVES.get());
		this.crossBlockWithPot(GRIMWOOD_SAPLING.get(), POTTED_GRIMWOOD_SAPLING.get());
		this.planksCompat(GRIMWOOD_PLANKS.get(), GRIMWOOD_BOARDS.get(), GRIMWOOD_LADDER.get(), GRIMWOOD_BOOKSHELF.get(), GRIMWOOD_BEEHIVE.get(), GRIMWOOD_CHESTS, VERTICAL_GRIMWOOD_PLANKS.get());
		this.logCompat(GRIMWOOD_LOG.get(), STRIPPED_GRIMWOOD_LOG.get(), GRIMWOOD_POST.get(), STRIPPED_GRIMWOOD_POST.get());
		this.leavesCompat(GRIMWOOD_LEAVES.get(), GRIMWOOD_LOG.get(), GRIMWOOD_LEAF_PILE.get(), GRIMWOOD_HEDGE.get(), GRIMWOOD_LEAF_CARPET.get());
	}

	public void block(Block block) {
		simpleBlock(block, cubeAll(block));
		blockItem(block);
	}

	public void blockFamily(BlockFamily family, Block verticalSlab) {
		Block block = family.getBaseBlock();
		this.block(block);

		if (family.getVariants().containsKey(Variant.CHISELED)) {
			this.block(family.get(Variant.CHISELED));
		}

		if (family.getVariants().containsKey(Variant.SLAB)) {
			SlabBlock slab = (SlabBlock) family.get(Variant.SLAB);
			this.slabBlock(slab, blockTexture(block), blockTexture(block));
			this.blockItem(slab);

			this.verticalSlabBlock(block, (VerticalSlabBlock) verticalSlab);
			this.blockItem(verticalSlab);
		}

		if (family.getVariants().containsKey(Variant.STAIRS)) {
			StairBlock stairs = (StairBlock) family.get(Variant.STAIRS);
			this.stairsBlock(stairs, blockTexture(block));
			this.blockItem(stairs);
		}

		if (family.getVariants().containsKey(Variant.WALL)) {
			WallBlock wall = (WallBlock) family.get(Variant.WALL);
			this.wallBlock(wall, blockTexture(block));
			this.itemModels().getBuilder(name(wall)).parent(this.models().wallInventory(name(wall) + "_inventory", blockTexture(block)));
		}

		if (family.getVariants().containsKey(Variant.FENCE)) {
			FenceBlock fence = (FenceBlock) family.get(Variant.FENCE);
			this.fenceBlock(fence, blockTexture(block));
			this.itemModels().getBuilder(name(fence)).parent(this.models().fenceInventory(name(fence) + "_inventory", blockTexture(block)));
		}

		if (family.getVariants().containsKey(Variant.FENCE_GATE)) {
			FenceGateBlock fenceGate = (FenceGateBlock) family.get(Variant.FENCE_GATE);
			this.fenceGateBlock(fenceGate, blockTexture(block));
			this.blockItem(fenceGate);
		}

		if (family.getVariants().containsKey(Variant.BUTTON)) {
			ButtonBlock button = (ButtonBlock) family.get(Variant.BUTTON);
			ModelFile buttonModel = models().withExistingParent(name(button), "block/button").texture("texture", blockTexture(block));
			ModelFile buttonPressedModel = models().withExistingParent(name(button) + "_pressed", "block/button_pressed").texture("texture", blockTexture(block));
			ModelFile buttonInventoryModel = models().withExistingParent(name(button) + "_inventory", "block/button_inventory").texture("texture", blockTexture(block));
			this.buttonBlock(button, (state -> state.getValue(BlockStateProperties.POWERED) ? buttonPressedModel : buttonModel));
			this.itemModels().getBuilder(name(button)).parent(buttonInventoryModel);
		}

		if (family.getVariants().containsKey(Variant.PRESSURE_PLATE)) {
			BasePressurePlateBlock pressurePlate = (BasePressurePlateBlock) family.get(Variant.PRESSURE_PLATE);
			ModelFile pressurePlateModel = models().withExistingParent(name(pressurePlate), "block/pressure_plate_up").texture("texture", blockTexture(block));
			ModelFile pressurePlateDownModel = models().withExistingParent(name(pressurePlate) + "_down", "block/pressure_plate_down").texture("texture", blockTexture(block));
			this.pressurePlateBlock(pressurePlate, (state -> state.getValue(BlockStateProperties.POWERED) ? pressurePlateDownModel : pressurePlateModel));
			this.blockItem(pressurePlate);
		}

		if (family.getVariants().containsKey(Variant.DOOR)) {
			DoorBlock door = (DoorBlock) family.get(Variant.DOOR);
			this.doorBlock(door, suffix(blockTexture(door), "_bottom"), suffix(blockTexture(door), "_top"));
			this.generatedItem(door, "item");
		}

		if (family.getVariants().containsKey(Variant.TRAPDOOR)) {
			TrapDoorBlock trapdoor = (TrapDoorBlock) family.get(Variant.TRAPDOOR);
			this.trapdoorBlock(trapdoor, blockTexture(trapdoor), true);
			this.itemModels().getBuilder(name(trapdoor)).parent(this.models().trapdoorOrientableBottom(name(trapdoor) + "_bottom", blockTexture(trapdoor)));
		}

		if (family.getVariants().containsKey(Variant.SIGN)) {
			SignBlock sign = (SignBlock) family.get(Variant.SIGN);
			this.simpleBlock(sign, particle(sign, blockTexture(block)));
			this.generatedItem(sign, "item");
		}

		if (family.getVariants().containsKey(Variant.WALL_SIGN)) {
			WallSignBlock wallSign = (WallSignBlock) family.get(Variant.WALL_SIGN);
			this.simpleBlock(wallSign, particle(wallSign, blockTexture(block)));
		}
	}

	public void blockItem(Block block) {
		this.simpleBlockItem(block, new ExistingModelFile(blockTexture(block), this.models().existingFileHelper));
	}

	private void generatedItem(ItemLike item, String type) {
		generatedItem(item, item, type);
	}

	private void generatedItem(ItemLike item, ItemLike texture, String type) {
		itemModels().withExistingParent(ForgeRegistries.ITEMS.getKey(item.asItem()).getPath(), "item/generated").texture("layer0", new ResourceLocation(ForgeRegistries.ITEMS.getKey(texture.asItem()).getNamespace(), type + "/" + ForgeRegistries.ITEMS.getKey(texture.asItem()).getPath()));
	}

	public void crossBlockWithPot(Block cross, Block flowerPot) {
		this.crossBlock(cross);
		this.simpleBlock(flowerPot, models().singleTexture(name(flowerPot), new ResourceLocation("block/flower_pot_cross"), "plant", blockTexture(cross)));
	}

	public void crossBlockWithCustomPot(Block cross, Block flowerPot) {
		this.crossBlock(cross);
		this.simpleBlock(flowerPot, models().singleTexture(name(flowerPot), new ResourceLocation("block/flower_pot_cross"), "plant", blockTexture(flowerPot)));
	}

	public void crossBlock(Block cross) {
		this.simpleBlock(cross, models().cross(name(cross), blockTexture(cross)));
		this.generatedItem(cross, "block");
	}

	public void bookshelfBlock(Block planks, Block bookshelf) {
		this.simpleBlock(bookshelf, this.models().cubeColumn(name(bookshelf), blockTexture(bookshelf), blockTexture(planks)));
		this.blockItem(bookshelf);
	}

	public void ladderBlock(Block ladder) {
		this.horizontalBlock(ladder, models().withExistingParent(name(ladder), "block/ladder").texture("particle", blockTexture(ladder)).texture("texture", blockTexture(ladder)));
		this.generatedItem(ladder, "block");
	}

	public void boardsBlock(Block boards) {
		ModelFile boardsModel = models().getBuilder(name(boards)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards"))).texture("all", blockTexture(boards));
		ModelFile boardsHorizontalModel = models().getBuilder(name(boards) + "_horizontal").parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards_horizontal"))).texture("all", blockTexture(boards));
		this.getVariantBuilder(boards).partialState().with(RotatedPillarBlock.AXIS, Axis.Y).modelForState().modelFile(boardsModel).addModel().partialState().with(RotatedPillarBlock.AXIS, Axis.Z).modelForState().modelFile(boardsHorizontalModel).addModel().partialState().with(RotatedPillarBlock.AXIS, Axis.X).modelForState().modelFile(boardsHorizontalModel).rotationY(270).addModel();
		this.blockItem(boards);
	}

	public void verticalPlanksBlock(Block planks, Block verticalPlanks) {
		this.simpleBlock(verticalPlanks, models().getBuilder(name(verticalPlanks)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/vertical_planks"))).texture("all", blockTexture(planks)));
		this.blockItem(verticalPlanks);
	}

	public void beehiveBlock(Block block) {
		ModelFile beehive = models().orientableWithBottom(name(block), suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_front"), suffix(blockTexture(block), "_end"), suffix(blockTexture(block), "_end")).texture("particle", suffix(blockTexture(block), "_side"));
		ModelFile beehiveHoney = models().orientableWithBottom(name(block) + "_honey", suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_front_honey"), suffix(blockTexture(block), "_end"), suffix(blockTexture(block), "_end")).texture("particle", suffix(blockTexture(block), "_side"));
		this.horizontalBlock(block, (state -> state.getValue(BlockStateProperties.LEVEL_HONEY) == 5 ? beehiveHoney : beehive));
		this.blockItem(block);
	}

	public void buttonBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
		this.getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).uvLock(state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.WALL).rotationX(state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.WALL ? 90 : state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.CEILING ? 180 : 0).rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + (state.getValue(BlockStateProperties.ATTACH_FACE) != AttachFace.CEILING ? 180 : 0)) % 360).build());
	}

	public void pressurePlateBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
		this.getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).build());
	}

	public void planksCompat(Block planks, Block boards, Block ladder, Block bookshelf, Block beehive, Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> chests, Block verticalPlanks) {
		this.boardsBlock(boards);
		this.ladderBlock(ladder);
		this.bookshelfBlock(planks, bookshelf);
		this.beehiveBlock(beehive);
		this.chestBlocks(planks, chests);
		this.verticalPlanksBlock(planks, verticalPlanks);
	}

	public void logCompat(Block log, Block strippedLog, Block post, Block strippedPost) {
		this.postBlock(log, post);
		this.postBlock(strippedLog, strippedPost);
	}

	public void leavesCompat(Block leaves, Block log, Block leafPile, Block hedge, Block leafCarpet) {
		this.leafPileBlock(leaves, leafPile);
		this.leafCarpetBlock(leaves, leafCarpet);
		this.hedgeBlock(leaves, log, hedge);
	}

	public void leavesBlock(Block leaves) {
		this.simpleBlock(leaves, models().getBuilder(name(leaves)).parent(new UncheckedModelFile(new ResourceLocation("block/leaves"))).texture("all", blockTexture(leaves)));
		this.blockItem(leaves);
	}

	public void leafPileBlock(Block leaves, Block leafPile) {
		this.leafPileBlock(leaves, leafPile, true);
	}

	public void leafPileBlock(Block leaves, Block leafPile, boolean tint) {
		ModelFile leafPileModel = models().getBuilder(name(leafPile)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/" + (tint ? "tinted_" : "") + "leaf_pile"))).texture("all", blockTexture(leaves));
		MultiPartBlockStateBuilder builder = getMultipartBuilder(leafPile);
		builder.part().modelFile(leafPileModel).rotationX(270).uvLock(true).addModel().condition(BlockStateProperties.UP, true);
		builder.part().modelFile(leafPileModel).rotationX(270).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).addModel().condition(BlockStateProperties.NORTH, true);
		builder.part().modelFile(leafPileModel).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.WEST, true);
		builder.part().modelFile(leafPileModel).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.SOUTH, true);
		builder.part().modelFile(leafPileModel).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.EAST, true);
		builder.part().modelFile(leafPileModel).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationX(90).uvLock(true).addModel().condition(BlockStateProperties.DOWN, true);
		builder.part().modelFile(leafPileModel).rotationX(90).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		this.generatedItem(leafPile, leaves, "block");
	}

	public void leafCarpetBlock(Block leaves, Block leafCarpet) {
		this.simpleBlock(leafCarpet, models().getBuilder(name(leafCarpet)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/leaf_carpet"))).texture("all", blockTexture(leaves)));
		this.blockItem(leafCarpet);
	}

	public void hedgeBlock(Block leaves, Block log, Block hedge) {
		ModelFile hedgePost = this.hedgePost(name(hedge) + "_post", blockTexture(log), blockTexture(leaves));
		ModelFile hedgeSide = this.hedgeSide(name(hedge) + "_side", blockTexture(leaves));
		ModelFile hedgeExtend = this.hedgeExtend(name(hedge) + "_extend", blockTexture(leaves));
		this.hedgeBlock(hedge, hedgePost, hedgeSide, hedgeExtend);
		this.itemModels().getBuilder(name(hedge)).parent(hedgePost);
	}

	public static final BooleanProperty[] CHAINED = new BooleanProperty[]{BooleanProperty.create("chain_down"), BooleanProperty.create("chain_up"), BooleanProperty.create("chain_north"), BooleanProperty.create("chain_south"), BooleanProperty.create("chain_west"), BooleanProperty.create("chain_east")};

	public void postBlock(Block log, Block post) {
		ModelFile model = models().getBuilder(name(post)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/post"))).texture("texture", blockTexture(log));
		ModelFile chainSmall = new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/chain_small"));
		ModelFile chainSmallTop = new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/chain_small_top"));
		this.getMultipartBuilder(post).part().modelFile(model).addModel().condition(RotatedPillarBlock.AXIS, Axis.Y).end().part().modelFile(model).rotationX(90).addModel().condition(RotatedPillarBlock.AXIS, Axis.Z).end().part().modelFile(model).rotationX(90).rotationY(90).addModel().condition(RotatedPillarBlock.AXIS, Axis.X).end().part().modelFile(chainSmall).addModel().condition(CHAINED[0], true).end().part().modelFile(chainSmallTop).addModel().condition(CHAINED[1], true).end().part().modelFile(chainSmallTop).rotationX(90).addModel().condition(CHAINED[2], true).end().part().modelFile(chainSmall).rotationX(90).addModel().condition(CHAINED[3], true).end().part().modelFile(chainSmall).rotationX(90).rotationY(90).addModel().condition(CHAINED[4], true).end().part().modelFile(chainSmallTop).rotationX(90).rotationY(90).addModel().condition(CHAINED[5], true).end();
		this.blockItem(post);
	}

	public void hedgeBlock(Block block, ModelFile post, ModelFile side, ModelFile extend) {
		MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
		builder.part().modelFile(post).addModel().condition(BooleanProperty.create("extend"), false).end().part().modelFile(extend).addModel().condition(BooleanProperty.create("extend"), true);
		PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
			Direction dir = e.getKey();
			if (dir.getAxis().isHorizontal()) {
				builder.part().modelFile(side).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true).addModel().condition(e.getValue(), true);
			}
		});
	}

	public ModelFile hedgePost(String name, ResourceLocation log, ResourceLocation leaf) {
		return models().getBuilder(name).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/hedge_post"))).texture("log", log).texture("leaf", leaf);
	}

	public ModelFile hedgeSide(String name, ResourceLocation leaf) {
		return models().getBuilder(name).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/hedge_side"))).texture("leaf", leaf);
	}

	public ModelFile hedgeExtend(String name, ResourceLocation leaf) {
		return models().getBuilder(name).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/hedge_extend"))).texture("leaf", leaf);
	}

	public void signBlocks(Block planks, Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> pair) {
		this.simpleBlock(pair.getFirst().get(), particle(pair.getFirst().get(), blockTexture(planks)));
		this.simpleBlock(pair.getSecond().get(), particle(pair.getFirst().get(), blockTexture(planks)));
		this.generatedItem(pair.getFirst().get(), "item");
	}

	public void chestBlocks(Block planks, Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> chests) {
		this.simpleBlock(chests.getFirst().get(), particle(chests.getFirst().get(), blockTexture(planks)));
		this.simpleBlock(chests.getSecond().get(), particle(chests.getFirst().get(), blockTexture(planks)));
		this.simpleBlockItem(chests.getFirst().get(), new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "item/template_chest")));
		this.simpleBlockItem(chests.getSecond().get(), new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "item/template_chest")));
	}

	public ModelFile particle(Block block, ResourceLocation texture) {
		return this.models().getBuilder(name(block)).texture("particle", texture);
	}

	public void logBlocks(Block log, Block wood) {
		this.logBlock(log);

		this.axisBlock((RotatedPillarBlock) wood, blockTexture(log), blockTexture(log));
		this.blockItem(wood);
	}

	public void logBlock(Block block) {
		this.axisBlock((RotatedPillarBlock) block, blockTexture(block), suffix(remove(blockTexture(block), "watchful_"), "_top"));
		this.blockItem(block);
	}

	public ModelFile verticalSlab(String name, ResourceLocation texture) {
		return models().getBuilder(name).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/vertical_slab"))).texture("side", texture).texture("bottom", texture).texture("top", texture);
	}

	public void verticalSlabBlock(Block planks, VerticalSlabBlock slab) {
		ModelFile verticalSlab = this.verticalSlab(name(slab), blockTexture(planks));
		this.getVariantBuilder(slab).partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.NORTH).addModels(new ConfiguredModel(verticalSlab, 0, 0, true)).partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.SOUTH).addModels(new ConfiguredModel(verticalSlab, 0, 180, true)).partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.EAST).addModels(new ConfiguredModel(verticalSlab, 0, 90, true)).partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.WEST).addModels(new ConfiguredModel(verticalSlab, 0, 270, true)).partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.DOUBLE).addModels(new ConfiguredModel(models().cubeAll(name(planks), blockTexture(planks))));
	}

	private String name(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	private ResourceLocation prefix(String prefix, ResourceLocation rl) {
		return new ResourceLocation(rl.getNamespace(), prefix + rl.getPath());
	}

	private ResourceLocation suffix(ResourceLocation rl, String suffix) {
		return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
	}

	private ResourceLocation remove(ResourceLocation rl, String remove) {
		return new ResourceLocation(rl.getNamespace(), rl.getPath().replace(remove, ""));
	}
}
