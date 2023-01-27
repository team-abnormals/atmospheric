package com.teamabnormals.atmospheric.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.common.item.*;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.integration.boatload.AtmosphericBoatTypes;
import com.teamabnormals.blueprint.core.util.registry.AbstractSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericItems {
	public static final ItemSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> PASSIONFRUIT = HELPER.createItem("passionfruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> SHIMMERING_PASSIONFRUIT = HELPER.createItem("shimmering_passionfruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.SHIMMERING_PASSIONFRUIT).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> PASSIONFRUIT_TART = HELPER.createItem("passionfruit_tart", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT_TART).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> PASSIONFRUIT_SORBET = HELPER.createItem("passionfruit_sorbet", () -> new BowlFoodItem(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT_SORBET).stacksTo(1).craftRemainder(Items.BOWL).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> PASSION_VINE_COIL = HELPER.createItem("passion_vine_coil", () -> new PassionVineCoilItem(new Item.Properties().stacksTo(16).tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> YELLOW_BLOSSOMS = HELPER.createItem("yellow_blossoms", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> YUCCA_FRUIT = HELPER.createItem("yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.YUCCA_FRUIT).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ROASTED_YUCCA_FRUIT = HELPER.createItem("roasted_yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.ROASTED_YUCCA_FRUIT).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> YUCCA_GATEAU = HELPER.createItem("yucca_gateau", () -> new BlockItem(AtmosphericBlocks.YUCCA_GATEAU.get(), new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> ALOE_KERNELS = HELPER.createItem("aloe_kernels", () -> new ItemNameBlockItem(AtmosphericBlocks.ALOE_VERA.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> ALOE_LEAVES = HELPER.createItem("aloe_leaves", () -> new AloeLeavesItem(new Item.Properties().food(AtmosphericFoods.ALOE_LEAVES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ALOE_GEL_BOTTLE = HELPER.createItem("aloe_gel_bottle", () -> new AloeGelBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(AtmosphericFoods.ALOE_GEL).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> WATER_HYACINTH = HELPER.createItem("water_hyacinth", () -> new WaterHyacinthItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> BARREL_CACTUS = HELPER.createItem("barrel_cactus", () -> new BarrelCactusItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> ROSEWOOD_BOAT = HELPER.createBoatAndChestBoatItem("rosewood", AtmosphericBlocks.ROSEWOOD_PLANKS);
	public static final RegistryObject<Item> ROSEWOOD_FURNACE_BOAT = HELPER.createItem("rosewood_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.ROSEWOOD_FURNACE_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	public static final RegistryObject<Item> LARGE_ROSEWOOD_BOAT = HELPER.createItem("large_rosewood_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_ROSEWOOD_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> MORADO_BOAT = HELPER.createBoatAndChestBoatItem("morado", AtmosphericBlocks.MORADO_PLANKS);
	public static final RegistryObject<Item> MORADO_FURNACE_BOAT = HELPER.createItem("morado_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.MORADO_FURNACE_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	public static final RegistryObject<Item> LARGE_MORADO_BOAT = HELPER.createItem("large_morado_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_MORADO_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> YUCCA_BOAT = HELPER.createBoatAndChestBoatItem("yucca", AtmosphericBlocks.YUCCA_PLANKS);
	public static final RegistryObject<Item> YUCCA_FURNACE_BOAT = HELPER.createItem("yucca_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.YUCCA_FURNACE_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	public static final RegistryObject<Item> LARGE_YUCCA_BOAT = HELPER.createItem("large_yucca_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_YUCCA_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> KOUSA_BOAT = HELPER.createBoatAndChestBoatItem("kousa", AtmosphericBlocks.KOUSA_PLANKS);
	public static final RegistryObject<Item> KOUSA_FURNACE_BOAT = HELPER.createItem("kousa_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.KOUSA_FURNACE_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	public static final RegistryObject<Item> LARGE_KOUSA_BOAT = HELPER.createItem("large_kousa_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_KOUSA_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> ASPEN_BOAT = HELPER.createBoatAndChestBoatItem("aspen", AtmosphericBlocks.ASPEN_PLANKS);
	public static final RegistryObject<Item> ASPEN_FURNACE_BOAT = HELPER.createItem("aspen_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.ASPEN_FURNACE_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	public static final RegistryObject<Item> LARGE_ASPEN_BOAT = HELPER.createItem("large_aspen_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_ASPEN_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> GRIMWOOD_BOAT = HELPER.createBoatAndChestBoatItem("grimwood", AtmosphericBlocks.GRIMWOOD_PLANKS);
	public static final RegistryObject<Item> GRIMWOOD_FURNACE_BOAT = HELPER.createItem("grimwood_furnace_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.GRIMWOOD_FURNACE_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	public static final RegistryObject<Item> LARGE_GRIMWOOD_BOAT = HELPER.createItem("large_grimwood_boat", ModList.get().isLoaded("boatload") ? AtmosphericBoatTypes.LARGE_GRIMWOOD_BOAT : () -> new Item(new Item.Properties().tab(AbstractSubRegistryHelper.areModsLoaded("boatload") ? CreativeModeTab.TAB_TRANSPORTATION : null)));

	public static final class AtmosphericFoods {
		public static final FoodProperties PASSIONFRUIT = new FoodProperties.Builder().nutrition(1).saturationMod(0.0F).fast().alwaysEat().effect(() -> new MobEffectInstance(AtmosphericMobEffects.SPITTING.get(), 140, 0, false, false, false), 1.0F).build();
		public static final FoodProperties SHIMMERING_PASSIONFRUIT = new FoodProperties.Builder().nutrition(3).saturationMod(0.0F).fast().alwaysEat().effect(() -> new MobEffectInstance(AtmosphericMobEffects.SPITTING.get(), 140, 1, false, false, false), 1.0F).build();
		public static final FoodProperties PASSIONFRUIT_TART = new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).build();
		public static final FoodProperties PASSIONFRUIT_SORBET = new FoodProperties.Builder().nutrition(15).saturationMod(0.5F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 4, false, false, true), 1.0F).build();

		public static final FoodProperties YUCCA_FRUIT = new FoodProperties.Builder().nutrition(1).saturationMod(0.3F).build();
		public static final FoodProperties ROASTED_YUCCA_FRUIT = new FoodProperties.Builder().nutrition(6).saturationMod(0.5F).effect(() -> new MobEffectInstance(AtmosphericMobEffects.PERSISTENCE.get(), 560, 0, false, false, true), 1.0F).build();

		public static final FoodProperties ALOE_LEAVES = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).alwaysEat().build();
		public static final FoodProperties ALOE_GEL = new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).alwaysEat().build();
	}
}
