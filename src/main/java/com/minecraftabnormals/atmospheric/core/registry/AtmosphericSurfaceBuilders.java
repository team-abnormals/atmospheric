package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.DunesSurfaceBuilder;
import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.DunesWavesSurfaceBuilder;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericSurfaceBuilders {
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES_WAVES = new DunesWavesSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);

	@SubscribeEvent
	public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
		event.getRegistry().registerAll(
				DUNES.setRegistryName(Atmospheric.MOD_ID, "dunes"),
				DUNES_WAVES.setRegistryName(Atmospheric.MOD_ID, "wavey_dunes")
		);
	}

	public static final class Configs {
		public static final SurfaceBuilderConfig DUNES = new SurfaceBuilderConfig(AtmosphericBlocks.ARID_SAND.get().getDefaultState(), AtmosphericBlocks.ARID_SAND.get().getDefaultState(), Blocks.GRAVEL.getDefaultState());
	}

	public static final class Configured {
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DUNES = AtmosphericSurfaceBuilders.DUNES.func_242929_a(Configs.DUNES);
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DUNES_WAVES = AtmosphericSurfaceBuilders.DUNES_WAVES.func_242929_a(Configs.DUNES);

		private static <SC extends ISurfaceBuilderConfig> void register(String key, ConfiguredSurfaceBuilder<SC> builder) {
			WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(Atmospheric.MOD_ID, key), builder);
		}

		public static void registerConfiguredSurfaceBuilders() {
			register("dunes", DUNES);
			register("wavey_dunes", DUNES_WAVES);
		}
	}
}
