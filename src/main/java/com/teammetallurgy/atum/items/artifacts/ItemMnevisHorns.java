package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.items.ItemTexturedArmor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

import javax.annotation.Nullable;

public class ItemMnevisHorns extends ItemTexturedArmor {

    public ItemMnevisHorns(ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(material, renderIndex, slot);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this && event.getSource() instanceof EntityDamageSource) {
            EntityDamageSource source = (EntityDamageSource) event.getSource();
            if (source.getEntity() != null) {
                source.getEntity().attackEntityFrom(DamageSource.GENERIC, (int) ((double) event.getAmount() / 2.0D));
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
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag tooltipType) {
        if (Keyboard.isKeyDown(42)) {
            tooltip.add(TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedName() + ".line1"));
            tooltip.add(TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedName() + ".line2"));
        } else {
            tooltip.add(I18n.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + TextFormatting.DARK_GRAY + "[SHIFT]");
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.DIAMOND;
    }
}