package com.teammetallurgy.atum.world.biome;

import java.util.Random;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class BiomeGenDeadOasis extends AtumBiomeGenBase {

    public BiomeGenDeadOasis(AtumConfig.BiomeConfig config) {
        super(config);
        
        super.topBlock = AtumBlocks.BLOCK_LIMESTONECOBBLE;
        
        super.setHeight(height_ShallowWaters);
        
        super.setTemperatureRainfall(0.8F, 0.9F);
        
        // no hostile spawns here
        
        super.palmRarity = 9;
        super.pyramidRarity = -1;
        super.deadwoodRarity = -1;
    }
    
}
