package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.handler.AtumConfig;

public class BiomeGenSandHills extends AtumBiomeGenBase {

    public BiomeGenSandHills(AtumConfig.BiomeConfig config) {
        super(config);
        
        super.setHeight(height_LowHills);
        //super.topBlock = Blocks.stone;
        
        super.palmRarity *= 4;
        super.pyramidRarity = -1;
        super.deadwoodRarity = -1;
        
        super.addDefaultSpawns();
    }
    
}
