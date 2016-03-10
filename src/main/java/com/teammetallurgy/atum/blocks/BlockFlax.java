package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFlax extends BlockCrops {
    @SideOnly(Side.CLIENT)

    protected BlockFlax() {
        super();
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        if (world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)) >> 3 == 1) {
            return world.getBlockState(pos.down()).getBlock() == AtumBlocks.FERTILE_SOIL;
        } else {
            Block soil = world.getBlockState(pos.down()).getBlock();
            return (world.getLight(pos) >= 8 || world.canSeeSky(pos)) && soil != null && soil.canSustainPlant(world, pos.down(), net.minecraft.util.EnumFacing.UP, this);
        }
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.farmland || ground == AtumBlocks.FERTILE_SOIL_TILLED;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    protected Item getSeed() {
        return AtumItems.FLAX_SEED;
    }

    @Override
    protected Item getCrop() {
        return AtumItems.FLAX;
    }
}