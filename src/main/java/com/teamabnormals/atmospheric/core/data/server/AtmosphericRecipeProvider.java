package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericBlockFamilies;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.api.conditions.QuarkFlagRecipeCondition;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.function.Consumer;

public class AtmosphericRecipeProvider extends RecipeProvider {
	public static final QuarkFlagRecipeCondition VERTICAL_SLABS = quarkFlag("vertical_slabs");

	public AtmosphericRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
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

	private static ResourceLocation getModConversionRecipeName(ItemLike result, ItemLike input) {
		return Atmospheric.location(getConversionRecipeName(result, input));
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