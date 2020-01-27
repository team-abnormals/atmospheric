package com.bagel.rosewood.common.blocks;

import net.minecraft.util.IStringSerializable;

public enum PassionVineAttachment implements IStringSerializable {
   NONE("none"),
   TOP("top"),
   BOTTOM("bottom"),
   MIDDLE("middle");

   private final String name;

   private PassionVineAttachment(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }
}


