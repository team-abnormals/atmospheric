package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericBlockFamilies;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.api.conditions.QuarkFlagRecipeCondition;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class AtmosphericRecipeProvider extends RecipeProvider {
	public static final ModLoadedCondition NEAPOLITAN_LOADED = new ModLoadedCondition("neapolitan");
	public static final NotCondition NEAPOLITAN_NOT_LOADED = new NotCondition(NEAPOLITAN_LOADED);

	public static final QuarkFlagRecipeCondition VERTICAL_SLABS = quarkFlag("vertical_slabs");
	public static final QuarkFlagRecipeCondition APPLE_CRATE = quarkFlag("apple_crate");
	public static final QuarkFlagRecipeCondition GOLDEN_APPLE_CRATE = quarkFlag("golden_apple_crate");

	public AtmosphericRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		oneToOneConversionRecipe(consumer, Items.RED_DYE, AtmosphericItems.CARMINE_HUSK.get(), "red_dye");
		oneToOneConversionRecipe(consumer, Items.RED_DYE, AtmosphericBlocks.FIRETHORN.get(), "red_dye");
		oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, AtmosphericBlocks.FORSYTHIA.get(), "yellow_dye");
		oneToOneConversionRecipe(consumer, Items.PINK_DYE, AtmosphericItems.DRAGON_FRUIT.get(), "pink_dye");

		ShapelessRecipeBuilder.shapeless(AtmosphericItems.CURRANT_MUFFIN.get()).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(Items.SUGAR).requires(BlueprintItemTags.EGGS).unlockedBy("has_currant", has(AtmosphericItemTags.FRUITS_CURRANT)).save(consumer);

		nineBlockStorageRecipes(consumer, AtmosphericItems.CARMINE_HUSK.get(), AtmosphericBlocks.CARMINE_BLOCK.get());
		ShapedRecipeBuilder.shaped(AtmosphericBlocks.CARMINE_SHINGLES.get(), 4).define('#', AtmosphericItems.CARMINE_HUSK.get()).pattern("##").pattern("##").unlockedBy("has_carmine_husk", has(AtmosphericItems.CARMINE_HUSK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(AtmosphericBlocks.CARMINE_PAVEMENT.get(), 4).define('#', AtmosphericBlocks.CARMINE_SHINGLES.get()).pattern("##").pattern("##").unlockedBy("has_carmine_shingles", has(AtmosphericBlocks.CARMINE_SHINGLES.get())).save(consumer);

		generateRecipes(consumer, AtmosphericBlockFamilies.CARMINE_SHINGLES_FAMILY);
		verticalSlabRecipes(consumer, AtmosphericBlockFamilies.CARMINE_SHINGLES_FAMILY, AtmosphericBlocks.CARMINE_SHINGLE_VERTICAL_SLAB.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_SHINGLE_SLAB.get(), AtmosphericBlocks.CARMINE_SHINGLES.get(), 2);
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_SHINGLE_STAIRS.get(), AtmosphericBlocks.CARMINE_SHINGLES.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_SHINGLE_WALL.get(), AtmosphericBlocks.CARMINE_SHINGLES.get());
		conditionalStonecuttingRecipe(consumer, VERTICAL_SLABS, AtmosphericBlocks.CARMINE_SHINGLE_VERTICAL_SLAB.get(), AtmosphericBlocks.CARMINE_SHINGLES.get(), 2);
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CHISELED_CARMINE_SHINGLES.get(), AtmosphericBlocks.CARMINE_SHINGLES.get());

		generateRecipes(consumer, AtmosphericBlockFamilies.CARMINE_PAVEMENT_FAMILY);
		verticalSlabRecipes(consumer, AtmosphericBlockFamilies.CARMINE_PAVEMENT_FAMILY, AtmosphericBlocks.CARMINE_PAVEMENT_VERTICAL_SLAB.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_PAVEMENT_SLAB.get(), AtmosphericBlocks.CARMINE_PAVEMENT.get(), 2);
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_PAVEMENT_STAIRS.get(), AtmosphericBlocks.CARMINE_PAVEMENT.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_PAVEMENT_WALL.get(), AtmosphericBlocks.CARMINE_PAVEMENT.get());
		conditionalStonecuttingRecipe(consumer, VERTICAL_SLABS, AtmosphericBlocks.CARMINE_PAVEMENT_VERTICAL_SLAB.get(), AtmosphericBlocks.CARMINE_PAVEMENT.get(), 2);
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_PAVEMENT.get(), AtmosphericBlocks.CARMINE_SHINGLES.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_PAVEMENT_SLAB.get(), AtmosphericBlocks.CARMINE_SHINGLES.get(), 2);
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_PAVEMENT_STAIRS.get(), AtmosphericBlocks.CARMINE_SHINGLES.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.CARMINE_PAVEMENT_WALL.get(), AtmosphericBlocks.CARMINE_SHINGLES.get());
		conditionalStonecuttingRecipe(consumer, VERTICAL_SLABS, AtmosphericBlocks.CARMINE_PAVEMENT_VERTICAL_SLAB.get(), AtmosphericBlocks.CARMINE_SHINGLES.get(), 2);

		ShapelessRecipeBuilder.shapeless(AtmosphericItems.COCHINEAL_BANNER_PATTERN.get()).requires(Items.PAPER).requires(AtmosphericItems.CARMINE_HUSK.get()).unlockedBy("has_carmine_husk", has(AtmosphericItems.CARMINE_HUSK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(AtmosphericItems.GOLDEN_DRAGON_FRUIT.get()).define('#', Items.GOLD_INGOT).define('X', AtmosphericItems.DRAGON_FRUIT.get()).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(consumer);
		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, AtmosphericItems.DRAGON_FRUIT.get(), AtmosphericBlocks.DRAGON_FRUIT_CRATE.get());
		conditionalNineBlockStorageRecipes(consumer, GOLDEN_APPLE_CRATE, AtmosphericItems.GOLDEN_DRAGON_FRUIT.get(), AtmosphericBlocks.GOLDEN_DRAGON_FRUIT_CRATE.get());
		ShapelessRecipeBuilder.shapeless(AtmosphericItems.CANDIED_ORANGE_SLICES.get()).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Items.SUGAR).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)).save(consumer);
		ShapelessRecipeBuilder.shapeless(AtmosphericItems.ORANGE_PUDDING.get()).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Items.SWEET_BERRIES).requires(Items.COCOA_BEANS).requires(BlueprintItemTags.EGGS).requires(BlueprintItemTags.MILK).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)).save(consumer);
		conditionalRecipe(consumer, NEAPOLITAN_NOT_LOADED, ShapelessRecipeBuilder.shapeless(AtmosphericItems.ORANGE_SORBET.get()).requires(Items.BOWL).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Blocks.ICE).requires(Items.SUGAR).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)));
		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, AtmosphericItems.ORANGE.get(), AtmosphericBlocks.ORANGE_CRATE.get());
		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, AtmosphericItems.BLOOD_ORANGE.get(), AtmosphericBlocks.BLOOD_ORANGE_CRATE.get());

		ShapelessRecipeBuilder.shapeless(AtmosphericBlocks.DOLERITE.get(), 2).requires(AtmosphericItemTags.TRAVERTINE).requires(Blocks.COBBLESTONE).unlockedBy("has_travertine", has(AtmosphericItemTags.TRAVERTINE)).save(consumer);
		generateRecipes(consumer, AtmosphericBlockFamilies.DOLERITE_FAMILY);
		verticalSlabRecipes(consumer, AtmosphericBlockFamilies.DOLERITE_FAMILY, AtmosphericBlocks.DOLERITE_VERTICAL_SLAB.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.DOLERITE_SLAB.get(), AtmosphericBlocks.DOLERITE.get(), 2);
		stonecutterResultFromBase(consumer, AtmosphericBlocks.DOLERITE_STAIRS.get(), AtmosphericBlocks.DOLERITE.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.DOLERITE_WALL.get(), AtmosphericBlocks.DOLERITE.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.POLISHED_DOLERITE.get(), AtmosphericBlocks.DOLERITE.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.POLISHED_DOLERITE_SLAB.get(), AtmosphericBlocks.DOLERITE.get(), 2);
		stonecutterResultFromBase(consumer, AtmosphericBlocks.POLISHED_DOLERITE_STAIRS.get(), AtmosphericBlocks.DOLERITE.get());
		ShapedRecipeBuilder.shaped(AtmosphericBlocks.POLISHED_DOLERITE.get(), 4).define('#', AtmosphericBlocks.DOLERITE.get()).pattern("##").pattern("##").unlockedBy("has_dolerite", has(AtmosphericBlocks.DOLERITE.get())).save(consumer);
		generateRecipes(consumer, AtmosphericBlockFamilies.POLISHED_DOLERITE_FAMILY);
		verticalSlabRecipes(consumer, AtmosphericBlockFamilies.POLISHED_DOLERITE_FAMILY, AtmosphericBlocks.POLISHED_DOLERITE_VERTICAL_SLAB.get());
		stonecutterResultFromBase(consumer, AtmosphericBlocks.POLISHED_DOLERITE_SLAB.get(), AtmosphericBlocks.POLISHED_DOLERITE.get(), 2);
		stonecutterResultFromBase(consumer, AtmosphericBlocks.POLISHED_DOLERITE_STAIRS.get(), AtmosphericBlocks.POLISHED_DOLERITE.get());
		conditionalStonecuttingRecipe(consumer, VERTICAL_SLABS, AtmosphericBlocks.DOLERITE_VERTICAL_SLAB.get(), AtmosphericBlocks.DOLERITE.get(), 2);
		conditionalStonecuttingRecipe(consumer, VERTICAL_SLABS, AtmosphericBlocks.POLISHED_DOLERITE_VERTICAL_SLAB.get(), AtmosphericBlocks.DOLERITE.get(), 2);
		conditionalStonecuttingRecipe(consumer, VERTICAL_SLABS, AtmosphericBlocks.POLISHED_DOLERITE_VERTICAL_SLAB.get(), AtmosphericBlocks.POLISHED_DOLERITE.get(), 2);

		ShapelessRecipeBuilder.shapeless(AtmosphericBlocks.GRIMWEB.get()).requires(AtmosphericItemTags.GRIMWOOD_LOGS).requires(Items.COBWEB).unlockedBy("has_grimwood", has(AtmosphericItemTags.GRIMWOOD_LOGS)).save(consumer);
	}

	public static QuarkFlagRecipeCondition quarkFlag(String flag) {
		return new QuarkFlagRecipeCondition(new ResourceLocation(Blueprint.MOD_ID, "quark_flag"), flag);
	}

	public static void conditionalStonecuttingRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, ItemLike output, ItemLike input, int count) {
		conditionalRecipe(consumer, condition, SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), output, count).unlockedBy(getHasName(input), has(input)), Atmospheric.location(getConversionRecipeName(output, input) + "_stonecutting"));
	}

	public static void verticalSlabRecipes(Consumer<FinishedRecipe> consumer, BlockFamily family, ItemLike verticalSlab) {
		conditionalRecipe(consumer, VERTICAL_SLABS, ShapedRecipeBuilder.shaped(verticalSlab, 6).define('#', family.getBaseBlock()).pattern("#").pattern("#").pattern("#").unlockedBy(getHasName(family.getBaseBlock()), has(family.getBaseBlock())));
		conditionalRecipe(consumer, VERTICAL_SLABS, ShapelessRecipeBuilder.shapeless(verticalSlab).requires(family.get(Variant.SLAB)).unlockedBy(getHasName(verticalSlab), has(verticalSlab)), new ResourceLocation(RecipeBuilder.getDefaultRecipeId(verticalSlab) + "_revert"));
	}

	public static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input, @Nullable String group) {
		oneToOneConversionRecipe(consumer, output, input, group, 1);
	}

	public static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input, @Nullable String group, int count) {
		ShapelessRecipeBuilder.shapeless(output, count).requires(input).group(group).unlockedBy(getHasName(input), has(input)).save(consumer, getModConversionRecipeName(output, input));
	}

	private static ResourceLocation getModConversionRecipeName(ItemLike result, ItemLike input) {
		return Atmospheric.location(getConversionRecipeName(result, input));
	}

	public static void conditionalNineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ICondition condition, ItemLike item, ItemLike storage) {
		conditionalNineBlockStorageRecipes(consumer, condition, item, storage, Atmospheric.MOD_ID + ":" + getSimpleRecipeName(storage), null, Atmospheric.MOD_ID + ":" + getSimpleRecipeName(item), null);
	}

	protected static void conditionalNineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ICondition condition, ItemLike item, ItemLike storage, String storageLocation, @Nullable String itemGroup, String itemLocation, @Nullable String storageGroup) {
		conditionalRecipe(consumer, condition, ShapelessRecipeBuilder.shapeless(item, 9).requires(storage).group(storageGroup).unlockedBy(getHasName(storage), has(storage)), getModConversionRecipeName(item, storage));
		conditionalRecipe(consumer, condition, ShapedRecipeBuilder.shaped(storage).define('#', item).pattern("###").pattern("###").pattern("###").group(itemGroup).unlockedBy(getHasName(item), has(item)), new ResourceLocation(storageLocation));
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeBuilder recipe) {
		conditionalRecipe(consumer, condition, recipe, RecipeBuilder.getDefaultRecipeId(recipe.getResult()));
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeBuilder recipe, ResourceLocation id) {
		ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public static void stonecutterResultFromBase(Consumer<FinishedRecipe> p_176736_, ItemLike p_176737_, ItemLike p_176738_) {
		stonecutterResultFromBase(p_176736_, p_176737_, p_176738_, 1);
	}

	public static void stonecutterResultFromBase(Consumer<FinishedRecipe> p_176547_, ItemLike p_176548_, ItemLike p_176549_, int p_176550_) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(p_176549_), p_176548_, p_176550_).unlockedBy(getHasName(p_176549_), has(p_176549_)).save(p_176547_, getModConversionRecipeName(p_176548_, p_176549_) + "_stonecutting");
	}
}