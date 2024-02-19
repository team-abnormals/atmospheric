package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class CochinealRandomHopGoal extends Goal {
	private final Cochineal cochineal;
	private int hops;

	public CochinealRandomHopGoal(Cochineal cochineal) {
		this.cochineal = cochineal;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.cochineal.getNoActionTime() >= 100)
			return false;
		if (this.cochineal.getRandom().nextInt(this.adjustedTickDelay(120)) != 0)
			return false;

		this.hops = this.cochineal.getRandom().nextInt(3) + 2;

		return true;
	}

	@Override
	public boolean canContinueToUse() {
		return this.hops > 0;
	}

	@Override
	public void tick() {
		if (this.cochineal.isOnGround() && !this.cochineal.getMoveControl().hasWanted()) {
			Vec3 vec3 = LandRandomPos.getPos(this.cochineal, 12, 8);
			if (vec3 != null) {
				this.cochineal.getMoveControl().setWantedPosition(vec3.x, vec3.y, vec3.z, 0.0F);
				--this.hops;
			}
		}
	}
}