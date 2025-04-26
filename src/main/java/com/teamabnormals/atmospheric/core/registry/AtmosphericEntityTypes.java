package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.OrangeVaporCloud;
import com.teamabnormals.atmospheric.common.entity.Tetra;
import com.teamabnormals.atmospheric.common.entity.projectile.DragonFruit;
import com.teamabnormals.atmospheric.common.entity.projectile.PassionFruitSeed;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericEntityTypes {
	public static final EntitySubRegistryHelper ENTITY_TYPES = Atmospheric.REGISTRY_HELPER.getEntitySubHelper();

	public static final DeferredHolder<EntityType<?>, EntityType<Tetra>> TETRA = ENTITY_TYPES.createEntity("tetra", Tetra::new, MobCategory.WATER_AMBIENT, 0.3F, 0.2F);
	public static final DeferredHolder<EntityType<?>, EntityType<Cochineal>> COCHINEAL = ENTITY_TYPES.createEntity("cochineal", Cochineal::new, MobCategory.CREATURE, 1.3F, 1.1F);
	public static final DeferredHolder<EntityType<?>, EntityType<PassionFruitSeed>> PASSION_FRUIT_SEED = ENTITY_TYPES.createEntity("passion_fruit_seed", PassionFruitSeed::new, MobCategory.MISC, 0.25F, 0.25F);
	public static final DeferredHolder<EntityType<?>, EntityType<DragonFruit>> DRAGON_FRUIT = ENTITY_TYPES.createEntity("dragon_fruit", DragonFruit::new, MobCategory.MISC, 0.375F, 0.375F);
	public static final DeferredHolder<EntityType<?>, EntityType<OrangeVaporCloud>> ORANGE_VAPOR_CLOUD = ENTITY_TYPES.getDeferredRegister().register("orange_vapor_cloud", () -> EntityType.Builder.<OrangeVaporCloud>of(OrangeVaporCloud::new, MobCategory.MISC).fireImmune().sized(3.0F, 3.0F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("atmospheric:orange_vapor_cloud"));

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(TETRA.get(), Tetra.createAttributes().build());
		event.put(COCHINEAL.get(), Cochineal.createAttributes().build());
	}

	@SubscribeEvent
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(TETRA.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Tetra::checkTetraSpawnRules, Operation.AND);
		event.register(COCHINEAL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, Operation.AND);
	}
}
