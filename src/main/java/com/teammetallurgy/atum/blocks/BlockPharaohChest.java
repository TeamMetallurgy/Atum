package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.items.AtumLoot;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPharaohChest extends BlockChest {

    protected BlockPharaohChest() {
        super(0);
        this.setHardness(4.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPharaohChest();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.chest);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof TileEntityPharaohChest) {
                ((TileEntityPharaohChest) world.getTileEntity(pos)).setCustomName(stack.getDisplayName());
            }
        }

        AtumLoot.fillChest((TileEntityPharaohChest) world.getTileEntity(pos), 15, 0.9F);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityPharaohChest) {
            TileEntityPharaohChest pharaohChest = (TileEntityPharaohChest) te;
            if (!pharaohChest.hasSpawned()) {
                pharaohChest.spawn(player);
            }
        }

        if (world.isRemote) {
            return true;
        } else {
            IInventory inventory = this.getLockableContainer(world, pos);
            if (inventory != null) {
                player.displayGUIChest(inventory);
            }
            return true;
        }
    }
}