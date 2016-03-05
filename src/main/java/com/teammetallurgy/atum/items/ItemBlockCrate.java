package com.teammetallurgy.atum.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockCrate extends ItemBlock { //TODO

    public ItemBlockCrate(Block block) {
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    /*@Override
    public String getUnlocalizedName(ItemStack stack) {
        return ((BlockCrate) block).getUnlocalizedName(stack.getItemDamage());
    }*/
}