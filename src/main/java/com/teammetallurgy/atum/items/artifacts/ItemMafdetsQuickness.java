package com.teammetallurgy.atum.items.artifacts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemMafdetsQuickness extends Item {

    public ItemMafdetsQuickness() {
        super();
        this.setMaxDamage(24000);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (isSelected && player.onGround && player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == this) {
                doEffect(player, stack);
            }
        }
    }

    public void doEffect(EntityPlayer player, ItemStack item) {
        player.addPotionEffect(new PotionEffect(MobEffects.moveSpeed, 40, 0, false, true)); //TODO Check showParticles parameter
        if (!player.capabilities.isCreativeMode) {
            if (item.getItemDamage() == 1) {
                item.damageItem(1, player);
            } else {
                item.setItemDamage(item.getItemDamage() + 1);
            }
        }

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

        double remaining = ((stack.getMaxDamage() - stack.getItemDamage()) / 12) / 100.0D;
        String localizedRemaining = I18n.translateToLocalFormatted("tooltip.atum.minutesRemaining", remaining);
        tooltip.add(localizedRemaining);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.diamond;
    }
}