package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockAtumBricks;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.items.AtumLoot;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;

public class EntityPharaoh extends EntityMob implements IBossDisplayData {
    public static String[] prefix = {"Ama", "Ata", "Ato", "Bak", "Cal", "Djet", "Eje", "For", "Gol", "Gut", "Hop", "Hor", "Huni", "Iam", "Jor", "Kal", "Khas", "Khor", "Lat", "Mal", "Not", "Oap", "Pra", "Qo", "Ras", "Shas", "Thoth", "Tui", "Uld", "Ver", "Wot", "Xo", "Yat", "Zyt", "Khep"};
    public static String[] suffix = {"Ahat", "Amesh", "Amon", "Anut", "Baroom", "Chanta", "Erant", "Funam", "Daresh", "Djer", "Hotesh", "Khaden", "Kron", "Gorkum", "Ialenter", "Ma'at", "Narmer", "Radeem", "Jaloom", "Lepsha", "Quor", "Oleshet", "Peput", "Talat", "Ulam", "Veresh", "Ranesh", "Snef", "Wollolo", "Hathor", "Intef", "Neferk", "Khatne", "Tepy", "Moret"};
    public static String[] numeral = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV"};
    int linkedX, linkedY, linkedZ;
    int stage;
    private int suffixID = 0;
    private int prefixID = 0;
    private int numID = 0;
    private int regenTime = 0;

    public EntityPharaoh(World world) {
        super(world);
        this.experienceValue = 250;
        stage = 0;

        this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.SCEPTER));

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

    @Override
    protected void despawnEntity() {
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);

        if (source.damageType == "player") {
            EntityPlayer slayer = (EntityPlayer) source.getEntity();
            if (!worldObj.isRemote) {
                List<EntityPlayerMP> players = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList;
                for (EntityPlayer player : players) {
                    player.addChatMessage(new ChatComponentText(this.getName() + " " + StatCollector.translateToLocal("chat.atum.killPharaoh") + " " + slayer.getGameProfile().getName()));
                }
            }
        }

        Integer chestX = dataWatcher.getWatchableObjectInt(21);
        Integer chestY = dataWatcher.getWatchableObjectInt(22);
        Integer chestZ = dataWatcher.getWatchableObjectInt(23);
        BlockPos chestPos = new BlockPos(chestX, chestY, chestZ);

        TileEntity te = worldObj.getTileEntity(chestPos);
        if (te != null) {
            if (te instanceof TileEntityPharaohChest) {
                TileEntityPharaohChest tepc = (TileEntityPharaohChest) te;
                tepc.setOpenable();
            }
        } else {
            Constants.LOG.error("Unable to find chest coords for " + this.getName() + " on " + chestPos);
        }
    }

    @Override
    public String getName() {
        try {
            int s = this.dataWatcher.getWatchableObjectInt(18);
            int p = this.dataWatcher.getWatchableObjectInt(19);
            int n = this.dataWatcher.getWatchableObjectInt(20);
            return "Pharaoh " + StatCollector.translateToLocal("entity.atum.pharaoh." + prefix[p]) + StatCollector.translateToLocal("entity.atum.pharaoh." + suffix[s]) + " " + numeral[n];
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void knockBack(Entity entity, float par2, double par3, double par5) {
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
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.isFireDamage()) {
            amount = 0;
        }

        if (super.attackEntityFrom(source, amount)) {
            if (source.getEntity() != null) {
                Entity par1Entity = source.getEntity();
                int j = 0;
                if (par1Entity instanceof EntityLiving) {
                    j += EnchantmentHelper.getKnockbackModifier(this);

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

    private boolean destroyBlocksInAABB(AxisAlignedBB axisAlignedBB) {
        int minX = MathHelper.floor_double(axisAlignedBB.minX);
        int minY = MathHelper.floor_double(axisAlignedBB.minY);
        int minZ = MathHelper.floor_double(axisAlignedBB.minZ);
        int maxX = MathHelper.floor_double(axisAlignedBB.maxX);
        int maxY = MathHelper.floor_double(axisAlignedBB.maxY);
        int maxZ = MathHelper.floor_double(axisAlignedBB.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    BlockPos pos = new BlockPos(x, y, z);
                    IBlockState state = worldObj.getBlockState(pos);

                    if (state != null) {
                        if (state != AtumBlocks.LIMESTONEBRICK.getDefaultState().withProperty(BlockAtumBricks.VARIANT, BlockAtumBricks.EnumType.LARGE) && state != AtumBlocks.PHARAOHCHEST.getDefaultState() && state != Blocks.bedrock.getDefaultState() && state.getBlock().getMaterial().isSolid()) {
                            state.getBlock().dropBlockAsItem(worldObj, pos, state, 0);
                            flag1 = this.worldObj.setBlockToAir(pos) || flag1;
                        }

                        flag = true;
                    }
                }
            }
        }

        if (flag1) {
            double d0 = axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * (double) this.rand.nextFloat();
            double d1 = axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * (double) this.rand.nextFloat();
            double d2 = axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * (double) this.rand.nextFloat();
            worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);

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
        if (numSpawned >= 2) {
            return;
        }
    }

    public boolean trySpawnMummy(int x, int y, int z) {
        EntityMummy entityMummy = new EntityMummy(worldObj);
        entityMummy.setPosition(x, y, z);
        if (entityMummy.getCanSpawnHere()) {
            if (!worldObj.isRemote) {
                worldObj.spawnEntityInWorld(entityMummy);
            }
            entityMummy.spawnExplosionParticle();
            return true;
        }
        return false;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("suffix", dataWatcher.getWatchableObjectInt(18));
        tagCompound.setInteger("prefix", dataWatcher.getWatchableObjectInt(19));
        tagCompound.setInteger("numeral", dataWatcher.getWatchableObjectInt(20));
        tagCompound.setInteger("chestX", dataWatcher.getWatchableObjectInt(21));
        tagCompound.setInteger("chestY", dataWatcher.getWatchableObjectInt(22));
        tagCompound.setInteger("chestZ", dataWatcher.getWatchableObjectInt(23));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        super.readEntityFromNBT(tagCompound);
        this.dataWatcher.updateObject(16, Float.valueOf(this.getHealth()));
        suffixID = tagCompound.getInteger("suffix");
        prefixID = tagCompound.getInteger("prefix");
        numID = tagCompound.getInteger("numeral");
        linkedX = tagCompound.getInteger("chestX");
        linkedY = tagCompound.getInteger("chestY");
        linkedZ = tagCompound.getInteger("chestZ");
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

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.worldObj.isRemote && this.worldObj.getDifficulty().getDifficultyId() == 0) {
            TileEntity te = worldObj.getTileEntity(new BlockPos(linkedX, linkedY, linkedZ));
            if (te instanceof TileEntityPharaohChest) {
                ((TileEntityPharaohChest) te).setPharaohDespawned();
            }
            this.setDead();
        }
    }

    @Override
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            this.dataWatcher.updateObject(16, Float.valueOf(this.getHealth()));
        }

        if (regenTime++ > 20) {
            regenTime = 0;
            this.heal(1);
        }

        super.onLivingUpdate();

        if (!worldObj.isRemote)
            this.destroyBlocksInAABB(this.getEntityBoundingBox().expand(0.1, 0, 0.1));
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        int amount = rand.nextInt(2) + 1;
        this.dropItem(Items.gold_ingot, amount);

        this.entityDropItem(AtumLoot.getRandomArtifact(), 0.0F);
    }
}