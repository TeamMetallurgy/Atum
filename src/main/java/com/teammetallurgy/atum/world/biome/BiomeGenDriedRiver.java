package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

public class BiomeGenDriedRiver extends AtumBiomeGenBase {

	public BiomeGenDriedRiver(AtumConfig.BiomeConfig config) {
		super(config);
		
		super.setHeight(height_RockyWaters);
		
		super.topBlock = AtumBlocks.BLOCK_LIMESTONEGRAVEL;
		super.fillerBlock = AtumBlocks.BLOCK_LIMESTONECOBBLE;
		
        super.setTemperatureRainfall(0.8F, 0.9F);
		
		super.palmRarity = -1;
		super.pyramidRarity = -1;
		super.deadwoodRarity = -1;
	}

}
