package com.teamabnormals.atmospheric.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OrangeVaporParticle extends TextureSheetParticle {

	public OrangeVaporParticle(ClientLevel world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(world, posX, posY, posZ, motionX, motionY, motionZ);
		this.scale(3.0F);
		this.setSize(0.25F, 0.25F);

		this.lifetime = random.nextInt(100) + 100;

		this.xd *= 0.05F;
		this.yd *= 0.05F;
		this.zd *= 0.05F;
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet p_105899_) {
			this.sprites = p_105899_;
		}

		public Particle createParticle(SimpleParticleType p_105910_, ClientLevel p_105911_, double p_105912_, double p_105913_, double p_105914_, double p_105915_, double p_105916_, double p_105917_) {
			OrangeVaporParticle particle = new OrangeVaporParticle(p_105911_, p_105912_, p_105913_, p_105914_, p_105915_, p_105916_, p_105917_);
			particle.pickSprite(this.sprites);
			return particle;
		}
	}
}