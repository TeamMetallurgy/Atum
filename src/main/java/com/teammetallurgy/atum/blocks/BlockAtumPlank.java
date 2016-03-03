package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockAtumPlank extends Block {
    public static final PropertyEnum<BlockAtumPlank.EnumType> VARIANT = PropertyEnum.create("variant", BlockAtumPlank.EnumType.class);

    protected BlockAtumPlank() {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeWood);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockAtumPlank.EnumType.PALM));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (BlockAtumPlank.EnumType enumType : BlockAtumPlank.EnumType.values()) {
            list.add(new ItemStack(item, 1, enumType.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockAtumPlank.EnumType.byMetadata(meta));
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return (state.getValue(VARIANT)).getMapColor();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }

    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{VARIANT});
    }

    public static enum EnumType implements IStringSerializable {
        PALM(0, "palm", MapColor.woodColor),
        DEADWOOD(1, "deadwood", MapColor.obsidianColor);

        private static final BlockAtumPlank.EnumType[] META_LOOKUP = new BlockAtumPlank.EnumType[values().length];
        private final int meta;
        private final String unlocalizedName;
        private final MapColor mapColor;

        private EnumType(int meta, String unlocalizedName, MapColor mapColor) {
            this.meta = meta;
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
            return this.unlocalizedName;
        }

        public static BlockAtumPlank.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }
            return META_LOOKUP[meta];
        }

        @Override
        public String getName() {
            return this.unlocalizedName;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            for (BlockAtumPlank.EnumType enumType : values()) {
                META_LOOKUP[enumType.getMetadata()] = enumType;
            }
        }
    }
}