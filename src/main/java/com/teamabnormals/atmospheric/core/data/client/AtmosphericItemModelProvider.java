package com.teamabnormals.atmospheric.core.data.client;

import com.teamabnormals.atmospheric.common.entity.TetraVariant;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericItems.*;

public class AtmosphericItemModelProvider extends BlueprintItemModelProvider {

	public AtmosphericItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Atmospheric.MOD_ID, helper);
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

		this.generatedItem(AtmosphericBlocks.YUCCA_GATEAU);
		this.handheldItem(AtmosphericBlocks.YUCCA_BRANCH);
		this.generatedItem(
				PASSION_FRUIT, SHIMMERING_PASSION_FRUIT, PASSION_FRUIT_SORBET, PASSION_FRUIT_TART, PASSION_VINE_COIL,
				ALOE_GEL_BOTTLE, ALOE_KERNELS, ALOE_LEAVES, YELLOW_BLOSSOMS, YUCCA_FRUIT, ROASTED_YUCCA_FRUIT,
				CURRANT, CURRANT_MUFFIN,
				CARMINE_HUSK, COCHINEAL_BANNER_PATTERN, AtmosphericBlocks.DRAGON_ROOTS, DRAGON_FRUIT, GOLDEN_DRAGON_FRUIT, ENDER_DRAGON_FRUIT,
				ORANGE_PUDDING, ORANGE_SORBET, CANDIED_ORANGE_SLICES, BLOOD_ORANGE,
				SCYTHE_POTTERY_SHERD, SUCCULENT_POTTERY_SHERD, SUN_POTTERY_SHERD,
				APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE, DRUID_ARMOR_TRIM_SMITHING_TEMPLATE, PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE
		);

		this.spawnEggItem(TETRA_SPAWN_EGG, COCHINEAL_SPAWN_EGG);

		this.item(ORANGE, "generated").override().model(this.item(new ResourceLocation(Atmospheric.MOD_ID, "annoying_orange"), "generated")).predicate(new ResourceLocation(Atmospheric.MOD_ID, "hey_apple"), 1.0F);

		this.getBuilder(name(TETRA_BUCKET.get()));
		Arrays.stream(TetraVariant.class.getDeclaredFields()).forEach(field -> {
			if (Modifier.isStatic(field.getModifiers()) && ResourceKey.class.isAssignableFrom(field.getType())) {
				try {
					ResourceLocation location = ((ResourceKey<?>) field.get(null)).location().withPath(s -> "item/tetra_bucket/" + s + "_tetra_bucket");
					this.withExistingParent(location.getPath(), "item/generated").texture("layer0", location);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}