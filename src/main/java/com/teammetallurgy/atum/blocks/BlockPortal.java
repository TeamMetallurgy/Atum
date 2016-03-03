package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.AtumTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class BlockPortal extends BlockBreakable {

    public BlockPortal() {
        super(Material.portal, true);
        this.setTickRandomly(true);
        this.setHardness(-1.0F);
        this.setBlockBounds(0, 0, 0, 1, 0.875F, 1);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        super.updateTick(par1World, par2, par3, par4, par5Random);
        if (par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.getDifficulty().getDifficultyId()) {
            ;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                for (int y = -1; y < 1; y++) {
                    if (par1World.getBlock(par2 + x, par3 + y, par4 + z) != Blocks.sandstone && par1World.getBlock(par2 + x, par3 + y, par4 + z) != this && par1World.getBlock(par2 + x, par3 + y, par4 + z) != AtumBlocks.LARGEBRICK) {
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                }
            }
        }
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public boolean tryToCreatePortal(World world, BlockPos pos, Block block) {
        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                if (world.getBlock(x + x1, y, z + z1) != block) {
                    return false;
                }
            }
        }
        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                if (x1 + x == x + 2 || z1 + z == z + 2 || x1 + x == x - 2 || z1 + z == z - 2) {
                    if (world.getBlock(x + x1, y + 1, z + z1) != block) {
                        return false;
                    }
                }
            }
        }
        for (int y1 = 2; y1 < 4; y1++) {
            for (int x1 = -2; x1 < 3; x1++) {
                for (int z1 = -2; z1 < 3; z1++) {
                    if ((x1 + x == x + 2 && z1 + z == z + 2) || (x1 + x == x - 2 && z1 + z == z + 2) || (x1 + x == x + 2 && z1 + z == z - 2) || (x1 + x == x - 2 && z1 + z == z - 2)) {
                        if (world.getBlock(x + x1, y + y1, z + z1) != block) {
                            return false;
                        }
                    }
                }
            }
        }
        for (int x1 = -1; x1 < 2; x1++) {
            for (int z1 = -1; z1 < 2; z1++) {
                world.setBlock(x + x1, y + 1, z + z1, AtumBlocks.PORTAL, 0, 2);

            }
        }
        return true;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null && par5Entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) par5Entity;
            if (par5Entity.timeUntilPortal == 0 && par5Entity instanceof EntityPlayerMP) {
                par5Entity.timeUntilPortal = 100;
                MinecraftServer minecraftserver = MinecraftServer.getServer();
                int dimID = par5Entity.dimension;
                int atumId = AtumConfig.DIMENSION_ID;
                WorldServer worldserver = minecraftserver.worldServerForDimension(0);
                WorldServer worldserver1 = minecraftserver.worldServerForDimension(atumId);
                if (dimID == atumId) {
                    minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) par5Entity, 0, new AtumTeleporter(worldserver));
                } else {
                    minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) par5Entity, atumId, new AtumTeleporter(worldserver1));
                }

                try {
                    ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, player, Integer.valueOf(-1), new String[]{"lastExperience", "cp", "field_71144_ck"});
                    ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, player, Integer.valueOf(-1), new String[]{"lastHealth", "cm", "field_71149_ch"});
                    ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, player, Integer.valueOf(-1), new String[]{"lastFoodLevel", "cn", "field_71146_ci"});
                } catch (Exception var12) {
                    var12.printStackTrace();
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par5Random.nextInt(100) == 0) {
            par1World.playSound((double) par2 + 0.5D, (double) par3 + 0.5D, (double) par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int l = 0; l < 8; ++l) {
            double d0 = (double) ((float) par2 + par5Random.nextFloat());
            double d1 = (double) ((float) par3 + par5Random.nextFloat());
            double d2 = (double) ((float) par4 + par5Random.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = par5Random.nextInt(2) * 2 - 1;
            d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
            if (par5Random.nextDouble() > 0.5D) {
                d0 = (double) par2 + 0.5D + 0.25D * (double) i1;
                d3 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
            } else {
                d2 = (double) par4 + 0.5D + 0.25D * (double) i1;
                d5 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
            }

            par1World.spawnParticle("sandportal", d0, d1, d2, d3, d4, d5);
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }
}