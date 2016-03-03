package com.teammetallurgy.atum.items.artifacts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemAnubisMercy extends Item {

    public ItemAnubisMercy() {
        super();
        this.setMaxDamage(20);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @SubscribeEvent
    public void onDamage(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            ItemStack stack = null;
            ItemStack[] damageAmount = player.inventory.mainInventory;
            int resistance = damageAmount.length;

            for (int i = 0; i < resistance; ++i) {
                ItemStack currStack = damageAmount[i];
                if (currStack != null && currStack.getItem() == this) {
                    stack = currStack;
                    break;
                }
            }

            if (stack == null) {
                return;
            }

            float amount = event.ammount;
            if (!event.source.isUnblockable()) {
                amount = (event.ammount * (25 - player.getTotalArmorValue()) + player.getAbsorptionAmount()) / 25.0F;
            }

            if (player.isPotionActive(Potion.resistance)) {
                resistance = 25 - (player.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
                amount = amount * (float) resistance / 25.0F;
            }

            if (Math.ceil((double) amount) >= (double) player.getHealth()) {
                event.setCanceled(true);
                this.respawnPlayer(event.entityLiving.worldObj, player);
                player.setHealth(player.getMaxHealth());
                player.getFoodStats().setFoodLevel(20);
                player.getFoodStats().setFoodSaturationLevel(20.0F);
                stack.damageItem(1, player);
                if (stack.getItemDamage() >= 20) {
                    stack = null;
                }
            }
        }

    }

    public void respawnPlayer(World world, EntityPlayer player) {
        BlockPos spawn = player.getBedLocation(player.dimension);
        if (spawn == null) {
            spawn = world.getSpawnPoint();
        }

        if (spawn == null) {
            spawn = world.getSpawnPoint();
        }

        spawn = player.getBedSpawnLocation(world, spawn, false);
        if (spawn == null) {
            spawn = world.getSpawnPoint();
        }

        player.rotationPitch = 0.0F;
        player.rotationYaw = 0.0F;
        player.setPositionAndUpdate((double) spawn.getX() + 0.5D, (double) spawn.getY() + 0.1D, (double) spawn.getZ());

        while (!world.getCollidingBoundingBoxes(player, player.getEntityBoundingBox()).isEmpty()) {
            player.setPosition(player.posX, player.posY + 1.0D, player.posZ);
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

        int remaining = stack.getMaxDamage() - stack.getItemDamage();
        String localizedRemaining = StatCollector.translateToLocalFormatted("tooltip.atum.usesRemaining", remaining);
        tooltip.add(localizedRemaining);
    }
}