package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class AtmosphericTreeGrowers {
	public static final TreeGrower ASPEN = new TreeGrower("atmospheric:aspen", Optional.empty(), Optional.of(AtmosphericConfiguredFeatures.ASPEN), Optional.of(AtmosphericConfiguredFeatures.ASPEN_BEES_005));
	public static final TreeGrower GREEN_ASPEN = new TreeGrower("atmospheric:green_aspen", Optional.empty(), Optional.of(AtmosphericConfiguredFeatures.GREEN_ASPEN), Optional.of(AtmosphericConfiguredFeatures.GREEN_ASPEN_BEES_005));
	public static final TreeGrower CURRANT = new TreeGrower("atmospheric:currant", Optional.empty(), Optional.of(AtmosphericConfiguredFeatures.CURRANT), Optional.empty());
	public static final TreeGrower GRIMWOOD = new TreeGrower("atmospheric:grimwood", Optional.of(AtmosphericConfiguredFeatures.GRIMWOOD), Optional.empty(), Optional.empty());
	public static final TreeGrower KOUSA = new TreeGrower("atmospheric:kousa", 0.1F, Optional.empty(), Optional.empty(), Optional.of(AtmosphericConfiguredFeatures.KOUSA), Optional.of(AtmosphericConfiguredFeatures.BABY_KOUSA), Optional.empty(), Optional.empty());
	public static final TreeGrower ROSEWOOD = new TreeGrower("atmospheric:rosewood", Optional.empty(), Optional.of(AtmosphericConfiguredFeatures.ROSEWOOD), Optional.of(AtmosphericConfiguredFeatures.ROSEWOOD_BEES_005));
	public static final TreeGrower MORADO = new TreeGrower("atmospheric:morado", Optional.empty(), Optional.of(AtmosphericConfiguredFeatures.MORADO), Optional.of(AtmosphericConfiguredFeatures.MORADO_BEES_005));
	public static final TreeGrower YUCCA = new TreeGrower("atmospheric:yucca", 0.1F, Optional.empty(), Optional.empty(), Optional.of(AtmosphericConfiguredFeatures.YUCCA), Optional.of(AtmosphericConfiguredFeatures.BABY_YUCCA), Optional.of(AtmosphericConfiguredFeatures.YUCCA_BEES_005), Optional.empty());

	public static final TreeGrower LAUREL = new TreeGrower("atmospheric:laurel", 0.3F, Optional.of(AtmosphericConfiguredFeatures.LARGE_LAUREL), Optional.of(AtmosphericConfiguredFeatures.GIANT_LAUREL), Optional.of(AtmosphericConfiguredFeatures.LAUREL), Optional.empty(), Optional.empty(), Optional.empty());
	public static final TreeGrower LAUREL_ORANGES = new TreeGrower("atmospheric:laurel_oranges", 0.3F, Optional.of(AtmosphericConfiguredFeatures.LARGE_LAUREL_ORANGES_08), Optional.of(AtmosphericConfiguredFeatures.GIANT_LAUREL_ORANGES_08), Optional.of(AtmosphericConfiguredFeatures.LAUREL_ORANGES_08), Optional.empty(), Optional.empty(), Optional.empty());
	public static final TreeGrower LAUREL_BLOOD_ORANGES = new TreeGrower("atmospheric:laurel_blood_oranges", 0.3F, Optional.of(AtmosphericConfiguredFeatures.LARGE_LAUREL_BLOOD_ORANGES_08), Optional.of(AtmosphericConfiguredFeatures.GIANT_LAUREL_BLOOD_ORANGES_08), Optional.of(AtmosphericConfiguredFeatures.LAUREL_BLOOD_ORANGES_08), Optional.empty(), Optional.empty(), Optional.empty());
	public static final TreeGrower DRY_LAUREL = new TreeGrower("atmospheric:dry_laurel", 0.3F, Optional.of(AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL), Optional.of(AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL), Optional.of(AtmosphericConfiguredFeatures.DRY_LAUREL), Optional.empty(), Optional.empty(), Optional.empty());
	public static final TreeGrower DRY_LAUREL_ORANGES = new TreeGrower("atmospheric:dry_laurel_oranges", 0.3F, Optional.of(AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL_ORANGES_08), Optional.of(AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL_ORANGES_08), Optional.of(AtmosphericConfiguredFeatures.DRY_LAUREL_ORANGES_08), Optional.empty(), Optional.empty(), Optional.empty());
	public static final TreeGrower DRY_LAUREL_BLOOD_ORANGES = new TreeGrower("atmospheric:dry_laurel_blood_oranes", 0.3F, Optional.of(AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL_BLOOD_ORANGES_08), Optional.of(AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL_BLOOD_ORANGES_08), Optional.of(AtmosphericConfiguredFeatures.DRY_LAUREL_BLOOD_ORANGES_08), Optional.empty(), Optional.empty(), Optional.empty());
}
