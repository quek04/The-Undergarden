package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.foliageplacer.VeilFoliagePlacer;

public class UGFoliagePlacers {

	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Undergarden.MODID);

	public static final RegistryObject<FoliagePlacerType<VeilFoliagePlacer>> VEIL = FOLIAGE_PLACERS.register("veil", () -> new FoliagePlacerType<>(VeilFoliagePlacer.CODEC));
}
