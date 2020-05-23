package com.bagel.atmospheric.core.other;

import com.bagel.atmospheric.common.data.PassionVineAttachment;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;

public class AtmosphericBlockStates {
	public static final IntegerProperty AGE_0_4 = IntegerProperty.create("age", 0, 4);
	public static final EnumProperty<PassionVineAttachment> PASSION_VINE_ATTACHMENT = EnumProperty.create("attachment", PassionVineAttachment.class);
	public static final IntegerProperty BITES_0_9 = IntegerProperty.create("bites", 0, 9);
	public static final BooleanProperty SNAPPED = BooleanProperty.create("snapped");
}

