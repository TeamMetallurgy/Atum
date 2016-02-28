package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.items.AtumLoot;
import com.teammetallurgy.atum.utils.Constants;
import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.items.AtumItems;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class EntityPharaoh extends EntityMob implements IBossDisplayData {
    public static String[] prefix = {"Ama", "Ata", "Ato", "Bak", "Cal", "Djet", "Eje", "For", "Gol", "Gut", "Hop", "Hor", "Huni", "Iam", "Jor", "Kal", "Khas", "Khor", "Lat", "Mal", "Not", "Oap", "Pra", "Qo", "Ras", "Shas", "Thoth", "Tui", "Uld", "Ver", "Wot", "Xo", "Yat", "Zyt", "Khep"};
    public static String[] suffix = {"Ahat", "Amesh", "Amon", "Anut", "Baroom", "Chanta", "Erant", "Funam", "Daresh", "Djer", "Hotesh", "Khaden", "Kron", "Gorkum", "Ialenter", "Ma'at", "Narmer", "Radeem", "Jaloom", "Lepsha", "Quor", "Oleshet", "Peput", "Talat", "Ulam", "Veresh", "Ranesh", "Snef", "Wollolo", "Hathor", "Intef", "Neferk", "Khatne", "Tepy", "Moret"};
    public static String[] numeral = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV"};
    int linkedX;
    int linkedY;
    int linkedZ;
    int stage;
    private int suffixID = 0;
    private int prefixID = 0;
    private int numID = 0;
    private int regenTime = 0;

    public EntityPharaoh(World par1World) {
        super(par1World);
        this.experienceValue = 250;
        stage = 0;

        this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.ITEM_SCEPTER));

        for (int i = 0; i < this.equipmentDropChances.length; ++i) {
            this.equipmentDropChances[i] = 0F;
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10.0D);
    }

    public void link(int x, int y, int z) {
        linkedX = x;
        linkedY = y;
        linkedZ = z;
        dataWatcher.updateObject(21, linkedX);
        dataWatcher.updateObject(22, linkedY);
        dataWatcher.updateObject(23, linkedZ);
    }

    /**
     * Makes the entity despawn if requirements are reached
     */
    @Override
    protected void despawnEntity() {
    }

    @Override
    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);

        if (par1DamageSource.damageType == "player") {
            EntityPlayer slayer = (EntityPlayer) par1DamageSource.getEntity();
            if (!worldObj.isRemote) {
                List<EntityPlayer> players = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList;
                for (EntityPlayer player : players) {
                    player.addChatMessage(new ChatComponentText(this.getCommandSenderName() + " " + StatCollector.translateToLocal("chat.atum.killPharaoh") + " " + slayer.getGameProfile().getName()));
                }
            }
        }

        Integer chestX = dataWatcher.getWatchableObjectInt(21);
        Integer chestY = dataWatcher.getWatchableObjectInt(22);
        Integer chestZ = dataWatcher.getWatchableObjectInt(23);
        
        if (chestX != null && chestY != null && chestZ != null) {
            TileEntity te = worldObj.getTileEntity(chestX, chestY, chestZ);
            if (te != null) {
                if (te instanceof TileEntityPharaohChest) {
                    TileEntityPharaohChest tepc = (TileEntityPharaohChest) te;
                    tepc.setOpenable();
                }
            } else {
                Constants.LOG.error("Unable to find chest coords for "  + this.getCommandSenderName() + " on " +  chestX + ", " + chestY + ", " + chestZ);
            }
        } else {
            Constants.LOG.error("Unable to get chest coords for "  + this.getCommandSenderName());
        }
    }

    @Override
    public String getCommandSenderName() {
        try {
            int s = this.dataWatcher.getWatchableObjectInt(18);
            int p = this.dataWatcher.getWatchableObjectInt(19);
            int n = this.dataWatcher.getWatchableObjectInt(20);
            return "Pharaoh " + StatCollector.translateToLocal("entity.atum.pharaoh." + prefix[p]) + StatCollector.translateToLocal("entity.atum.pharaoh." + suffix[s]) + " " + numeral[n];
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * knocks back this entity
     */
    @Override
    public void knockBack(Entity par1Entity, float par2, double par3, double par5) {
        this.isAirBorne = true;
        float f = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
        float f1 = 0.3F;
        this.motionX /= 2.0D;
        this.motionY /= 2.0D;
        this.motionZ /= 2.0D;
        this.motionX -= par3 / (double) f * (double) f1;
        this.motionZ -= par5 / (double) f * (double) f1;

        if (this.motionY > 0.4000000059604645D) {
            this.motionY = 0.4000000059604645D;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.isFireDamage()) {
            par2 = 0;
        }

        if (super.attackEntityFrom(par1DamageSource, par2)) {
            if (par1DamageSource.getEntity() != null) {
                Entity par1Entity = par1DamageSource.getEntity();
                int j = 0;
                if (par1Entity instanceof EntityLiving) {
                    j += EnchantmentHelper.getKnockbackModifier((EntityLiving) par1Entity, this);

                    if (j > 0) {
                        this.motionX /= 0.6D;
                        this.motionZ /= 0.6D;
                        this.addVelocity((double) (MathHelper.sin(par1Entity.rotationYaw * (float) Math.PI / 180.0F) * (float) j * 0.5F), -0.1D, (double) (-MathHelper.cos(par1Entity.rotationYaw * (float) Math.PI / 180.0F) * (float) j * 0.5F));
                    }
                }

            }

            if (this.getHealth() < this.getMaxHealth() * 0.75 && stage == 0) {
                stage++;
                spawnGuards();
            } else if (stage == 1 && this.getHealth() < this.getMaxHealth() * 0.5) {
                stage++;
                spawnGuards();
            } else if (stage == 2 && this.getHealth() < this.getMaxHealth() * 0.25) {
                stage++;
                spawnGuards();
            }
            return true;
        }

        return false;
    }

    /**
     * Destroys all blocks that aren't associated with 'The End' inside the
     * given bounding box.
     */

    private boolean destroyBlocksInAABB(AxisAlignedBB par1AxisAlignedBB) {
        int minX = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int minY = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int minZ = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int maxX = MathHelper.floor_double(par1AxisAlignedBB.maxX);
        int maxY = MathHelper.floor_double(par1AxisAlignedBB.maxY);
        int maxZ = MathHelper.floor_double(par1AxisAlignedBB.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    Block block = this.worldObj.getBlock(x, y, z);
                    int metadata = this.worldObj.getBlockMetadata(x, y, z);

                    if (block != null) {
                        if (block != AtumBlocks.BLOCK_LARGEBRICK && block != AtumBlocks.BLOCK_PHARAOHCHEST && block != Blocks.bedrock && block.isBlockSolid(worldObj, x, y, z, 0)) {
                            block.dropBlockAsItem(worldObj, x, y, z, metadata, 0);
                            flag1 = this.worldObj.setBlockToAir(x, y, z) || flag1;
                        }

                        flag = true;
                    }
                }
            }
        }

        if (flag1) {
            double d0 = par1AxisAlignedBB.minX + (par1AxisAlignedBB.maxX - par1AxisAlignedBB.minX) * (double) this.rand.nextFloat();
            double d1 = par1AxisAlignedBB.minY + (par1AxisAlignedBB.maxY - par1AxisAlignedBB.minY) * (double) this.rand.nextFloat();
            double d2 = par1AxisAlignedBB.minZ + (par1AxisAlignedBB.maxZ - par1AxisAlignedBB.minZ) * (double) this.rand.nextFloat();
            this.worldObj.spawnParticle("largeexplode", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }

        return flag;
    }

    private void spawnGuards() {
        int numSpawned = 0;

        if (trySpawnMummy((int) posX + 1, (int) posY, (int) posZ)) {
            numSpawned++;
        }
        if (numSpawned >= 2)
            return;

        if (trySpawnMummy((int) posX - 1, (int) posY, (int) posZ - 1)) {
            numSpawned++;
        }
        if (numSpawned >= 2)
            return;

        if (trySpawnMummy((int) posX, (int) posY, (int) posZ + 1)) {
            numSpawned++;
        }
        if (numSpawned >= 2)
            return;

        if (trySpawnMummy((int) posX, (int) posY, (int) posZ - 1)) {
            numSpawned++;
        }
        if (numSpawned >= 2)
            return;

        if (trySpawnMummy((int) posX + 1, (int) posY, (int) posZ + 1)) {
            numSpawned++;
        }
        if (numSpawned >= 2)
            return;

        if (trySpawnMummy((int) posX - 1, (int) posY, (int) posZ - 1)) {
            numSpawned++;
        }
        if (numSpawned >= 2)
            return;

        if (trySpawnMummy((int) posX - 1, (int) posY, (int) posZ + 1)) {
            numSpawned++;
        }
        if (numSpawned >= 2)
            return;

        if (trySpawnMummy((int) posX + 1, (int) posY, (int) posZ - 1)) {
            numSpawned++;
        }
        if (numSpawned >= 2)
            return;

    }

    public boolean trySpawnMummy(int x, int y, int z) {
        EntityMummy mummy1 = new EntityMummy(worldObj);
        mummy1.setPosition(x, y, z);
        if (mummy1.getCanSpawnHere()) {
            if (!worldObj.isRemote)
                worldObj.spawnEntityInWorld(mummy1);
            mummy1.spawnExplosionParticle();
            return true;
        }

        return false;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("suffix", dataWatcher.getWatchableObjectInt(18));
        par1NBTTagCompound.setInteger("prefix", dataWatcher.getWatchableObjectInt(19));
        par1NBTTagCompound.setInteger("numeral", dataWatcher.getWatchableObjectInt(20));
        par1NBTTagCompound.setInteger("chestX", dataWatcher.getWatchableObjectInt(21));
        par1NBTTagCompound.setInteger("chestY", dataWatcher.getWatchableObjectInt(22));
        par1NBTTagCompound.setInteger("chestZ", dataWatcher.getWatchableObjectInt(23));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.dataWatcher.updateObject(16, Float.valueOf(this.prevHealth));
        suffixID = par1NBTTagCompound.getInteger("suffix");
        prefixID = par1NBTTagCompound.getInteger("prefix");
        numID = par1NBTTagCompound.getInteger("numeral");
        linkedX = par1NBTTagCompound.getInteger("chestX");
        linkedY = par1NBTTagCompound.getInteger("chestY");
        linkedZ = par1NBTTagCompound.getInteger("chestZ");
        this.dataWatcher.updateObject(18, new Integer(suffixID));
        this.dataWatcher.updateObject(19, new Integer(prefixID));
        this.dataWatcher.updateObject(20, new Integer(numID));
        this.dataWatcher.updateObject(21, new Integer(linkedX));
        this.dataWatcher.updateObject(22, new Integer(linkedY));
        this.dataWatcher.updateObject(23, new Integer(linkedZ));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Float(100));
        if (suffixID == 0 && prefixID == 0 && numID == 0) {
            suffixID = rand.nextInt(suffix.length);
            prefixID = rand.nextInt(prefix.length);
            numID = rand.nextInt(numeral.length);
        }
        this.dataWatcher.addObject(18, new Integer(suffixID));
        this.dataWatcher.addObject(19, new Integer(prefixID));
        this.dataWatcher.addObject(20, new Integer(numID));
        this.dataWatcher.addObject(21, new Integer(0));
        this.dataWatcher.addObject(22, new Integer(0));
        this.dataWatcher.addObject(23, new Integer(0));
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.worldObj.isRemote && this.worldObj.difficultySetting.getDifficultyId() == 0) {
            TileEntity te = worldObj.getTileEntity(linkedX, linkedY, linkedZ);
            if (te instanceof TileEntityPharaohChest) {
                ((TileEntityPharaohChest) te).setPharaohDespawned();
            }
            this.setDead();
        }
    }

    @Override
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            this.dataWatcher.updateObject(16, Float.valueOf(this.prevHealth));
        }

        if (regenTime++ > 20) {
            regenTime = 0;
            this.heal(1);
        }

        super.onLivingUpdate();

        if (!worldObj.isRemote)
            this.destroyBlocksInAABB(this.boundingBox.expand(0.1, 0, 0.1));
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity
     * has recently been hit by a player. @param par2 - Level of Looting used to
     * kill this mob.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2) {
        int amount = rand.nextInt(2) + 1;
        this.dropItem(Items.gold_ingot, amount);

        this.entityDropItem(AtumLoot.getRandomArtifact(), 0.0F);
    }
}
