package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class BiomeGenLimestoneMountains extends AtumBiome {

    public BiomeGenLimestoneMountains(BiomeProperties properties) {
        super(properties);

        super.fillerBlock = AtumBlocks.LIMESTONE.getDefaultState();

        super.palmRarity = -1;
        super.pyramidRarity = -1;
        super.deadwoodRarity *= 2;

        super.addDefaultSpawns();
    }

    @Override
    public void genTerrainBlocks(World world, Random random, ChunkPrimer chunkPrimer, int x, int z, double stoneNoise) {
        //final int y = world.getHeightValue(x, z);

        if ( /*y <= 72 ||*/ stoneNoise < 1.0D) {
            super.topBlock = AtumBlocks.SAND.getDefaultState();
        } else {
            super.topBlock = AtumBlocks.LIMESTONE.getDefaultState();
        }

        // something weird's going on here...
        super.genTerrainBlocks(world, random, chunkPrimer, x, z, stoneNoise);
    }
}