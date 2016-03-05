package com.teammetallurgy.atum.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockLeave extends Block /*extends BlockLeaves*/ {//TODO
    public BlockLeave(Material materialIn) {
        super(materialIn);
    }
    /*public static final PropertyEnum<BlockAtumPlank.EnumType> VARIANT = PropertyEnum.<BlockAtumPlank.EnumType>create("variant", BlockAtumPlank.EnumType.class, new Predicate<BlockAtumPlank.EnumType>() {
        @Override
        public boolean apply(BlockAtumPlank.EnumType enumType) {
            return enumType.getMetadata() >= 4;
        }
    });

    public BlockLeave() {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockAtumPlank.EnumType.PALM).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((BlockAtumPlank.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public int getDamageValue(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().getMetaFromState(state) & 3;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(item, 1, 0)); //Only add Palm Leaves atm.
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, (state.getValue(VARIANT)).getMetadata() - 4);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockAtumPlank.EnumType.PALM).withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(VARIANT).getMetadata() - 4;

        if (!state.getValue(DECAYABLE)) {
            i |= 4;
        }

        if (state.getValue(CHECK_DECAY)) {
            i |= 8;
        }
        return i;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] {VARIANT, CHECK_DECAY, DECAYABLE});
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
        {
            super.harvestBlock(world, player, pos, state, te);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.getBlockState(pos);
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(this, 1, ((BlockAtumPlank.EnumType)state.getValue(VARIANT)).getMetadata() - 4)));
    }*/
}