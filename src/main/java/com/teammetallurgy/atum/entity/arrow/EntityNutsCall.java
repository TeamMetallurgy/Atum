package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;

public class EntityNutsCall extends CustomArrow {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData;
    private boolean inGround;
    private int ticksInGround;
    private int ticksInAir;
    private double damage = 2.0D;
    private int knockbackStrength;
    public ItemStack stack;

    public EntityNutsCall(World world) {
        super(world);
    }

    public EntityNutsCall(World world, EntityLivingBase shooter) {
        super(world, shooter);
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(CRITICAL, (byte) 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if (block.getMaterial() != Material.air) {
            block.setBlockBoundsBasedOnState(this.worldObj, blockpos);
            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(this.worldObj, blockpos, iblockstate);

            if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3d(this.posX, this.posY, this.posZ))) {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inGround) {
            int j = block.getMetaFromState(iblockstate);

            if (block == this.inTile && j == this.inData) {
                ++this.ticksInGround;

                if (this.ticksInGround >= 1200) {
                    this.setDead();
                }
            } else {
                this.inGround = false;
                this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
                this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            ++this.ticksInAir;
            Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
            vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null) {
                vec3 = new Vec3d(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            Entity entity = null;
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int i = 0; i < list.size(); ++i) {
                Entity entity1 = list.get(i);

                if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
                    float f1 = 0.3F;
                    AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand((double) f1, (double) f1, (double) f1);
                    RayTraceResult movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);

                    if (movingobjectposition1 != null) {
                        double d1 = vec31.squareDistanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D) {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) movingobjectposition.entityHit;

                if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
                    movingobjectposition = null;
                }
            }

            if (movingobjectposition != null) {
                if (movingobjectposition.entityHit != null) {
                    float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    int l = MathHelper.ceiling_double_int((double) f2 * this.damage);

                    if (this.getIsCritical()) {
                        l += this.rand.nextInt(l / 2 + 2);
                    }

                    DamageSource damagesource;

                    if (this.shootingEntity == null) {
                        damagesource = DamageSource.causeArrowDamage(this, this);
                    } else {
                        damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
                    }

                    if (this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman)) {
                        movingobjectposition.entityHit.setFire(5);
                    }

                    /* Adds lightning strike 50% of the time, when Nuts call hits something. */
                    if (Math.random() > 0.5) {
                        worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, posX, posY, posZ));
                    }

                    if (movingobjectposition.entityHit.attackEntityFrom(damagesource, (float) l)) {
                        if (movingobjectposition.entityHit instanceof EntityLivingBase) {
                            EntityLivingBase entitylivingbase = (EntityLivingBase) movingobjectposition.entityHit;

                            if (!this.worldObj.isRemote) {
                                entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
                            }

                            if (this.knockbackStrength > 0) {
                                float f7 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

                                if (f7 > 0.0F) {
                                    movingobjectposition.entityHit.addVelocity(this.motionX * (double) this.knockbackStrength * 0.6000000238418579D / (double) f7, 0.1D, this.motionZ * (double) this.knockbackStrength * 0.6000000238418579D / (double) f7);
                                }
                            }

                            if (this.shootingEntity instanceof EntityLivingBase) {
                                EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
                                EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity, entitylivingbase);
                            }

                            if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                                ((EntityPlayerMP) this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
                            }
                        }

                        this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                        if (!(movingobjectposition.entityHit instanceof EntityEnderman)) {
                            this.setDead();
                        }
                    } else {
                        this.motionX *= -0.10000000149011612D;
                        this.motionY *= -0.10000000149011612D;
                        this.motionZ *= -0.10000000149011612D;
                        this.rotationYaw += 180.0F;
                        this.prevRotationYaw += 180.0F;
                        this.ticksInAir = 0;
                    }
                } else {
                    BlockPos blockpos1 = movingobjectposition.getBlockPos();
                    this.xTile = blockpos1.getX();
                    this.yTile = blockpos1.getY();
                    this.zTile = blockpos1.getZ();
                    IBlockState iblockstate1 = this.worldObj.getBlockState(blockpos1);
                    this.inTile = iblockstate1.getBlock();
                    this.inData = this.inTile.getMetaFromState(iblockstate1);
                    this.motionX = (double) ((float) (movingobjectposition.hitVec.xCoord - this.posX));
                    this.motionY = (double) ((float) (movingobjectposition.hitVec.yCoord - this.posY));
                    this.motionZ = (double) ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
                    float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    this.posX -= this.motionX / (double) f5 * 0.05000000074505806D;
                    this.posY -= this.motionY / (double) f5 * 0.05000000074505806D;
                    this.posZ -= this.motionZ / (double) f5 * 0.05000000074505806D;
                    this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    this.inGround = true;
                    this.arrowShake = 7;
                    this.setIsCritical(false);

                    if (this.inTile.getMaterial() != Material.air) {
                        this.inTile.onEntityCollidedWithBlock(this.worldObj, blockpos1, iblockstate1, this);
                    }
                }
            }

            if (this.getIsCritical()) {
                for (int k = 0; k < 4; ++k) {
                    this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double) k / 4.0D, this.posY + this.motionY * (double) k / 4.0D, this.posZ + this.motionZ * (double) k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ, new int[0]);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

            for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f3) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f4 = 0.99F;
            float f6 = 0.05F;

            if (this.isInWater()) {
                for (int i1 = 0; i1 < 4; ++i1) {
                    float f8 = 0.25F;
                    this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double) f8, this.posY - this.motionY * (double) f8, this.posZ - this.motionZ * (double) f8, this.motionX, this.motionY, this.motionZ, new int[0]);
                }

                f4 = 0.6F;
            }

            if (this.isWet()) {
                this.extinguish();
            }

            this.motionX *= (double) f4;
            this.motionY *= (double) f4;
            this.motionZ *= (double) f4;
            this.motionY -= (double) f6;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
    }


    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        NBTTagCompound stackTag = new NBTTagCompound();
        stack.writeToNBT(stackTag);

        tagCompound.setTag("stack", stackTag);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        super.readEntityFromNBT(tagCompound);

        NBTTagCompound stackTag = tagCompound.getCompoundTag("stack");
        stack = ItemStack.loadItemStackFromNBT(stackTag);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
            boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityIn.capabilities.isCreativeMode;

            if (this.canBePickedUp == 1 && !entityIn.inventory.addItemStackToInventory(stack)) {
                flag = false;
            }

            if (flag) {
                this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityIn.onItemPickup(this, 1);
                this.setDead();
            }
        }
    }

    @Override
    public void setKnockbackStrength(int knockbackStrength) {
        this.knockbackStrength = knockbackStrength;
    }

}