package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
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

		createAdvancement("spit_passionfruit", "husbandry", new ResourceLocation("husbandry/plant_seed"), AtmosphericItems.PASSIONFRUIT.get(), FrameType.TASK, true, true, false)
				.addCriterion("spit_passionfruit", AtmosphericCriteriaTriggers.SPIT_PASSIONFRUIT.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/spit_passionfruit");

		createAdvancement("finish_gateau", "husbandry", new ResourceLocation("husbandry/plant_seed"), AtmosphericBlocks.YUCCA_GATEAU.get(), FrameType.TASK, true, true, false)
				.addCriterion("finish_gateau", AtmosphericCriteriaTriggers.SPIT_PASSIONFRUIT.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/finish_gateau");

		createAdvancement("put_out_fire", "husbandry", new ResourceLocation("husbandry/root"), AtmosphericItems.ALOE_LEAVES.get(), FrameType.TASK, true, true, false)
				.addCriterion("put_out_fire", AtmosphericCriteriaTriggers.SPIT_PASSIONFRUIT.createInstance())
				.save(consumer, Atmospheric.MOD_ID + ":husbandry/put_out_fire");
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon,
				new TranslatableComponent("advancements." + Atmospheric.MOD_ID + "." + category + "." + name + ".title"),
				new TranslatableComponent("advancements." + Atmospheric.MOD_ID + "." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}
}