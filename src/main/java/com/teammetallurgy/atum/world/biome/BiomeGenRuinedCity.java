package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.WorldGenRuins;
import net.minecraft.util.math.BlockPos;
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
    public void decorate(World world, Random random, BlockPos pos) {
        int x = random.nextInt(16) + 8;
        int z = random.nextInt(16) + 8;
        int height = world.getHeight(pos.add(x, 0, z)).getY() * 2;

        if (height > 0) {
            int randomHeight = random.nextInt(height);
            this.genRuins.generate(world, random, pos.add(x, randomHeight, z));
        }

        super.decorate(world, random, pos);
    }
}