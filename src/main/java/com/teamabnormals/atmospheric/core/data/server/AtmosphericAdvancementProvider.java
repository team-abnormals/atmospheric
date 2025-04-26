package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.AdvancementProvider.AdvancementGenerator;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AtmosphericAdvancementProvider implements AdvancementGenerator {

	public static AdvancementProvider create(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		return new AdvancementProvider(output, provider, helper, List.of(new AtmosphericAdvancementProvider()));
	}

	@Override
	public void generate(Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {
		AdvancementHolder dunesPricks = createAdvancement("dunes_pricks", "adventure", ResourceLocation.withDefaultNamespace("adventure/root"), AtmosphericBlocks.YUCCA_FLOWER.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("yucca_flower", AtmosphericCriteriaTriggers.yuccaPrick())
				.addCriterion("aloe_vera", AtmosphericCriteriaTriggers.aloeVeraPrick())
				.addCriterion("barrel_cactus", AtmosphericCriteriaTriggers.barrelCactusPrick())
				.save(consumer, Atmospheric.MOD_ID + ":adventure/dunes_pricks");
		createAdvancement("loot_arid_garden", "adventure", dunesPricks, AtmosphericBlocks.BARREL_CACTUS.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("loot_arid_garden", AtmosphericCriteriaTriggers.lootAridGarden())
				.save(consumer, Atmospheric.MOD_ID + ":adventure/loot_arid_garden");

		AdvancementHolder spitPassionFruit = createAdvancement("spit_passion_fruit", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/root"), AtmosphericItems.PASSION_FRUIT.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("spit_passion_fruit", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntityWithDamage(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(DamageTypeTags.IS_PROJECTILE)).direct(EntityPredicate.Builder.entity().of(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get())))))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/spit_passion_fruit");
		createAdvancement("kill_mob_with_passion_fruit", "husbandry", spitPassionFruit, AtmosphericItems.SHIMMERING_PASSION_FRUIT.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("kill_mob_with_passion_fruit", KilledTrigger.TriggerInstance.playerKilledEntity(Optional.empty(), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(DamageTypeTags.IS_PROJECTILE)).direct(EntityPredicate.Builder.entity().of(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get()))))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/kill_mob_with_passion_fruit");

		AdvancementHolder findOrange = createAdvancement("find_orange", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/root"), AtmosphericItems.ORANGE.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("find_orange", InventoryChangeTrigger.TriggerInstance.hasItems(AtmosphericItems.ORANGE.get()))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/find_orange");
		createAdvancement("obtain_blood_orange", "husbandry", findOrange, AtmosphericItems.BLOOD_ORANGE.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("obtain_blood_orange", InventoryChangeTrigger.TriggerInstance.hasItems(AtmosphericItems.BLOOD_ORANGE.get()))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/obtain_blood_orange");

		AdvancementHolder persistenceWhileStarving = createAdvancement("persistence_while_starving", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/root"), AtmosphericItems.ROASTED_YUCCA_FRUIT.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("persistence_while_starving", AtmosphericCriteriaTriggers.persistenceWhileStarving())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/persistence_while_starving");
		createAdvancement("finish_gateau", "husbandry", persistenceWhileStarving, AtmosphericBlocks.YUCCA_GATEAU.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("finish_gateau", AtmosphericCriteriaTriggers.finishGateau())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/finish_gateau");

		createAdvancement("put_out_fire", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/root"), AtmosphericItems.ALOE_LEAVES.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("put_out_fire", AtmosphericCriteriaTriggers.putOutFire())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/put_out_fire");

		createAdvancement("forbidden_fruit", "end", ResourceLocation.withDefaultNamespace("end/root"), AtmosphericItems.ENDER_DRAGON_FRUIT.get(), AdvancementType.GOAL, true, true, true)
				.addCriterion("forbidden_fruit", ConsumeItemTrigger.TriggerInstance.usedItem(AtmosphericItems.ENDER_DRAGON_FRUIT.get()))
				.save(consumer, Atmospheric.MOD_ID + ":end/forbidden_fruit");
	}

	private static Advancement.Builder createAdvancement(String name, String category, AdvancementHolder parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(parent).display(icon,
				Component.translatable("advancements." + Atmospheric.MOD_ID + "." + category + "." + name + ".title"),
				Component.translatable("advancements." + Atmospheric.MOD_ID + "." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return createAdvancement(name, category, Advancement.Builder.advancement().build(parent), icon, frame, showToast, announceToChat, hidden);
	}
}