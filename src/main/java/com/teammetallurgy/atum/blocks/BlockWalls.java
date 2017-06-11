package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import com.teammetallurgy.atum.items.AtumItemBlock;

public class BlockWalls extends BlockWall implements IAtumBlock {
	public static final PropertyEnum<BlockWalls.EnumType> ATUM_VARIANT = PropertyEnum.create("atum_variant", BlockWalls.EnumType.class);
    public BlockWalls(Block modelBlock) {
        super(modelBlock);

        this.setDefaultState(this.blockState.getBaseState().withProperty(UP,false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(ATUM_VARIANT, BlockWalls.EnumType.SMOOTH));
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return AtumItemBlock.class;
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[] {VARIANT};
    }

    @Override
    public String getStateName(IBlockState state) {
        return BlockWalls.EnumType.byMetadata(state.getBlock().getMetaFromState(state)).getUnlocalizedName();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ATUM_VARIANT, BlockWalls.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(ATUM_VARIANT)).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {UP, NORTH, EAST, WEST, SOUTH, VARIANT, ATUM_VARIANT});
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(ATUM_VARIANT)).getMetadata();
    }

    @Override
    @Nonnull
    public MapColor getMapColor(IBlockState state,IBlockAccess blockAccess, BlockPos blockPos) {
        return (state.getValue(ATUM_VARIANT)).getMapColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for (BlockWalls.EnumType enumType : BlockWalls.EnumType.values()) {
            list.add(new ItemStack(this, 1, enumType.getMetadata()));
        }
    }

    public enum EnumType implements IStringSerializable {
        SMOOTH(0, "smooth", "smooth", MapColor.SAND),
        CRACKED(1, "cracked", "cracked", MapColor.SAND),
        LARGE_BRICK(2, "large_brick", "largeBrick", MapColor.SAND),
        SMALL_BRICK(3, "small_brick", "smallBrick", MapColor.SAND);

        private static final BlockWalls.EnumType[] META_LOOKUP = new BlockWalls.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        private final MapColor mapColor;

        EnumType(int meta, String name, String unlocalizedName, MapColor mapColor) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
            this.mapColor = mapColor;
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

        public static BlockWalls.EnumType byMetadata(int meta) {
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
            for (BlockWalls.EnumType enumType : values()) {
                META_LOOKUP[enumType.getMetadata()] = enumType;
            }
        }
    }
}