package com.teammetallurgy.atum.items;

import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;

public class ItemAtumGoldenDate extends ItemAppleGold {

    public ItemAtumGoldenDate(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getItemDamage() > 0) {
            return super.getUnlocalizedName() + "Enchanted";
        }
        return super.getUnlocalizedName();
    }
}