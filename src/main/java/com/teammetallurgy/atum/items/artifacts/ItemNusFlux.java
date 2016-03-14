package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.entity.EntityStoneguard;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemNusFlux extends ItemSword {

    public ItemNusFlux(ToolMaterial material) {
        super(material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!attacker.worldObj.isRemote && Math.random() > 0.75D && !(target instanceof EntityStoneguard)) {
            double dx = target.posX - attacker.posX;
            double dz = target.posZ - attacker.posZ;
            double magnitude = Math.sqrt(dx * dx + dz * dz);
            dx /= magnitude;
            dz /= magnitude;
            target.isAirBorne = true;
            target.addVelocity(dx / 2.0D, 1.5D, dz / 2.0D);
            if (target.motionY > 1.0D) {
                target.motionY = 1.0D;
            }

            // entity.attackEntityFrom(DamageSource.generic,
            // this.getDamageVsEntity(entity, par1ItemStack));
            if (attacker.worldObj.isRemote) {
                this.spawnParticle(target);
            }
        }
        return super.hitEntity(stack, target, attacker);
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticle(Entity entity) {
        Minecraft.getMinecraft().effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.CRIT);
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