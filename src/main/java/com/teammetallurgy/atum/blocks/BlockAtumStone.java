package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;

public class BlockAtumStone extends BlockStone {

    public BlockAtumStone() {
        super();
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
    }
}