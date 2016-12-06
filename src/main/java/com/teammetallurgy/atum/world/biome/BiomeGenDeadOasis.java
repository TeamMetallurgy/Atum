package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;

public class BiomeGenDeadOasis extends AtumBiome {

    public BiomeGenDeadOasis(BiomeProperties properties) {
        super(properties);

        super.topBlock = AtumBlocks.LIMESTONE_CRACKED.getDefaultState();

        //no hostile spawns here

        super.palmRarity = 9;
        super.pyramidRarity = -1;
        super.deadwoodRarity = -1;
    }
}