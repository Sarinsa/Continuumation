package com.sarinsa.continuumation.common.core;

import com.mojang.logging.LogUtils;
import com.sarinsa.continuumation.common.core.config.ConCommonConfig;
import com.sarinsa.continuumation.common.core.registry.ConBlocks;
import com.sarinsa.continuumation.common.core.registry.ConItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(Continuumation.MODID)
public class Continuumation {

    public static final String MODID = "continuumation";
    private static final Logger LOGGER = LogUtils.getLogger();


    public static final List<Item> VIABLE_ITEMS = new ArrayList<>();


    public Continuumation() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ConBlocks.REGISTRY.register(modEventBus);
        ConItems.REGISTRY.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ConItems::onCreativeTabBuild);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConCommonConfig.COMMON_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        VIABLE_ITEMS.addAll(ForgeRegistries.ITEMS.getValues());
    }
}
