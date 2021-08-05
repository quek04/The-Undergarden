package quek.undergarden.registry;

import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.UGBoatEntity;
import quek.undergarden.item.*;
import quek.undergarden.item.armor.*;
import quek.undergarden.item.tool.*;

public class UGItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Undergarden.MODID);

    public static final Rarity FORGOTTEN = Rarity.create("forgotten", TextFormatting.GREEN);

    //discs
    public static final RegistryObject<Item> MAMMOTH_DISC = ITEMS.register("music_disc_mammoth", () -> new MusicDiscItem(0, UGSoundEvents.MAMMOTH_DISC, new Item.Properties().tab(UGItemGroups.GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> LIMAX_MAXIMUS_DISC = ITEMS.register("music_disc_limax_maximus", () -> new MusicDiscItem(1, UGSoundEvents.LIMAX_MAXIMUS_DISC, new Item.Properties().tab(UGItemGroups.GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> RELICT_DISC = ITEMS.register("music_disc_relict", () -> new MusicDiscItem(2, UGSoundEvents.RELICT_DISC, new Item.Properties().tab(UGItemGroups.GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> GLOOMPER_ANTHEM_DISC = ITEMS.register("music_disc_gloomper_anthem", () -> new MusicDiscItem(3, UGSoundEvents.GLOOMPER_ANTHEM_DISC, new Item.Properties().tab(UGItemGroups.GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> GLOOMPER_SECRET_DISC = ITEMS.register("music_disc_gloomper_secret", () -> new MusicDiscItem(15, UGSoundEvents.GLOOMPER_SECRET_DISC, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    //crafting materials
    public static final RegistryObject<Item> CLOGGRUM_INGOT = ITEMS.register("cloggrum_ingot", UGItem::new);
    public static final RegistryObject<Item> CLOGGRUM_NUGGET = ITEMS.register("cloggrum_nugget", UGItem::new);

    public static final RegistryObject<Item> FROSTSTEEL_INGOT = ITEMS.register("froststeel_ingot", UGItem::new);
    public static final RegistryObject<Item> FROSTSTEEL_NUGGET = ITEMS.register("froststeel_nugget", UGItem::new);

    public static final RegistryObject<Item> UTHERIC_SHARD = ITEMS.register("utheric_shard", UGItem::new);
    public static final RegistryObject<Item> UTHERIUM_INGOT = ITEMS.register("utherium_ingot", UGItem::new);
    public static final RegistryObject<Item> UTHERIUM_CHUNK = ITEMS.register("utherium_chunk", UGItem::new);

    public static final RegistryObject<Item> REGALIUM_INGOT = ITEMS.register("regalium_ingot", () -> new UGItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> REGALIUM_NUGGET = ITEMS.register("regalium_nugget", () -> new UGItem(Rarity.UNCOMMON));

    public static final RegistryObject<Item> FORGOTTEN_INGOT = ITEMS.register("forgotten_ingot", () -> new UGItem(FORGOTTEN));
    public static final RegistryObject<Item> FORGOTTEN_NUGGET = ITEMS.register("forgotten_nugget", () -> new UGItem(FORGOTTEN));

    public static final RegistryObject<Item> DEPTHROCK_PEBBLE = ITEMS.register("depthrock_pebble", DepthrockPebbleItem::new);
    public static final RegistryObject<Item> TWISTYTWIG = ITEMS.register("twistytwig", UGItem::new);
    public static final RegistryObject<Item> DITCHBULB = ITEMS.register("ditchbulb", DitchbulbItem::new);
    public static final RegistryObject<Item> BRUTE_TUSK = ITEMS.register("brute_tusk", UGItem::new);
    public static final RegistryObject<Item> MOGMOSS = ITEMS.register("mogmoss", UGItem::new);

    //boss loot
    public static final RegistryObject<Item> MASTICATOR_SCALES = ITEMS.register("masticator_scales", () -> new UGItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> MASTICATED_CHESTPLATE = ITEMS.register("masticated_chestplate", () -> new MasticatedChestplateItem(UGArmors.MASTICATED));

    //tools
    public static final RegistryObject<Item> CLOGGRUM_BATTLEAXE = ITEMS.register("cloggrum_battleaxe", CloggrumBattleaxeItem::new);
    public static final RegistryObject<Item> CLOGGRUM_SWORD = ITEMS.register("cloggrum_sword", () -> new UGSwordItem(UGTools.CLOGGRUM));
    public static final RegistryObject<Item> CLOGGRUM_PICKAXE = ITEMS.register("cloggrum_pickaxe", () -> new UGPickaxeItem(UGTools.CLOGGRUM));
    public static final RegistryObject<Item> CLOGGRUM_AXE = ITEMS.register("cloggrum_axe", () -> new UGAxeItem(UGTools.CLOGGRUM, 3.0F, -3.2F));
    public static final RegistryObject<Item> CLOGGRUM_SHOVEL = ITEMS.register("cloggrum_shovel", () -> new UGShovelItem(UGTools.CLOGGRUM));
    public static final RegistryObject<Item> CLOGGRUM_HOE = ITEMS.register("cloggrum_hoe", () -> new UGHoeItem(UGTools.CLOGGRUM, -2, -2.0F));
    public static final RegistryObject<Item> CLOGGRUM_SHIELD = ITEMS.register("cloggrum_shield", () -> new UGShieldItem(UGShields.CLOGGRUM));

    public static final RegistryObject<Item> FROSTSTEEL_SWORD = ITEMS.register("froststeel_sword", () -> new UGSwordItem(UGTools.FROSTSTEEL));
    public static final RegistryObject<Item> FROSTSTEEL_PICKAXE = ITEMS.register("froststeel_pickaxe", () -> new UGPickaxeItem(UGTools.FROSTSTEEL));
    public static final RegistryObject<Item> FROSTSTEEL_AXE = ITEMS.register("froststeel_axe", () -> new UGAxeItem(UGTools.FROSTSTEEL, 5.0F, -3.1F));
    public static final RegistryObject<Item> FROSTSTEEL_SHOVEL = ITEMS.register("froststeel_shovel", () -> new UGShovelItem(UGTools.FROSTSTEEL));
    public static final RegistryObject<Item> FROSTSTEEL_HOE = ITEMS.register("froststeel_hoe", () -> new UGHoeItem(UGTools.FROSTSTEEL, -2, -1.0F));

    public static final RegistryObject<Item> UTHERIUM_SWORD = ITEMS.register("utheric_sword", () -> new UGSwordItem(UGTools.UTHERIC));
    public static final RegistryObject<Item> UTHERIUM_PICKAXE = ITEMS.register("utheric_pickaxe", () -> new UGPickaxeItem(UGTools.UTHERIC));
    public static final RegistryObject<Item> UTHERIUM_AXE = ITEMS.register("utheric_axe", () -> new UGAxeItem(UGTools.UTHERIC, 4.0F, -3.0F));
    public static final RegistryObject<Item> UTHERIUM_SHOVEL = ITEMS.register("utheric_shovel", () -> new UGShovelItem(UGTools.UTHERIC));
    public static final RegistryObject<Item> UTHERIUM_HOE = ITEMS.register("utheric_hoe", () -> new UGHoeItem(UGTools.UTHERIC, -3, 0.0F));

    public static final RegistryObject<Item> FORGOTTEN_SWORD = ITEMS.register("forgotten_sword", () -> new UGSwordItem(UGTools.FORGOTTEN));
    public static final RegistryObject<Item> FORGOTTEN_PICKAXE = ITEMS.register("forgotten_pickaxe", () -> new UGPickaxeItem(UGTools.FORGOTTEN));
    public static final RegistryObject<Item> FORGOTTEN_AXE = ITEMS.register("forgotten_axe", () -> new UGAxeItem(UGTools.FORGOTTEN, 4.0F, -3.1F));
    public static final RegistryObject<Item> FORGOTTEN_SHOVEL = ITEMS.register("forgotten_shovel", () -> new UGShovelItem(UGTools.FORGOTTEN));
    public static final RegistryObject<Item> FORGOTTEN_HOE = ITEMS.register("forgotten_hoe", () -> new UGHoeItem(UGTools.FORGOTTEN, -3, 0.0F));

    //misc tools
    public static final RegistryObject<Item> CATALYST = ITEMS.register("catalyst", CatalystItem::new);
    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot", SlingshotItem::new);
    public static final RegistryObject<Item> BLISTERBOMB = ITEMS.register("blisterbomb", BlisterbombItem::new);
    public static final RegistryObject<Item> GOO_BALL = ITEMS.register("goo_ball", GooBallItem::new);

    public static final RegistryObject<Item> SMOGSTEM_BOAT = ITEMS.register("smogstem_boat", () -> new UGBoatItem(UGBoatEntity.Type.SMOGSTEM, (new Item.Properties()).tab(UGItemGroups.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> WIGGLEWOOD_BOAT = ITEMS.register("wigglewood_boat", () -> new UGBoatItem(UGBoatEntity.Type.WIGGLEWOOD, (new Item.Properties()).tab(UGItemGroups.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> GRONGLE_BOAT = ITEMS.register("grongle_boat", () -> new UGBoatItem(UGBoatEntity.Type.GRONGLE, (new Item.Properties()).tab(UGItemGroups.GROUP).stacksTo(1)));

    public static final RegistryObject<Item> VIRULENT_MIX_BUCKET = ITEMS.register("virulent_mix_bucket", () -> new BucketItem(
            UGFluids.VIRULENT_MIX_SOURCE, (new Item.Properties()).tab(UGItemGroups.GROUP).stacksTo(1)));

    public static final RegistryObject<Item> GWIBLING_BUCKET = ITEMS.register("gwibling_bucket", () -> new FishBucketItem(
            () -> UGEntityTypes.GWIBLING_TYPE, () -> Fluids.WATER, (new Item.Properties()).stacksTo(1).tab(UGItemGroups.GROUP)));

    //armors
    public static final RegistryObject<Item> CLOGGRUM_HELMET = ITEMS.register("cloggrum_helmet", () -> new UndergardenArmorItem(UGArmors.CLOGGRUM, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> CLOGGRUM_CHESTPLATE = ITEMS.register("cloggrum_chestplate", () -> new UndergardenArmorItem(UGArmors.CLOGGRUM, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> CLOGGRUM_LEGGINGS = ITEMS.register("cloggrum_leggings", () -> new UndergardenArmorItem(UGArmors.CLOGGRUM, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> CLOGGRUM_BOOTS = ITEMS.register("cloggrum_boots", () -> new UndergardenArmorItem(UGArmors.CLOGGRUM, EquipmentSlotType.FEET));

    public static final RegistryObject<Item> FROSTSTEEL_HELMET = ITEMS.register("froststeel_helmet", () -> new UndergardenArmorItem(UGArmors.FROSTSTEEL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> FROSTSTEEL_CHESTPLATE = ITEMS.register("froststeel_chestplate", () -> new UndergardenArmorItem(UGArmors.FROSTSTEEL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> FROSTSTEEL_LEGGINGS = ITEMS.register("froststeel_leggings", () -> new UndergardenArmorItem(UGArmors.FROSTSTEEL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> FROSTSTEEL_BOOTS = ITEMS.register("froststeel_boots", () -> new UndergardenArmorItem(UGArmors.FROSTSTEEL, EquipmentSlotType.FEET));

    public static final RegistryObject<Item> UTHERIUM_HELMET = ITEMS.register("utheric_helmet", () -> new UndergardenArmorItem(UGArmors.UTHERIC, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> UTHERIUM_CHESTPLATE = ITEMS.register("utheric_chestplate", () -> new UndergardenArmorItem(UGArmors.UTHERIC, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> UTHERIUM_LEGGINGS = ITEMS.register("utheric_leggings", () -> new UndergardenArmorItem(UGArmors.UTHERIC, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> UTHERIUM_BOOTS = ITEMS.register("utheric_boots", () -> new UndergardenArmorItem(UGArmors.UTHERIC, EquipmentSlotType.FEET));

    //foods/plants
    public static final RegistryObject<Item> DROOPFRUIT = ITEMS.register("droopvine_item", DroopfruitItem::new);
    public static final RegistryObject<Item> UNDERBEANS = ITEMS.register("underbeans", () -> new BlockNamedItem(
            UGBlocks.UNDERBEAN_BUSH.get(), (new Item.Properties()).tab(UGItemGroups.GROUP).food(UGFoods.UNDERBEANS)));
    public static final RegistryObject<Item> ROASTED_UNDERBEANS = ITEMS.register("roasted_underbeans", () -> new UGItem(UGFoods.ROASTED_UNDERBEANS));
    public static final RegistryObject<Item> BLISTERBERRY = ITEMS.register("blisterberry", () -> new BlockNamedItem(
            UGBlocks.BLISTERBERRY_BUSH.get(), (new Item.Properties()).tab(UGItemGroups.GROUP).food(UGFoods.BLISTERBERRY)));
    public static final RegistryObject<Item> ROTTEN_BLISTERBERRY = ITEMS.register("rotten_blisterberry", RottenBlisterberryItem::new);
    public static final RegistryObject<Item> GLOOMGOURD_PIE = ITEMS.register("gloomgourd_pie", () -> new UGItem(UGFoods.GLOOMGOURD_PIE));
    public static final RegistryObject<Item> RAW_DWELLER_MEAT = ITEMS.register("raw_dweller_meat", () -> new UGItem(UGFoods.RAW_DWELLER));
    public static final RegistryObject<Item> DWELLER_STEAK = ITEMS.register("dweller_steak", () -> new UGItem(UGFoods.COOKED_DWELLER));
    public static final RegistryObject<Item> RAW_GWIBLING = ITEMS.register("raw_gwibling", () -> new UGItem(UGFoods.RAW_GWIBLING));
    public static final RegistryObject<Item> COOKED_GWIBLING = ITEMS.register("cooked_gwibling", () -> new UGItem(UGFoods.COOKED_GWIBLING));
    public static final RegistryObject<Item> RAW_GLOOMPER_LEG = ITEMS.register("raw_gloomper_leg", () -> new UGItem(UGFoods.RAW_GLOOMPER_LEG));
    public static final RegistryObject<Item> GLOOMPER_LEG = ITEMS.register("gloomper_leg", () -> new UGItem(UGFoods.GLOOMPER_LEG));
    public static final RegistryObject<Item> GLOWING_KELP = ITEMS.register("glowing_kelp", () -> new BlockItem(UGBlocks.GLOWING_KELP.get(), (new Item.Properties()).tab(UGItemGroups.GROUP)));
    public static final RegistryObject<Item> GLOOMGOURD_SEEDS = ITEMS.register("gloomgourd_seeds", () -> new BlockNamedItem(UGBlocks.GLOOMGOURD_STEM.get(), (new Item.Properties()).tab(UGItemGroups.GROUP)));
    public static final RegistryObject<Item> BLOODY_STEW = ITEMS.register("bloody_stew", () -> new SoupItem((new Item.Properties()).tab(UGItemGroups.GROUP).food(UGFoods.BLOODY).stacksTo(1)));
    public static final RegistryObject<Item> INKY_STEW = ITEMS.register("inky_stew", () -> new SoupItem((new Item.Properties()).tab(UGItemGroups.GROUP).food(UGFoods.INKY).stacksTo(1)));
    public static final RegistryObject<Item> INDIGO_STEW = ITEMS.register("indigo_stew", () -> new SoupItem((new Item.Properties()).tab(UGItemGroups.GROUP).food(UGFoods.INDIGO).stacksTo(1)));
    public static final RegistryObject<Item> VEILED_STEW = ITEMS.register("veiled_stew", () -> new SoupItem((new Item.Properties()).tab(UGItemGroups.GROUP).food(UGFoods.VEILED).stacksTo(1)));

    //spawn eggs
    public static final RegistryObject<UGSpawnEggItem> DWELLER_SPAWN_EGG = ITEMS.register("dweller_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.DWELLER_TYPE, 4804417, 16776960));
    public static final RegistryObject<UGSpawnEggItem> GWIBLING_SPAWN_EGG = ITEMS.register("gwibling_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.GWIBLING_TYPE, 10064737, 15845330));
    public static final RegistryObject<UGSpawnEggItem> ROTDWELLER_SPAWN_EGG = ITEMS.register("rotdweller_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.ROTDWELLER_TYPE, 4804417, 16776960));
    public static final RegistryObject<UGSpawnEggItem> ROTLING_SPAWN_EGG = ITEMS.register("rotling_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.ROTLING_TYPE, 5590327, 10500660));
    public static final RegistryObject<UGSpawnEggItem> ROTWALKER_SPAWN_EGG = ITEMS.register("rotwalker_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.ROTWALKER_TYPE, 5590327, 10500660));
    public static final RegistryObject<UGSpawnEggItem> ROTBEAST_SPAWN_EGG = ITEMS.register("rotbeast_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.ROTBEAST_TYPE, 5590327,10500660));
    public static final RegistryObject<UGSpawnEggItem> BRUTE_SPAWN_EGG = ITEMS.register("brute_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.BRUTE_TYPE, 7035982, 4012083));
    public static final RegistryObject<UGSpawnEggItem> SCINTLING_SPAWN_EGG = ITEMS.register("scintling_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.SCINTLING_TYPE, 8556655, 6314558));
    public static final RegistryObject<UGSpawnEggItem> GLOOMPER_SPAWN_EGG = ITEMS.register("gloomper_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.GLOOMPER_TYPE, 4138045, 6579581));
    public static final RegistryObject<UGSpawnEggItem> STONEBORN_SPAWN_EGG = ITEMS.register("stoneborn_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.STONEBORN_TYPE, 3227179, 9502615));
    public static final RegistryObject<UGSpawnEggItem> NARGOYLE_SPAWN_EGG = ITEMS.register("nargoyle_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.NARGOYLE_TYPE, 3747634, 15508905));
    public static final RegistryObject<UGSpawnEggItem> MUNCHER_SPAWN_EGG = ITEMS.register("muncher_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.MUNCHER_TYPE, 2366466, 15881511));
    public static final RegistryObject<UGSpawnEggItem> SPLOOGIE_SPAWN_EGG = ITEMS.register("sploogie_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.SPLOOGIE_TYPE, 8209171, 13554509));
    public static final RegistryObject<UGSpawnEggItem> GWIB_SPAWN_EGG = ITEMS.register("gwib_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.GWIB_TYPE, 10064737, 4203803));
    public static final RegistryObject<UGSpawnEggItem> MOG_SPAWN_EGG = ITEMS.register("mog_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.MOG_TYPE, 3227179, 6393396));

    public static final RegistryObject<Item> MASTICATOR_SPAWN_EGG = ITEMS.register("masticator_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.MASTICATOR_TYPE, 2366466, 15881511));
    public static final RegistryObject<Item> FORGOTTEN_GUARDIAN_SPAWN_EGG = ITEMS.register("forgotten_guardian_spawn_egg", () -> new UGSpawnEggItem(UGEntityTypes.FORGOTTEN_GUARDIAN_TYPE, 8126397, 3170136));
}