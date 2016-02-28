package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.handler.AtumConfig;

public class BiomeGenSandPlains extends AtumBiomeGenBase {

    public BiomeGenSandPlains(AtumConfig.BiomeConfig config) {
        super(config);
        
        super.setHeight(height_LowPlains.attenuate());
        
        super.deadwoodRarity = -1;
        
        super.addDefaultSpawns();
    }

}
