package com.teammetallurgy.atum.world.decorators;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

import static net.minecraft.util.EnumFacing.*;

public class WorldGenPyramid extends WorldGenerator { //TODO What is this based of?
    @Override
    public boolean generate(World world, Random random, BlockPos pos) { //TODO
        /*if (random.nextFloat() > 0.3) {
            j -= 8;
        }

        int width = 17;
        int depth = 17;

        boolean[][] maze = new boolean[17][17];

        int zIn = 9;

        maze[0][zIn] = true;
        generateMaze(maze, random, 1, zIn);

        for (int y = -6; y < 10; y++) {
            for (int x = y; x <= width - y; x++) {
                for (int z = y; z <= depth - y; z++) {
                    Block block = world.getBlockState(x + i, y + j + 3, z + k);
                    if (block == AtumBlocks.SAND) {
                        world.setBlockToAir(x + i, y + j + 3, z + k);
                    }
                    world.setBlock(x + i, y + j + 3, z + k, AtumBlocks.LARGEBRICK);
                }
            }
        }

        for (int x = -3; x < width + 3; x++) {
            for (int z = -3; z < depth + 3; z++) {
                if (x >= 0 && x < width && z >= 0 && z < depth) {
                    world.setBlockToAir(x + i, j, z + k);
                    world.setBlock(x + i, j - 1, z + k, AtumBlocks.LIMESTONE);
                    if (!maze[x][z]) {
                        if (random.nextFloat() > 0.1F) {
                            world.setBlock(x + i, j, z + k, AtumBlocks.LARGEBRICK);
                            Block temp = world.getBlock(x + i, j + 1, z + k);
                            if (temp != null) {
                                temp.setBlockUnbreakable();
                            }
                        } else
                            placeTrap(world, x + i, j, z + k);
                        world.setBlock(x + i, j + 1, z + k, AtumBlocks.LARGEBRICK);
                        Block temp = world.getBlock(x + i, j + 1, z + k);
                        if (temp != null) {
                            temp.setBlockUnbreakable();
                        }

                        world.setBlock(x + i, j + 2, z + k, AtumBlocks.LARGEBRICK);
                        temp = world.getBlock(x + i, j + 2, z + k);
                        if (temp != null) {
                            temp.setBlockUnbreakable();
                        }
                    } else {
                        int meta = random.nextInt(5);
                        world.setBlock(x + i, j, z + k, AtumBlocks.SANDLAYERED, meta, 0);
                        world.setBlockToAir(x + i, j + 1, z + k);
                        world.setBlockToAir(x + i, j + 2, z + k);
                    }
                    world.setBlock(x + i, j + 3, z + k, AtumBlocks.LARGEBRICK);
                    Block temp = world.getBlock(x + i, j + 3, z + k);
                    if (temp != null) {
                        temp.setBlockUnbreakable();
                    }
                }
            }
        }

        world.setBlockToAir(i - 1, j, k + zIn);
        world.setBlockToAir(i - 1, j + 1, k + zIn);
        world.setBlockToAir(i - 2, j, k + zIn);
        world.setBlockToAir(i - 2, j + 1, k + zIn);
        world.setBlockToAir(i - 3, j, k + zIn);
        world.setBlockToAir(i - 3, j + 1, k + zIn);
        world.setBlockToAir(i - 4, j, k + zIn);
        world.setBlockToAir(i - 4, j + 1, k + zIn);

        for (int y = 4; y < 8; y++) {
            for (int x = 6; x < 12; x++) {
                for (int z = 6; z < 12; z++) {
                    world.setBlockToAir(i + x, j + y, k + z);
                }
            }
        }

        world.setBlock(i + 11, j + 6, k + 7, Blocks.torch, 2, 0);
        world.setBlock(i + 11, j + 6, k + 10, Blocks.torch, 2, 0);

        world.setBlock(i + 10, j + 4, k + 8, AtumBlocks.PHARAOHCHEST, 0, 2);
        try {
            TileEntityPharaohChest te = (TileEntityPharaohChest) world.getTileEntity(i + 10, j + 4, k + 8);
            AtumLoot.fillChest(te, 15, 0.9f);
        } catch (ClassCastException e) {
        }
        if (world.isAirBlock(i + 7, j + 1, k + 7)) {
            placeLadders(world, i + 7, j, k + 7, 4);
        } else {
            boolean found = false;
            for (int dx = -1; dx <= 1; dx++) {
                if (found)
                    break;

                for (int dz = -1; dz <= 1; dz++) {
                    if (world.isAirBlock(i + 7 + dx, j + 1, k + 7 + dz)) {
                        placeLadders(world, i + 7 + dx, j, k + 7 + dz, 3);
                        found = true;
                        break;
                    }
                }
            }
        }*/

        return false;
    }

    public void placeTrap(World world, BlockPos pos) {
        int meta = 0;
        if (world.isSideSolid(pos.south(), NORTH)) {
            meta = 3;
        }

        if (world.isSideSolid(pos.north(), SOUTH)) {
            meta = 4;
        }

        if (world.isSideSolid(pos.east(), WEST)) {
            meta = 5;
        }

        if (world.isSideSolid(pos.west(), EAST)) {
            meta = 2;
        }

        world.setBlockState(pos, AtumBlocks.BURNING_TRAP.getStateFromMeta(meta), 0);
    }

    public void placeLadders(World world, BlockPos pos, int height) {
        int meta = 0;
        if (world.isSideSolid(pos.south(), NORTH)) {
            meta = 2;
        }

        if (world.isSideSolid(pos.north(), SOUTH)) {
            meta = 3;
        }

        if (world.isSideSolid(pos.east(), WEST)) {
            meta = 4;
        }

        if (world.isSideSolid(pos.west(), EAST)) {
            meta = 5;
        }

        for (int i = 0; i < height; i++) {
            world.setBlockState(pos.up(i), Blocks.LADDER.getStateFromMeta(meta), 0);
        }
    }

    public void generateMaze(boolean[][] array, Random random, int x, int y) {
        ArrayList<Pair> choices = new ArrayList<Pair>();
        do {
            choices.clear();
            if (x + 2 < 16 && !array[x + 2][y])
                choices.add(new Pair(2, 0));
            if (x - 2 >= 0 && !array[x - 2][y])
                choices.add(new Pair(-2, 0));
            if (y + 2 < 16 && !array[x][y + 2])
                choices.add(new Pair(0, 2));
            if (y - 2 >= 0 && !array[x][y - 2])
                choices.add(new Pair(0, -2));

            if (choices.size() > 0) {
                int i = random.nextInt(choices.size());
                Pair choice = choices.get(i);
                choices.remove(i);
                array[choice.x + x][choice.y + y] = true;
                array[x + choice.x / 2][y + choice.y / 2] = true;
                generateMaze(array, random, x + choice.x, y + choice.y);
            }

        } while (choices.size() > 0);
    }

    class Pair {
        public int x;
        public int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object p) {
            if (p instanceof Pair)
                return ((Pair) p).x == x && ((Pair) p).y == y;
            else
                return false;
        }
    }
}