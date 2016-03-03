package com.teammetallurgy.atum.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AtumFish {
    private static AtumWeightedLootSet fish;

    static {
        fish = new AtumWeightedLootSet();

        AtumFish.addFish(new ItemStack(Items.fish, 1, 0), 100);
        AtumFish.addFish(new ItemStack(AtumItems.FISH, 1, 0), 30);
        AtumFish.addFish(new ItemStack(AtumItems.FISH, 1, 1), 5);
        AtumFish.addFish(new ItemStack(AtumItems.FISH, 1, 2), 50);
        AtumFish.addFish(new ItemStack(AtumItems.FISH, 1, 3), 50);
    }

    public static void addFish(ItemStack fishStack, int weight) {
        fish.addLoot(fishStack, weight, 1, 1);
    }

    public static ItemStack getRandomFish() {
        return fish.getRandomLoot();
    }
}