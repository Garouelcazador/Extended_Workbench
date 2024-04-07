package com.garouelcazador.extended_workbench.items.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ExtendedCrossbowItem extends CrossbowItem {

    public ExtendedCrossbowItem(Properties properties) {
        super(properties);
    }

    /*@Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (isCharged(itemstack)) {
            performShooting(level, player, interactionHand, itemstack, getShootingPower(itemstack), 1.0F);
            setCharged(itemstack, false);
            return InteractionResultHolder.consume(itemstack);
        } else if (!player.getProjectile(itemstack).isEmpty()) {
            if (!isCharged(itemstack)) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
                player.startUsingItem(interactionHand);
            }

            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }*/

    /*@Override
    public void releaseUsing(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity entity, int useDuration) {
        int i = this.getUseDuration(itemStack) - useDuration;
        float f = getPowerForTime(i, itemStack);
        if (f >= 1.0F && !isCharged(itemStack) && tryLoadProjectiles(entity, itemStack)) {
            setCharged(itemStack, true);
            if (f >= 1.5F) setExtendedCharge(itemStack, true);
            SoundSource soundsource = entity instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundsource, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }
    }*/

    @Override
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return (int) (super.getUseDuration(itemStack) * 18.0F / 11.0F);
    }

    /*protected static float getShootingPower(ItemStack itemStack) {
        float value = CrossbowItem.getShootingPower(itemStack);
        if (hasExtendedCharge(itemStack))
            value *= 1.5F;
        return value;
    }*/

    public static boolean hasExtendedCharge(ItemStack itemStack) {
        CompoundTag compoundtag = itemStack.getTag();
        return compoundtag != null && compoundtag.getBoolean("ExtendedCharge");
    }

    public static void setExtendedCharge(ItemStack itemStack, boolean value) {
        CompoundTag compoundtag = itemStack.getOrCreateTag();
        compoundtag.putBoolean("ExtendedCharge", value);
    }
}
