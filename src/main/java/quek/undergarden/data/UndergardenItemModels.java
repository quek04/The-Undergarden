package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import quek.undergarden.UndergardenMod;
import quek.undergarden.data.provider.UndergardenItemModelProvider;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItems;

public class UndergardenItemModels extends UndergardenItemModelProvider {

    public UndergardenItemModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Item Models";
    }

    @Override
    protected void registerModels() {
        itemBlock(UndergardenBlocks.depthrock);
        itemBlock(UndergardenBlocks.coal_ore);
        itemBlock(UndergardenBlocks.cloggrum_ore);
        itemBlock(UndergardenBlocks.froststeel_ore);
        itemBlock(UndergardenBlocks.utherium_ore);
        itemBlock(UndergardenBlocks.deepturf_block);
        itemBlock(UndergardenBlocks.deepsoil_farmland);
        itemBlock(UndergardenBlocks.deepsoil);
        itemBlockFlat(UndergardenBlocks.smogstem_sapling);
        itemBlock(UndergardenBlocks.smogstem_log);
        itemBlock(UndergardenBlocks.smogstem_leaves);
        itemBlockFlat(UndergardenBlocks.wigglewood_sapling);
        itemBlock(UndergardenBlocks.wigglewood_log);
        itemBlock(UndergardenBlocks.wigglewood_leaves);
        itemBlock(UndergardenBlocks.smogstem_planks);
        itemBlock(UndergardenBlocks.wigglewood_planks);
        itemBlock(UndergardenBlocks.depthrock_bricks);
        itemBlock(UndergardenBlocks.cracked_depthrock_bricks);
        itemBlockFlat(UndergardenBlocks.tall_deepturf);
        itemBlockFlat(UndergardenBlocks.ditchbulb_plant);
        itemBlockFlat(UndergardenBlocks.indigo_mushroom);
        itemBlockFlat(UndergardenBlocks.veil_mushroom);
        itemBlockFlat(UndergardenBlocks.ink_mushroom);
        itemBlockFlat(UndergardenBlocks.blood_mushroom);
        itemBlock(UndergardenBlocks.gloomgourd);
        itemBlock(UndergardenBlocks.carved_gloomgourd);
        itemBlock(UndergardenBlocks.depthrock_pebbles);
        itemBlock(UndergardenBlocks.gloom_o_lantern);
        itemBlock(UndergardenBlocks.cloggrum_block);
        itemBlock(UndergardenBlocks.froststeel_block);
        itemBlock(UndergardenBlocks.utherium_block);
        itemBlock(UndergardenBlocks.depthrock_stairs);
        itemBlock(UndergardenBlocks.depthrock_brick_stairs);
        itemBlock(UndergardenBlocks.smogstem_stairs);
        itemBlock(UndergardenBlocks.wigglewood_stairs);
        itemBlock(UndergardenBlocks.depthrock_slab);
        itemBlock(UndergardenBlocks.depthrock_brick_slab);
        itemBlock(UndergardenBlocks.smogstem_slab);
        itemBlock(UndergardenBlocks.wigglewood_slab);
        itemFence(UndergardenBlocks.smogstem_fence, "smogstem_planks");
        itemFence(UndergardenBlocks.wigglewood_fence, "wigglewood_planks");
        itemBlockFlat(UndergardenBlocks.cloggrum_bars);
        itemBlockFlat(UndergardenBlocks.glowing_sea_grass);
        itemBlock(UndergardenBlocks.shiverstone);
        itemBlock(UndergardenBlocks.goo);
        itemBlock(UndergardenBlocks.smog_vent);
        itemBlockFlat(UndergardenBlocks.ashen_tall_deepturf);
        itemBlock(UndergardenBlocks.ashen_deepturf);
        itemBlock(UndergardenBlocks.regalium_ore);
        itemBlock(UndergardenBlocks.shiverstone_bricks);
        itemBlock(UndergardenBlocks.shiverstone_stairs);
        itemBlock(UndergardenBlocks.shiverstone_brick_stairs);
        itemBlock(UndergardenBlocks.shiverstone_slab);
        itemBlock(UndergardenBlocks.shiverstone_brick_slab);
        itemBlock(UndergardenBlocks.regalium_block);
        itemBlock(UndergardenBlocks.tremblecrust);
        itemBlock(UndergardenBlocks.tremblecrust_bricks);
        itemBlock(UndergardenBlocks.otherside_utherium_ore);
        itemBlock(UndergardenBlocks.smogstem_wood);
        itemBlock(UndergardenBlocks.wigglewood_wood);
        itemBlock(UndergardenBlocks.loose_tremblecrust);
        itemBlock(UndergardenBlocks.iron_ore);
        itemBlock(UndergardenBlocks.gold_ore);
        itemBlock(UndergardenBlocks.diamond_ore);
        itemBlock(UndergardenBlocks.smogstem_fence_gate);
        itemBlock(UndergardenBlocks.wigglewood_fence_gate);
        wallInventory("depthrock_brick_wall", new ResourceLocation(UndergardenMod.MODID, "block/depthrock_bricks"));
        wallInventory("shiverstone_brick_wall", new ResourceLocation(UndergardenMod.MODID, "block/shiverstone_bricks"));
        itemBlock(UndergardenBlocks.coarse_deepsoil);
        itemBlock(UndergardenBlocks.smogstem_pressure_plate);
        itemBlock(UndergardenBlocks.wigglewood_pressure_plate);
        itemBlock(UndergardenBlocks.depthrock_pressure_plate);
        itemBlock(UndergardenBlocks.shiverstone_pressure_plate);
        itemBlockFlat(UndergardenBlocks.gronglet);
        itemBlock(UndergardenBlocks.grongle_stem);
        itemBlock(UndergardenBlocks.grongle_cap);
        itemBlock(UndergardenBlocks.grongle_hyphae);
        itemBlock(UndergardenBlocks.grongle_slab);
        itemFence(UndergardenBlocks.grongle_fence, "grongle_planks");
        itemBlock(UndergardenBlocks.grongle_planks);
        itemBlock(UndergardenBlocks.grongle_fence_gate);
        itemBlock(UndergardenBlocks.grongle_stairs);
        itemBlock(UndergardenBlocks.grongle_pressure_plate);
        itemBlock(UndergardenBlocks.stripped_smogstem_log);
        itemBlock(UndergardenBlocks.stripped_wigglewood_log);
        itemBlock(UndergardenBlocks.stripped_grongle_stem);

        normalItem(UndergardenItems.catalyst_item);
        normalItem(UndergardenItems.depthrock_pebble);
        toolItem(UndergardenItems.smogstem_stick);
        normalItem(UndergardenItems.twistytwig);
        normalItem(UndergardenItems.cloggrum_ingot);
        normalItem(UndergardenItems.cloggrum_nugget);
        normalItem(UndergardenItems.froststeel_ingot);
        normalItem(UndergardenItems.froststeel_nugget);
        normalItem(UndergardenItems.utheric_shard);
        normalItem(UndergardenItems.utherium_ingot);
        normalItem(UndergardenItems.utherium_chunk);
        normalItem(UndergardenItems.regalium_ingot);
        normalItem(UndergardenItems.regalium_nugget);
        torchItem(UndergardenItems.smogstem_torch);
        torchItem(UndergardenItems.shard_torch);
        normalItem(UndergardenItems.ditchbulb);
        normalItem(UndergardenItems.gloomgourd_seeds);
        normalItem(UndergardenItems.brute_tusk);
        normalItem(UndergardenItems.glowing_kelp);
        normalItem(UndergardenItems.goo_ball);
        normalItem(UndergardenItems.rotten_blisterberry);
        normalItem(UndergardenItems.blisterbomb);
        normalItem(UndergardenItems.droopvine_item);

        toolItem(UndergardenItems.smogstem_sword);
        toolItem(UndergardenItems.smogstem_pickaxe);
        toolItem(UndergardenItems.smogstem_axe);
        toolItem(UndergardenItems.smogstem_shovel);
        toolItem(UndergardenItems.smogstem_hoe);

        toolItem(UndergardenItems.cloggrum_sword);
        toolItem(UndergardenItems.cloggrum_pickaxe);
        toolItem(UndergardenItems.cloggrum_axe);
        toolItem(UndergardenItems.cloggrum_shovel);
        toolItem(UndergardenItems.cloggrum_hoe);

        normalItem(UndergardenItems.masticator_scales);
        normalItem(UndergardenItems.masticated_chestplate);

        normalItem(UndergardenItems.cloggrum_helmet);
        normalItem(UndergardenItems.cloggrum_chestplate);
        normalItem(UndergardenItems.cloggrum_leggings);
        normalItem(UndergardenItems.cloggrum_boots);

        normalItem(UndergardenItems.froststeel_helmet);
        normalItem(UndergardenItems.froststeel_chestplate);
        normalItem(UndergardenItems.froststeel_leggings);
        normalItem(UndergardenItems.froststeel_boots);

        normalItem(UndergardenItems.utheric_helmet);
        normalItem(UndergardenItems.utheric_chestplate);
        normalItem(UndergardenItems.utheric_leggings);
        normalItem(UndergardenItems.utheric_boots);

        toolItem(UndergardenItems.froststeel_sword);
        toolItem(UndergardenItems.froststeel_pickaxe);
        toolItem(UndergardenItems.froststeel_axe);
        toolItem(UndergardenItems.froststeel_shovel);
        toolItem(UndergardenItems.froststeel_hoe);

        toolItem(UndergardenItems.utheric_sword);
        toolItem(UndergardenItems.utheric_pickaxe);
        toolItem(UndergardenItems.utheric_axe);
        toolItem(UndergardenItems.utheric_shovel);
        toolItem(UndergardenItems.utheric_hoe);

        normalItem(UndergardenItems.virulent_mix_bucket);

        normalItem(UndergardenItems.gwibling_bucket);

        normalItem(UndergardenItems.underbeans);
        normalItem(UndergardenItems.blisterberry);
        normalItem(UndergardenItems.gloomgourd_pie);
        normalItem(UndergardenItems.raw_dweller_meat);
        normalItem(UndergardenItems.dweller_steak);
        normalItem(UndergardenItems.raw_gwibling);
        normalItem(UndergardenItems.cooked_gwibling);

        egg(UndergardenItems.dweller_spawn_egg);
        egg(UndergardenItems.gwibling_spawn_egg);
        egg(UndergardenItems.rotdweller_spawn_egg);
        egg(UndergardenItems.rotling_spawn_egg);
        egg(UndergardenItems.rotwalker_spawn_egg);
        egg(UndergardenItems.rotbeast_spawn_egg);
        egg(UndergardenItems.brute_spawn_egg);
        egg(UndergardenItems.scintling_spawn_egg);
        egg(UndergardenItems.blisterbomber_spawn_egg);
        egg(UndergardenItems.gloomper_spawn_egg);
        egg(UndergardenItems.stoneborn_spawn_egg);

        egg(UndergardenItems.masticator_spawn_egg);
    }


}
