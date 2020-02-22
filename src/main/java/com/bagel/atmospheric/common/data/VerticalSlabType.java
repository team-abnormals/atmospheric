package com.bagel.atmospheric.common.data;

import net.minecraft.util.IStringSerializable;

public enum VerticalSlabType implements IStringSerializable {
   HALF("half"),
   DOUBLE("double");

   private final String name;

   private VerticalSlabType(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }
}
