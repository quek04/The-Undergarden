package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.IForgeShearable;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mog extends Animal implements IForgeShearable {

	private static final EntityDataAccessor<Boolean> HAS_MOSS = SynchedEntityData.defineId(Mog.class, EntityDataSerializers.BOOLEAN);
	private int timeWithoutMoss;

	public Mog(EntityType<? extends Animal> type, Level level) {
		super(type, level);
		this.maxUpStep = 1.0F;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, Ingredient.of(UGItems.DEPTHROCK_PEBBLE.get()), false));
		this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.9D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.MOG_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return UGSoundEvents.MOG_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.MOG_DEATH.get();
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return UGEntityTypes.MOG.get().create(level);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return Ingredient.of(UGItems.DEPTHROCK_PEBBLE.get()).test(stack);
	}

	@Override
	public void tick() {
		super.tick();
		if (!hasMoss()) {
			timeWithoutMoss++;
		} else {
			timeWithoutMoss = 0;
		}

		if (timeWithoutMoss == 6000) {
			setMoss(true);
		}
	}

	public boolean hasMoss() {
		return this.entityData.get(HAS_MOSS);
	}

	public void setMoss(boolean hasMoss) {
		this.entityData.set(HAS_MOSS, hasMoss);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 0.2F;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("HasMoss", this.hasMoss());
		this.timeWithoutMoss = nbt.getInt("TimeWithoutMoss");
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		this.setMoss(nbt.getBoolean("HasMoss"));
		nbt.putInt("TimeWithoutMoss", this.timeWithoutMoss);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_MOSS, true);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag nbt) {
		this.setMoss(true);
		return super.finalizeSpawn(level, difficulty, spawnType, data, nbt);
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, Level world, BlockPos pos) {
		return this.hasMoss() && this.isAlive() && !this.isBaby();
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack stack, Level level, BlockPos pos, int fortune) {
		level.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		if (!level.isClientSide) {
			this.setMoss(false);
			int mossAmount = 1 + this.random.nextInt(2);

			List<ItemStack> items = new ArrayList<>();
			for (int i = 0; i < mossAmount; i++) {
				items.add(new ItemStack(UGItems.MOGMOSS.get()));
			}
			return items;
		}
		return Collections.emptyList();
	}
}