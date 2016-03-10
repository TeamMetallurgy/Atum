package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.entity.arrow.EntityArrowDoubleShot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemNeithsAudacity extends ItemBow {

    public ItemNeithsAudacity() {
        super();
        super.maxStackSize = 1;
        this.setMaxDamage(384);
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
            tooltip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line1"));
            tooltip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line2"));
        } else {
            tooltip.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int timeLeft) {
        int j = this.getMaxItemUseDuration(stack) - timeLeft;
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            j = event.charge;
            boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;
            if (flag || player.inventory.hasItemStack(new ItemStack(Items.arrow, 2))) {
                float f = (float) j / 20.0F;
                f = (f * f + f * 2.0F) / 3.0F;
                if ((double) f < 0.1D) {
                    return;
                }

                if (f > 1.0F) {
                    f = 1.0F;
                }

                EntityArrowDoubleShot entityarrow = new EntityArrowDoubleShot(world, player, f * 2.0F);
                EntityArrowDoubleShot entityarrow1 = new EntityArrowDoubleShot(world, player, f * 2.0F);
                entityarrow.motionX += Math.random() * 0.4D - 0.2D;
                entityarrow.motionY += Math.random() * 0.4D - 0.2D;
                entityarrow.motionZ += Math.random() * 0.4D - 0.2D;
                entityarrow1.motionX += Math.random() * 0.4D - 0.2D;
                entityarrow1.motionY += Math.random() * 0.4D - 0.2D;
                entityarrow1.motionZ += Math.random() * 0.4D - 0.2D;
                entityarrow.setDamage(entityarrow.getDamage() + 0.5D);
                entityarrow1.setDamage(entityarrow.getDamage() + 0.5D);
                if (f == 1.0F) {
                    entityarrow.setIsCritical(true);
                    entityarrow1.setIsCritical(true);
                }

                int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                if (k > 0) {
                    entityarrow.setDamage(entityarrow.getDamage() + (double) k * 0.5D + 0.5D);
                    entityarrow1.setDamage(entityarrow.getDamage() + (double) k * 0.5D + 0.5D);
                }

                int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
                if (l > 0) {
                    entityarrow.setKnockbackStrength(l);
                    entityarrow1.setKnockbackStrength(l);
                }

                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0) {
                    entityarrow.setFire(100);
                    entityarrow1.setFire(100);
                }

                stack.damageItem(1, player);
                world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (Item.itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                if (flag) {
                    entityarrow.canBePickedUp = 2;
                    entityarrow1.canBePickedUp = 2;
                } else {
                    player.inventory.consumeInventoryItem(Items.arrow);
                    player.inventory.consumeInventoryItem(Items.arrow);
                }

                player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

                if (!world.isRemote) {
                    world.spawnEntityInWorld(entityarrow);
                    world.spawnEntityInWorld(entityarrow1);
                }
            }

        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) { //TODO Check if this is needed, or it's fine that it's only in the super class.
        ArrowNockEvent event = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return event.result;
        } else {
            if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.arrow)) {
                player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
            }
            return stack;
        }
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.diamond;
    }
}