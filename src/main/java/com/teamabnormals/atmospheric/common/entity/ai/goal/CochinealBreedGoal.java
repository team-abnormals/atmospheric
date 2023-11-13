package com.teamabnormals.atmospheric.common.entity.ai.goal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;

public class CochinealBreedGoal extends BreedGoal {

	public CochinealBreedGoal(Animal p_25122_, double p_25123_) {
		super(p_25122_, p_25123_);
	}

	protected void breed() {
		super.breed();
		this.animal.kill();
		this.partner.spawnChildFromBreeding((ServerLevel) this.level, this.animal);
		this.partner.kill();
	}
}