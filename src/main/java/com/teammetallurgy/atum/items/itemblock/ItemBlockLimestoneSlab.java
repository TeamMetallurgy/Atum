package com.teammetallurgy.atum.items.itemblock;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockLimestoneSlab;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemBlockLimestoneSlab extends ItemSlab {

    public ItemBlockLimestoneSlab(Block baseBlock) {
        super(baseBlock, AtumBlocks.LIMESTONE_SLAB, AtumBlocks.LIMESTONE_DOUBLE_SLAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + BlockLimestoneSlab.EnumType.byMetadata(stack.getItemDamage());
    }
}