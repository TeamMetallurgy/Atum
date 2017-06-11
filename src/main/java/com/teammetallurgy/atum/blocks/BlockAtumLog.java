package com.teammetallurgy.atum.blocks;

import com.google.common.base.Predicate;
import com.teammetallurgy.atum.items.AtumItemBlock;
import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockAtumLog extends BlockLog implements IAtumBlock {
    public static final PropertyEnum<BlockAtumPlank.EnumType> VARIANT = PropertyEnum.create("variant", BlockAtumPlank.EnumType.class, new Predicate<BlockAtumPlank.EnumType>() {
        @Override
        public boolean apply(BlockAtumPlank.EnumType enumType) {
            return enumType.getMetadata() < 4;
        }
    });

    public BlockAtumLog() {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockAtumPlank.EnumType.PALM).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return AtumItemBlock.class;
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return null;
    }

    @Override
    public String getStateName(IBlockState state) {
        return BlockAtumPlank.EnumType.byMetadata(state.getBlock().getMetaFromState(state)).getUnlocalizedName();
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        BlockAtumPlank.EnumType enumType = world.getBlockState(pos).getValue(VARIANT);
        if (enumType.getMetadata() == BlockAtumPlank.EnumType.DEADWOOD.getMetadata()) {
            return false;
        }
        return true;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos blockPos) {
        BlockAtumPlank.EnumType enumType = state.getValue(VARIANT);

        switch (state.getValue(LOG_AXIS)) {
            case X:
            case Z:
            case NONE:
            default:
                switch (enumType) {
                    case PALM:
                    default:
                        return BlockAtumPlank.EnumType.PALM.getMapColor();
                    case DEADWOOD:
                        return BlockAtumPlank.EnumType.DEADWOOD.getMapColor();
                }

            case Y:
                return enumType.getMapColor();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        list.add(new ItemStack(this, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        list.add(new ItemStack(this, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockAtumPlank.EnumType.byMetadata((meta & 3) % 4));

        switch (meta & 12) {
            case 0:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | (state.getValue(VARIANT)).getMetadata();

        switch (state.getValue(LOG_AXIS)) {
            case X:
                i |= 4;
                break;
            case Y:
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }
        return i;
    }

    @Override
    @Nonnull
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, (state.getValue(VARIANT)).getMetadata());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT, LOG_AXIS});
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        BlockAtumPlank.EnumType enumType = world.getBlockState(pos).getValue(VARIANT);

        if (enumType.getMetadata() == BlockAtumPlank.EnumType.DEADWOOD.getMetadata() && RANDOM.nextInt(100) <= 25) {
            // Drop Beetles.
            int amount = RANDOM.nextInt(1) + 1;
            List<ItemStack> drops = new ArrayList<ItemStack>();
            drops.add(new ItemStack(AtumItems.DEADWOOD_BEETLE, amount));
            return drops;
        }

        return super.getDrops(world, pos, state, fortune);
    }
}