package com.teammetallurgy.atum.client.render.item;

public class RendererItemBow { //TODO Move to json
    /*@Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type == ItemRenderType.EQUIPPED ? false : false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        EntityLivingBase e = (EntityLivingBase) data[1];
        GL11.glPopMatrix();
        boolean renderFirstPerson = false;
        if (e instanceof EntityPlayer && ((EntityPlayer) e).getGameProfile().getName().equals(Minecraft.getMinecraft().thePlayer.getGameProfile().getName()) && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            renderFirstPerson = true;
        }

        if ((!renderFirstPerson) && (e instanceof EntityPlayer || e instanceof EntityBanditArcher)) {
            float f22 = 0.375F;
            GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(1.0F / f22, 1.0F / f22, 1.0F / f22);
            GL11.glTranslatef(-0.25F, -0.1875F, 0.1875F);
            float f2 = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(f2, -f2, f2);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
        this.renderItem(e, item, 0);
        GL11.glPushMatrix();
    }

    public void renderItem(EntityLivingBase par1EntityLiving, ItemStack par2ItemStack, int par3) {
        GL11.glPushMatrix();
        IIcon icon = par1EntityLiving.getItemIcon(par2ItemStack, par3);
        if (icon == null) {
            GL11.glPopMatrix();
        } else {
            Tessellator tessellator = Tessellator.instance;
            float f = icon.getMinU();
            float f1 = icon.getMaxU();
            float f2 = icon.getMinV();
            float f3 = icon.getMaxV();
            float f4 = 0.0F;
            float f5 = 0.3F;
            GL11.glEnable('\u803a');
            GL11.glTranslatef(-f4, -f5, 0.0F);
            float f6 = 1.5F;
            GL11.glScalef(f6, f6, f6);
            GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
            ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
            if (par2ItemStack != null && par2ItemStack.hasEffect(0) && par3 == 0) {
                GL11.glDepthFunc(514);
                GL11.glDisable(2896);
                // Minecraft.getMinecraft().renderEngine.bindTexture(new
                // ResourceLocation("%blur%/misc/glint.png"));
                GL11.glEnable(3042);
                GL11.glBlendFunc(768, 1);
                float f7 = 0.76F;
                GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
                GL11.glMatrixMode(5890);
                GL11.glPushMatrix();
                float f8 = 0.125F;
                GL11.glScalef(f8, f8, f8);
                float f9 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                GL11.glTranslatef(f9, 0.0F, 0.0F);
                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(f8, f8, f8);
                f9 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-f9, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(5888);
                GL11.glDisable(3042);
                GL11.glEnable(2896);
                GL11.glDepthFunc(515);
                GL11.glDisable('\u803a');
            }

            GL11.glPopMatrix();
        }
    }*/
}
