package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.Random;

public class BiomeGenLimestoneMountains extends AtumBiomeGenBase {

    public BiomeGenLimestoneMountains(AtumConfig.BiomeConfig config) {
        super(config);

        super.fillerBlock = AtumBlocks.STONE.getDefaultState();

        super.setHeight(height_MidHills);

        super.palmRarity = -1;
        super.pyramidRarity = -1;
        super.deadwoodRarity *= 2;

        super.addDefaultSpawns();
    }

    @Override
    public void genTerrainBlocks(World world, Random rng, Block[] blocks, byte[] bytes, int x, int z, double noise) {
        //final int y = world.getHeightValue(x, z);

        if ( /*y <= 72 ||*/ noise < 1.0D) {
            super.topBlock = AtumBlocks.SAND.getDefaultState();
        } else {
            super.topBlock = AtumBlocks.STONE.getDefaultState();
        }

        // something weird's going on here...
        //super.genBiomeTerrain(world, rng, blocks, bytes, x, z, noise);
        super.genTerrainBlocks(world, rng, blocks, bytes, x, z, noise);
    }
}