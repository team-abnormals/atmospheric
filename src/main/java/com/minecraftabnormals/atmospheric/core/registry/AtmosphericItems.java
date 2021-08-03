package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.minecraftabnormals.atmospheric.common.item.*;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericFoods;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericItems {
	public static final ItemSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> PASSIONFRUIT = HELPER.createItem("passionfruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT).tab(ItemGroup.TAB_FOOD)));
	public static final RegistryObject<Item> SHIMMERING_PASSIONFRUIT = HELPER.createItem("shimmering_passionfruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.SHIMMERING_PASSIONFRUIT).tab(ItemGroup.TAB_FOOD)));
	public static final RegistryObject<Item> PASSIONFRUIT_TART = HELPER.createItem("passionfruit_tart", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT_TART).tab(ItemGroup.TAB_FOOD)));
	public static final RegistryObject<Item> PASSIONFRUIT_SORBET = HELPER.createItem("passionfruit_sorbet", () -> new SoupItem(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT_SORBET).stacksTo(1).craftRemainder(Items.BOWL).tab(ItemGroup.TAB_FOOD)));
	public static final RegistryObject<Item> PASSION_VINE_COIL = HELPER.createItem("passion_vine_coil", () -> new PassionVineCoilItem(new Item.Properties().stacksTo(16).tab(ItemGroup.TAB_MISC)));

	public static final RegistryObject<Item> YELLOW_BLOSSOMS = HELPER.createItem("yellow_blossoms", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

	public static final RegistryObject<Item> YUCCA_FRUIT = HELPER.createItem("yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.YUCCA_FRUIT).tab(ItemGroup.TAB_FOOD)));
	public static final RegistryObject<Item> ROASTED_YUCCA_FRUIT = HELPER.createItem("roasted_yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.ROASTED_YUCCA_FRUIT).tab(ItemGroup.TAB_FOOD)));
	public static final RegistryObject<Item> YUCCA_GATEAU = HELPER.createItem("yucca_gateau", () -> new BlockItem(AtmosphericBlocks.YUCCA_GATEAU.get(), new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_FOOD)));
	public static final RegistryObject<Item> YUCCA_JUICE = HELPER.createItem("yucca_juice", () -> new YuccaJuiceItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(AtmosphericFoods.YUCCA_JUICE).stacksTo(16).tab(ItemGroup.TAB_FOOD)));

	public static final RegistryObject<Item> ALOE_KERNELS = HELPER.createItem("aloe_kernels", () -> new BlockNamedItem(AtmosphericBlocks.ALOE_VERA.get(), new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> ALOE_LEAVES = HELPER.createItem("aloe_leaves", () -> new AloeLeavesItem(new Item.Properties().food(AtmosphericFoods.ALOE_LEAVES).tab(ItemGroup.TAB_FOOD)));
	public static final RegistryObject<Item> ALOE_GEL_BOTTLE = HELPER.createItem("aloe_gel_bottle", () -> new AloeGelBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(AtmosphericFoods.ALOE_GEL).stacksTo(16).tab(ItemGroup.TAB_FOOD)));

	public static final RegistryObject<Item> WATER_HYACINTH = HELPER.createItem("water_hyacinth", () -> new WaterHyacinthItem(new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));

	public static final RegistryObject<Item> ROSEWOOD_BOAT = HELPER.createBoatItem("rosewood", AtmosphericBlocks.ROSEWOOD_PLANKS);
	public static final RegistryObject<Item> MORADO_BOAT = HELPER.createBoatItem("morado", AtmosphericBlocks.MORADO_PLANKS);
	public static final RegistryObject<Item> YUCCA_BOAT = HELPER.createBoatItem("yucca", AtmosphericBlocks.YUCCA_PLANKS);
	public static final RegistryObject<Item> KOUSA_BOAT = HELPER.createBoatItem("kousa", AtmosphericBlocks.KOUSA_PLANKS);
	public static final RegistryObject<Item> ASPEN_BOAT = HELPER.createBoatItem("aspen", AtmosphericBlocks.ASPEN_PLANKS);
	public static final RegistryObject<Item> GRIMWOOD_BOAT = HELPER.createBoatItem("grimwood", AtmosphericBlocks.GRIMWOOD_PLANKS);
}
