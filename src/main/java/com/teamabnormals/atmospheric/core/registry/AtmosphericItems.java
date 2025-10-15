package com.teamabnormals.atmospheric.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.common.item.*;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericCompat;
import com.teamabnormals.atmospheric.core.other.AtmosphericEvents;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBannerPatternTags;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericTrimPatterns;
import com.teamabnormals.atmospheric.integration.boatload.AtmosphericBoatTypes;
import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Predicate;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class AtmosphericItems {
	public static final ItemSubRegistryHelper ITEMS = Atmospheric.REGISTRY_HELPER.getItemSubHelper();

	public static final DeferredItem<Item> PASSION_FRUIT = ITEMS.createItem("passion_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSION_FRUIT)));
	public static final DeferredItem<Item> SHIMMERING_PASSION_FRUIT = ITEMS.createItem("shimmering_passion_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.SHIMMERING_PASSION_FRUIT)));
	public static final DeferredItem<Item> PASSION_FRUIT_TART = ITEMS.createItem("passion_fruit_tart", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSION_FRUIT_TART)));
	public static final DeferredItem<Item> PASSION_FRUIT_SORBET = ITEMS.createItem("passion_fruit_sorbet", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSION_FRUIT_SORBET).stacksTo(1).craftRemainder(Items.BOWL)));
	public static final DeferredItem<Item> PASSION_VINE_COIL = ITEMS.createItem("passion_vine_coil", () -> new PassionVineCoilItem(new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> WATER_HYACINTH = ITEMS.createItem("water_hyacinth", () -> new PlaceOnWaterBlockItem(AtmosphericBlocks.WATER_HYACINTH.get(), new Item.Properties()));

	public static final DeferredItem<Item> YELLOW_BLOSSOMS = ITEMS.createItem("yellow_blossoms", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> YUCCA_FRUIT = ITEMS.createItem("yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.YUCCA_FRUIT)));
	public static final DeferredItem<Item> ROASTED_YUCCA_FRUIT = ITEMS.createItem("roasted_yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.ROASTED_YUCCA_FRUIT)));
	public static final DeferredItem<Item> YUCCA_GATEAU = ITEMS.createItem("yucca_gateau", () -> new BlockItem(AtmosphericBlocks.YUCCA_GATEAU.get(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> BARREL_CACTUS = ITEMS.createItem("barrel_cactus", () -> new BarrelCactusItem(new Item.Properties()));
	public static final DeferredItem<Item> ALOE_KERNELS = ITEMS.createItem("aloe_kernels", () -> new ItemNameBlockItem(AtmosphericBlocks.ALOE_VERA.get(), new Item.Properties()));
	public static final DeferredItem<Item> ALOE_LEAVES = ITEMS.createItem("aloe_leaves", () -> new AloeLeavesItem(new Item.Properties().food(AtmosphericFoods.ALOE_LEAVES)));
	public static final DeferredItem<Item> ALOE_GEL_BOTTLE = ITEMS.createItem("aloe_gel_bottle", () -> new AloeGelBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(AtmosphericFoods.ALOE_GEL).stacksTo(16)));

	public static final DeferredItem<Item> CURRANT = ITEMS.createItem("currant", () -> new Item(new Item.Properties().food(AtmosphericFoods.CURRANT)));
	public static final DeferredItem<Item> CURRANT_MUFFIN = ITEMS.createItem("currant_muffin", () -> new Item(new Item.Properties().food(AtmosphericFoods.CURRANT_MUFFIN)));

	public static final DeferredItem<Item> CARMINE_HUSK = ITEMS.createItem("carmine_husk", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> COCHINEAL_BANNER_PATTERN = ITEMS.createItem("cochineal_banner_pattern", () -> new BannerPatternItem(AtmosphericBannerPatternTags.PATTERN_ITEM_COCHINEAL, new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> DRAGON_FRUIT = ITEMS.createItem("dragon_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.DRAGON_FRUIT)));
	public static final DeferredItem<Item> GOLDEN_DRAGON_FRUIT = ITEMS.createItem("golden_dragon_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.GOLDEN_DRAGON_FRUIT)));
	public static final DeferredItem<Item> ENDER_DRAGON_FRUIT = ITEMS.createItem("ender_dragon_fruit", () -> new EnderDragonFruitItem(new Item.Properties().food(AtmosphericFoods.ENDER_DRAGON_FRUIT).rarity(Rarity.EPIC).stacksTo(1)));

	public static final DeferredItem<Item> ORANGE = ITEMS.createItem("orange", () -> new OrangeBlockItem(AtmosphericBlocks.ORANGE.get(), new Item.Properties().food(AtmosphericFoods.ORANGE).stacksTo(AtmosphericCompat.isAprilFools() ? 1 : 64)));
	public static final DeferredItem<Item> ORANGE_PUDDING = ITEMS.createItem("orange_pudding", () -> new Item(new Item.Properties().food(AtmosphericFoods.ORANGE_PUDDING)));
	public static final DeferredItem<Item> ORANGE_SORBET = ITEMS.createItem("orange_sorbet", () -> new Item(new Item.Properties().food(AtmosphericFoods.ORANGE_SORBET).stacksTo(1).craftRemainder(Items.BOWL)));
	public static final DeferredItem<Item> CANDIED_ORANGE_SLICES = ITEMS.createItem("candied_orange_slices", () -> new Item(new Item.Properties().food(AtmosphericFoods.CANDIED_ORANGE_SLICES)));
	public static final DeferredItem<Item> BLOOD_ORANGE = ITEMS.createItem("blood_orange", () -> new OrangeBlockItem(AtmosphericBlocks.BLOOD_ORANGE.get(), new Item.Properties().food(AtmosphericFoods.BLOOD_ORANGE)));

	public static final DeferredItem<Item> SCYTHE_POTTERY_SHERD = ITEMS.createItem("scythe_pottery_sherd", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> SUCCULENT_POTTERY_SHERD = ITEMS.createItem("succulent_pottery_sherd", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> SUN_POTTERY_SHERD = ITEMS.createItem("sun_pottery_sherd", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.createItem("apostle_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(AtmosphericTrimPatterns.APOSTLE));
	public static final DeferredItem<Item> DRUID_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.createItem("druid_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(AtmosphericTrimPatterns.DRUID));
	public static final DeferredItem<Item> PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.createItem("petrified_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(AtmosphericTrimPatterns.PETRIFIED));

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> ROSEWOOD_BOATS = ITEMS.createBoatAndChestBoatItem("rosewood", AtmosphericBlocks.ROSEWOOD_PLANKS);
	public static final DeferredItem<BlueprintBoatItem> ROSEWOOD_BOAT = ROSEWOOD_BOATS.getFirst();
	public static final DeferredItem<BlueprintBoatItem> ROSEWOOD_CHEST_BOAT = ROSEWOOD_BOATS.getSecond();
	public static final DeferredItem<Item> ROSEWOOD_FURNACE_BOAT = ITEMS.createItem("rosewood_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.ROSEWOOD_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_ROSEWOOD_BOAT = ITEMS.createItem("large_rosewood_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_ROSEWOOD_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> MORADO_BOATS = ITEMS.createBoatAndChestBoatItem("morado", AtmosphericBlocks.MORADO_PLANKS);
	public static final DeferredItem<BlueprintBoatItem> MORADO_BOAT = MORADO_BOATS.getFirst();
	public static final DeferredItem<BlueprintBoatItem> MORADO_CHEST_BOAT = MORADO_BOATS.getSecond();
	public static final DeferredItem<Item> MORADO_FURNACE_BOAT = ITEMS.createItem("morado_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.MORADO_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_MORADO_BOAT = ITEMS.createItem("large_morado_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_MORADO_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> YUCCA_BOATS = ITEMS.createBoatAndChestBoatItem("yucca", AtmosphericBlocks.YUCCA_PLANKS);
	public static final DeferredItem<BlueprintBoatItem> YUCCA_BOAT = YUCCA_BOATS.getFirst();
	public static final DeferredItem<BlueprintBoatItem> YUCCA_CHEST_BOAT = YUCCA_BOATS.getSecond();
	public static final DeferredItem<Item> YUCCA_FURNACE_BOAT = ITEMS.createItem("yucca_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.YUCCA_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_YUCCA_BOAT = ITEMS.createItem("large_yucca_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_YUCCA_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> KOUSA_BOATS = ITEMS.createBoatAndChestBoatItem("kousa", AtmosphericBlocks.KOUSA_PLANKS);
	public static final DeferredItem<BlueprintBoatItem> KOUSA_BOAT = KOUSA_BOATS.getFirst();
	public static final DeferredItem<BlueprintBoatItem> KOUSA_CHEST_BOAT = KOUSA_BOATS.getSecond();
	public static final DeferredItem<Item> KOUSA_FURNACE_BOAT = ITEMS.createItem("kousa_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.KOUSA_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_KOUSA_BOAT = ITEMS.createItem("large_kousa_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_KOUSA_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> ASPEN_BOATS = ITEMS.createBoatAndChestBoatItem("aspen", AtmosphericBlocks.ASPEN_PLANKS);
	public static final DeferredItem<BlueprintBoatItem> ASPEN_BOAT = ASPEN_BOATS.getFirst();
	public static final DeferredItem<BlueprintBoatItem> ASPEN_CHEST_BOAT = ASPEN_BOATS.getSecond();
	public static final DeferredItem<Item> ASPEN_FURNACE_BOAT = ITEMS.createItem("aspen_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.ASPEN_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_ASPEN_BOAT = ITEMS.createItem("large_aspen_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_ASPEN_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> LAUREL_BOATS = ITEMS.createBoatAndChestBoatItem("laurel", AtmosphericBlocks.LAUREL_PLANKS);
	public static final DeferredItem<BlueprintBoatItem> LAUREL_BOAT = LAUREL_BOATS.getFirst();
	public static final DeferredItem<BlueprintBoatItem> LAUREL_CHEST_BOAT = LAUREL_BOATS.getSecond();
	public static final DeferredItem<Item> LAUREL_FURNACE_BOAT = ITEMS.createItem("laurel_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LAUREL_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_LAUREL_BOAT = ITEMS.createItem("large_laurel_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_LAUREL_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> GRIMWOOD_BOATS = ITEMS.createBoatAndChestBoatItem("grimwood", AtmosphericBlocks.GRIMWOOD_PLANKS);
	public static final DeferredItem<BlueprintBoatItem> GRIMWOOD_BOAT = GRIMWOOD_BOATS.getFirst();
	public static final DeferredItem<BlueprintBoatItem> GRIMWOOD_CHEST_BOAT = GRIMWOOD_BOATS.getSecond();
	public static final DeferredItem<Item> GRIMWOOD_FURNACE_BOAT = ITEMS.createItem("grimwood_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.GRIMWOOD_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_GRIMWOOD_BOAT = ITEMS.createItem("large_grimwood_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_GRIMWOOD_BOAT : () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> TETRA_BUCKET = ITEMS.createItem("tetra_bucket", () -> new TetraBucketItem(new Item.Properties().stacksTo(1)));

	public static final DeferredItem<DeferredSpawnEggItem> TETRA_SPAWN_EGG = ITEMS.createSpawnEggItem("tetra", AtmosphericEntityTypes.TETRA::get, 0xFF4A47, 0x30FFCE);
	public static final DeferredItem<DeferredSpawnEggItem> COCHINEAL_SPAWN_EGG = ITEMS.createSpawnEggItem("cochineal", AtmosphericEntityTypes.COCHINEAL::get, 0xEF5B58, 0xAA3D3A);

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(Atmospheric.MOD_ID)
				.tab(FOOD_AND_DRINKS)
				.addItemsAfter(of(Items.MELON_SLICE), PASSION_FRUIT, SHIMMERING_PASSION_FRUIT, ORANGE, BLOOD_ORANGE, DRAGON_FRUIT, GOLDEN_DRAGON_FRUIT, YUCCA_FRUIT, ROASTED_YUCCA_FRUIT)
				.addItemsBefore(of(Items.CHORUS_FRUIT), CURRANT)
				.addItemsBefore(of(Items.PUMPKIN_PIE), YUCCA_GATEAU)
				.addItemsAfter(of(Items.PUMPKIN_PIE), PASSION_FRUIT_TART, CANDIED_ORANGE_SLICES, ORANGE_PUDDING, CURRANT_MUFFIN)
				.addItemsBefore(of(Items.MILK_BUCKET), PASSION_FRUIT_SORBET, ORANGE_SORBET)
				.addItemsAfter(of(Items.DRIED_KELP), ALOE_LEAVES)
				.addItemsAfter(of(Items.HONEY_BOTTLE), ALOE_GEL_BOTTLE)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Items.BEETROOT_SEEDS), ALOE_KERNELS)
				.tab(INGREDIENTS)
				.addItemsAfter(of(Items.HONEYCOMB), CARMINE_HUSK, YELLOW_BLOSSOMS)
				.addItemsAfter(of(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE), PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE, DRUID_ARMOR_TRIM_SMITHING_TEMPLATE, APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE)
				.addPotterySherdsAlphabetically(SCYTHE_POTTERY_SHERD, SUCCULENT_POTTERY_SHERD, SUN_POTTERY_SHERD)
				.addItemsAfter(of(Items.GLOBE_BANNER_PATTERN), COCHINEAL_BANNER_PATTERN)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsBefore(of(Items.ENDER_PEARL), PASSION_VINE_COIL)
				.addItemsAfter(of(Items.TROPICAL_FISH_BUCKET), TETRA_BUCKET)
				.addItemsBefore(of(Items.BAMBOO_RAFT), ROSEWOOD_BOAT, ROSEWOOD_CHEST_BOAT)
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), ROSEWOOD_FURNACE_BOAT, LARGE_ROSEWOOD_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), MORADO_BOAT, MORADO_CHEST_BOAT)
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), MORADO_FURNACE_BOAT, LARGE_MORADO_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), YUCCA_BOAT, YUCCA_CHEST_BOAT)
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), YUCCA_FURNACE_BOAT, LARGE_YUCCA_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), LAUREL_BOAT, LAUREL_CHEST_BOAT)
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), LAUREL_FURNACE_BOAT, LARGE_LAUREL_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), ASPEN_BOAT, ASPEN_CHEST_BOAT)
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), ASPEN_FURNACE_BOAT, LARGE_ASPEN_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), KOUSA_BOAT, KOUSA_CHEST_BOAT)
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), KOUSA_FURNACE_BOAT, LARGE_KOUSA_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), GRIMWOOD_BOAT, GRIMWOOD_CHEST_BOAT)
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), GRIMWOOD_FURNACE_BOAT, LARGE_GRIMWOOD_BOAT)
				.tab(SPAWN_EGGS)
				.addSpawnEggsAlphabetically(TETRA_SPAWN_EGG, COCHINEAL_SPAWN_EGG);
	}

	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static final class AtmosphericFoods {
		public static final FoodProperties PASSION_FRUIT = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).fast().alwaysEdible().effect(() -> new MobEffectInstance(AtmosphericMobEffects.SPITTING, 140, 0, false, false, false), 1.0F).build();
		public static final FoodProperties SHIMMERING_PASSION_FRUIT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1F).fast().alwaysEdible().effect(() -> new MobEffectInstance(AtmosphericMobEffects.SPITTING, 140, 1, false, false, false), 1.0F).build();
		public static final FoodProperties PASSION_FRUIT_TART = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).build();
		public static final FoodProperties PASSION_FRUIT_SORBET = new FoodProperties.Builder().nutrition(15).saturationModifier(0.6F).usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 4, false, false, true), 1.0F).build();

		public static final FoodProperties YUCCA_FRUIT = new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).build();
		public static final FoodProperties ROASTED_YUCCA_FRUIT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).effect(() -> new MobEffectInstance(AtmosphericMobEffects.PERSISTENCE, 560, 0, false, false, true), 1.0F).build();
		public static final FoodProperties ALOE_LEAVES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).alwaysEdible().build();
		public static final FoodProperties ALOE_GEL = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).alwaysEdible().build();

		public static final FoodProperties CURRANT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
		public static final FoodProperties CURRANT_MUFFIN = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();

		public static final FoodProperties DRAGON_FRUIT = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();
		public static final FoodProperties GOLDEN_DRAGON_FRUIT = new FoodProperties.Builder().nutrition(8).saturationModifier(0.1F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600), 1.0F).build();
		public static final FoodProperties ENDER_DRAGON_FRUIT = new FoodProperties.Builder().nutrition(20).saturationModifier(1.2F).build();

		public static final FoodProperties ORANGE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();
		public static final FoodProperties ORANGE_PUDDING = new FoodProperties.Builder().nutrition(8).saturationModifier(0.3F).build();
		public static final FoodProperties ORANGE_SORBET = new FoodProperties.Builder().nutrition(15).saturationModifier(0.6F).usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 4, false, false, true), 1.0F).build();
		public static final FoodProperties CANDIED_ORANGE_SLICES = new FoodProperties.Builder().nutrition(6).saturationModifier(0.3F).build();
		public static final FoodProperties BLOOD_ORANGE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();
	}
}
