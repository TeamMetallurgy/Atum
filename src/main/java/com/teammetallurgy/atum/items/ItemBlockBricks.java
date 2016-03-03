package com.teammetallurgy.atum.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBricks extends ItemBlock {

    public ItemBlockBricks(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getItemDamage() != 2) {
            return getUnlocalizedName() + "Unbreakable";
        }
        return getUnlocalizedName();
    }
}