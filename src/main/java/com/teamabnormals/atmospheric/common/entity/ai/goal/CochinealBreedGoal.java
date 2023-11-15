package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;

public class CochinealBreedGoal extends BreedGoal {

	public CochinealBreedGoal(Animal p_25122_, double p_25123_) {
		super(p_25122_, p_25123_);
	}

	protected void breed() {
		this.spawnBabies((Cochineal) this.animal, this.partner);
		this.spawnBabies((Cochineal) this.partner, this.animal);
	}

	public void spawnBabies(Cochineal cochineal, Animal partner) {
		RandomSource random = cochineal.getRandom();
		int count = 1 + random.nextInt(2) + (cochineal.isSuperInLove() ? 1 : 0);
		for (int i = 0; i < count; i++) {
			cochineal.spawnChildFromBreeding((ServerLevel) this.level, partner);
		}
		cochineal.kill();
	}
}