package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.structure.processor.NoWaterloggingProcessor;

@SuppressWarnings("unused")
public class UGStructureProcessors {

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, Undergarden.MODID);

    public static final RegistryObject<StructureProcessorType<NoWaterloggingProcessor>> NO_WATERLOGGING = PROCESSORS.register("no_waterlogging", () -> () -> NoWaterloggingProcessor.CODEC);
}
