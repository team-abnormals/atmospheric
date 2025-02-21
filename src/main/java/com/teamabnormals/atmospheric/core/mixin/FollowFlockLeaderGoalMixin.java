package com.teamabnormals.atmospheric.core.mixin;

import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

@Mixin(FollowFlockLeaderGoal.class)
public class FollowFlockLeaderGoalMixin {

	@ModifyArg(method = "canUse", at = @At(value = "INVOKE", target = "java/util/stream/Stream.filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;", ordinal = 1, remap = false))
	private Predicate<AbstractSchoolingFish> canUse(Predicate<AbstractSchoolingFish> predicate) {
		return predicate.and(Predicate.not(AbstractSchoolingFish::hasFollowers));
	}
}