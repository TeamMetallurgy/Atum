package com.teammetallurgy.atum.world.biome;

public class BiomeGenSandPlains extends AtumBiomeGenBase {

    public BiomeGenSandPlains(BiomeProperties properties) {
        super(properties);

        super.deadwoodRarity = -1;

        super.addDefaultSpawns();
    }
}