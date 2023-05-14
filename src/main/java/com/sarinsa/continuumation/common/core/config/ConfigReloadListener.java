package com.sarinsa.continuumation.common.core.config;

import com.mojang.logging.LogUtils;
import com.sarinsa.continuumation.common.core.Continuumation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Continuumation.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigReloadListener {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onConfigLoad(ModConfigEvent event) {
        if (!(event instanceof ModConfigEvent.Unloading)) {
            refreshOrbFilterItems();
        }
    }

    private static void refreshOrbFilterItems() {
        OrbFilterMode filterMode = ConCommonConfig.COMMON.orbFilterMode.get();
        List<ResourceLocation> itemIds = new ArrayList<>();

        for (String s : ConCommonConfig.COMMON.getContinuumFilterItems()) {
            ResourceLocation id = ResourceLocation.tryParse(s);

            if (id == null) {
                error("Found invalid item id in continuum item filter list: '{}'", s);
                continue;
            }

            if (ForgeRegistries.ITEMS.containsKey(id)) {
                itemIds.add(id);
            }
            else {
                error("Found id for a non-existent item in continuum item filter list: '{}'", id.toString());
            }
        }
        Continuumation.VIABLE_ITEMS.clear();

        if (filterMode == OrbFilterMode.BLACKLIST) {
            for (Item item : ForgeRegistries.ITEMS.getValues()) {
                if (!itemIds.contains(ForgeRegistries.ITEMS.getKey(item)) && item.isEnabled(FeatureFlags.DEFAULT_FLAGS))
                    Continuumation.VIABLE_ITEMS.add(item);
            }
        }
        else {
            for (ResourceLocation id : itemIds) {
                if (ForgeRegistries.ITEMS.getValue(id).isEnabled(FeatureFlags.DEFAULT_FLAGS))
                    Continuumation.VIABLE_ITEMS.add(ForgeRegistries.ITEMS.getValue(id));
            }
        }
    }


    private static void error(String message, Object... args) {
        LOGGER.error(message, args);
    }
}
