package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AtumTeleporter extends Teleporter {

    private final WorldServer worldServerInstance;
    private final Random random;

    private final LongHashMap destinationCoordinateCache = new LongHashMap();
    private final List destinationCoordinateKeys = new ArrayList();

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
    public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
        short short1 = 128;
        double d3 = -1.0D;
        int x = 0;
        int y = 0;
        int z = 0;
        int l = MathHelper.floor_double(entity.posX);
        int i1 = MathHelper.floor_double(entity.posZ);
        long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
        boolean flag = true;
        double d4;
        int k1;
        int k2;
        double var46;
        if (this.destinationCoordinateCache.containsItem(j1)) {
            AtumPortalPosition portalposition = (AtumPortalPosition) this.destinationCoordinateCache.getValueByKey(j1);
            d3 = 0.0D;
            x = portalposition.posX;
            y = portalposition.posY;
            z = portalposition.posZ;
            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
            flag = false;
        } else {
            for (k1 = l - short1; k1 <= l + short1; ++k1) {
                var46 = (double) k1 + 0.5D - entity.posX;
                for (int d9 = i1 - short1; d9 <= i1 + short1; ++d9) {
                    double d6 = (double) d9 + 0.5D - entity.posZ;

                    for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2) {
                        if (this.worldServerInstance.getBlock(k1, k2, d9) == AtumBlocks.PORTAL) {
                            while (this.worldServerInstance.getBlock(k1, k2 - 1, d9) == AtumBlocks.PORTAL) {
                                --k2;
                            }

                            d4 = (double) k2 + 0.5D - entity.posY;
                            double l2 = var46 * var46 + d4 * d4 + d6 * d6;
                            if (d3 < 0.0D || l2 < d3) {
                                d3 = l2;
                                x = k1;
                                y = k2;
                                z = d9;
                            }
                        }
                    }
                }
            }
        }

        if (d3 >= 0.0D) {
            if (flag) {
                this.destinationCoordinateCache.add(j1, new AtumPortalPosition(x, y, z, this.worldServerInstance.getTotalWorldTime()));
                this.destinationCoordinateKeys.add(Long.valueOf(j1));
            }

            var46 = (double) x + 0.5D;
            double var47 = (double) y + 0.5D;
            d4 = (double) z + 0.5D;
            int j2 = -1;
            if (this.worldServerInstance.getBlock(x - 1, y, z) == AtumBlocks.PORTAL) {
                j2 = 2;
            }

            if (this.worldServerInstance.getBlock(x + 1, y, z) == AtumBlocks.PORTAL) {
                j2 = 0;
            }

            if (this.worldServerInstance.getBlock(x, y, z - 1) == AtumBlocks.PORTAL) {
                j2 = 3;
            }

            if (this.worldServerInstance.getBlock(x, y, z + 1) == AtumBlocks.PORTAL) {
                j2 = 1;
            }

            k2 = entity.getTeleportDirection();
            if (j2 > -1) {
                int var48 = Direction.rotateLeft[j2];
                int i3 = Direction.offsetX[j2];
                int j3 = Direction.offsetZ[j2];
                int k3 = Direction.offsetX[var48];
                int l3 = Direction.offsetZ[var48];
                boolean flag1 = !this.worldServerInstance.isAirBlock(x + i3 + k3, y, z + j3 + l3) || !this.worldServerInstance.isAirBlock(x + i3 + k3, y + 1, z + j3 + l3);
                boolean flag2 = !this.worldServerInstance.isAirBlock(x + i3, y, z + j3) || !this.worldServerInstance.isAirBlock(x + i3, y + 1, z + j3);
                if (flag1 && flag2) {
                    j2 = Direction.rotateOpposite[j2];
                    var48 = Direction.rotateOpposite[var48];
                    i3 = Direction.offsetX[j2];
                    j3 = Direction.offsetZ[j2];
                    k3 = Direction.offsetX[var48];
                    l3 = Direction.offsetZ[var48];
                    k1 = x - k3;
                    var46 -= (double) k3;
                    int f1 = z - l3;
                    d4 -= (double) l3;
                    flag1 = !this.worldServerInstance.isAirBlock(k1 + i3 + k3, y, f1 + j3 + l3) || !this.worldServerInstance.isAirBlock(k1 + i3 + k3, y + 1, f1 + j3 + l3);
                    flag2 = !this.worldServerInstance.isAirBlock(k1 + i3, y, f1 + j3) || !this.worldServerInstance.isAirBlock(k1 + i3, y + 1, f1 + j3);
                }

                float var49 = 0.5F;
                float f2 = 0.5F;
                if (!flag1 && flag2) {
                    var49 = 1.0F;
                } else if (flag1 && !flag2) {
                    var49 = 0.0F;
                } else if (flag1 && flag2) {
                    f2 = 0.0F;
                }

                var46 += (double) ((float) k3 * var49 + f2 * (float) i3);
                d4 += (double) ((float) l3 * var49 + f2 * (float) j3);
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = 0.0F;
                if (j2 == k2) {
                    f3 = 1.0F;
                    f4 = 1.0F;
                } else if (j2 == Direction.rotateOpposite[k2]) {
                    f3 = -1.0F;
                    f4 = -1.0F;
                } else if (j2 == Direction.rotateRight[k2]) {
                    f5 = 1.0F;
                    f6 = -1.0F;
                } else {
                    f5 = -1.0F;
                    f6 = 1.0F;
                }

                double d10 = entity.motionX;
                double d11 = entity.motionZ;
                entity.motionX = d10 * (double) f3 + d11 * (double) f6;
                entity.motionZ = d10 * (double) f5 + d11 * (double) f4;
                entity.rotationYaw = rotationYaw - (float) (k2 * 90) + (float) (j2 * 90);
            } else {
                entity.motionX = entity.motionY = entity.motionZ = 0.0D;
            }

            entity.setLocationAndAngles(var46, var47, d4, entity.rotationYaw, entity.rotationPitch);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean makePortal(Entity entity) {
        byte b0 = 16;
        double d0 = -1.0D;
        int entityX = MathHelper.floor_double(entity.posX);
        int entityY = MathHelper.floor_double(entity.posY);
        int entityZ = MathHelper.floor_double(entity.posZ);
        int l = entityX;
        int i1 = entityY;
        int j1 = entityZ;
        int k1 = 0;
        int l1 = this.random.nextInt(4);

        int i2;
        double d1;
        double d2;
        int k2 = 0, j2 = 0, i3 = 0, l2 = 0, k3 = 0, j3 = 0, i4 = 0, l3 = 0, k4 = 0, j4 = 0;
        double d3;
        double d4;
        int i5;
        for (i2 = entityX - b0; i2 <= entityX + b0; ++i2) {
            d1 = (double) i2 + 0.5D - entity.posX;

            for (j2 = entityZ - b0; j2 <= entityZ + b0; ++j2) {
                d2 = (double) j2 + 0.5D - entity.posZ;

                label272:
                for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2) {
                    if (this.worldServerInstance.isAirBlock(i2, k2, j2)) {
                        while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2)) {
                            --k2;
                        }

                        for (i3 = l1; i3 < l1 + 4; ++i3) {
                            l2 = i3 % 2;
                            k3 = 1 - l2;
                            if (i3 % 4 >= 2) {
                                l2 = -l2;
                                k3 = -k3;
                            }

                            for (j3 = 0; j3 < 3; ++j3) {
                                for (i4 = 0; i4 < 4; ++i4) {
                                    for (l3 = -1; l3 < 4; ++l3) {
                                        k4 = i2 + (i4 - 1) * l2 + j3 * k3;
                                        j4 = k2 + l3;
                                        i5 = j2 + (i4 - 1) * k3 - j3 * l2;
                                        if (l3 < 0 && !this.worldServerInstance.getBlock(k4, j4, i5).getMaterial().isSolid() || l3 >= 0 && !this.worldServerInstance.isAirBlock(k4, j4, i5)) {
                                            continue label272;
                                        }
                                    }
                                }
                            }

                            d4 = (double) k2 + 0.5D - entity.posY;
                            d3 = d1 * d1 + d4 * d4 + d2 * d2;
                            if (d0 < 0.0D || d3 < d0) {
                                d0 = d3;
                                l = i2;
                                i1 = k2;
                                j1 = j2;
                                k1 = i3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (i2 = entityX - b0; i2 <= entityX + b0; ++i2) {
                d1 = (double) i2 + 0.5D - entity.posX;

                for (j2 = entityZ - b0; j2 <= entityZ + b0; ++j2) {
                    d2 = (double) j2 + 0.5D - entity.posZ;

                    label220:
                    for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2) {
                        if (this.worldServerInstance.isAirBlock(i2, k2, j2)) {
                            while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2)) {
                                --k2;
                            }

                            for (i3 = l1; i3 < l1 + 2; ++i3) {
                                l2 = i3 % 2;
                                k3 = 1 - l2;

                                for (j3 = 0; j3 < 4; ++j3) {
                                    for (i4 = -1; i4 < 4; ++i4) {
                                        l3 = i2 + (j3 - 1) * l2;
                                        k4 = k2 + i4;
                                        j4 = j2 + (j3 - 1) * k3;
                                        if (i4 < 0 && !this.worldServerInstance.getBlock(l3, k4, j4).getMaterial().isSolid() || i4 >= 0 && !this.worldServerInstance.isAirBlock(l3, k4, j4)) {
                                            continue label220;
                                        }
                                    }
                                }

                                d4 = (double) k2 + 0.5D - entity.posY;
                                d3 = d1 * d1 + d4 * d4 + d2 * d2;
                                if (d0 < 0.0D || d3 < d0) {
                                    d0 = d3;
                                    l = i2;
                                    i1 = k2;
                                    j1 = j2;
                                    k1 = i3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        i5 = l;
        int j5 = i1;
        j2 = j1;
        int k5 = k1 % 2;
        int l5 = 1 - k5;
        if (k1 % 4 >= 2) {
            k5 = -k5;
            l5 = -l5;
        }

        boolean flag;
        if (d0 < 0.0D) {
            if (i1 < 70) {
                i1 = 70;
            }

            if (i1 > this.worldServerInstance.getActualHeight() - 10) {
                i1 = this.worldServerInstance.getActualHeight() - 10;
            }

            j5 = i1;

            for (k2 = -1; k2 <= 1; ++k2) {
                for (i3 = 1; i3 < 3; ++i3) {
                    for (l2 = -1; l2 < 3; ++l2) {
                        k3 = i5 + (i3 - 1) * k5 + k2 * l5;
                        j3 = j5 + l2;
                        i4 = j2 + (i3 - 1) * l5 - k2 * k5;
                        flag = l2 < 0;
                        this.worldServerInstance.setBlock(k3, j3, i4, flag ? Blocks.sandstone : null);
                    }
                }
            }
        }

        Block block;
        if (entity.dimension == 0) {
            block = Blocks.sandstone;
        } else {
            block = AtumBlocks.LARGEBRICK;
        }
        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {

                this.worldServerInstance.setBlock(l + x1, i1, j1 + z1, block);
            }
        }
        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                if (x1 == 2 || z1 == 2 || x1 == -2 || z1 == -2) {
                    this.worldServerInstance.setBlock(l + x1, i1 + 1, j1 + z1, block);
                }
            }
        }
        for (int y1 = 2; y1 < 4; y1++) {
            for (int x1 = -2; x1 < 3; x1++) {
                for (int z1 = -2; z1 < 3; z1++) {
                    if ((x1 == 2 && z1 == 2) || (x1 == -2 && z1 == 2) || (x1 == 2 && z1 == -2) || (x1 == -2 && z1 == -2)) {
                        this.worldServerInstance.setBlock(l + x1, i1 + y1, j1 + z1, block);
                    }
                }
            }
        }

        for (int x1 = -1; x1 < 2; x1++) {
            for (int z1 = -1; z1 < 2; z1++) {
                this.worldServerInstance.setBlock(l + x1, i1 + 1, j1 + z1, AtumBlocks.PORTAL, 0, 2);
            }
        }
        return true;
    }

    @Override
    public void removeStalePortalLocations(long par1) {
        if (par1 % 100L == 0L) {
            Iterator iterator = this.destinationCoordinateKeys.iterator();
            long j = par1 - 600L;

            while (iterator.hasNext()) {
                Long olong = (Long) iterator.next();
                AtumPortalPosition portalPosition = (AtumPortalPosition) this.destinationCoordinateCache.getValueByKey(olong.longValue());
                if (portalPosition == null || portalPosition.lastUpdateTime < j) {
                    iterator.remove();
                    this.destinationCoordinateCache.remove(olong.longValue());
                }
            }
        }
    }
}