package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockFertileSoil extends BlockDirt {

    public BlockFertileSoil() {
        super();
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setHardness(0.5F);
        this.setStepSound(Block.soundTypeGrass);
        setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) { //TODO Check if it works (Badly ported)
        if (!world.isRemote) {
            if ((world.getLight(pos.up()) < 4) && (world.getBlockLightOpacity(pos.up()) > 2)) {
                world.setBlockState(pos, state.getBlock().getStateFromMeta(1), 2); //world.setBlockMetadataWithNotify(pos, 1, 2);
            } else if (world.getLight(pos.up()) >= 9) {
                for (int l = 0; l < 4; l++) {
                    int i1 = pos.getX() + random.nextInt(3) - 1;
                    int j1 = pos.getY() + random.nextInt(5) - 3;
                    int k1 = pos.getZ() + random.nextInt(3) - 1;
                    BlockPos blockPos = new BlockPos(i1, j1, k1);
                    IBlockState blockState = world.getBlockState(blockPos);

                    if ((blockState.getBlock() == this) && (blockState.getBlock().getMetaFromState(blockState) == 1) && (world.getLight(blockPos.up()) >= 4) && (world.getBlockLightOpacity(blockPos.up()) <= 2)) {
                        world.setBlockState(pos, AtumBlocks.FERTILESOIL.getDefaultState(), 2);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plantable) {
        EnumPlantType plantType = plantable.getPlantType(world, pos.up());

        boolean hasWater = (world.getBlockState(pos.east()).getBlock().getMaterial() == Material.water ||
                world.getBlockState(pos.west()).getBlock().getMaterial() == Material.water ||
                world.getBlockState(pos.north()).getBlock().getMaterial() == Material.water ||
                world.getBlockState(pos.south()).getBlock().getMaterial() == Material.water);

        switch (plantType) {
            case Plains:
                return true;
            case Beach:
                return hasWater;
            default:
                return super.canSustainPlant(world, pos, side, plantable);
        }
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return super.getMapColor(state);
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
        super.onNeighborBlockChange(world, pos, state, neighborBlock);
        if (world.getBlockState(pos.up()).getBlock().getMaterial().isSolid()) {
            world.setBlockState(pos, AtumBlocks.FERTILESOIL.getDefaultState());
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(AtumBlocks.SAND);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }
}