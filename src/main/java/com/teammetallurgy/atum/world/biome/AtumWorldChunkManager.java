package com.teammetallurgy.atum.world.biome;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.*;

import java.util.List;
import java.util.Random;

public class AtumWorldChunkManager extends WorldChunkManager {

	public static final int BIOME_SCALE = 4;
	private GenLayer genBiomeLayer;
	
	public AtumWorldChunkManager(long seed) {
		
		GenLayer layerBiome = new GenLayerAtumBiome(seed);		
		for( int k = 0; k < BIOME_SCALE; ++k ) {
			layerBiome = new GenLayerZoom((long)(1000 + k), layerBiome);
		}
		
		GenLayer layerSmooth = new GenLayerSmooth(1000L, layerBiome);
		GenLayer layerVoronoi = new GenLayerVoronoiZoom(10L, layerSmooth);
		layerVoronoi.initWorldGenSeed(seed);
		
		GenLayer layerRiver = new GenLayerRiverInit(100L, layerSmooth);
		layerRiver = GenLayerZoom.magnify(1000L, layerRiver, 2);
		layerRiver = GenLayerZoom.magnify(1000L, layerRiver, BIOME_SCALE);
		layerRiver = new GenLayerAtumRiver(1L, layerRiver);
		layerRiver = new GenLayerSmooth(1000L, layerRiver);
		layerRiver = new GenLayerAtumRiverMix(100L, layerVoronoi, layerRiver);
		
		genBiomeLayer = layerRiver;
	}
	
    @Override
    public float[] getRainfall(float[] list, int x, int z, int width, int length) {
        if (list == null || list.length < width * length) {
            list = new float[width * length];
        }

        // easy cheatsy way
        // Arrays.fill(list, 0, width * length, 0.0F);
        
        final int[] cache = genBiomeLayer.getInts(x, z, width, length);
        
		for( int k = 0; k < length * width; ++k ) {
			// NB: reference wraps this in a try/catch with crash reporting
    		final float rain = BiomeGenBase.getBiome(cache[k]).getIntRainfall() / 65536.0F;
    		list[k] = (rain > 1.0F ? 1.0F : rain);
    	}
                
        return list;
    }

    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] list, int x, int z, int width, int length) {
        if (list == null || list.length < width * length) {
            list = new BiomeGenBase[width * length];
        }

        final int[] cache = genBiomeLayer.getInts(x, z, width, length);
        
		// NB: reference wraps this in a try/catch with crash reporting
		for( int k = 0; k < length * width; ++k ) {
    		list[k] = BiomeGenBase.getBiome(cache[k]);
    	}

        return list;
    }


    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] list, int x, int z, int width, int length, boolean checkCache) {
        IntCache.resetIntCache();

        if (list == null || list.length < width * length) {
            list = new BiomeGenBase[width * length];
        }

    	/*
    	 * Irritating scope on biomeCache, have to do it hard way :(
    	 * 
        if (checkCache && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
            BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiomegenbase1, 0, list, 0, width * length);
            return list;
        } else {
        	*/
            int[] cache = this.genBiomeLayer.getInts(x, z, width, length);

            for (int k = 0; k < width * length; ++k) {
                list[k] = BiomeGenBase.getBiome(cache[k]);
            }

            return list;
        /*}*/
    }
    
    @Override
    public BlockPos findBiomePosition(int p_150795_1_, int p_150795_2_, int p_150795_3_, List p_150795_4_, Random p_150795_5_) {
        IntCache.resetIntCache();
        int l = p_150795_1_ - p_150795_3_ >> 2;
        int i1 = p_150795_2_ - p_150795_3_ >> 2;
        int j1 = p_150795_1_ + p_150795_3_ >> 2;
        int k1 = p_150795_2_ + p_150795_3_ >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomeLayer.getInts(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;

        for (int k2 = 0; k2 < l1 * i2; ++k2)
        {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);

            if (p_150795_4_.contains(biomegenbase) && (chunkposition == null || p_150795_5_.nextInt(j2 + 1) == 0))
            {
                chunkposition = new ChunkPosition(l2, 0, i3);
                ++j2;
            }
        }

        return chunkposition;
    }
    
    @Override
    public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List p_76940_4_) {
    	// TODO: don't be lazy
    	return true;
    	
        //return p_76940_4_.contains(this.biomeGenerator);
    }

}
