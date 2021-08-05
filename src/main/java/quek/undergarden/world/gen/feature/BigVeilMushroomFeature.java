package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class BigVeilMushroomFeature extends UGBigMushroomFeature {

    public BigVeilMushroomFeature(Codec<BigMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected int getTreeRadiusForHeight(int p_225563_1_, int p_225563_2_, int p_225563_3_, int p_225563_4_) {
        int i = 0;
        if (p_225563_4_ < p_225563_2_ && p_225563_4_ >= p_225563_2_ - 3) {
            i = p_225563_3_;
        } else if (p_225563_4_ == p_225563_2_) {
            i = p_225563_3_;
        }

        return i;
    }

    @Override //cap
    protected void makeCap(IWorld world, Random random, BlockPos pos, int p_225564_4_, BlockPos.Mutable posMutable, BigMushroomFeatureConfig config) {
        for(int i = p_225564_4_ - 3; i <= p_225564_4_; ++i) {
            int j = i < p_225564_4_ ? config.foliageRadius : config.foliageRadius - 1;
            int k = config.foliageRadius - 2;

            for(int l = -j; l <= j; ++l) {
                for(int i1 = -j; i1 <= j; ++i1) {
                    boolean flag = l == -j;
                    boolean flag1 = l == j;
                    boolean flag2 = i1 == -j;
                    boolean flag3 = i1 == j;
                    boolean flag4 = flag || flag1;
                    boolean flag5 = flag2 || flag3;
                    if (i >= p_225564_4_ || flag4 != flag5) {
                        posMutable.setWithOffset(pos, l, i, i1);
                        if (world.getBlockState(posMutable).canBeReplacedByLeaves(world, posMutable)) {
                            this.setBlock(world, posMutable, config.capProvider.getState(random, pos).setValue(HugeMushroomBlock.UP, i >= p_225564_4_ - 1).setValue(HugeMushroomBlock.WEST, l < -k).setValue(HugeMushroomBlock.EAST, l > k).setValue(HugeMushroomBlock.NORTH, i1 < -k).setValue(HugeMushroomBlock.SOUTH, i1 > k));

                            if(random.nextInt(5) == 0) {
                                doVeil(posMutable, world, random);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void doVeil(BlockPos pos, IWorld world, Random random) {
        BlockPos.Mutable blockpos$mutable = pos.mutable().move(Direction.DOWN);
        if (world.isEmptyBlock(blockpos$mutable)) {
            int i = MathHelper.nextInt(random, 1, 5);
            if (random.nextInt(7) == 0) {
                i *= 2;
            }

            placeVeil(world, random, blockpos$mutable, i, 23, 25);
        }
    }

    public static void placeVeil(IWorld world, Random rand, BlockPos.Mutable posMutable, int x, int y, int z) {
        for(int i = 0; i <= x; ++i) {
            if (world.isEmptyBlock(posMutable)) {
                if (i == x || !world.isEmptyBlock(posMutable.below())) {
                    world.setBlock(posMutable, UGBlocks.MUSHROOM_VEIL_TOP.get().defaultBlockState().setValue(AbstractTopPlantBlock.AGE, MathHelper.nextInt(rand, y, z)), 2);
                    break;
                }

                world.setBlock(posMutable, UGBlocks.MUSHROOM_VEIL.get().defaultBlockState(), 2);
            }

            posMutable.move(Direction.DOWN);
        }

    }
}
