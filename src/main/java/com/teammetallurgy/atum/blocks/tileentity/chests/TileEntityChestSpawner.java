package com.teammetallurgy.atum.blocks.tileentity.chests;

import com.teammetallurgy.atum.blocks.BlockChestSpawner;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class TileEntityChestSpawner extends TileEntityChest {

    private final MobSpawnerBaseLogic spawnerLogic = new MobSpawnerBaseLogic() {
        @Override
        public void func_98267_a(int id) {
            TileEntityChestSpawner.this.worldObj.addBlockEvent(TileEntityChestSpawner.this.pos, Blocks.mob_spawner, id, 0);
        }

        @Override
        public World getSpawnerWorld() {
            return TileEntityChestSpawner.this.worldObj;
        }

        @Override
        public BlockPos getSpawnerPosition() {
            return TileEntityChestSpawner.this.pos;
        }

        @Override
        public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart entity) {
            super.setRandomEntity(entity);

            if (this.getSpawnerWorld() != null) {
                this.getSpawnerWorld().markBlockForUpdate(TileEntityChestSpawner.this.pos);
            }
        }
    };

    private ItemStack[] chestContents = new ItemStack[27];
    private String customName;

    public TileEntityChestSpawner() {
        this.spawnerLogic.setEntityName(entityName());

        this.spawnerLogic.setDelayToMin(0);
    }

    private String entityName() {
        int entityID = (int) (Math.random() * 6.0D);
        switch (entityID) {
            case 0:
                return "AtumMummy";
            case 1:
                return "AtumBanditWarrior";
            case 2:
                return "AtumBanditArcher";
            case 3:
                return "AtumDustySkeleton";
            case 4:
                return "AtumDesertGhost";
            case 5:
                return "AtumStoneSoldier";
            case 6:
                return "AtumDesertWolf";
            default:
                return "";
        }
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.chestContents[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.chestContents[index] != null) {
            if (this.chestContents[index].stackSize <= count) {
                ItemStack stack = this.chestContents[index];
                this.chestContents[index] = null;
                this.markDirty();
                return stack;
            } else {
                ItemStack stack1 = this.chestContents[index].splitStack(count);

                if (this.chestContents[index].stackSize == 0) {
                    this.chestContents[index] = null;
                }
                this.markDirty();
                return stack1;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (this.chestContents[index] != null) {
            ItemStack stack = this.chestContents[index];
            this.chestContents[index] = null;
            return stack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.chestContents[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.chest";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && this.customName.length() > 0;
    }

    @Override
    public void setCustomName(String name) {
        this.customName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];

        if (tagCompound.hasKey("CustomName", 8)) {
            this.customName = tagCompound.getString("CustomName");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < this.chestContents.length) {
                this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }

        this.spawnerLogic.readFromNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.chestContents[i].writeToNBT(compound);
                tagList.appendTag(compound);
            }
        }
        tagCompound.setTag("Items", tagList);

        if (this.hasCustomName()) {
            tagCompound.setString("CustomName", this.customName);
        }

        this.spawnerLogic.writeToNBT(tagCompound);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        double d0 = 4.0D;
        double d1 = 3.0D;
        List<EntityMob> list = super.worldObj.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB((double) super.pos.getX() - d0, (double) super.pos.getY() - d1, (double) super.pos.getZ() - d0, (double) super.pos.getX() + d0, (double) super.pos.getY() + d1, (double) super.pos.getZ() + d0));
        if (!list.isEmpty()) {
            if (!super.worldObj.isRemote) {
                player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("chat.atum.enemies")));
            }
            return false;
        } else {
            return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }


    @Override
    public void update() {
        this.spawnerLogic.updateSpawner();
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        if (!player.isSpectator() && this.getBlockType() instanceof BlockChestSpawner) {
            --this.numPlayersUsing;
            this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
            this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType());
        }
    }

    @Override
    public void invalidate() {
        this.tileEntityInvalid = true;
        this.updateContainingBlockInfo();
    }
}