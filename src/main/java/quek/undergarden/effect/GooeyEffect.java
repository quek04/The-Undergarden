package quek.undergarden.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.entity.animal.Scintling;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEffects;

public class GooeyEffect extends MobEffect {

	public GooeyEffect() {
		super(MobEffectCategory.HARMFUL, 7827026);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		BlockState blockstate = UGBlocks.GOO.get().defaultBlockState();
		BlockPos pos = BlockPos.containing(entity.getX(), entity.getY(), entity.getZ());

		if (entity.level.isEmptyBlock(pos) && blockstate.canSurvive(entity.level, pos) && !(entity instanceof Scintling)) {
			entity.level.setBlockAndUpdate(pos, blockstate);
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return this == UGEffects.GOOEY.get();
	}
}