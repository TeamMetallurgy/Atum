package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.world.decorators.WorldGenPalm;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAtumSapling extends BlockBush implements IGrowable {
    public static final PropertyEnum<BlockAtumPlank.EnumType> TYPE = PropertyEnum.create("type", BlockAtumPlank.EnumType.class);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public BlockAtumSapling() {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockAtumPlank.EnumType.PALM).withProperty(STAGE, 0));
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @Override
    public String getLocalizedName() {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockAtumPlank.EnumType.PALM.getUnlocalizedName() + ".name");
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == AtumBlocks.SAND || ground == Blocks.grass || ground == Blocks.dirt;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, pos, state, rand);

            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(20) == 0) {
                this.grow(world, pos, state, rand);
            }
        }
    }

    public void grow(World world, BlockPos pos, IBlockState state, Random random) {
        if ((state.getValue(STAGE)).intValue() == 0) {
            world.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(world, pos, state, random);
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        Block blockDown = world.getBlockState(pos.down()).getBlock();
        return blockDown == AtumBlocks.SAND || blockDown == Blocks.grass || blockDown == Blocks.dirt;
    }

    public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, pos)) return;
        WorldGenerator generator = (rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true));
        int i = 0;
        int j = 0;
        boolean flag = false;

        switch (state.getValue(TYPE)) {
            case PALM:
                generator = new WorldGenPalm(true);
                break;
            case DEADWOOD:
                break;
        }

        IBlockState stateAir = Blocks.air.getDefaultState();

        if (flag) {
            world.setBlockState(pos.add(i, 0, j), stateAir, 4);
            world.setBlockState(pos.add(i + 1, 0, j), stateAir, 4);
            world.setBlockState(pos.add(i, 0, j + 1), stateAir, 4);
            world.setBlockState(pos.add(i + 1, 0, j + 1), stateAir, 4);
        } else {
            world.setBlockState(pos, stateAir, 4);
        }

        if (!generator.generate(world, rand, pos.add(i, 0, j))) {
            if (flag) {
                world.setBlockState(pos.add(i, 0, j), state, 4);
                world.setBlockState(pos.add(i + 1, 0, j), state, 4);
                world.setBlockState(pos.add(i, 0, j + 1), state, 4);
                world.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
            } else {
                world.setBlockState(pos, state, 4);
            }
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(TYPE)).getMetadata();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(item, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return (double) world.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        this.grow(world, pos, state, rand);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, BlockAtumPlank.EnumType.byMetadata(meta & 7)).withProperty(STAGE, (meta & 8) >> 3);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | (state.getValue(TYPE)).getMetadata();
        i = i | state.getValue(STAGE) << 3;
        return i;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{TYPE, STAGE});
    }
}