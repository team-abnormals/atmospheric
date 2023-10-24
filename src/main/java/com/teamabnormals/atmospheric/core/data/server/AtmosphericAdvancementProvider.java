package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriteriaTriggers;
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
		createAdvancement("dunes_pricks", "adventure", new ResourceLocation("adventure/root"), AtmosphericBlocks.YUCCA_FLOWER.get(), FrameType.GOAL, true, true, false)
				.addCriterion("yucca_flower", AtmosphericCriteriaTriggers.YUCCA_FLOWER_PRICK.createInstance())
				.addCriterion("yucca_leaves", AtmosphericCriteriaTriggers.YUCCA_LEAVES_PRICK.createInstance())
				.addCriterion("yucca_branch", AtmosphericCriteriaTriggers.YUCCA_BRANCH_PRICK.createInstance())
				.addCriterion("aloe_vera", AtmosphericCriteriaTriggers.ALOE_VERA_PRICK.createInstance())
				.addCriterion("barrel_cactus", AtmosphericCriteriaTriggers.BARREL_CACTUS_PRICK.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":adventure/dunes_pricks");

		createAdvancement("spit_passion_fruit", "husbandry", new ResourceLocation("husbandry/plant_seed"), AtmosphericItems.PASSION_FRUIT.get(), FrameType.TASK, true, true, false)
				.addCriterion("spit_passion_fruit", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().isProjectile(true).direct(EntityPredicate.Builder.entity().of(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get())))))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/spit_passion_fruit");

		createAdvancement("finish_gateau", "husbandry", new ResourceLocation("husbandry/plant_seed"), AtmosphericBlocks.YUCCA_GATEAU.get(), FrameType.TASK, true, true, false)
				.addCriterion("finish_gateau", AtmosphericCriteriaTriggers.FINISH_GATEAU.createInstance())
				//.addCriterion("finish_gateau", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(AtmosphericBlocks.YUCCA_GATEAU.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(YuccaGateauBlock.BITES, 9).build()).build()), ItemPredicate.Builder.item()))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/finish_gateau");

		createAdvancement("put_out_fire", "husbandry", new ResourceLocation("husbandry/root"), AtmosphericItems.ALOE_LEAVES.get(), FrameType.TASK, true, true, false)
				.addCriterion("on_fire", new PlayerTrigger.TriggerInstance(CriteriaTriggers.TICK.getId(), EntityPredicate.Composite.wrap(EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()).build())))
				.addCriterion("consume_aloe_leaves", ConsumeItemTrigger.TriggerInstance.usedItem(AtmosphericItems.ALOE_LEAVES.get()))
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/put_out_fire");
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon,
				Component.translatable("advancements." + Atmospheric.MOD_ID + "." + category + "." + name + ".title"),
				Component.translatable("advancements." + Atmospheric.MOD_ID + "." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}
}