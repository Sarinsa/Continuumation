package com.sarinsa.continuumation.common.worldgen.biome.modifier;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum ModOre implements StringRepresentable {
    CONTINUUM("continuum");

    ModOre(String name) {
        this.name = name;
    }

    public static final Codec<ModOre> CODEC = StringRepresentable.fromEnum(ModOre::values);
    private final String name;

    @Override
    public String getSerializedName() {
        return name;
    }
}
