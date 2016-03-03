package com.teammetallurgy.atum.client.render.tileentity;

public class RenderDate /*implements ISimpleBlockRenderingHandler*/ { //TODO Move to JSON? Or BakedModel?

   /* ModelDate modelDate = new ModelDate();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        if (world != null) {
            tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        } else {
            tessellator.setBrightness(1);
        }

        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        IIcon icon = block.getIcon(0, 0);
        double sideu1 = (double) icon.getInterpolatedU(0.0D);
        double sideu2 = (double) icon.getInterpolatedU(6.0D);
        double sidev1 = (double) icon.getInterpolatedV(14.0D);
        double sidev2 = (double) icon.getInterpolatedV(6.0D);
        double topu1 = (double) icon.getInterpolatedU(0.0D);
        double topu2 = (double) icon.getInterpolatedU(6.0D);
        double topv1 = (double) icon.getInterpolatedV(6.0D);
        double topv2 = (double) icon.getInterpolatedV(0.0D);
        double fx1 = 0.3125D;
        double fx2 = 0.6875D;
        double fz = 0.6875D;
        double y1 = 0.125D;
        double y2 = 0.625D;
        double bz = 0.3125D;
        tessellator.addVertexWithUV((double) x + fx1, (double) y + y1, (double) z + fz, sideu1, sidev1);
        tessellator.addVertexWithUV((double) x + fx2, (double) y + y1, (double) z + fz, sideu2, sidev1);
        tessellator.addVertexWithUV((double) x + fx2, (double) y + y2, (double) z + fz, sideu2, sidev2);
        tessellator.addVertexWithUV((double) x + fx1, (double) y + y2, (double) z + fz, sideu1, sidev2);
        tessellator.addVertexWithUV((double) x + fx1, (double) y + y1, (double) z + bz, sideu1, sidev1);
        tessellator.addVertexWithUV((double) x + fx1, (double) y + y2, (double) z + bz, sideu1, sidev2);
        tessellator.addVertexWithUV((double) x + fx2, (double) y + y2, (double) z + bz, sideu2, sidev2);
        tessellator.addVertexWithUV((double) x + fx2, (double) y + y1, (double) z + bz, sideu2, sidev1);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y1, (double) z + fx1, sideu1, sidev1);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y2, (double) z + fx1, sideu1, sidev2);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y2, (double) z + fx2, sideu2, sidev2);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y1, (double) z + fx2, sideu2, sidev1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y1, (double) z + fx1, sideu1, sidev1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y1, (double) z + fx2, sideu2, sidev1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y2, (double) z + fx2, sideu2, sidev2);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y2, (double) z + fx1, sideu1, sidev2);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y2, (double) z + fx1, topu1, topv1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y2, (double) z + fx2, topu1, topv2);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y2, (double) z + fx2, topu2, topv2);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y2, (double) z + fx1, topu2, topv1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y1, (double) z + fx1, topu1, topv1);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y1, (double) z + fx1, topu2, topv1);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y1, (double) z + fx2, topu2, topv2);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y1, (double) z + fx2, topu1, topv2);
        sideu1 = (double) icon.getInterpolatedU(6.0D);
        sideu2 = (double) icon.getInterpolatedU(8.0D);
        sidev1 = (double) icon.getInterpolatedV(12.0D);
        sidev2 = (double) icon.getInterpolatedV(6.0D);
        topu1 = (double) icon.getInterpolatedU(6.0D);
        topu2 = (double) icon.getInterpolatedU(8.0D);
        topv1 = (double) icon.getInterpolatedV(2.0D);
        topv2 = (double) icon.getInterpolatedV(0.0D);
        fx1 = 0.4375D;
        fx2 = 0.5625D;
        bz = 0.4375D;
        fz = 0.5625D;
        y1 = 0.625D;
        y2 = 1.0D;
        tessellator.addVertexWithUV((double) x + fx1, (double) y + y1, (double) z + fz, sideu1, sidev1);
        tessellator.addVertexWithUV((double) x + fx2, (double) y + y1, (double) z + fz, sideu2, sidev1);
        tessellator.addVertexWithUV((double) x + fx2, (double) y + y2, (double) z + fz, sideu2, sidev2);
        tessellator.addVertexWithUV((double) x + fx1, (double) y + y2, (double) z + fz, sideu1, sidev2);
        tessellator.addVertexWithUV((double) x + fx1, (double) y + y1, (double) z + bz, sideu1, sidev1);
        tessellator.addVertexWithUV((double) x + fx1, (double) y + y2, (double) z + bz, sideu1, sidev2);
        tessellator.addVertexWithUV((double) x + fx2, (double) y + y2, (double) z + bz, sideu2, sidev2);
        tessellator.addVertexWithUV((double) x + fx2, (double) y + y1, (double) z + bz, sideu2, sidev1);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y1, (double) z + fx1, sideu1, sidev1);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y2, (double) z + fx1, sideu1, sidev2);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y2, (double) z + fx2, sideu2, sidev2);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y1, (double) z + fx2, sideu2, sidev1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y1, (double) z + fx1, sideu1, sidev1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y1, (double) z + fx2, sideu2, sidev1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y2, (double) z + fx2, sideu2, sidev2);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y2, (double) z + fx1, sideu1, sidev2);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y2, (double) z + fx1, topu1, topv1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y2, (double) z + fx2, topu1, topv2);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y2, (double) z + fx2, topu2, topv2);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y2, (double) z + fx1, topu2, topv1);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y1, (double) z + fx1, topu1, topv1);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y1, (double) z + fx1, topu2, topv1);
        tessellator.addVertexWithUV((double) x + fz, (double) y + y1, (double) z + fx2, topu2, topv2);
        tessellator.addVertexWithUV((double) x + bz, (double) y + y1, (double) z + fx2, topu1, topv2);
        return true;
    }

    @Override
    public int getRenderId() {
        return ((BlockDate) AtumBlocks.DATEBLOCK).renderID;
    }

    @Override
    public boolean shouldRender3DInInventory(int arg0) {
        return false;
    }*/
}
