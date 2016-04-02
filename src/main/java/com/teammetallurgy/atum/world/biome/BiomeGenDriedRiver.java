package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;

public class BiomeGenDriedRiver extends AtumBiomeGenBase {

	public BiomeGenDriedRiver(BiomeProperties properties) {
		super(properties);

		super.topBlock = AtumBlocks.LIMESTONE_GRAVEL.getDefaultState();
		super.fillerBlock = AtumBlocks.LIMESTONE_CRACKED.getDefaultState();

		super.palmRarity = -1;
		super.pyramidRarity = -1;
		super.deadwoodRarity = -1;
	}
}