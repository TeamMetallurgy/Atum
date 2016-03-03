package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockFertileSoil extends BlockDirt {

    public BlockFertileSoil() {
        super();
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setHardness(0.5F);
        this.setStepSound(Block.soundTypeGrass);
        setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, int par2, int par3, int par4, Random random) {
        if (!world.isRemote) {
            if ((world.getBlockLightValue(par2, par3 + 1, par4) < 4) && (world.getBlockLightOpacity(par2, par3 + 1, par4) > 2)) {
                world.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
            } else if (world.getBlockLightValue(par2, par3 + 1, par4) >= 9) {
                for (int l = 0; l < 4; l++) {
                    int i1 = par2 + random.nextInt(3) - 1;
                    int j1 = par3 + random.nextInt(5) - 3;
                    int k1 = par4 + random.nextInt(3) - 1;
                    Block l1 = world.getBlock(i1, j1 + 1, k1);

                    if ((world.getBlock(i1, j1, k1) == this) && (world.getBlockMetadata(i1, j1, k1) == 1) && (world.getBlockLightValue(i1, j1 + 1, k1) >= 4) && (world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)) {
                        world.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plant) {
        EnumPlantType plantType = plant.getPlantType(world, pos.up());

        if ((plant instanceof BlockFlower)) {
            return true;
        }

        if (plantType == EnumPlantType.Beach) {
            boolean hasWater = (world.getBlock(pos.down()).getMaterial() == Material.water) || (world.getBlock(pos.up()).getMaterial() == Material.water) || (world.getBlock(x, y, z - 1).getMaterial() == Material.water) || (world.getBlock(x, y, z + 1).getMaterial() == Material.water);

            return hasWater;
        }

        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        super.onNeighborBlockChange(world, x, y, z, neighborBlock);
        Material material = world.getBlock(x, y + 1, z).getMaterial();
        if (material.isSolid()) {
            world.setBlock(x, y, z, AtumBlocks.FERTILESOIL);
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(AtumBlocks.SAND);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }
}