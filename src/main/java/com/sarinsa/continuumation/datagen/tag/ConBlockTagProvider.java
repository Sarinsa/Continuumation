package com.sarinsa.continuumation.datagen.tag;

import com.sarinsa.continuumation.common.core.Continuumation;
import com.sarinsa.continuumation.common.core.registry.ConBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ConBlockTagProvider extends BlockTagsProvider {

    public ConBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper fileHelper) {
        super(output, lookupProvider, Continuumation.MODID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Blocks.ORES).add(ConBlocks.CONTINUUM_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ConBlocks.CONTINUUM_ORE.get());
    }
}
