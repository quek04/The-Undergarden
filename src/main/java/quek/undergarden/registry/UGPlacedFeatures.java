package quek.undergarden.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import quek.undergarden.Undergarden;

import java.util.List;

@SuppressWarnings({"unused"})
public class UGPlacedFeatures {

	//ores
	public static final ResourceKey<PlacedFeature> COAL_ORE = create("coal_ore");
	public static final ResourceKey<PlacedFeature> IRON_ORE = create("iron_ore");
	public static final ResourceKey<PlacedFeature> GOLD_ORE = create("gold_ore");
	public static final ResourceKey<PlacedFeature> DIAMOND_ORE = create("diamond_ore");
	public static final ResourceKey<PlacedFeature> CLOGGRUM_ORE = create("cloggrum_ore");
	public static final ResourceKey<PlacedFeature> FROSTSTEEL_ORE = create("froststeel_ore");
	public static final ResourceKey<PlacedFeature> UTHERIUM_ORE = create("utherium_ore");
	public static final ResourceKey<PlacedFeature> REGALIUM_ORE = create("regalium_ore");
	public static final ResourceKey<PlacedFeature> SHIVERSTONE_ORE = create("shiverstone_ore");
	public static final ResourceKey<PlacedFeature> DEEPSOIL_ORE = create("deepsoil_ore");
	public static final ResourceKey<PlacedFeature> ICE_ORE = create("ice_ore");
	public static final ResourceKey<PlacedFeature> SEDIMENT_ORE = create("sediment_ore");

	//deltas
	public static final ResourceKey<PlacedFeature> BOG_DELTA = create("bog_delta");
	public static final ResourceKey<PlacedFeature> GRONGLEGROWTH_DELTA = create("gronglegrowth_delta");

	//vegetation
	public static final ResourceKey<PlacedFeature> AMOROUS_BRISTLE_PATCH = create("amorous_bristle_patch");
	public static final ResourceKey<PlacedFeature> MISERABELL_PATCH = create("miserabell_patch");
	public static final ResourceKey<PlacedFeature> BUTTERBUNCH_PATCH = create("butterbunch_patch");
	public static final ResourceKey<PlacedFeature> DEEPTURF_PATCH = create("deepturf_patch");
	public static final ResourceKey<PlacedFeature> ASHEN_DEEPTURF_PATCH = create("ashen_deepturf_patch");
	public static final ResourceKey<PlacedFeature> FROZEN_DEEPTURF_PATCH = create("frozen_deepturf_patch");
	public static final ResourceKey<PlacedFeature> SHIMMERWEED_PATCH = create("shimmerweed_patch");
	public static final ResourceKey<PlacedFeature> DEPTHROCK_PEBBLE_PATCH = create("depthrock_pebble_patch");
	public static final ResourceKey<PlacedFeature> DITCHBULB_PATCH = create("ditchbulb_patch");
	public static final ResourceKey<PlacedFeature> TALL_DEEPTURF_PATCH = create("tall_deepturf_patch");
	public static final ResourceKey<PlacedFeature> TALL_SHIMMERWEED_PATCH = create("tall_shimmerweed_patch");
	public static final ResourceKey<PlacedFeature> INDIGO_MUSHROOM_PATCH = create("indigo_mushroom_patch");
	public static final ResourceKey<PlacedFeature> VEIL_MUSHROOM_PATCH = create("veil_mushroom_patch");
	public static final ResourceKey<PlacedFeature> INK_MUSHROOM_PATCH = create("ink_mushroom_patch");
	public static final ResourceKey<PlacedFeature> BLOOD_MUSHROOM_PATCH = create("blood_mushroom_patch");
	public static final ResourceKey<PlacedFeature> UNDERBEAN_BUSH_PATCH = create("underbean_bush_patch");
	public static final ResourceKey<PlacedFeature> BLISTERBERRY_BUSH_PATCH = create("blisterberry_bush_patch");
	public static final ResourceKey<PlacedFeature> GLOOMGOURD_PATCH = create("gloomgourd_patch");
	public static final ResourceKey<PlacedFeature> DROOPVINE_PATCH = create("droopvine_patch");
	public static final ResourceKey<PlacedFeature> GLITTERKELP_PATCH = create("glitterkelp_patch");

