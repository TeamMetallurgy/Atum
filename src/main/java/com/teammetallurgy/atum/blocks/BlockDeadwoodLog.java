package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class BlockDeadwoodLog extends BlockPalmLog {
    
    private static Random RANDOM = new Random();

    protected BlockDeadwoodLog() {
        super();
        this.setBlockName("deadwoodLog");
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }
    
    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.tree_top = register.registerIcon("atum:deadwood_top");
        this.tree_side = register.registerIcon("atum:deadwood_side");
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {

        if (metadata == 3 && RANDOM.nextInt(100) <= 25) {
            // Drop Beetles.
            int amount = RANDOM.nextInt(1) + 1;
            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
            drops.add(new ItemStack (AtumItems.ITEM_DEADWOOD_BEETLE, amount));
            return drops;
        }

        return super.getDrops(world, x, y, z, metadata, fortune);
    }
}
