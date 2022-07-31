package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.common.potion.PersistenceEffect;
import com.minecraftabnormals.atmospheric.common.potion.ReliefEffect;
import com.minecraftabnormals.atmospheric.common.potion.SpittingEffect;
import com.minecraftabnormals.atmospheric.common.potion.WorseningEffect;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericMobEffects {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Atmospheric.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Atmospheric.MOD_ID);

	public static final RegistryObject<MobEffect> RELIEF = EFFECTS.register("relief", ReliefEffect::new);
	public static final RegistryObject<MobEffect> WORSENING = EFFECTS.register("worsening", WorseningEffect::new);
	public static final RegistryObject<MobEffect> SPITTING = EFFECTS.register("spitting", SpittingEffect::new);
	public static final RegistryObject<MobEffect> PERSISTENCE = EFFECTS.register("persistence", () -> new PersistenceEffect().addAttributeModifier(Attributes.MOVEMENT_SPEED, "7A8BEE59-3D67-4D88-8223-81A15A706AE9", 0.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));

	public static final RegistryObject<Potion> RELIEF_NORMAL = POTIONS.register("relief", () -> new Potion(new MobEffectInstance(RELIEF.get(), 3600)));
	public static final RegistryObject<Potion> RELIEF_STRONG = POTIONS.register("relief_strong", () -> new Potion(new MobEffectInstance(RELIEF.get(), 1800, 1)));
	public static final RegistryObject<Potion> RELIEF_LONG = POTIONS.register("relief_long", () -> new Potion(new MobEffectInstance(RELIEF.get(), 9600)));
	public static final RegistryObject<Potion> WORSENING_NORMAL = POTIONS.register("worsening", () -> new Potion(new MobEffectInstance(WORSENING.get(), 3600)));
	public static final RegistryObject<Potion> WORSENING_STRONG = POTIONS.register("worsening_strong", () -> new Potion(new MobEffectInstance(WORSENING.get(), 1800, 1)));
	public static final RegistryObject<Potion> WORSENING_LONG = POTIONS.register("worsening_long", () -> new Potion(new MobEffectInstance(WORSENING.get(), 9600)));

	public static void registerBrewingRecipes() {
		DataUtil.addMix(Potions.AWKWARD, AtmosphericItems.ALOE_LEAVES.get(), RELIEF_NORMAL.get());
		DataUtil.addMix(RELIEF_NORMAL.get(), Items.GLOWSTONE_DUST, RELIEF_STRONG.get());
		DataUtil.addMix(RELIEF_NORMAL.get(), Items.REDSTONE, RELIEF_LONG.get());
		DataUtil.addMix(RELIEF_NORMAL.get(), Items.FERMENTED_SPIDER_EYE, WORSENING_NORMAL.get());
		DataUtil.addMix(WORSENING_NORMAL.get(), Items.GLOWSTONE_DUST, WORSENING_STRONG.get());
		DataUtil.addMix(WORSENING_NORMAL.get(), Items.REDSTONE, WORSENING_LONG.get());
	}
}
