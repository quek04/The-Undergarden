package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.registry.*;

public class BlisterberryBushBlock extends UGBushBlock implements BonemealableBlock {

	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

	public BlisterberryBushBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(UGItems.BLISTERBERRY.get());
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		super.tick(state, level, pos, random);
		int i = state.getValue(AGE);
		if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(5) == 0)) {
			level.setBlock(pos, state.setValue(AGE, i + 1), 2);
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
		}

	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity.getType() != UGEntityTypes.SCINTLING.get()) {
			entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75D, 0.8F));
			if (!level.isClientSide && state.getValue(AGE) > 0 && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
				double d0 = Math.abs(entity.getX() - entity.xOld);
				double d1 = Math.abs(entity.getZ() - entity.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					entity.hurt(level.damageSources().source(UGDamageSources.BLISTERBERRY_BUSH), 2.0F);
				}
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		int age = state.getValue(AGE);
		boolean flag = age == 3;
		if (!flag && player.getItemInHand(hand).getItem() == Items.BONE_MEAL) {
			return InteractionResult.PASS;
		} else if (age > 1) {
			int random = 1 + level.random.nextInt(2);
			int random2 = level.random.nextInt(2);
			popResource(level, pos, new ItemStack(UGItems.BLISTERBERRY.get(), random + (flag ? 1 : 0)));
			popResource(level, pos, new ItemStack(UGItems.ROTTEN_BLISTERBERRY.get(), random2 + (flag ? 1 : 0)));
			level.playSound(null, pos, UGSoundEvents.PICK_BLISTERBERRY_BUSH.get(), SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(AGE, 1), 2);
			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, level, pos, player, hand, result);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 3;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		int i = Math.min(3, state.getValue(AGE) + 1);
		level.setBlock(pos, state.setValue(AGE, i), 2);
	}
}