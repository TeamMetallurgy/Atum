package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockAtumRedstone extends Block { //TODO Make sure it goes out of the lit form again. If not possible, then extend BlockRedstoneOre

    public BlockAtumRedstone() {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public int tickRate(World world) {
        return 30;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        this.activate(worldIn, pos);
        super.onBlockClicked(worldIn, pos, playerIn);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
        this.activate(world, pos);
        super.onEntityCollidedWithBlock(world, pos, entity);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        this.activate(world, pos);
        return super.onBlockActivated(world, pos, state, player, side, hitX, hitY, hitZ);
    }

    private void activate(World worldIn, BlockPos pos) {
        this.spawnParticles(worldIn, pos);

        if (this == AtumBlocks.REDSTONEORE) {
            worldIn.setBlockState(pos, AtumBlocks.REDSTONEORE.getStateFromMeta(1));
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (this == AtumBlocks.REDSTONEORE.getStateFromMeta(1).getBlock()) {
            world.setBlockState(pos, AtumBlocks.REDSTONEORE.getDefaultState());
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.redstone;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }

    @Override
    public int quantityDropped(Random random) {
        return 4 + random.nextInt(2);
    }

    @Override
    public int getExpDrop(net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        if (this.getItemDropped(world.getBlockState(pos), RANDOM, fortune) != Item.getItemFromBlock(this)) {
            return 1 + RANDOM.nextInt(5);
        }
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (this == AtumBlocks.REDSTONEORE.getStateFromMeta(1).getBlock()) {
            this.spawnParticles(world, pos);
        }
    }

    private void spawnParticles(World worldIn, BlockPos pos) {
        Random random = worldIn.rand;
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i) {
            double x = (double) ((float) pos.getX() + random.nextFloat());
            double y = (double) ((float) pos.getY() + random.nextFloat());
            double z = (double) ((float) pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).getBlock().isOpaqueCube()) {
                y = (double) pos.getY() + d0 + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).getBlock().isOpaqueCube()) {
                y = (double) pos.getY() - d0;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).getBlock().isOpaqueCube()) {
                z = (double) pos.getZ() + d0 + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).getBlock().isOpaqueCube()) {
                z = (double) pos.getZ() - d0;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).getBlock().isOpaqueCube()) {
                x = (double) pos.getX() + d0 + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).getBlock().isOpaqueCube()) {
                x = (double) pos.getX() - d0;
            }

            if (x < (double) pos.getX() || x > (double) (pos.getX() + 1) || y < 0.0D || y > (double) (pos.getY() + 1) || z < (double) pos.getZ() || z > (double) (pos.getZ() + 1)) {
                worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(AtumBlocks.REDSTONEORE);
    }
}