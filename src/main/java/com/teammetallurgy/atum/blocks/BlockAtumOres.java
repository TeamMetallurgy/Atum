package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
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
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return this == AtumBlocks.COALORE ? Items.coal : (this == AtumBlocks.DIAMONDORE ? Items.diamond : (this == AtumBlocks.LAPISORE ? Items.dye : Item.getItemFromBlock(this)));
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return this == AtumBlocks.LAPISORE ? 4 + par1Random.nextInt(5) : 1;
    }

    @Override
    public int quantityDroppedWithBonus(int par1, Random random) {
        if (par1 > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, random, par1)) {
            int j = random.nextInt(par1 + 2) - 1;
            if (j < 0) {
                j = 0;
            }

            return this.quantityDropped(random) * (j + 1);
        } else {
            return this.quantityDropped(random);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7) {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

        if (this.getItemDropped(par5, world.rand, par7) != Item.getItemFromBlock(this)) {
            int j1 = 0;
            if (this == AtumBlocks.COALORE) {
                j1 = MathHelper.getRandomIntegerInRange(world.rand, 0, 2);
            } else if (this == AtumBlocks.DIAMONDORE) {
                j1 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
            } else if (this == AtumBlocks.LAPISORE) {
                j1 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
            }

            this.dropXpOnBlockBreak(world, par2, par3, par4, j1);
        }

    }

    @Override
    public int damageDropped(int par1) {
        return this == AtumBlocks.LAPISORE ? 4 : 0;
    }
}
