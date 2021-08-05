package quek.undergarden.world.gen.feature;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.BitSetVoxelShapePart;
import net.minecraft.util.math.shapes.VoxelShapePart;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import quek.undergarden.registry.UGBlocks;

import java.util.*;

public class UGTreeFeature extends Feature<BaseTreeFeatureConfig> {

    public UGTreeFeature(Codec<BaseTreeFeatureConfig> codec) {
        super(codec);
    }

    public static boolean isLog(IWorldGenerationBaseReader world, BlockPos pos) {
        return isReplaceableAt(world, pos) || world.isStateAtPosition(pos, (state) -> state.is(BlockTags.LOGS));
    }

    private static boolean isVine(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> state.is(Blocks.VINE));
    }

    private static boolean isWater(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> state.is(Blocks.WATER));
    }

    public static boolean isLeaves(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> state.isAir() || state.is(BlockTags.LEAVES));
    }

    private static boolean isDirtOrFarmland(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> {
            Block block = state.getBlock();
            return isDirt(block) || block == Blocks.FARMLAND;
        });
    }

    private static boolean isTallPlant(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> {
            Material material = state.getMaterial();
            return material == Material.REPLACEABLE_PLANT;
        });
    }

    public static void setBlockKnownShape(IWorldWriter world, BlockPos pos, BlockState state) {
        world.setBlock(pos, state, 19);
    }

    public static boolean isReplaceableAt(IWorldGenerationBaseReader world, BlockPos pos) {
        return isLeaves(world, pos) || isTallPlant(world, pos) || isWater(world, pos);
    }

    private boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> leaves, Set<BlockPos> logs, MutableBoundingBox boundingBoxIn, BaseTreeFeatureConfig configIn) {
        int trunk = configIn.trunkPlacer.getTreeHeight(rand);
        int leaf = configIn.foliagePlacer.foliageHeight(rand, trunk, configIn);
        int k = trunk - leaf;
        int l = configIn.foliagePlacer.foliageRadius(rand, k);
        BlockPos blockpos;

        blockpos = positionIn;

        if (blockpos.getY() >= 1 && blockpos.getY() + trunk + 1 <= 256) {
            if (!isDirtOrFarmland(generationReader, blockpos.below()) || isWater(generationReader, positionIn)) {
                return false;
            }
            else {
                OptionalInt optionalint = configIn.minimumSize.minClippedHeight();
                int l1 = this.getMaxFreeTreeHeight(generationReader, trunk, blockpos, configIn);
                if (l1 >= trunk || optionalint.isPresent() && l1 >= optionalint.getAsInt()) {
                    List<FoliagePlacer.Foliage> list = configIn.trunkPlacer.placeTrunk(generationReader, rand, l1, blockpos, leaves, boundingBoxIn, configIn);
                    list.forEach((foliage) -> configIn.foliagePlacer.createFoliage(generationReader, rand, configIn, l1, foliage, leaf, l, logs, boundingBoxIn));
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }

    private int getMaxFreeTreeHeight(IWorldGenerationBaseReader p_241521_1_, int p_241521_2_, BlockPos p_241521_3_, BaseTreeFeatureConfig p_241521_4_) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int i = 0; i <= p_241521_2_ + 1; ++i) {
            int j = p_241521_4_.minimumSize.getSizeAtHeight(p_241521_2_, i);

            for(int k = -j; k <= j; ++k) {
                for(int l = -j; l <= j; ++l) {
                    blockpos$mutable.setWithOffset(p_241521_3_, k, i, l);
                    if (!isLog(p_241521_1_, blockpos$mutable) || !p_241521_4_.ignoreVines && isVine(p_241521_1_, blockpos$mutable)) {
                        return i - 2;
                    }
                }
            }
        }

        return p_241521_2_;
    }

    protected void setBlock(IWorldWriter world, BlockPos pos, BlockState state) {
        setBlockKnownShape(world, pos, state);
    }

    @Override
    public final boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BaseTreeFeatureConfig config) {
        Set<BlockPos> set = Sets.newHashSet();
        Set<BlockPos> set1 = Sets.newHashSet();
        Set<BlockPos> set2 = Sets.newHashSet();
        MutableBoundingBox mutableboundingbox = MutableBoundingBox.getUnknownBox();
        boolean canPlace = this.place(reader, rand, pos, set, set1, mutableboundingbox, config);
        if (mutableboundingbox.x0 <= mutableboundingbox.x1 && canPlace && !set.isEmpty()) {
            if (!config.decorators.isEmpty()) {
                List<BlockPos> list = Lists.newArrayList(set);
                List<BlockPos> list1 = Lists.newArrayList(set1);
                list.sort(Comparator.comparingInt(Vector3i::getY));
                list1.sort(Comparator.comparingInt(Vector3i::getY));
                config.decorators.forEach((treeDecorator) -> treeDecorator.place(reader, rand, list, list1, set2, mutableboundingbox));
            }

            VoxelShapePart voxelshapepart = this.placeLogsAndLeaves(reader, mutableboundingbox, set, set2);
            Template.updateShapeAtEdge(reader, 3, voxelshapepart, mutableboundingbox.x0, mutableboundingbox.y0, mutableboundingbox.z0);
            return true;
        }
        else {
            return false;
        }
    }

    private VoxelShapePart placeLogsAndLeaves(IWorld world, MutableBoundingBox boundingBox, Set<BlockPos> p_236403_3_, Set<BlockPos> p_236403_4_) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        VoxelShapePart voxelshapepart = new BitSetVoxelShapePart(boundingBox.getXSpan(), boundingBox.getYSpan(), boundingBox.getZSpan());

        for(int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(BlockPos blockpos : Lists.newArrayList(p_236403_4_)) {
            if (boundingBox.isInside(blockpos)) {
                voxelshapepart.setFull(blockpos.getX() - boundingBox.x0, blockpos.getY() - boundingBox.y0, blockpos.getZ() - boundingBox.z0, true, true);
            }
        }

        for(BlockPos blockpos1 : Lists.newArrayList(p_236403_3_)) {
            if (boundingBox.isInside(blockpos1)) {
                voxelshapepart.setFull(blockpos1.getX() - boundingBox.x0, blockpos1.getY() - boundingBox.y0, blockpos1.getZ() - boundingBox.z0, true, true);
            }

            for(Direction direction : Direction.values()) {
                blockpos$mutable.setWithOffset(blockpos1, direction);
                if (!p_236403_3_.contains(blockpos$mutable)) {
                    BlockState blockstate = world.getBlockState(blockpos$mutable);
                    if (blockstate.hasProperty(BlockStateProperties.DISTANCE)) {
                        list.get(0).add(blockpos$mutable.immutable());
                        setBlockKnownShape(world, blockpos$mutable, blockstate.setValue(BlockStateProperties.DISTANCE, 1));
                        if (boundingBox.isInside(blockpos$mutable)) {
                            voxelshapepart.setFull(blockpos$mutable.getX() - boundingBox.x0, blockpos$mutable.getY() - boundingBox.y0, blockpos$mutable.getZ() - boundingBox.z0, true, true);
                        }
                    }
                }
            }
        }

        for(int l = 1; l < 6; ++l) {
            Set<BlockPos> set = list.get(l - 1);
            Set<BlockPos> set1 = list.get(l);

            for(BlockPos blockpos2 : set) {
                if (boundingBox.isInside(blockpos2)) {
                    voxelshapepart.setFull(blockpos2.getX() - boundingBox.x0, blockpos2.getY() - boundingBox.y0, blockpos2.getZ() - boundingBox.z0, true, true);
                }

                for(Direction direction1 : Direction.values()) {
                    blockpos$mutable.setWithOffset(blockpos2, direction1);
                    if (!set.contains(blockpos$mutable) && !set1.contains(blockpos$mutable)) {
                        BlockState blockstate1 = world.getBlockState(blockpos$mutable);
                        if (blockstate1.hasProperty(BlockStateProperties.DISTANCE)) {
                            int k = blockstate1.getValue(BlockStateProperties.DISTANCE);
                            if (k > l + 1) {
                                BlockState blockstate2 = blockstate1.setValue(BlockStateProperties.DISTANCE, l + 1);
                                setBlockKnownShape(world, blockpos$mutable, blockstate2);
                                if (boundingBox.isInside(blockpos$mutable)) {
                                    voxelshapepart.setFull(blockpos$mutable.getX() - boundingBox.x0, blockpos$mutable.getY() - boundingBox.y0, blockpos$mutable.getZ() - boundingBox.z0, true, true);
                                }

                                set1.add(blockpos$mutable.immutable());
                            }
                        }
                    }
                }
            }
        }

        return voxelshapepart;
    }
}