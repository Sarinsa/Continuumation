package com.sarinsa.continuumation.datagen.loot;

import com.google.common.collect.Sets;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConLootProvider extends LootTableProvider {

    public ConLootProvider(PackOutput packOutput, List<SubProviderEntry> providers) {
        super(packOutput, null, providers);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
        // No validation
    }
}
