package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;

public class BlockAtumFenceGate extends BlockFenceGate {

    public BlockAtumFenceGate() {
        super(BlockPlanks.EnumType.OAK); //TODO
        this.setHardness(2.0F).setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
    }
}