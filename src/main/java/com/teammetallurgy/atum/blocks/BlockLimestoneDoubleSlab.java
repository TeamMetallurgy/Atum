package com.teammetallurgy.atum.blocks;

public class BlockLimestoneDoubleSlab extends BlockLimestoneSlab {

    public BlockLimestoneDoubleSlab() {
        setUnlocalizedName("limestone_slab");
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}