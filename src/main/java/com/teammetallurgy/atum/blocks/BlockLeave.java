package com.teammetallurgy.atum.blocks;

import com.google.common.base.Predicate;
import com.teammetallurgy.atum.items.AtumItemBlock;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockLeave extends BlockLeaves implements IAtumBlock {
    public static final PropertyEnum<BlockAtumPlank.EnumType> VARIANT = PropertyEnum.create("variant", BlockAtumPlank.EnumType.class, new Predicate<BlockAtumPlank.EnumType>() {
        @Override
        public boolean apply(BlockAtumPlank.EnumType enumType) {
            return enumType.getMetadata() < 4;
        }
    });

    public BlockLeave() {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockAtumPlank.EnumType.PALM).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
    }
    
    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return AtumItemBlock.class;
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[]{CHECK_DECAY, DECAYABLE};
    }

    @Override
    public String getStateName(IBlockState state) {
        return BlockAtumPlank.EnumType.byMetadata(state.getBlock().getMetaFromState(state)).getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
        list.add(new ItemStack(item, 1, BlockAtumPlank.EnumType.PALM.getMetadata())); //Only add Palm Leaves atm.
    }

    @Override
    @Nonnull
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, (state.getValue(VARIANT)).getMetadata());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockAtumPlank.EnumType.byMetadata(meta)).withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(VARIANT).getMetadata();

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
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT, CHECK_DECAY, DECAYABLE});
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, @Nonnull ItemStack stack) {
        if (!world.isRemote && !stack.isEmpty() && stack.getItem() == Items.SHEARS) {
            player.addStat(StatList.getBlockStats(this));
        } else {
            super.harvestBlock(world, player, pos, state, te, stack);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.getBlockState(pos);
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(this, 1, (state.getValue(VARIANT)).getMetadata())));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return Blocks.LEAVES.getBlockLayer();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return Blocks.LEAVES.isOpaqueCube(Blocks.LEAVES.getDefaultState());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
            EnumFacing side) {

        if (!(Blocks.LEAVES.getBlockLayer() == BlockRenderLayer.CUTOUT_MIPPED) && blockAccess.getBlockState(pos.offset(side)).getBlock() == this) {
            return false;
        }

        AxisAlignedBB boundingBox = blockState.getBoundingBox(blockAccess, pos);

        switch (side) {
             case DOWN:

                 if (boundingBox.minY > 0.0D){
                     return true;
                 }

                 break;
             case UP:

                 if (boundingBox.maxY < 1.0D){
                     return true;
                 }

                 break;
             case NORTH:

                 if (boundingBox.minZ > 0.0D){
                     return true;
                 }

                 break;
             case SOUTH:

                 if (boundingBox.maxZ < 1.0D){
                     return true;
                 }

                 break;
             case WEST:

                 if (boundingBox.minX > 0.0D){
                     return true;
                 }

                 break;
             case EAST:

                 if (boundingBox.maxX < 1.0D){
                     return true;
                 }
         }

         return !blockAccess.getBlockState(pos.offset(side)).doesSideBlockRendering(blockAccess, pos.offset(side), side.getOpposite());
    }
}