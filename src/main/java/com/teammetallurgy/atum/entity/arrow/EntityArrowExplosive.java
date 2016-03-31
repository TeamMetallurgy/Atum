package com.teammetallurgy.atum.entity.arrow;

import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class EntityArrowExplosive extends CustomArrow {
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

    public EntityArrowExplosive(World world) {
        super(world);
    }

    public EntityArrowExplosive(World world, EntityLivingBase shooter) {
        super(world, shooter);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(CRITICAL, (byte) 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
            this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI));
        }

        BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState state = this.worldObj.getBlockState(blockpos);
        Block block = state.getBlock();

        if (state.getMaterial() != Material.air) {
            AxisAlignedBB axisalignedbb = state.getCollisionBoundingBox(this.worldObj, blockpos);

            if (axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).isVecInside(new Vec3d(this.posX, this.posY, this.posZ))) {
                this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 2.0F, this.isBurning(), true);
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inGround) {
            int j = block.getMetaFromState(state);

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

            ++this.field_184552_b;
        } else {
            this.field_184552_b = 0;
            ++this.ticksInAir;
            Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytraceresult = this.worldObj.rayTraceBlocks(vec3d1, vec3d, false, true, false);
            vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (raytraceresult != null) {
                vec3d = new Vec3d(raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord);
            }

            Entity entity = this.func_184551_a(vec3d1, vec3d);

            if (entity != null) {
                raytraceresult = new RayTraceResult(entity);
            }

            if (raytraceresult != null && raytraceresult.entityHit != null && raytraceresult.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) raytraceresult.entityHit;

                if (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
                    raytraceresult = null;
                }
            }

            if (raytraceresult != null) {
                this.func_184549_a(raytraceresult);
                this.worldObj.createExplosion(this, raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord, 2.0F, true);
                this.worldObj.createExplosion(this, entity.posX, entity.posY, entity.posZ, 2.0F, true); //TODO
            }

            if (this.getIsCritical()) {
                for (int k = 0; k < 4; ++k) {
                    this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double) k / 4.0D, this.posY + this.motionY * (double) k / 4.0D, this.posZ + this.motionZ * (double) k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ, new int[0]);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

            for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f4) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
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
            float f1 = 0.99F;
            float f2 = 0.05F;

            if (this.isInWater()) {
                for (int i = 0; i < 4; ++i) {
                    float f3 = 0.25F;
                    this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double) f3, this.posY - this.motionY * (double) f3, this.posZ - this.motionZ * (double) f3, this.motionX, this.motionY, this.motionZ, new int[0]);
                }

                f1 = 0.6F;
            }

            if (this.isWet()) {
                this.extinguish();
            }

            this.motionX *= (double) f1;
            this.motionY *= (double) f1;
            this.motionZ *= (double) f1;
            this.motionY -= (double) f2;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
    }

    @Override
    public String getTexture() {
        return Constants.MODID + ":" + "textures/projectiles/arrows_exploding.png";
    }
}