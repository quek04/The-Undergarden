package quek.undergarden.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class RottenBlisterberryEntity extends ProjectileItemEntity {

    public RottenBlisterberryEntity(EntityType<? extends RottenBlisterberryEntity> type, World world) {
        super(type, world);
    }

    public RottenBlisterberryEntity(World world, LivingEntity thrower) {
        super(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), thrower, world);
    }

    public RottenBlisterberryEntity(World worldIn, double x, double y, double z) {
        super(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.ROTTEN_BLISTERBERRY.get();
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (!this.level.isClientSide) {
            if (result.getType() == RayTraceResult.Type.ENTITY || result.getType() == RayTraceResult.Type.BLOCK) {
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.5F, Explosion.Mode.NONE);
                this.remove();
            }
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
