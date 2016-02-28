package com.teammetallurgy.atum.items;

import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;

public class ItemAtumGoldenDate extends ItemAppleGold {

    public ItemAtumGoldenDate(int healAmount, float saturationModifier, boolean isWolfsFood) {
        super(healAmount, saturationModifier, isWolfsFood);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if (itemStack.getItemDamage() > 0)
            return super.getUnlocalizedName() + "Enchanted";
        return super.getUnlocalizedName();
    }

}
