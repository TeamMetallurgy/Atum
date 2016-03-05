package com.teammetallurgy.atum.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class ItemLoot extends Item {

    private static String[] typeArray = new String[]{"idol", "necklace", "ring", "broach", "scepter"};
    private static String[] qualityArray = new String[]{"dirty", "silver", "gold", "sapphire", "ruby", "emerald", "diamond"};

    public ItemLoot() {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }

    public static ItemStack getRandomLoot(Random random, boolean isDirty) {
        int type = random.nextInt(typeArray.length);
        int quality = qualityArray.length - 6;
        return new ItemStack(AtumItems.LOOT, 1, type << 5 | quality | (isDirty ? 1 : 0));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int quality = stack.getItemDamage() >> 1 & 15;
        int type = stack.getItemDamage() >> 5 & 15;
        if (type < typeArray.length && quality < qualityArray.length) {
            return "item.atum.loot." + qualityArray[quality] + "." + typeArray[type];
        }
        return "item.atum.loot.unknown";
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {

        int quality = stack.getItemDamage() >> 1 & 15;
        int type = stack.getItemDamage() >> 5 & 15;
        if (type < typeArray.length && quality < qualityArray.length) {

            // Individual localization
            String unlocalizedName = this.getUnlocalizedName(stack) + ".name";
            if (StatCollector.canTranslate(unlocalizedName))
                return StatCollector.translateToLocal(unlocalizedName);

            // General localization
            String unlocalizedQuality = "item.atum.loot." + qualityArray[quality] + ".name";
            String unlocalizedType = "item.atum.loot." + typeArray[type] + ".name";

            if (StatCollector.canTranslate(unlocalizedQuality) && StatCollector.canTranslate(unlocalizedType)) {
                String LocalizedGeneralName = StatCollector.translateToLocal(unlocalizedQuality);
                LocalizedGeneralName += " " + StatCollector.translateToLocal(unlocalizedType);
                return LocalizedGeneralName;
            }

            // No localization
            return unlocalizedName;
        }

        return super.getItemStackDisplayName(stack);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        Block block = entityItem.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(entityItem.posX), MathHelper.floor_double(entityItem.posY), MathHelper.floor_double(entityItem.posZ))).getBlock();
        if (block == Blocks.water || block == Blocks.flowing_water) {
            ItemStack item = entityItem.getEntityItem();
            int damage = item.getItemDamage() >> 1;
            int quality = damage & 15;
            if (quality == 0) {
                damage |= (int) (Math.random() * 6.0D) + 1;
            }


            item.setItemDamage(damage << 1);
            entityItem.setEntityItemStack(item);
        }

        return super.onEntityItemUpdate(entityItem);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (int type = 0; type < typeArray.length; ++type) {
            subItems.add(new ItemStack(item, 1, type << 5 | 1));

            for (int quality = 1; quality < qualityArray.length; ++quality) {
                subItems.add(new ItemStack(item, 1, type << 5 | quality << 1));
            }
        }

    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        int dirty = par1 & 1;
        int quality = par1 >> 1 & 15;
        int type = par1 >> 5 & 15;
        return dirty == 1 ? this.iconArray[type * 7] : this.iconArray[type * 7 + quality];
    }*/

    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IIconRegister) {
        this.iconArray = new IIcon[typeArray.length * (qualityArray.length + 1)];

        for (int type = 0; type < 5; ++type) {
            this.iconArray[type * 7] = par1IIconRegister.registerIcon("atum:Dirty" + firstUpperCase(typeArray[type]));

            for (int quality = 1; quality < 7; ++quality) {
                this.iconArray[type * 7 + quality] = par1IIconRegister.registerIcon("atum:" + firstUpperCase(qualityArray[quality]) + firstUpperCase(typeArray[type]));
            }
        }

    }*/
}