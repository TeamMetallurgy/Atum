package com.teammetallurgy.atum.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
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
            if (I18n.canTranslate(unlocalizedName))
                return I18n.translateToLocal(unlocalizedName);

            // General localization
            String unlocalizedQuality = "item.atum.loot." + qualityArray[quality] + ".name";
            String unlocalizedType = "item.atum.loot." + typeArray[type] + ".name";

            if (I18n.canTranslate(unlocalizedQuality) && I18n.canTranslate(unlocalizedType)) {
                String LocalizedGeneralName = I18n.translateToLocal(unlocalizedQuality);
                LocalizedGeneralName += " " + I18n.translateToLocal(unlocalizedType);
                return LocalizedGeneralName;
            }

            // No localization
            return unlocalizedName;
        }

        return super.getItemStackDisplayName(stack);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        Block block = entityItem.world.getBlockState(new BlockPos(MathHelper.floor(entityItem.posX), MathHelper.floor(entityItem.posY), MathHelper.floor(entityItem.posZ))).getBlock();
        if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) {
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
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int type = 0; type < typeArray.length; ++type) {
            subItems.add(new ItemStack(this, 1, type << 5 | 1));

            for (int quality = 1; quality < qualityArray.length; ++quality) {
                subItems.add(new ItemStack(this, 1, type << 5 | quality << 1));
            }
        }
    }
}