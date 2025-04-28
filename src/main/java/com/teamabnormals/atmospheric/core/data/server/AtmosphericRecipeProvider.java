package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericBlockFamilies;
import com.teamabnormals.atmospheric.core.other.AtmosphericConditions;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.integration.boatload.AtmosphericBoatTypes;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.clayworks.core.data.server.ClayworksRecipeProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;
import static net.minecraft.data.recipes.RecipeCategory.*;

public class AtmosphericRecipeProvider extends BlueprintRecipeProvider {

	public AtmosphericRecipeProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(Atmospheric.MOD_ID, output, provider);
	}

	@Override
	public void buildRecipes(RecipeOutput output) {
		conversionRecipe(output, Items.RED_DYE, AtmosphericItems.CARMINE_HUSK, "red_dye");
		conversionRecipe(output, Items.RED_DYE, FIRETHORN, "red_dye");
		conversionRecipe(output, Items.RED_DYE, SCALDING_MONKEY_BRUSH, "red_dye");
		conversionRecipe(output, Items.ORANGE_DYE, HOT_MONKEY_BRUSH, "orange_dye");
		conversionRecipe(output, Items.ORANGE_DYE, BARREL_CACTUS, "orange_dye");
		conversionRecipe(output, Items.YELLOW_DYE, AtmosphericItems.YELLOW_BLOSSOMS, "yellow_dye");
		conversionRecipe(output, Items.YELLOW_DYE, WARM_MONKEY_BRUSH, "yellow_dye");
		conversionRecipe(output, Items.YELLOW_DYE, FORSYTHIA, "yellow_dye");
		conversionRecipe(output, Items.PINK_DYE, AtmosphericItems.DRAGON_FRUIT, "pink_dye");
		conversionRecipe(output, Items.MAGENTA_DYE, GILIA, "magenta_dye");
		conversionRecipe(output, Items.PURPLE_DYE, AtmosphericItems.CURRANT, "purple_dye");
		conversionRecipe(output, Items.PURPLE_DYE, WATER_HYACINTH, "purple_dye");
		conversionRecipe(output, Items.LIGHT_GRAY_DYE, YUCCA_FLOWER, "light_gray_dye");
		conversionRecipe(output, Items.LIGHT_GRAY_DYE, TALL_YUCCA_FLOWER, "light_gray_dye", 2);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(AGAVE), MISC, Items.CYAN_DYE, 1.0F, 200).unlockedBy("has_agave", has(AGAVE)).save(output, this.getModConversionRecipeName(Items.CYAN_DYE, AGAVE));
		conditionalStorageRecipes(output, AtmosphericConditions.QUARK_LOADED, MISC, BARREL_CACTUS, BUILDING_BLOCKS, BARREL_CACTUS_BATCH);

		trimRecipes(output, AtmosphericItems.APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE, STRIPPED_KOUSA_LOG);
		trimRecipes(output, AtmosphericItems.DRUID_ARMOR_TRIM_SMITHING_TEMPLATE, RED_ARID_SANDSTONE);
		trimRecipes(output, AtmosphericItems.PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE, ARID_SANDSTONE);

		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.CURRANT_MUFFIN).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(Items.SUGAR).requires(Tags.Items.EGGS).unlockedBy("has_currant", has(AtmosphericItemTags.FRUITS_CURRANT)).save(output);
		conditionalStorageRecipes(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.CURRANT, BUILDING_BLOCKS, CURRANT_CRATE);

		storageRecipes(output, MISC, AtmosphericItems.CARMINE_HUSK, BUILDING_BLOCKS, CARMINE_BLOCK);
		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, CARMINE_SHINGLES, 4).define('#', AtmosphericItems.CARMINE_HUSK).pattern("##").pattern("##").unlockedBy("has_carmine_husk", has(AtmosphericItems.CARMINE_HUSK)).save(output);
		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, CARMINE_PAVEMENT, 4).define('#', CARMINE_SHINGLES).pattern("##").pattern("##").unlockedBy("has_carmine_shingles", has(CARMINE_SHINGLES)).save(output);

		generateRecipes(output, AtmosphericBlockFamilies.CARMINE_SHINGLES_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, CARMINE_SHINGLE_SLAB, CARMINE_SHINGLES, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, CARMINE_SHINGLE_STAIRS, CARMINE_SHINGLES);
		stonecutterRecipe(output, DECORATIONS, CARMINE_SHINGLE_WALL, CARMINE_SHINGLES);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_CARMINE_SHINGLES, CARMINE_SHINGLES);

		generateRecipes(output, AtmosphericBlockFamilies.CARMINE_PAVEMENT_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, CARMINE_PAVEMENT_SLAB, CARMINE_PAVEMENT, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, CARMINE_PAVEMENT_STAIRS, CARMINE_PAVEMENT);
		stonecutterRecipe(output, DECORATIONS, CARMINE_PAVEMENT_WALL, CARMINE_PAVEMENT);
		stonecutterRecipe(output, BUILDING_BLOCKS, CARMINE_PAVEMENT, CARMINE_SHINGLES);
		stonecutterRecipe(output, BUILDING_BLOCKS, CARMINE_PAVEMENT_SLAB, CARMINE_SHINGLES, 2);
		stonecutterRecipe(output, DECORATIONS, CARMINE_PAVEMENT_STAIRS, CARMINE_SHINGLES);
		stonecutterRecipe(output, BUILDING_BLOCKS, CARMINE_PAVEMENT_WALL, CARMINE_SHINGLES);

		ShapelessRecipeBuilder.shapeless(MISC, AtmosphericItems.COCHINEAL_BANNER_PATTERN).requires(Items.PAPER).requires(AtmosphericItems.CARMINE_HUSK).unlockedBy("has_carmine_husk", has(AtmosphericItems.CARMINE_HUSK)).save(output);
		ShapedRecipeBuilder.shaped(FOOD, AtmosphericItems.GOLDEN_DRAGON_FRUIT).define('#', Items.GOLD_INGOT).define('X', AtmosphericItems.DRAGON_FRUIT).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(output);
		conditionalStorageRecipes(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.DRAGON_FRUIT, BUILDING_BLOCKS, DRAGON_FRUIT_CRATE);
		conditionalStorageRecipesWithCustomUnpacking(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.GOLDEN_DRAGON_FRUIT, BUILDING_BLOCKS, GOLDEN_DRAGON_FRUIT_CRATE, "golden_dragon_fruit_from_golden_dragon_fruit_crate", "golden_dragon_fruit");
		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.CANDIED_ORANGE_SLICES).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Items.SUGAR).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)).save(output);
		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.ORANGE_PUDDING).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Items.SWEET_BERRIES).requires(Items.COCOA_BEANS).requires(Tags.Items.EGGS).requires(BlueprintItemTags.MILK).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)).save(output);
		conditionalStorageRecipes(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.ORANGE, BUILDING_BLOCKS, ORANGE_CRATE);
		conditionalStorageRecipes(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.BLOOD_ORANGE, BUILDING_BLOCKS, BLOOD_ORANGE_CRATE);

		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.ORANGE_SORBET).requires(Items.BOWL).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Blocks.ICE).requires(Items.SUGAR).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)).save(output.withConditions(AtmosphericConditions.NEAPOLITAN_NOT_LOADED));
		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.ORANGE_SORBET).requires(Items.BOWL).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(AtmosphericItemTags.ICE_CUBES).requires(Items.SUGAR).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)).save(output.withConditions(AtmosphericConditions.NEAPOLITAN_LOADED), Atmospheric.location("orange_sorbet_from_ice_cubes"));

		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.PASSION_FRUIT_SORBET).requires(Items.BOWL).requires(AtmosphericItemTags.FRUITS_PASSION_FRUIT).requires(Blocks.ICE).requires(Items.SUGAR).unlockedBy("has_passion_fruit", has(AtmosphericItemTags.FRUITS_PASSION_FRUIT)).save(output.withConditions(AtmosphericConditions.NEAPOLITAN_NOT_LOADED));
		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.PASSION_FRUIT_SORBET).requires(Items.BOWL).requires(AtmosphericItemTags.FRUITS_PASSION_FRUIT).requires(AtmosphericItemTags.ICE_CUBES).requires(Items.SUGAR).unlockedBy("has_passion_fruit", has(AtmosphericItemTags.FRUITS_PASSION_FRUIT)).save(output.withConditions(AtmosphericConditions.NEAPOLITAN_LOADED), Atmospheric.location("passion_fruit_sorbet_from_ice_cubes"));

		twoByTwoPacker(output, BUILDING_BLOCKS, CUT_IVORY_TRAVERTINE, IVORY_TRAVERTINE);
		generateRecipes(output, AtmosphericBlockFamilies.CUT_IVORY_TRAVERTINE_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, IVORY_TRAVERTINE_SLAB, IVORY_TRAVERTINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, IVORY_TRAVERTINE_STAIRS, IVORY_TRAVERTINE);
		stonecutterRecipe(output, DECORATIONS, IVORY_TRAVERTINE_WALL, IVORY_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_IVORY_TRAVERTINE, IVORY_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CUT_IVORY_TRAVERTINE, IVORY_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, IVORY_TRAVERTINE_SLAB, CUT_IVORY_TRAVERTINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, IVORY_TRAVERTINE_STAIRS, CUT_IVORY_TRAVERTINE);
		stonecutterRecipe(output, DECORATIONS, IVORY_TRAVERTINE_WALL, CUT_IVORY_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_IVORY_TRAVERTINE, CUT_IVORY_TRAVERTINE);

		twoByTwoPacker(output, BUILDING_BLOCKS, CUT_PEACH_TRAVERTINE, PEACH_TRAVERTINE);
		generateRecipes(output, AtmosphericBlockFamilies.CUT_PEACH_TRAVERTINE_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, PEACH_TRAVERTINE_SLAB, PEACH_TRAVERTINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, PEACH_TRAVERTINE_STAIRS, PEACH_TRAVERTINE);
		stonecutterRecipe(output, DECORATIONS, PEACH_TRAVERTINE_WALL, PEACH_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_PEACH_TRAVERTINE, PEACH_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CUT_PEACH_TRAVERTINE, PEACH_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, PEACH_TRAVERTINE_SLAB, CUT_PEACH_TRAVERTINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, PEACH_TRAVERTINE_STAIRS, CUT_PEACH_TRAVERTINE);
		stonecutterRecipe(output, DECORATIONS, PEACH_TRAVERTINE_WALL, CUT_PEACH_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_PEACH_TRAVERTINE, CUT_PEACH_TRAVERTINE);

		twoByTwoPacker(output, BUILDING_BLOCKS, CUT_PERSIMMON_TRAVERTINE, PERSIMMON_TRAVERTINE);
		generateRecipes(output, AtmosphericBlockFamilies.CUT_PERSIMMON_TRAVERTINE_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, PERSIMMON_TRAVERTINE_SLAB, PERSIMMON_TRAVERTINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, PERSIMMON_TRAVERTINE_STAIRS, PERSIMMON_TRAVERTINE);
		stonecutterRecipe(output, DECORATIONS, PERSIMMON_TRAVERTINE_WALL, PERSIMMON_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_PERSIMMON_TRAVERTINE, PERSIMMON_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CUT_PERSIMMON_TRAVERTINE, PERSIMMON_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, PERSIMMON_TRAVERTINE_SLAB, CUT_PERSIMMON_TRAVERTINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, PERSIMMON_TRAVERTINE_STAIRS, CUT_PERSIMMON_TRAVERTINE);
		stonecutterRecipe(output, DECORATIONS, PERSIMMON_TRAVERTINE_WALL, CUT_PERSIMMON_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_PERSIMMON_TRAVERTINE, CUT_PERSIMMON_TRAVERTINE);

		twoByTwoPacker(output, BUILDING_BLOCKS, CUT_SAFFRON_TRAVERTINE, SAFFRON_TRAVERTINE);
		generateRecipes(output, AtmosphericBlockFamilies.CUT_SAFFRON_TRAVERTINE_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, SAFFRON_TRAVERTINE_SLAB, SAFFRON_TRAVERTINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SAFFRON_TRAVERTINE_STAIRS, SAFFRON_TRAVERTINE);
		stonecutterRecipe(output, DECORATIONS, SAFFRON_TRAVERTINE_WALL, SAFFRON_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_SAFFRON_TRAVERTINE, SAFFRON_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CUT_SAFFRON_TRAVERTINE, SAFFRON_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, SAFFRON_TRAVERTINE_SLAB, CUT_SAFFRON_TRAVERTINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SAFFRON_TRAVERTINE_STAIRS, CUT_SAFFRON_TRAVERTINE);
		stonecutterRecipe(output, DECORATIONS, SAFFRON_TRAVERTINE_WALL, CUT_SAFFRON_TRAVERTINE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_SAFFRON_TRAVERTINE, CUT_SAFFRON_TRAVERTINE);

		ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, DOLERITE, 2).requires(AtmosphericItemTags.TRAVERTINE).requires(Blocks.COBBLESTONE).unlockedBy("has_travertine", has(AtmosphericItemTags.TRAVERTINE)).save(output);
		generateRecipes(output, AtmosphericBlockFamilies.DOLERITE_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, DOLERITE_SLAB, DOLERITE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, DOLERITE_STAIRS, DOLERITE);
		stonecutterRecipe(output, DECORATIONS, DOLERITE_WALL, DOLERITE);
		stonecutterRecipe(output, BUILDING_BLOCKS, POLISHED_DOLERITE, DOLERITE);
		stonecutterRecipe(output, BUILDING_BLOCKS, POLISHED_DOLERITE_SLAB, DOLERITE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, POLISHED_DOLERITE_STAIRS, DOLERITE);
		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, POLISHED_DOLERITE, 4).define('#', DOLERITE).pattern("##").pattern("##").unlockedBy("has_dolerite", has(DOLERITE)).save(output);
		generateRecipes(output, AtmosphericBlockFamilies.POLISHED_DOLERITE_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, POLISHED_DOLERITE_SLAB, POLISHED_DOLERITE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, POLISHED_DOLERITE_STAIRS, POLISHED_DOLERITE);

		ShapelessRecipeBuilder.shapeless(DECORATIONS, GRIMWEB).requires(AtmosphericItemTags.GRIMWOOD_LOGS).requires(Items.COBWEB).unlockedBy("has_grimwood", has(AtmosphericItemTags.GRIMWOOD_LOGS)).save(output);

		generateRecipes(output, AtmosphericBlockFamilies.ROSEWOOD_PLANKS_FAMILY);
		planksFromLogs(output, ROSEWOOD_PLANKS, AtmosphericItemTags.ROSEWOOD_LOGS, 4);
		woodFromLogs(output, ROSEWOOD, ROSEWOOD_LOG);
		woodFromLogs(output, STRIPPED_ROSEWOOD, STRIPPED_ROSEWOOD_LOG);
		hangingSign(output, ROSEWOOD_HANGING_SIGNS.getFirst(), STRIPPED_ROSEWOOD_LOG);
		BoatloadRecipeProvider.boatRecipes(output, AtmosphericBoatTypes.ROSEWOOD);
		WoodworksRecipeProvider.baseRecipes(output, ROSEWOOD_PLANKS, ROSEWOOD_SLAB, ROSEWOOD_BOARDS, ROSEWOOD_BOOKSHELF, CHISELED_ROSEWOOD_BOOKSHELF, ROSEWOOD_LADDER, ROSEWOOD_BEEHIVE, ROSEWOOD_CHEST, TRAPPED_ROSEWOOD_CHEST, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, AtmosphericBlockFamilies.ROSEWOOD_PLANKS_FAMILY, AtmosphericItemTags.ROSEWOOD_LOGS, ROSEWOOD_BOARDS, ROSEWOOD_LADDER, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, ROSEWOOD_LEAVES, ROSEWOOD_LEAF_PILE, Atmospheric.MOD_ID);

		conditionalStorageRecipes(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.PASSION_FRUIT, BUILDING_BLOCKS, PASSION_FRUIT_CRATE);
		conditionalStorageRecipesWithCustomUnpacking(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.SHIMMERING_PASSION_FRUIT, BUILDING_BLOCKS, SHIMMERING_PASSION_FRUIT_CRATE, "shimmering_passion_fruit_from_shimmering_passion_fruit_crate", "shimmering_passion_fruit");

		generateRecipes(output, AtmosphericBlockFamilies.MORADO_PLANKS_FAMILY);
		planksFromLogs(output, MORADO_PLANKS, AtmosphericItemTags.MORADO_LOGS, 4);
		woodFromLogs(output, MORADO_WOOD, MORADO_LOG);
		woodFromLogs(output, STRIPPED_MORADO_WOOD, STRIPPED_MORADO_LOG);
		hangingSign(output, MORADO_HANGING_SIGNS.getFirst(), STRIPPED_MORADO_LOG);
		BoatloadRecipeProvider.boatRecipes(output, AtmosphericBoatTypes.MORADO);
		WoodworksRecipeProvider.baseRecipes(output, MORADO_PLANKS, MORADO_SLAB, MORADO_BOARDS, MORADO_BOOKSHELF, CHISELED_MORADO_BOOKSHELF, MORADO_LADDER, MORADO_BEEHIVE, MORADO_CHEST, TRAPPED_MORADO_CHEST, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, AtmosphericBlockFamilies.MORADO_PLANKS_FAMILY, AtmosphericItemTags.MORADO_LOGS, MORADO_BOARDS, MORADO_LADDER, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, MORADO_LEAVES, MORADO_LEAF_PILE, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, FLOWERING_MORADO_LEAVES, FLOWERING_MORADO_LEAF_PILE, Atmospheric.MOD_ID);

		generateRecipes(output, AtmosphericBlockFamilies.YUCCA_PLANKS_FAMILY);
		planksFromLogs(output, YUCCA_PLANKS, AtmosphericItemTags.YUCCA_LOGS, 4);
		woodFromLogs(output, YUCCA_WOOD, YUCCA_LOG);
		woodFromLogs(output, STRIPPED_YUCCA_WOOD, STRIPPED_YUCCA_LOG);
		hangingSign(output, YUCCA_HANGING_SIGNS.getFirst(), STRIPPED_YUCCA_LOG);
		BoatloadRecipeProvider.boatRecipes(output, AtmosphericBoatTypes.YUCCA);
		WoodworksRecipeProvider.baseRecipes(output, YUCCA_PLANKS, YUCCA_SLAB, YUCCA_BOARDS, YUCCA_BOOKSHELF, CHISELED_YUCCA_BOOKSHELF, YUCCA_LADDER, YUCCA_BEEHIVE, YUCCA_CHEST, TRAPPED_YUCCA_CHEST, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, AtmosphericBlockFamilies.YUCCA_PLANKS_FAMILY, AtmosphericItemTags.YUCCA_LOGS, YUCCA_BOARDS, YUCCA_LADDER, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, YUCCA_LEAVES, YUCCA_LEAF_PILE, Atmospheric.MOD_ID);

		foodCookingRecipes(output, AtmosphericItems.YUCCA_FRUIT, AtmosphericItems.ROASTED_YUCCA_FRUIT);
		foodCookingRecipes(output, YUCCA_BUNDLE, ROASTED_YUCCA_BUNDLE);
		ShapedRecipeBuilder.shaped(FOOD, YUCCA_GATEAU).define('R', AtmosphericItems.ROASTED_YUCCA_FRUIT).define('Y', AtmosphericItems.ALOE_GEL_BOTTLE).define('#', Items.WHEAT).define('X', YUCCA_FLOWER).pattern("RYR").pattern("#X#").unlockedBy("has_roasted_yucca_fruit", has(AtmosphericItems.ROASTED_YUCCA_FRUIT)).save(output);

		conditionalStorageRecipes(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.YUCCA_FRUIT, BUILDING_BLOCKS, YUCCA_CASK);
		conditionalStorageRecipesWithCustomUnpacking(output, AtmosphericConditions.QUARK_LOADED, FOOD, AtmosphericItems.ROASTED_YUCCA_FRUIT, BUILDING_BLOCKS, ROASTED_YUCCA_CASK, "roasted_yucca_fruit_from_roasted_yucca_cask", "roasted_yucca_fruit");

		generateRecipes(output, AtmosphericBlockFamilies.ASPEN_PLANKS_FAMILY);
		planksFromLogs(output, ASPEN_PLANKS, AtmosphericItemTags.ASPEN_LOGS, 4);
		woodFromLogs(output, ASPEN_WOOD, ASPEN_LOG);
		woodFromLogs(output, STRIPPED_ASPEN_WOOD, STRIPPED_ASPEN_LOG);
		woodFromLogs(output, WATCHFUL_ASPEN_WOOD, WATCHFUL_ASPEN_LOG);
		woodFromLogs(output, CRUSTOSE_WOOD, CRUSTOSE_LOG);
		hangingSign(output, ASPEN_HANGING_SIGNS.getFirst(), STRIPPED_ASPEN_LOG);
		BoatloadRecipeProvider.boatRecipes(output, AtmosphericBoatTypes.ASPEN);
		WoodworksRecipeProvider.baseRecipes(output, ASPEN_PLANKS, ASPEN_SLAB, ASPEN_BOARDS, ASPEN_BOOKSHELF, CHISELED_ASPEN_BOOKSHELF, ASPEN_LADDER, ASPEN_BEEHIVE, ASPEN_CHEST, TRAPPED_ASPEN_CHEST, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, AtmosphericBlockFamilies.ASPEN_PLANKS_FAMILY, AtmosphericItemTags.ASPEN_LOGS, ASPEN_BOARDS, ASPEN_LADDER, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, ASPEN_LEAVES, ASPEN_LEAF_PILE, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE, Atmospheric.MOD_ID);

		generateRecipes(output, AtmosphericBlockFamilies.LAUREL_PLANKS_FAMILY);
		planksFromLogs(output, LAUREL_PLANKS, AtmosphericItemTags.LAUREL_LOGS, 4);
		woodFromLogs(output, LAUREL_WOOD, LAUREL_LOG);
		woodFromLogs(output, STRIPPED_LAUREL_WOOD, STRIPPED_LAUREL_LOG);
		hangingSign(output, LAUREL_HANGING_SIGNS.getFirst(), STRIPPED_LAUREL_LOG);
		BoatloadRecipeProvider.boatRecipes(output, AtmosphericBoatTypes.LAUREL);
		WoodworksRecipeProvider.baseRecipes(output, LAUREL_PLANKS, LAUREL_SLAB, LAUREL_BOARDS, LAUREL_BOOKSHELF, CHISELED_LAUREL_BOOKSHELF, LAUREL_LADDER, LAUREL_BEEHIVE, LAUREL_CHEST, TRAPPED_LAUREL_CHEST, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, AtmosphericBlockFamilies.LAUREL_PLANKS_FAMILY, AtmosphericItemTags.LAUREL_LOGS, LAUREL_BOARDS, LAUREL_LADDER, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, LAUREL_LEAVES, LAUREL_LEAF_PILE, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, DRY_LAUREL_LEAVES, DRY_LAUREL_LEAF_PILE, Atmospheric.MOD_ID);

		generateRecipes(output, AtmosphericBlockFamilies.KOUSA_PLANKS_FAMILY);
		planksFromLogs(output, KOUSA_PLANKS, AtmosphericItemTags.KOUSA_LOGS, 4);
		woodFromLogs(output, KOUSA_WOOD, KOUSA_LOG);
		woodFromLogs(output, STRIPPED_KOUSA_WOOD, STRIPPED_KOUSA_LOG);
		hangingSign(output, KOUSA_HANGING_SIGNS.getFirst(), STRIPPED_KOUSA_LOG);
		BoatloadRecipeProvider.boatRecipes(output, AtmosphericBoatTypes.KOUSA);
		WoodworksRecipeProvider.baseRecipes(output, KOUSA_PLANKS, KOUSA_SLAB, KOUSA_BOARDS, KOUSA_BOOKSHELF, CHISELED_KOUSA_BOOKSHELF, KOUSA_LADDER, KOUSA_BEEHIVE, KOUSA_CHEST, TRAPPED_KOUSA_CHEST, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, AtmosphericBlockFamilies.KOUSA_PLANKS_FAMILY, AtmosphericItemTags.KOUSA_LOGS, KOUSA_BOARDS, KOUSA_LADDER, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, KOUSA_LEAVES, KOUSA_LEAF_PILE, Atmospheric.MOD_ID);

		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.ALOE_GEL_BOTTLE, 4).requires(ALOE_GEL_BLOCK).requires(Items.GLASS_BOTTLE, 4).unlockedBy("has_aloe_gel_block", has(ALOE_GEL_BLOCK)).save(output, getModConversionRecipeName(AtmosphericItems.ALOE_GEL_BOTTLE, ALOE_GEL_BLOCK));
		twoByTwoPacker(output, REDSTONE, ALOE_GEL_BLOCK, AtmosphericItems.ALOE_GEL_BOTTLE);
		ShapelessRecipeBuilder.shapeless(FOOD, AtmosphericItems.ALOE_GEL_BOTTLE).requires(AtmosphericItems.ALOE_LEAVES, 8).requires(Items.GLASS_BOTTLE).unlockedBy("has_aloe_leaves", has(AtmosphericItems.ALOE_LEAVES)).save(output);

		ShapedRecipeBuilder.shaped(BREWING, AtmosphericItems.SHIMMERING_PASSION_FRUIT).define('#', Tags.Items.NUGGETS_GOLD).define('X', AtmosphericItems.PASSION_FRUIT).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_passion_fruit", has(AtmosphericItems.PASSION_FRUIT)).save(output);

		twoByTwoPacker(output, BUILDING_BLOCKS, CURRANT_STALK_BUNDLE, CURRANT_STALK);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, CURRANT_LEAVES, CURRANT_LEAF_PILE, Atmospheric.MOD_ID);

		threeByThreePacker(output, BUILDING_BLOCKS, ALOE_BUNDLE, AtmosphericItems.ALOE_LEAVES);
		ShapelessRecipeBuilder.shapeless(MISC, AtmosphericItems.ALOE_LEAVES, 9).requires(ALOE_BUNDLE).unlockedBy("has_aloe_bundle", has(ALOE_BUNDLE)).save(output);

		threeByThreePacker(output, BUILDING_BLOCKS, PASSION_VINE_BUNDLE, PASSION_VINE);
		ShapelessRecipeBuilder.shapeless(MISC, PASSION_VINE, 9).requires(PASSION_VINE_BUNDLE).unlockedBy("has_passion_vine_bundle", has(PASSION_VINE_BUNDLE)).save(output);
		ShapedRecipeBuilder.shaped(DECORATIONS, AtmosphericItems.PASSION_VINE_COIL).define('#', PASSION_VINE).pattern("###").pattern("# #").pattern("###").unlockedBy("has_passion_vine", has(PASSION_VINE)).save(output);
		ShapelessRecipeBuilder.shapeless(MISC, PASSION_VINE, 8).requires(AtmosphericItems.PASSION_VINE_COIL).unlockedBy("has_passion_vine_coil", has(AtmosphericItems.PASSION_VINE_COIL)).save(output, getModConversionRecipeName(PASSION_VINE, AtmosphericItems.PASSION_VINE_COIL));
		ShapedRecipeBuilder.shaped(FOOD, AtmosphericItems.PASSION_FRUIT_TART).define('#', AtmosphericItemTags.FRUITS_PASSION_FRUIT).define('X', Tags.Items.EGGS).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_passion_fruit", has(AtmosphericItemTags.FRUITS_PASSION_FRUIT)).save(output);

		generateRecipes(output, AtmosphericBlockFamilies.GRIMWOOD_PLANKS_FAMILY);
		planksFromLogs(output, GRIMWOOD_PLANKS, AtmosphericItemTags.GRIMWOOD_LOGS, 4);
		woodFromLogs(output, GRIMWOOD, GRIMWOOD_LOG);
		woodFromLogs(output, STRIPPED_GRIMWOOD, STRIPPED_GRIMWOOD_LOG);
		hangingSign(output, GRIMWOOD_HANGING_SIGNS.getFirst(), STRIPPED_GRIMWOOD_LOG);
		BoatloadRecipeProvider.boatRecipes(output, AtmosphericBoatTypes.GRIMWOOD);
		WoodworksRecipeProvider.baseRecipes(output, GRIMWOOD_PLANKS, GRIMWOOD_SLAB, GRIMWOOD_BOARDS, GRIMWOOD_BOOKSHELF, CHISELED_GRIMWOOD_BOOKSHELF, GRIMWOOD_LADDER, GRIMWOOD_BEEHIVE, GRIMWOOD_CHEST, TRAPPED_GRIMWOOD_CHEST, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, AtmosphericBlockFamilies.GRIMWOOD_PLANKS_FAMILY, AtmosphericItemTags.GRIMWOOD_LOGS, GRIMWOOD_BOARDS, GRIMWOOD_LADDER, Atmospheric.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, GRIMWOOD_LEAVES, GRIMWOOD_LEAF_PILE, Atmospheric.MOD_ID);

		twoByTwoPacker(output, BUILDING_BLOCKS, ARID_SANDSTONE, ARID_SAND);
		generateRecipes(output, AtmosphericBlockFamilies.ARID_SANDSTONE_FAMILY);
		generateRecipes(output, AtmosphericBlockFamilies.CUT_ARID_SANDSTONE_FAMILY);
		generateRecipes(output, AtmosphericBlockFamilies.SMOOTH_ARID_SANDSTONE_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, ARID_SANDSTONE_SLAB, ARID_SANDSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, ARID_SANDSTONE_STAIRS, ARID_SANDSTONE);
		stonecutterRecipe(output, DECORATIONS, ARID_SANDSTONE_WALL, ARID_SANDSTONE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_ARID_SANDSTONE, ARID_SANDSTONE);
		stonecutterRecipe(output, DECORATIONS, CUT_ARID_SANDSTONE, ARID_SANDSTONE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CUT_ARID_SANDSTONE_SLAB, ARID_SANDSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, CUT_ARID_SANDSTONE_SLAB, CUT_ARID_SANDSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SMOOTH_ARID_SANDSTONE_SLAB, SMOOTH_ARID_SANDSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SMOOTH_ARID_SANDSTONE_STAIRS, SMOOTH_ARID_SANDSTONE);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ARID_SANDSTONE), BUILDING_BLOCKS, SMOOTH_ARID_SANDSTONE, 0.1F, 200).unlockedBy("has_arid_sandstone", has(ARID_SANDSTONE)).save(output);

		twoByTwoPacker(output, BUILDING_BLOCKS, RED_ARID_SANDSTONE, RED_ARID_SAND);
		generateRecipes(output, AtmosphericBlockFamilies.RED_ARID_SANDSTONE_FAMILY);
		generateRecipes(output, AtmosphericBlockFamilies.CUT_RED_ARID_SANDSTONE_FAMILY);
		generateRecipes(output, AtmosphericBlockFamilies.SMOOTH_RED_ARID_SANDSTONE_FAMILY);
		stonecutterRecipe(output, BUILDING_BLOCKS, RED_ARID_SANDSTONE_SLAB, RED_ARID_SANDSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, RED_ARID_SANDSTONE_STAIRS, RED_ARID_SANDSTONE);
		stonecutterRecipe(output, DECORATIONS, RED_ARID_SANDSTONE_WALL, RED_ARID_SANDSTONE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_RED_ARID_SANDSTONE, RED_ARID_SANDSTONE);
		stonecutterRecipe(output, DECORATIONS, CUT_RED_ARID_SANDSTONE, RED_ARID_SANDSTONE);
		stonecutterRecipe(output, BUILDING_BLOCKS, CUT_RED_ARID_SANDSTONE_SLAB, RED_ARID_SANDSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, CUT_RED_ARID_SANDSTONE_SLAB, CUT_RED_ARID_SANDSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SMOOTH_RED_ARID_SANDSTONE_SLAB, SMOOTH_RED_ARID_SANDSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SMOOTH_RED_ARID_SANDSTONE_STAIRS, SMOOTH_RED_ARID_SANDSTONE);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(RED_ARID_SANDSTONE), BUILDING_BLOCKS, SMOOTH_RED_ARID_SANDSTONE, 0.1F, 200).unlockedBy("has_red_arid_sandstone", has(RED_ARID_SANDSTONE)).save(output);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(AtmosphericItemTags.SMELTS_TO_ARID_GLASS), BUILDING_BLOCKS, ARID_GLASS, 0.1F, 200).unlockedBy("has_smelts_to_arid_glass", has(AtmosphericItemTags.SMELTS_TO_ARID_GLASS)).save(output);
		ShapedRecipeBuilder.shaped(DECORATIONS, ARID_GLASS_PANE, 16).define('#', ARID_GLASS).pattern("###").pattern("###").unlockedBy("has_arid_glass", has(ARID_GLASS)).save(output);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ARID_GLASS), BUILDING_BLOCKS, Blocks.GLASS, 0.1F, 200).unlockedBy("has_arid_glass", has(ARID_GLASS)).save(output, getModConversionRecipeName(Blocks.GLASS, ARID_GLASS));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ARID_GLASS_PANE), DECORATIONS, Blocks.GLASS_PANE, 0.1F, 200).unlockedBy("has_arid_glass_pane", has(ARID_GLASS_PANE)).save(output, getModConversionRecipeName(Blocks.GLASS_PANE, ARID_GLASS_PANE));

		ShapedRecipeBuilder.shaped(MISC, Blocks.BEACON).define('S', Items.NETHER_STAR).define('G', Tags.Items.GLASS_BLOCKS_COLORLESS).define('O', Blocks.OBSIDIAN).pattern("GGG").pattern("GSG").pattern("OOO").unlockedBy("has_nether_star", has(Items.NETHER_STAR)).save(output);
		ShapedRecipeBuilder.shaped(REDSTONE, Blocks.DAYLIGHT_DETECTOR).define('Q', Items.QUARTZ).define('G', Tags.Items.GLASS_BLOCKS_COLORLESS).define('W', Ingredient.of(ItemTags.WOODEN_SLABS)).pattern("GGG").pattern("QQQ").pattern("WWW").unlockedBy("has_quartz", has(Items.QUARTZ)).save(output);
		ShapedRecipeBuilder.shaped(DECORATIONS, Items.END_CRYSTAL).define('T', Items.GHAST_TEAR).define('E', Items.ENDER_EYE).define('G', Tags.Items.GLASS_BLOCKS_COLORLESS).pattern("GGG").pattern("GEG").pattern("GTG").unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(output);
		ShapedRecipeBuilder.shaped(BREWING, Items.GLASS_BOTTLE, 3).define('#', Tags.Items.GLASS_BLOCKS_COLORLESS).pattern("# #").pattern(" # ").unlockedBy("has_glass", has(Blocks.GLASS)).save(output);
		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, Blocks.TINTED_GLASS, 2).define('G', Tags.Items.GLASS_BLOCKS_COLORLESS).define('S', Tags.Items.GEMS_AMETHYST).pattern(" S ").pattern("SGS").pattern(" S ").unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD)).save(output);

		stainedGlassFromGlassAndDye(output, Blocks.BLACK_STAINED_GLASS, Items.BLACK_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.BLACK_STAINED_GLASS_PANE, Items.BLACK_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.BLUE_STAINED_GLASS, Items.BLUE_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.BLUE_STAINED_GLASS_PANE, Items.BLUE_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.BROWN_STAINED_GLASS, Items.BROWN_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.BROWN_STAINED_GLASS_PANE, Items.BROWN_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.CYAN_STAINED_GLASS, Items.CYAN_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.CYAN_STAINED_GLASS_PANE, Items.CYAN_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.GRAY_STAINED_GLASS, Items.GRAY_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.GRAY_STAINED_GLASS_PANE, Items.GRAY_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.GREEN_STAINED_GLASS, Items.GREEN_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.GREEN_STAINED_GLASS_PANE, Items.GREEN_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.LIGHT_BLUE_STAINED_GLASS, Items.LIGHT_BLUE_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, Items.LIGHT_BLUE_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.LIGHT_GRAY_STAINED_GLASS, Items.LIGHT_GRAY_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, Items.LIGHT_GRAY_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.LIME_STAINED_GLASS, Items.LIME_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.LIME_STAINED_GLASS_PANE, Items.LIME_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.MAGENTA_STAINED_GLASS, Items.MAGENTA_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.MAGENTA_STAINED_GLASS_PANE, Items.MAGENTA_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.ORANGE_STAINED_GLASS, Items.ORANGE_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.ORANGE_STAINED_GLASS_PANE, Items.ORANGE_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.PINK_STAINED_GLASS, Items.PINK_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.PINK_STAINED_GLASS_PANE, Items.PINK_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.PURPLE_STAINED_GLASS, Items.PURPLE_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.PURPLE_STAINED_GLASS_PANE, Items.PURPLE_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.RED_STAINED_GLASS, Items.RED_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.RED_STAINED_GLASS_PANE, Items.RED_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.WHITE_STAINED_GLASS, Items.WHITE_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.WHITE_STAINED_GLASS_PANE, Items.WHITE_DYE);
		stainedGlassFromGlassAndDye(output, Blocks.YELLOW_STAINED_GLASS, Items.YELLOW_DYE);
		stainedGlassPaneFromGlassPaneAndDye(output, Blocks.YELLOW_STAINED_GLASS_PANE, Items.YELLOW_DYE);

		ClayworksRecipeProvider.bakingRecipe(output, MISC, AGAVE, Items.CYAN_DYE, 1.0F, 100, Atmospheric.MOD_ID);
		ClayworksRecipeProvider.bakingRecipe(output, BUILDING_BLOCKS, ARID_SANDSTONE, SMOOTH_ARID_SANDSTONE, 0.1F, 100, Atmospheric.MOD_ID);
		ClayworksRecipeProvider.bakingRecipe(output, BUILDING_BLOCKS, RED_ARID_SANDSTONE, SMOOTH_RED_ARID_SANDSTONE, 0.1F, 100, Atmospheric.MOD_ID);
		ClayworksRecipeProvider.bakingRecipe(output, BUILDING_BLOCKS, AtmosphericItemTags.SMELTS_TO_ARID_GLASS, "has_smelts_to_arid_glass", ARID_GLASS, 0.1F, 100, Atmospheric.MOD_ID);
		ClayworksRecipeProvider.bakingRecipe(output, BUILDING_BLOCKS, ARID_GLASS, Blocks.GLASS, 0.1F, 100, Atmospheric.MOD_ID);
		ClayworksRecipeProvider.bakingRecipe(output, DECORATIONS, ARID_GLASS_PANE, Blocks.GLASS_PANE, 0.1F, 100, Atmospheric.MOD_ID);
	}

	protected static void stainedGlassFromGlassAndDye(RecipeOutput output, ItemLike glass, ItemLike dye) {
		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, glass, 8).define('#', Tags.Items.GLASS_BLOCKS_COLORLESS).define('X', dye).pattern("###").pattern("#X#").pattern("###").group("stained_glass").unlockedBy("has_glass", has(Blocks.GLASS)).save(output);
	}

	protected static void stainedGlassPaneFromGlassPaneAndDye(RecipeOutput output, ItemLike glassPane, ItemLike dye) {
		ShapedRecipeBuilder.shaped(DECORATIONS, glassPane, 8).define('#', Tags.Items.GLASS_PANES_COLORLESS).define('$', dye).pattern("###").pattern("#$#").pattern("###").group("stained_glass_pane").unlockedBy("has_glass_pane", has(Blocks.GLASS_PANE)).unlockedBy(getHasName(dye), has(dye)).save(output, getConversionRecipeName(glassPane, Blocks.GLASS_PANE));
	}
}