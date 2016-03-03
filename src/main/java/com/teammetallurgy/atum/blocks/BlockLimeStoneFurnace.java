package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.blocks.tileentity.furnace.TileEntityLimestoneFurnace;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockLimeStoneFurnace extends BlockFurnace {
    private static boolean keepInventory;

    public BlockLimeStoneFurnace(boolean isBurning) {
        super(isBurning);
        this.setHardness(3.5F);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(AtumBlocks.FURNACE);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntity tileEntity = world.getTileEntity(pos);

            if (tileEntity instanceof TileEntityLimestoneFurnace) {
                //player.openGui(Atum.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
                player.displayGUIChest((TileEntityLimestoneFurnace)tileEntity);
                player.triggerAchievement(StatList.field_181741_Y);
            }

            return true;
        }
    }

    public static void setState(boolean active, World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        TileEntity te = world.getTileEntity(pos);
        keepInventory = true;

        if (active) {
            world.setBlockState(pos, AtumBlocks.FURNACE_LIT.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
            world.setBlockState(pos, AtumBlocks.FURNACE_LIT.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        } else {
            world.setBlockState(pos, AtumBlocks.FURNACE.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
            world.setBlockState(pos, AtumBlocks.FURNACE.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (te != null) {
            te.validate();
            world.setTileEntity(pos, te);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityLimestoneFurnace();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityLimestoneFurnace) {
                ((TileEntityLimestoneFurnace) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityLimestoneFurnace) {
                InventoryHelper.dropInventoryItems(world, pos, (TileEntityLimestoneFurnace) tileentity);
                world.updateComparatorOutputLevel(pos, this);
            }
        }
        super.breakBlock(world, pos, state);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos) {
        return Item.getItemFromBlock(AtumBlocks.FURNACE);
    }
}