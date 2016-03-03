package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BlockAtumFenceGate extends BlockFenceGate {

    public BlockAtumFenceGate() {
        super(BlockPlanks.EnumType.OAK);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        EnumFacing.Axis axis = (state.getValue(FACING)).getAxis();

        if (axis == EnumFacing.Axis.Z && (world.getBlockState(pos.west()).getBlock() == AtumBlocks.WALL || world.getBlockState(pos.east()).getBlock() == AtumBlocks.WALL) || axis == EnumFacing.Axis.X && (world.getBlockState(pos.north()).getBlock() == AtumBlocks.WALL || world.getBlockState(pos.south()).getBlock() == AtumBlocks.WALL)) {
            state = state.withProperty(IN_WALL, Boolean.valueOf(true));
        }

        return state;
    }
}