	//tree
	public static final ResourceKey<PlacedFeature> SMOGSTEM_TREE = create("smogstem_tree");
	public static final ResourceKey<PlacedFeature> WIDE_SMOGSTEM_TREE = create("wide_smogstem_tree");
	public static final ResourceKey<PlacedFeature> TALL_SMOGSTEM_TREE = create("tall_smogstem_tree");
	public static final ResourceKey<PlacedFeature> SMOGSTEM_BUSH = create("smogstem_bush");
	public static final ResourceKey<PlacedFeature> WIGGLEWOOD_TREE = create("wigglewood_tree");
	public static final ResourceKey<PlacedFeature> TALL_WIGGLEWOOD_TREE = create("tall_wigglewood_tree");
	public static final ResourceKey<PlacedFeature> GRONGLE_TREE = create("grongle_tree");
	public static final ResourceKey<PlacedFeature> SMALL_GRONGLE_TREE = create("small_grongle_tree");
	public static final ResourceKey<PlacedFeature> GRONGLE_BUSH = create("grongle_bush");

	//huge mushrooms
	public static final ResourceKey<PlacedFeature> HUGE_INDIGO_MUSHROOM = create("huge_indigo_mushroom");
	public static final ResourceKey<PlacedFeature> HUGE_INDIGO_MUSHROOM_SMOGSTEM_FOREST = create("huge_indigo_mushroom_smogstem_forest");
	public static final ResourceKey<PlacedFeature> HUGE_VEIL_MUSHROOM = create("huge_veil_mushroom");
	public static final ResourceKey<PlacedFeature> HUGE_INK_MUSHROOM = create("huge_ink_mushroom");
	public static final ResourceKey<PlacedFeature> HUGE_BLOOD_MUSHROOM = create("huge_blood_mushroom");

	//rocks
	public static final ResourceKey<PlacedFeature> DEPTHROCK_ROCK = create("depthrock_rock");
	public static final ResourceKey<PlacedFeature> SHIVERSTONE_ROCK = create("shiverstone_rock");

	//misc
	public static final ResourceKey<PlacedFeature> SMOG_VENT = create("smog_vent");
	public static final ResourceKey<PlacedFeature> ICE_PILLAR = create("ice_pillar");

