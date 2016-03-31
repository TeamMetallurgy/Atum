package com.teammetallurgy.atum.items.artifacts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
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

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        if (Keyboard.isKeyDown(42)) {
            tooltip.add(TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedName() + ".line1"));
            tooltip.add(TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedName() + ".line2"));
        } else {
            tooltip.add(I18n.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + TextFormatting.DARK_GRAY + "[SHIFT]");
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.diamond;
    }
}