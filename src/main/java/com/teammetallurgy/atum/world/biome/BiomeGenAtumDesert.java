package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.WorldGenDeadwood;
import com.teammetallurgy.atum.world.decorators.WorldGenPalm;
import com.teammetallurgy.atum.world.decorators.WorldGenPyramid;
import com.teammetallurgy.atum.world.decorators.WorldGenRuins;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

@Deprecated
public class BiomeGenAtumDesert extends AtumBiomeGenBase {

    public WorldGenerator treeGenerator;
    public WorldGenerator palmGenerator;
    public WorldGenerator ruinsGenerator;

    public BiomeGenAtumDesert(AtumConfig.BiomeConfig config) {
        super(config);

        this.treeGenerator = new WorldGenDeadwood(true);
        this.palmGenerator = new WorldGenPalm(true);
        this.ruinsGenerator = new WorldGenRuins();

        super.addDefaultSpawns();
    }

    @Override
    public void decorate(World world, Random random, int par3, int par4) {
        super.decorate(world, random, par3, par4);
        int k;
        int l;

        if (random.nextInt(100) == 0) {
            k = par3 + random.nextInt(16) + 8;
            l = par4 + random.nextInt(16) + 8;
            this.ruinsGenerator.generate(world, random, k, world.getHeightValue(k, l) + 1, l);
        }

        int height;
        if (random.nextInt(5) == 0) {
            k = par3 + random.nextInt(16) + 8;
            l = par4 + random.nextInt(16) + 8;
            height = random.nextInt(4) + 5;
            (new WorldGenPalm(true, height, AtumBlocks.LOG.getDefaultState(), AtumBlocks.LEAVES.getDefaultState())).generate(world, random, k, world.getHeightValue(k, l), l);
        }

        if (random.nextInt(7) == 0) {
            k = par3 + random.nextInt(16) + 8;
            l = par4 + random.nextInt(16) + 8;
            height = random.nextInt(4) + 4;
            this.treeGenerator.generate(world, random, k, world.getHeightValue(k, l), l);
        }

        if (random.nextInt(240) == 0) {
            k = par3 + random.nextInt(16) + 8;
            l = par4 + random.nextInt(16) + 8;
            (new WorldGenPyramid()).generate(world, random, k, world.getHeightValue(k, l), l);
        }

        if (random.nextInt(1000) == 0) {
            k = par3 + random.nextInt(16) + 8;
            l = par4 + random.nextInt(16) + 8;
            height = random.nextInt(10);
            if (height <= 20) {
                height += 30;
            }
            //(new WorldGenPalace()).generate(world, par2Random, k, world.getHeightValue(k, l), l);
        }
    }
}