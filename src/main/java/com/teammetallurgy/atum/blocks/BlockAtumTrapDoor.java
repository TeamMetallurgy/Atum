package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class BlockAtumTrapDoor extends BlockTrapDoor {

    public BlockAtumTrapDoor(Material material) {
        super(material);
        disableStats();
        this.setCreativeTab(Atum.creativeTab);
    }

}
