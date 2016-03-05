package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
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

public class BlockAtumBricks extends Block {
    public static final PropertyEnum<BlockAtumBricks.EnumType> VARIANT = PropertyEnum.create("variant", BlockAtumBricks.EnumType.class);

    public BlockAtumBricks() {
        super(Material.rock);
        //this.setResistance(200000.0F); //TODO?
        this.setBlockUnbreakable();
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.SMALL));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (BlockAtumBricks.EnumType enumType : BlockAtumBricks.EnumType.values()) {
            list.add(new ItemStack(item, 1, enumType.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockAtumBricks.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{VARIANT});
    }

    public static enum EnumType implements IStringSerializable {
        SMALL(0, "small"),
        LARGE(1, "large"),
        CRACKED(2, "cracked"),
        CARVED(3, "carved");

        private static final BlockAtumBricks.EnumType[] META_LOOKUP = new BlockAtumBricks.EnumType[values().length];
        private final int meta;
        private final String name;

        private EnumType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public static BlockAtumBricks.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.name;
        }

        static {
            for (BlockAtumBricks.EnumType enumType : values()) {
                META_LOOKUP[enumType.getMetadata()] = enumType;
            }
        }
    }

    /*@Override
    public float getBlockHardness(World world, BlockPos pos) { //TODO
        int meta = world.getBlockMetadata(x, y, z);
        if (meta != 2) {
            return -1F;
        }
        return 2.0F;
    }*/
    
    /*@Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) { //TODO ?
        int meta = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
        if (meta != 2) {
            return 200000.0F;
        }
        return 1.0F;
    }*/
}