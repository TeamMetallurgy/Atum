package com.teammetallurgy.atum.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.*;

public class AtumWeightedLootSet {
    public Map<Integer, ItemStack> loot;
    public Map<Integer, Integer> lootMin;
    public Map<Integer, Integer> lootMax;
    public int totalWeight;

    public AtumWeightedLootSet() {
        loot = new HashMap<Integer, ItemStack>();
        lootMin = new HashMap<Integer, Integer>();
        lootMax = new HashMap<Integer, Integer>();
        totalWeight = 0;
    }

    public void addLoot(ItemStack stack, int weight, int min, int max) {
        if (weight <= 0 || stack == null)
            return;

        loot.put(totalWeight + weight, stack);
        lootMin.put(totalWeight + weight, min);
        lootMax.put(totalWeight + weight, max);
        totalWeight += weight;
    }

    public ItemStack getRandomLoot() {
        Random rand = new Random();
        int weight = rand.nextInt(totalWeight);

        ItemStack stack = null;

        Set<Integer> keySet = loot.keySet();
        Integer[] keys = keySet.toArray(new Integer[keySet.size()]);
        Arrays.sort(keys);

        for (Integer key : keys) {
            if (key >= weight) {
                stack = loot.get(key).copy();
                int min = lootMin.get(key);
                int max = lootMax.get(key);
                int amount = rand.nextInt(max - min + 1) + min;
                stack.stackSize = amount;
                if (stack.getItem() == Items.enchanted_book) {
                    Enchantment enchantment = Enchantment.enchantmentRegistry.getRandomObject(rand);
                    int l = MathHelper.getRandomIntegerInRange(rand, enchantment.getMinLevel(), enchantment.getMaxLevel());
                    ((ItemEnchantedBook) stack.getItem()).addEnchantment(stack, new EnchantmentData(enchantment, l));
                }
                break;
            }
        }
        return stack;
    }
}