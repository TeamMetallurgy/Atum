package com.teammetallurgy.atum.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

import java.util.List;
import java.util.Random;

public class ItemLoot extends Item {

    private static String[] typeArray = new String[]{"idol", "necklace", "ring", "broach", "scepter"};
    private static String[] qualityArray = new String[]{"dirty", "silver", "gold", "sapphire", "ruby", "emerald", "diamond"};
    IIcon[] iconArray;

    public ItemLoot() {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }

    public static ItemStack getRandomLoot(Random rand, boolean isDirty) {
        int type = rand.nextInt(typeArray.length);
        int quality = qualityArray.length - 6;
        return new ItemStack(AtumItems.ITEM_LOOT, 1, type << 5 | quality | (isDirty ? 1 : 0));
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int quality = par1ItemStack.getItemDamage() >> 1 & 15;
        int type = par1ItemStack.getItemDamage() >> 5 & 15;
        if (type < typeArray.length && quality < qualityArray.length) {
            return "item.loot." + qualityArray[quality] + "." + typeArray[type];
        }
        return "item.loot.unknown";
    }

    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack) {

        int quality = par1ItemStack.getItemDamage() >> 1 & 15;
        int type = par1ItemStack.getItemDamage() >> 5 & 15;
        if (type < typeArray.length && quality < qualityArray.length) {

            // Individual localization
            String unlocalizedName = this.getUnlocalizedName(par1ItemStack) + ".name";
            if (StatCollector.canTranslate(unlocalizedName))
                return StatCollector.translateToLocal(unlocalizedName);

            // General localization
            String unlocalizedQuality = "item.loot." + qualityArray[quality] + ".name";
            String unlocalizedType = "item.loot." + typeArray[type] + ".name";

            if (StatCollector.canTranslate(unlocalizedQuality) && StatCollector.canTranslate(unlocalizedType)) {
                String LocalizedGeneralName = StatCollector.translateToLocal(unlocalizedQuality);
                LocalizedGeneralName += " " + StatCollector.translateToLocal(unlocalizedType);
                return LocalizedGeneralName;
            }

            // No localization
            return unlocalizedName;
        }

        return super.getItemStackDisplayName(par1ItemStack);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        Block id = entityItem.worldObj.getBlock(MathHelper.floor_double(entityItem.posX), MathHelper.floor_double(entityItem.posY), MathHelper.floor_double(entityItem.posZ));
        if (id == Blocks.water || id == Blocks.flowing_water) {
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
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int type = 0; type < typeArray.length; ++type) {
            par3List.add(new ItemStack(par1, 1, type << 5 | 1));

            for (int quality = 1; quality < qualityArray.length; ++quality) {
                par3List.add(new ItemStack(par1, 1, type << 5 | quality << 1));
            }
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        int dirty = par1 & 1;
        int quality = par1 >> 1 & 15;
        int type = par1 >> 5 & 15;
        return dirty == 1 ? this.iconArray[type * 7] : this.iconArray[type * 7 + quality];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IIconRegister) {
        this.iconArray = new IIcon[typeArray.length * (qualityArray.length + 1)];

        for (int type = 0; type < 5; ++type) {
            this.iconArray[type * 7] = par1IIconRegister.registerIcon("atum:Dirty" + firstUpperCase(typeArray[type]));

            for (int quality = 1; quality < 7; ++quality) {
                this.iconArray[type * 7 + quality] = par1IIconRegister.registerIcon("atum:" + firstUpperCase(qualityArray[quality]) + firstUpperCase(typeArray[type]));
            }
        }

    }

    public String firstUpperCase(String s) {
        return Character.toString(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
    }
}