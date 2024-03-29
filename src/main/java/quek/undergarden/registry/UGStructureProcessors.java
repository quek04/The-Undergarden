package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.structure.processor.NoWaterloggingProcessor;

public class UGStructureProcessors {

	public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, Undergarden.MODID);

	public static final DeferredHolder<StructureProcessorType<?>, StructureProcessorType<NoWaterloggingProcessor>> NO_WATERLOGGING = PROCESSORS.register("no_waterlogging", () -> () -> NoWaterloggingProcessor.CODEC);
}
