package com.teammetallurgy.atum.items.artifacts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemPtahsDestruction extends ItemPickaxe {

    public ItemPtahsDestruction(ToolMaterial material) {
        super(material);
        super.efficiencyOnProperMaterial = 11.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    // @Override
    // public float getDamageVsEntity(Entity entity, ItemStack stack) {
    // float damage = 4 + super.toolMaterial.getDamageVsEntity();
    // return entity instanceof EntityStoneSoldier ? (int) ((double) damage *
    // (2.0D + Math.random())) : damage;
    // }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.RARE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (Keyboard.isKeyDown(42)) {
            tooltip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line1"));
            tooltip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line2"));
        } else {
            tooltip.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.diamond;
    }

    /*@Override
    public void registerIcons(IIconRegister par1IIconRegister) {
        this.itemIcon = par1IIconRegister.registerIcon("atum:PtahsDestruction");
    }*/
}