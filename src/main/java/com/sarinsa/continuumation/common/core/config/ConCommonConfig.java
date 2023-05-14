package com.sarinsa.continuumation.common.core.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ConCommonConfig {

    public static final ConCommonConfig.Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(ConCommonConfig.Common::new);
        COMMON = clientPair.getLeft();
        COMMON_SPEC = clientPair.getRight();
    }

    public static final class Common {

        public ForgeConfigSpec.EnumValue<OrbFilterMode> orbFilterMode;
        private ForgeConfigSpec.ConfigValue<List<? extends String>> continuumFilterItems;


        private Common(ForgeConfigSpec.Builder configBuilder) {
            configBuilder.push("general");

            orbFilterMode = configBuilder.comment("Determines if the continuumFilterItems config list should be treated as a blacklist or whitelist.")
                    .defineEnum("orbFilterMode", OrbFilterMode.BLACKLIST);

            continuumFilterItems = configBuilder.comment("If orbFilterMode is set to BLACKLIST, continuum orbs can grant any random item EXCEPT those listed here. If set to WHITELIST, continuum orbs will only drop a random item that exist in this list.")
                    .defineList("continuumFilterItems", Common::defaultFilterItems, (o) -> existInRegistry(o, ForgeRegistries.BLOCKS));

            configBuilder.pop();
        }

        private static boolean existInRegistry(Object o, IForgeRegistry<?> registry) {
            if (o instanceof String string && ResourceLocation.tryParse(string) != null) {
                ResourceLocation id = ResourceLocation.tryParse(string);
                return registry.containsKey(id);
            }
            return false;
        }

        private static List<? extends String> defaultFilterItems() {
            List<String> list = new ArrayList<>();

            list.add(new ResourceLocation("barrier").toString());
            list.add(new ResourceLocation("structure_void").toString());
            list.add(new ResourceLocation("command_block").toString());
            list.add(new ResourceLocation("chain_command_block").toString());
            list.add(new ResourceLocation("structure_block").toString());
            list.add(new ResourceLocation("jigsaw").toString());
            list.add(new ResourceLocation("debug_stick").toString());
            list.add(new ResourceLocation("bedrock").toString());

            return list;
        }

        public List<? extends String> getContinuumFilterItems() {
            return continuumFilterItems.get();
        }
    }
}
