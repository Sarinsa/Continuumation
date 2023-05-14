package com.sarinsa.continuumation.common.core.registry;

import com.sarinsa.continuumation.common.core.Continuumation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ConBlocks {

    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Continuumation.MODID);


    public static final RegistryObject<Block> CONTINUUM_ORE = register("continuum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE), UniformInt.of(5, 6)));


    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> regObj = REGISTRY.register(name, block);
        ConItems.REGISTRY.register(name, () -> new BlockItem(regObj.get(), new Item.Properties()));
        return regObj;
    }

    private static <T extends Block> RegistryObject<T> registerNoBlockItem(String name, Supplier<T> block) {
        return REGISTRY.register(name, block);
    }
}
