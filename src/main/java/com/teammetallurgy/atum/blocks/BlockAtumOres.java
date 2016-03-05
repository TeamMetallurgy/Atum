package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAtumOres extends BlockOre {

    public BlockAtumOres() {
        super();
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this == AtumBlocks.COALORE ? Items.coal : (this == AtumBlocks.DIAMONDORE ? Items.diamond : (this == AtumBlocks.LAPISORE ? Items.dye : Item.getItemFromBlock(this)));
    }

    @Override
    public int quantityDropped(Random random) {
        return this == AtumBlocks.LAPISORE ? 4 + random.nextInt(5) : 1;
    }

    @Override
    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.getBlockState(pos);
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
            int xp = 0;

            if (this == AtumBlocks.COALORE) {
                xp = MathHelper.getRandomIntegerInRange(rand, 0, 2);
            } else if (this == AtumBlocks.DIAMONDORE) {
                xp = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            } else if (this == AtumBlocks.LAPISORE) {
                xp = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            }
            return xp;
        }
        return 0;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this == AtumBlocks.LAPISORE ? EnumDyeColor.BLUE.getDyeDamage() : 0;
    }
}