package com.teammetallurgy.atum.blocks;

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
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAtumStoneSlab extends BlockSlab {
    public static final PropertyEnum<BlockAtumStoneSlab.EnumType> VARIANT = PropertyEnum.create("variant", BlockAtumStoneSlab.EnumType.class);
    private boolean isDoubleslab;

    public BlockAtumStoneSlab(boolean isDoubleSlab) {
        super(Material.rock);
        this.isDoubleslab = isDoubleSlab;

        IBlockState state = this.blockState.getBaseState();

        if (!this.isDouble()) {
            state = state.withProperty(HALF, BlockAtumStoneSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(state.withProperty(VARIANT, EnumType.LIMESTONE));

        this.setHardness(2.0F);
        this.useNeighborBrightness = true;
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return (state.getValue(VARIANT)).getMapColor();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(AtumBlocks.SLABS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos) {
        return AtumBlocks.SLABS == this ? Item.getItemFromBlock(this) : (this == AtumBlocks.DOUBLESLAB ? Item.getItemFromBlock(AtumBlocks.SLABS) : Item.getItemFromBlock(AtumBlocks.SLABS));
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName() + "." + EnumType.byMetadata(meta).getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return isDoubleslab;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Object getVariant(ItemStack stack) {
        return EnumType.byMetadata(stack.getMetadata() & 7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        if (item != Item.getItemFromBlock(AtumBlocks.DOUBLESLAB)) {
            for (EnumType enumType : EnumType.values()) {
                list.add(new ItemStack(item, 1, enumType.getMetadata()));
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 7));

        if (!this.isDouble()) {
            state = state.withProperty(HALF, (meta & 8) == 0 ? BlockAtumStoneSlab.EnumBlockHalf.BOTTOM : BlockAtumStoneSlab.EnumBlockHalf.TOP);
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
    protected BlockState createBlockState() {
        return this.isDouble() ? new BlockState(this, VARIANT) : new BlockState(this, new IProperty[]{HALF, VARIANT});
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }

    public static enum EnumType implements IStringSerializable {
        LIMESTONE(0, MapColor.sandColor, "limestone"),
        LIMESTONECRACKED(1, MapColor.sandColor, "limestone_cracked", "limestoneCracked"),
        SMALLBRICK(2, MapColor.sandColor, "small_brick", "smallBrick"),
        LARGEBRICK(3, MapColor.sandColor, "large_brick", "largeBrick"),
        CRACKEDBRICK(4, MapColor.sandColor, "cracked_brick", "carckedBrick"),
        CARVEDBRICK(4, MapColor.sandColor, "carved_brick", "carvedBrick");

        private static final BlockAtumStoneSlab.EnumType[] META_LOOKUP = new BlockAtumStoneSlab.EnumType[values().length];
        private final int meta;
        private final MapColor mapColor;
        private final String name;
        private final String unlocalizedName;

        private EnumType(int meta, MapColor mapColor, String name) {
            this(meta, mapColor, name, name);
        }

        private EnumType(int meta, MapColor mapColor, String name, String unlocalizedName) {
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

        public static BlockAtumStoneSlab.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        @Override
        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            for (BlockAtumStoneSlab.EnumType enumType : values()) {
                META_LOOKUP[enumType.getMetadata()] = enumType;
            }
        }
    }
}