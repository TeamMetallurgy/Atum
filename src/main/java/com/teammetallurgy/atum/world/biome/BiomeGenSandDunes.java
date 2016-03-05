package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

public class BiomeGenSandDunes extends AtumBiomeGenBase {

    public BiomeGenSandDunes(AtumConfig.BiomeConfig config) {
        super(config);

        super.minHeight = height_LowPlains.rootHeight + 0.1F;
        super.maxHeight = height_LowPlains.variation + 0.2F;

        super.fillerBlock = AtumBlocks.SAND.getDefaultState();

        super.palmRarity *= 2;
        super.deadwoodRarity = -1;

        super.addDefaultSpawns();
    }
}