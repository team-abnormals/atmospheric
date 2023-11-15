package com.teamabnormals.atmospheric.common.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class CochinealRandomStrollGoal extends RandomStrollGoal {
	public static final float PROBABILITY = 0.001F;
	protected final float probability;

	public CochinealRandomStrollGoal(PathfinderMob mob, double speed) {
		this(mob, speed, 0.001F);
	}

	public CochinealRandomStrollGoal(PathfinderMob mob, double speed, float probability) {
		super(mob, speed, 30);
		this.probability = probability;
	}

	@Override
	public boolean canUse() {
		if (!this.forceTrigger) {
			if (this.mob.getNoActionTime() >= 100) {
				return false;
			}

			if (this.mob.getRandom().nextInt(reducedTickDelay(this.interval)) != 0) {
				return false;
			}
		}

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

	@Nullable
	protected Vec3 getPosition() {
		if (this.mob.isInWaterOrBubble()) {
			Vec3 vec3 = LandRandomPos.getPos(this.mob, 15, 7);
			return vec3 == null ? super.getPosition() : vec3;
		} else {
			return this.mob.getRandom().nextFloat() >= this.probability ? LandRandomPos.getPos(this.mob, 30, 7) : DefaultRandomPos.getPos(this.mob, 30, 7);
		}
	}

	public boolean canContinueToUse() {
		return this.mob.isInWaterOrBubble();
	}
}