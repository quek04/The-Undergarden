package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class RottenBlisterberry extends SlingshotProjectile {

	public RottenBlisterberry(EntityType<? extends RottenBlisterberry> type, Level level) {
		super(type, level);
		this.setDropItem(false);
	}

	public RottenBlisterberry(Level level, LivingEntity shooter) {
		super(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), shooter, level);
	}

	public RottenBlisterberry(Level level, double x, double y, double z) {
		super(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), x, y, z, level);
	}

	@Override
	protected Item getDefaultItem() {
		return UGItems.ROTTEN_BLISTERBERRY.get();
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		//Entity victim = result.getEntity();
		//victim.hurt(new IndirectEntityDamageSource("arrow", this, this.getOwner()), 0.0F);
		if (!this.level.isClientSide) {
			this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.5F, Level.ExplosionInteraction.NONE);
			this.discard();
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!this.level.isClientSide && this.ricochetTimes == 0) {
			this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.5F, Level.ExplosionInteraction.NONE);
			this.discard();
		}
	}
}
