package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPalmSapling extends BlockBush {

    protected BlockPalmSapling() {
        super();
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, x, y, z, rand);
            if (world.getBlockLightValue(x, y + 1, z) > 9 && rand.nextInt(20) == 0) {
                this.growTree(world, x, y, z, rand);
            }
        }

    }

    public void growTree(World world, BlockPos pos, Random rand) {
        int height = rand.nextInt(4) + 5;

        int i;
        for (i = 0; i < height; ++i) {
            world.setBlock(x, y + i, z, AtumBlocks.LOG, 0, 2);
        }

        spawnLeaf(world, x, y+height, z);
        for (i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                if (i != 0 || j != 0) {
                    spawnLeaf(world, x+i, y+height-1, z+j);
                }
            }
        }

        spawnLeaf(world, x+2, y+height-1, z);
        spawnLeaf(world, x+2, y+height-2, z);
        spawnLeaf(world, x+3, y+height-2, z);

        if (rand.nextInt(100) < 15) {
            world.setBlock(x + 1, y + height - 2, z, AtumBlocks.DATEBLOCK, 0, 2);
        }

        spawnLeaf(world, x-2, y+height-1, z);
        spawnLeaf(world, x-2, y+height-2, z);
        spawnLeaf(world, x-3, y+height-2, z);

        if (rand.nextInt(100) < 15) {
            world.setBlock(x - 1, y + height - 2, z, AtumBlocks.DATEBLOCK, 0, 2);
        }

        spawnLeaf(world, x, y+height-1, z+2);
        spawnLeaf(world, x, y+height-2, z+2);
        spawnLeaf(world, x, y+height-2, z+3);
        
        if (rand.nextInt(100) < 15) {
            world.setBlock(x, y + height - 2, z + 1, AtumBlocks.DATEBLOCK, 0, 2);
        }

        spawnLeaf(world, x, y+height-1, z-2);
        spawnLeaf(world, x, y+height-2, z-2);
        spawnLeaf(world, x, y+height-2, z-3);
        
        if (rand.nextInt(100) < 15) {
            world.setBlock(x, y + height - 2, z - 1, AtumBlocks.DATEBLOCK, 0, 2);
        }

    }

    @Override
    protected boolean canPlaceBlockOn(Block id) {
        return id == AtumBlocks.SAND || id == Blocks.grass || id == Blocks.dirt;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z) == AtumBlocks.SAND || world.getBlock(x, y - 1, z) == Blocks.grass || world.getBlock(x, y - 1, z) == Blocks.dirt;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = par1IIconRegister.registerIcon("atum:AtumPalmSapling");
    }
    
    public void spawnLeaf(World world, int x, int y, int z){
    	Block block = world.getBlock(x, y, z);
    	if(world.isAirBlock(x, y, z) || block.canBeReplacedByLeaves(world, x, y, z)){
    		world.setBlock(x, y, z, AtumBlocks.LEAVES, 0,2);
    	}
    }
}
