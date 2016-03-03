package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.items.ItemTexturedArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemSekhmetsWrath extends ItemTexturedArmor {

    public ItemSekhmetsWrath(int renderIndex, int armorType) {
        super(ArmorMaterial.DIAMOND, renderIndex, armorType);
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
        player.addPotionEffect(new PotionEffect(12, 20, 0, true, true)); //TODO Check 2nd true (ShowEffect)
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.entityLiving.getEquipmentInSlot(3) != null && event.entityLiving.getEquipmentInSlot(3).getItem() == this && event.source instanceof EntityDamageSource) {
            EntityDamageSource source = (EntityDamageSource) event.source;
            if (source.getEntity() != null && Math.random() > 0.5D) {
                source.getEntity().setFire(10);
            }
        }
    }

    @SubscribeEvent
    public void onLivingAttack(LivingHurtEvent event) {
        if (event.entityLiving.getEquipmentInSlot(3) != null && event.entityLiving.getEquipmentInSlot(3).getItem() == this && event.source.isFireDamage()) {
            event.ammount /= 2;
            if (event.ammount == 0 && Math.random() > 0.5D) {
                event.ammount = 1;
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