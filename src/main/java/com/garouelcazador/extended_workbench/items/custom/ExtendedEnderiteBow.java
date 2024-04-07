package com.garouelcazador.extended_workbench.items.custom;

import net.enderitemc.enderitemod.tools.EnderiteBow;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public class ExtendedEnderiteBow extends EnderiteBow {

    public ExtendedEnderiteBow(Properties properties) {
        super(properties);
    }

    /*@Override
    public void releaseUsing(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity entity, int useTime) {
        if (!(entity instanceof Player player)) return;
        boolean flag = player.getAbilities().instabuild || itemStack.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0;
        ItemStack itemstack = player.getProjectile(itemStack);

        int i = this.getUseDuration(itemStack) - useTime;
        i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(itemStack, level, player, i, !itemstack.isEmpty() || flag);
        if (i < 0) return;

        if (!itemstack.isEmpty() || flag) {
            if (itemstack.isEmpty())
                itemstack = new ItemStack(Items.ARROW);

            float f = getPowerForTime(i);
            if (f < 0.1F) return;
            boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, itemStack, player));
            if (!level.isClientSide) {
                ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                AbstractArrow abstractarrow = arrowitem.createArrow(level, itemstack, player);
                abstractarrow = customArrow(abstractarrow);
                abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                if (f >= 1.0F) {
                    abstractarrow.setCritArrow(true);
                }

                int j = itemStack.getEnchantmentLevel(Enchantments.POWER_ARROWS);
                if (j > 0)
                    abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double)j * 0.5D + 0.5D);

                int k = itemStack.getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
                if (k > 0)
                    abstractarrow.setKnockback(k);

                if (itemStack.getEnchantmentLevel(Enchantments.FLAMING_ARROWS) > 0)
                    abstractarrow.setSecondsOnFire(100);

                itemStack.hurtAndBreak(f >= 1.3F && level.random.nextInt(4) == 0 ? 2 : 1, player, (p_276007_) -> p_276007_.broadcastBreakEvent(player.getUsedItemHand()));
                if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                    abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                level.addFreshEntity(abstractarrow);
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if (!flag1 && !player.getAbilities().instabuild) {
                itemstack.shrink(1);
                if (itemstack.isEmpty())
                    player.getInventory().removeItem(itemstack);
            }

            player.awardStat(Stats.ITEM_USED.get(this));

        }
    }*/

    @Override
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return 44000;
    }

    /*@Override
    public @NotNull AbstractArrow customArrow(@NotNull AbstractArrow a) {
        AbstractArrow arrow = super.customArrow(a);
        arrow.getPersistentData().putBoolean("canHitEnderman", true);
        return arrow;
    }*/
}
