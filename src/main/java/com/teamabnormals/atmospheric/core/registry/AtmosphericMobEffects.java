package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.effect.PersistenceEffect;
import com.teamabnormals.atmospheric.common.effect.SpittingEffect;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericMobEffects {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Atmospheric.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Atmospheric.MOD_ID);

	public static final DeferredHolder<MobEffect, MobEffect> RELIEF = EFFECTS.register("relief", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 15494786));
	public static final DeferredHolder<MobEffect, MobEffect> WORSENING = EFFECTS.register("worsening", () -> new BlueprintMobEffect(MobEffectCategory.HARMFUL, 3110759));
	public static final DeferredHolder<MobEffect, MobEffect> SPITTING = EFFECTS.register("spitting", SpittingEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> PERSISTENCE = EFFECTS.register("persistence", PersistenceEffect::new);

	public static final DeferredHolder<Potion, Potion> RELIEF_NORMAL = POTIONS.register("relief", () -> new Potion(new MobEffectInstance(RELIEF, 3600)));
	public static final DeferredHolder<Potion, Potion> RELIEF_STRONG = POTIONS.register("relief_strong", () -> new Potion(new MobEffectInstance(RELIEF, 1800, 1)));
	public static final DeferredHolder<Potion, Potion> RELIEF_LONG = POTIONS.register("relief_long", () -> new Potion(new MobEffectInstance(RELIEF, 9600)));
	public static final DeferredHolder<Potion, Potion> WORSENING_NORMAL = POTIONS.register("worsening", () -> new Potion(new MobEffectInstance(WORSENING, 3600)));
	public static final DeferredHolder<Potion, Potion> WORSENING_STRONG = POTIONS.register("worsening_strong", () -> new Potion(new MobEffectInstance(WORSENING, 1800, 1)));
	public static final DeferredHolder<Potion, Potion> WORSENING_LONG = POTIONS.register("worsening_long", () -> new Potion(new MobEffectInstance(WORSENING, 9600)));

	@SubscribeEvent
	public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
		PotionBrewing.Builder builder = event.getBuilder();
		builder.addMix(Potions.AWKWARD, AtmosphericItems.ALOE_LEAVES.get(), RELIEF_NORMAL);
		builder.addMix(RELIEF_NORMAL, Items.GLOWSTONE_DUST, RELIEF_STRONG);
		builder.addMix(RELIEF_NORMAL, Items.REDSTONE, RELIEF_LONG);
		builder.addMix(RELIEF_NORMAL, Items.FERMENTED_SPIDER_EYE, WORSENING_NORMAL);
		builder.addMix(WORSENING_NORMAL, Items.GLOWSTONE_DUST, WORSENING_STRONG);
		builder.addMix(WORSENING_NORMAL, Items.REDSTONE, WORSENING_LONG);
	}
}
