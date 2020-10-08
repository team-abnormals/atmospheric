package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.common.item.AloeGelBottleItem;
import com.minecraftabnormals.atmospheric.common.item.AloeLeavesItem;
import com.minecraftabnormals.atmospheric.common.item.PassionVineCoilItem;
import com.minecraftabnormals.atmospheric.common.item.YuccaJuiceItem;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericFoods;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SoupItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericItems {
	public static final RegistryHelper HELPER = Atmospheric.REGISTRY_HELPER;	
	
	public static final RegistryObject<Item> PASSIONFRUIT				= HELPER.createItem("passionfruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> SHIMMERING_PASSIONFRUIT 	= HELPER.createItem("shimmering_passionfruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.SHIMMERING_PASSIONFRUIT).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> PASSIONFRUIT_TART 			= HELPER.createItem("passionfruit_tart", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT_TART).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> PASSIONFRUIT_SORBET 		= HELPER.createItem("passionfruit_sorbet", () -> new SoupItem(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT_SORBET).maxStackSize(1).containerItem(Items.BOWL).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> PASSION_VINE_COIL			= HELPER.createItem("passion_vine_coil", () -> new PassionVineCoilItem(new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)));
	
	public static final RegistryObject<Item> YUCCA_FRUIT			= HELPER.createItem("yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.YUCCA_FRUIT).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> ROASTED_YUCCA_FRUIT	= HELPER.createItem("roasted_yucca_fruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.ROASTED_YUCCA_FRUIT).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> YUCCA_GATEAU			= HELPER.createItem("yucca_gateau", () -> new BlockItem(AtmosphericBlocks.YUCCA_GATEAU.get(), new Item.Properties().maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> YUCCA_JUICE			= HELPER.createItem("yucca_juice", () -> new YuccaJuiceItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).food(AtmosphericFoods.YUCCA_JUICE).maxStackSize(16).group(ItemGroup.FOOD)));

	public static final RegistryObject<Item> ALOE_KERNELS		= HELPER.createItem("aloe_kernels", () -> new BlockNamedItem(AtmosphericBlocks.ALOE_VERA.get(), new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> ALOE_LEAVES		= HELPER.createItem("aloe_leaves", () -> new AloeLeavesItem(new Item.Properties().food(AtmosphericFoods.ALOE_LEAVES).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> ALOE_GEL_BOTTLE	= HELPER.createItem("aloe_gel_bottle", () -> new AloeGelBottleItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).food(AtmosphericFoods.ALOE_GEL).maxStackSize(16).group(ItemGroup.FOOD)));
	
	public static final RegistryObject<Item> ROSEWOOD_BOAT 	= HELPER.createBoatItem("rosewood", AtmosphericBlocks.ROSEWOOD_PLANKS);
	public static final RegistryObject<Item> YUCCA_BOAT 	= HELPER.createBoatItem("yucca", AtmosphericBlocks.YUCCA_PLANKS);
	public static final RegistryObject<Item> KOUSA_BOAT 	= HELPER.createBoatItem("kousa", AtmosphericBlocks.KOUSA_PLANKS);
	public static final RegistryObject<Item> ASPEN_BOAT 	= HELPER.createBoatItem("aspen", AtmosphericBlocks.ASPEN_PLANKS);
	public static final RegistryObject<Item> GRIMWOOD_BOAT 	= HELPER.createBoatItem("grimwood", AtmosphericBlocks.GRIMWOOD_PLANKS);
}
