package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public abstract class BlockLimestoneSlab extends BlockSlab implements IAtumBlock {
    public static final PropertyEnum<BlockLimestoneSlab.EnumType> VARIANT = PropertyEnum.create("variant", BlockLimestoneSlab.EnumType.class);

    public BlockLimestoneSlab() {
        super(Material.ROCK);

        IBlockState state = this.blockState.getBaseState();

        if (!this.isDouble()) {
            useNeighborBrightness = true;
            state = state.withProperty(HALF, BlockLimestoneSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(state.withProperty(VARIANT, EnumType.SMOOTH));

        this.setHardness(2.0F);
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return null;
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return null;
    }

    @Override
    public String getStateName(IBlockState state) {
        return BlockLimestoneSlab.EnumType.byMetadata(state.getBlock().getMetaFromState(state)).getUnlocalizedName();
    }

    @Override
    @Nonnull
    public MapColor getMapColor(IBlockState state) {
        return (state.getValue(VARIANT)).getMapColor();
    }

    @Override
    @Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(AtumBlocks.LIMESTONE_SLAB);
    }

    @Override
    @Nonnull
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(AtumBlocks.LIMESTONE_SLAB) == new ItemStack(this) ? new ItemStack(this) : (new ItemStack(this) == new ItemStack(AtumBlocks.LIMESTONE_DOUBLE_SLAB) ? new ItemStack(AtumBlocks.LIMESTONE_SLAB) : new ItemStack(AtumBlocks.LIMESTONE_SLAB));
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName() + "." + EnumType.byMetadata(meta).getUnlocalizedName();
    }

    @Override
    @Nonnull
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    @Nonnull
    public Comparable<?> getTypeForItem(@Nonnull ItemStack stack) {
        return EnumType.byMetadata(stack.getMetadata() & 7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(@Nonnull Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
        if (item != Item.getItemFromBlock(AtumBlocks.LIMESTONE_DOUBLE_SLAB)) {
            for (EnumType enumType : EnumType.values()) {
                list.add(new ItemStack(item, 1, enumType.getMetadata()));
            }
        }
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 7));

        if (!this.isDouble()) {
            state = state.withProperty(HALF, (meta & 8) == 0 ? BlockLimestoneSlab.EnumBlockHalf.BOTTOM : BlockLimestoneSlab.EnumBlockHalf.TOP);
        }

        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | (state.getValue(VARIANT)).getMetadata();

        if (!this.isDouble() && state.getValue(HALF) == BlockAtumWoodSlab.EnumBlockHalf.TOP) {
            i |= 8;
        }
        return i;
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }

    public static enum EnumType implements IStringSerializable {
        SMOOTH(0, MapColor.SAND, "smooth"),
        CRACKED(1, MapColor.SAND, "cracked"),
        SMALL(2, MapColor.SAND, "small"),
        LARGE(3, MapColor.SAND, "large"),
        CHISELED(4, MapColor.SAND, "chiseled");

        private static final BlockLimestoneSlab.EnumType[] META_LOOKUP = new BlockLimestoneSlab.EnumType[values().length];
        private final int meta;
        private final MapColor mapColor;
        private final String name;
        private final String unlocalizedName;

        EnumType(int meta, MapColor mapColor, String name) {
            this(meta, mapColor, name, name);
        }

        EnumType(int meta, MapColor mapColor, String name, String unlocalizedName) {
            this.meta = meta;
            this.mapColor = mapColor;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata() {
            return this.meta;
        }

        public MapColor getMapColor() {
            return this.mapColor;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static BlockLimestoneSlab.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        @Override
        @Nonnull
        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            for (BlockLimestoneSlab.EnumType enumType : values()) {
                META_LOOKUP[enumType.getMetadata()] = enumType;
            }
        }
    }
}