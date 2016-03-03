package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAtumWoodSlab extends BlockSlab {
    public static final PropertyEnum<BlockAtumPlank.EnumType> VARIANT = PropertyEnum.create("variant", BlockAtumPlank.EnumType.class);
    private boolean isDoubleSlab;

    public BlockAtumWoodSlab(boolean isDoubleSlab) {
        super(Material.wood);
        this.isDoubleSlab = isDoubleSlab;

        IBlockState state = this.blockState.getBaseState();

        if (!this.isDouble()) {
            state = state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(state.withProperty(VARIANT, BlockAtumPlank.EnumType.PALM));

        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.useNeighborBrightness = true;
        this.setUnlocalizedName("woodSlab"); //TODO
        this.setCreativeTab(Atum.creativeTab); //TODO
    }


    /*@Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(this, 2, state.getBlock().getMetaFromState(state) & 4);
    }*/

    @Override
    public MapColor getMapColor(IBlockState state) {
        return ((BlockAtumPlank.EnumType) state.getValue(VARIANT)).getMapColor();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(AtumBlocks.WOOD_SLAB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos) {
        return Item.getItemFromBlock(AtumBlocks.WOOD_SLAB);
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName() + "." + BlockAtumPlank.EnumType.byMetadata(meta).getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return isDoubleSlab ? true : false;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Object getVariant(ItemStack stack) {
        return BlockAtumPlank.EnumType.byMetadata(stack.getMetadata() & 7);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        if (itemIn != Item.getItemFromBlock(AtumBlocks.WOOD_DOUBLESLAB)) {
            for (BlockAtumPlank.EnumType enumType : BlockAtumPlank.EnumType.values()) {
                list.add(new ItemStack(itemIn, 1, enumType.getMetadata()));
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockAtumPlank.EnumType.byMetadata(meta & 7));

        if (!this.isDouble()) {
            state = state.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | (state.getValue(VARIANT)).getMetadata();

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
            i |= 8;
        }
        return i;
    }

    @Override
    protected BlockState createBlockState() {
        return this.isDouble() ? new BlockState(this, new IProperty[]{VARIANT}) : new BlockState(this, new IProperty[]{HALF, VARIANT});
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }
}