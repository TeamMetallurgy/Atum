package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockPapyrus extends Block implements IPlantable {

    public BlockPapyrus() {
        super(Material.plants);
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
        float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        this.setTickRandomly(true);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par5EntityPlayer.capabilities.isCreativeMode) {
            this.updateTick(par1World, par2, par3, par4, new Random());
        }

        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if ((double) par5Random.nextFloat() <= 0.75D) {
            if (par1World.isAirBlock(par2, par3 + 1, par4)) {
                int l;
                for (l = 1; par1World.getBlock(par2, par3 - l, par4) == this; ++l) {
                    ;
                }

                if (l < 5) {
                    int i1 = par1World.getBlockMetadata(par2, par3, par4);
                    byte reqHeight = 0;
                    if (l == 1) {
                        reqHeight = 2;
                    } else if (l == 2) {
                        reqHeight = 4;
                    } else if (l == 3) {
                        reqHeight = 8;
                    } else if (l == 4) {
                        reqHeight = 15;
                    }

                    if (i1 >= reqHeight) {
                        par1World.setBlock(par2, par3 + 1, par4, this);
                        par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
                    } else {
                        par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 + 1, 4);
                    }
                }
            }

        }
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        Block block = par1World.getBlock(par2, par3 - 1, par4);
        return block != null && block.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
        this.checkBlockCoordValid(par1World, par2, par3, par4);
    }

    protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4) {
        if (!this.canBlockStay(par1World, par2, par3, par4)) {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }

    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
        return this.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plant) {
        if (plant.getPlant(world, pos.up()) == this) {
            return true;
        }
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
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
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return AtumItems.papyrusPlant;
    }

    @Override
    public Block getPlant(IBlockAccess world, BlockPos pos) {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess arg0, int arg1, int arg2, int arg3) {
        return EnumPlantType.Beach;
    }
}