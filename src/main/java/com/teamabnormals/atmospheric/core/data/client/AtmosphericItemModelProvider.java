package com.teamabnormals.atmospheric.core.data.client;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericItems.*;

public class AtmosphericItemModelProvider extends ItemModelProvider {

	public AtmosphericItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Atmospheric.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		this.generatedItem(
				ROSEWOOD_BOAT.getFirst(), ROSEWOOD_BOAT.getSecond(), ROSEWOOD_FURNACE_BOAT, LARGE_ROSEWOOD_BOAT,
				MORADO_BOAT.getFirst(), MORADO_BOAT.getSecond(), MORADO_FURNACE_BOAT, LARGE_MORADO_BOAT,
				YUCCA_BOAT.getFirst(), YUCCA_BOAT.getSecond(), YUCCA_FURNACE_BOAT, LARGE_YUCCA_BOAT,
				ASPEN_BOAT.getFirst(), ASPEN_BOAT.getSecond(), ASPEN_FURNACE_BOAT, LARGE_ASPEN_BOAT,
				LAUREL_BOAT.getFirst(), LAUREL_BOAT.getSecond(), LAUREL_FURNACE_BOAT, LARGE_LAUREL_BOAT,
				KOUSA_BOAT.getFirst(), KOUSA_BOAT.getSecond(), KOUSA_FURNACE_BOAT, LARGE_KOUSA_BOAT,
				GRIMWOOD_BOAT.getFirst(), GRIMWOOD_BOAT.getSecond(), GRIMWOOD_FURNACE_BOAT, LARGE_GRIMWOOD_BOAT
		);

		this.generatedItem(AtmosphericBlocks.PASSION_VINE.get(), AtmosphericBlocks.YUCCA_GATEAU.get());
		this.handheldItem(AtmosphericBlocks.YUCCA_BRANCH.get());
		this.generatedItem(
				PASSIONFRUIT, SHIMMERING_PASSIONFRUIT, PASSIONFRUIT_SORBET, PASSIONFRUIT_TART, PASSION_VINE_COIL,
				ALOE_GEL_BOTTLE, ALOE_KERNELS, ALOE_LEAVES, YELLOW_BLOSSOMS, YUCCA_FRUIT, ROASTED_YUCCA_FRUIT,
				CURRANT
		);
	}

	private void generatedItem(RegistryObject<Item>... items) {
		for (RegistryObject<Item> item : items)
			generatedItem(item.get());
	}

	private void generatedItem(ItemLike... items) {
		for (ItemLike item : items)
			item(item, "generated");
	}

	private void handheldItem(ItemLike... items) {
		for (ItemLike item : items)
			item(item, "handheld");
	}

	private void item(ItemLike item, String type) {
		ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item.asItem());
		withExistingParent(itemName.getPath(), "item/" + type).texture("layer0", new ResourceLocation(this.modid, "item/" + itemName.getPath()));
	}

	private void spawnEggItem(ItemLike... items) {
		for (ItemLike item : items) {
			ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item.asItem());
			withExistingParent(itemName.getPath(), "item/template_spawn_egg");
		}
	}

	private void blockItem(Block block) {
		ResourceLocation name = ForgeRegistries.BLOCKS.getKey(block);
		this.getBuilder(name.getPath()).parent(new UncheckedModelFile(new ResourceLocation(this.modid, "block/" + name.getPath())));
	}
}