package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockStainedGlassPane;

public class BlockAtumPaneStained extends BlockStainedGlassPane {

    public BlockAtumPaneStained() {
        super();
        this.setHardness(0.3F);
        this.setStepSound(soundTypeGlass);
    }
}