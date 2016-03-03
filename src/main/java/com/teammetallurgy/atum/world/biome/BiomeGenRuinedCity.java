package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.WorldGenRuins;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

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
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        int xx = chunkX + random.nextInt(16) + 8;
        int yy = random.nextInt(random.nextInt(248) + 8);
        int zz = chunkZ + random.nextInt(16) + 8;

        this.genRuins.generate(world, random, new BlockPos(xx, yy, zz));

        super.decorate(world, random, chunkX, chunkZ);
    }
}