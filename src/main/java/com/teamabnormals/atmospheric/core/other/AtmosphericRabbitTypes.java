package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.api.BlueprintRabbitTypes;
import com.teamabnormals.blueprint.core.api.BlueprintRabbitTypes.BlueprintRabbitType;

public class AtmosphericRabbitTypes {
	private static final int UNIQUE_OFFSET = 1337;

	public static final BlueprintRabbitType YELLOW = BlueprintRabbitTypes.register(UNIQUE_OFFSET, Atmospheric.location("yellow"));
}