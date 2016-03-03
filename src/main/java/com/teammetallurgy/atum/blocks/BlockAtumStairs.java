package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockAtumStairs extends BlockStairs {

    protected BlockAtumStairs(IBlockState modelState) {
        super(modelState);
        this.useNeighborBrightness = true;
    }
}