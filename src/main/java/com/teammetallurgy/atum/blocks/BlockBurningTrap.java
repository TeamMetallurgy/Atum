package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.blocks.tileentity.TileEntityBurningTrap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistryDefaulted;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBurningTrap extends BlockContainer {

    public static final IRegistry dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
    protected Random random = new Random();

    public BlockBurningTrap() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setHardness(-1.0F);
    }

    public static EnumFacing getFacing(int par0) {
        return EnumFacing.getFront(par0 & 7);
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4) {
        return par1World.getBlock(par2, par3 + 1, par4) == AtumBlocks.LARGEBRICK && par1World.getBlockMetadata(par2, par3 + 1, par4) == 1 ? -1.0F : super.blockHardness;
    }

    @Override
    public int tickRate(World par1World) {
        return 4;
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDispenserDefaultDirection(par1World, par2, par3, par4);
    }

    private void setDispenserDefaultDirection(World par1World, int par2, int par3, int par4) {
        if (!par1World.isRemote) {
            Block l = par1World.getBlock(par2, par3, par4 - 1);
            Block i1 = par1World.getBlock(par2, par3, par4 + 1);
            Block j1 = par1World.getBlock(par2 - 1, par3, par4);
            Block k1 = par1World.getBlock(par2 + 1, par3, par4);
            byte b0 = 3;
            if (l.isOpaqueCube() && !i1.isOpaqueCube()) {
                b0 = 3;
            }

            if (i1.isOpaqueCube() && !l.isOpaqueCube()) {
                b0 = 2;
            }

            if (j1.isOpaqueCube() && !k1.isOpaqueCube()) {
                b0 = 5;
            }

            if (k1.isOpaqueCube() && !j1.isOpaqueCube()) {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }

    }

    /*@SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int par1, int par2) {
        if (par2 == 0) {
            par2 = 3;
        }

        int k = par2 & 7;
        return par1 == k ? (k != 1 && k != 0 ? this.fireTrap : this.fireTrap) : (k != 1 && k != 0 ? (par1 != 1 && par1 != 0 ? super.blockIcon : this.blockIcon) : this.blockIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = par1IIconRegister.registerIcon("atum:TrapSide");
        this.fireTrap = par1IIconRegister.registerIcon("atum:TrapFire");
    }*/

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par1World.isRemote) {
            return true;
        } else {
            TileEntityBurningTrap tileEntityBurningTrap = (TileEntityBurningTrap) par1World.getTileEntity(par2, par3, par4);
            if (tileEntityBurningTrap != null) {
                ;
            }

            return true;
        }
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
        boolean flag = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        boolean flag1 = (i1 & 8) != 0;
        if (flag && !flag1) {
            par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
            par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 | 8, 4);
        } else if (!flag && flag1) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 & -9, 4);
        }

    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int l = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityBurningTrap();
    }
}