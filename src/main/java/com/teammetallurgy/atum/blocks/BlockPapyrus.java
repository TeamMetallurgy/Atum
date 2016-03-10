package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockPapyrus extends BlockReed {

    public BlockPapyrus() {
        super();
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plant) {
        if (plant.getPlant(world, pos.up()) == this) {
            return true;
        }
        return super.canSustainPlant(world, pos, side, plant);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return AtumItems.PAPYRUS_PLANT;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos) {
        return AtumItems.PAPYRUS_PLANT;
    }
}