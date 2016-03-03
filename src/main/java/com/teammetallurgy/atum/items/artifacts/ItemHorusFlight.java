package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.items.ItemTexturedArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemHorusFlight extends ItemTexturedArmor {

    public ItemHorusFlight(ArmorMaterial material, int renderIndex, int armorType) {
        super(material, renderIndex, armorType);
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
        player.addPotionEffect(new PotionEffect(8, 40, 1, true, true)); //TODO Check showParticles boolean
    }

    @SubscribeEvent
    public void onJump(LivingJumpEvent event) {
        if (event.entityLiving.getEquipmentInSlot(1) != null && event.entityLiving.getEquipmentInSlot(1).getItem() == this) {
            event.entityLiving.motionY += 0.2D;
            event.entityLiving.motionX *= 1.2D;
            event.entityLiving.motionZ *= 1.2D;
        }
    }

    @SubscribeEvent
    public void onFallDamage(LivingFallEvent event) {
        if (event.entityLiving.getEquipmentInSlot(1) != null && event.entityLiving.getEquipmentInSlot(1).getItem() == AtumItems.HORUS_FLIGHT) {
            event.distance = 0.0F;
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
}