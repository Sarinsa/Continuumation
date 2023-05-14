package com.sarinsa.continuumation.datagen;

import com.sarinsa.continuumation.common.core.Continuumation;
import com.sarinsa.continuumation.datagen.loot.ConBlockLoot;
import com.sarinsa.continuumation.datagen.loot.ConLootProvider;
import com.sarinsa.continuumation.datagen.tag.ConBlockTagProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Continuumation.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGatherListener {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
            List<LootTableProvider.SubProviderEntry> subproviders = new ArrayList<>();

            subproviders.add(new LootTableProvider.SubProviderEntry(() -> new ConBlockLoot(FeatureFlags.DEFAULT_FLAGS), LootContextParamSets.BLOCK));

            ConLootProvider lootProvider = new ConLootProvider(dataGenerator.getPackOutput(), subproviders);
            dataGenerator.addProvider(event.includeServer(), lootProvider);

            dataGenerator.addProvider(event.includeServer(), new ConBlockTagProvider(dataGenerator.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
    }
}
