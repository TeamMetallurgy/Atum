package com.teammetallurgy.atum.blocks.tileentity.crate;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiCrate extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    private TileEntityCrate tileEntity;
    
    public GuiCrate(InventoryPlayer playerInventory, TileEntityCrate crateTe) {
        super(new ContainerCrate(playerInventory, crateTe));
        tileEntity = crateTe;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int mouseX, int mouseZ) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 3 * 18 + 17);
        this.drawTexturedModalRect(k, l + 3 * 18 + 17, 0, 126, this.xSize, 96);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseZ) {
        fontRendererObj.drawString(tileEntity.hasCustomInventoryName() ? tileEntity.getInventoryName() : I18n.format(tileEntity.getInventoryName(), new Object[0]), 8, 6, 4210752);
    }

}
