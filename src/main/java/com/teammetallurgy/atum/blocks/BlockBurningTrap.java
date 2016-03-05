package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.blocks.tileentity.TileEntityBurningTrap;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBurningTrap extends BlockDispenser {

    public BlockBurningTrap() {
        super();
        this.setHardness(-1.0F);
    }

    @Override
    public float getBlockHardness(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos.up());
        return world.getBlockState(pos.up()) == AtumBlocks.LIMESTONEBRICK.getDefaultState().withProperty(BlockAtumBricks.VARIANT, BlockAtumBricks.EnumType.LARGE) && state.getBlock().getMetaFromState(state) == 1 ? -1.0F : super.blockHardness;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) { //TODO
        return true;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityBurningTrap();
    }
}