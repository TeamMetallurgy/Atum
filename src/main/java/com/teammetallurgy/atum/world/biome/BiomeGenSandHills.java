package com.teammetallurgy.atum.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenSandHills extends AtumBiomeGenBase {

    public BiomeGenSandHills(BiomeGenBase.BiomeProperties properties) {
        super(properties);

        super.palmRarity *= 4;
        super.pyramidRarity = -1;
        super.deadwoodRarity = -1;

        super.addDefaultSpawns();
    }
}