package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAtumOres extends BlockOre {

    public BlockAtumOres() {
        super();
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this == AtumBlocks.COAL_ORE ? Items.coal : (this == AtumBlocks.DIAMOND_ORE ? Items.diamond : (this == AtumBlocks.LAPIS_ORE ? Items.dye : Item.getItemFromBlock(this)));
    }

    @Override
    public int quantityDropped(Random random) {
        return this == AtumBlocks.LAPIS_ORE ? 4 + random.nextInt(5) : 1;
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
            int xp = 0;

            if (this == AtumBlocks.COAL_ORE) {
                xp = MathHelper.getRandomIntegerInRange(rand, 0, 2);
            } else if (this == AtumBlocks.DIAMOND_ORE) {
                xp = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            } else if (this == AtumBlocks.LAPIS_ORE) {
                xp = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            }
            return xp;
        }
        return 0;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this == AtumBlocks.LAPIS_ORE ? EnumDyeColor.BLUE.getDyeDamage() : 0;
    }
}