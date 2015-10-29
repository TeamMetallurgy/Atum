package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDate extends Block {

    public int renderID = RenderingRegistry.getNextAvailableRenderId();

    public BlockDate() {
        super(Material.plants);
        this.setBlockName("date");
        this.setHardness(0.5F);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return renderID;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_) {
        if (world.getBlock(x, y + 1, z) != AtumBlocks.BLOCK_LEAVES && !world.isRemote) {
            EntityItem entityItem = new EntityItem(world, (double) x, (double) y, (double) z, new ItemStack(AtumItems.ITEM_DATE, 0, this.quantityDropped(new Random())));
            entityItem.dropItem(AtumItems.ITEM_DATE, this.quantityDropped(new Random()));
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return AtumItems.ITEM_DATE;
    }

    @Override
    public int quantityDropped(Random rand) {
        return rand.nextInt(4) + 1;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(AtumItems.ITEM_DATE);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegistry) {
        this.blockIcon = iconRegistry.registerIcon("atum:AtumDate");
    }
}
