package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDate extends Block {

    public BlockDate() {
        super(Material.plants);
        this.setHardness(0.5F);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (world.getBlockState(pos.up()).getBlock() != AtumBlocks.LEAVES && !world.isRemote) {
            EntityItem entityItem = new EntityItem(world, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), new ItemStack(AtumItems.DATE, 0, this.quantityDropped(new Random())));
            entityItem.dropItem(AtumItems.DATE, this.quantityDropped(new Random()));
            world.setBlockToAir(pos);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return AtumItems.DATE;
    }

    @Override
    public int quantityDropped(Random rand) {
        return rand.nextInt(4) + 1;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(AtumItems.DATE);
    }
}