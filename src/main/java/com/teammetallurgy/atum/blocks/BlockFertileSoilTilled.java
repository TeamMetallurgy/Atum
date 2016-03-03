package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
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

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
        int enchanted = (world.getBlockMetadata(par2, par3, par4) & 4 & 4) >> 2;
        if (enchanted == 1 && rand.nextDouble() > 0.6D) {
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            world.spawnParticle("happyVillager", (double) ((float) par2 + par5Random.nextFloat()), (double) par3 + (double) par5Random.nextFloat() * this.getBlockBoundsMaxY() * 0.4D + 1.0D, (double) ((float) par4 + par5Random.nextFloat()), d0, d1, d2);
        }

    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return AxisAlignedBB.getBoundingBox((double) (par2 + 0), (double) (par3 + 0), (double) (par4 + 0), (double) (par2 + 1), (double) (par3 + 1), (double) (par4 + 1));
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
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        if (!this.isWaterNearby(par1World, par2, par3, par4) && !par1World.canLightningStrikeAt(par2, par3 + 1, par4)) {
            if ((meta & 3) > 0 && Math.random() > 0.5D) {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, meta - 1, 2);
            } else if (!this.isCropsNearby(par1World, par2, par3, par4)) {
                this.revertToDirt(par1World, par2, par3, par4);
            }
        } else {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 3, 2);
        }

        Block block = par1World.getBlock(par2, par3 + 1, par4);
        if (block != null) {
            for (int i = 0; i < 2; ++i) {
                block.updateTick(par1World, par2, par3 + 1, par4, par5Random);
            }
        }

    }

    @Override
    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6) {
        if (!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F) {
            if (!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
                return;
            }

            this.revertToDirt(par1World, par2, par3, par4);
        }

    }

    private boolean isCropsNearby(World par1World, int par2, int par3, int par4) {
        byte b0 = 0;

        for (int l = par2 - b0; l <= par2 + b0; ++l) {
            for (int i1 = par4 - b0; i1 <= par4 + b0; ++i1) {
                Block j1 = par1World.getBlock(l, par3 + 1, i1);
                if (j1 instanceof IPlantable && this.canSustainPlant(par1World, par2, par3, par4, ForgeDirection.UP, (IPlantable) j1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isWaterNearby(World par1World, int par2, int par3, int par4) {
        for (int l = par2 - 4; l <= par2 + 4; ++l) {
            for (int i1 = par3; i1 <= par3 + 1; ++i1) {
                for (int j1 = par4 - 4; j1 <= par4 + 4; ++j1) {
                    if (par1World.getBlock(l, i1, j1).getMaterial() == Material.water) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        Material material = par1World.getBlock(par2, par3 + 1, par4).getMaterial();
        if (material.isSolid()) {
            this.revertToDirt(par1World, par2, par3, par4);
        }

    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, EnumFacing facing, IPlantable plant) {
        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);
        return plantType == EnumPlantType.Crop;
    }

    @Override
    public Item getItemDropped(int par1, Random p_149650_2_, int p_149650_3_) {
        return par1 >> 3 == 0 ? Item.getItemFromBlock(AtumBlocks.SAND) : Item.getItemFromBlock(Blocks.dirt);
    }

    public void revertToDirt(World world, int x, int y, int z) {
        int type = world.getBlockMetadata(x, y, z) >> 3;
        if (type == 0) {
            world.setBlock(x, y, z, AtumBlocks.FERTILESOIL);
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        } else {
            world.setBlock(x, y, z, Blocks.dirt);
        }

    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        int type = world.getBlockMetadata(x, y, z) >> 3;
        return type == 0 ? new ItemStack(AtumBlocks.SAND) : new ItemStack(Blocks.dirt);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        this.farmlandWet = par1IIconRegister.registerIcon("farmland_wet");
        this.farmlandDry = par1IIconRegister.registerIcon("farmland_dry");
    }
}