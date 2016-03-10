package com.teammetallurgy.atum.world.decorators;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockAtumLog;
import com.teammetallurgy.atum.blocks.BlockAtumPlank;
import com.teammetallurgy.atum.blocks.BlockLeave;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenPalm extends WorldGenAbstractTree {
    private static final IBlockState blockLog = AtumBlocks.LOG.getDefaultState().withProperty(BlockAtumLog.VARIANT, BlockAtumPlank.EnumType.PALM);
    private static final IBlockState blockLeaves = AtumBlocks.LEAVES.getDefaultState().withProperty(BlockLeave.VARIANT, BlockAtumPlank.EnumType.PALM).withProperty(BlockLeave.CHECK_DECAY, Boolean.valueOf(false));
    private final int minTreeHeight;
    private final IBlockState metaWood;
    private final IBlockState metaLeaves;

    public WorldGenPalm(boolean notify) {
        this(notify, 5, blockLog, blockLeaves);
    }

    public WorldGenPalm(boolean notify, int minTreeHeight) {
        this(notify, minTreeHeight, blockLog, blockLeaves);
    }

    public WorldGenPalm(boolean notify, int minTreeHeight, IBlockState wood, IBlockState leaves) {
        super(notify);
        this.minTreeHeight = minTreeHeight;
        this.metaWood = wood;
        this.metaLeaves = leaves;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        int i = random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;
        Block blocks = world.getBlockState(pos.down()).getBlock();
        if ((blocks == AtumBlocks.SAND || blocks == AtumBlocks.FERTILE_SOIL || blocks == Blocks.dirt) && pos.getY() >= 1 && pos.getY() + i + 1 <= 256) {
            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j) {

                int k = 1;
                if (j == pos.getY()) {
                    k = 0;
                }

                if (j >= pos.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

                for (int l = pos.getX() - k; l <= pos.getX() + k && flag; ++l) {
                    for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < 256) {
                            if (!this.isReplaceable(world, mutableBlockPos.set(l, j, i1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                BlockPos down = pos.down();
                Block blockDown = world.getBlockState(down).getBlock();

                if (pos.getY() < 256 - i - 1) {
                    return false;
                } else {
                    blockDown.onPlantGrow(world, down, pos);
                    int i3 = pos.getX();
                    int j1 = pos.getZ();
                    int k1 = 0;

                    BlockPos pos1 = new BlockPos(i3, k1, j1);

                    for (int j3 = -3; j3 <= 3; ++j3) {
                        for (int i4 = -3; i4 <= 3; ++i4) {
                            if (Math.abs(j3) != 3 || Math.abs(i4) != 3) {
                                this.spawnLeaf(world, pos1.add(j3, 0, i4));
                            }
                        }
                    }

                    pos1 = pos1.up();

                    for (int k3 = -1; k3 <= 1; ++k3) {
                        for (int j4 = -1; j4 <= 1; ++j4) {
                            this.spawnLeaf(world, pos1.add(k3, 0, j4));
                        }
                    }

                    this.spawnLeaf(world, pos1.east(2));
                    this.spawnLeaf(world, pos1.west(2));
                    this.spawnLeaf(world, pos1.south(2));
                    this.spawnLeaf(world, pos1.north(2));

                    if (k1 > 0) {
                        BlockPos blockpos3 = new BlockPos(i3, k1, j1);

                        for (int i5 = -2; i5 <= 2; ++i5) {
                            for (int k5 = -2; k5 <= 2; ++k5) {
                                if (Math.abs(i5) != 2 || Math.abs(k5) != 2) {
                                    this.spawnLeaf(world, blockpos3.add(i5, 0, k5));
                                }
                            }
                        }

                        blockpos3 = blockpos3.up();

                        for (int j5 = -1; j5 <= 1; ++j5) {
                            for (int l5 = -1; l5 <= 1; ++l5) {
                                this.spawnLeaf(world, blockpos3.add(j5, 0, l5));
                            }
                        }
                    } //TODO Test

                    /*this.spawnLeaf(world, par3, par4 + i + 1, par5);

                    for (int block = -1; block <= 1; ++block) {
                        for (int z = -1; z <= 1; ++z) {
                            if (block != 0 || z != 0) {
                                this.spawnLeaf(world, par3 + block, par4 + l, par5 + z);
                            }
                        }
                    }

                    this.spawnLeaf(world, par3 + 2, par4 + l, par5);
                    this.spawnLeaf(world, par3 - 2, par4 + l, par5);
                    this.spawnLeaf(world, par3, par4 + l, par5 + 2);
                    this.spawnLeaf(world, par3, par4 + l, par5 - 2);
                    this.spawnLeaf(world, par3, par4 + l - 1, par5 - 2);
                    this.spawnLeaf(world, par3, par4 + l - 1, par5 + 2);
                    this.spawnLeaf(world, par3 + 2, par4 + l - 1, par5);
                    this.spawnLeaf(world, par3 - 2, par4 + l - 1, par5);
                    this.spawnLeaf(world, par3, par4 + l - 1, par5 - 3);
                    this.spawnLeaf(world, par3, par4 + l - 1, par5 + 3);
                    this.spawnLeaf(world, par3 + 3, par4 + l - 1, par5);
                    this.spawnLeaf(world, par3 - 3, par4 + l - 1, par5);
                    if (random.nextInt(100) < 15) {
                        world.setBlock(par3 + 1, par4 + l - 1, par5, AtumBlocks.DATEBLOCK, 0, 2);
                    }

                    if (random.nextInt(100) < 15) {
                        world.setBlock(par3 - 1, par4 + l - 1, par5, AtumBlocks.DATEBLOCK, 0, 2);
                    }

                    if (random.nextInt(100) < 15) {
                        world.setBlock(par3, par4 + l - 1, par5 + 1, AtumBlocks.DATEBLOCK, 0, 2);
                    }

                    if (random.nextInt(100) < 15) {
                        world.setBlock(par3, par4 + l - 1, par5 - 1, AtumBlocks.DATEBLOCK, 0, 2);*/ //TODO Proper solution for leaves and dates
                }

                for (int j3 = 0; j3 < i; ++j3) {
                    BlockPos upN = pos.up(j3);
                    Block block2 = world.getBlockState(upN).getBlock();

                    if (block2.isAir(world, upN) || block2.isLeaves(world, upN)) {
                        this.setBlockAndNotifyAdequately(world, pos.up(j3), this.metaWood);
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    public void spawnLeaf(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        if (world.isAirBlock(pos) || block.canBeReplacedByLeaves(world, pos)) {
            this.setBlockAndNotifyAdequately(world, pos, this.metaLeaves);
        }
    }
}