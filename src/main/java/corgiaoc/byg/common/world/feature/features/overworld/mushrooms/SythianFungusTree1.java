package corgiaoc.byg.common.world.feature.features.overworld.mushrooms;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import corgiaoc.byg.common.world.feature.features.config.BYGMushroomFeatureConfig;
import corgiaoc.byg.common.world.feature.features.overworld.mushrooms.util.BYGAbstractMushroomFeature;
import corgiaoc.byg.core.BYGBlocks;

import java.util.Random;

public class SythianFungusTree1 extends BYGAbstractMushroomFeature<BYGMushroomFeatureConfig> {

    public SythianFungusTree1(Codec<BYGMushroomFeatureConfig> configIn) {
        super(configIn);
    }

    protected boolean placeMushroom(ISeedReader worldIn, Random rand, BlockPos pos, boolean isMushroom, BYGMushroomFeatureConfig config) {
        BlockState STEM = config.getStemProvider().getBlockState(rand, pos);
        BlockState MUSHROOM = config.getMushroomProvider().getBlockState(rand, pos);
        BlockState MUSHROOM2 = config.getMushroom2Provider().getBlockState(rand, pos);
        BlockState MUSHROOM3 = config.getMushroom3Provider().getBlockState(rand, pos);
        BlockState POLLEN = config.getPollenProvider().getBlockState(rand, pos);
        int randTreeHeight = 11 + rand.nextInt(5);
        BlockPos.Mutable mainmutable = new BlockPos.Mutable().setPos(pos);

        if (pos.getY() + randTreeHeight + 1 < worldIn.getHeight()) {
            if (!isDesiredGroundwDirtTag(worldIn, pos.down(), BYGBlocks.SYTHIAN_NYLIUM)) {
                return false;
            } else if (!this.isAnotherMushroomLikeThisNearby(worldIn, pos, randTreeHeight, 0, STEM.getBlock(), MUSHROOM.getBlock(), isMushroom)) {
                return false;
            } else if (!this.doesMushroomHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isMushroom)) {
                return false;
            } else {
                placeStem(STEM, worldIn, mainmutable.add(0, 0, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 1, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 2, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 3, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 4, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 5, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 6, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 7, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 8, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 9, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 10, 0));
                placeStem(STEM, worldIn, mainmutable.add(0, 11, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 1, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 1, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 1, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 1, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 1, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 1, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 1, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 1, 1));
                this.shroomLight(worldIn, mainmutable.add(-2, 3, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 3, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 3, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 3, -1));
                this.shroomLight(worldIn, mainmutable.add(-2, 3, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 3, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 3, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 3, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 3, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 3, 1));
                this.shroomLight(worldIn, mainmutable.add(-1, 4, -1));
                this.shroomLight(worldIn, mainmutable.add(-2, 4, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 5, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 5, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 5, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 5, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 5, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 5, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 5, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 5, 1));
                this.shroomLight(worldIn, mainmutable.add(2, 6, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 7, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 7, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 7, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 7, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 7, 0));
                this.shroomLight(worldIn, mainmutable.add(2, 7, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 7, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 7, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 7, 1));
                this.shroomLight(worldIn, mainmutable.add(2, 7, 1));
                this.shroomLight(worldIn, mainmutable.add(1, 7, 2));
                this.shroomLight(worldIn, mainmutable.add(-1, 8, 0));
                this.shroomLight(worldIn, mainmutable.add(1, 8, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 9, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 9, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 9, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 9, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 9, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 9, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 9, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 9, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 11, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 11, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 11, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 11, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 11, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(-1, 11, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 11, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(1, 11, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.add(0, 12, 0));
            }
        }
        return true;
    }

    private void shroomLight(ISeedReader reader, BlockPos pos) {
        if (isAir(reader, pos)) {
            this.setFinalBlockState(reader, pos, Blocks.SHROOMLIGHT.getDefaultState());
        }
    }
}