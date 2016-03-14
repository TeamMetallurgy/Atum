package com.teammetallurgy.atum.items.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;

public class ItemBlockStainedGlass extends ItemCloth {

    public ItemBlockStainedGlass(Block block) {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + WordUtils.capitalize(EnumDyeColor.byMetadata(stack.getMetadata()).getUnlocalizedName());
    }
}