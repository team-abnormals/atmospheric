package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.Cochineal.CochinealMoveControl;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class CochinealFleeGoal extends Goal {
	private final Cochineal cochineal;
	private int idleTime;
	private int fleeTime;
	private float direction;

	public CochinealFleeGoal(Cochineal cochineal) {
		this.cochineal = cochineal;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		return !this.cochineal.isInFluidType() && this.shouldPanic();
	}

	@Override
	public boolean canContinueToUse() {
		return !this.cochineal.isInFluidType() && --this.fleeTime > 0;
	}

	@Override
	public void start() {
		this.idleTime = 0;
		this.fleeTime = this.adjustedTickDelay(240);
		this.direction = this.cochineal.getRandom().nextFloat() * Mth.TWO_PI;
		this.cochineal.setJumpingQuickly(true);
	}

	@Override
	public void stop() {
		this.cochineal.setJumpingQuickly(false);
	}

	@Override
	public void tick() {
		if (this.cochineal.canLeap()) {
			CochinealMoveControl control = (CochinealMoveControl) cochineal.getMoveControl();
			Vec3 vec3 = this.cochineal.position().add(new Vec3(Math.sin(this.direction) * 32.0D, 0.0D, Math.cos(this.direction) * 32.0D));
			for (int i = 0; i < 3; i++) {
				Vec3 vec31 = DefaultRandomPos.getPosTowards(this.cochineal, 14, 7, vec3, Math.PI / 2.0D);
				if (vec31 != null && control.canReach(vec31.x, vec31.y, vec31.z)) {
					control.leapTo(vec31.x, vec31.y, vec31.z);
					this.idleTime = 0;
					return;
				}
			}

			if (++this.idleTime > this.adjustedTickDelay(40)) {
				this.direction = this.cochineal.getRandom().nextFloat() * Mth.TWO_PI;
				this.idleTime = 0;
			}
		}
	}

	private boolean shouldPanic() {
		return this.cochineal.getLastHurtByMob() != null || this.cochineal.isFreezing() || this.cochineal.isOnFire();
	}
}