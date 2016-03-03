package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.WorldGenDeadwood;
import com.teammetallurgy.atum.world.decorators.WorldGenPalm;
import com.teammetallurgy.atum.world.decorators.WorldGenPyramid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class AtumBiomeGenBase extends BiomeGenBase {

	protected int weight = AtumConfig.DEFAULT_BIOME_WEIGHT;
	protected AtumConfig.BiomeConfig config;
	
	protected int deadwoodRarity = 5;
	protected int palmRarity = 5;
	protected int pyramidRarity = 240;
	
	public AtumBiomeGenBase(int biomeID) {
		super(biomeID);
		
        super.spawnableMonsterList.clear();
        super.spawnableCreatureList.clear();
        super.spawnableWaterCreatureList.clear();
        super.spawnableCaveCreatureList.clear();
        
        this.setDisableRain();
        
        this.topBlock = AtumBlocks.SAND.getDefaultState();
        this.fillerBlock = AtumBlocks.LIMESTONE.getDefaultState();

        this.setColor(16421912);
        
        this.setTemperatureRainfall(2.0F, 0.0F);
        this.setHeight(height_Default);	// same as plains
	}
	
	public AtumBiomeGenBase(AtumConfig.BiomeConfig config) {
		this(config.getID());
		this.config = config;
		
        this.setBiomeName(config.toString());
        this.weight = config.getWeight();
        config.setGen(this);
	}
	
	public int getWeight() {
		return weight;
	}
	
	protected void addDefaultSpawns() {
        super.spawnableMonsterList.add(new SpawnListEntry(EntityMummy.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityBanditWarrior.class, 6, 2, 2));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityBarbarian.class, 2, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityBanditArcher.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityDustySkeleton.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityGhost.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityDesertWolf.class, 4, 1, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityStoneSoldier.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityBonestorm.class, 6, 4, 4));
	}

    @Override
    public BiomeDecorator createBiomeDecorator() {
        final BiomeDecorator dec = new BiomeDecoratorAtum();
        // dec.treesPerChunk = 1; // defaults to 0
        dec.deadBushPerChunk = 5;
        dec.reedsPerChunk = 0;
        dec.cactiPerChunk = 0;
        
        return dec;
    }
    
    @Override
    public void decorate(World world, Random rng, int chunkx, int chunkz) {
        super.decorate(world, rng, chunkx, chunkz);
        
        int xx, zz, height;
        
        if (palmRarity > 0 && rng.nextInt(palmRarity) == 0) {
            xx = chunkx + rng.nextInt(16) + 8;
            zz = chunkz + rng.nextInt(16) + 8;
            height = rng.nextInt(4) + 5;
           	(new WorldGenPalm(true, height, 0, 0)).generate(world, rng, xx, world.getHeightValue(xx, zz), zz);
        } else
        if (pyramidRarity > 0 && rng.nextInt(pyramidRarity) == 0) {
            xx = chunkx + rng.nextInt(16) + 8;
            zz = chunkz + rng.nextInt(16) + 8;
            (new WorldGenPyramid()).generate(world, rng, xx, world.getHeightValue(xx, zz), zz);
		} else 
	    if (deadwoodRarity > 0 && rng.nextInt(deadwoodRarity) == 0){
	        xx = chunkx + rng.nextInt(16) + 8;
            zz = chunkz + rng.nextInt(16) + 8;
            height = rng.nextInt(1) + 6;
            (new WorldGenDeadwood(true, height)).generate(world, rng, xx, world.getHeightValue(xx, zz), zz);
	    }

    }
    
    @Override
    public void genTerrainBlocks(World world, Random rng, Block[] blocks, byte[] bytes, int x, int z, double stoneNoise) {
        Block block = this.topBlock;
        final byte b0 = 0; //(byte) (this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        
        int flag = -1;
        int elevation = (int) (stoneNoise / 3.0D + 3.0D + rng.nextDouble() * 0.25D);
        
        final int xx = x & 15;
        final int zz = z & 15;
        final int offset = blocks.length / 256;

        final int bedrock = 0 + rng.nextInt(5);
        
        for (int yy = 255; yy >= 0; --yy) {
            int index = (zz * 16 + xx) * offset + yy;

            if (yy <= bedrock) {
                blocks[index] = Blocks.bedrock;
            } else {
                Block existingBlock = blocks[index];

                if (existingBlock != null && existingBlock.getMaterial() != Material.air) {
                	
                	// !!!!! SOMETHING WRONG HERE !!!!! ---------------------
                    if (existingBlock == AtumBlocks.LIMESTONE) {
                    	
                        if (flag == -1) {
                            if (elevation <= 0) {
                                block = null;
                                //b0 = 0;
                                block1 = AtumBlocks.LIMESTONE;
                            } else if (yy >= 59 && yy <= 64) {
                                block = this.topBlock;
                                //b0 = (byte) (this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            /*
                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air)) {
                                if (this.getFloatTemperature(x, l1, z) < 0.15F) {
                                    block = Blocks.ice;
                                    b0 = 0;
                                } else {
                                    block = Blocks.water;
                                    b0 = 0;
                                }
                            }
                            */

                            flag = elevation;

                            if (yy >= 62) {
                                blocks[index] = block;
                                bytes[index] = b0;
                            } else if (yy < 56 - elevation) {
                                block = null;
                                block1 = Blocks.stone;
                                blocks[index] = Blocks.gravel;
                            } else {
                                blocks[index] = block1;
                            }
                        } else if (flag > 0) {
                            --flag;
                            blocks[index] = block1;

                            if (flag == 0 && block1 == Blocks.sand) {
                                flag = rng.nextInt(4) + Math.max(0, yy - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                } else {
                    flag = -1;
                }
            }
        }
    }
}