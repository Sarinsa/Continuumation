package com.sarinsa.continuumation.datagen.loot;

import com.sarinsa.continuumation.common.core.registry.ConBlocks;
import com.sarinsa.continuumation.common.core.registry.ConItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

public class ConBlockLoot extends BlockLootSubProvider {

    private final Set<Block> knownBlocks = new HashSet<>();

    public ConBlockLoot(FeatureFlagSet flagSet) {
        super(Set.of(), flagSet);
    }

    @Override
    protected void generate() {
        add(ConBlocks.CONTINUUM_ORE.get(), createOreDrop(ConBlocks.CONTINUUM_ORE.get(), ConItems.CONTINUUM_ORB.get()));
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        knownBlocks.add(block);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }
}
