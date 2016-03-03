package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAtum extends Block {

    public BlockAtum() {
        super(Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
    }
}