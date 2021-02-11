package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.common.world.gen.structure.AridShrinePieces;
import com.minecraftabnormals.atmospheric.common.world.gen.structure.AridShrineStructure;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericStructures {
	public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Atmospheric.MOD_ID);

	public static final RegistryObject<Structure<NoFeatureConfig>> ARID_SHRINE = STRUCTURES.register("arid_shrine", () -> new AridShrineStructure(NoFeatureConfig.field_236558_a_));

	public static final class Configured {
		public static final StructureFeature<?, ?> ARID_SHRINE = AtmosphericStructures.ARID_SHRINE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

		private static <FC extends IFeatureConfig> void register(String name, StructureFeature<FC, ?> stuctureFeature) {
			Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(Atmospheric.MOD_ID, name), stuctureFeature);
		}

		public static void registerConfiguredFeatures() {
			register("arid_shrine", ARID_SHRINE);
		}
	}

	public static final class Pieces {
		public static final IStructurePieceType ARID_SHRINE_PIECE = IStructurePieceType.register(AridShrinePieces.Piece::new, "arid_shrine_piece");
	}

	public static void registerNoiseSettings() {
		Structure.NAME_STRUCTURE_BIMAP.put("arid_shrine", ARID_SHRINE.get());
		WorldGenRegistries.NOISE_SETTINGS.forEach(settings -> {
			settings.getStructures().func_236195_a_().put(ARID_SHRINE.get(), new StructureSeparationSettings(32, 8, 304972539));
		});
	}
}
