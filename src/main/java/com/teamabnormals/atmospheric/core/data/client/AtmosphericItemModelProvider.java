package com.teamabnormals.atmospheric.core.data.client;

import com.teamabnormals.atmospheric.common.entity.TetraVariant;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
				AtmosphericItems.ROSEWOOD_BOAT, AtmosphericItems.ROSEWOOD_CHEST_BOAT, ROSEWOOD_FURNACE_BOAT, LARGE_ROSEWOOD_BOAT,
				MORADO_BOAT, MORADO_CHEST_BOAT, MORADO_FURNACE_BOAT, LARGE_MORADO_BOAT,
				YUCCA_BOAT, YUCCA_CHEST_BOAT, YUCCA_FURNACE_BOAT, LARGE_YUCCA_BOAT,
				ASPEN_BOAT, ASPEN_CHEST_BOAT, ASPEN_FURNACE_BOAT, LARGE_ASPEN_BOAT,
				LAUREL_BOAT, LAUREL_CHEST_BOAT, LAUREL_FURNACE_BOAT, LARGE_LAUREL_BOAT,
				KOUSA_BOAT, KOUSA_CHEST_BOAT, KOUSA_FURNACE_BOAT, LARGE_KOUSA_BOAT,
				GRIMWOOD_BOAT, GRIMWOOD_CHEST_BOAT, GRIMWOOD_FURNACE_BOAT, LARGE_GRIMWOOD_BOAT
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

		this.item(ORANGE, "generated").override().model(this.item(Atmospheric.location("annoying_orange"), "generated")).predicate(Atmospheric.location("hey_apple"), 1.0F);

		this.getBuilder(name(TETRA_BUCKET.get()));
		Arrays.stream(TetraVariant.class.getDeclaredFields()).forEach(field -> {
			if (Modifier.isStatic(field.getModifiers()) && ResourceKey.class.isAssignableFrom(field.getType())) {
				try {
					ResourceLocation location = ((ResourceKey<?>) field.get(null)).location().withPath(s -> "item/tetra_bucket/" + s);
					this.withExistingParent(location.getPath(), "item/generated").texture("layer0", location);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}