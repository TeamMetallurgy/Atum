package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockAtumFence extends BlockFence {

    public BlockAtumFence(String textureName, Material material) {
        super(textureName, material);
    }
    
    @Override
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return !isConnectabledBlock(block) ? super.canConnectFenceTo(world, x, y, z) : true;
    }
    
    private boolean isConnectabledBlock(Block block) {
        return block == AtumBlocks.BLOCK_PALM_FENCE || block == AtumBlocks.BLOCK_PALM_FENCE_GATE ||
                block == AtumBlocks.BLOCK_DEADWOOD_FENCE || block == AtumBlocks.BLOCK_DEADWOOD_FENCE_GATE;
    }

}
