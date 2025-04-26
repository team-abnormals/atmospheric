package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger.TriggerInstance;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;

public class AtmosphericCriteriaTriggers {
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Atmospheric.MOD_ID);

	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> FINISH_GATEAU = TRIGGERS.register("finish_gateau", PlayerTrigger::new);

	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> PUT_OUT_FIRE = TRIGGERS.register("put_out_fire", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> LOOT_ARID_GARDEN = TRIGGERS.register("loot_arid_garden", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> PERSISTENCE_WHILE_STARVING = TRIGGERS.register("persistence_while_starving", PlayerTrigger::new);

	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> BARREL_CACTUS_PRICK = TRIGGERS.register("barrel_cactus_prick", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> ALOE_VERA_PRICK = TRIGGERS.register("aloe_vera_prick", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> YUCCA_PRICK = TRIGGERS.register("yucca_prick", PlayerTrigger::new);

	public static Criterion<TriggerInstance> finishGateau() {
		return FINISH_GATEAU.get().createCriterion(new PlayerTrigger.TriggerInstance(EntityPredicate.wrap(Optional.empty())));
	}

	public static Criterion<TriggerInstance> putOutFire() {
		return PUT_OUT_FIRE.get().createCriterion(new PlayerTrigger.TriggerInstance(EntityPredicate.wrap(Optional.empty())));
	}

	public static Criterion<TriggerInstance> lootAridGarden() {
		return LOOT_ARID_GARDEN.get().createCriterion(new PlayerTrigger.TriggerInstance(EntityPredicate.wrap(Optional.empty())));
	}

	public static Criterion<TriggerInstance> persistenceWhileStarving() {
		return PERSISTENCE_WHILE_STARVING.get().createCriterion(new PlayerTrigger.TriggerInstance(EntityPredicate.wrap(Optional.empty())));
	}

	public static Criterion<TriggerInstance> barrelCactusPrick() {
		return BARREL_CACTUS_PRICK.get().createCriterion(new PlayerTrigger.TriggerInstance(EntityPredicate.wrap(Optional.empty())));
	}

	public static Criterion<TriggerInstance> aloeVeraPrick() {
		return ALOE_VERA_PRICK.get().createCriterion(new PlayerTrigger.TriggerInstance(EntityPredicate.wrap(Optional.empty())));
	}

	public static Criterion<TriggerInstance> yuccaPrick() {
		return YUCCA_PRICK.get().createCriterion(new PlayerTrigger.TriggerInstance(EntityPredicate.wrap(Optional.empty())));
	}
}
