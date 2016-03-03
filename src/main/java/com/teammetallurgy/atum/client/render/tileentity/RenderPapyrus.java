package com.teammetallurgy.atum.client.render.tileentity;

public class RenderPapyrus { //TODO Move to json?
/*
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f = 1.0F;
        int l = block.colorMultiplier(world, x, y, z);
        float f1 = (float) (l >> 16 & 255) / 255.0F;
        float f2 = (float) (l >> 8 & 255) / 255.0F;
        float f3 = (float) (l & 255) / 255.0F;
        if (EntityRenderer.anaglyphEnable) {
            float d0 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float d1 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = d0;
            f2 = f5;
            f3 = d1;
        }

        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        double d01 = (double) x;
        double d11 = (double) y;
        double d2 = (double) z;
        if (block == Blocks.tallgrass) {
            long icon = (long) (x * 3129871) ^ (long) z * 116129781L ^ (long) y;
            icon = icon * icon * 42317861L + icon * 11L;
            d01 += ((double) ((float) (icon >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
            d11 += ((double) ((float) (icon >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
            d2 += ((double) ((float) (icon >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
        }

        IIcon icon1 = block.getBlockTextureFromSide(0);
        this.drawCrossedSquares(icon1, block, world.getBlockMetadata(x, y, z), d01, d11, d2, 1.0F);
        return true;
    }

    public void drawCrossedSquares(IIcon icon, Block par1Block, int par2, double par3, double par5, double par7, float par9) {
        Tessellator tessellator = Tessellator.instance;
        double d3 = (double) icon.getMinU();
        double d4 = (double) icon.getMinV();
        double d5 = (double) icon.getMaxU();
        double d6 = (double) icon.getMaxV();
        double d7 = 0.45D * (double) par9;
        double d8 = par3 + 0.5D - d7;
        double d9 = par3 + 0.5D + d7;
        double d10 = par7 + 0.5D - d7;
        double d11 = par7 + 0.5D + d7;
        tessellator.addVertexWithUV(d8, par5 + (double) par9, d10, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + (double) par9, d11, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + (double) par9, d11, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + (double) par9, d10, d5, d4);
        tessellator.addVertexWithUV(d8, par5 + (double) par9, d11, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + (double) par9, d10, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + (double) par9, d10, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + (double) par9, d11, d5, d4);
    }

    @Override
    public int getRenderId() {
        return 0;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    @Override
    public boolean shouldRender3DInInventory(int arg0) {
        return false;
    }*/
}