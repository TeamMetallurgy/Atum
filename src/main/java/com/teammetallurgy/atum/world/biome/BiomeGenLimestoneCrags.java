package com.teammetallurgy.atum.world.biome;

import java.util.Random;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenLimestoneCrags extends AtumBiomeGenBase {

	private WorldGenerator genSpikes;
	
    public BiomeGenLimestoneCrags(AtumConfig.BiomeConfig config) {
        super(config);
        
        super.palmRarity = -1;
        super.pyramidRarity = -1;
        super.deadwoodRarity = 2; 
        
        this.genSpikes = new WorldGenLimestoneSpike();
        
        super.addDefaultSpawns();
    }
    
    @Override
    public void decorate(World world, Random rng, int x, int z) {
        for (int k = 0; k < 3; ++k) {
            final int xx = x + rng.nextInt(16) + 8;
            final int zz = z + rng.nextInt(16) + 8;
            this.genSpikes.generate(world, rng, xx, world.getHeightValue(xx, zz), zz);
        }

        super.decorate(world, rng, x, z);
    }
    
    /**
     * Adapted from WorldGenIceSpike
     */
    public class WorldGenLimestoneSpike extends WorldGenerator {

    	private final Block spikeBlock = AtumBlocks.BLOCK_STONE;
    	private final Block groundBlock = AtumBlocks.BLOCK_SAND;
    	
    	private boolean isBlockReplaceable(Block block) {
    		return block.getMaterial() == Material.air || block == AtumBlocks.BLOCK_SAND || block == AtumBlocks.BLOCK_SANDLAYERED || block == Blocks.dirt;
    	}
    	
		@Override
		public boolean generate(World world, Random rng, int x, int z, int y) {

			// find the surface
			while (world.isAirBlock(x, z, y) && z > 2) {
	            --z;
	        }

	        if (world.getBlock(x, z, y) != groundBlock) {
	            return false;
	        } else {
	            z += rng.nextInt(4);
	            int l = rng.nextInt(4) + 7;
	            int i1 = l / 4 + rng.nextInt(2);

	            if (i1 > 1 && rng.nextInt(60) == 0) {
	                z += 10 + rng.nextInt(30);
	            }

	            int j1;
	            int k1;
	            int l1;

	            for (j1 = 0; j1 < l; ++j1) {
	                float f = (1.0F - (float)j1 / (float)l) * (float)i1;
	                k1 = MathHelper.ceiling_float_int(f);

	                for (l1 = -k1; l1 <= k1; ++l1) {
	                    float f1 = (float)MathHelper.abs_int(l1) - 0.25F;

	                    for (int i2 = -k1; i2 <= k1; ++i2) {
	                        float f2 = (float)MathHelper.abs_int(i2) - 0.25F;

	                        if ((l1 == 0 && i2 == 0 || f1 * f1 + f2 * f2 <= f * f) && (l1 != -k1 && l1 != k1 && i2 != -k1 && i2 != k1 || rng.nextFloat() <= 0.75F)) {
	                            Block block = world.getBlock(x + l1, z + j1, y + i2);

	                            if (isBlockReplaceable(block)) {
	                            	this.setBlockAndNotifyAdequately(world, x + l1, z + j1, y + i2, spikeBlock, 0);
	                            }

	                            if (j1 != 0 && k1 > 1) {
	                                block = world.getBlock(x + l1, z - j1, y + i2);

	                                if (isBlockReplaceable(block)) {
	                                    this.setBlockAndNotifyAdequately(world, x + l1, z - j1, y + i2, spikeBlock, 0);
	                                }
	                            }
	                        }
	                    }
	                }
	            }

	            j1 = i1 - 1;

	            if (j1 < 0) {
	                j1 = 0;
	            } else if (j1 > 1) {
	                j1 = 1;
	            }

	            for (int j2 = -j1; j2 <= j1; ++j2) {
	                k1 = -j1;

	                while (k1 <= j1) {
	                    l1 = z - 1;
	                    int k2 = 50;

	                    if (Math.abs(j2) == 1 && Math.abs(k1) == 1) {
	                        k2 = rng.nextInt(5);
	                    }

	                    while (true) {
	                        if (l1 > 50) {
	                            Block block1 = world.getBlock(x + j2, l1, y + k1);

	                            if (isBlockReplaceable(block1) || block1 == spikeBlock) {
	                                this.func_150515_a(world, x + j2, l1, y + k1, spikeBlock);
	                                --l1;
	                                --k2;

	                                if (k2 <= 0) {
	                                    l1 -= rng.nextInt(5) + 1;
	                                    k2 = rng.nextInt(5);
	                                }

	                                continue;
	                            }
	                        }

	                        ++k1;
	                        break;
	                    }
	                }
	            }

	            return true;
		}
    	
    }

    }
}
