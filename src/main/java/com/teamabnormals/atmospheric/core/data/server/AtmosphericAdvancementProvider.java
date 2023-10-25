package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class AtmosphericAdvancementProvider extends AdvancementProvider {

	public AtmosphericAdvancementProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, existingFileHelper);
	}

	@Override
	protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
		Advancement dunesPricks = createAdvancement("dunes_pricks", "adventure", new ResourceLocation("adventure/root"), AtmosphericBlocks.YUCCA_FLOWER.get(), FrameType.TASK, true, true, false)
				.addCriterion("yucca_flower", AtmosphericCriteriaTriggers.YUCCA_PRICK.createInstance())
				.addCriterion("aloe_vera", AtmosphericCriteriaTriggers.ALOE_VERA_PRICK.createInstance())
				.addCriterion("barrel_cactus", AtmosphericCriteriaTriggers.BARREL_CACTUS_PRICK.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":adventure/dunes_pricks");
		createAdvancement("loot_arid_garden", "adventure", dunesPricks, AtmosphericBlocks.BARREL_CACTUS.get(), FrameType.TASK, true, true, false)
				.addCriterion("loot_arid_garden", AtmosphericCriteriaTriggers.LOOT_ARID_GARDEN.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":adventure/loot_arid_garden");

		Advancement spitPassionFruit = createAdvancement("spit_passion_fruit", "husbandry", new ResourceLocation("husbandry/root"), AtmosphericItems.PASSION_FRUIT.get(), FrameType.TASK, true, true, false)
				.addCriterion("spit_passion_fruit", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().isProjectile(true).direct(EntityPredicate.Builder.entity().of(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get())))))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/spit_passion_fruit");
		createAdvancement("kill_mob_with_passion_fruit", "husbandry", spitPassionFruit, AtmosphericItems.SHIMMERING_PASSION_FRUIT.get(), FrameType.TASK, true, true, false)
				.addCriterion("kill_mob_with_passion_fruit", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.ANY, DamageSourcePredicate.Builder.damageType().isProjectile(true).direct(EntityPredicate.Builder.entity().of(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get()))))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/kill_mob_with_passion_fruit");

		Advancement findOrange = createAdvancement("find_orange", "husbandry", new ResourceLocation("husbandry/root"), AtmosphericItems.ORANGE.get(), FrameType.TASK, true, true, false)
				.addCriterion("find_orange", InventoryChangeTrigger.TriggerInstance.hasItems(AtmosphericItems.ORANGE.get()))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/find_orange");
		createAdvancement("obtain_blood_orange", "husbandry", findOrange, AtmosphericItems.BLOOD_ORANGE.get(), FrameType.TASK, true, true, false)
				.addCriterion("obtain_blood_orange", InventoryChangeTrigger.TriggerInstance.hasItems(AtmosphericItems.BLOOD_ORANGE.get()))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/obtain_blood_orange");

		Advancement persistenceWhileStarving = createAdvancement("persistence_while_starving", "husbandry", new ResourceLocation("husbandry/root"), AtmosphericItems.ROASTED_YUCCA_FRUIT.get(), FrameType.TASK, true, true, false)
				.addCriterion("persistence_while_starving", AtmosphericCriteriaTriggers.PERSISTENCE_WHILE_STARVING.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/persistence_while_starving");
		createAdvancement("finish_gateau", "husbandry", persistenceWhileStarving, AtmosphericBlocks.YUCCA_GATEAU.get(), FrameType.TASK, true, true, false)
				.addCriterion("finish_gateau", AtmosphericCriteriaTriggers.FINISH_GATEAU.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/finish_gateau");

		createAdvancement("put_out_fire", "husbandry", new ResourceLocation("husbandry/root"), AtmosphericItems.ALOE_LEAVES.get(), FrameType.TASK, true, true, false)
				.addCriterion("put_out_fire", AtmosphericCriteriaTriggers.PUT_OUT_FIRE.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/put_out_fire");

		createAdvancement("forbidden_fruit", "end", new ResourceLocation("end/root"), AtmosphericItems.ENDER_DRAGON_FRUIT.get(), FrameType.GOAL, true, true, true)
				.addCriterion("forbidden_fruit", ConsumeItemTrigger.TriggerInstance.usedItem(AtmosphericItems.ENDER_DRAGON_FRUIT.get()))
				.save(consumer, Atmospheric.MOD_ID + ":end/forbidden_fruit");
	}

	private static Advancement.Builder createAdvancement(String name, String category, Advancement parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(parent).display(icon,
				Component.translatable("advancements." + Atmospheric.MOD_ID + "." + category + "." + name + ".title"),
				Component.translatable("advancements." + Atmospheric.MOD_ID + "." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return createAdvancement(name, category, Advancement.Builder.advancement().build(parent), icon, frame, showToast, announceToChat, hidden);
	}
}