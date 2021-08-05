package quek.undergarden.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.client.particle.GrongleSporeParticle;
import quek.undergarden.client.particle.ShardParticle;
import quek.undergarden.client.particle.UndergardenPortalParticle;

@Mod.EventBusSubscriber(modid = Undergarden.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Undergarden.MODID);

    public static final RegistryObject<BasicParticleType> SHARD = PARTICLES.register("shard", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> GRONGLE_SPORE = PARTICLES.register("grongle_spore", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> UNDERGARDEN_PORTAL = PARTICLES.register("undergarden_portal", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> GLOOMPER_FART = PARTICLES.register("gloomper_fart", () -> new BasicParticleType(false));

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager particleManager = Minecraft.getInstance().particleEngine;

        particleManager.register(SHARD.get(), ShardParticle.Factory::new);
        particleManager.register(GRONGLE_SPORE.get(), GrongleSporeParticle.Factory::new);
        particleManager.register(UNDERGARDEN_PORTAL.get(), UndergardenPortalParticle.Factory::new);
        particleManager.register(GLOOMPER_FART.get(), SmokeParticle.Factory::new);
    }
}