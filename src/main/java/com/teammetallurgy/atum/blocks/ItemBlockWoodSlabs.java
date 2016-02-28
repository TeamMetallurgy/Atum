package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

public class ItemBlockWoodSlabs extends ItemSlab {

    public ItemBlockWoodSlabs(Block block, BlockAtumWoodSlab slabBlock, BlockAtumWoodSlab doubleSlabBlock, Boolean isDoubleSlab) {
        super(block, slabBlock, doubleSlabBlock, isDoubleSlab);
    }

}
