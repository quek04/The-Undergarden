package quek.undergarden.block.world;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class DroopvineTopBlock extends AbstractTopPlantBlock {

    public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 1.0D, 1.0D, 16.0D, 16.0D, 16.0D);

    public DroopvineTopBlock() {
        super(Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .setLightLevel((state) -> 10)
                .hardnessAndResistance(0.2F)
                .sound(SoundType.WET_GRASS),
                Direction.DOWN,
                SHAPE,
                false,
                0.1D
        );
    }

    @Override
    protected int getGrowthAmount(Random rand) {
        return PlantBlockHelper.getGrowthAmount(rand);
    }

    @Override
    protected boolean canGrowIn(BlockState state) {
        return PlantBlockHelper.isAir(state);
    }

    @Override
    protected Block getBodyPlantBlock() {
        return UGBlocks.droopvine.get().getDefaultState().with(DroopvineBlock.GLOWY, DroopvineBlock.randomTorF()).getBlock();
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }
}
