package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockSandLayered extends Block {

    public static final PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);

    public BlockSandLayered() {
        super(Material.sand);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LAYERS, Integer.valueOf(1)));
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setBlockBoundsForItemRender();
    }

    @Override
    public boolean isPassable(IBlockAccess w    , BlockPos pos) {
        return (w.getBlockState(pos).getValue(LAYERS)).intValue() < 5;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.getBoundsForLayers(0);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        this.getBoundsForLayers((state.getValue(LAYERS)).intValue());
    }

    protected void getBoundsForLayers(int layer) {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (float) layer / 8.0F, 1.0F);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos.down());
        Block block = state.getBlock();
        return block == null ? false : (block == this ? true : (block == this && (state.getValue(LAYERS)).intValue() >= 7 ? true : (!block.isLeaves(world, pos.down()) && !block.isOpaqueCube() ? false : block.getMaterial().blocksMovement())));
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
        this.checkAndDropBlock(world, pos);
    }

    private boolean checkAndDropBlock(World world, BlockPos pos) {
        if (!this.canPlaceBlockAt(world, pos)) {
            world.setBlockToAir(pos);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
        super.harvestBlock(world, player, pos, state, te);
        world.setBlockToAir(pos);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11) {
            world.setBlockToAir(pos);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side) {
        return side == EnumFacing.UP ? true : super.shouldSideBeRendered(world, pos, side);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(LAYERS, Integer.valueOf((meta & 7) + 1));
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos) {
        return (world.getBlockState(pos).getValue(LAYERS)).intValue() == 1;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(LAYERS)).intValue() - 1;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{LAYERS});
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return (state.getValue(LAYERS)) + 1;
    }
}