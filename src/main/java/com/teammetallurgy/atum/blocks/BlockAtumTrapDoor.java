package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class BlockAtumTrapDoor extends BlockTrapDoor {

    public BlockAtumTrapDoor(Material material) {
        super(material);
        disableStats();
    }
}