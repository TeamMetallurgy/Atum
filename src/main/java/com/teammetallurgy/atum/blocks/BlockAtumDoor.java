package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockAtumDoor extends BlockDoor implements IAtumBlock {
    private Item doorItem;

    protected BlockAtumDoor() {
        super(Material.WOOD);
        this.disableStats();
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return null;
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[]{POWERED};
    }

    @Override
    public String getStateName(IBlockState state) {
        return "";
    }

    public void setDoorItem(Item doorItem) {
        this.doorItem = doorItem;
    }

    @Override
    @Nonnull
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(this.getDoorItem());
    }

    public Item getDoorItem() {
        return this.doorItem == null ? AtumItems.PALM_DOOR : this.doorItem;
    }

    @Override
    @Nonnull
    public ItemStack getPickBlock(@Nonnull IBlockState state, RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, EntityPlayer player) {
        return new ItemStack(this.getDoorItem(), 1);
    }

    @Override
    @Nonnull
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : this.getDoorItem();
    }
}