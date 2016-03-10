package com.teammetallurgy.atum.items.itemblock;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockAtumPlank;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemBlockWoodSlabs extends ItemSlab {

    public ItemBlockWoodSlabs(Block baseBlock) {
        super(baseBlock, AtumBlocks.WOOD_SLAB, AtumBlocks.WOOD_DOUBLE_SLAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + BlockAtumPlank.EnumType.byMetadata(stack.getItemDamage());
    }
}