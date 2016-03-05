package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockAtumBricks;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class AtumTeleporter extends Teleporter {
    private final WorldServer worldServerInstance;
    private final Random random;

    public AtumTeleporter(WorldServer worldServer) {
        super(worldServer);
        this.worldServerInstance = worldServer;
        this.random = new Random(worldServer.getSeed());
    }

    @Override
    public void placeInPortal(Entity entity, float rotationYaw) {
        if (this.worldServerInstance.provider.getDimensionId() != 1) {
            if (!this.placeInExistingPortal(entity, rotationYaw)) {
                this.makePortal(entity);
                this.placeInExistingPortal(entity, rotationYaw);
            }
        } else {
            int x = MathHelper.floor_double(entity.posX);
            int y = MathHelper.floor_double(entity.posY) - 1;
            int z = MathHelper.floor_double(entity.posZ);
            int l = 1;
            int i1 = 0;

            for (int j1 = -2; j1 <= 2; ++j1) {
                for (int k1 = -2; k1 <= 2; ++k1) {
                    for (int l1 = -1; l1 < 3; ++l1) {
                        int i2 = x + k1 * l + j1 * i1;
                        int j2 = y + l1;
                        int k2 = z + k1 * i1 - j1 * l;
                        boolean flag = l1 < 0;
                        this.worldServerInstance.setBlockState(new BlockPos(i2, j2, k2), flag ? Blocks.sandstone.getDefaultState() : Blocks.air.getDefaultState());
                    }
                }
            }

            entity.setLocationAndAngles((double) x, (double) y, (double) z, entity.rotationYaw, 0.0F);
            entity.motionX = entity.motionY = entity.motionZ = 0.0D;
        }
    }

    @Override
    public boolean makePortal(Entity entity) {
        int i = 16;
        double d0 = -1.0D;
        int j = MathHelper.floor_double(entity.posX);
        int k = MathHelper.floor_double(entity.posY);
        int l = MathHelper.floor_double(entity.posZ);
        int i1 = j;
        int j1 = k;
        int k1 = l;
        int l1 = 0;
        int i2 = this.random.nextInt(4);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int j2 = j - i; j2 <= j + i; ++j2) {
            double d1 = (double) j2 + 0.5D - entity.posX;

            for (int l2 = l - i; l2 <= l + i; ++l2) {
                double d2 = (double) l2 + 0.5D - entity.posZ;
                label142:

                for (int j3 = this.worldServerInstance.getActualHeight() - 1; j3 >= 0; --j3) {
                    if (this.worldServerInstance.isAirBlock(mutableBlockPos.set(j2, j3, l2))) {
                        while (j3 > 0 && this.worldServerInstance.isAirBlock(mutableBlockPos.set(j2, j3 - 1, l2))) {
                            --j3;
                        }

                        for (int k3 = i2; k3 < i2 + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;

                            if (k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4) {
                                for (int k4 = 0; k4 < 4; ++k4) {
                                    for (int l4 = -1; l4 < 4; ++l4) {
                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
                                        int j5 = j3 + l4;
                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
                                        mutableBlockPos.set(i5, j5, k5);

                                        if (l4 < 0 && !this.worldServerInstance.getBlockState(mutableBlockPos).getBlock().getMaterial().isSolid() || l4 >= 0 && !this.worldServerInstance.isAirBlock(mutableBlockPos)) {
                                            continue label142;
                                        }
                                    }
                                }
                            }

                            double d5 = (double) j3 + 0.5D - entity.posY;
                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;

                            if (d0 < 0.0D || d7 < d0) {
                                d0 = d7;
                                i1 = j2;
                                j1 = j3;
                                k1 = l2;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (int l5 = j - i; l5 <= j + i; ++l5) {
                double d3 = (double) l5 + 0.5D - entity.posX;

                for (int j6 = l - i; j6 <= l + i; ++j6) {
                    double d4 = (double) j6 + 0.5D - entity.posZ;
                    label562:

                    for (int i7 = this.worldServerInstance.getActualHeight() - 1; i7 >= 0; --i7) {
                        if (this.worldServerInstance.isAirBlock(mutableBlockPos.set(l5, i7, j6))) {
                            while (i7 > 0 && this.worldServerInstance.isAirBlock(mutableBlockPos.set(l5, i7 - 1, j6))) {
                                --i7;
                            }

                            for (int k7 = i2; k7 < i2 + 2; ++k7) {
                                int j8 = k7 % 2;
                                int j9 = 1 - j8;

                                for (int j10 = 0; j10 < 4; ++j10) {
                                    for (int j11 = -1; j11 < 4; ++j11) {
                                        int j12 = l5 + (j10 - 1) * j8;
                                        int i13 = i7 + j11;
                                        int j13 = j6 + (j10 - 1) * j9;
                                        mutableBlockPos.set(j12, i13, j13);

                                        if (j11 < 0 && !this.worldServerInstance.getBlockState(mutableBlockPos).getBlock().getMaterial().isSolid() || j11 >= 0 && !this.worldServerInstance.isAirBlock(mutableBlockPos)) {
                                            continue label562;
                                        }
                                    }
                                }

                                double d6 = (double) i7 + 0.5D - entity.posY;
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;

                                if (d0 < 0.0D || d8 < d0) {
                                    d0 = d8;
                                    i1 = l5;
                                    j1 = i7;
                                    k1 = j6;
                                    l1 = k7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i6 = i1;
        int k2 = j1;
        int k6 = k1;
        int l6 = l1 % 2;
        int i3 = 1 - l6;

        if (l1 % 4 >= 2) {
            l6 = -l6;
            i3 = -i3;
        }

        if (d0 < 0.0D) {
            j1 = MathHelper.clamp_int(j1, 70, this.worldServerInstance.getActualHeight() - 10);
            k2 = j1;

            for (int j7 = -1; j7 <= 1; ++j7) {
                for (int l7 = 1; l7 < 3; ++l7) {
                    for (int k8 = -1; k8 < 3; ++k8) {
                        int k9 = i6 + (l7 - 1) * l6 + j7 * i3;
                        int k10 = k2 + k8;
                        int k11 = k6 + (l7 - 1) * i3 - j7 * l6;
                        boolean flag = k8 < 0;
                        this.worldServerInstance.setBlockState(new BlockPos(k9, k10, k11), flag ? Blocks.sandstone.getDefaultState() : Blocks.air.getDefaultState());
                    }
                }
            }
        }

        IBlockState portalState = AtumBlocks.PORTAL.getDefaultState().withProperty(BlockPortal.AXIS, l6 != 0 ? EnumFacing.Axis.X : EnumFacing.Axis.Z);
        IBlockState sandState;

        if (entity.dimension == 0) {
            sandState = Blocks.sandstone.getDefaultState();
        } else {
            sandState = AtumBlocks.LIMESTONEBRICK.getDefaultState().withProperty(BlockAtumBricks.VARIANT, BlockAtumBricks.EnumType.LARGE);
        }
        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                this.worldServerInstance.setBlockState(new BlockPos(l + x1, i1, j1 + z1), sandState, 2);
            }
        }
        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                if (x1 == 2 || z1 == 2 || x1 == -2 || z1 == -2) {
                    this.worldServerInstance.setBlockState(new BlockPos(l + x1, i1 + 1, j1 + z1), sandState, 2);
                }
            }
        }
        for (int y1 = 2; y1 < 4; y1++) {
            for (int x1 = -2; x1 < 3; x1++) {
                for (int z1 = -2; z1 < 3; z1++) {
                    if ((x1 == 2 && z1 == 2) || (x1 == -2 && z1 == 2) || (x1 == 2 && z1 == -2) || (x1 == -2 && z1 == -2)) {
                        this.worldServerInstance.setBlockState(new BlockPos(l + x1, i1 + y1, j1 + z1), sandState, 2);
                    }
                }
            }
        }

        for (int x1 = -1; x1 < 2; x1++) {
            for (int z1 = -1; z1 < 2; z1++) {
                this.worldServerInstance.setBlockState(new BlockPos(l + x1, i1 + 1, j1 + z1), portalState, 2);
            }
        }
        return true;
    }
}