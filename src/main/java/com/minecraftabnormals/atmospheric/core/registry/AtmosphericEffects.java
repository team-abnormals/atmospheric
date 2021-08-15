package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.common.potion.PersistenceEffect;
import com.minecraftabnormals.atmospheric.common.potion.ReliefEffect;
import com.minecraftabnormals.atmospheric.common.potion.SpittingEffect;
import com.minecraftabnormals.atmospheric.common.potion.WorseningEffect;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericEffects {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Atmospheric.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Atmospheric.MOD_ID);

	public static final RegistryObject<Effect> RELIEF = EFFECTS.register("relief", ReliefEffect::new);
	public static final RegistryObject<Effect> WORSENING = EFFECTS.register("worsening", WorseningEffect::new);
	public static final RegistryObject<Effect> SPITTING = EFFECTS.register("spitting", SpittingEffect::new);
	public static final RegistryObject<Effect> PERSISTENCE = EFFECTS.register("persistence", () -> new PersistenceEffect().addAttributeModifier(Attributes.MOVEMENT_SPEED, "7A8BEE59-3D67-4D88-8223-81A15A706AE9", 0.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));

	public static final RegistryObject<Potion> RELIEF_NORMAL = POTIONS.register("relief", () -> new Potion(new EffectInstance(RELIEF.get(), 3600)));
	public static final RegistryObject<Potion> RELIEF_STRONG = POTIONS.register("relief_strong", () -> new Potion(new EffectInstance(RELIEF.get(), 1800, 1)));
	public static final RegistryObject<Potion> RELIEF_LONG = POTIONS.register("relief_long", () -> new Potion(new EffectInstance(RELIEF.get(), 9600)));
	public static final RegistryObject<Potion> WORSENING_NORMAL = POTIONS.register("worsening", () -> new Potion(new EffectInstance(WORSENING.get(), 3600)));
	public static final RegistryObject<Potion> WORSENING_STRONG = POTIONS.register("worsening_strong", () -> new Potion(new EffectInstance(WORSENING.get(), 1800, 1)));
	public static final RegistryObject<Potion> WORSENING_LONG = POTIONS.register("worsening_long", () -> new Potion(new EffectInstance(WORSENING.get(), 9600)));

	public static void registerBrewingRecipes() {
		PotionBrewing.addMix(Potions.AWKWARD, AtmosphericItems.ALOE_LEAVES.get(), RELIEF_NORMAL.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.GLOWSTONE_DUST, RELIEF_STRONG.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.REDSTONE, RELIEF_LONG.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.FERMENTED_SPIDER_EYE, WORSENING_NORMAL.get());
		PotionBrewing.addMix(WORSENING_NORMAL.get(), Items.GLOWSTONE_DUST, WORSENING_STRONG.get());
		PotionBrewing.addMix(WORSENING_NORMAL.get(), Items.REDSTONE, WORSENING_LONG.get());
	}
}
