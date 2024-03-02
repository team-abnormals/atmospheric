package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import net.minecraft.world.entity.ai.goal.Goal;

public class CochinealDetachFromCactusGoal extends Goal {
	private final Cochineal cochineal;

	public CochinealDetachFromCactusGoal(Cochineal cochineal) {
		this.cochineal = cochineal;
	}

	@Override
	public boolean canUse() {
		return this.cochineal.isAttachedToCactus() && !this.cochineal.isBaby() && !this.cochineal.isOnSuckleCooldown() && this.cochineal.getRandom().nextInt(this.adjustedTickDelay(800)) == 0;
	}

	@Override
	public boolean canContinueToUse() {
		return false;
	}

	@Override
	public void start() {
		this.cochineal.detachFromCactus();
	}
}