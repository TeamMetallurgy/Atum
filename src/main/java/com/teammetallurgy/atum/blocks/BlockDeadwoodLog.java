package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class BlockDeadwoodLog extends BlockPalmLog {

    private static Random RANDOM = new Random();

    protected BlockDeadwoodLog() {
        super();
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {

        if (metadata == 3 && RANDOM.nextInt(100) <= 25) {
            // Drop Beetles.
            int amount = RANDOM.nextInt(1) + 1;
            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
            drops.add(new ItemStack(AtumItems.DEADWOOD_BEETLE, amount));
            return drops;
        }

        return super.getDrops(world, x, y, z, metadata, fortune);
    }
}