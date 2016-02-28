package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

public class BiomeGenSandDunes extends AtumBiomeGenBase {

    public BiomeGenSandDunes(AtumConfig.BiomeConfig config) {
        super(config);

        super.rootHeight = height_LowPlains.rootHeight + 0.1F;
        super.heightVariation = height_LowPlains.variation + 0.2F;
        
        super.fillerBlock = AtumBlocks.BLOCK_SAND;
        
        super.palmRarity *= 2;
        super.deadwoodRarity = -1;
        
        super.addDefaultSpawns();
    }

}
