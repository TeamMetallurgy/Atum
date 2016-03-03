package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

public class BlockSands extends BlockFalling {

    public BlockSands() {
        super(Material.sand);
        this.setStepSound(Block.soundTypeSand);
        this.setHardness(0.5F);
    }
}