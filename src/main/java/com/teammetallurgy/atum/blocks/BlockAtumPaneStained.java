package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.SoundType;

public class BlockAtumPaneStained extends BlockStainedGlassPane {

    public BlockAtumPaneStained() {
        super();
        this.setHardness(0.3F);
        this.setSoundType(SoundType.GLASS);
    }
}