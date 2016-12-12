package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public class BlockAtumStairs extends BlockStairs {

    protected BlockAtumStairs(IBlockState modelState) {
        super(modelState);
        this.useNeighborBrightness = true;
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT));
    }
}