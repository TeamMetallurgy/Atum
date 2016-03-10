package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.AtumTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockPortal extends BlockBreakable { //TODO Redo for 1.9
    public BlockPortal() {
        super(Material.portal, true);
        this.setTickRandomly(true);
        this.setHardness(-1.0F);
        this.setBlockBounds(0, 0, 0, 1, 0.875F, 1);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
        return null;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                for (int y = -1; y < 1; y++) {
                    IBlockState blockState = world.getBlockState(pos.add(x, y, z));
                    if (blockState != Blocks.sandstone.getDefaultState() && blockState != this && blockState != AtumBlocks.LIMESTONEBRICK.getDefaultState().withProperty(BlockLimestoneBricks.VARIANT, BlockLimestoneBricks.EnumType.LARGE)) {
                        world.setBlockToAir(pos);
                    }
                }
            }
        }
    }

    public boolean tryToCreatePortal(World world, BlockPos pos, IBlockState state) { //TODO Redo for 1.9
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                if (world.getBlockState(pos.add(x + x1, y, z + z1)) != state) {
                    return false;
                }
            }
        }
        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                if (x1 + x == x + 2 || z1 + z == z + 2 || x1 + x == x - 2 || z1 + z == z - 2) {
                    if (world.getBlockState(pos.add(x + x1, y + 1, z + z1)) != state) {
                        return false;
                    }
                }
            }
        }
        for (int y1 = 2; y1 < 4; y1++) {
            for (int x1 = -2; x1 < 3; x1++) {
                for (int z1 = -2; z1 < 3; z1++) {
                    if ((x1 + x == x + 2 && z1 + z == z + 2) || (x1 + x == x - 2 && z1 + z == z + 2) || (x1 + x == x + 2 && z1 + z == z - 2) || (x1 + x == x - 2 && z1 + z == z - 2)) {
                        if (world.getBlockState(pos.add(x + x1, y + y1, z + z1)) != state) {
                            return false;
                        }
                    }
                }
            }
        }
        for (int x1 = -1; x1 < 2; x1++) {
            for (int z1 = -1; z1 < 2; z1++) {
                world.setBlockState(pos.add(x + x1, y + 1, z + z1), AtumBlocks.PORTAL.getDefaultState(), 2);
            }
        }
        return true;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
        if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) entity;
            if (entity.timeUntilPortal == 0 && entity instanceof EntityPlayerMP) {
                entity.timeUntilPortal = 100;
                MinecraftServer minecraftserver = MinecraftServer.getServer();
                int dimID = entity.dimension;
                int atumId = AtumConfig.DIMENSION_ID;
                WorldServer worldserver = minecraftserver.worldServerForDimension(0);
                WorldServer worldserver1 = minecraftserver.worldServerForDimension(atumId);
                if (dimID == atumId) {
                    minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entity, 0, new AtumTeleporter(worldserver));
                } else {
                    minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entity, atumId, new AtumTeleporter(worldserver1));
                }

                try {
                    ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, player, Integer.valueOf(-1), new String[]{"lastExperience", "cp", "field_71144_ck"});
                    ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, player, Integer.valueOf(-1), new String[]{"lastHealth", "cm", "field_71149_ch"});
                    ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, player, Integer.valueOf(-1), new String[]{"lastFoodLevel", "cn", "field_71146_ci"});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos) {
        return null;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
}