package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.entity.RosewoodBoatEntity;
import com.bagel.atmospheric.common.items.RosewoodBoatItem;
import com.bagel.atmospheric.core.AtmosphericExpansion;
import com.bagel.atmospheric.core.util.RegistryUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SoupItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AtmosphericItems {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, AtmosphericExpansion.MODID);
	
	public static RegistryObject<Item> PASSIONFRUIT = RegistryUtils.createItem("passionfruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT).group(ItemGroup.FOOD)));
	public static RegistryObject<Item> SHIMMERING_PASSIONFRUIT = RegistryUtils.createItem("shimmering_passionfruit", () -> new Item(new Item.Properties().food(AtmosphericFoods.SHIMMERING_PASSIONFRUIT).group(ItemGroup.FOOD)));
	public static RegistryObject<Item> PASSIONFRUIT_TART = RegistryUtils.createItem("passionfruit_tart", () -> new Item(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT_TART).group(ItemGroup.FOOD)));
	public static RegistryObject<Item> PASSIONFRUIT_SORBET = RegistryUtils.createItem("passionfruit_sorbet", () -> new SoupItem(new Item.Properties().food(AtmosphericFoods.PASSIONFRUIT_SORBET).maxStackSize(1).containerItem(Items.BOWL).group(ItemGroup.FOOD)));
	public static RegistryObject<Item> ROSEWOOD_BOAT = RegistryUtils.createItem("rosewood_boat", () -> new RosewoodBoatItem(RosewoodBoatEntity.Type.ROSEWOOD, new Item.Properties().group(ItemGroup.TRANSPORTATION)));

	public static RegistryObject<Item> PASSIONFRUIT_SEED = RegistryUtils.createItem("passionfruit_seed", () -> new Item(new Item.Properties()));
}
