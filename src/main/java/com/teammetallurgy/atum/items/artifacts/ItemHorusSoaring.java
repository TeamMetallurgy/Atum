package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.entity.arrow.EntityArrowVelocity;
import com.teammetallurgy.atum.items.ItemAtumBaseBow;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemHorusSoaring extends ItemAtumBaseBow {

    public ItemHorusSoaring() {
        super();
        this.setMaxDamage(650);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.diamond;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.infinity, stack) > 0;
            ItemStack ammoStack = this.findAmmo(player);

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = ForgeEventFactory.onArrowLoose(stack, world, (EntityPlayer) entityLiving, i, ammoStack != null || flag);
            if (i < 0) return;

            if (ammoStack != null || flag) {
                if (ammoStack == null) {
                    ammoStack = new ItemStack(Items.arrow);
                }

                float f = getArrowVelocity(i);

                if ((double) f >= 0.1D) {
                    boolean flagAmmo = flag && ammoStack.getItem() instanceof ItemArrow;

                    if (!world.isRemote) {
                        EntityArrowVelocity entityarrow = new EntityArrowVelocity(world, player);
                        entityarrow.func_184547_a(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                        entityarrow.setDamage(entityarrow.getDamage() * 1.5D);

                        if (f == 1.0F) {
                            entityarrow.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.power, stack);
                        if (j > 0) {
                            entityarrow.setDamage(entityarrow.getDamage() + (double) j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.punch, stack);
                        if (k > 0) {
                            entityarrow.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.flame, stack) > 0) {
                            entityarrow.setFire(100);
                        }

                        stack.damageItem(1, player);

                        if (flagAmmo) {
                            entityarrow.canBePickedUp = EntityArrowVelocity.PickupStatus.CREATIVE_ONLY;
                        }

                        world.spawnEntityInWorld(entityarrow);
                    }

                    world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.entity_arrow_shoot, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flagAmmo) {
                        --ammoStack.stackSize;

                        if (ammoStack.stackSize == 0) {
                            player.inventory.deleteStack(ammoStack);
                        }
                    }
                    player.addStat(StatList.getObjectUseStats(this));
                }
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
    }
}