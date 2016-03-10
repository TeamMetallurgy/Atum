package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenLimestoneCrags extends AtumBiomeGenBase {

    private WorldGenerator genSpikes;

    public BiomeGenLimestoneCrags(AtumConfig.BiomeConfig config) {
        super(config);

        super.palmRarity = -1;
        super.pyramidRarity = -1;
        super.deadwoodRarity = 2;

        this.genSpikes = new WorldGenLimestoneSpike();

        super.addDefaultSpawns();
    }

    @Override
    public void decorate(World world, Random random, BlockPos pos) {
        for (int i = 0; i < 3; ++i) {
            int j = random.nextInt(16) + 8;
            int k = random.nextInt(16) + 8;
            this.genSpikes.generate(world, random, world.getHeight(pos.add(j, 0, k)));
        }

        super.decorate(world, random, pos);
    }

    /** Adapted from WorldGenIceSpike */
    public class WorldGenLimestoneSpike extends WorldGenerator {
        private final Block spikeBlock = AtumBlocks.LIMESTONE;
        private final Block groundBlock = AtumBlocks.SAND;

        private boolean isBlockReplaceable(Block block) {
            return block.getMaterial() == Material.air || block == AtumBlocks.SAND || block == AtumBlocks.SAND_LAYERED || block == Blocks.dirt;
        }

        @Override
        public boolean generate(World world, Random rand, BlockPos pos) {

            // find the surface
            while (world.isAirBlock(pos) && pos.getY() > 2) {
                pos = pos.down();
            }

            if (world.getBlockState(pos).getBlock() != groundBlock) {
                return false;
            } else {
                pos = pos.up(rand.nextInt(4));
                int i = rand.nextInt(4) + 7;
                int j = i / 4 + rand.nextInt(2);

                if (j > 1 && rand.nextInt(60) == 0) {
                    pos = pos.up(10 + rand.nextInt(30));
                }

                for (int k = 0; k < i; ++k) {
                    float f = (1.0F - (float) k / (float) i) * (float) j;
                    int l = MathHelper.ceiling_float_int(f);

                    for (int i1 = -l; i1 <= l; ++i1) {
                        float f1 = (float) MathHelper.abs_int(i1) - 0.25F;

                        for (int j1 = -l; j1 <= l; ++j1) {
                            float f2 = (float) MathHelper.abs_int(j1) - 0.25F;

                            if ((i1 == 0 && j1 == 0 || f1 * f1 + f2 * f2 <= f * f) && (i1 != -l && i1 != l && j1 != -l && j1 != l || rand.nextFloat() <= 0.75F)) {
                                Block block = world.getBlockState(pos.add(i1, k, j1)).getBlock();

                                if (isBlockReplaceable(block)) {
                                    this.setBlockAndNotifyAdequately(world, pos.add(i1, k, j1), spikeBlock.getDefaultState());
                                }

                                if (k != 0 && l > 1) {
                                    block = world.getBlockState(pos.add(i1, -k, j1)).getBlock();

                                    if (isBlockReplaceable(block)) {
                                        this.setBlockAndNotifyAdequately(world, pos.add(i1, -k, j1), spikeBlock.getDefaultState());

                                    }
                                }
                            }
                        }
                    }
                }

                int k1 = j - 1;

                if (k1 < 0) {
                    k1 = 0;
                } else if (k1 > 1) {
                    k1 = 1;
                }

                for (int l1 = -k1; l1 <= k1; ++l1) {
                    for (int i2 = -k1; i2 <= k1; ++i2) {
                        BlockPos blockpos = pos.add(l1, -1, i2);
                        int j2 = 50;

                        if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
                            j2 = rand.nextInt(5);
                        }

                        while (blockpos.getY() > j2) {
                            Block block1 = world.getBlockState(blockpos).getBlock();

                            if (isBlockReplaceable(block1) || block1 == spikeBlock) {
                                break;
                            }

                            this.setBlockAndNotifyAdequately(world, blockpos, spikeBlock.getDefaultState());
                            blockpos = blockpos.down();
                            --j2;


                            if (j2 <= 0) {
                                blockpos = blockpos.down(rand.nextInt(5) + 1);
                                j2 = rand.nextInt(5);
                            }

                            continue;
                        }
                    }
                }
            }
            return true;
        }
    }
}