package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.WorldGenShrub;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.Post;
import net.minecraftforge.event.terraingen.OreGenEvent.Pre;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class BiomeDecoratorAtum extends BiomeDecorator {

    private float shrubChance;

    public BiomeDecoratorAtum() {
        super();
        this.dirtGen = genMinableStone(AtumBlocks.SAND.getDefaultState(), 32);
        this.gravelGen = genMinableStone(AtumBlocks.LIMESTONE_GRAVEL.getDefaultState(), 32);
        this.clayGen = genMinableStone(Blocks.clay.getDefaultState(), 16);
        if (AtumConfig.COAL_ENABLED) { this.coalGen = genMinableStone(AtumBlocks.COAL_ORE.getDefaultState(), AtumConfig.COAL_VEIN); }
        if (AtumConfig.IRON_ENABLED) { this.ironGen = genMinableStone(AtumBlocks.IRON_ORE.getDefaultState(), AtumConfig.IRON_VEIN); }
        if (AtumConfig.GOLD_ENABLED) { this.goldGen = genMinableStone(AtumBlocks.GOLD_ORE.getDefaultState(), AtumConfig.GOLD_VEIN); }
        if (AtumConfig.REDSTONE_ENABLED) { this.redstoneGen = genMinableStone(AtumBlocks.REDSTONE_ORE.getDefaultState(), AtumConfig.REDSTONE_VEIN); }
        if (AtumConfig.DIAMOND_ENABLED) { this.diamondGen = genMinableStone(AtumBlocks.DIAMOND_ORE.getDefaultState(), AtumConfig.DIAMOND_VEIN); }
        if (AtumConfig.LAPIS_ENABLED) { this.lapisGen = genMinableStone(AtumBlocks.LAPIS_ORE.getDefaultState(), AtumConfig.LAPIS_VEIN); }

        this.treesPerChunk = 0;
        this.shrubChance = 0.3F;
        this.generateLakes = false;
    }

    public WorldGenMinable genMinableStone(IBlockState state, int size) { //TODO Better name?
        return new WorldGenMinable(state, size, BlockHelper.forBlock(AtumBlocks.LIMESTONE));
    }

    @Override
    public void decorate(World world, Random random, BiomeGenBase biomeGenBase, BlockPos pos) {
        if (this.currentWorld != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            this.currentWorld = world;
            this.randomGenerator = random;
            this.field_180294_c = pos;
            this.genDecorations(biomeGenBase);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }

    @Override
    protected void genDecorations(BiomeGenBase biomeGenBase) {
        MinecraftForge.EVENT_BUS.post(new Pre(this.currentWorld, this.randomGenerator, this.field_180294_c));
        this.generateOres();
        boolean doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, field_180294_c, EventType.SAND_PASS2);

        int i;
        int j;
        int k;
        for (i = 0; doGen && i < this.sandPerChunk; ++i) {
            j = this.randomGenerator.nextInt(16) + 8;
            k = this.randomGenerator.nextInt(16) + 8;
            this.sandGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(j, 0, k)));
        }

        i = this.treesPerChunk;
        if (this.randomGenerator.nextInt(10) == 0) {
            ++i;
        }

        doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, field_180294_c, EventType.GRASS);

        for (j = 0; doGen && j < this.grassPerChunk; ++j) {
            int j7 = this.randomGenerator.nextInt(16) + 8;
            int i11 = this.randomGenerator.nextInt(16) + 8;
            int k14 = this.currentWorld.getHeight(this.field_180294_c.add(j7, 0, i11)).getY() * 2;

            if (k14 > 0) {
                int l17 = this.randomGenerator.nextInt(k14);
                biomeGenBase.getRandomWorldGenForGrass(this.randomGenerator).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(j7, l17, i11));
            }
        }

        if (this.randomGenerator.nextFloat() < this.shrubChance) {
            int k7 = this.randomGenerator.nextInt(16) + 8;
            int j11 = this.randomGenerator.nextInt(16) + 8;
            int l14 = this.currentWorld.getHeight(this.field_180294_c.add(k7, 0, j11)).getY() * 2;

            if (l14 > 0) {
                int i18 = this.randomGenerator.nextInt(l14);
                (new WorldGenShrub(AtumBlocks.SHRUB, 8)).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(k7, i18, j11));
            }
        }

        if (this.randomGenerator.nextFloat() < this.shrubChance) {
            int k7 = this.randomGenerator.nextInt(16) + 8;
            int j11 = this.randomGenerator.nextInt(16) + 8;
            int l14 = this.currentWorld.getHeight(this.field_180294_c.add(k7, 0, j11)).getY() * 2;

            if (l14 > 0) {
                int i18 = this.randomGenerator.nextInt(l14);
                (new WorldGenShrub(AtumBlocks.WEED, 8)).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(k7, i18, j11));
            }
        }

        MinecraftForge.EVENT_BUS.post(new Post(this.currentWorld, this.randomGenerator, field_180294_c));
    }

    @Override
    protected void generateOres() {
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(this.currentWorld, this.randomGenerator, field_180294_c));
        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.coalGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.COAL)) {
            this.genStandardOre1(20, this.coalGen, 0, 128);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.ironGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.IRON)) {
            this.genStandardOre1(20, this.ironGen, 0, 62);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.goldGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.GOLD)) {
            this.genStandardOre1(2, this.goldGen, 0, 32);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.redstoneGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
            this.genStandardOre1(8, this.redstoneGen, 0, 16);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.diamondGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
            this.genStandardOre1(1, this.diamondGen, 0, 16);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.lapisGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.LAPIS)) {
            this.genStandardOre2(1, this.lapisGen, 16, 16);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.dirtGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.DIRT)) {
            this.genStandardOre1(20, this.dirtGen, 0, 256);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.gravelGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.GRAVEL)) {
            this.genStandardOre1(10, this.gravelGen, 0, 256);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.clayGen, field_180294_c, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            this.genStandardOre1(8, this.clayGen, 0, 62);
        }
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(this.currentWorld, this.randomGenerator, field_180294_c));
    }
}