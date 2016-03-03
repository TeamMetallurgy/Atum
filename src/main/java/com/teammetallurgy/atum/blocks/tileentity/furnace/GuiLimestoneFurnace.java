package com.teammetallurgy.atum.blocks.tileentity.furnace;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLimestoneFurnace /*extends GuiContainer*/ { //TODO Is this even needed? Looks like we can just use GuiFurnace directly.
    /*private final InventoryPlayer playerInventory;
    private IInventory tileFurnace;

    public GuiLimestoneFurnace(InventoryPlayer inventoryPlayer, IInventory furnace) {
        super(new ContainerFurnace(inventoryPlayer, furnace));
        this.playerInventory = inventoryPlayer;
        this.tileFurnace = furnace;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseZ) {
        String s = StatCollector.translateToLocal(this.playerInventory.getName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(new ResourceLocation("textures/gui/container/furnace.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.playerInventory.isBurning()) {
            i1 = this.playerInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.playerInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }*/
}