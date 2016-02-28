package com.teammetallurgy.atum.world.biome;

import java.util.Random;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class BiomeGenOasis extends AtumBiomeGenBase {

	protected final int lakeRarity = 3;
	protected final int waterLevel = 64;
	
    public BiomeGenOasis(AtumConfig.BiomeConfig config) {
        super(config);
        
        //super.topBlock = Blocks.grass;
        super.topBlock = AtumBlocks.BLOCK_FERTILESOIL;
        
        super.setHeight(height_ShallowWaters);
        
        super.setTemperatureRainfall(0.8F, 0.9F);
        
        // no hostile spawns here
        
        super.theBiomeDecorator.reedsPerChunk = 10;
        super.theBiomeDecorator.clayPerChunk = 1;
        super.theBiomeDecorator.waterlilyPerChunk = 2;
        
        super.palmRarity = 3;
        super.pyramidRarity = -1;
        super.deadwoodRarity = -1;
    }
    
    @Override
    public void decorate(World world, Random rng, int chunkx, int chunkz) {
        int xx = chunkx + rng.nextInt(16) + 8;
        int yy = rng.nextInt(rng.nextInt(248) + 8);
        int zz = chunkz + rng.nextInt(16) + 8;

        if (yy < waterLevel || rng.nextInt(lakeRarity) == 0) {
            (new WorldGenLakes(Blocks.water)).generate(world, rng, xx, yy, zz);
        }
        
        super.decorate(world, rng, chunkx, chunkz);
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rng, Block[] blocks, byte[] bytes, int x, int z, double stoneNoise) {   	
        double noise = plantNoise.func_151601_a((double)x * 0.25D, (double)z * 0.25D);
        
        boolean makingPond = false;
        int depth = 1 + rng.nextInt(5);

        if (noise > -0.0D) {
            int xx = x & 15;
            int zz = z & 15;
            int offset = blocks.length / 256;

            // if the block is visible to the sky and there is ground at "waterLevel",
            // turn it into shallow water instead:
            
            for (int yy = 255; yy >= 0; --yy) {
                int k = (zz * 16 + xx) * offset + yy;

                if (blocks[k] == null || blocks[k].getMaterial() != Material.air) {
                	if( makingPond ) {
                    	if( --depth == 0 ) break;
                    	blocks[k] = Blocks.water;
                    }else if (yy <= waterLevel && blocks[k] == this.topBlock) {
                        blocks[k] = Blocks.water;
                        if (noise < 0.12D) {
                            blocks[k + 1] = Blocks.waterlily;
                        }
                        makingPond = true;
                    } 
                }
            }
        }
        
        // In Atum biomes, genTerrainBlocks does what genBiomeTerrain normally does
        super.genTerrainBlocks(world, rng, blocks, bytes, x, z, stoneNoise);

    }
    
}