	public static ResourceKey<PlacedFeature> create(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Undergarden.MODID, name));
	}

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
		//ores
		context.register(COAL_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.COAL_ORE), OrePlacements.commonOrePlacement(30, PlacementUtils.FULL_RANGE)));
		context.register(IRON_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.IRON_ORE), OrePlacements.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.belowTop(64), VerticalAnchor.belowTop(-64)))));
		context.register(GOLD_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.GOLD_ORE), OrePlacements.commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.belowTop(32), VerticalAnchor.belowTop(-32)))));
		context.register(DIAMOND_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.DIAMOND_ORE), OrePlacements.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.belowTop(16), VerticalAnchor.belowTop(-16)))));
		context.register(CLOGGRUM_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.CLOGGRUM_ORE), OrePlacements.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-128), VerticalAnchor.aboveBottom(128)))));
		context.register(FROSTSTEEL_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.FROSTSTEEL_ORE), OrePlacements.commonOrePlacement(15, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-128), VerticalAnchor.aboveBottom(128)))));
		context.register(UTHERIUM_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.UTHERIUM_ORE), OrePlacements.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)))));
		context.register(REGALIUM_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.REGALIUM_ORE), OrePlacements.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(12)))));
		context.register(SHIVERSTONE_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.SHIVERSTONE_ORE), OrePlacements.commonOrePlacement(10, PlacementUtils.FULL_RANGE)));
		context.register(DEEPSOIL_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.DEEPSOIL_ORE), OrePlacements.commonOrePlacement(10, PlacementUtils.FULL_RANGE)));
		context.register(ICE_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.ICE_ORE), OrePlacements.commonOrePlacement(20, PlacementUtils.FULL_RANGE)));
		context.register(SEDIMENT_ORE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.SEDIMENT_ORE), OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)))));

		//deltas
		context.register(BOG_DELTA, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.BOG_DELTA), List.of(CountOnEveryLayerPlacement.of(40), BiomeFilter.biome())));
		context.register(GRONGLEGROWTH_DELTA, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.GRONGLEGROWTH_DELTA), List.of(CountOnEveryLayerPlacement.of(40), BiomeFilter.biome())));

		//vegetation
		context.register(AMOROUS_BRISTLE_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.AMOROUS_BRISTLE_PATCH), patch(5)));
		context.register(MISERABELL_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.MISERABELL_PATCH), patch(5)));
		context.register(BUTTERBUNCH_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.BUTTERBUNCH_PATCH), patch(5)));
		context.register(DEEPTURF_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.DEEPTURF_PATCH), patch(100)));
		context.register(ASHEN_DEEPTURF_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.ASHEN_DEEPTURF_PATCH), patch(100)));
		context.register(FROZEN_DEEPTURF_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.FROZEN_DEEPTURF_PATCH), patch(100)));
		context.register(SHIMMERWEED_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.SHIMMERWEED_PATCH), noise(400, 75.0D, 0.0D)));
		context.register(DEPTHROCK_PEBBLE_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.DEPTHROCK_PEBBLE_PATCH), noise(400, 50.0D, 0.D)));
		context.register(DITCHBULB_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.DITCHBULB_PATCH), patch(75)));
		context.register(TALL_DEEPTURF_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.TALL_DEEPTURF_PATCH), patch(100)));
		context.register(TALL_SHIMMERWEED_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.TALL_SHIMMERWEED_PATCH), noise(400, 75.0D, 0.0D)));
		context.register(INDIGO_MUSHROOM_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.INDIGO_MUSHROOM_PATCH), patch(1)));
		context.register(VEIL_MUSHROOM_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.VEIL_MUSHROOM_PATCH), patch(1)));
		context.register(INK_MUSHROOM_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.INK_MUSHROOM_PATCH), patch(1)));
		context.register(BLOOD_MUSHROOM_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.BLOOD_MUSHROOM_PATCH), patch(1)));
		context.register(UNDERBEAN_BUSH_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.UNDERBEAN_BUSH_PATCH), patch(5)));
		context.register(BLISTERBERRY_BUSH_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.BLISTERBERRY_BUSH_PATCH), patch(5)));
		context.register(GLOOMGOURD_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.GLOOMGOURD_PATCH), patch(5)));
		context.register(DROOPVINE_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.DROOPVINE), patch(100)));
		context.register(GLITTERKELP_PATCH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.GLITTERKELP), List.of(NoiseBasedCountPlacement.of(1000, 80.0D, 0.0D), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(31)), BiomeFilter.biome())));

		//tree
		context.register(SMOGSTEM_TREE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.SMOGSTEM_TREE), tree(8)));
		context.register(WIDE_SMOGSTEM_TREE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.WIDE_SMOGSTEM_TREE), tree(2)));
		context.register(TALL_SMOGSTEM_TREE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.TALL_SMOGSTEM_TREE), tree(4)));
		context.register(SMOGSTEM_BUSH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.SMOGSTEM_BUSH), tree(8)));
		context.register(WIGGLEWOOD_TREE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.WIGGLEWOOD_TREE), tree(8)));
		context.register(TALL_WIGGLEWOOD_TREE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.TALL_WIGGLEWOOD_TREE), tree(4)));
		context.register(GRONGLE_TREE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.GRONGLE_TREE), tree(8)));
		context.register(SMALL_GRONGLE_TREE, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.SMALL_GRONGLE_TREE), tree(8)));
		context.register(GRONGLE_BUSH, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.GRONGLE_BUSH), tree(8)));

		//huge mushrooms
		context.register(HUGE_INDIGO_MUSHROOM, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM), tree(8)));
		context.register(HUGE_INDIGO_MUSHROOM_SMOGSTEM_FOREST, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM), tree(1)));
		context.register(HUGE_VEIL_MUSHROOM, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.HUGE_VEIL_MUSHROOM), tree(8)));
		context.register(HUGE_INK_MUSHROOM, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.HUGE_INK_MUSHROOM), tree(8)));
		context.register(HUGE_BLOOD_MUSHROOM, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.HUGE_BLOOD_MUSHROOM), tree(8)));

		//rocks
		context.register(DEPTHROCK_ROCK, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.DEPTHROCK_ROCK), patch(5)));
		context.register(SHIVERSTONE_ROCK, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.SHIVERSTONE_ROCK), patch(5)));

		//misc
		context.register(SMOG_VENT, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.SMOG_VENT), tree(8)));
		context.register(ICE_PILLAR, new PlacedFeature(features.getOrThrow(UGConfiguredFeatures.ICE_PILLAR), patch(50)));

	}

	private static List<PlacementModifier> tree(int count) {
		return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
	}

	private static List<PlacementModifier> patch(int count) {
		return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
	}

	private static List<PlacementModifier> noise(int noiseToCountRatio, double factor, double offset) {
		return List.of(NoiseBasedCountPlacement.of(noiseToCountRatio, factor, offset), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
	}
}