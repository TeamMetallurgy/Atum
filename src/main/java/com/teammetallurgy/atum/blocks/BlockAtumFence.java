package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockAtumFence extends BlockFence {

    public BlockAtumFence(Material material) {
        super(material);
    }

    @Override
    public boolean canConnectTo(IBlockAccess world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return !canBlockConnect(block) ? super.canConnectTo(world, pos) : true;
    }

    private boolean canBlockConnect(Block block) {
        return block == AtumBlocks.PALM_FENCE || block == AtumBlocks.PALM_FENCE_GATE ||
                block == AtumBlocks.DEADWOOD_FENCE || block == AtumBlocks.DEADWOOD_FENCE_GATE;
    }
}