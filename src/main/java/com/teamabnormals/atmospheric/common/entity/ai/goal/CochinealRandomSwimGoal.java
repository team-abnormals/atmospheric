package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.phys.Vec3;

public class CochinealRandomSwimGoal extends WaterAvoidingRandomStrollGoal {

	public CochinealRandomSwimGoal(Cochineal cochineal, double speed) {
		super(cochineal, speed);
	}

	@Override
	public boolean canUse() {
		if (!this.mob.isInFluidType() || ((Cochineal) this.mob).isAttachedToCactus() || this.mob.getNoActionTime() >= 100 || this.mob.getRandom().nextInt(reducedTickDelay(this.interval)) != 0)
			return false;

		Vec3 vec3 = this.getPosition();
		if (vec3 == null) {
			return false;
		} else {
			this.wantedX = vec3.x;
			this.wantedY = vec3.y;
			this.wantedZ = vec3.z;
			this.forceTrigger = false;
			return true;
		}
	}
}