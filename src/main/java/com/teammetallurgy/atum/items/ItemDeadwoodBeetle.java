package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.Atum;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemDeadwoodBeetle extends ItemFood {

    public ItemDeadwoodBeetle() {
        super(1, 0.1F, false);
        setTextureName("atum:woodborer_beetle");
        setUnlocalizedName("deadwoodBeetle");
        setCreativeTab(Atum.creativeTab);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 10;
    }
}
