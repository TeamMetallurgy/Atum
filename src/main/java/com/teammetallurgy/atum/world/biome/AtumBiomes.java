package com.teammetallurgy.atum.world.biome;

import java.util.List;

import com.google.common.collect.Lists;
import com.teammetallurgy.atum.handler.AtumConfig;

public class AtumBiomes {
	
	public static List<AtumBiomeGenBase> biomes = Lists.newArrayList();
	public static AtumBiomeGenBase riverBiome;
	
	public void register() {
		registerBiome(new BiomeGenSandPlains(AtumConfig.BiomeConfig.SAND_PLAINS));
		registerBiome(new BiomeGenSandDunes(AtumConfig.BiomeConfig.SAND_DUNES));
		registerBiome(new BiomeGenSandHills(AtumConfig.BiomeConfig.SAND_HILLS));
		registerBiome(new BiomeGenLimestoneCrags(AtumConfig.BiomeConfig.LIMESTONE_CRAGS));
		registerBiome(new BiomeGenLimestoneMountains(AtumConfig.BiomeConfig.LIMESTONE_MOUNTAINS));
		// TODO: Disabled until fixed
		// registerBiome(new BiomeGenOasis(AtumConfig.BiomeConfig.OASIS));
		// registerBiome(new BiomeGenDeadOasis(AtumConfig.BiomeConfig.DEAD_OASIS));
		registerBiome(new BiomeGenRuinedCity(AtumConfig.BiomeConfig.RUINED_CITY));
		
		riverBiome = new BiomeGenDriedRiver(AtumConfig.BiomeConfig.DRIED_RIVER);
	}
	
	// in case we want to do anything extra at registration time...
	private void registerBiome(AtumBiomeGenBase biome) {
		biomes.add(biome);
	}

}
