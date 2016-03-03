package com.teammetallurgy.atum.world.decorators;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenShrub extends WorldGenerator {

    private Block shrub;
    private int groupSize;

    public WorldGenShrub(Block block, int size) {
        this.shrub = block;
        this.groupSize = size;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        int size = random.nextInt(this.groupSize / 2) + this.groupSize / 2;
        Block block;

        do {
            block = world.getBlockState(pos).getBlock();
            if (!block.isLeaves(world, pos) && !block.isLeaves(world, pos)) break;
            pos = pos.down();
        } while (pos.getY() > 0);

        for (int i = 0; i < size; ++i) {
            BlockPos blockpos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (world.isAirBlock(blockpos) && AtumBlocks.SHRUB.canBlockStay(world, blockpos, shrub.getDefaultState())) {
                world.setBlockState(blockpos, shrub.getDefaultState(), 2);
            }
        }
        return true;
    }
}