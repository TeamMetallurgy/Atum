package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.tileentity.crate.TileEntityCrate;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockCrate extends BlockContainer { //TODO Fix custom inventory name
    public static final PropertyEnum<BlockAtumPlank.EnumType> VARIANT = PropertyEnum.create("variant", BlockAtumPlank.EnumType.class);

    protected BlockCrate() {
        super(Material.wood);
        this.setHardness(3.0F);
        this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockAtumPlank.EnumType.PALM));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCrate();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return true;

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null && tileEntity instanceof TileEntityCrate) {
            player.openGui(Atum.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }

        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityCrate) {
                ((TileEntityCrate) tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof IInventory) {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tileentity);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (BlockAtumPlank.EnumType enumType : BlockAtumPlank.EnumType.values()) {
            list.add(new ItemStack(item, 1, enumType.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockAtumPlank.EnumType.byMetadata(meta));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return (state.getValue(VARIANT)).getMapColor();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }
}