package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.world.biome.AtumBiomeProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderAtum extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return AtumWorlds.ATUM;
    }

    @Override
    protected void init() {
         this.biomeProvider = new AtumBiomeProvider(world.getWorldInfo());
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderAtum(world, world.getSeed(), true, world.getWorldInfo().getGeneratorOptions());
    }

    @Override
    protected void generateLightBrightnessTable() {
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i) {
            float scaledBrightness = (float) i / 15.0F;
            float f1 = 1.0F - scaledBrightness;
            if (i < 5) {
                super.lightBrightnessTable[i] = 0.5F * scaledBrightness / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
            } else {
                super.lightBrightnessTable[i] = scaledBrightness / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
            }
        }

    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        int j = (int) (worldTime % 48000L);
        float f1 = ((float) j + partialTicks) / 48000.0F - 0.25F;
        if (f1 < 0.0F) {
            ++f1;
        }

        if (f1 > 1.0F) {
            --f1;
        }

        float f2 = f1;
        f1 = 1.0F - (float) ((Math.cos((double) f1 * 3.141592653589793D) + 1.0D) / 2.0D);
        f1 = f2 + (f1 - f2) / 3.0F;
        return f1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float par1, float par2) {
        float f = MathHelper.cos(par1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
        if (f < 0.2F) {
            f = 0.2F;
        }

        if (f > 1.0F) {
            f = 1.0F;
        }

        float f1 = 0.9F * f;
        float f2 = 0.75F * f;
        float f3 = 0.6F * f;
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public String getWelcomeMessage() {
        return "Entering Atum";
    }

    @Override
    public String getDepartMessage() {
        return "Leaving Atum";
    }
}