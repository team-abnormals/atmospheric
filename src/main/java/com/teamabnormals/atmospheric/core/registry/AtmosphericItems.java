package com.teamabnormals.atmospheric.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.common.item.*;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericEvents;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBannerPatternTags;
import com.teamabnormals.atmospheric.core.registry.builtin.AtmosphericTrimPatterns;
import com.teamabnormals.atmospheric.integration.boatload.AtmosphericBoatTypes;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Predicate;

import static com.teamabnormals.blueprint.core.util.item.ItemStackUtil.is;
import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericItems {
	public static final ItemSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> PASSION_FRUIT = HELPER.createItem("passion_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSION_FRUIT)));
	public static final RegistryObject<Item> SHIMMERING_PASSION_FRUIT = HELPER.createItem("shimmering_passion_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.SHIMMERING_PASSION_FRUIT)));
	public static final RegistryObject<Item> PASSION_FRUIT_TART = HELPER.createItem("passion_fruit_tart", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSION_FRUIT_TART)));
	public static final RegistryObject<Item> PASSION_FRUIT_SORBET = HELPER.createItem("passion_fruit_sorbet", () -> new BowlFoodItem(new Item.Properties().food(AtmosphericFoods.PASSION_FRUIT_SORBET).stacksTo(1).craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> PASSION_VINE_COIL = HELPER.createItem("passion_vine_coil", () -> new PassionVineCoilItem(new Item.Properties().stacksTo(16)));
	public static final RegistryObject<Item> WATER_HYACINTH = HELPER.createItem("water_hyacinth", () -> new WaterHyacinthItem(new Item.Properties()));

	public static final RegistryObject<Item> YELLOW_BLOSSOMS = HELPER.createItem("yellow_blossoms", () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> YUCCA_FRUIT = HELPER.createItem("yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.YUCCA_FRUIT)));
	public static final RegistryObject<Item> ROASTED_YUCCA_FRUIT = HELPER.createItem("roasted_yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.ROASTED_YUCCA_FRUIT)));
	public static final RegistryObject<Item> YUCCA_GATEAU = HELPER.createItem("yucca_gateau", () -> new BlockItem(AtmosphericBlocks.YUCCA_GATEAU.get(), new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> BARREL_CACTUS = HELPER.createItem("barrel_cactus", () -> new BarrelCactusItem(new Item.Properties()));
	public static final RegistryObject<Item> ALOE_KERNELS = HELPER.createItem("aloe_kernels", () -> new ItemNameBlockItem(AtmosphericBlocks.ALOE_VERA.get(), new Item.Properties()));
	public static final RegistryObject<Item> ALOE_LEAVES = HELPER.createItem("aloe_leaves", () -> new AloeLeavesItem(new Item.Properties().food(AtmosphericFoods.ALOE_LEAVES)));
	public static final RegistryObject<Item> ALOE_GEL_BOTTLE = HELPER.createItem("aloe_gel_bottle", () -> new AloeGelBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(AtmosphericFoods.ALOE_GEL).stacksTo(16)));

	public static final RegistryObject<Item> CURRANT = HELPER.createItem("currant", () -> new Item(new Item.Properties().food(AtmosphericFoods.CURRANT)));
	public static final RegistryObject<Item> CURRANT_MUFFIN = HELPER.createItem("currant_muffin", () -> new Item(new Item.Properties().food(AtmosphericFoods.CURRANT_MUFFIN)));

	public static final RegistryObject<Item> CARMINE_HUSK = HELPER.createItem("carmine_husk", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> COCHINEAL_BANNER_PATTERN = HELPER.createItem("cochineal_banner_pattern", () -> new BannerPatternItem(AtmosphericBannerPatternTags.PATTERN_ITEM_COCHINEAL, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> DRAGON_FRUIT = HELPER.createItem("dragon_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.DRAGON_FRUIT)));
	public static final RegistryObject<Item> GOLDEN_DRAGON_FRUIT = HELPER.createItem("golden_dragon_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.GOLDEN_DRAGON_FRUIT)));
	public static final RegistryObject<Item> ENDER_DRAGON_FRUIT = HELPER.createItem("ender_dragon_fruit", () -> new EnderDragonFruitItem(new Item.Properties().food(AtmosphericFoods.ENDER_DRAGON_FRUIT).rarity(Rarity.EPIC).stacksTo(1)));

	public static final RegistryObject<Item> ORANGE = HELPER.createItem("orange", () -> new OrangeBlockItem(AtmosphericBlocks.ORANGE.get(), new Item.Properties().food(AtmosphericFoods.ORANGE).stacksTo(AtmosphericEvents.isAprilFools() ? 1 : 64)));
	public static final RegistryObject<Item> ORANGE_PUDDING = HELPER.createItem("orange_pudding", () -> new Item(new Item.Properties().food(AtmosphericFoods.ORANGE_PUDDING)));
	public static final RegistryObject<Item> ORANGE_SORBET = HELPER.createItem("orange_sorbet", () -> new BowlFoodItem(new Item.Properties().food(AtmosphericFoods.ORANGE_SORBET).stacksTo(1).craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> CANDIED_ORANGE_SLICES = HELPER.createItem("candied_orange_slices", () -> new Item(new Item.Properties().food(AtmosphericFoods.CANDIED_ORANGE_SLICES)));
	public static final RegistryObject<Item> BLOOD_ORANGE = HELPER.createItem("blood_orange", () -> new OrangeBlockItem(AtmosphericBlocks.BLOOD_ORANGE.get(), new Item.Properties().food(AtmosphericFoods.BLOOD_ORANGE)));

	public static final RegistryObject<Item> SCYTHE_POTTERY_SHERD = HELPER.createItem("scythe_pottery_sherd", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SUCCULENT_POTTERY_SHERD = HELPER.createItem("succulent_pottery_sherd", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SUN_POTTERY_SHERD = HELPER.createItem("sun_pottery_sherd", () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE = HELPER.createItem("apostle_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(AtmosphericTrimPatterns.APOSTLE));
	public static final RegistryObject<Item> DRUID_ARMOR_TRIM_SMITHING_TEMPLATE = HELPER.createItem("druid_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(AtmosphericTrimPatterns.DRUID));
	public static final RegistryObject<Item> PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE = HELPER.createItem("petrified_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(AtmosphericTrimPatterns.PETRIFIED));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> ROSEWOOD_BOAT = HELPER.createBoatAndChestBoatItem("rosewood", AtmosphericBlocks.ROSEWOOD_PLANKS);
	public static final RegistryObject<Item> ROSEWOOD_FURNACE_BOAT = HELPER.createItem("rosewood_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.ROSEWOOD_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_ROSEWOOD_BOAT = HELPER.createItem("large_rosewood_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_ROSEWOOD_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> MORADO_BOAT = HELPER.createBoatAndChestBoatItem("morado", AtmosphericBlocks.MORADO_PLANKS);
	public static final RegistryObject<Item> MORADO_FURNACE_BOAT = HELPER.createItem("morado_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.MORADO_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_MORADO_BOAT = HELPER.createItem("large_morado_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_MORADO_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> YUCCA_BOAT = HELPER.createBoatAndChestBoatItem("yucca", AtmosphericBlocks.YUCCA_PLANKS);
	public static final RegistryObject<Item> YUCCA_FURNACE_BOAT = HELPER.createItem("yucca_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.YUCCA_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_YUCCA_BOAT = HELPER.createItem("large_yucca_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_YUCCA_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> KOUSA_BOAT = HELPER.createBoatAndChestBoatItem("kousa", AtmosphericBlocks.KOUSA_PLANKS);
	public static final RegistryObject<Item> KOUSA_FURNACE_BOAT = HELPER.createItem("kousa_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.KOUSA_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_KOUSA_BOAT = HELPER.createItem("large_kousa_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_KOUSA_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> ASPEN_BOAT = HELPER.createBoatAndChestBoatItem("aspen", AtmosphericBlocks.ASPEN_PLANKS);
	public static final RegistryObject<Item> ASPEN_FURNACE_BOAT = HELPER.createItem("aspen_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.ASPEN_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_ASPEN_BOAT = HELPER.createItem("large_aspen_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_ASPEN_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> LAUREL_BOAT = HELPER.createBoatAndChestBoatItem("laurel", AtmosphericBlocks.LAUREL_PLANKS);
	public static final RegistryObject<Item> LAUREL_FURNACE_BOAT = HELPER.createItem("laurel_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LAUREL_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_LAUREL_BOAT = HELPER.createItem("large_laurel_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_LAUREL_BOAT : () -> new Item(new Item.Properties()));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> GRIMWOOD_BOAT = HELPER.createBoatAndChestBoatItem("grimwood", AtmosphericBlocks.GRIMWOOD_PLANKS);
	public static final RegistryObject<Item> GRIMWOOD_FURNACE_BOAT = HELPER.createItem("grimwood_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.GRIMWOOD_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_GRIMWOOD_BOAT = HELPER.createItem("large_grimwood_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_GRIMWOOD_BOAT : () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> TETRA_BUCKET = HELPER.createItem("tetra_bucket", () -> new TetraBucketItem(new Item.Properties().stacksTo(1)));

	public static final RegistryObject<ForgeSpawnEggItem> TETRA_SPAWN_EGG = HELPER.createSpawnEggItem("tetra", AtmosphericEntityTypes.TETRA::get, 0xFF4A47, 0x30FFCE);
	public static final RegistryObject<ForgeSpawnEggItem> COCHINEAL_SPAWN_EGG = HELPER.createSpawnEggItem("cochineal", AtmosphericEntityTypes.COCHINEAL::get, 0xEF5B58, 0xAA3D3A);

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
				.addItemsAlphabetically(stack -> stack.is(ItemTags.DECORATED_POT_SHERDS), SCYTHE_POTTERY_SHERD, SUCCULENT_POTTERY_SHERD, SUN_POTTERY_SHERD)
				.addItemsAfter(of(Items.GLOBE_BANNER_PATTERN), COCHINEAL_BANNER_PATTERN)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsBefore(of(Items.ENDER_PEARL), PASSION_VINE_COIL)
				.addItemsAfter(of(Items.TROPICAL_FISH_BUCKET), TETRA_BUCKET)
				.addItemsBefore(of(Items.BAMBOO_RAFT), ROSEWOOD_BOAT.getFirst(), ROSEWOOD_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), ROSEWOOD_FURNACE_BOAT, LARGE_ROSEWOOD_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), MORADO_BOAT.getFirst(), MORADO_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), MORADO_FURNACE_BOAT, LARGE_MORADO_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), YUCCA_BOAT.getFirst(), YUCCA_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), YUCCA_FURNACE_BOAT, LARGE_YUCCA_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), LAUREL_BOAT.getFirst(), LAUREL_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), LAUREL_FURNACE_BOAT, LARGE_LAUREL_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), ASPEN_BOAT.getFirst(), ASPEN_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), ASPEN_FURNACE_BOAT, LARGE_ASPEN_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), KOUSA_BOAT.getFirst(), KOUSA_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), KOUSA_FURNACE_BOAT, LARGE_KOUSA_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), GRIMWOOD_BOAT.getFirst(), GRIMWOOD_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), GRIMWOOD_FURNACE_BOAT, LARGE_GRIMWOOD_BOAT)
				.tab(SPAWN_EGGS)
				.addItemsAlphabetically(is(SpawnEggItem.class), TETRA_SPAWN_EGG, COCHINEAL_SPAWN_EGG);
	}

	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static final class AtmosphericFoods {
		public static final FoodProperties PASSION_FRUIT = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).fast().alwaysEat().effect(() -> new MobEffectInstance(AtmosphericMobEffects.SPITTING.get(), 140, 0, false, false, false), 1.0F).build();
		public static final FoodProperties SHIMMERING_PASSION_FRUIT = new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).fast().alwaysEat().effect(() -> new MobEffectInstance(AtmosphericMobEffects.SPITTING.get(), 140, 1, false, false, false), 1.0F).build();
		public static final FoodProperties PASSION_FRUIT_TART = new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).build();
		public static final FoodProperties PASSION_FRUIT_SORBET = new FoodProperties.Builder().nutrition(15).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 4, false, false, true), 1.0F).build();

		public static final FoodProperties YUCCA_FRUIT = new FoodProperties.Builder().nutrition(1).saturationMod(0.3F).build();
		public static final FoodProperties ROASTED_YUCCA_FRUIT = new FoodProperties.Builder().nutrition(3).saturationMod(0.6F).effect(() -> new MobEffectInstance(AtmosphericMobEffects.PERSISTENCE.get(), 560, 0, false, false, true), 1.0F).build();
		public static final FoodProperties ALOE_LEAVES = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).alwaysEat().build();
		public static final FoodProperties ALOE_GEL = new FoodProperties.Builder().nutrition(3).saturationMod(0.6F).alwaysEat().build();

		public static final FoodProperties CURRANT = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
		public static final FoodProperties CURRANT_MUFFIN = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();

		public static final FoodProperties DRAGON_FRUIT = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
		public static final FoodProperties GOLDEN_DRAGON_FRUIT = new FoodProperties.Builder().nutrition(8).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600), 1.0F).build();
		public static final FoodProperties ENDER_DRAGON_FRUIT = new FoodProperties.Builder().nutrition(20).saturationMod(1.2F).build();

		public static final FoodProperties ORANGE = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
		public static final FoodProperties ORANGE_PUDDING = new FoodProperties.Builder().nutrition(8).saturationMod(0.3F).build();
		public static final FoodProperties ORANGE_SORBET = new FoodProperties.Builder().nutrition(15).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 4, false, false, true), 1.0F).build();
		public static final FoodProperties CANDIED_ORANGE_SLICES = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).build();
		public static final FoodProperties BLOOD_ORANGE = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
	}
}
