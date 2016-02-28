package com.teammetallurgy.atum.world.decorators;

import com.teammetallurgy.atum.blocks.AtumBlocks;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenDeadwood extends WorldGenAbstractTree {

    private int minHeight = 6;
    
    public WorldGenDeadwood(boolean doBlockNotify) {
        super(doBlockNotify);
    }

    public WorldGenDeadwood(boolean doBlockNotify, int minimunHeight) {
       super(doBlockNotify);
       minHeight = minimunHeight;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        Block baseBlock = world.getBlock(x, y - 1, z);
        boolean isGenerateable = true;
        if(baseBlock == AtumBlocks.BLOCK_SAND && y >= 1 && y + minHeight + 1 <= 256) {
            
            for (int spaceY = y; spaceY <= y + 1 + minHeight; spaceY++){
                
                byte extraGirth = 1;
                if (spaceY == y) {
                    extraGirth = 0;
                }
                if (spaceY >= y + 1 + minHeight - 2) {
                    extraGirth = 2;
                }
                
                for (int spaceX = x - extraGirth; spaceX <= x + extraGirth && isGenerateable; spaceX++) {
                    for (int spaceZ = z - extraGirth; spaceZ <= z + extraGirth && isGenerateable; spaceZ++) {
                        
                        if(spaceY < 0 || spaceY >= 256) {
                            isGenerateable = false;
                            continue;
                        }
                        
                        if (!this.isReplaceable(world, spaceX, spaceY, spaceZ)){
                            isGenerateable = false;
                        }
                    }
                }
            }
            
            if(isGenerateable && y < 256 - 1 - minHeight) {
                baseBlock.onPlantGrow(world, x, y - 1, z, x, y, z);
                
                int splitStartfromTop = 4;
                int logX = x;
                int logZ = z;
                int branchStart = minHeight - random.nextInt(splitStartfromTop + 1) - 1; 
                int branchHeight = splitStartfromTop - random.nextInt(splitStartfromTop);
                int branchDirection = random.nextInt(3);
                
                // First branch
                for(int logY = y; logY < minHeight + y ; logY++) {
                    
                    if(logY >= branchStart + y && branchHeight > 0) {
                        logX += Direction.offsetX[branchDirection];
                        logZ += Direction.offsetZ[branchDirection];
                        branchHeight --;
                    }
                    
                    Block currentBlock = world.getBlock(logX, logY, logZ);
                    if(currentBlock.isAir(world, logX, logY, logZ) || currentBlock.isLeaves(world, logX, logY, logZ)){
                        setBlockAndNotifyAdequately(world, logX, logY, logZ, AtumBlocks.BLOCK_DEADWOOD_LOG, 3);
                    }
                    
                    // Trunk base
                    if(logY == y) {
                        int numberOfBaseSides = random.nextInt(3) + 1;
                        for(int i = 0; i < numberOfBaseSides; i++) {
                            int baseDirection = random.nextInt(3);
                            int baseX = Direction.offsetX[baseDirection] + logX;
                            int baseZ = Direction.offsetZ[baseDirection] + logZ;
                            
                            Block currentBaseBlock = world.getBlock(baseX, logY, baseZ);
                            Block lowerBaseBlock = world.getBlock(baseX, logY - 1, baseZ);
                            if ((currentBaseBlock.isAir(world, logX, logY, logZ) || currentBaseBlock.isLeaves(world, logX, logY, logZ)) && lowerBaseBlock == AtumBlocks.BLOCK_SAND) {
                                setBlockAndNotifyAdequately(world, baseX, logY, baseZ, AtumBlocks.BLOCK_DEADWOOD_LOG, 3);
                            }
                        }
                    }
                }
                
                // Second branch
                logX = x;
                logZ = z;
                int branch2Direction = random.nextInt(3);
                if(branchDirection != branch2Direction) {
                    int branch2Start = branchStart - random.nextInt(splitStartfromTop - 1) - 1;
                    int branch2Height = 1 + random.nextInt(splitStartfromTop);
                    boolean firstRun = true;
                    for(int logY = branch2Start + y; logY < minHeight + y && branch2Height > 0; logY++){
                        
                        if(!firstRun)
                        {
                            logX += Direction.offsetX[branch2Direction];
                            logZ += Direction.offsetZ[branch2Direction];
                            
                            Block currentBlock = world.getBlock(logX, logY, logZ);
                            if(currentBlock.isAir(world, logX, logY, logZ) || currentBlock.isLeaves(world, logX, logY, logZ)){
                                setBlockAndNotifyAdequately(world, logX, logY, logZ, AtumBlocks.BLOCK_DEADWOOD_LOG, 3);
                            }
                        }
                        
                        firstRun = false;
                        branch2Height --;
                    }
                    
                }
                return true;
            }
        }
        return false;
    }

}
