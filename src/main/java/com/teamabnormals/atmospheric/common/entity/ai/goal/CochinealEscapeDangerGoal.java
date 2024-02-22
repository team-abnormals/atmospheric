package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class CochinealEscapeDangerGoal extends Goal {
	private final Cochineal cochineal;

	public CochinealEscapeDangerGoal(Cochineal cochineal) {
		this.cochineal = cochineal;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		return false;
	}
}
