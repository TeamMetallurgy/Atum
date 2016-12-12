package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.BlockReed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
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
        this.setSoundType(SoundType.GLASS);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        if (plantable.getPlant(world, pos.up()) == this) {
            return true;
        }
        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return AtumItems.PAPYRUS_PLANT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(AtumItems.PAPYRUS_PLANT);
    }
}