package com.teammetallurgy.atum.world.biome;

import java.util.Random;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.WorldGenRuins;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenRuinedCity extends AtumBiomeGenBase {

	protected final WorldGenerator genRuins;
	
    public BiomeGenRuinedCity(AtumConfig.BiomeConfig config) {
        super(config);
        
        this.genRuins = new WorldGenRuins();
        
        super.setHeight(height_LowPlains);
        
        super.deadwoodRarity = -1;
        
        super.addDefaultSpawns();
    }
    
    @Override
    public void decorate(World world, Random rng, int chunkx, int chunkz) {
        int xx = chunkx + rng.nextInt(16) + 8;
        int yy = rng.nextInt(rng.nextInt(248) + 8);
        int zz = chunkz + rng.nextInt(16) + 8;

        //if (rng.nextInt(2) == 0) {
        	this.genRuins.generate(world, rng, xx, yy, zz);
        //}
        
        super.decorate(world, rng, chunkx, chunkz);
    }

}
