package com.sarinsa.continuumation.common.item;

import com.sarinsa.continuumation.common.core.Continuumation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ContinuumOrbItem extends Item {

    public ContinuumOrbItem() {
        super(new Item.Properties()
                .rarity(Rarity.UNCOMMON)
                .stacksTo(1)
                .fireResistant()
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (!level.isClientSide) {
            ItemStack randomDrop = new ItemStack(getRandomItem(level.getRandom()));

            if (!player.getInventory().add(randomDrop)) {
                Block.popResource(level, player.blockPosition(), randomDrop);
            }

            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
        }
        return InteractionResultHolder.sidedSuccess(heldItem, level.isClientSide);
    }

    private static Item getRandomItem(RandomSource random) {
        int index = random.nextInt(Continuumation.VIABLE_ITEMS.size());
        return Continuumation.VIABLE_ITEMS.get(index);
    }
}
