package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlockShrub extends BlockDeadBush {

    protected BlockShrub() {
        super();
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
    }

    @Override
    public boolean canSustainBush(IBlockState state) {
        return state.getBlock() == AtumBlocks.SAND;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return new ItemStack(AtumItems.STICK, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()).getItem();
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(AtumBlocks.SHRUB));
    }
}