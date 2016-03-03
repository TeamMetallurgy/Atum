package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

public class BiomeGenDeadOasis extends AtumBiomeGenBase {

    public BiomeGenDeadOasis(AtumConfig.BiomeConfig config) {
        super(config);

        super.topBlock = AtumBlocks.LIMESTONECOBBLE.getDefaultState();

        super.setHeight(height_ShallowWaters);

        super.setTemperatureRainfall(0.8F, 0.9F);

        //no hostile spawns here

        super.palmRarity = 9;
        super.pyramidRarity = -1;
        super.deadwoodRarity = -1;
    }
}