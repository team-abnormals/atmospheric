package com.bagel.rosewood.core.util;

import com.bagel.rosewood.common.blocks.PassionVineAttachment;

import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;

public class StateUtils {
	public static final IntegerProperty AGE_0_4 = IntegerProperty.create("age", 0, 4);
	
	public static final EnumProperty<PassionVineAttachment> PASSION_VINE_ATTACHMENT = EnumProperty.create("attachment", PassionVineAttachment.class);
}

