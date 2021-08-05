package quek.undergarden.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import quek.undergarden.entity.projectile.SlingshotAmmoEntity;
import quek.undergarden.item.DepthrockPebbleItem;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class SlingshotItem extends ShootableItem {

    public SlingshotItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(192)
                .tab(UGItemGroups.GROUP)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.slingshot").withStyle(TextFormatting.GRAY));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == UGItems.DEPTHROCK_PEBBLE.get();
    }

    @Override
    public int getDefaultProjectileRange() {
        return 10;
    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entityLiving;
            boolean creativeOrInfinity = player.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemstack = player.getProjectile(stack);

            int i = getUseDuration(stack) - timeLeft;
            i = onArrowLoose(stack, worldIn, player, i, !itemstack.isEmpty() || creativeOrInfinity);
            if (i < 0) return;

            if (!itemstack.isEmpty() || creativeOrInfinity) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(UGItems.DEPTHROCK_PEBBLE.get());
                }

                float f = getProjectileVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.abilities.instabuild || (itemstack.getItem() instanceof DepthrockPebbleItem && ((DepthrockPebbleItem)itemstack.getItem()).isInfinite(itemstack, stack, player));
                    if (!worldIn.isClientSide) {
                        SlingshotAmmoEntity ammoEntity = new SlingshotAmmoEntity(worldIn, entityLiving);

                        ammoEntity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, f * 3.0F, 1.0F);

                        stack.hurtAndBreak(1, player, (entity) -> player.broadcastBreakEvent(player.getUsedItemHand()));

                        worldIn.addFreshEntity(ammoEntity);
                    }

                    worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_SHOOT.get(), SoundCategory.PLAYERS, 0.5F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !player.abilities.instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.inventory.removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand handIn) {
        ItemStack itemstack = player.getItemInHand(handIn);
        boolean hasAmmo = !player.getProjectile(itemstack).isEmpty();

        ActionResult<ItemStack> ret = onArrowNock(itemstack, worldIn, player, handIn, hasAmmo);
        if (ret != null) return ret;

        if (!player.abilities.instabuild && !hasAmmo) {
            return ActionResult.fail(itemstack);
        } else {
            player.startUsingItem(handIn);
            worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_DRAW.get(), SoundCategory.PLAYERS, 0.5F, 1.0F);
            return ActionResult.consume(itemstack);
        }
    }

    public static ActionResult<ItemStack> onArrowNock(ItemStack item, World world, PlayerEntity player, Hand hand, boolean hasAmmo)
    {
        ArrowNockEvent event = new ArrowNockEvent(player, item, hand, world, hasAmmo);
        if (MinecraftForge.EVENT_BUS.post(event))
            return new ActionResult<>(ActionResultType.FAIL, item);
        return event.getAction();
    }

    public static int onArrowLoose(ItemStack stack, World world, PlayerEntity player, int charge, boolean hasAmmo)
    {
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, world, charge, hasAmmo);
        if (MinecraftForge.EVENT_BUS.post(event))
            return -1;
        return event.getCharge();
    }

    public static float getProjectileVelocity(int charge) {
        float f = (float)charge / 5.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 36000;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.getItem().is(ItemTags.PLANKS);
    }
}