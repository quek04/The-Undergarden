package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.structure.BiggerJigsawStructure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UGStructures {

	public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Undergarden.MODID);

	public static final RegistryObject<StructureType<BiggerJigsawStructure>> BIGGER_JIGSAW = STRUCTURES.register("bigger_jigsaw", () -> () -> BiggerJigsawStructure.CODEC);

	public static final ResourceKey<Structure> CATACOMBS = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(Undergarden.MODID, "catacombs"));
	public static final ResourceKey<StructureSet> CATACOMBS_SET = ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(Undergarden.MODID, "catacombs"));

	public static final ResourceKey<StructureTemplatePool> CATACOMBS_START = ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(Undergarden.MODID, "catacombs/catacombs_entrance"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_CHEST = ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(Undergarden.MODID, "catacombs/chest_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_INTERIOR = ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(Undergarden.MODID, "catacombs/interior_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_TUNNEL = ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(Undergarden.MODID, "catacombs/tunnel_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_WAY = ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(Undergarden.MODID, "catacombs/way_pool"));

	public static final ResourceKey<StructureProcessorList> CATACOMBS_DEGRADATION = ResourceKey.create(Registries.PROCESSOR_LIST, new ResourceLocation(Undergarden.MODID, "catacombs_degradation"));


	public static void bootstrapStructures(BootstapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(CATACOMBS, new BiggerJigsawStructure(new Structure.StructureSettings(biomes.getOrThrow(UGTags.Biomes.HAS_CATACOMBS), Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN), pools.getOrThrow(CATACOMBS_START), Optional.empty(), 25, ConstantHeight.of(VerticalAnchor.aboveBottom(48)), Optional.empty(), 116));
	}

	public static void bootstrapSets(BootstapContext<StructureSet> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
		context.register(CATACOMBS_SET, new StructureSet(structures.getOrThrow(CATACOMBS), new RandomSpreadStructurePlacement(24, 12, RandomSpreadType.LINEAR, 276320045)));
	}

	public static void bootstrapPools(BootstapContext<StructureTemplatePool> context) {
		Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
		HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

		context.register(CATACOMBS_START, new StructureTemplatePool(emptyPool, ImmutableList.of(Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/entrance").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1)), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_CHEST, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single("minecraft:empty"), 2),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/chest").toString()), 2),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/chest_nargoyle").toString()), 1),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/chest_rotling").toString()), 1),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/chest_rotwalker").toString()), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_INTERIOR, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/interior1").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/interior2").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/interior3").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/interior4").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_TUNNEL, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/way_pool").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/tunnel1").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/tunnel2").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/tunnel3").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/tunnel4").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/tunnel5").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/tunnel6").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/tunnel7").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/room1").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/room2").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/room3").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/tunnel_guardian").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 25)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_WAY, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/entrance").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/4way").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 25),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/3way").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/2way").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 75),
				Pair.of(StructurePoolElement.single(new ResourceLocation(Undergarden.MODID, "catacombs/1way").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100)
		), StructureTemplatePool.Projection.RIGID));
	}

	public static void bootstrapProcessors(BootstapContext<StructureProcessorList> context) {
		context.register(CATACOMBS_DEGRADATION, new StructureProcessorList(List.of(
				new RuleProcessor(List.of(
						new ProcessorRule(
								new RandomBlockMatchTest(UGBlocks.DEPTHROCK_BRICKS.get(), 0.5F),
								AlwaysTrueTest.INSTANCE,
								UGBlocks.CRACKED_DEPTHROCK_BRICKS.get().defaultBlockState()
						)
				))
		)));
	}
}