package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAtumRiverMix extends GenLayer {
    private GenLayer biomePatternGeneratorChain;
    private GenLayer riverPatternGeneratorChain;

    public GenLayerAtumRiverMix(long scale, GenLayer biomeChain, GenLayer riverChain) {
        super(scale);
        this.biomePatternGeneratorChain = biomeChain;
        this.riverPatternGeneratorChain = riverChain;
    }

    public void initWorldGenSeed(long seed) {
        this.biomePatternGeneratorChain.initWorldGenSeed(seed);
        this.riverPatternGeneratorChain.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int length) {
        int[] bInts = this.biomePatternGeneratorChain.getInts(x, z, width, length);
        int[] rInts = this.riverPatternGeneratorChain.getInts(x, z, width, length);
        int[] cache = IntCache.getIntCache(width * length);

        final int badId_1 = AtumConfig.BiomeConfig.SAND_DUNES.getID();
        final int badId_2 = AtumConfig.BiomeConfig.SAND_HILLS.getID();
        final int badId_3 = AtumConfig.BiomeConfig.LIMESTONE_MOUNTAINS.getID();
        final int riverId = AtumConfig.BiomeConfig.DRIED_RIVER.getID();

        for (int idx = 0; idx < width * length; ++idx) {
            if (rInts[idx] == riverId && bInts[idx] != badId_1 && bInts[idx] != badId_2 && bInts[idx] != badId_3) {
                cache[idx] = rInts[idx] & 255;
            } else {
                cache[idx] = bInts[idx];
            }
        }

        return cache;
    }
}