package com.sarinsa.continuumation.common.core.registry;

import com.sarinsa.continuumation.common.core.Continuumation;
import com.sarinsa.continuumation.common.item.ContinuumOrbItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ConItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Continuumation.MODID);

    private static final ResourceLocation AETHER_NATURAL_BLOCKS = new ResourceLocation("aether", "natural_blocks");
    private static final ResourceLocation AETHER_EAU = new ResourceLocation("aether", "equipment_and_utilities");


    public static final RegistryObject<Item> CONTINUUM_ORB = register("continuum_orb", ContinuumOrbItem::new);


    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return REGISTRY.register(name, item);
    }

    public static void onCreativeTabBuild(CreativeModeTabEvent.BuildContents event) {

        if (event.getTab() == CreativeModeTabRegistry.getTab(AETHER_EAU)) {
            event.accept(ConItems.CONTINUUM_ORB);
        }

        else if (event.getTab() == CreativeModeTabRegistry.getTab(AETHER_NATURAL_BLOCKS)) {
            event.accept(ConBlocks.CONTINUUM_ORE);
        }
    }
}
