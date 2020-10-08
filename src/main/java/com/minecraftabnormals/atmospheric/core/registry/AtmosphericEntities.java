package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.common.entity.PassionfruitSeedEntity;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MODID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericEntities {
    public static final RegistryHelper HELPER = Atmospheric.REGISTRY_HELPER;

	public static final RegistryObject<EntityType<PassionfruitSeedEntity>> PASSIONFRUIT_SEED = HELPER.createEntity("passionfruit_seed", PassionfruitSeedEntity::new, PassionfruitSeedEntity::new, EntityClassification.MISC, 0.25F, 0.25F);
}
