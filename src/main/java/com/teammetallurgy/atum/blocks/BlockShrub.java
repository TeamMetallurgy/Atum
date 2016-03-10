package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockShrub extends BlockDeadBush { //TODO Add 10% chance to drop deadwood sticks

    protected BlockShrub() {
        super();
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
    }

    @Override
    public boolean canPlaceBlockOn(Block ground) {
        return ground == AtumBlocks.SAND;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { //TODO Figure out if Shrubs should drop sticks like dead bushes in 1.9
        return null;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(AtumBlocks.SHRUB)));
    }
}