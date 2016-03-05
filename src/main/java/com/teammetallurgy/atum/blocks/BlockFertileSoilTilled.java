package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockFertileSoilTilled extends Block {

    public static final PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 7);

    public BlockFertileSoilTilled() {
        super(Material.ground);
        this.setHardness(0.5F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setLightOpacity(255);
    }

    /*@Override
    @SideOnly(Side.CLIENT) //TODO Is this needed?
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
        int enchanted = (world.getBlockMetadata(par2, par3, par4) & 4 & 4) >> 2;
        if (enchanted == 1 && rand.nextDouble() > 0.6D) {
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            world.spawnParticle("happyVillager", (double) ((float) par2 + par5Random.nextFloat()), (double) par3 + (double) par5Random.nextFloat() * this.getBlockBoundsMaxY() * 0.4D + 1.0D, (double) ((float) par4 + par5Random.nextFloat()), d0, d1, d2);
        }
    }*/

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) (pos.getY() + 1), (double) (pos.getZ() + 1));
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        int i = (state.getValue(MOISTURE)).intValue();

        if (!this.hasWater(world, pos) && !world.canLightningStrike(pos.up())) {
            if ((i & 3) > 0 && rand.nextDouble() > 0.5D) {
                world.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
            } else if (!this.hasCrops(world, pos)) {
                world.setBlockState(pos, AtumBlocks.FERTILESOIL.getDefaultState());
            }
        } else {
            world.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(7)), 2);
        }

        Block block = world.getBlockState(pos.up()).getBlock();
        if (block != null) {
            for (int j = 0; j < 2; ++i) {
                block.updateTick(world, pos.up(), state, rand);
            }
        }
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
        if (entity instanceof EntityLivingBase) {
            if (!world.isRemote && world.rand.nextFloat() < fallDistance - 0.5F) {
                if (!(entity instanceof EntityPlayer) && !world.getGameRules().getBoolean("mobGriefing")) {
                    return;
                }

                world.setBlockState(pos, AtumBlocks.FERTILESOIL.getDefaultState());
            }
            super.onFallenUpon(world, pos, entity, fallDistance);
        }
    }

    private boolean hasCrops(World world, BlockPos pos) {
        Block block = world.getBlockState(pos.up()).getBlock();
        return block instanceof IPlantable && canSustainPlant(world, pos, EnumFacing.UP, (IPlantable) block);
    }

    private boolean hasWater(World worldIn, BlockPos pos) {
        for (BlockPos.MutableBlockPos mutableBlockPos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
            if (worldIn.getBlockState(mutableBlockPos).getBlock().getMaterial() == Material.water) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
        super.onNeighborBlockChange(world, pos, state, neighborBlock);

        if (world.getBlockState(pos.up()).getBlock().getMaterial().isSolid()) {
            world.setBlockState(pos, AtumBlocks.FERTILESOIL.getDefaultState());
        }
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        EnumPlantType plantType = plantable.getPlantType(world, pos.up());

        switch (plantType) {
            case Crop:
                return true;
            case Plains:
                return true;
            default:
                return super.canSustainPlant(world, pos, direction, plantable);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side) {
        switch (side) {
            case UP:
                return true;
            case NORTH:
            case SOUTH:
            case WEST:
            case EAST:
                Block block = world.getBlockState(pos).getBlock();
                return !block.isOpaqueCube() && block != AtumBlocks.FERTILESOIL;
            default:
                return super.shouldSideBeRendered(world, pos, side);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return AtumBlocks.SAND.getItemDropped(AtumBlocks.SAND.getDefaultState(), rand, fortune);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(MOISTURE, Integer.valueOf(meta & 7));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(AtumBlocks.SAND);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((Integer) state.getValue(MOISTURE)).intValue();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{MOISTURE});
    }
}