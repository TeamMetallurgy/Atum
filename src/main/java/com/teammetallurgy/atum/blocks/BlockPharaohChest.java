package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.items.AtumLoot;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPharaohChest extends BlockChest {

    protected BlockPharaohChest() {
        super(0);
        this.setHardness(4.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPharaohChest();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.chest);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);
        BlockPos blockpos = pos.north();
        BlockPos blockpos1 = pos.south();
        BlockPos blockpos2 = pos.west();
        BlockPos blockpos3 = pos.east();
        boolean flag = this == world.getBlockState(blockpos).getBlock();
        boolean flag1 = this == world.getBlockState(blockpos1).getBlock();
        boolean flag2 = this == world.getBlockState(blockpos2).getBlock();
        boolean flag3 = this == world.getBlockState(blockpos3).getBlock();

        if (!flag && !flag1 && !flag2 && !flag3) {
            world.setBlockState(pos, state, 3);
        } else if (enumfacing.getAxis() != EnumFacing.Axis.X || !flag && !flag1) {
            if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3)) {
                if (flag2) {
                    world.setBlockState(blockpos2, state, 3);
                } else {
                    world.setBlockState(blockpos3, state, 3);
                }

                world.setBlockState(pos, state, 3);
            }
        } else {
            if (flag) {
                world.setBlockState(blockpos, state, 3);
            } else {
                world.setBlockState(blockpos1, state, 3);
            }

            world.setBlockState(pos, state, 3);
        }

        if (stack.hasDisplayName()) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof TileEntityPharaohChest) {
                ((TileEntityPharaohChest) world.getTileEntity(pos)).setCustomName(stack.getDisplayName());
            }
        }

        AtumLoot.fillChest((TileEntityPharaohChest) world.getTileEntity(pos), 15, 0.9F);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityPharaohChest) {
            TileEntityPharaohChest pharaohChest = (TileEntityPharaohChest) te;
            if (!pharaohChest.hasSpawned()) {
                pharaohChest.spawn(player);
            }
        }

        if (world.isRemote) {
            return true;
        } else {
            IInventory inventory = this.getLockableContainer(world, pos);
            if (inventory != null) {
                player.displayGUIChest(inventory);
            }

            return true;
        }
    }
}