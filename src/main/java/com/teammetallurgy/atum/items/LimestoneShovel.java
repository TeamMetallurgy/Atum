package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class LimestoneShovel extends ItemSpade {

    public LimestoneShovel(ToolMaterial material) {
        super(material);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return Block.getBlockFromItem(repair.getItem()) == AtumBlocks.LIMESTONE_CRACKED;
    }
}