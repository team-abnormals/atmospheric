package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.DunesSurfaceBuilder;
import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.DunesWavesSurfaceBuilder;
import com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders.HotSpringsSurfaceBuilder;
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
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES = new DunesSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNES_WAVES = new DunesWavesSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> HOT_SPRINGS = new HotSpringsSurfaceBuilder(SurfaceBuilderConfig.CODEC);

	@SubscribeEvent
	public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
		event.getRegistry().registerAll(
				DUNES.setRegistryName(Atmospheric.MOD_ID, "dunes"),
				DUNES_WAVES.setRegistryName(Atmospheric.MOD_ID, "wavey_dunes"),
				HOT_SPRINGS.setRegistryName(Atmospheric.MOD_ID, "hot_springs")
		);
	}

	public static final class Configs {
		public static final SurfaceBuilderConfig DUNES = new SurfaceBuilderConfig(AtmosphericBlocks.ARID_SAND.get().defaultBlockState(), AtmosphericBlocks.ARID_SAND.get().defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
		public static final SurfaceBuilderConfig HOT_SPRINGS = new SurfaceBuilderConfig(AtmosphericBlocks.IVORY_TRAVERTINE.get().defaultBlockState(), AtmosphericBlocks.IVORY_TRAVERTINE.get().defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
	}

	public static final class Configured {
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DUNES = AtmosphericSurfaceBuilders.DUNES.configured(Configs.DUNES);
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DUNES_WAVES = AtmosphericSurfaceBuilders.DUNES_WAVES.configured(Configs.DUNES);
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> HOT_SPRINGS = AtmosphericSurfaceBuilders.HOT_SPRINGS.configured(Configs.HOT_SPRINGS);

		private static <SC extends ISurfaceBuilderConfig> void register(String key, ConfiguredSurfaceBuilder<SC> builder) {
			WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(Atmospheric.MOD_ID, key), builder);
		}

		public static void registerConfiguredSurfaceBuilders() {
			register("dunes", DUNES);
			register("wavey_dunes", DUNES_WAVES);
			register("hot_springs", HOT_SPRINGS);
		}
	}
}
