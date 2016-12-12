package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.items.ItemTexturedArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemHorusFlight extends ItemTexturedArmor {

    public ItemHorusFlight(ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(material, renderIndex, slot);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);

        if (world.isRemote || stack == null || stack.getItem() != this) {
            return;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.jump, 40, 1, true, true));
    }

    @SubscribeEvent
    public void onJump(LivingJumpEvent event) {
        if (event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET) != null && event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == this) {
            event.getEntityLiving().motionY += 0.2D;
            event.getEntityLiving().motionX *= 1.2D;
            event.getEntityLiving().motionZ *= 1.2D;
        }
    }

    @SubscribeEvent
    public void onFallDamage(LivingFallEvent event) {
        if (event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET) != null && event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == AtumItems.HORUS_FLIGHT) {
            event.setDistance(0.0F);
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
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.diamond;
    }
}