package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockAtumGlassStained extends BlockStainedGlass {
    public BlockAtumGlassStained() {
        super(Material.GLASS);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(0.3F);
    }
}