package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.common.entity.OrangeVaporCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Shadow
	public Level level;

	@Shadow
	public abstract AABB getBoundingBox();

	@Inject(method = "isInWaterOrRain", at = @At("RETURN"), cancellable = true)
	private void isInWaterOrRain(CallbackInfoReturnable<Boolean> cir) {
		if (!cir.getReturnValue()) {
			List<OrangeVaporCloud> list = this.level.getEntitiesOfClass(OrangeVaporCloud.class, this.getBoundingBox());
			if (!list.isEmpty()) {
				cir.setReturnValue(true);
			}
		}
	}
}